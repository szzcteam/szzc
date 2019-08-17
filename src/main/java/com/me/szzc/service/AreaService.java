package com.me.szzc.service;

import com.me.szzc.dao.AreaMapper;
import com.me.szzc.dao.AreaRoleMapper;
import com.me.szzc.enums.AreaStatusEnum;
import com.me.szzc.pojo.entity.Area;
import com.me.szzc.pojo.entity.AreaRole;
import com.me.szzc.pojo.entity.RoomChange;
import com.me.szzc.utils.DateHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Autowired
    private AreaRoleMapper areaRoleMapper;

    @Transactional
    public int insert(String name, String roleIds, Long createUserId) {
        //保存片区
        Area area = new Area();
        area.setStatus(AreaStatusEnum.ENABLE.getCode());
        area.setName(name);
        area.setDeleted(false);
        area.setCreateDate(DateHelper.getTimestamp());
        area.setCreateUserId(createUserId);
        area.setModifiedDate(area.getCreateDate());
        area.setModifiedUserId(createUserId);
        int result = areaMapper.insert(area);
        if(result <= 0) {
            return result;
        }

        Long areaId = area.getId();
        String[] arr = roleIds.split(",");
        for(String str : arr) {
            str = str.trim();
            if(StringUtils.isBlank(str)) {
                continue;
            }

            AreaRole areaRole = new AreaRole();
            areaRole.setAreaId(areaId);
            areaRole.setRoleId(Long.valueOf(str));
            areaRoleMapper.insert(areaRole);
        }
        return result;
    }

    public Area getByName(String name){
        return areaMapper.getByName(name);
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
