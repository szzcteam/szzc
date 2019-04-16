package com.me.szzc.dao;

import com.me.szzc.pojo.entity.SettleAccounts;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SettleAccountsMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(SettleAccounts record);

    SettleAccounts selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SettleAccounts record);

    int selectNmae(String houseOwner);

    SettleAccounts selectByHouseOwner(String houseOwner);

    SettleAccounts selectByHouseOwnerAndAddr(@Param("houseOwner") String houseOwner,
                                             @Param("address") String address);


    int delete(SettleAccounts record);

    List<SettleAccounts> list(@Param("firstResult") int firstResult, @Param("maxResults")int maxResults);
}