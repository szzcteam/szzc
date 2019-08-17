package com.me.szzc.service;

import com.me.szzc.dao.FareaRodeMapper;
import com.me.szzc.pojo.entity.FareaRode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FareaRodeService {
    @Autowired
    FareaRodeMapper fareaRodeMapper;

    public void addfareaRode(FareaRode fareaRode) {
        this.fareaRodeMapper.insert(fareaRode);

    }

    public List<FareaRode> queryFareaRode(int pid) {
        return this.fareaRodeMapper.queryFareaRode(pid);
    }
}
