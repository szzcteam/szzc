package com.me.szzc.service;

import com.me.szzc.dao.RmbRecompenseMapper;
import com.me.szzc.pojo.entity.RmbRecompense;
import com.me.szzc.utils.BigDecimalUtil;
import com.me.szzc.utils.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Service
public class RmbRecompenseService {

    @Autowired
    private RmbRecompenseMapper rmbRecompenseMapper;

    public void add(RmbRecompense rmbRecompense) {
        Timestamp date = DateHelper.getTimestamp();
        rmbRecompense.setCreateDate(date);
        rmbRecompense.setModifiedDate(date);
        rmbRecompense.setDeleted(false);
        rmbRecompense.setHotWaterCompensate(BigDecimalUtil.toSum(rmbRecompense.getSolarHeater(), rmbRecompense.getOtherHeater()));
        this.rmbRecompenseMapper.insert(rmbRecompense);
    }

    public void delete(RmbRecompense rmbRecompense) {
        rmbRecompense.setModifiedDate(DateHelper.getTimestamp());
        rmbRecompense.setDeleted(true);
        this.rmbRecompenseMapper.delete(rmbRecompense);
    }

    public void update(RmbRecompense rmbRecompense) {
        rmbRecompense.setModifiedDate(DateHelper.getTimestamp());
        rmbRecompense.setHotWaterCompensate(BigDecimalUtil.toSum(rmbRecompense.getSolarHeater(), rmbRecompense.getOtherHeater()));
        this.rmbRecompenseMapper.updateByPrimaryKey(rmbRecompense);
    }

    public RmbRecompense getById(Long id) {
        return this.rmbRecompenseMapper.getById(id);
    }

    public RmbRecompense getLastByAreaId(Long areaId) {
        return this.rmbRecompenseMapper.getLastByAreaId(areaId);
    }

    public RmbRecompense getByHouseOwnerAddr(String houseOwner, String address) {
        return this.rmbRecompenseMapper.getByHouseOwnerAddr(houseOwner, address);
    }

    public List<RmbRecompense> listAll(List<Long> areaIdList){
        return rmbRecompenseMapper.listAll(areaIdList);
    }

    public RmbRecompense getByHouseOwnerAddrID(String houseOwner, String address, Long id) {
        return this.rmbRecompenseMapper.getByHouseOwnerAddrID(houseOwner,address,id);
    }

    public List<RmbRecompense> listByNameAddressList(List<String> houseOwnerList, List<String> addressList) {
        if (addressList == null || addressList.isEmpty() || houseOwnerList == null || houseOwnerList.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        return rmbRecompenseMapper.listByNameAddressList(houseOwnerList, addressList);
    }
}
