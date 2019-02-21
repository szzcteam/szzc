package com.me.szzc.service;

import com.me.szzc.dao.RmbRecompenseMapper;
import com.me.szzc.pojo.entity.RmbRecompense;
import com.me.szzc.utils.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RmbRecompenseService {

    @Autowired
    private RmbRecompenseMapper rmbRecompenseMapper;

    public void add(RmbRecompense rmbRecompense) {
        rmbRecompense.setCreateDate(DateHelper.getTimestamp());
        rmbRecompense.setModifiedDate(DateHelper.getTimestamp());
        rmbRecompense.setDeleted(false);
        this.rmbRecompenseMapper.insert(rmbRecompense);
    }

    public void detele(RmbRecompense rmbRecompense) {
        //this.rmbRecompenseMapper.deleteByPrimaryKey(rmbRecompense.getId());
        rmbRecompense.setDeleted(true);
        this.rmbRecompenseMapper.updateByStatus(rmbRecompense.getId(),rmbRecompense.getDeleted());
    }

    public void update(RmbRecompense rmbRecompense) {
        this.rmbRecompenseMapper.updateByPrimaryKeySelective(rmbRecompense);
    }

    public void query(RmbRecompense rmbRecompense) {
        this.rmbRecompenseMapper.selectByPrimaryKey(rmbRecompense.getId());
    }

    public int queryOne(Long id) {
        return this.rmbRecompenseMapper.selectOne(id);
    }


    public RmbRecompense selectByHouseOwner(String houseOwner) {
        return rmbRecompenseMapper.selectByHouseOwner(houseOwner);
    }
}
