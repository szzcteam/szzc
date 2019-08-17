package com.me.szzc.dao;

import com.me.szzc.pojo.entity.FareaRode;

import java.util.List;

public interface FareaRodeMapper {

    public void insert(FareaRode fareaRode);

    public List<FareaRode> queryFareaRode(int pid);
}
