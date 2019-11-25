package com.me.szzc.service;

import com.me.szzc.dao.SwapHouseMapper;
import com.me.szzc.pojo.entity.SwapHouse;
import com.me.szzc.utils.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bbfang on 2019/2/16.
 */
@Service
public class SwapHouseService {
    @Autowired
    private SwapHouseMapper swapHouseMapper;

    public void addSwapHouse(SwapHouse request) {
        request.setCreateDate(DateHelper.getTimestamp());
        request.setModifiedDate(DateHelper.getTimestamp());
        request.setDeleted(false);
        swapHouseMapper.insert(request);
    }

    public void delete(SwapHouse swapHouse) {
        swapHouse.setModifiedDate(DateHelper.getTimestamp());
        swapHouse.setDeleted(true);
        swapHouseMapper.delete(swapHouse);
    }


    public void updateSwapHouse(SwapHouse request) {
        request.setModifiedDate(DateHelper.getTimestamp());
        swapHouseMapper.updateByPrimaryKey(request);
    }

    public SwapHouse getById(Long id) {
        return swapHouseMapper.getById(id);
    }

    public SwapHouse getByHouseOwnerAddr(String houseOwner, String address) {
        return swapHouseMapper.getByHouseOwnerAddr(houseOwner, address);
    }


    public SwapHouse getLastByAreaId(Long areaId){
        return swapHouseMapper.getLastByAreaId(areaId);
    }

    public List<SwapHouse> listAll(List<Long> areaIdList){
        return swapHouseMapper.listAll(areaIdList);
    }
}
