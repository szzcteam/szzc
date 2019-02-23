package com.me.szzc.service;

import com.me.szzc.dao.NoticeMapper;
import com.me.szzc.pojo.entity.Notice;
import com.me.szzc.utils.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    public void add(Notice notice) {
        Timestamp  date = DateHelper.getTimestamp();
        notice.setCreateDate(date);
        notice.setModifiedDate(date);
        notice.setDeleted(false);
        //封装年月日
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        notice.setYears(cal.get(Calendar.YEAR)+"");
        int month = cal.get(Calendar.MONTH)+1;
        notice.setMonths(month+"");
        notice.setDays(cal.get(Calendar.DATE)+"");
        this.noticeMapper.insertSelective(notice);
    }

    public void detele(Notice notice) {
        notice.setModifiedDate(DateHelper.getTimestamp());
        notice.setDeleted(true);
        this.noticeMapper.delete(notice);
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
