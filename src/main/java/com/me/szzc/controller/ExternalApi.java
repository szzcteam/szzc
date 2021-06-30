package com.me.szzc.controller;

import com.me.szzc.dao.RoomChangeMapper;
import com.me.szzc.pojo.entity.RoomChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 2021-06-17 10:50
 *
 * @author yaoyy
 */
@Controller
@RequestMapping("/ssadmin/api/external")
public class ExternalApi {

    private final RoomChangeMapper roomChangeMapper;

    @Autowired
    public ExternalApi(RoomChangeMapper roomChangeMapper) {
        this.roomChangeMapper = roomChangeMapper;
    }

    @RequestMapping("/r_change/insert")
    @ResponseBody
    public int insertRoomChange(@RequestBody RoomChange roomChange) {
        return roomChangeMapper.insertRoomChange(roomChange);
    }
    @RequestMapping("/r_change/update")
    @ResponseBody
    public int updateRoomChange(@RequestBody RoomChange roomChange) {
        return roomChangeMapper.updateByProjectAndNumber(roomChange);
    }
    @RequestMapping("/r_change/del")
    @ResponseBody
    public int delRoomChange(@RequestBody RoomChange roomChange) {
        return roomChangeMapper.delByRemoteId(roomChange.getRemoteId());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "我报错了")
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public int exHandler(Exception ex) {
        ex.printStackTrace();
        return 0;
    }
}
