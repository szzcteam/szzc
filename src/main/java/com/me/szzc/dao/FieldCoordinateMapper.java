package com.me.szzc.dao;

import com.me.szzc.pojo.dto.FieldCoordinateDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 打印字段坐标Mapper
 * Created by bbfang on 2019/8/12.
 */
public interface FieldCoordinateMapper {

    List<FieldCoordinateDto> getFieldCoordinateListByTableName(@Param("tableName") String tableNamesss,
                                                               @Param("itemCode") String projectCode);

    List<FieldCoordinateDto> getNoticeFieldCoordinateList(String name);
}
