package com.me.szzc.pojo.vo;


import lombok.Data;

@Data
public class ProtocolVO {

    private String name;
    private String address;
    private String phone;

    /**片区名称**/
    private String areaName;

    /**签约状态描述**/
    private String signingStatusDesc;

    /**签约状态**/
    private Integer signingStatus;

    /**签约时间**/
    private String signingDateStr;

    private Long settleAccountId;

    private Long swapHouseId;

    private Long rmbRecompenseId;

}

