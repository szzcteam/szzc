package com.me.szzc.pojo.dto;

import lombok.Data;

/**
 * 协议状态统计
 * @author luwei
 * @date 2020/5/21
 */
@Data
public class ProtocolCountStatusDTO {

    /**项目code**/
    private String projectCode;

    /**项目名称**/
    private String projectName;

    /**片区名称**/
    private String name;

    /**未签约数量**/
    private Long noSigned;

    /**已签约数量**/
    private Long completer;

    /**已审核数量**/
    private Long audit;

}
