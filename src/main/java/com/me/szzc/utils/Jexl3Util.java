package com.me.szzc.utils;

/**
 * Created by bbfang on 2019/3/13.
 */

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;

import java.math.BigDecimal;

/**
 * String类型公式计算求值
 * <dependency>
 * <groupId>org.apache.commons</groupId>
 * <artifactId>commons-jexl3</artifactId>
 * <version>3.0</version>
 * </dependency>
 */
public class Jexl3Util {

    /**
     * 传入正确的字符串计算公式,返回小数点后两位的BigDecimal数据
     *
     * @param stringExpression
     * @return BigDecimal
     */
    public static BigDecimal stringFormulaComputing(String stringExpression) {
        //创建Jexl对象
        JexlEngine jexlEngine = new JexlBuilder().create();
        //添加String类型表达式
        JexlExpression jexlExpression = jexlEngine.createExpression(stringExpression);
        //计算求值
        Object evaluate = jexlExpression.evaluate(null);
        //转换返回值类型且保留小数点后两位
        BigDecimal result = new BigDecimal(null == evaluate ? "0" : evaluate.toString())
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        return result;
    }

}
