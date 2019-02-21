package com.me.szzc.service;

import com.me.szzc.dao.RmbRecompenseMapper;
import com.me.szzc.pojo.entity.RmbRecompense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RmbRecompenseService {

    @Autowired
    private RmbRecompenseMapper rmbRecompenseMapper;

    public void add(RmbRecompense rmbRecompense) {
        this.rmbRecompenseMapper.insert(rmbRecompense);
    }

    public void detele(RmbRecompense rmbRecompense) {
        //this.rmbRecompenseMapper.deleteByPrimaryKey(rmbRecompense.getId());
        rmbRecompense.setDeleted(false);
        this.rmbRecompenseMapper.updateByStatus(rmbRecompense.getId(),rmbRecompense.getDeleted());
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
}
