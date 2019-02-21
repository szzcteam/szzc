package com.me.szzc.controller;

import com.me.szzc.pojo.vo.ProtocolVO;
import com.me.szzc.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 协议管理
 * @author luwei
 * @date 2019-02-17
 */
@Controller
public class ProtocolController extends BaseController {

    private int numPerPage = Utils.getNumPerPage();

    @RequestMapping("/ssadmin/protocolList")
    public ModelAndView Index(HttpServletRequest request) throws Exception{
        ModelAndView view = new ModelAndView();
        view.setViewName("ssadmin/protocolList");
        int currentPage = 1;

        Map<String ,String>map= this.noticeService.queryAll();
        List<ProtocolVO> list = new ArrayList<>();

        for (Map.Entry<String,String> maps:map.entrySet()) {
            ProtocolVO protocol = new ProtocolVO();
            protocol.setName(maps.getKey());
            protocol.setPhone(maps.getValue());
            boolean swapHouse = this.swapHouseService.queryName(maps.getKey());
            boolean rmbRecompense = this.rmbRecompenseService.queryName(maps.getKey());
            boolean settleAccounts = this.settleAccountsService.queryName(maps.getKey());
            protocol.setNoticeFlag(true);
            protocol.setSwapHouseFlag(swapHouse);
            protocol.setRmbRecompenseFlag(rmbRecompense);
            protocol.setSettleAccountsFlag(settleAccounts);

            if(true&&swapHouse&&settleAccounts){
                protocol.setStatus("已完成");
            }else if(true&&rmbRecompense&&settleAccounts){
                protocol.setStatus("已完成");
            }else{
                protocol.setStatus("未完成");
            }

            list.add(protocol);
        }

        view.addObject("protocolList", list);
        return view;
    }


    @RequestMapping("/ssadmin/goProtocolJSP")
    public ModelAndView toPage(HttpServletRequest request) {
        String url = request.getParameter("url");
        ModelAndView modelAndView = new ModelAndView() ;
        modelAndView.setViewName(url) ;
        return modelAndView;
    }



}
