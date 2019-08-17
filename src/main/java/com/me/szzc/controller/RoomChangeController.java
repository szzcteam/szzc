package com.me.szzc.controller;

import com.alibaba.fastjson.JSONObject;
import com.me.szzc.aspect.SysLog;
import com.me.szzc.enums.ModuleConstont;
import com.me.szzc.pojo.entity.RoomChange;
import com.me.szzc.pojo.vo.ResultVo;
import com.me.szzc.service.RoomChangeService;
import com.me.szzc.utils.CustomizedPropertyConfigurer;
import com.me.szzc.utils.StringUtils;
import com.me.szzc.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 换房管理controller
 * Created by bbfang on 2019/7/27.
 */
@Slf4j
@Controller
@RequestMapping("/ssadmin/roomChange")
public class RoomChangeController {
    @Autowired
    private RoomChangeService roomChangeService;

    /**
     * 房源信息excle导入
     *
     * @param file excle文件
     * @return
     */
    @RequestMapping("/importExcel")
    public ModelAndView importExcel(@RequestParam(value = "file", required = false) MultipartFile file) {
        ModelAndView view = new ModelAndView();
        view.setViewName("ssadmin/comm/ajaxDone");
        ResultVo resultVo = null;
        try {
            //校验
            if (file == null) {
                view.addObject("statusCode", 300);
                view.addObject("message", "请选择房源excel文件");
                return view;
            }

            String fileName = file.getOriginalFilename();
            int extStart = fileName.lastIndexOf(".");
            String ext = fileName.substring(extStart, fileName.length()).toLowerCase();
            log.info("文件后缀:" + ext);
            if (!ext.equals(".xls") && !ext.equals(".xlsx")) {
                view.addObject("statusCode", 300);
                view.addObject("message", "文件格式错误，只能是后缀为.xls 或 .xlsx 的excel文件");
                return view;
            }

            //上传
            resultVo = roomChangeService.importExcle(file);
        } catch (Exception e) {
            view.addObject("statusCode", 300);
            view.addObject("message", e.getMessage());
            return view;
        }
        if (resultVo.getCode() == 0) {
            view.addObject("statusCode", 200);
            view.addObject("message", "上传成功");
            view.addObject("callbackType", "closeCurrent");
        } else {
            view.addObject("statusCode", resultVo.getCode());
            view.addObject("message", resultVo.getMsg());
        }
        return view;
    }

    /**
     * 房源信息条件分页查询
     *
     * @param request
     * @param name            房源項目
     * @param number          房号
     * @param choosePeople    点房人
     * @param assignedProject 分配征收项目(片区)
     * @param housingPlatform 提供房源平台
     * @return
     */
    @RequestMapping("/queryPage")
    public ModelAndView queryPage(HttpServletRequest request, String name, String number,
                                  String choosePeople, String assignedProject, String housingPlatform) {
        ModelAndView view = new ModelAndView();
        if (!StringUtils.isNullOrEmpty(name)) {
            name = name.trim();
            if (name.length() > 0) {
                view.addObject("name", name);
            }
        }
        if (!StringUtils.isNullOrEmpty(number)) {
            number = number.trim();
            if (number.length() > 0) {
                view.addObject("number", number);
            }
        }
        if (!StringUtils.isNullOrEmpty(choosePeople)) {
            choosePeople = choosePeople.trim();
            if (choosePeople.length() > 0) {
                view.addObject("choosePeople", choosePeople);
            }
        }
        if (!StringUtils.isNullOrEmpty(assignedProject)) {
            assignedProject = assignedProject.trim();
            if (assignedProject.length() > 0) {
                view.addObject("assignedProject", assignedProject);
            }
        }
        if (!StringUtils.isNullOrEmpty(housingPlatform)) {
            housingPlatform = housingPlatform.trim();
            if (housingPlatform.length() > 0) {
                view.addObject("housingPlatform", housingPlatform);
            }
        }
        int currentPage = 1;
        if (request.getParameter("pageNum") != null) {
            currentPage = Integer.parseInt(request.getParameter("pageNum"));
        }
        view.setViewName("ssadmin/roomChangeList");
        Map<String, Object> map = roomChangeService.queryPage(Utils.getNumPerPage(), currentPage, name, number, choosePeople, assignedProject, housingPlatform);
        view.addObject("roomList", map.get("datas"));
        view.addObject("numPerPage", Utils.getNumPerPage());
        view.addObject("currentPage", currentPage);
        view.addObject("rel", "roomChange");
        view.addObject("totalCount", map.get("total"));
        return view;
    }

    /**
     * 批量删除房源信息
     *
     * @param ids
     * @return
     */
    @RequestMapping("/batchDelete")
    @SysLog(code = ModuleConstont.ROOM_CHANGE_OPERATION, method = "批量删除房源")
    public ModelAndView deleteRoomChange(String ids) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        int result = 0;
        if (!StringUtils.isNullOrEmpty(ids)) {
            String[] idArr = ids.split(",");
            for (String idStr : idArr) {
                Long id = Long.valueOf(idStr);
                result = roomChangeService.deleteRoomChange(id);
                if (result == 0) {
                    break;
                }
            }

            if (result > 0) {
                modelAndView.addObject("statusCode", 200);
                modelAndView.addObject("message", "删除成功");
            } else {
                modelAndView.addObject("statusCode", 300);
                modelAndView.addObject("message", "删除失败");
            }

            return modelAndView;
        }
        modelAndView.addObject("statusCode", 300);
        modelAndView.addObject("message", "请选择需要删除的记录");
        return modelAndView;
    }

    /**
     * 根据ID获取房源信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/getRoomChangeById")
    public ModelAndView getRoomChangeById(Long id, String url) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(url);

        RoomChange roomChange = roomChangeService.getRoomChangeById(id);
        if (roomChange != null) {
            modelAndView.addObject("roomChange", roomChange);
        }
        return modelAndView;
    }

    @RequestMapping("/download")
    public ModelAndView download(HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");

        String templateFolder = CustomizedPropertyConfigurer.getValue("template.folder");
        String fileName = "房源模板.xlsx";
        String filePath = templateFolder + File.separator + fileName;
        File file = new File(filePath);
        InputStream fin = null;
        ServletOutputStream out = null;
        try {
            // 调用工具类的createDoc方法生成Word文档
            fin = new FileInputStream(file);

            response.setCharacterEncoding("utf-8");
            response.setContentType("application/msword");
            // 设置浏览器以下载的方式处理该文件名
            response.setHeader("Content-Disposition", "attachment;filename="
                    .concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));
            response.setCharacterEncoding("utf-8");
            //您在这里稍微注意一下,中文在火狐下会出现乱码的现象
            out = response.getOutputStream();
            byte[] buffer = new byte[512];  // 缓冲区
            int bytesToRead = -1;
            // 通过循环将读入的Word文件的内容输出到浏览器中
            while ((bytesToRead = fin.read(buffer)) != -1) {
                out.write(buffer, 0, bytesToRead);
            }
        } catch (FileNotFoundException ex) {
            log.error("文件异常", ex);
        } catch (UnsupportedEncodingException ex) {
            log.error("文件下载时，名称转码异常", ex);
        } catch (IOException ex) {
            log.error("文件下载时，流异常", ex);
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                log.error("流关闭异常", e);
            }
        }
        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "下载成功");
        modelAndView.addObject("callbackType", "closeCurrent");
        return modelAndView;
    }

    /**
     * 修改单个房源信息
     *
     * @param roomChange
     * @return
     */
    @RequestMapping("/updateRoomChangeById")
    @SysLog(code = ModuleConstont.ROOM_CHANGE_OPERATION, method = "修改房源")
    public ModelAndView updateRoomChangeById(RoomChange roomChange) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //数据不为空
        if (StringUtils.isNullOrEmpty(roomChange) || StringUtils.isNullOrEmpty(roomChange.getId())) {
            modelAndView.addObject("statusCode", 300);
            modelAndView.addObject("message", "修改失败,入参为空");
            return modelAndView;
        }
        //获取数据
        RoomChange roomChangeById = roomChangeService.getRoomChangeById(roomChange.getId());
        if (StringUtils.isNullOrEmpty(roomChangeById)) {
            modelAndView.addObject("statusCode", 300);
            modelAndView.addObject("message", "修改失败,数据为空");
            return modelAndView;
        }
        Boolean status = null;
        try {
            //修改
            status = roomChangeService.updateRoomChangeById(roomChange);
        } catch (Exception e) {
            modelAndView.addObject("statusCode", 300);
            modelAndView.addObject("message", e.getMessage());
            return modelAndView;
        }

        if (status) {
            modelAndView.addObject("statusCode", 200);
            modelAndView.addObject("message", "修改成功");
            modelAndView.addObject("callbackType", "closeCurrent");
        } else {
            modelAndView.addObject("statusCode", 300);
            modelAndView.addObject("message", "修改失败");
        }
        return modelAndView;
    }

}
