package com.me.szzc.dao;

import com.me.szzc.pojo.dto.ProtocolCountMoneyDTO;
import com.me.szzc.pojo.dto.ProtocolCountStatusDTO;
import com.me.szzc.pojo.dto.ProtocolSummaryDTO;
import com.me.szzc.pojo.dto.SettleAccountsLineDTO;
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

    SettleAccounts getByHouseOwnerAddrNeqId(@Param("houseOwner") String houseOwner,
                                            @Param("address") String address,
                                            @Param("id") Long id);

    int delete(SettleAccounts record);

    List<SettleAccounts> list(@Param("firstResult") int firstResult, @Param("maxResults") int maxResults,
                              @Param("isFY") boolean isFY,
                              @Param("signingStatus") Integer signingStatus, @Param("address") String address,
                              @Param("houseOwner") String houseOwner, @Param("areaId") Long areaId,
                              @Param("areaIdList") List<Long> areaIdList, @Param("startDate") String startDate,
                              @Param("endDate") String endDate, @Param("compensateType") Integer compensateType,
                              @Param("cardNo") String cardNo, @Param("remark") String remark);

    Integer getCount(@Param("signingStatus") Integer signingStatus, @Param("address") String address,
                     @Param("houseOwner") String houseOwner, @Param("areaId") Long areaId,
                     @Param("areaIdList") List<Long> areaIdList, @Param("startDate") String startDate,
                     @Param("endDate") String endDate, @Param("compensateType") Integer compensateType,
                     @Param("cardNo") String cardNo, @Param("remark") String remark);


    Integer changeSignStatus(SettleAccounts settleAccounts);


    Integer updateSignDate(SettleAccounts settleAccounts);


    /** 获取指定时间段内的已签约调换、货币数量***/
    List<SettleAccounts> getSigning(@Param("signingStatus") Integer signingStatus, @Param("areaIdList") List<Long> areaIdList, @Param("startDate") String startDate,
                                    @Param("endDate") String endDate);

    /**统计协议状态**/
    List<ProtocolCountStatusDTO> countStatus();

    /**统计协议金额**/
    List<ProtocolCountMoneyDTO> countMoney();

    List<ProtocolSummaryDTO> summaryList(@Param("projectCode") String projectCode, @Param("areaIdList") List<Long> areaIdList);

    SettleAccounts getByHouseOwner(String choosePeople);
}