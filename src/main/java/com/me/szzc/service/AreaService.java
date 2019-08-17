package com.me.szzc.service;

import com.me.szzc.dao.AreaMapper;
import com.me.szzc.pojo.entity.Farea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaService {

    @Autowired
    private AreaMapper areaMapper;

    public void addArea(Farea farea) {
        this.areaMapper.insert(farea);
    }
}
