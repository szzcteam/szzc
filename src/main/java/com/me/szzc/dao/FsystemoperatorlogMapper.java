package com.me.szzc.dao;


import com.me.szzc.pojo.entity.Fsystemoperatorlog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FsystemoperatorlogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Fsystemoperatorlog record);

    int insertSelective(Fsystemoperatorlog record);

    Fsystemoperatorlog selectByPrimaryKey(Long id);

    List<Fsystemoperatorlog> selectAll();

    List<Fsystemoperatorlog> list(Fsystemoperatorlog record);

    Integer getAllCount(@Param("filter") String filter);

    List<Fsystemoperatorlog> findByProperty(Fsystemoperatorlog record);

    int updateByPrimaryKeySelective(Fsystemoperatorlog record);

    int updateByPrimaryKey(Fsystemoperatorlog record);
}