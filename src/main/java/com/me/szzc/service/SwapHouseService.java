package com.me.szzc.service;

import com.me.szzc.dao.SwapHouseMapper;
import com.me.szzc.pojo.entity.SwapHouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bbfang on 2019/2/16.
 */
@Service
public class SwapHouseService {
    @Autowired
    private SwapHouseMapper swapHouseMapper;

    public void add(SwapHouse request) {
        swapHouseMapper.insert(request);
    }
}
