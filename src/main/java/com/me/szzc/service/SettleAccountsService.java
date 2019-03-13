package com.me.szzc.service;

import com.me.szzc.dao.SettleAccountsMapper;
import com.me.szzc.pojo.entity.Notice;
import com.me.szzc.pojo.entity.SettleAccounts;
import com.me.szzc.utils.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SettleAccountsService {

    @Autowired
    private SettleAccountsMapper settleAccountsMapper;

    public void add(SettleAccounts settleAccounts) {
        Timestamp date = DateHelper.getTimestamp();
        settleAccounts.setCreateDate(date);
        settleAccounts.setModifiedDate(date);
        settleAccounts.setDeleted(false);
        this.settleAccountsMapper.insertSelective(settleAccounts);
    }

    public void delete(SettleAccounts settleAccounts) {
        settleAccounts.setModifiedDate(DateHelper.getTimestamp());
        settleAccounts.setDeleted(true);
        this.settleAccountsMapper.delete(settleAccounts);
    }

    public void update(SettleAccounts settleAccounts) {
        this.settleAccountsMapper.updateByPrimaryKeySelective(settleAccounts);
    }

    public void query(SettleAccounts settleAccounts) {
        this.settleAccountsMapper.selectByPrimaryKey(settleAccounts.getId());
    }

    public boolean queryName(String name) {
        int i = this.settleAccountsMapper.selectNmae(name);
        if (i == 0) {
            return false;
        } else {
            return true;
        }
    }

    public SettleAccounts selectByHouseOwner(String houseOwner) {
        return settleAccountsMapper.selectByHouseOwner(houseOwner);
    }


    public Map<String,String> list(int firstResult, int maxResults) {

        List<SettleAccounts> listSettleAccounts =  this.settleAccountsMapper.list(firstResult, maxResults);
        Map<String, String> map = new HashMap<String, String>();
        for (SettleAccounts settleAccounts:listSettleAccounts) {
            map.put(settleAccounts.getHouseOwner(),settleAccounts.getPhone());
        }
        return map;
    }

}
