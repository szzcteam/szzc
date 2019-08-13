package com.me.szzc.dao;

import com.me.szzc.pojo.entity.RoomChange;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by bbfang on 2019/7/29.
 */
public interface RoomChangeMapper {

    int insertRoomChange(RoomChange roomChange);

    Integer getCount(@Param("name") String name, @Param("district") String district);

    List<RoomChange> queryPage(@Param("start") Integer start,
                               @Param("pageSize") Integer pageSize,
                               @Param("name") String name,
                               @Param("district") String district);

    Integer deleteRoomChange(Long id);

    RoomChange getRoomChangeById(Long id);

    Integer selectRoomChangeByParam(RoomChange roomChange);

    Integer updateChooseRoom(@Param("choosePeople")String choosePeople,
                             @Param("name")String name,
                             @Param("number")String number);

    Integer updateRoomChange(RoomChange roomChange);

    List<RoomChange> selectRoomChange(RoomChange roomChange);
}
