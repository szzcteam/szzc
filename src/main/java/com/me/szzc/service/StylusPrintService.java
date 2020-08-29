package com.me.szzc.service;

import com.me.szzc.constant.Constant;
import com.me.szzc.dao.*;
import com.me.szzc.enums.GovernmentEnum;
import com.me.szzc.enums.PrintTableEnum;
import com.me.szzc.enums.PrintTypeEnum;
import com.me.szzc.pojo.dto.FieldCoordinateDto;
import com.me.szzc.pojo.entity.*;
import com.me.szzc.pojo.vo.PaymentNoticeVO;
import com.me.szzc.pojo.vo.RmbRecompenseVO;
import com.me.szzc.pojo.vo.SettleAccountsVO;
import com.me.szzc.pojo.vo.SwapHouseVO;
import com.me.szzc.utils.NumberToCapitalizedUtils;
import com.me.szzc.utils.PrintUtil;
import com.me.szzc.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 针式打印service
 * Created by bbfang on 2019/8/12.
 */
@Service
public class StylusPrintService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SettleAccountsMapper settleAccountsMapper;

    @Autowired
    private RmbRecompenseMapper rmbRecompenseMapper;

    @Autowired
    private SwapHouseMapper swapHouseMapper;

    @Autowired
    private FieldCoordinateMapper fieldCoordinateMapper;

    @Autowired
    private AreaService areaService;

    @Autowired
    private PrintUtil printUtil;

    @Autowired
    private RoomChangeMapper roomChangeMapper;

    public List<Map<String, Object>> settleAccountsPrint(Long id) throws Exception {
        //根据ID获取数据
        SettleAccounts settleAccounts = settleAccountsMapper.selectByPrimaryKey(id);
        if (StringUtils.isNullOrEmpty(settleAccounts)) {
            logger.error("房屋征收补偿计算表打印获取数据为空,id(" + id + ")");
            return null;
        }
        SettleAccountsVO vo = SettleAccountsVO.parse(settleAccounts);
        //获取项目编码
        Area area = areaService.getById(settleAccounts.getAreaId());
        String projectCode = area.getProjectCode();
        //获取打印数据坐标
        List<FieldCoordinateDto> FieldCoordinateList =
                fieldCoordinateMapper.getFieldCoordinateListByTableName(PrintTableEnum.HOUSE_EXPROPRIATION_COMPENSATION.getName(), projectCode);
        if (StringUtils.isNullOrEmpty(FieldCoordinateList)) {
            logger.error("房屋征收补偿计算表打印获取坐标数据为空,tableName(" + PrintTableEnum.HOUSE_EXPROPRIATION_COMPENSATION.getName() + ")");
            return null;
        }
        List<Map<String, Object>> dataList = new ArrayList();
        Map<String, Object> map = null;
        //打印数据封装
        for (FieldCoordinateDto fieldCoordinateDto : FieldCoordinateList) {
            map = new HashMap();
            //结算方式：0货币补偿、1产权交换
            if ("compensateType0".equals(fieldCoordinateDto.getCode())) {
                if (vo.getCompensateType() == 0) {
                    map.put("data", "✔");
                    map.put("fontName", fieldCoordinateDto.getFontName());
                    map.put("fontSize", fieldCoordinateDto.getFontSize());
                    map.put("x", fieldCoordinateDto.getAbscissa());
                    map.put("y", fieldCoordinateDto.getOrdinate());
                    map.put("width", fieldCoordinateDto.getWidth());
                    map.put("height", fieldCoordinateDto.getHeight());
                    dataList.add(map);
                }
                continue;
            }
            if ("compensateType1".equals(fieldCoordinateDto.getCode())) {
                if (vo.getCompensateType() == 1) {
                    map.put("data", "✔");
                    map.put("fontName", fieldCoordinateDto.getFontName());
                    map.put("fontSize", fieldCoordinateDto.getFontSize());
                    map.put("x", fieldCoordinateDto.getAbscissa());
                    map.put("y", fieldCoordinateDto.getOrdinate());
                    map.put("width", fieldCoordinateDto.getWidth());
                    map.put("height", fieldCoordinateDto.getHeight());
                    dataList.add(map);
                }
                continue;
            }
            String value = getFieldValueByFieldName(fieldCoordinateDto.getCode(), vo);
            if (StringUtils.isNullOrEmpty(value)) {
                continue;
            }
            map.put("data", value);
            map.put("fontName", fieldCoordinateDto.getFontName());
            map.put("fontSize", fieldCoordinateDto.getFontSize());
            map.put("x", fieldCoordinateDto.getAbscissa());
            map.put("y", fieldCoordinateDto.getOrdinate());
            map.put("width", fieldCoordinateDto.getWidth());
            map.put("height", fieldCoordinateDto.getHeight());
            dataList.add(map);
        }
        if (StringUtils.isNullOrEmpty(dataList)) {
            logger.error("房屋征收补偿计算表打印获取数据为空");
            return null;
        }

        return dataList;

    }

    public List<Map<String, Object>> houseSwapPrint(Long id) throws Exception {
        //根据ID获取数据
        SwapHouse swapHouse = swapHouseMapper.getById(id);
        if (StringUtils.isNullOrEmpty(swapHouse)) {
            logger.error("产权调换打印获取数据为空,id(" + id + ")");
            return null;
        }
        SwapHouseVO vo = SwapHouseVO.parse(swapHouse);
        //获取项目编码
        Area area = areaService.getById(swapHouse.getAreaId());
        String projectCode = area.getProjectCode();
        //数据特殊处理：紫阳村模板少打印一个百分号
        if (area.getProjectCode().equalsIgnoreCase(GovernmentEnum.ZYC.getCode())) {
            vo.setHistoryProportion(vo.getHistoryProportion() + "%");
        }
        //获取打印数据坐标
        List<FieldCoordinateDto> FieldCoordinateList =
                fieldCoordinateMapper.getFieldCoordinateListByTableName(PrintTableEnum.HOUSE_SWAP.getName(), projectCode);
        if (StringUtils.isNullOrEmpty(FieldCoordinateList)) {
            logger.error("产权调换打印获取坐标数据为空,tableName(" + PrintTableEnum.HOUSE_SWAP.getName() + ")");
            return null;
        }
        List<Map<String, Object>> dataList = new ArrayList();
        Map<String, Object> map = null;
        //打印数据封装
        for (FieldCoordinateDto fieldCoordinateDto : FieldCoordinateList) {
            map = new HashMap();
            String value = getFieldValueByFieldName(fieldCoordinateDto.getCode(), vo);
            if (StringUtils.isNullOrEmpty(value)) {
                continue;
            }
            map.put("data", value);
            map.put("fontName", fieldCoordinateDto.getFontName());
            map.put("fontSize", fieldCoordinateDto.getFontSize());
            map.put("x", fieldCoordinateDto.getAbscissa());
            map.put("y", fieldCoordinateDto.getOrdinate());
            map.put("width", fieldCoordinateDto.getWidth());
            map.put("height", fieldCoordinateDto.getHeight());
            dataList.add(map);
        }
        if (StringUtils.isNullOrEmpty(dataList)) {
            logger.error("产权调换补助打印获取数据为空");
            return null;
        }

        return dataList;
    }

    public List<Map<String, Object>> rmbRecompensePrint(Long id) throws Exception {
        //根据ID获取数据
        RmbRecompense recompense = rmbRecompenseMapper.getById(id);
        if (StringUtils.isNullOrEmpty(recompense)) {
            logger.error("货币补偿补助打印获取数据为空,id(" + id + ")");
            return null;
        }
        RmbRecompenseVO vo = RmbRecompenseVO.parse(recompense);
        //获取项目编码
        Area area = areaService.getById(recompense.getAreaId());
        String projectCode = area.getProjectCode();
        //数据特殊处理：紫阳村模板少打印一个百分号
        if (area.getProjectCode().equalsIgnoreCase(GovernmentEnum.ZYC.getCode())) {
            vo.setHistoryProportion(vo.getHistoryProportion() + "%");
        }
        //获取打印数据坐标
        List<FieldCoordinateDto> FieldCoordinateList =
                fieldCoordinateMapper.getFieldCoordinateListByTableName(PrintTableEnum.HOUSE_RMB_RECOMPENSE.getName(), projectCode);
        if (StringUtils.isNullOrEmpty(FieldCoordinateList)) {
            logger.error("货币补偿补助打印获取坐标数据为空,tableName(" + PrintTableEnum.HOUSE_RMB_RECOMPENSE.getName() + ")");
            return null;
        }
        List<Map<String, Object>> dataList = new ArrayList();
        Map<String, Object> map = null;
        //打印数据封装
        for (FieldCoordinateDto fieldCoordinateDto : FieldCoordinateList) {
            map = new HashMap();
            String value = getFieldValueByFieldName(fieldCoordinateDto.getCode(), vo);
            if (StringUtils.isNullOrEmpty(value)) {
                continue;
            }
            map.put("data", value);
            map.put("fontName", fieldCoordinateDto.getFontName());
            map.put("fontSize", fieldCoordinateDto.getFontSize());
            map.put("x", fieldCoordinateDto.getAbscissa());
            map.put("y", fieldCoordinateDto.getOrdinate());
            map.put("width", fieldCoordinateDto.getWidth());
            map.put("height", fieldCoordinateDto.getHeight());
            dataList.add(map);
        }
        if (StringUtils.isNullOrEmpty(dataList)) {
            logger.error("货币补偿补助打印获取数据为空");
            return null;
        }
        return dataList;
    }

    /**
     * 房屋征收补偿计算表打印实现
     *
     * @param id
     */
    public boolean houseExpropriationCompensationPrint(Long id) throws Exception {
        //根据ID获取数据
        SettleAccounts settleAccounts = settleAccountsMapper.selectByPrimaryKey(id);
        if (StringUtils.isNullOrEmpty(settleAccounts)) {
            logger.error("房屋征收补偿计算表打印获取数据为空,id(" + id + ")");
            return false;
        }
        SettleAccountsVO vo = SettleAccountsVO.parse(settleAccounts);
        vo.setSwapPrice(vo.getSwapPrice1() + " " + vo.getSwapPrice2() + " " + vo.getSwapPrice3() + " " + vo.getSwapPrice4() + " " + vo.getSwapPrice5());
        vo.setSwapArea(vo.getSwapArea1() + " " + vo.getSwapArea2() + " " + vo.getSwapArea3() + " " + vo.getSwapArea4() + " " + vo.getSwapArea5());
        vo.setSwapMoney(vo.getSwapMoney1() + " " + vo.getSwapMoney2() + " " + vo.getSwapMoney3() + " " + vo.getSwapMoney4() + " " + vo.getSwapMoney5());
        //获取项目编码
        Area area = areaService.getById(settleAccounts.getAreaId());
        String projectCode = area.getProjectCode();
        //获取打印数据坐标
        List<FieldCoordinateDto> FieldCoordinateList =
                fieldCoordinateMapper.getFieldCoordinateListByTableName(PrintTableEnum.HOUSE_EXPROPRIATION_COMPENSATION.getName(), projectCode);
        if (StringUtils.isNullOrEmpty(FieldCoordinateList)) {
            logger.error("房屋征收补偿计算表打印获取坐标数据为空,tableName(" + PrintTableEnum.HOUSE_EXPROPRIATION_COMPENSATION.getName() + ")");
            return false;
        }
        List<Map<String, Object>> dataList = new ArrayList();
        Map<String, Object> map = null;
        //打印数据封装
        for (FieldCoordinateDto fieldCoordinateDto : FieldCoordinateList) {
            map = new HashMap();
            //结算方式：0货币补偿、1产权交换
            if ("compensateType0".equals(fieldCoordinateDto.getCode())) {
                if (vo.getCompensateType() == 0) {
                    map.put("data", "✔");
                    map.put("fontName", fieldCoordinateDto.getFontName());
                    map.put("fontSize", fieldCoordinateDto.getFontSize());
                    map.put("x", fieldCoordinateDto.getAbscissa());
                    map.put("y", fieldCoordinateDto.getOrdinate());
                    dataList.add(map);
                }
                continue;
            }
            if ("compensateType1".equals(fieldCoordinateDto.getCode())) {
                if (vo.getCompensateType() == 1) {
                    map.put("data", "✔");
                    map.put("fontName", fieldCoordinateDto.getFontName());
                    map.put("fontSize", fieldCoordinateDto.getFontSize());
                    map.put("x", fieldCoordinateDto.getAbscissa());
                    map.put("y", fieldCoordinateDto.getOrdinate());
                    dataList.add(map);
                }
                continue;
            }
            String value = getFieldValueByFieldName(fieldCoordinateDto.getCode(), vo);
            if (StringUtils.isNullOrEmpty(value)) {
                continue;
            }
            map.put("data", value);
            map.put("fontName", fieldCoordinateDto.getFontName());
            map.put("fontSize", fieldCoordinateDto.getFontSize());
            map.put("x", fieldCoordinateDto.getAbscissa());
            map.put("y", fieldCoordinateDto.getOrdinate());
            dataList.add(map);
        }
        if (StringUtils.isNullOrEmpty(dataList)) {
            logger.error("房屋征收补偿计算表打印获取数据为空");
            return false;
        }
        //调用打印工具
        printUtil.starPrint(PrintTypeEnum.LENGTHWAYS.getCode(), dataList);
        return true;

    }

    /**
     * 反射获取字段值
     *
     * @param fieldName
     * @param object
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static String getFieldValueByFieldName(String fieldName, Object object) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        //对private的属性的访问
        field.setAccessible(true);
        Object objValue = field.get(object);
        if (StringUtils.isNullOrEmpty(objValue)) {
            return null;
        } else {
            return objValue.toString();
        }

    }

    /*
    交房通知书打印
     */

    public List<Map<String, Object>> noticePrint(Long id) throws NoSuchFieldException, IllegalAccessException {
        //1、根据房源ID获取数据
        RoomChange roomChange = roomChangeMapper.getRoomChangeById(id);
        //2、判断roomChange是否为空
        if (StringUtils.isNullOrEmpty(roomChange)) {
            return null;
        }
        //3、判断状态是否已签
        if (roomChange.getStatus() != 1) {
            return null;
        }
        //4、通过姓名获取结算单数据
        SettleAccounts settleAccounts = settleAccountsMapper.getByHouseOwner(roomChange.getChoosePeople());
        if (StringUtils.isNullOrEmpty(settleAccounts)) {
            return null;
        }
        //创建甲方通知书实体类
        PaymentNoticeVO paymentNoticeVO = new PaymentNoticeVO();
        //5、用人名获取产权调换数据
        if (settleAccounts.getCompensateType() == 0) {
            RmbRecompense recompense = rmbRecompenseMapper.getByHouseOwnerAddr(settleAccounts.getHouseOwner(), settleAccounts.getAddress());
            paymentNoticeVO.setIdentityNo(recompense.getIdentityNo());
        } else if (settleAccounts.getCompensateType() == 1) {
            SwapHouse swapHouse = swapHouseMapper.getByHouseOwnerAddr(settleAccounts.getHouseOwner(), settleAccounts.getAddress());
            paymentNoticeVO.setIdentityNo(swapHouse.getIdentityNo());
        }

        //6、放入实体类
        paymentNoticeVO.setName(roomChange.getName());
        paymentNoticeVO.setCardNo(settleAccounts.getCardNo());
        paymentNoticeVO.setHouseOwner(settleAccounts.getHouseOwner());
        paymentNoticeVO.setProjectCode(settleAccounts.getProjectName());
        paymentNoticeVO.setRidgepole(roomChange.getRidgepole());
        paymentNoticeVO.setUnit(roomChange.getUnit());
        paymentNoticeVO.setFloor(roomChange.getFloor());
        paymentNoticeVO.setMark(roomChange.getMark());
        paymentNoticeVO.setArea(roomChange.getArea());

        paymentNoticeVO.setSumRbm(settleAccounts.getHouseMoney().toPlainString());
        paymentNoticeVO.setTransferRmb(settleAccounts.getDeduction().toPlainString());

        if (settleAccounts.getSumCompensate().compareTo(settleAccounts.getHouseMoney()) > 0) {
            paymentNoticeVO.setDifference(settleAccounts.getPayTotal().toPlainString());
        } else {
            paymentNoticeVO.setLessDifference(settleAccounts.getPayTotal().toPlainString());
        }

        //7、金额大写拆分存储
        String tempMoney = paymentNoticeVO.getSumRbm();
        if (tempMoney.length() < 8) {
            tempMoney = "00000000" + tempMoney;
        }
        tempMoney = tempMoney.substring(tempMoney.length() - 8, tempMoney.length());
        paymentNoticeVO.setPayParm1(NumberToCapitalizedUtils.CHINESE_NUM_MAP.get(Integer.valueOf(tempMoney.substring(tempMoney.length() - 1, tempMoney.length()))));
        paymentNoticeVO.setPayParm2(NumberToCapitalizedUtils.CHINESE_NUM_MAP.get(Integer.valueOf(tempMoney.substring(tempMoney.length() - 2, tempMoney.length() - 1))));
        paymentNoticeVO.setPayParm3(NumberToCapitalizedUtils.CHINESE_NUM_MAP.get(Integer.valueOf(tempMoney.substring(tempMoney.length() - 3, tempMoney.length() - 2))));
        paymentNoticeVO.setPayParm4(NumberToCapitalizedUtils.CHINESE_NUM_MAP.get(Integer.valueOf(tempMoney.substring(tempMoney.length() - 4, tempMoney.length() - 3))));
        paymentNoticeVO.setPayParm5(NumberToCapitalizedUtils.CHINESE_NUM_MAP.get(Integer.valueOf(tempMoney.substring(tempMoney.length() - 5, tempMoney.length() - 4))));
        paymentNoticeVO.setPayParm6(NumberToCapitalizedUtils.CHINESE_NUM_MAP.get(Integer.valueOf(tempMoney.substring(tempMoney.length() - 6, tempMoney.length() - 5))));
        paymentNoticeVO.setPayParm7(NumberToCapitalizedUtils.CHINESE_NUM_MAP.get(Integer.valueOf(tempMoney.substring(tempMoney.length() - 7, tempMoney.length() - 6))));
        paymentNoticeVO.setPayParm8(NumberToCapitalizedUtils.CHINESE_NUM_MAP.get(Integer.valueOf(tempMoney.substring(tempMoney.length() - 8, tempMoney.length() - 7))));

        if (paymentNoticeVO.getPayParm8().equals(Constant.CHINESE_ZERO)) {
            paymentNoticeVO.setPayParm8("");
        }
        if (org.apache.commons.lang3.StringUtils.isBlank(paymentNoticeVO.getPayParm8()) && paymentNoticeVO.getPayParm7().equals(Constant.CHINESE_ZERO)) {
            paymentNoticeVO.setPayParm7("");
        }
        if (org.apache.commons.lang3.StringUtils.isAllBlank(paymentNoticeVO.getPayParm8(), paymentNoticeVO.getPayParm7()) && paymentNoticeVO.getPayParm6().equals(Constant.CHINESE_ZERO)) {
            paymentNoticeVO.setPayParm6("");
        }
        //8、获取打印数据坐标
        List<FieldCoordinateDto> FieldCoordinateList =
                fieldCoordinateMapper.getNoticeFieldCoordinateList(PrintTableEnum.HOUSE_NOTICE.getName());
        if (StringUtils.isNullOrEmpty(FieldCoordinateList)) {
            logger.error("交房通知书打印获取坐标数据为空,tableName(" + PrintTableEnum.HOUSE_NOTICE.getName() + ")");
            return null;
        }
        List<Map<String, Object>> dataList = new ArrayList();
        Map<String, Object> map = null;
        //9、打印数据封装
        for (FieldCoordinateDto fieldCoordinateDto : FieldCoordinateList) {
            map = new HashMap();
            String value = getFieldValueByFieldName(fieldCoordinateDto.getCode(), paymentNoticeVO);
            if (StringUtils.isNullOrEmpty(value)) {
                continue;
            }
            map.put("data", value);
            map.put("fontName", fieldCoordinateDto.getFontName());
            map.put("fontSize", fieldCoordinateDto.getFontSize());
            map.put("x", fieldCoordinateDto.getAbscissa());
            map.put("y", fieldCoordinateDto.getOrdinate());
            dataList.add(map);
        }
        if (StringUtils.isNullOrEmpty(dataList)) {
            logger.error("交房通知书打印获取数据为空");
            return null;
        }
        //调用打印工具
        //printUtil.starPrint(PrintTypeEnum.LENGTHWAYS.getCode(), dataList);
        return dataList;
    }
}
