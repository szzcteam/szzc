package com.me.szzc.controller;

import com.me.szzc.constant.SystemArgsConstant;
import com.me.szzc.enums.CompensateTypeEnum;
import com.me.szzc.enums.SigningStatusEnum;
import com.me.szzc.pojo.entity.*;
import com.me.szzc.pojo.vo.ProtocolVO;
import com.me.szzc.utils.DateHelper;
import com.me.szzc.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
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

        String signingStatusStr = request.getParameter("signingStatus");
        Integer signingStatus = null;
        if(StringUtils.isNotBlank(signingStatusStr)) {
            signingStatus = Integer.valueOf(signingStatusStr);
            view.addObject("signingStatus", signingStatus);
        }
        String address = request.getParameter("address");
        if(address != null && address.trim().length() >0){
            address = address.trim();
            view.addObject("address", address);
        }

        String houseOwner = request.getParameter("houseOwner");
        if(houseOwner != null && houseOwner.trim().length() >0){
            houseOwner = houseOwner.trim();
            view.addObject("houseOwner", houseOwner);
        }

        String areaIdStr = request.getParameter("areaId");
        Long areaId =  null;
        if(areaIdStr != null && areaIdStr.trim().length() >0){
            areaIdStr = areaIdStr.trim();
            areaId =  Long.valueOf(areaIdStr);
            view.addObject("areaId", areaId);
        }

        String startDate = request.getParameter("startDate");
        if(StringUtils.isNotBlank(startDate) && startDate.trim().length()>0){
            view.addObject("startDate", startDate);
        }

        String endDate = request.getParameter("endDate");
        if(StringUtils.isNotBlank(endDate) && endDate.trim().length()>0){
            view.addObject("endDate", endDate);
        }

        String compensateTypeStr = request.getParameter("compensateType");
        Integer compensateType = null;
        if(StringUtils.isNotBlank(compensateTypeStr)){
            compensateType = Integer.valueOf(compensateTypeStr);
            view.addObject("compensateType", compensateType);
        }

        Long userId = getAdminSession(request).getFid();

        //获取用户管理的片区
        List<Area> areaList = getUserArea(userId);
        view.addObject("areaList", areaList);

        //转换格式
        List<Long> areaIdList = convertAreaIdList(areaList);

        //封装片区
        Map<Long, String> areaMap = convertUserAreaMap(areaList);

        int firstResult = (currentPage - 1) * numPerPage;
        List<SettleAccounts> dataList = this.settleAccountsService.list(firstResult, numPerPage, true,
                signingStatus, address, houseOwner, areaId, areaIdList, startDate, endDate, compensateType);

        //Map<String ,String>map= this.noticeService.queryAll();
        List<ProtocolVO> list = new ArrayList<>();

        for (SettleAccounts account : dataList) {
            ProtocolVO protocol = new ProtocolVO();
            if(StringUtils.isNotBlank(account.getHouseOwner())){
                protocol.setName(account.getHouseOwner());
            }else{
                protocol.setName(account.getLessee());
            }

            protocol.setAddress(account.getAddress());
            if(StringUtils.isNotBlank(account.getPhone())){
                protocol.setPhone(account.getPhone());
            }else{
                protocol.setPhone(account.getLesseePhone());
            }

            SwapHouse swapHouse = this.swapHouseService.getByHouseOwnerAddr(protocol.getName(), account.getAddress());
            RmbRecompense rmbRecompense = this.rmbRecompenseService.getByHouseOwnerAddr(protocol.getName(), account.getAddress());

            protocol.setSigningStatus(account.getSigningStatus());
            protocol.setSigningStatusDesc(SigningStatusEnum.getDesc(account.getSigningStatus()));
            protocol.setCreateDateStr(DateHelper.date2String(account.getCreateDate(), DateHelper.DateFormatType.YearMonthDay_HourMinuteSecond));
            protocol.setSettleAccountId(account.getId());
            protocol.setSwapHouseId(swapHouse != null ? swapHouse.getId() : 0);
            protocol.setRmbRecompenseId(rmbRecompense != null ? rmbRecompense.getId() : 0);
            protocol.setAreaName(areaMap.get(account.getAreaId()));

            list.add(protocol);
        }

        view.addObject("protocolList", list);
        view.addObject("signingStatusMap", SigningStatusEnum.getDescMap());
        view.addObject("numPerPage", numPerPage);
        view.addObject("currentPage", currentPage);
        view.addObject("rel", "protocolList");

        view.addObject("compensateTypeMap", CompensateTypeEnum.getDescMap());

        //总数量
        view.addObject("totalCount", this.settleAccountsService.getCount(signingStatus, address, houseOwner,
                areaId, areaIdList, startDate, endDate, compensateType));
        return view;
    }


    @RequestMapping("/ssadmin/goProtocolJSP")
    public ModelAndView toPage(HttpServletRequest request) {
        String url = request.getParameter("url");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(url);
        //初始化水电空调参数
        initWaterAmmerParam(modelAndView);
        //获取用户管理的片区
        Long userId = getAdminSession(request).getFid();
        List<Area> areaList = getUserArea(userId);
        modelAndView.addObject("areaList", areaList);
        return modelAndView;
    }


    @RequestMapping("/ssadmin/goPageJsp")
    public ModelAndView goPageJsp(HttpServletRequest request) {
        String url = request.getParameter("url");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(url);
        return modelAndView;
    }



}
