package com.me.szzc.dao;


import com.me.szzc.pojo.entity.Frole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FroleMapper {

	/**
	 * 添加角色
	 */
    int insert(Frole record);

    /**
     * 根据主键id查询角色
     */
    Frole selectByPrimaryKey(Long fid);

    /**
     * 查询所以角色
     */
    List<Frole> selectAll();

    /**
     * 更新角色信息
     */
    int updateByPrimaryKey(Frole record);
    
    /**
     * 分页查询角色信息
     */
    List<Frole> selectRolePageList(Map<String, Object> map);
    
    /**
     * 查询角色总数
     */
    int countRoleByPage(Map<String, Object> map);
    
    int checkRole(String fname);

    int delete(int fid);

    List<Frole> list(@Param("firstResult") int firstResult, @Param("maxResults")int maxResults,
                     @Param("filter") String filter, @Param("isFY") boolean isFY);


    Integer getAllCount(@Param("filter") String filter);
}