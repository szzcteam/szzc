package com.me.szzc.service;

import com.alibaba.fastjson.JSONObject;
import com.me.szzc.constant.Constant;
import com.me.szzc.dao.RmbRecompenseMapper;
import com.me.szzc.dao.SettleAccountsMapper;
import com.me.szzc.dao.SwapHouseMapper;
import com.me.szzc.enums.SigningStatusEnum;
import com.me.szzc.pojo.dto.*;
import com.me.szzc.pojo.entity.RmbRecompense;
import com.me.szzc.pojo.entity.SettleAccounts;
import com.me.szzc.pojo.entity.SwapHouse;
import com.me.szzc.pojo.vo.ProtocolCountMoneyVO;
import com.me.szzc.utils.BigDecimalUtil;
import com.me.szzc.utils.DateHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

@Slf4j
@Service
public class SettleAccountsService {

    @Autowired
    private SettleAccountsMapper settleAccountsMapper;

    @Autowired
    private SwapHouseMapper swapHouseMapper;

    @Autowired
    private RmbRecompenseMapper rmbRecompenseMapper;

    @Autowired
    private RoomChangeService roomChangeService;

    public void add(SettleAccounts settleAccounts) {
        Timestamp date = DateHelper.getTimestamp();
        settleAccounts.setCreateDate(date);
        settleAccounts.setModifiedDate(date);
        settleAccounts.setSigningDate(date);
        settleAccounts.setDeleted(false);
        settleAccounts.setSigningStatus(SigningStatusEnum.NOT_SIGNED.getCode());
        this.settleAccountsMapper.insert(settleAccounts);
    }

    public void delete(SettleAccounts settleAccounts) {
        settleAccounts.setModifiedDate(DateHelper.getTimestamp());
        settleAccounts.setDeleted(true);
        this.settleAccountsMapper.delete(settleAccounts);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteCase(Long userId, Long id, Long rmbId, Long swapId){
        SettleAccounts settleAccounts = this.settleAccountsMapper.selectByPrimaryKey(id);
        //修改人
        settleAccounts.setModifiedUserId(userId);
        //删除结算单
        this.delete(settleAccounts);
        //删除货币补偿协议
        if(rmbId != null && rmbId > 0){
            RmbRecompense recompense = rmbRecompenseMapper.getById(rmbId);
            recompense.setModifiedUserId(userId);
            recompense.setModifiedDate(DateHelper.getTimestamp());
            rmbRecompenseMapper.delete(recompense);
        }

        //删除产权调换协议
        if(swapId != null &&swapId > 0){
            SwapHouse swapHouse = swapHouseMapper.getById(swapId);
            swapHouse.setModifiedUserId(userId);
            swapHouse.setModifiedDate(DateHelper.getTimestamp());
            swapHouseMapper.delete(swapHouse);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public void update(SettleAccounts settleAccounts) {
        /**
         * 2020-01-12 luwei修改
         * 特殊情况，在新增了结算单、协议后，又修改结算单，并且修改了姓名或地址，导致之前增加的协议不显示，用于又开始新增协议
         * 影响：老协议仍旧存在
         * 修正：修改结算单时，修改了姓名或地址，关联修改协议
         */
        //根据结算单ID查询老结算单信息
        SettleAccounts oldSettle = settleAccountsMapper.selectByPrimaryKey(settleAccounts.getId());
        boolean isChangeFlag = false;
        if (StringUtils.isNoneBlank(oldSettle.getAddress(), settleAccounts.getAddress()) && !oldSettle.getAddress().equals(settleAccounts.getAddress())) {
            log.warn("修改结算单时，新旧地址不一样,oldAddress:{},newAddress:{}", oldSettle.getAddress(), settleAccounts.getAddress());
            isChangeFlag = true;
        }
        //地址没变，继续判断被征收人姓名是否有改变
        String oldHouseOwner = "";
        String newHouseOwner = "";
        if(!isChangeFlag) {
            if (StringUtils.isNoneBlank(oldSettle.getHouseOwner())) {
                oldHouseOwner = oldSettle.getHouseOwner();
            } else if (StringUtils.isNoneBlank(oldSettle.getLessee())) {
                oldHouseOwner = oldSettle.getLessee();
            }

            if (StringUtils.isNoneBlank(settleAccounts.getHouseOwner())) {
                newHouseOwner = settleAccounts.getHouseOwner();
            } else if (StringUtils.isNoneBlank(settleAccounts.getLessee())) {
                newHouseOwner = settleAccounts.getLessee();
            }

            if (!oldHouseOwner.equals(newHouseOwner)) {
                log.warn("修改结算单时，被征收人姓名不一样,oldHouseOwner:{},newHouseOwner:{}", oldHouseOwner, newHouseOwner);
                isChangeFlag = true;
            }

        }

        //有变动，要同时修改协议
        if(isChangeFlag){
            RmbRecompense rmbPO =  rmbRecompenseMapper.getByHouseOwnerAddr(oldHouseOwner, oldSettle.getAddress());
            SwapHouse swapPO = swapHouseMapper.getByHouseOwnerAddr(oldHouseOwner, oldSettle.getAddress());
            if(rmbPO != null){
                rmbPO.setHouseOwner(newHouseOwner);
                rmbPO.setAddress(settleAccounts.getAddress());
                rmbRecompenseMapper.updateByPrimaryKey(rmbPO);
            }

            if(swapPO != null){
                swapPO.setHouseOwner(newHouseOwner);
                swapPO.setAddress(settleAccounts.getAddress());
                swapHouseMapper.updateByPrimaryKey(swapPO);
            }
        }

        this.settleAccountsMapper.updateByPrimaryKey(settleAccounts);
    }

    public SettleAccounts query(SettleAccounts settleAccounts) {
        return this.settleAccountsMapper.selectByPrimaryKey(settleAccounts.getId());
    }

    public SettleAccounts getById(Long id){
        return this.settleAccountsMapper.selectByPrimaryKey(id);
    }

    public SettleAccounts getByHouseOwnerAddr(String houseOwner, String address) {
        return settleAccountsMapper.getByHouseOwnerAddr(houseOwner, address);
    }

    public SettleAccounts getByHouseOwnerAddrNeqId(String houseOwner, String address, Long id){
        return settleAccountsMapper.getByHouseOwnerAddrNeqId(houseOwner, address, id);
    }


    public List<SettleAccounts> list(int firstResult, int maxResults, boolean isFY, Integer signingStatus, String address, String houseOwner, Long areaId,
                                     List<Long> areaIdList, String startDate, String endDate, Integer compensateType, String cardNo) {
        List<SettleAccounts> listSettleAccounts = this.settleAccountsMapper.list(firstResult, maxResults, isFY, signingStatus, address, houseOwner, areaId,
                areaIdList, startDate, endDate, compensateType, cardNo);
        return listSettleAccounts;
    }

    public Integer getCount(Integer signingStatus, String address, String houseOwner, Long areaId, List<Long> areaIdList,
                            String startDate, String endDate, Integer compensateType, String cardNo) {
        return this.settleAccountsMapper.getCount(signingStatus, address, houseOwner, areaId, areaIdList, startDate, endDate, compensateType, cardNo);
    }

    /**变更签约状态**/
    @Transactional
    public Integer changeSignStatus(SettleAccounts settleAccounts){
        //如果是已签约的，则修改签约时间
        if(settleAccounts.getSigningStatus().equals(SigningStatusEnum.COMPLETE.getCode())){
            settleAccounts.setSigningDate(DateHelper.getTimestamp());
        }
        int result = this.settleAccountsMapper.changeSignStatus(settleAccounts);
        //2019-08-23号，注释，去掉自动点房映射的功能
        /*//签约完成，反向映射点房人
        if (settleAccounts.getSigningStatus().intValue() == SigningStatusEnum.COMPLETE.getCode()) {
            //根据签约地址，获取点房人(仅限产权调换的)，同一个旧地址，可能对应多个新房点方人
            List<ChooseHouseDTO> list = swapHouseMapper.getChooseHouseListBySettleId(settleAccounts.getId());
            if (list == null || list.isEmpty()) {
                return result;
            }

            Map<String, String> map = new HashMap<>();
            for (ChooseHouseDTO dto : list) {
                String value = dto.getHouseOwner();
                //以项目地址为key
                String key = dto.getNewHouseAddress() + "_" + dto.getSeat() + "_" + dto.getUnit() + "_" + dto.getFloors() + "_" + dto.getHouseNumber();
                String temp_value = map.get(key);
                //存在则更新
                if (StringUtils.isNotBlank(temp_value)) {
                    temp_value += "," + value;
                    map.put(key, temp_value);
                    continue;
                }
                map.put(key, value);
            }
            //更新房源中的点房人信息
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                String v_key[] = key.split("_");
                String newHouseAddress = v_key[0];
                Integer seat = Integer.valueOf(v_key[1]);
                Integer unit = Integer.valueOf(v_key[2]);
                Integer floors = Integer.valueOf(v_key[3]);
                Integer number = Integer.valueOf(v_key[4]);
                ChooseHouseDTO dto = new ChooseHouseDTO();
                dto.setAddress(settleAccounts.getAddress());
                dto.setHouseOwner(value);
                dto.setNewHouseAddress(newHouseAddress);
                dto.setSeat(seat);
                dto.setUnit(unit);
                dto.setFloors(floors);
                dto.setHouseNumber(number);
                log.info("点方信息dto:{}", JSONObject.toJSONString(dto));
                roomChangeService.chooseRoom(dto);
            }
        }*/
        return result;
    }

    public Integer updateSignDate(SettleAccounts settleAccounts) {
        int result = this.settleAccountsMapper.updateSignDate(settleAccounts);
        return result;
    }

    public Integer getNoSigning(List areaIdList, String startDate, String endDate) {
        int result = this.settleAccountsMapper.getNoSigning(SigningStatusEnum.NOT_SIGNED.getCode(), areaIdList, startDate, endDate);
        return result;
    }

    public List<SettleAccounts> getSigning(List areaIdList, String startDate, String endDate) {
        List<SettleAccounts> list = this.settleAccountsMapper.getSigning(SigningStatusEnum.NOT_SIGNED.getCode(), areaIdList, startDate, endDate);
        return list;
    }


    public List<SettleAccountsLineDTO> getTotalSign(List areaIdList, String startDate, String endDate) {
        List<SettleAccountsLineDTO> resultList = new ArrayList<>();

        //获取协议摘要
        List<ProtocolSummaryDTO> dataList = settleAccountsMapper.summaryList(null, areaIdList);
        if (dataList == null || dataList.isEmpty()) {
            return resultList;
        }

        //将摘要进行汇总，分成按天的集合
        Map<String, SettleAccountsLineDTO> map = new LinkedHashMap<>();
        for (int i = dataList.size() - 1; i >= 0; i--) {
            ProtocolSummaryDTO summaryDTO = dataList.get(i);
            SettleAccountsLineDTO dto = map.get(summaryDTO.getDate());
            if (dto == null) {
                dto = new SettleAccountsLineDTO();
            }
            dto.setDate(summaryDTO.getDate());
            //户数累加
            dto.setNum(dto.getNum() != null ? dto.getNum() + 1 : 1);
            //面积累加
            dto.setArea(dto.getArea() != null ? dto.getArea().add(summaryDTO.getArea()) : summaryDTO.getArea());
            //应付金额  根据新房金额，加正负号
            BigDecimal payTotal = null;
            if (summaryDTO.getHouseMoney().compareTo(summaryDTO.getSumCompensate()) > 0) {
                payTotal = (summaryDTO.getPayTotal().multiply(Constant.NEGATIVE_ONE));
            } else {
                payTotal = summaryDTO.getPayTotal();
            }

            dto.setPayTotal(dto.getPayTotal() != null ? dto.getPayTotal().add(payTotal) : payTotal);

            //用房数
            long useHouseNum = 0;
            //用房面积
            BigDecimal useHouseArea = BigDecimal.ZERO;

            if (summaryDTO.getSwapArea1() != null && summaryDTO.getSwapArea1().compareTo(BigDecimal.ZERO) > 0) {
                useHouseNum++;
                useHouseArea = useHouseArea.add(summaryDTO.getSwapArea1());
            }
            if (summaryDTO.getSwapArea2() != null && summaryDTO.getSwapArea2().compareTo(BigDecimal.ZERO) > 0) {
                useHouseNum++;
                useHouseArea = useHouseArea.add(summaryDTO.getSwapArea2());
            }

            if (summaryDTO.getSwapArea3() != null && summaryDTO.getSwapArea3().compareTo(BigDecimal.ZERO) > 0) {
                useHouseNum++;
                useHouseArea = useHouseArea.add(summaryDTO.getSwapArea3());
            }

            if (summaryDTO.getSwapArea4() != null && summaryDTO.getSwapArea4().compareTo(BigDecimal.ZERO) > 0) {
                useHouseNum++;
                useHouseArea = useHouseArea.add(summaryDTO.getSwapArea1());
            }
            if (summaryDTO.getSwapArea5() != null && summaryDTO.getSwapArea5().compareTo(BigDecimal.ZERO) > 0) {
                useHouseNum++;
                useHouseArea = useHouseArea.add(summaryDTO.getSwapArea5());
            }

            dto.setUseHouseNum(dto.getUseHouseNum() != null ? dto.getUseHouseNum() + useHouseNum: useHouseNum);
            dto.setUseHouseArea(dto.getUseHouseArea() != null ? dto.getUseHouseArea().add(useHouseArea) : useHouseArea);

            //总补偿金额
            dto.setSumCompensate(dto.getSumCompensate() != null ? dto.getSumCompensate().add(summaryDTO.getSumCompensate()) : summaryDTO.getSumCompensate());

            map.put(summaryDTO.getDate(), dto);
        }

        //获取每天的数据
        Collection<SettleAccountsLineDTO> dayDataList = map.values();


        //计算后数据集合
        List<SettleAccountsLineDTO> calcList = new ArrayList<>();

        //针对每天的进行累加
        Long upNum = 0L;
        BigDecimal upArea = BigDecimal.ZERO;
        BigDecimal upPayTotal = BigDecimal.ZERO;
        BigDecimal upUseHouseArea = BigDecimal.ZERO;
        BigDecimal upSumCompensate = BigDecimal.ZERO;
        Long upUseHouseNum = 0L;

        for (SettleAccountsLineDTO dto : dayDataList) {

            upNum = upNum + dto.getNum();
            upArea = upArea.add(dto.getArea());
            upUseHouseNum = upUseHouseNum + dto.getUseHouseNum();
            upUseHouseArea = upUseHouseArea.add(dto.getUseHouseArea());
            upSumCompensate = upSumCompensate.add(dto.getSumCompensate().divide(Constant.ONE_Y, 4, BigDecimal.ROUND_DOWN));

            //计算用房率：房源使用套数/总征收户数
            BigDecimal useRate = new BigDecimal(upUseHouseNum).divide(new BigDecimal(upNum), 2);

            //做累计的时候，按4位小数进行
            upPayTotal = upPayTotal.add(dto.getPayTotal().divide(Constant.ONE_Y, 4, BigDecimal.ROUND_DOWN));

            SettleAccountsLineDTO lineDTO = new SettleAccountsLineDTO();
            lineDTO.setDate(dto.getDate());
            lineDTO.setDateTime(DateHelper.string2Date(dto.getDate(), DateHelper.DateFormatType.YearMonthDay));
            lineDTO.setNum(upNum);
            lineDTO.setArea(upArea);
            //页面显示 保留2位小数
            lineDTO.setPayTotal(upPayTotal.setScale(2, BigDecimal.ROUND_DOWN));
            lineDTO.setSumCompensate(upSumCompensate.setScale(2, BigDecimal.ROUND_DOWN));

            lineDTO.setUseHouseNum(upUseHouseNum);
            lineDTO.setUseHouseArea(upUseHouseArea);
            lineDTO.setUseRate(useRate);
            calcList.add(lineDTO);
        }

        //根据时间范围扣取符合条件的
        if (StringUtils.isAllBlank(startDate, endDate)) {
            //时间为空，返回所有的
            resultList.addAll(calcList);
            return resultList;
        }

        Long endDateTime = null;
        //结束时间为空，则默认为系统时间
        if (StringUtils.isBlank(endDate)) {
            endDateTime = Calendar.getInstance().getTime().getTime();
        } else {
            endDateTime = DateHelper.string2Date(endDate, DateHelper.DateFormatType.YearMonthDay).getTime();
        }

        //开始时间为空，则默认为数据的第一条记录时间
        Long startDateTime = null;
        if (StringUtils.isBlank(startDate)) {
            startDateTime = DateHelper.string2Date(dataList.get(0).getDate(), DateHelper.DateFormatType.YearMonthDay).getTime();
        } else {
            startDateTime = DateHelper.string2Date(startDate, DateHelper.DateFormatType.YearMonthDay).getTime();
        }

        log.info("折线图数据搜索的时间,startDate:{},endDate:{}", startDate, endDate);

        for (SettleAccountsLineDTO dto : calcList) {
            Long time = dto.getDateTime().getTime();
            if (time >= startDateTime && time <= endDateTime) {
                resultList.add(dto);
            }
        }


        return resultList;
    }


    /**协议状态统计**/
    public List<ProtocolCountStatusDTO> countStatus(){
        List<ProtocolCountStatusDTO> list = settleAccountsMapper.countStatus();
        return list;
    }

    public List<ProtocolCountMoneyVO> countMoney(){
        List<ProtocolCountMoneyDTO> list = settleAccountsMapper.countMoney();
        if(list == null || list.isEmpty()){
            return Collections.emptyList();
        }

        List<ProtocolCountMoneyVO> voList = new ArrayList<>();
        for(ProtocolCountMoneyDTO dto : list){
            voList.add(dto.toVo());
        }

        return voList;

    }


    /**微信小程序 获取协议摘要**/
    public List<List<Object>> summaryListByWx(String projectCode) {
        List<List<Object>> resultList = new ArrayList<>();
        List<ProtocolSummaryDTO> list = settleAccountsMapper.summaryList(projectCode, null);
        list.forEach(dto -> {
            //金额转亿元
            BigDecimal sumCompensate = dto.getSumCompensate().divide(Constant.ONE_Y, 4, BigDecimal.ROUND_DOWN);
            BigDecimal payTotal = dto.getPayTotal().divide(Constant.ONE_Y, 4, BigDecimal.ROUND_DOWN);

            //每一行是个数组
            List<Object> row = new ArrayList<>();
            row.add(dto.getDate());
            row.add(dto.getArea().setScale(2, BigDecimal.ROUND_DOWN).stripTrailingZeros());
            row.add(sumCompensate);
            //根据新房金额，加正负号
            if (dto.getHouseMoney().compareTo(dto.getSumCompensate()) > 0) {
                row.add((payTotal.multiply(Constant.NEGATIVE_ONE)).stripTrailingZeros());
            } else {
                row.add(payTotal.stripTrailingZeros());
            }

            row.add(dto.getCompensateType());
            row.add(dto.getDate());  //过审时间
            row.add(dto.getDate());  //付款时间
            row.add(StringUtils.isNotBlank(dto.getNewHouseName()) ? dto.getNewHouseName() : "");
//            row.add(dto.getChooseArea());
            if (dto.getSwapArea1() != null && dto.getSwapArea1().compareTo(BigDecimal.ZERO) > 0) {
                row.add(dto.getSwapArea1().setScale(2, BigDecimal.ROUND_DOWN).stripTrailingZeros());
            } else {
                row.add(0);
            }
            //除第一套房外，其他的面积非必填项
            if (dto.getSwapArea2() != null && dto.getSwapArea2().compareTo(BigDecimal.ZERO) > 0) {
                row.add(dto.getSwapArea2().setScale(2, BigDecimal.ROUND_DOWN).stripTrailingZeros());
            }
            if (dto.getSwapArea3() != null && dto.getSwapArea3().compareTo(BigDecimal.ZERO) > 0) {
                row.add(dto.getSwapArea3().setScale(2, BigDecimal.ROUND_DOWN).stripTrailingZeros());
            }
            if (dto.getSwapArea4() != null && dto.getSwapArea4().compareTo(BigDecimal.ZERO) > 0) {
                row.add(dto.getSwapArea4().setScale(2, BigDecimal.ROUND_DOWN).stripTrailingZeros());
            }
            if (dto.getSwapArea5() != null && dto.getSwapArea5().compareTo(BigDecimal.ZERO) > 0) {
                row.add(dto.getSwapArea5().setScale(2, BigDecimal.ROUND_DOWN).stripTrailingZeros());
            }
            resultList.add(row);
        });
        return resultList;
    }
}
