package com.me.szzc.dao;

import com.me.szzc.pojo.entity.SettleAccounts;

public interface SettleAccountsMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(SettleAccounts record);

    SettleAccounts selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SettleAccounts record);

    int selectNmae(String houseOwner);

    SettleAccounts selectByHouseOwner(String houseOwner);

    int delete(SettleAccounts record);

}