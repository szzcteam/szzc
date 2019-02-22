package com.me.szzc.dao;


import com.me.szzc.pojo.entity.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);

    List<Notice> selectAll();
    
    List<Notice> list(@Param("firstResult") int firstResult, @Param("maxResults")int maxResults);


    int queryByname(String houseOwner);

    Notice selectByHouseOwner(String houseOwner );
}