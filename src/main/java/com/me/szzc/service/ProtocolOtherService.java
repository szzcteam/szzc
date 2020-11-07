package com.me.szzc.service;

import com.me.szzc.dao.ProtocolOtherMapper;
import com.me.szzc.pojo.entity.ProtocolOther;
import com.me.szzc.utils.DateHelper;
import com.me.szzc.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProtocolOtherService {

    @Autowired
    private ProtocolOtherMapper protocolOtherMapper;

    public List<ProtocolOther> queryAll(){
        List<ProtocolOther> list = protocolOtherMapper.queryAll();
        return list;
    }

    /*查询备注*/
    public ProtocolOther queryRemark(long accountsId){
        ProtocolOther protocolOther = this.protocolOtherMapper.queryRemark(accountsId);
        return protocolOther;
    }

    /*添加备注 */
    public boolean addProtocolOther(String remark, Long accountsId ,Long userId) {
        ProtocolOther protocolOther = this.protocolOtherMapper.queryRemark(accountsId);
        if (protocolOther == null) {
            protocolOther = new ProtocolOther();
            protocolOther.setAccountsId(accountsId);
            protocolOther.setRemark(remark);
            protocolOther.setCreateUserId(userId);
            protocolOther.setCreateDate(DateHelper.getTimestamp());
            protocolOther.setModifiedDate(DateHelper.getTimestamp());
            protocolOther.setModifiedUserId(userId);
            return protocolOtherMapper.addProtocolOther(protocolOther) > 0 ? true : false;
        }

        //重置备注值
        if (StringUtils.isNullOrEmpty(remark)) {
            protocolOther.setRemark("");
        } else {
            protocolOther.setRemark(remark);
        }
        protocolOther.setModifiedUserId(userId);
        protocolOther.setModifiedDate(DateHelper.getTimestamp());
        return protocolOtherMapper.updateRoomChange(protocolOther) > 0 ? true : false;

    }


    public List<ProtocolOther> queryByAreaId(List<Long> areaIdList) {
        return protocolOtherMapper.queryByAreaId(areaIdList);
    }

}
