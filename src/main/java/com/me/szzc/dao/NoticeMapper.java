package com.me.szzc.dao;


import com.me.szzc.pojo.entity.Notice;

import java.util.List;

public interface NoticeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);

    List<Notice> selectAll();

    int queryByname(String houseOwner);
}