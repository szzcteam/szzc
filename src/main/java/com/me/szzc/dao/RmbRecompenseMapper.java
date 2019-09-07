package com.me.szzc.dao;

import com.me.szzc.pojo.entity.RmbRecompense;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RmbRecompenseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RmbRecompense record);


    int delete(RmbRecompense record);

    RmbRecompense getById(Long id);

    int updateByPrimaryKey(RmbRecompense record);

    RmbRecompense getByHouseOwnerAddr(@Param("houseOwner") String houseOwner, @Param("address") String address);

    List<RmbRecompense> listAll();

}