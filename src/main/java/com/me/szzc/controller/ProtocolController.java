package com.me.szzc.controller;

import com.me.szzc.enums.CompensateTypeEnum;
import com.me.szzc.enums.SigningStatusEnum;
import com.me.szzc.pojo.entity.*;
import com.me.szzc.pojo.vo.ProtocolVO;
import com.me.szzc.utils.DateHelper;
import com.me.szzc.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 协议管理
 * @author luwei
 * @date 2019-02-17
 */
@Slf4j
@Controller
public class ProtocolController extends BaseController {

    private int numPerPage = Utils.getNumPerPage();

    @RequestMapping("/ssadmin/protocolList")
    public ModelAndView Index(HttpServletRequest request) throws Exception {
        ModelAndView view = new ModelAndView();
        view.setViewName("ssadmin/protocolList");
        int currentPage = 1;
        if (request.getParameter("pageNum") != null) {
            currentPage = Integer.parseInt(request.getParameter("pageNum"));
        }

        String signingStatusStr = request.getParameter("signingStatus");
        Integer signingStatus = null;
        if (StringUtils.isNotBlank(signingStatusStr)) {
            signingStatus = Integer.valueOf(signingStatusStr);
            view.addObject("signingStatus", signingStatus);
        }
        String address = request.getParameter("address");
        if (address != null && address.trim().length() > 0) {
            address = address.trim();
            view.addObject("address", address);
        }

        String cardNo = request.getParameter("cardNo");
        if (StringUtils.isNotBlank(cardNo)) {
            cardNo = cardNo.trim();
            view.addObject("cardNo", cardNo);
        }

        String houseOwner = request.getParameter("houseOwner");
        if (houseOwner != null && houseOwner.trim().length() > 0) {
            houseOwner = houseOwner.trim();
            view.addObject("houseOwner", houseOwner);
        }

        String remark = request.getParameter("remark");
        if (remark != null && remark.trim().length() > 0) {
            remark = remark.trim();
            view.addObject("remark", remark);
        }


        String areaIdStr = request.getParameter("areaId");
        Long areaId = null;
        if (areaIdStr != null && areaIdStr.trim().length() > 0) {
            areaIdStr = areaIdStr.trim();
            areaId = Long.valueOf(areaIdStr);
            view.addObject("areaId", areaId);
        }

        String startDate = request.getParameter("startDate");
        if (StringUtils.isNotBlank(startDate) && startDate.trim().length() > 0) {
            view.addObject("startDate", startDate);
        }

        String endDate = request.getParameter("endDate");
        if (StringUtils.isNotBlank(endDate) && endDate.trim().length() > 0) {
            view.addObject("endDate", endDate);
        }

        String compensateTypeStr = request.getParameter("compensateType");
        Integer compensateType = null;
        if (StringUtils.isNotBlank(compensateTypeStr)) {
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

        //如果没有片区权限，则页面应该无记录
        if (areaList == null || areaList.isEmpty()) {
            areaId = -1L;
        }

        int firstResult = (currentPage - 1) * numPerPage;

        long startTime = System.currentTimeMillis();
        //查询符合条件的结算单
        List<SettleAccounts> dataList = this.settleAccountsService.list(firstResult, numPerPage, true,
                signingStatus, address, houseOwner, areaId, areaIdList, startDate, endDate, compensateType, cardNo,
                remark);
        long endTime = System.currentTimeMillis();
        log.info("查询结算单耗时 ms:{}", endTime - startTime);

        //结算单ID 集合
        List<Long> accountIdList = new ArrayList<>();
        //结算单姓名集合
        List<String> houseOwnerList = new ArrayList<>();
        //计算单地址集合
        List<String> addressList = new ArrayList<>();

        for (SettleAccounts account : dataList) {
            accountIdList.add(account.getId());

            addressList.add(account.getAddress());
            if (StringUtils.isNotBlank(account.getHouseOwner())) {
                houseOwnerList.add(account.getHouseOwner());
            } else {
                houseOwnerList.add(account.getLessee());
            }
        }

        //结算单的其它信息-备注
        Map<Long, ProtocolOther> protocolOtherMap = new HashMap<>();
        if (!accountIdList.isEmpty()) {
            List<ProtocolOther> protocolOtherList = this.protocolOtherService.queryByAccounts(accountIdList);
            if (protocolOtherList != null && !protocolOtherList.isEmpty()) {
                protocolOtherList.forEach(other -> {
                    protocolOtherMap.put(other.getAccountsId(), other);
                });
            }
        }
        long otherTime = System.currentTimeMillis();
        log.info("查询结算单其他信息信息耗时 ms:{}", otherTime - endTime);

        //结算单-对应的货币补偿
        List<SwapHouse> swapHouseList = swapHouseService.listByNameAddressList(houseOwnerList, addressList);
        long swapTime = System.currentTimeMillis();
        log.info("查询产权调换耗时 ms:{}", swapTime - otherTime);

        //结算单-对应的产权调换
        List<RmbRecompense> rmbRecompensesList = rmbRecompenseService.listByNameAddressList(houseOwnerList, addressList);
        long rmbTime = System.currentTimeMillis();
        log.info("查询货币补偿耗时 ms:{}", rmbTime - swapTime);

        //封装返回对象VO
        List<ProtocolVO> list = new ArrayList<>();

        for (SettleAccounts account : dataList) {
            ProtocolVO protocol = new ProtocolVO();
            if (StringUtils.isNotBlank(account.getHouseOwner())) {
                protocol.setName(account.getHouseOwner());
            } else {
                protocol.setName(account.getLessee());
            }

            protocol.setCardNo(account.getCardNo());
            protocol.setAddress(account.getAddress());
            if (StringUtils.isNotBlank(account.getPhone())) {
                protocol.setPhone(account.getPhone());
            } else {
                protocol.setPhone(account.getLesseePhone());
            }

            protocol.setSigningStatus(account.getSigningStatus());
            protocol.setSigningStatusDesc(SigningStatusEnum.getDesc(account.getSigningStatus()));
            protocol.setSigningDateStr(DateHelper.date2String(account.getSigningDate(), DateHelper.DateFormatType.YearMonthDay_HourMinuteSecond));
            protocol.setSettleAccountId(account.getId());
            protocol.setSwapHouseId(0L);
            protocol.setRmbRecompenseId(0L);

            //填充协议ID
            if (rmbRecompensesList != null && !rmbRecompensesList.isEmpty()) {
                Optional<RmbRecompense> opt = rmbRecompensesList.stream().filter(po -> po.getHouseOwner().equals(protocol.getName())).filter(po -> po.getAddress().equals(protocol.getAddress())).findFirst();
                if (opt != null && opt.isPresent()) {
                    protocol.setRmbRecompenseId(opt.get().getId());
                }
            }
            if (swapHouseList != null && !swapHouseList.isEmpty()) {
                Optional<SwapHouse> opt = swapHouseList.stream().filter(po -> po.getHouseOwner().equals(protocol.getName())).filter(po -> po.getAddress().equals(protocol.getAddress())).findFirst();
                if (opt != null && opt.isPresent()) {
                    protocol.setSwapHouseId(opt.get().getId());
                }
            }

            protocol.setAreaName(areaMap.get(account.getAreaId()));

            ProtocolOther other = protocolOtherMap.get(account.getId());
            if (other != null) {
                protocol.setRemark(other.getRemark());
            }

            list.add(protocol);
        }

        long fullTime = System.currentTimeMillis();
        log.info("封装协议列表数据耗时 ms:{}", fullTime - rmbTime);

        view.addObject("protocolList", list);
        view.addObject("signingStatusMap", SigningStatusEnum.getDescMap());
        view.addObject("numPerPage", numPerPage);
        view.addObject("currentPage", currentPage);
        view.addObject("rel", "protocolList");

        view.addObject("compensateTypeMap", CompensateTypeEnum.getDescMap());

        //总数量
        int total = this.settleAccountsService.getCount(signingStatus, address, houseOwner,
                areaId, areaIdList, startDate, endDate, compensateType, cardNo, remark);
        long countTime = System.currentTimeMillis();
        log.info("查询总记录条数耗时 ms:{}", countTime - fullTime);
        view.addObject("totalCount", total);
        return view;
    }


    @RequestMapping("/ssadmin/goProtocolJSP")
    public ModelAndView toPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String url = request.getParameter("url");
        modelAndView.setViewName(url);

        String projectCode = request.getParameter("projectCode");
        if(StringUtils.isNotBlank(projectCode)){
            modelAndView.addObject("projectCode", projectCode);
        }

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

    @RequestMapping("/ssadmin/to-print-test")
    public ModelAndView toPrintTest(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/printTest");
        return modelAndView;
    }


}
