package com.me.szzc.pojo.dto;

import lombok.Data;

/**
 * 房源数量统计
 * @author luwei
 * @date 2020/5/21
 */
@Data
public class RoomChangeNumDTO {

    /**项目code**/
    private String projectCode;

    /**项目名称**/
    private String projectName;

    /**总房源数量**/
    private Long totalHouse;

    /**空置数量**/
    private Long emptys;

    /**已点房数量**/
    private Long signed;

    /**预留数量**/
    private Long prior;

    /**作废数量**/
    private Long invalid;


    /**面积，可能有中文**/
    private String area;

    /**套数**/
    private Integer num;


}
