package com.me.szzc.controller;

import com.me.szzc.pojo.dto.ProtocolCountStatusDTO;
import com.me.szzc.pojo.dto.RoomChangeNumDTO;
import com.me.szzc.pojo.entity.Area;
import com.me.szzc.pojo.vo.ProtocolCountMoneyVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 协议统计
 * @author luwei
 * @date 2020/5/21
 */
@Slf4j
@Controller
@RequestMapping("ssadmin/report/count/")
public class ProtocolCountController extends BaseController{

    @RequestMapping("/init")
    public ModelAndView export(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView();
        view.setViewName("ssadmin/report/countList");

        //查房源统计
        List<RoomChangeNumDTO> roomList = roomChangeService.countRoomNum();
        view.addObject("roomList", roomList);

        //查协议状态统计
        List<ProtocolCountStatusDTO> statusList = settleAccountsService.countStatus();
        view.addObject("statusList", statusList);

        //查协议金额统计
        List<ProtocolCountMoneyVO> moneyList =  settleAccountsService.countMoney();
        view.addObject("moneyList", moneyList);

        return view;
    }


}
