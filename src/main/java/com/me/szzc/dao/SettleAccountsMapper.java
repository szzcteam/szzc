package com.me.szzc.dao;

import com.me.szzc.pojo.entity.SettleAccounts;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface SettleAccountsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SettleAccounts record);

    SettleAccounts selectByPrimaryKey(Long id);

    int updateByPrimaryKey(SettleAccounts record);

    int selectNmae(String houseOwner);

    SettleAccounts getByHouseOwnerAddr(@Param("houseOwner") String houseOwner,
                                             @Param("address") String address);


    int delete(SettleAccounts record);

    List<SettleAccounts> list(@Param("firstResult") int firstResult, @Param("maxResults") int maxResults,
                              @Param("isFY") boolean isFY,
                              @Param("signingStatus") Integer signingStatus, @Param("address") String address,
                              @Param("houseOwner") String houseOwner, @Param("areaId") Long areaId,
                              @Param("areaIdList") List<Long> areaIdList, @Param("startDate") String startDate,
                              @Param("endDate") String endDate, @Param("compensateType") Integer compensateType);

    Integer getCount(@Param("signingStatus") Integer signingStatus, @Param("address") String address,
                     @Param("houseOwner") String houseOwner, @Param("areaId") Long areaId,
                     @Param("areaIdList") List<Long> areaIdList, @Param("startDate") String startDate,
                     @Param("endDate") String endDate, @Param("compensateType") Integer compensateType);


    Integer changeSignStatus(SettleAccounts settleAccounts);
}