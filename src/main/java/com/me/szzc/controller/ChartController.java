package com.me.szzc.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.me.szzc.aspect.SysLog;
import com.me.szzc.enums.CompensateTypeEnum;
import com.me.szzc.enums.ModuleConstont;
import com.me.szzc.enums.SigningStatusEnum;
import com.me.szzc.pojo.entity.Area;
import com.me.szzc.pojo.entity.SettleAccounts;
import com.me.szzc.pojo.vo.ReportPieCriVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 图形汇总
 * @author luwei
 * @date 2020/4/28
 */
@Slf4j
@Controller
@RequestMapping("ssadmin/report")
public class ChartController extends BaseController{


    @RequestMapping("chartList")
    public ModelAndView toChartList(HttpServletRequest request) throws Exception {
        ModelAndView view = new ModelAndView();
        view.setViewName("ssadmin/report/chartList");
        Long userId = getAdminSession(request).getFid();

        //签约状态
//        view.addObject("signingStatusMap", SigningStatusEnum.getDescMap());

        //片区
        List<Area> areaList = getUserArea(userId);
        view.addObject("areaList", areaList);

        return view;
    }


    @RequestMapping(value = "get-pie",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getPie(@RequestBody ReportPieCriVO criVO) {
        JSONObject resultJSON = new JSONObject();

        //未签约
        int notSignedNum = settleAccountsService.getNoSigning(criVO.getAreaIdList(), criVO.getStartDate(), criVO.getEndDate());
        //已签约
        List<SettleAccounts> list = settleAccountsService.getSigning(criVO.getAreaIdList(), criVO.getStartDate(), criVO.getEndDate());
        Long rmbNum = 0L;
        Long swapNum = 0L;
        if (list != null && !list.isEmpty()) {
            for (SettleAccounts accounts : list) {
                if (accounts.getCompensateType().intValue() == CompensateTypeEnum.RMB_TYPE.getCode()) {
                    rmbNum = accounts.getId();
                } else if (accounts.getCompensateType().intValue() == CompensateTypeEnum.SWAP_TYPE.getCode()) {
                    swapNum = accounts.getId();
                }
            }
        }

        List<JSONObject> seriesData = new ArrayList<>();
        JSONObject notSingnedJSON = new JSONObject();
        notSingnedJSON.put("name", SigningStatusEnum.NOT_SIGNED.getDesc());
        notSingnedJSON.put("value", notSignedNum);
        seriesData.add(notSingnedJSON);

        JSONObject rmbJSON = new JSONObject();
        rmbJSON.put("name", CompensateTypeEnum.RMB_TYPE.getShortName());
        rmbJSON.put("value", rmbNum);
        seriesData.add(rmbJSON);

        JSONObject swapJSON = new JSONObject();
        swapJSON.put("name", CompensateTypeEnum.SWAP_TYPE.getShortName());
        swapJSON.put("value", swapNum);
        seriesData.add(swapJSON);


        resultJSON.put("seriesData", seriesData);
        return resultJSON;
    }


}
