package com.me.szzc.pojo.vo;


import lombok.Data;

@Data
public class ProtocolVO {

    private String name;
    private String phone;
    private String status;
    private Boolean noticeFlag;
    private Boolean rmbRecompenseFlag;
    private Boolean settleAccountsFlag;
    private Boolean swapHouseFlag;

}

