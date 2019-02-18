package com.me.szzc.dao;

import com.me.szzc.pojo.entity.SwapHouse;

public interface SwapHouseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SwapHouse record);

    int insertSelective(SwapHouse record);

    SwapHouse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SwapHouse record);

    int updateByPrimaryKey(SwapHouse record);

    SwapHouse selectSwapHouseByHouseOwner(String houseOwner);

}