package com.me.szzc.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bbfang on 2019/2/18.
 */
public class NumberToCapitalizedUtils {

    public static final Map<Integer, String> CHINESE_NUM_MAP = new HashMap<>();
    static {
        CHINESE_NUM_MAP.put(0, "零");
        CHINESE_NUM_MAP.put(1, "壹");
        CHINESE_NUM_MAP.put(2, "贰");
        CHINESE_NUM_MAP.put(3, "叁");
        CHINESE_NUM_MAP.put(4, "肆");
        CHINESE_NUM_MAP.put(5, "伍");
        CHINESE_NUM_MAP.put(6, "陆");
        CHINESE_NUM_MAP.put(7, "柒");
        CHINESE_NUM_MAP.put(8, "捌");
        CHINESE_NUM_MAP.put(9, "玖");
    }


    public static void main(String[] args) {
        double a = 2631130;
        System.out.println(digitUppercase(a));
    }

    /**
     * 数字金额大写转换，思想先写个完整的然后将如零拾替换成零 要用到正则表达式
     */
    public static String digitUppercase(double n) {
        String fraction[] = {"角", "分"};
        String digit[] = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String unit[][] = {{"元", "万", "亿"}, {"", "拾", "佰", "仟"}};

        String head = n < 0 ? "负" : "";
        n = Math.abs(n);

        String s = "";
        for (int i = 0; i < fraction.length; i++) {
            s += (digit[(int) (Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", "");
        }
        if (s.length() < 1) {
            s = "整";
        }
        int integerPart = (int) Math.floor(n);

        for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
            String p = "";
            for (int j = 0; j < unit[1].length && n > 0; j++) {
                p = digit[integerPart % 10] + unit[1][j] + p;
                integerPart = integerPart / 10;
            }
            s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
        }
        return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
    }
}

