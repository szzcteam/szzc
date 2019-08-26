package com.me.szzc.service;

import com.me.szzc.dao.FieldCoordinateMapper;
import com.me.szzc.dao.SettleAccountsMapper;
import com.me.szzc.dao.SwapHouseMapper;
import com.me.szzc.enums.PrintTableEnum;
import com.me.szzc.enums.PrintTypeEnum;
import com.me.szzc.pojo.dto.FieldCoordinateDto;
import com.me.szzc.pojo.entity.SettleAccounts;
import com.me.szzc.pojo.entity.SwapHouse;
import com.me.szzc.pojo.vo.SettleAccountsVO;
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
    private FieldCoordinateMapper fieldCoordinateMapper;

    @Autowired
    private PrintUtil printUtil;

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
        //获取打印数据坐标
        List<FieldCoordinateDto> FieldCoordinateList =
                fieldCoordinateMapper.getFieldCoordinateListByTableName(PrintTableEnum.HOUSE_EXPROPRIATION_COMPENSATION.getName());
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
}
