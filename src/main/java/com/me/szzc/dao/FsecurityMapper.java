package com.me.szzc.dao;

import java.util.List;
import java.util.Map;

import com.me.szzc.pojo.entity.Fsecurity;
import org.apache.ibatis.annotations.Param;


public interface FsecurityMapper {

	/**
	 * 删除权限
	 */
	int deleteByPrimaryKey(Long fid);

	/**
	 * 添加权限
	 */
	int insert(Fsecurity record);

	/**
	 * 根据id查询权限
	 */
	Fsecurity selectByPrimaryKey(Long fid);

	Fsecurity selectByPrimaryKeyAndRole(@Param("fid") Integer fid, @Param("froleid") Integer froleid);

	/**
	 * 查询所有的权限
	 */
	List<Fsecurity> selectAll();

	List<Fsecurity> list(@Param("firstResult") int firstResult, @Param("maxResults")int maxResults,
						 @Param("filter") String filter, @Param("isFY") boolean isFY);

	Integer getAllCount(@Param("filter") String filter);


	List<Fsecurity> selectByProperty(Fsecurity obj);

	/**
	 * 根据id更新权限
	 */
	int updateByPrimaryKey(Fsecurity record);

	/***** Admin ******/

	/**
	 * 根据角色查询所以的权限
	 */
	List<Fsecurity> findFSecurityList(@Param("froleid") int froleid);

	/**
	 * 查询权限树
	 */
	List<Fsecurity> getSecurityByTree();

	/**
	 * 分页查询权限记录
	 */
	List<Fsecurity> getSecurityByPid(Map<String, Object> map);

	/**
	 * 分页查询权限记录数量
	 */
	int countSecurityByPid(Map<String, Object> map);

	/**
	 * 通过父id查询所有的子元素
	 */
	List<Fsecurity> selectByParentid(int pid);

	List<Fsecurity> selectByParentidAndRole(@Param("froleid") Integer froleid, @Param("fparentid") Integer fparentid);

}