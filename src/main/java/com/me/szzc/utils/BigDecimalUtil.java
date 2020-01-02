package com.me.szzc.utils;

import org.apache.commons.lang3.StringUtils;

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

    //2个字符串求和，返回数字
    public static BigDecimal toSum(String num1, String num2){
        if (StringUtils.isBlank(num1)) {
            num1 = "0";
        }
        if (StringUtils.isBlank(num2)) {
            num2 = "0";
        }
        BigDecimal sum = new BigDecimal(num1).add(new BigDecimal(num2));
        return sum;
    }

    //2个数组求和，返回数字
    public static BigDecimal toSum(BigDecimal num1, BigDecimal num2){
        if (num1 == null) {
            num1 = BigDecimal.ZERO;
        }
        if (num2 == null) {
            num2 = BigDecimal.ZERO;
        }
        BigDecimal sum = num1.add(num2);
        return sum;
    }

    public static void main(String[] args) {
        String num1 = "300";
        String num2 = "";
        System.out.println(toSum(num1, num2).toPlainString());
    }
}
