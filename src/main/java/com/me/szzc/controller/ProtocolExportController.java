package com.me.szzc.controller;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.me.szzc.pojo.RoomChangeExport;
import com.me.szzc.pojo.entity.RmbRecompense;
import com.me.szzc.pojo.entity.SwapHouse;
import com.me.szzc.pojo.vo.ProtocolExportVO;
import com.me.szzc.pojo.vo.RmbRecompenseVO;
import com.me.szzc.pojo.vo.SwapHouseVO;
import com.me.szzc.utils.BigDecimalUtil;
import com.me.szzc.utils.DateHelper;
import com.me.szzc.utils.excle.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luwei
 * @date 2019/9/7
 */
@Slf4j
@Controller
public class ProtocolExportController extends BaseController {

    @RequestMapping("/ssadmin/protocol/export/excel")
    public void export(HttpServletResponse response) {

        //查询产权调换
        List<SwapHouse> swapHouseList = swapHouseService.listAll();
        List<SwapHouseVO> swapHouseVOList = new ArrayList<>();
        try {
            if (swapHouseList != null && swapHouseList.size() > 0) {
                for (SwapHouse po : swapHouseList) {
                    SwapHouseVO vo = SwapHouseVO.parse(po);
                    swapHouseVOList.add(vo);
                }
            }
        } catch (Exception e) {
            log.error("导出协议时，产权调换信息转换异常", e);
        }

        //查询货币补偿
        List<RmbRecompense> rmbRecompenseList = rmbRecompenseService.listAll();
        List<RmbRecompenseVO> rmbRecompenseVOList = new ArrayList<>();
        try {
            if (rmbRecompenseList != null && rmbRecompenseList.size() > 0) {
                for (RmbRecompense po : rmbRecompenseList) {
                    RmbRecompenseVO vo = RmbRecompenseVO.parse(po);
                    rmbRecompenseVOList.add(vo);
                }
            }
        } catch (Exception e) {
            log.error("导出协议时，货币补偿信息转换异常", e);
        }

        //将2个list合并到一个
        List<ProtocolExportVO> list = new ArrayList<>();
        for(RmbRecompenseVO vo : rmbRecompenseVOList){
            ProtocolExportVO exportVO = new ProtocolExportVO();
            BeanUtils.copyProperties(vo, exportVO);
            list.add(exportVO);
        }

        for(SwapHouseVO vo : swapHouseVOList){
            ProtocolExportVO exportVO = new ProtocolExportVO();
            BeanUtils.copyProperties(vo, exportVO);
            list.add(exportVO);
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

            /*
            for(ProtocolExportVO vo : list){
                List<Object> row = new ArrayList<>();
                row.add(index+"");
                row.add("");
                row.add(vo.getCardNo());
                row.add(vo.getHouseOwner());
                row.add(vo.getAddress());
                row.add(vo.getIdentityNo());
                row.add(""); //产别
                row.add(""); //联系电话
                row.add(vo.getHouseOwnerNumber());
                row.add(vo.getPublicOwnerNumber());
                row.add(vo.getCertifiedArea());
                row.add(vo.getProportion());
                row.add(vo.getUseing());

                row.add(vo.getCertifiedArea());
                row.add(vo.getAssessPrice());
                row.add(vo.getValueCompensate());

                row.add(vo.getNoRegisterLegalArea());
                row.add(vo.getNoRegisterAssessPrice());
                row.add(vo.getNoRegisterLegal());

                row.add(vo.getHistoryLegacyArea());
                row.add(vo.getHistoryAssessPrice());
                row.add(vo.getHistoryLegacy());

                //装修折旧补偿
                row.add(vo.getDecorationCompensate());
                row.add(vo.getMoveHouseFee());
                row.add(vo.getInterimFee());
                row.add(vo.getGuarantee());
                row.add(vo.getSuspendBusinessFee());
                row.add(vo.getMoveWaterMeterFee());
                row.add(vo.getMoveAmmeterFee());
                row.add(vo.getMoveAirConditioningFee());
                //热水器拆装费：太阳能+电热水器
                BigDecimal heater = new BigDecimal(org.apache.commons.lang3.StringUtils.isNotBlank(vo.getSolarHeater()) ? vo.getSolarHeater() : "0")
                        .add(new BigDecimal(org.apache.commons.lang3.StringUtils.isNotBlank(vo.getOtherHeater()) ? vo.getOtherHeater() : "0"));
                row.add(BigDecimalUtil.stripTrailingZeros(heater));
                row.add(vo.getGasFee());

                //构筑物补偿
                row.add(vo.getStructureBalcony());
                row.add(vo.getStructureDark());
                row.add(vo.getStructureMezzanine());
                row.add(vo.getStructureBuild());
                row.add(vo.getStructureRoof());

                //住改商
                row.add(vo.getChangeCompensate());
                row.add(vo.getLifeCompensate());
                row.add(vo.getRmbCompensate());
                row.add(vo.getSmallAreaReward());
                row.add(vo.getBuildingAreaFee());
                row.add(vo.getMoveReward());
                row.add(vo.getOtherFee());

                //安置房屋情况
                row.add(vo.getNewHouseAddress());
                //房号
                String houseNumber = vo.getSeat()+"栋"+vo.getUnit() +"单元" + vo.getFloors() + "层" + vo.getHouseNumber() +"号房";
                row.add(houseNumber);
                row.add(vo.getCoveredArea());
                row.add(vo.getPrice());
                row.add(vo.getTotalPrice());

                //抵扣房款
                row.add(vo.getTransferRmb());
                //实际应付金额
                row.add(vo.getDifference());
                //实际应收金额
                row.add(vo.getLessDifference());

                //超级过渡费
                row.add("");
                row.add("");
                row.add("");
                row.add("");
                //交房结算情况
                row.add("");
                row.add("");
                row.add("");

                index++;
                dataList.add(row);
            }

            writer.write1(dataList, sheet);
*/


    }
}
