package com.me.szzc.controller;

import com.me.szzc.enums.CompensateTypeEnum;
import com.me.szzc.enums.SigningStatusEnum;
import com.me.szzc.pojo.entity.Area;
import com.me.szzc.pojo.entity.RmbRecompense;
import com.me.szzc.pojo.entity.SettleAccounts;
import com.me.szzc.pojo.entity.SwapHouse;
import com.me.szzc.pojo.vo.ProtocolExportVO;
import com.me.szzc.pojo.vo.RmbRecompenseVO;
import com.me.szzc.pojo.vo.SwapHouseVO;
import com.me.szzc.utils.BigDecimalUtil;
import com.me.szzc.utils.DateHelper;
import com.me.szzc.utils.excle.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luwei
 * @date 2019/9/7
 */
@Slf4j
@Controller
public class ProtocolExportController extends BaseController {

    @RequestMapping("/ssadmin/protocol/export/excel")
    public void export(HttpServletRequest request, HttpServletResponse response) {

        //获取用户管理的片区
        Long userId = getAdminSession(request).getFid();
        List<Area> areaList = getUserEnableArea(userId);
        //转换格式
        List<Long> areaIdList = convertAreaIdList(areaList);

        //查询权限下片区信息
        Map<Long, String> areaMap = getUserAreaMap(userId);

        //查询产权调换
        List<SwapHouse> swapHouseList = swapHouseService.listAll(areaIdList);
        Map<String, SwapHouseVO> swapMap = new HashMap<>();
        try {
            if (swapHouseList != null && swapHouseList.size() > 0) {
                for (SwapHouse po : swapHouseList) {
                    SwapHouseVO vo = SwapHouseVO.parse(po);
                    String key = vo.getAddress().trim() + "|" + vo.getHouseOwner().trim();
                    swapMap.put(key, vo);
                }
            }
        } catch (Exception e) {
            log.error("导出协议时，产权调换信息转换异常", e);
        }

        //查询货币补偿
        List<RmbRecompense> rmbRecompenseList = rmbRecompenseService.listAll(areaIdList);
        Map<String, RmbRecompenseVO> rmbMap = new HashMap<>();
        try {
            if (rmbRecompenseList != null && rmbRecompenseList.size() > 0) {
                for (RmbRecompense po : rmbRecompenseList) {
                    RmbRecompenseVO vo = RmbRecompenseVO.parse(po);
                    String key = vo.getAddress().trim() + "|" + vo.getHouseOwner().trim();
                    rmbMap.put(key, vo);
                }
            }
        } catch (Exception e) {
            log.error("导出协议时，货币补偿信息转换异常", e);
        }

        //查询结算单
        List<SettleAccounts> settleAccountsList = settleAccountsService.list(0, 0, false,
                null, null, null, null,
                areaIdList, null, null, null);

        //融合数据，生成excel vo
        List<ProtocolExportVO> list = new ArrayList<>();
        if(settleAccountsList != null && !settleAccountsList.isEmpty()) {
            for (SettleAccounts settle : settleAccountsList) {
                String houseOwner = StringUtils.isNotBlank(settle.getHouseOwner()) ? settle.getHouseOwner() : settle.getLessee();
                String key = settle.getAddress().trim() + "|" + houseOwner.trim();
                ProtocolExportVO exportVO = new ProtocolExportVO();

                //取货币补偿
                RmbRecompenseVO rmbVo = rmbMap.get(key);
                //取产权调换
                SwapHouseVO swapVo = swapMap.get(key);
                if (rmbVo != null && swapVo != null) {
                    log.error("异常，同一个客户，同时存在货币和调换协议，key:{}", key);
                    continue;
                } else if (rmbVo == null && swapVo == null) {
                    log.error("当前只有结算单信息，没有签订协议,key:{}", key);
                    continue;
                }


                if (rmbVo != null) {
                    BeanUtils.copyProperties(rmbVo, exportVO);
                    exportVO.setProtocolType(CompensateTypeEnum.RMB_TYPE.getDesc());
                    exportVO.setDifference(rmbVo.getSumRbm());
                    //计算热水器总和
                    BigDecimal heater = new BigDecimal(StringUtils.isNotBlank(rmbVo.getSolarHeater()) ? rmbVo.getSolarHeater() : "0")
                            .add(new BigDecimal(StringUtils.isNotBlank(rmbVo.getOtherHeater()) ? rmbVo.getOtherHeater() : "0"));
                    if (heater.compareTo(BigDecimal.ZERO) > 0) {
                        exportVO.setHeater(BigDecimalUtil.stripTrailingZeros(heater));
                    }

                } else if (swapVo != null) {
                    BeanUtils.copyProperties(swapVo, exportVO);
                    exportVO.setProtocolType(CompensateTypeEnum.SWAP_TYPE.getDesc());
                    //拼接房号
                    String newHouseAddress = "";
                    if (StringUtils.isNotBlank(swapVo.getSeat())) {
                        newHouseAddress += swapVo.getSeat() + "栋";
                    }
                    if (StringUtils.isNotBlank(swapVo.getUnit())) {
                        newHouseAddress += swapVo.getUnit() + "单元";
                    }
                    if (StringUtils.isNotBlank(swapVo.getFloors())) {
                        newHouseAddress += swapVo.getFloors() + "层";
                    }
                    if (StringUtils.isNotBlank(swapVo.getHouseNumber())) {
                        newHouseAddress += swapVo.getHouseNumber() + "号房";
                    }

                    exportVO.setNewHouseNumber(newHouseAddress);

                    //计算热水器总和
                    BigDecimal heater = new BigDecimal(StringUtils.isNotBlank(swapVo.getSolarHeater()) ? swapVo.getSolarHeater() : "0")
                            .add(new BigDecimal(StringUtils.isNotBlank(swapVo.getOtherHeater()) ? swapVo.getOtherHeater() : "0"));
                    if (heater.compareTo(BigDecimal.ZERO) > 0) {
                        exportVO.setHeater(BigDecimalUtil.stripTrailingZeros(heater));
                    }
                }

                //联系电话
                if (StringUtils.isNotBlank(settle.getHouseOwner())) {
                    exportVO.setPhone(settle.getPhone());
                } else {
                    exportVO.setPhone(settle.getLesseePhone());
                }

                //签约时间、状态
                exportVO.setSigningStatusDesc(SigningStatusEnum.getDesc(settle.getSigningStatus()));
                exportVO.setSigningDateStr(DateHelper.date2String(settle.getSigningDate(), DateHelper.DateFormatType.YearMonthDay_HourMinuteSecond));

                //片区
                String areaName = areaMap.get(settle.getAreaId());
                areaName = StringUtils.isNotBlank(areaName) ? areaName :"";
                exportVO.setAreaName(areaName);

                list.add(exportVO);

            }
        }


        //导出Excel

        //数据行
        int index = 1;

        for (ProtocolExportVO vo : list) {
            vo.setIndex(index);
            index++;
        }

        try {
            ExcelUtil.writeExcel(response, list,
                    "房屋征收补偿台账-" + DateHelper.getCurrentDateYearMonthDayHourMinuteSecond() + ".xlsx",
                    "台账", new ProtocolExportVO());
        } catch (Exception e) {
            log.error("导出征收补偿台账异常", e);
        }

    }
}
