package com.me.szzc.dao;

import com.me.szzc.pojo.RoomChangeExport;
import com.me.szzc.pojo.dto.RoomChangeNumDTO;
import com.me.szzc.pojo.entity.RoomChange;
import com.me.szzc.pojo.vo.RoomChangeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by bbfang on 2019/7/29.
 */
public interface RoomChangeMapper {

    int insertRoomChange(RoomChange roomChange);

    Integer getCount(RoomChangeVo roomChangeVo);

    List<RoomChange> queryPage(RoomChangeVo roomChangeVo);

    Integer deleteRoomChange(Long id);

    RoomChange getRoomChangeById(Long id);

    Integer selectRoomChangeByParam(RoomChange roomChange);

    Integer updateChooseRoom(@Param("choosePeople") String choosePeople,
                             @Param("name") String name,
                             @Param("number") String number);

    Integer updateRoomChange(RoomChange roomChange);

    List<RoomChange> selectRoomChange(RoomChange roomChange);

    void addChooseRoom(RoomChangeVo roomChangeVo);

    void updateChooseRoomBy0(RoomChangeVo roomChangeVo);

    void updateChooseRoomNot0(RoomChangeVo roomChangeVo);

    List<RoomChangeExport> selectAll(@Param("list") List<String> list);

    /**统计房源数量**/
    List<RoomChangeNumDTO> countNum();

    /**统计房源-各面积套数**/
    List<RoomChangeNumDTO> countAreaNumByProjectCode(@Param("projectCode") String projectCode);


}
