package com.me.szzc.utils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.math.BigDecimal;

/**
 * @author luwei
 * @date 2019/9/11
 */
public class ScriptEngineUtil {

    static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");


    public static BigDecimal runCalc(String original) {
       BigDecimal result = BigDecimal.ZERO;
        try {
            Object temp = jse.eval(original);
            result = new BigDecimal(temp.toString());
        } catch (Exception t) {
            return result;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(ScriptEngineUtil.runCalc("0*0.8"));
    }

}
