package com.me.szzc.dao;

import com.me.szzc.pojo.entity.AreaRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AreaRoleMapper {

    int insert(AreaRole areaRole);

    int update(AreaRole areaRole);

    List<AreaRole> listByAreaId(@Param("areaId") Long areaId);

    int deleteByAreaId(@Param("areaId") Long areaId);

}
