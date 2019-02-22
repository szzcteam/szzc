package com.me.szzc.enums;

import com.me.szzc.controller.RmbRecompenseController;
import com.me.szzc.service.RmbRecompenseService;

/**
 * Created by bbfang on 2019/2/18.
 */
public enum ProtocolEnum {
    HOUSING_PROPERTY_ECHANGE_AGREEMENT("房屋产权易变协议书"),
    RMB_RECOMPENSE_AGREEMENT("货币补偿协议书")
    ;

    private final String code;

    ProtocolEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
