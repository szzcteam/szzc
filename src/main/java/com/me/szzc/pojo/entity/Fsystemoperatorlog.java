package com.me.szzc.pojo.entity;


import lombok.Data;
import java.util.Date;

@Data
public class Fsystemoperatorlog {


    public Fsystemoperatorlog(){

    }

    private Long id;

    /**
     * User Agent,所属表字段为tbl_system_operator_log.user_agent
     */
    private String userAgent;

    /**
     * 操作模块,所属表字段为tbl_system_operator_log.module
     */
    private String module;

    /**
     * 操作时间,所属表字段为tbl_system_operator_log.operator_date
     */
    private Date operatorDate;

    /**
     * 操作人,所属表字段为tbl_system_operator_log.user_id
     */
    private int userId;

    /**
     * 操作人登录名,所属表字段为tbl_system_operator_log.login_name
     */
    private String loginName;

    /**
     * IP,所属表字段为tbl_system_operator_log.ip
     */
    private String ip;

    /**
     * 操作代码,所属表字段为tbl_system_operator_log.operator_code
     */
    private String operatorCode;

    /**
     * 操作名称,所属表字段为tbl_system_operator_log.operator_name
     */
    private String operatorName;

    /**
     * 执行类名,所属表字段为tbl_system_operator_log.class_name
     */
    private String className;

    /**
     * 执行方法名,所属表字段为tbl_system_operator_log.method_name
     */
    private String methodName;

    /**
     * 操作是否成功,所属表字段为tbl_system_operator_log.issuccess
     */
    private Boolean issuccess;

    /**
     * 请求参数,所属表字段为tbl_system_operator_log.request_parameters
     */
    private String requestParameters;



    private String responseResult;

    /**
     * 操作内容,所属表字段为tbl_system_operator_log.operator_content
     */
    private String operatorContent;

    /**
     * 备注,所属表字段为tbl_system_operator_log.remart
     */
    private String remart;

}
