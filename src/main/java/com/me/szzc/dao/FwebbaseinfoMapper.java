package com.me.szzc.dao;


import com.me.szzc.pojo.entity.Fwebbaseinfo;

import java.util.List;

public interface FwebbaseinfoMapper {
    int deleteByPrimaryKey(Long fid);

    int insert(Fwebbaseinfo record);

    int insertSelective(Fwebbaseinfo record);

    Fwebbaseinfo selectByPrimaryKey(Long fid);

    List<Fwebbaseinfo > selectAll();

    int updateByPrimaryKeySelective(Fwebbaseinfo record);

    int updateByPrimaryKey(Fwebbaseinfo record);
}