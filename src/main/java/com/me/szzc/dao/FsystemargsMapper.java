package com.me.szzc.dao;


import com.me.szzc.pojo.entity.Fsystemargs;

import java.util.List;

public interface FsystemargsMapper {
    int deleteByPrimaryKey(Long fid);

    int insert(Fsystemargs record);

    int insertSelective(Fsystemargs record);

    Fsystemargs selectByPrimaryKey(Long fid);

    List<Fsystemargs> selectAll();

    List<Fsystemargs> selectByProperty(Fsystemargs record);

    int updateByPrimaryKeySelective(Fsystemargs record);

    int updateByPrimaryKey(Fsystemargs record);
}