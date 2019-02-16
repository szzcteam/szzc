package com.me.szzc.service;

import com.me.szzc.dao.NoticeMapper;
import com.me.szzc.pojo.entity.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    public void add(Notice notice) {
        this.noticeMapper.insert(notice);
    }

    public void detele(Notice notice) {
        this.noticeMapper.deleteByPrimaryKey(notice.getId());
    }

    public void update(Notice notice) {
        this.noticeMapper.updateByPrimaryKeySelective(notice);
    }

    public void query(Notice notice) {
        this.noticeMapper.selectByPrimaryKey(notice.getId());
    }
}
