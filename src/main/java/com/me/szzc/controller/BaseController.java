package com.me.szzc.controller;

import com.me.szzc.constant.Constant;
import com.me.szzc.constant.SystemArgsConstant;
import com.me.szzc.enums.GovernmentEnum;
import com.me.szzc.pojo.entity.Area;
import com.me.szzc.pojo.entity.Fadmin;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luwei
 * @date 2019-01-25
 */
public class BaseController extends BaseServiceCtrl{

    protected static final String STATUS_CODE_KEY = "statusCode";
    protected static final String MESSAGE_KEY = "message";
    protected static final String CALLBACK_TYPE_KEY = "callbackType";

    protected static final Integer ERROR_CODE_NUM = Constant.ERROR_CODE;
    protected static final Integer SUCCESS_CODE_NUM = Constant.SUCCESS_CODE;



    public static String getIpAddr(HttpServletRequest request) {
        try {
            String ip = request.getHeader("X-Forwarded-For");
            try {
                if(ip != null && ip.trim().length() >0){
                    return ip.split(",")[0];
                }
            } catch (Exception e) {}

            try {
                ip = request.getHeader("X-Real-IP");
                if ((ip != null && ip.trim().length() >0) && (!"unKnown".equalsIgnoreCase(ip))) {
                    return ip;
                }
            } catch (Exception e) {}

            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("http_client_ip");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            // 如果是多级代理，那么取第一个ip为客户ip
            if (ip != null && ip.indexOf(",") != -1) {
                ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
            }
            return ip;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return request.getRemoteAddr();
        }
    }


    public HttpSession getSession(HttpServletRequest request){
        return request.getSession() ;
    }


    /**此方法会在每个controller前执行**/
    @ModelAttribute
    public void addConstant(HttpServletRequest request){
        //前端常量
        request.setAttribute("constant", constantMap.getMap()) ;
        String ossURL = "";
        //静态文件在项目里面，无需配置全局oss_url
        request.setAttribute("oss_url", ossURL) ;

    }


    //获得管理员session
    public Fadmin getAdminSession(HttpServletRequest request){
        Object session = getSession(request).getAttribute("login_admin");
        Fadmin fadmin = null;
        if (session != null) {
            fadmin = (Fadmin) session;
        }
        return fadmin;
    }

    /***获取用户管理的所有项目 格式Map**/
    public Map<String, String> getUserProject(Long userId){
        Map<String, String> map =new HashMap<>();
        List<Area> list = areaService.listByUserId(userId);
        if (list != null && !list.isEmpty()) {
            for (Area area : list) {
                map.put(area.getProjectCode(), GovernmentEnum.getNameByCode(area.getProjectCode()));
            }
        }
        return map;
    }


    /***获取用户管理的所有项目-启用-片区项目 格式Map**/
    public Map<String, String> getUserEnableProject(Long userId){
        Map<String, String> map =new HashMap<>();
        List<Area> list = areaService.listEnableByUserId(userId);
        if (list != null && !list.isEmpty()) {
            for (Area area : list) {
                map.put(area.getProjectCode(), GovernmentEnum.getNameByCode(area.getProjectCode()));
            }
        }
        return map;
    }

    /**  Map<String, String> 转换为 List<String> **/
    public List<String> convertProejctCode(Map<String, String> map){
        List<String> codeList = new ArrayList<>();
        if (map != null && !map.isEmpty()) {
            for (Map.Entry entry : map.entrySet()) {
                codeList.add(entry.getKey().toString());
            }
        }
        return codeList;
    }



    /***获取用户管理的所有片区，返回list 格式**/
    public List<Area> getUserArea(Long userId){
        List<Area> list = areaService.listByUserId(userId);
        return list;
    }

    /***获取用户管理的所有-启用-片区，返回list 格式**/
    public List<Area> getUserEnableArea(Long userId){
        List<Area> list = areaService.listEnableByUserId(userId);
        return list;
    }

    /** List<Area> 转换为 List<Long> **/
    public List<Long> convertAreaIdList(List<Area> list){
        List<Long> idList = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (Area area : list) {
                idList.add(area.getId());
            }
        }
        return idList;
    }

    /***获取用户管理的所有片区，返回map 格式**/
    public Map<Long, String> getUserAreaMap(Long userId) {
        Map<Long, String> map = new HashMap<>();
        List<Area> list = areaService.listByUserId(userId);
        if(list != null && !list.isEmpty()) {
            for(Area area : list) {
                map.put(area.getId(), area.getName());
            }
        }
        return map;
    }

    /** List<Area> 转换为 Map<Long, String> **/
    public Map<Long, String> convertUserAreaMap(List<Area> list) {
        Map<Long, String> map = new HashMap<>();
        if(list != null && !list.isEmpty()) {
            for(Area area : list) {
                map.put(area.getId(), area.getName());
            }
        }
        return map;
    }


    /**初始化水电表参数**/
    public void initWaterAmmerParam(ModelAndView modelAndView){
        //查询空调
        String airConditionerCabinet = systemArgsService.getValue(SystemArgsConstant.AIR_CONDITIONER_CABINET);
        String airConditionerHang = systemArgsService.getValue(SystemArgsConstant.AIR_CONDITIONER_HANG);
        String airConditionerShutter = systemArgsService.getValue(SystemArgsConstant.AIR_CONDITIONER_SHUTTER);
        modelAndView.addObject("airConditionerCabinet", airConditionerCabinet);
        modelAndView.addObject("airConditionerHang", airConditionerHang);
        modelAndView.addObject("airConditionerShutter", airConditionerShutter);

        //紫阳村查询空调
        String zycAirConditionerCabinet = systemArgsService.getValue(SystemArgsConstant.ZYC_AIR_CONDITIONER_CABINET);
        String zycAirConditionerHang = systemArgsService.getValue(SystemArgsConstant.ZYC_AIR_CONDITIONER_HANG);
        String zycAirConditionerShutter = systemArgsService.getValue(SystemArgsConstant.ZYC_AIR_CONDITIONER_SHUTTER);
        modelAndView.addObject("zycAirConditionerCabinet", zycAirConditionerCabinet);
        modelAndView.addObject("zycAirConditionerHang", zycAirConditionerHang);
        modelAndView.addObject("zycAirConditionerShutter", zycAirConditionerShutter);

        //查询水表
        String waterMeterMain = systemArgsService.getValue(SystemArgsConstant.WATER_METER_MAIN);
        String waterMeterSub = systemArgsService.getValue(SystemArgsConstant.WATER_METER_SUB);
        modelAndView.addObject("waterMeterMain", waterMeterMain);
        modelAndView.addObject("waterMeterSub", waterMeterSub);

        //查询电表
        String ammeterMain = systemArgsService.getValue(SystemArgsConstant.AMMETER_MAIN);
        String ammeterSub = systemArgsService.getValue(SystemArgsConstant.AMMETER_SUB);
        String ammeterSa = systemArgsService.getValue(SystemArgsConstant.AMMETER_SA);
        String ammeterTime = systemArgsService.getValue(SystemArgsConstant.AMMETER_TIME);
        modelAndView.addObject("ammeterMain", ammeterMain);
        modelAndView.addObject("ammeterSub", ammeterSub);
        modelAndView.addObject("ammeterSa", ammeterSa);
        modelAndView.addObject("ammeterTime", ammeterTime);

        //查询搬家费
        String moveHouseRmb = systemArgsService.getValue(SystemArgsConstant.MOVE_HOUSE_RMB);
        String moveHouseSwap = systemArgsService.getValue(SystemArgsConstant.MOVE_HOUSE_SWAP);
        modelAndView.addObject("moveHouseRmb", moveHouseRmb);
        modelAndView.addObject("moveHouseSwap", moveHouseSwap);

        //查询生活困难补助
        String diseaseSubsidy = systemArgsService.getValue(SystemArgsConstant.DISEASE_SUBSIDY);
        String disabilitySubsidy = systemArgsService.getValue(SystemArgsConstant.DISABILITY_SUBSIDY);
        String basicLivingSubsidy = systemArgsService.getValue(SystemArgsConstant.BASIC_LIVING_SUBSIDY);
        String martyr = systemArgsService.getValue(SystemArgsConstant.MARTYR);
        String noChild = systemArgsService.getValue(SystemArgsConstant.NO_CHILD);
        modelAndView.addObject("diseaseSubsidy", diseaseSubsidy);
        modelAndView.addObject("disabilitySubsidy", disabilitySubsidy);
        modelAndView.addObject("basicLivingSubsidy", basicLivingSubsidy);
        modelAndView.addObject("martyr", martyr);
        modelAndView.addObject("noChild", noChild);

        //搬迁奖励
        String oneMoveReward = systemArgsService.getValue(SystemArgsConstant.ONE_MOVE_REWARD);
        String twoMoveReward = systemArgsService.getValue(SystemArgsConstant.TWO_MOVE_REWARD);
        String threeMoveReward = systemArgsService.getValue(SystemArgsConstant.THREE_MOVE_REWARD);
        modelAndView.addObject("oneMoveReward", oneMoveReward);
        modelAndView.addObject("twoMoveReward", twoMoveReward);
        modelAndView.addObject("threeMoveReward", threeMoveReward);

        //停产停业损失补偿比例
        String suspendBusinessProportion = systemArgsService.getValue(SystemArgsConstant.SUSPEND_BUSINESS_PROPORTION);
        modelAndView.addObject("suspendBusinessProportion", suspendBusinessProportion);


        //货币补偿补助比例
        String rmbCompensateProportion = systemArgsService.getValue(SystemArgsConstant.RMB_COMPENSATE_PROPORTION);
        modelAndView.addObject("rmbCompensateProportion", rmbCompensateProportion);

        //货币搬迁奖励比例
        String rmbMoveRewardProportion = systemArgsService.getValue(SystemArgsConstant.RMB_MOVE_REWARD_PROPORTION);
        modelAndView.addObject("rmbMoveRewardProportion", rmbMoveRewardProportion);

        /**无烟灶台**/
        String smokeFreeStove = systemArgsService.getValue(SystemArgsConstant.SMOKE_FREE_STOVE);
        modelAndView.addObject("smokeFreeStove", smokeFreeStove);

        /**电热水器**/
        String waterHeater = systemArgsService.getValue(SystemArgsConstant.WATER_HEATER);
        modelAndView.addObject("waterHeater", waterHeater);

        String zycWaterHeater = systemArgsService.getValue(SystemArgsConstant.ZYC_WATER_HEATER);
        modelAndView.addObject("zycWaterHeater", zycWaterHeater);



        /**太阳能热水器**/
        String solarWaterHeaters = systemArgsService.getValue(SystemArgsConstant.SOLAR_WATER_HEATERS);
        modelAndView.addObject("solarWaterHeaters", solarWaterHeaters);

        String zycSolarWaterHeaters = systemArgsService.getValue(SystemArgsConstant.ZYC_SOLAR_WATER_HEATERS);
        modelAndView.addObject("zycSolarWaterHeaters", zycSolarWaterHeaters);

        /**奖励**/
        String rewardRmbProportion = systemArgsService.getValue(SystemArgsConstant.REWARD_RMB_PROPORTION);
        String rewardSwapProportion = systemArgsService.getValue(SystemArgsConstant.REWARD_SWAP_PROPORTION);
        modelAndView.addObject("rewardRmbProportion", rewardRmbProportion);
        modelAndView.addObject("rewardSwapProportion", rewardSwapProportion);

        //保底补偿面积
        String selGuarantee = systemArgsService.getValue(SystemArgsConstant.SEL_GUARANTEE);
        modelAndView.addObject("selGuarantee", selGuarantee);

        //会计机构出具报告N天后，甲方付款给乙方
        String rmbAfterDayAudit= systemArgsService.getValue(SystemArgsConstant.RMB_AFTER_DAY_AUDIT);
        modelAndView.addObject("rmbAfterDayAudit", rmbAfterDayAudit);

    }


    protected String getExistsOnlyMsg(String houseOwner, String address) {
        String message = "";
        if (StringUtils.isBlank(houseOwner)) {
            message = "操作失败，被征收人姓名不能为空";
        } else if (StringUtils.isBlank(address)) {
            message = "操作失败，被征收房屋地址不能为空";
        } else {
            message = "操作失败，用户:" + houseOwner + ",地址:" + address + " 记录已存在，请核对后再操作";
        }
        return message;
    }


}
