package com.me.szzc.dao;


import com.me.szzc.pojo.entity.Fadmin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FadminMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(Fadmin record);

    int insertSelective(Fadmin record);

    Fadmin selectByPrimaryKey(Integer fid);

    List<Fadmin> findAll();

    List<Fadmin> list(@Param("firstResult") int firstResult, @Param("maxResults")int maxResults,
                      @Param("filter") String filter, @Param("isFY") boolean isFY);


    Integer getAllCount(@Param("filter") String filter);

    List<Fadmin> login(Fadmin fadmin);

    List<Fadmin> findByProperty(Fadmin fadmin);

    int updateByPrimaryKeySelective(Fadmin record);

    int updateByPrimaryKey(Fadmin record);
}