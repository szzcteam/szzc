package com.me.szzc.controller;

import com.me.szzc.constant.SystemArgsConstant;
import com.me.szzc.pojo.entity.Fsystemargs;
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

       // Map<String ,String>map= this.noticeService.list((currentPage - 1) * numPerPage, numPerPage);

        Map<String ,String>map= this.settleAccountsService.list((currentPage - 1) * numPerPage, numPerPage);


        //Map<String ,String>map= this.noticeService.queryAll();
        List<ProtocolVO> list = new ArrayList<>();

        for (Map.Entry<String,String> maps:map.entrySet()) {
            ProtocolVO protocol = new ProtocolVO();
            protocol.setName(maps.getKey());
            protocol.setPhone(maps.getValue());
            boolean swapHouse = this.swapHouseService.queryName(maps.getKey());
            boolean rmbRecompense = this.rmbRecompenseService.queryName(maps.getKey());
           // boolean settleAccounts = this.settleAccountsService.queryName(maps.getKey());
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
        //查询空调
        String airConditionerCabinet = systemArgsService.getValue(SystemArgsConstant.AIR_CONDITIONER_CABINET);
        String airConditionerHang = systemArgsService.getValue(SystemArgsConstant.AIR_CONDITIONER_HANG);
        String airConditionerShutter = systemArgsService.getValue(SystemArgsConstant.AIR_CONDITIONER_SHUTTER);
        modelAndView.addObject("airConditionerCabinet", airConditionerCabinet);
        modelAndView.addObject("airConditionerHang", airConditionerHang);
        modelAndView.addObject("airConditionerShutter", airConditionerShutter);

        //查询水表
        String waterMeterMain = systemArgsService.getValue(SystemArgsConstant.WATER_METER_MAIN);
        String waterMeterSub = systemArgsService.getValue(SystemArgsConstant.WATER_METER_SUB);
        modelAndView.addObject("waterMeterMain", waterMeterMain);
        modelAndView.addObject("waterMeterSub", waterMeterSub);

        //查询电表
        String ammeterMain= systemArgsService.getValue(SystemArgsConstant.AMMETER_MAIN);
        String ammeterSub= systemArgsService.getValue(SystemArgsConstant.AMMETER_SUB);
        modelAndView.addObject("ammeterMain", ammeterMain);
        modelAndView.addObject("ammeterSub", ammeterSub);
        return modelAndView;
    }



}
