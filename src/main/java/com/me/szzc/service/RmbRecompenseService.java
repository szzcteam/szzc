package com.me.szzc.service;

import com.me.szzc.dao.RmbRecompenseMapper;
import com.me.szzc.pojo.entity.RmbRecompense;
import com.me.szzc.utils.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;

@Service
public class RmbRecompenseService {

    @Autowired
    private RmbRecompenseMapper rmbRecompenseMapper;

    public void add(RmbRecompense rmbRecompense) {
        Timestamp date = DateHelper.getTimestamp();
        rmbRecompense.setCreateDate(date);
        rmbRecompense.setModifiedDate(date);
        rmbRecompense.setDeleted(false);
        this.rmbRecompenseMapper.insertSelective(rmbRecompense);
    }

    public void delete(RmbRecompense rmbRecompense) {
        rmbRecompense.setModifiedDate(DateHelper.getTimestamp());
        rmbRecompense.setDeleted(true);
        this.rmbRecompenseMapper.delete(rmbRecompense);
    }

    public void update(RmbRecompense rmbRecompense) {
        this.rmbRecompenseMapper.updateByPrimaryKey(rmbRecompense);
    }

    public void query(RmbRecompense rmbRecompense) {
        this.rmbRecompenseMapper.selectByPrimaryKey(rmbRecompense.getId());
    }

    public int queryOne(Long id) {
        return this.rmbRecompenseMapper.selectOne(id);
    }

    public RmbRecompense selectByHouseOwner(String houseOwner) {
        return this.rmbRecompenseMapper.selectByHouseOwner(houseOwner);
    }

    public boolean queryName(String name) {
        int i = this.rmbRecompenseMapper.selectNmae(name);
        if(i==0){
            return false;
        }
        else return true;
    }
}
