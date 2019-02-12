package com.me.szzc.service;

import com.me.szzc.dao.FwebbaseinfoMapper;
import com.me.szzc.pojo.entity.Fwebbaseinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luwei
 * @date 2019-02-12
 */
@Service
public class WebBaseInfoService {

    @Autowired
    private FwebbaseinfoMapper fwebbaseinfoMapper;


    public List<Fwebbaseinfo> selectAll() {
        return fwebbaseinfoMapper.selectAll();
    }

    public void saveObj(Fwebbaseinfo fwebbaseinfo) {
        fwebbaseinfoMapper.insertSelective(fwebbaseinfo);
    }

    public void updateObj(Fwebbaseinfo fwebbaseinfo) {
        fwebbaseinfoMapper.updateByPrimaryKeySelective(fwebbaseinfo);
    }

    public Fwebbaseinfo selectByPrimaryKey(Long fid) {
        return fwebbaseinfoMapper.selectByPrimaryKey(fid);
    }
}
