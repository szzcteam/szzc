package com.me.szzc.dao;

import com.me.szzc.pojo.entity.RmbRecompense;

public interface RmbRecompenseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RmbRecompense record);

    int insertSelective(RmbRecompense record);

    RmbRecompense selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RmbRecompense record);

    int updateByPrimaryKey(RmbRecompense record);


    void updateByStatus(Long id, Boolean deteled);

    int selectOne(Long id);

    RmbRecompense selectByHouseOwner(String houseOwner);
}