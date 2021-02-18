package com.me.szzc.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bbfang on 2019/2/18.
 */
public class NumberToCapitalizedUtils {

    private static final String[] SIMPLE_DIGITS = new String[]{"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    private static final String[] TRADITIONAL_DIGITS = new String[]{"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    private static final String[] SIMPLE_UNITS = new String[]{"", "十", "百", "千"};
    private static final String[] TRADITIONAL_UNITS = new String[]{"", "拾", "佰", "仟"};

    public static final Map<Integer, String> CHINESE_NUM_MAP = new HashMap<>();

    static {
        for (int i = 0; i < TRADITIONAL_DIGITS.length; i++) {
            CHINESE_NUM_MAP.put(i, TRADITIONAL_DIGITS[i]);
        }
    }


    public static void main(String[] args) {
        double a = 958189.44;
        System.out.println(digitUppercase(a));
    }

    public static String digitUppercase(double n) {
        return format(n, true, true);
    }

    /**
     * 阿拉伯数字转换成中文
     * 小数点后四舍五入保留2位小数
     *
     * @param amount           阿拉伯数字
     * @param isUseTraditional 是否使用金额大写
     * @param isMoneyMode      是否是金额模式
     * @return
     */
    public static String format(double amount, boolean isUseTraditional, boolean isMoneyMode) {
        String[] numArray = isUseTraditional ? TRADITIONAL_DIGITS : SIMPLE_DIGITS;
        if (amount <= 9.999999999999998E13D && amount >= -9.999999999999998E13D) {
            boolean negative = false;
            if (amount < 0.0D) {
                negative = true;
                amount = -amount;
            }

            long temp = Math.round(amount * 100.0D);
            int numFen = (int) (temp % 10L);
            temp /= 10L;
            int numJiao = (int) (temp % 10L);
            temp /= 10L;
            int[] parts = new int[20];
            int numParts = 0;

            for (int i = 0; temp != 0L; ++i) {
                int part = (int) (temp % 10000L);
                parts[i] = part;
                ++numParts;
                temp /= 10000L;
            }

            boolean beforeWanIsZero = true;
            StringBuilder chineseStr = new StringBuilder();

            for (int i = 0; i < numParts; ++i) {
                String partChinese = toChinese(parts[i], isUseTraditional);
                if (i % 2 == 0) {
                    beforeWanIsZero = StringUtils.isEmpty(partChinese);
                }

                if (i != 0) {
                    if (i % 2 == 0) {
                        chineseStr.insert(0, "亿");
                    } else if ("".equals(partChinese) && !beforeWanIsZero) {
                        chineseStr.insert(0, "零");
                    } else {
                        if (parts[i - 1] < 1000 && parts[i - 1] > 0) {
                            chineseStr.insert(0, "零");
                        }

                        if (parts[i] > 0) {
                            chineseStr.insert(0, "万");
                        }
                    }
                }

                chineseStr.insert(0, partChinese);
            }

            if ("".equals(chineseStr.toString())) {
                chineseStr = new StringBuilder(numArray[0]);
            }

            if (negative) {
                chineseStr.insert(0, "负");
            }

            if (numFen == 0 && numJiao == 0) {
                if (isMoneyMode) {
                    chineseStr.append("元整");
                }
            } else if (numFen == 0) {
                chineseStr.append(isMoneyMode ? "元" : "点").append(numArray[numJiao]).append(isMoneyMode ? "角" : "");
            } else if (numJiao == 0) {
                chineseStr.append(isMoneyMode ? "元零" : "点零").append(numArray[numFen]).append(isMoneyMode ? "分" : "");
            } else {
                chineseStr.append(isMoneyMode ? "元" : "点").append(numArray[numJiao]).append(isMoneyMode ? "角" : "").append(numArray[numFen]).append(isMoneyMode ? "分" : "");
            }

            return chineseStr.toString();
        } else {
            throw new IllegalArgumentException("Number support only: (-99999999999999.99 ～ 99999999999999.99)！");
        }
    }

    private static String toChinese(int amountPart, boolean isUseTraditional) {
        String[] numArray = isUseTraditional ? TRADITIONAL_DIGITS : SIMPLE_DIGITS;
        String[] units = isUseTraditional ? TRADITIONAL_UNITS : SIMPLE_UNITS;
        int temp = amountPart;
        StringBuilder chineseStr = new StringBuilder();
        boolean lastIsZero = true;

        for (int i = 0; temp > 0; ++i) {
            int digit = temp % 10;
            if (digit == 0) {
                if (!lastIsZero) {
                    chineseStr.insert(0, "零");
                }

                lastIsZero = true;
            } else {
                chineseStr.insert(0, numArray[digit] + units[i]);
                lastIsZero = false;
            }

            temp /= 10;
        }

        return chineseStr.toString();
    }

}

