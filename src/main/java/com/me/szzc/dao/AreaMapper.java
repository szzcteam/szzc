package com.me.szzc.dao;

import com.me.szzc.pojo.entity.Area;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author luwei
 * @date 2019/8/17
 */
public interface AreaMapper {


    int insert(Area area);

    Integer getCount(@Param("name") String name);

    Area getByName(@Param("name") String name);

    Area getById(Long id);

    List<Area> queryPage(@Param("start") Integer start,
                         @Param("pageSize") Integer pageSize,
                         @Param("name") String name,
                         @Param("projectCode") String projectCode);


    int updateStatus(Area area);

    int delete(Long id);

    int existsByUpdateName(@Param("name") String name, @Param("id") Long id);

    int update(Area area);

    List<Area> listByUserId(@Param("userId") Long userId);

    List<Area> listEnableByUserId(@Param("userId") Long userId, @Param("status") Integer status);
}
