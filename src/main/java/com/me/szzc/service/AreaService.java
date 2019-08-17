package com.me.szzc.service;

import com.me.szzc.dao.AreaMapper;
import com.me.szzc.pojo.entity.Area;
import com.me.szzc.pojo.entity.RoomChange;
import com.me.szzc.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AreaService {

    @Autowired
    private AreaMapper areaMapper;

    public void insert(Area area) {
        this.areaMapper.insert(area);
    }


    public Map<String, Object> queryPage(int pageSize, int pageNum, String name) {
        int count = areaMapper.getCount(name);
        int start = (pageNum - 1) * pageSize;
        List<Area> list = areaMapper.queryPage(start, pageSize, name);
        Map<String, Object> map = new HashMap();
        map.put("total", count);
        map.put("datas", list);
        return map;
    }

}
