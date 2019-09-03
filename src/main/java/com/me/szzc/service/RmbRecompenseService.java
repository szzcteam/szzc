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
        this.rmbRecompenseMapper.insert(rmbRecompense);
    }

    public void delete(RmbRecompense rmbRecompense) {
        rmbRecompense.setModifiedDate(DateHelper.getTimestamp());
        rmbRecompense.setDeleted(true);
        this.rmbRecompenseMapper.delete(rmbRecompense);
    }

    public void update(RmbRecompense rmbRecompense) {
        rmbRecompense.setModifiedDate(DateHelper.getTimestamp());
        this.rmbRecompenseMapper.updateByPrimaryKey(rmbRecompense);
    }

    public RmbRecompense getById(Long id) {
        return this.rmbRecompenseMapper.getById(id);
    }

    public RmbRecompense getByHouseOwnerAddr(String houseOwner, String address) {
        return this.rmbRecompenseMapper.getByHouseOwnerAddr(houseOwner, address);
    }

}
