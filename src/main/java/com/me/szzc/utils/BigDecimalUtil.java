package com.me.szzc.utils;

import java.math.BigDecimal;

/**
 * @author luwei
 * @date 2019/3/29
 */
public class BigDecimalUtil {

    /**
     * 数字去掉多余的0
     * @param num  1000.00
     * @return  1000
     */
    public static String stripTrailingZeros(BigDecimal num) {
        if(num == null) {
            return "";
        }
        return num.stripTrailingZeros().toPlainString();
    }
}
