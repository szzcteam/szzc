package com.me.szzc.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 结算单折现图数据
 * @author luwei
 * @date 2020/5/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettleAccountsLineDTO {

    /**日期字符串格式*/
    private String date;

    /***日期-时间格式**/
    private Date dateTime;

    /**户数**/
    private Long num;

    /**面积**/
    private BigDecimal area;

    /**金额**/
    private BigDecimal payTotal;

}
