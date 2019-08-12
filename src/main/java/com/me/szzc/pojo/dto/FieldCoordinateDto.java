package com.me.szzc.pojo.dto;

import lombok.Data;

/**
 * 打印字段坐标DTO
 * Created by bbfang on 2019/8/12.
 */
@Data
public class FieldCoordinateDto {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 字段编码
     */
    private String code;

    /**
     * 横坐标
     */
    private Integer abscissa;

    /**
     * 纵坐标
     */
    private Integer ordinate;

    /**
     * 字体类型名称
     */
    private String fontName;

    /**
     * 字体大小
     */
    private Integer fontSize;
}
