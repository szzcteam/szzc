package com.me.szzc.service;

import com.me.szzc.dao.AreaRoleMapper;
import com.me.szzc.pojo.entity.AreaRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaRoleService {
    @Autowired
    AreaRoleMapper areaRoleMapper;

    public void insert(AreaRole areaRole) {
        this.areaRoleMapper.insert(areaRole);

    }


}
