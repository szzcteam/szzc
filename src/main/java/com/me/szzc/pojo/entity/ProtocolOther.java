package com.me.szzc.pojo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ProtocolOther {

    /**
     *
     */
    private Long id;
    /**
     *结算单
     */
    private Long accountsId;

    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 创建人id
     */
    private Long createUserId;

    /**
     * 修改时间
     */
    private Date modifiedDate;

    /**
     * 修改人id
     */
    private Long modifiedUserId;
}
