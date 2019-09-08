package com.me.szzc.controller;

import com.me.szzc.aspect.SysLog;
import com.me.szzc.constant.SystemArgsConstant;
import com.me.szzc.enums.ModuleConstont;
import com.me.szzc.enums.ProtocolEnum;
import com.me.szzc.pojo.entity.RmbRecompense;
import com.me.szzc.pojo.entity.SwapHouse;
import com.me.szzc.pojo.vo.RmbRecompenseVO;
import com.me.szzc.pojo.vo.SwapHouseVO;
import com.me.szzc.utils.DateHelper;
import com.me.szzc.utils.ObjTransMapUtils;
import com.me.szzc.utils.WordUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * 产权调换协议
 *
 * @author luwei
 * @date 2019-02-16
 */
@Controller
public class SwapHouseController extends BaseController {

    @RequestMapping("/ssadmin/addSwapHouse")
    @SysLog(code = ModuleConstont.PROTOCOL_OPERATION, method = "新增产权调换协议")
    public ModelAndView addSwapHouse(SwapHouse swapHouse, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //判断是否存在
        SwapHouse entity = swapHouseService.getByHouseOwnerAddr(swapHouse.getHouseOwner(), swapHouse.getAddress());
        if (entity != null) {
            modelAndView.addObject("statusCode", 300);
            modelAndView.addObject("message", getOwnerOnlyMsg(swapHouse.getHouseOwner(), swapHouse.getAddress()));
            return modelAndView;
        }
        //创建人
        Long userId = getAdminSession(request).getFid();
        swapHouse.setCreateUserId(userId);
        swapHouse.setModifiedUserId(userId);
        //征收公司
        swapHouse.setCompany(constantMap.getValue(SystemArgsConstant.COMPANY));
        this.swapHouseService.addSwapHouse(swapHouse);
        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "新增成功");
        modelAndView.addObject("callbackType", "closeCurrent");
        return modelAndView;
    }

    @RequestMapping("/ssadmin/updateSwapHouse")
    @SysLog(code = ModuleConstont.PROTOCOL_OPERATION, method = "修改产权调换协议")
    public ModelAndView updateSwapHouse(SwapHouse swapHouse, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //修改人
        Long userId = getAdminSession(request).getFid();
        swapHouse.setModifiedUserId(userId);
        this.swapHouseService.updateSwapHouse(swapHouse);
        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "修改成功");
        modelAndView.addObject("callbackType", "closeCurrent");
        return modelAndView;
    }

    @RequestMapping("/ssadmin/deleteSwapHouse")
    @SysLog(code = ModuleConstont.PROTOCOL_OPERATION, method = "删除产权调换协议")
    public ModelAndView deleteSwapHouse(String idMore, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //获取条件
        String[] idArr = idMore.split(",");
        Long id = Long.valueOf(idArr[2]);
        SwapHouse swapHouse = this.swapHouseService.getById(id);
        if(swapHouse == null) {
            modelAndView.addObject("statusCode", 300);
            modelAndView.addObject("message", "查询不到产权调换协议");
            return modelAndView;
        }

        if(swapHouse.getDeleted()) {
            modelAndView.addObject("statusCode", 300);
            modelAndView.addObject("message", "数据已删除，请核查后再操作");
            return modelAndView;
        }

        //修改人
        Long userId = getAdminSession(request).getFid();
        swapHouse.setModifiedUserId(userId);
        this.swapHouseService.delete(swapHouse);
        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "删除成功");
        return modelAndView;
    }




    @RequestMapping("/ssadmin/selectSwapHouseByHouseOwner")
    public ModelAndView selectSwapHouseByHouseOwner(String idMore, String url) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(url);
        //获取条件
        String[] idArr = idMore.split(",");
        Long id = Long.valueOf(idArr[2]);
        SwapHouse swapHouse = this.swapHouseService.getById(id);
        if(swapHouse != null) {
            modelAndView.addObject("swapHouse", swapHouse);
        }
        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "查询成功");
        return modelAndView;
    }

    @RequestMapping("/ssadmin/exportSwapHouse")
    public ModelAndView exportSwapHouse(@RequestParam String idMore, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //获取条件
        String[] idArr = idMore.split(",");
        Long id = Long.valueOf(idArr[2]);
        //根据人名获取产权协议书数据
        SwapHouse swapHouse = this.swapHouseService.getById(id);
        if(swapHouse == null) {
            swapHouse = new SwapHouse();
        }
        //对象转map
        Map<String, String> map = ObjTransMapUtils.obj2Map(swapHouse);
        //协议书名称(枚举保存维护)
        String protocol = ProtocolEnum.HOUSING_PROPERTY_ECHANGE_AGREEMENT.getCode();
        //yyyyMMddHHmmssSSS的时间格式
        String date = DateHelper.date2String(new Date(), DateHelper.DateFormatType.YearMonthDay_HourMinuteSecond_MESC);
        String fileName = protocol + "_" + swapHouse.getHouseOwner() + "_" + date +".doc";
        //文件导出
        WordUtils.exportMillCertificateWord
                (response, map, fileName, "HousingPropertyEchangeAgreement.ftl");

        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "导出成功");
        return modelAndView;
    }

    @RequestMapping("ssadmin/swapHouse/preview")
    public ModelAndView preview(Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/detailSwapHouse");
        SwapHouse entity = this.swapHouseService.getById(id);
        if(entity != null) {
            SwapHouseVO vo = SwapHouseVO.parse(entity);
            modelAndView.addObject("swapHouse", vo);
        }
        return modelAndView;
    }
}
