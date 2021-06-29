package com.me.szzc.service;

import com.me.szzc.pojo.vo.RoomChangeVo;
import org.springframework.context.ApplicationEvent;

/**
 * 2021-06-14 21:49
 *
 * @author yaoyy
 */
public class ChooseRoomEvent extends ApplicationEvent {

    private final RoomChangeVo roomChangeVo;

    public ChooseRoomEvent(Object source, RoomChangeVo roomChangeVo) {
        super(source);
        this.roomChangeVo = roomChangeVo;
    }

    public RoomChangeVo getVo() {
        return roomChangeVo;
    }
}
