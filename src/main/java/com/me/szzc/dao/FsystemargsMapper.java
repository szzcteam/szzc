package com.me.szzc.dao;


import com.me.szzc.pojo.entity.Fsystemargs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FsystemargsMapper {
    int deleteByPrimaryKey(Long fid);

    int insert(Fsystemargs record);

    int insertSelective(Fsystemargs record);

    Fsystemargs selectByPrimaryKey(Long fid);

    List<Fsystemargs> selectAll();

    List<Fsystemargs> list(@Param("firstResult") int firstResult, @Param("maxResults")int maxResults,
                           @Param("filter") String filter, @Param("isFY") boolean isFY);

    Integer getAllCount(@Param("filter") String filter);

    List<Fsystemargs> selectByProperty(Fsystemargs record);

    int updateByPrimaryKeySelective(Fsystemargs record);

    int updateByPrimaryKey(Fsystemargs record);

    List<Fsystemargs> selectByLike(@Param("keywordFilter") String keywordFilter);

}