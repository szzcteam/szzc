package com.me.szzc.pojo.dto;

import lombok.Data;

/**
 * 点选房子
 * @author luwei
 * @date 2019/8/2
 */
@Data
public class ChooseHouseDTO {

    /**旧房地址**/
    private String address;

    /**点房人**/
    private String houseOwner;

    /**新房地址**/
    private String newHouseAddress;

    /**栋**/
    private Integer seat;

    /**单元**/
    private Integer unit;

    /**楼层**/
    private Integer floors;

    /**房号**/
    private Integer houseNumber;






















}
