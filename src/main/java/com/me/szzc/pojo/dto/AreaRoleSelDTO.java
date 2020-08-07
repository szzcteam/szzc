package com.me.szzc.pojo.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 初始化修改时，返回所有角色信息，并标识出哪些是选中的
 * @author luwei
 * @date 2019/8/17
 */
@Setter
@Getter
public class AreaRoleSelDTO {

    Long fid;

    String fname;

    boolean selFlag = false;

}
