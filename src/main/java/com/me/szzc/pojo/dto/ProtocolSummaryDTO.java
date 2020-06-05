package com.me.szzc.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 协议摘要
 * @author luwei
 * @date 2020/6/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProtocolSummaryDTO {

    /**日期字符串格式*/
    private String date;

    /**面积**/
    private BigDecimal area;

    /**拆迁房屋的总价值 **/
    private BigDecimal sumCompensate;

    /** 新房金额 **/
    private BigDecimal houseMoney;

    /**应付(收) 金额**/
    private BigDecimal payTotal;

    /**协议类型  0货币 1调换**/
    private Integer compensateType;

    /**新房源名**/
    private String newHouseName;

    /** 选房总面积 **/
    private BigDecimal chooseArea;

    /**
     * 交换房面积1
     */
    private BigDecimal swapArea1;

    /**
     * 交换房面积2
     */
    private BigDecimal swapArea2;

    /**
     * 交换房面积3
     */
    private BigDecimal swapArea3;

    /**
     * 交换房面积4
     */
    private BigDecimal swapArea4;

    /**
     * 交换房面积5
     */
    private BigDecimal swapArea5;

}
