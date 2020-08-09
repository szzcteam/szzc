package com.me.szzc.dao;

import com.me.szzc.pojo.entity.ProtocolOther;

import java.util.List;

public interface ProtocolOtherMapper {

    List<ProtocolOther> queryAll();

    ProtocolOther queryRemark(long accountsId);

    Integer addProtocolOther(ProtocolOther protocolOther);

    Integer updateRoomChange(ProtocolOther protocolOther);
}
