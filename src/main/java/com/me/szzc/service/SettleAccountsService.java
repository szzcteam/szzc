package com.me.szzc.service;

import com.me.szzc.dao.SettleAccountsMapper;
import com.me.szzc.pojo.entity.SettleAccounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettleAccountsService {

    @Autowired
    private SettleAccountsMapper settleAccountsMapper;

    public void add(SettleAccounts settleAccounts) {
        this.settleAccountsMapper.insert(settleAccounts);
    }

    public void detele(SettleAccounts settleAccounts) {
        this.settleAccountsMapper.deleteByPrimaryKey(settleAccounts.getId());
    }

    public void update(SettleAccounts settleAccounts) {
        this.settleAccountsMapper.updateByPrimaryKey(settleAccounts);
    }

    public void query(SettleAccounts settleAccounts) {
        this.settleAccountsMapper.selectByPrimaryKey(settleAccounts.getId());
    }

    public boolean queryName(String name) {
        int i = this.settleAccountsMapper.selectNmae(name);
        if(i==0){
            return false;
        }
        else return true;
    }

    public SettleAccounts selectByHouseOwner(String houseOwner) {
        return settleAccountsMapper.selectByHouseOwner(houseOwner);
    }


}
