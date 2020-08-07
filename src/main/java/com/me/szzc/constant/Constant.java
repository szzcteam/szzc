package com.me.szzc.constant;

import java.math.BigDecimal;

/**
 * @author luwei
 * @date 2019/8/23
 */
public interface Constant {

    String CHINESE_ZERO = "零";

    //失败code
    Integer ERROR_CODE = 300;

    //成功code
    Integer SUCCESS_CODE = 200;

    //admin 管理员
    String ADMIN = "admin";

    /**1W**/
    BigDecimal ONE_W = BigDecimal.valueOf(10000);

    /**1亿**/
    BigDecimal ONE_Y = BigDecimal.valueOf(100000000);

    /**负一**/
    BigDecimal NEGATIVE_ONE = BigDecimal.valueOf(-1);



    /**微信小程序加密-密钥**/
    String WX_SALT = "Y3j_#xa8N";

    /**编码**/
    String UTF8 = "UTF-8";

    /**小程序 url 过期时间 **/
    Long WX_EXPIRE_URL_TIME = 1*60*1000L;



}
