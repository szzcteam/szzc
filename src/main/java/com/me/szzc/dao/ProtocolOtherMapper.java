package com.me.szzc.dao;

import com.me.szzc.pojo.entity.ProtocolOther;

public interface ProtocolOtherMapper {

    ProtocolOther queryRemark(long accountsId);

    Integer addProtocolOther(ProtocolOther protocolOther);

    Integer updateRoomChange(ProtocolOther protocolOther);
}
