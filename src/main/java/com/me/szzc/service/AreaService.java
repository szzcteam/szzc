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

    public Area getById(Long id) {
        return areaMapper.getById(id);
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


    public int updateStatus(Area area){
        return areaMapper.updateStatus(area);
    }

    @Transactional
    public void delete(Area area) {
        //删除片区
        areaMapper.delete(area.getId());
        //删除片区对应的片区权限
        areaRoleMapper.deleteByAreaId(area.getId());
    }


    public List<AreaRole> areaRoleListByAreaId(Long areaId) {
        return areaRoleMapper.listByAreaId(areaId);
    }

    public int existsByUpdateName(String name, Long id) {
        return areaMapper.existsByUpdateName(name, id);
    }

    public void update(String roleIds, String name, Long id, Long userId){
        Area area = areaMapper.getById(id);
        area.setModifiedUserId(userId);
        area.setModifiedDate(DateHelper.getTimestamp());
        area.setName(name);
        //更新片区
        areaMapper.update(area);
        //删除权限
        areaRoleMapper.deleteByAreaId(area.getId());
        //重新添加片区
        String[] arr = roleIds.split(",");
        for(String str : arr) {
            str = str.trim();
            if(StringUtils.isBlank(str)) {
                continue;
            }

            AreaRole areaRole = new AreaRole();
            areaRole.setAreaId(area.getId());
            areaRole.setRoleId(Long.valueOf(str));
            areaRoleMapper.insert(areaRole);
        }
    }
}
