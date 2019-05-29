package com.me.szzc.service;

import com.me.szzc.dao.SettleAccountsMapper;
import com.me.szzc.pojo.entity.Notice;
import com.me.szzc.pojo.entity.SettleAccounts;
import com.me.szzc.utils.DateHelper;
import org.apache.commons.lang3.StringUtils;
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

    public SettleAccounts query(SettleAccounts settleAccounts) {
        return this.settleAccountsMapper.selectByPrimaryKey(settleAccounts.getId());
    }

    public SettleAccounts getById(Long id){
        return this.settleAccountsMapper.selectByPrimaryKey(id);
    }

    public SettleAccounts getByHouseOwnerAddr(String houseOwner, String address) {
        return settleAccountsMapper.getByHouseOwnerAddr(houseOwner, address);
    }


    public List<SettleAccounts> list(int firstResult, int maxResults, boolean isFY, Integer signingStatus, String keywords) {
        List<SettleAccounts> listSettleAccounts = this.settleAccountsMapper.list(firstResult, maxResults, isFY, signingStatus, keywords);
        return listSettleAccounts;
    }

    public Integer getCount(Integer signingStatus, String keywords) {
        return this.settleAccountsMapper.getCount(signingStatus, keywords);
    }


}
