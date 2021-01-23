package com.me.szzc.dao;

import com.me.szzc.pojo.entity.ProtocolOther;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProtocolOtherMapper {

    List<ProtocolOther> queryAll();

    List<ProtocolOther> queryByAccounts(@Param("accounts")  List<Long> accounts);

    ProtocolOther queryRemark(long accountsId);

    Integer addProtocolOther(ProtocolOther protocolOther);

    Integer updateRoomChange(ProtocolOther protocolOther);

    List<ProtocolOther> queryByAreaId(@Param("areaIdList") List<Long> areaIdList);
}
