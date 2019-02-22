package com.me.szzc.service;

import com.me.szzc.dao.NoticeMapper;
import com.me.szzc.pojo.entity.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    public Map<String ,String> queryAll() {
        List<Notice> listNotice= this.noticeMapper.selectAll();

        Map<String, String> map = new HashMap<String, String>();
        for (Notice notice:listNotice) {
            map.put(notice.getHouseOwner(),notice.getPhone());
        }
        return map;
    }

    public Map<String,String> list(int firstResult, int maxResults) {
        List<Notice> listNotice =  this.noticeMapper.list(firstResult, maxResults);
        Map<String, String> map = new HashMap<String, String>();
        for (Notice notice:listNotice) {
            map.put(notice.getHouseOwner(),notice.getPhone());
        }
        return map;
    }

    public Boolean queryName(String houseOwner) {
        int i = this.noticeMapper.queryByname(houseOwner);
        if(i==0){
            return false;
        }
        else{
            return true;
        }
    }

    public Notice selectByHouseOwner(String houseOwner) {
        return this.noticeMapper.selectByHouseOwner(houseOwner);

    }

}
