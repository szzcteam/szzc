package com.me.szzc.controller;

import com.me.szzc.constant.SystemArgsConstant;
import com.me.szzc.pojo.entity.Fsystemargs;
import com.me.szzc.pojo.entity.SettleAccounts;
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
        if(request.getParameter("pageNum") != null){
            currentPage = Integer.parseInt(request.getParameter("pageNum"));
        }

        List<SettleAccounts> dataList = this.settleAccountsService.list((currentPage - 1) * numPerPage, numPerPage);


        //Map<String ,String>map= this.noticeService.queryAll();
        List<ProtocolVO> list = new ArrayList<>();

        for (SettleAccounts account : dataList) {
            ProtocolVO protocol = new ProtocolVO();
            protocol.setName(account.getHouseOwner());
            protocol.setAddress(account.getAddress());
            protocol.setPhone(account.getPhone());
            boolean swapHouse = this.swapHouseService.queryName(account.getHouseOwner());
            boolean rmbRecompense = this.rmbRecompenseService.queryName(account.getHouseOwner());
            protocol.setSettleAccountsFlag(true);
            protocol.setSwapHouseFlag(swapHouse);
            protocol.setRmbRecompenseFlag(rmbRecompense);
           // protocol.setSettleAccountsFlag(settleAccounts);

            if(true&&swapHouse){
                protocol.setStatus("已完成");
            }else if(true&&rmbRecompense){
                protocol.setStatus("已完成");
            }else{
                protocol.setStatus("未完成");
            }

            list.add(protocol);
        }

        view.addObject("protocolList", list);
        view.addObject("numPerPage", numPerPage);
        view.addObject("currentPage", currentPage);
        return view;
    }


    @RequestMapping("/ssadmin/goProtocolJSP")
    public ModelAndView toPage(HttpServletRequest request) {
        String url = request.getParameter("url");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(url);
        //初始化水电空调参数
        initWaterAmmerParam(modelAndView);
        return modelAndView;
    }



}
