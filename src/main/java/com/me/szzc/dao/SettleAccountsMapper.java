package com.me.szzc.dao;

import com.me.szzc.pojo.entity.SettleAccounts;

public interface SettleAccountsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SettleAccounts record);

    int insertSelective(SettleAccounts record);

    SettleAccounts selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SettleAccounts record);

    int updateByPrimaryKey(SettleAccounts record);
}