package com.me.szzc.pojo.entity;

import lombok.Data;

/**
 * 货币补偿时，交易的新房信息
 * @author luwei
 * @date 2019/12/28
 */
@Data
public class TradeHouse {

    /**申购房面积**/
    private String coveredArea;

    /**申购序号**/
    private String buySerialNumber;

    /**抵扣金额**/
    private String transferRmb;

    /**甲方支付给乙方的差额**/
    private String difference;

    /**差额大写**/
    private String upperDifference;
}
