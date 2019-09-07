package com.me.szzc.dao;

import com.me.szzc.pojo.dto.ChooseHouseDTO;
import com.me.szzc.pojo.entity.SwapHouse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SwapHouseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SwapHouse record);

    SwapHouse getById(Long id);

    int updateByPrimaryKey(SwapHouse record);

    SwapHouse getByHouseOwnerAddr(@Param("houseOwner") String houseOwner, @Param("address") String address);

    int  delete(SwapHouse swapHouse);

    /**通过结算单id获取选房信息**/
    List<ChooseHouseDTO> getChooseHouseListBySettleId(@Param("settleId") Long settleId);

    List<SwapHouse> listAll();

}