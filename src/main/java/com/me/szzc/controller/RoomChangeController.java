package com.me.szzc.controller;

import com.me.szzc.aspect.SysLog;
import com.me.szzc.enums.ChooseStatusEnum;
import com.me.szzc.enums.ModuleConstont;
import com.me.szzc.pojo.RoomChangeExport;
import com.me.szzc.pojo.entity.RoomChange;
import com.me.szzc.pojo.vo.PaymentNoticeVO;
import com.me.szzc.pojo.vo.ResultVO;
import com.me.szzc.pojo.vo.RoomChangeVo;
import com.me.szzc.service.RoomChangeService;
import com.me.szzc.service.StylusPrintService;
import com.me.szzc.utils.CustomizedPropertyConfigurer;
import com.me.szzc.utils.DateHelper;
import com.me.szzc.utils.StringUtils;
import com.me.szzc.utils.Utils;
import com.me.szzc.utils.excle.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * 换房管理controllerqueryPage
 * Created by bbfang on 2019/7/27.
 */
@Slf4j
@Controller
@RequestMapping("/ssadmin/roomChange")
public class RoomChangeController extends BaseController {
    @Autowired
    private RoomChangeService roomChangeService;

    @Autowired
    private StylusPrintService stylusPrintService;

    /**
     * 房源信息excle导入
     *
     * @param file     excle文件
     * @param itemCode 项目编码
     * @return
     */
    @RequestMapping("/importExcel")
    public ModelAndView importExcel(@RequestParam(value = "file", required = false) MultipartFile file, String itemCode) {
        ModelAndView view = new ModelAndView();
        view.setViewName("ssadmin/comm/ajaxDone");
        ResultVO resultVo = null;
        try {
            //校验
            if (file == null) {
                view.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
                view.addObject(MESSAGE_KEY, "请选择房源excel文件");
                return view;
            }
            if (null == itemCode || itemCode.length() < 1) {
                view.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
                view.addObject(MESSAGE_KEY, "请选择房源所属项目");
                return view;
            }
            String fileName = file.getOriginalFilename();
            int extStart = fileName.lastIndexOf(".");
            String ext = fileName.substring(extStart, fileName.length()).toLowerCase();
            log.info("文件后缀:" + ext);
            if (!ext.equals(".xls") && !ext.equals(".xlsx")) {
                view.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
                view.addObject(MESSAGE_KEY, "文件格式错误，只能是后缀为.xls 或 .xlsx 的excel文件");
                return view;
            }

            //上传
            resultVo = roomChangeService.importExcle(file, itemCode);
        } catch (Exception e) {
            view.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            view.addObject(MESSAGE_KEY, e.getMessage());
            return view;
        }
        if (resultVo.getSuccess()) {
            view.addObject(STATUS_CODE_KEY, SUCCESS_CODE_NUM);
            view.addObject(MESSAGE_KEY, "上传成功");
            view.addObject(CALLBACK_TYPE_KEY, "closeCurrent");
        } else {
            view.addObject(STATUS_CODE_KEY, resultVo.getCode());
            view.addObject(MESSAGE_KEY, resultVo.getMessage());
        }
        return view;
    }

    /**
     * 房源信息条件分页查询
     *
     * @param request
     * @param roomChangeVo 条件
     * @return
     */
    @RequestMapping("/queryPage")
    public ModelAndView queryPage(HttpServletRequest request, RoomChangeVo roomChangeVo) {
        ModelAndView view = new ModelAndView();
        // TODO 2019/12/17 新增字段备注
        if (!StringUtils.isNullOrEmpty(roomChangeVo.getRemark())) {
            roomChangeVo.setRemark(roomChangeVo.getRemark().trim());
            if (roomChangeVo.getRemark().length() > 0) {
                view.addObject("remark", roomChangeVo.getRemark());
            }
        }
        if (!StringUtils.isNullOrEmpty(roomChangeVo.getName())) {
            roomChangeVo.setName(roomChangeVo.getName().trim());
            if (roomChangeVo.getName().length() > 0) {
                view.addObject("name", roomChangeVo.getName());
            }
        }
        if (!StringUtils.isNullOrEmpty(roomChangeVo.getNumber())) {
            roomChangeVo.setNumber(roomChangeVo.getNumber().trim());
            if (roomChangeVo.getNumber().length() > 0) {
                view.addObject("number", roomChangeVo.getNumber());
            }
        }
        if (!StringUtils.isNullOrEmpty(roomChangeVo.getChoosePeople())) {
            roomChangeVo.setChoosePeople(roomChangeVo.getChoosePeople().trim());
            if (roomChangeVo.getChoosePeople().length() > 0) {
                view.addObject("choosePeople", roomChangeVo.getChoosePeople());
            }
        }
        if (!StringUtils.isNullOrEmpty(roomChangeVo.getAssignedProject())) {
            roomChangeVo.setAssignedProject(roomChangeVo.getAssignedProject().trim());
            if (roomChangeVo.getAssignedProject().length() > 0) {
                view.addObject("assignedProject", roomChangeVo.getAssignedProject());
            }
        }
        if (!StringUtils.isNullOrEmpty(roomChangeVo.getHousingPlatform())) {
            roomChangeVo.setHousingPlatform(roomChangeVo.getHousingPlatform().trim());
            if (roomChangeVo.getHousingPlatform().length() > 0) {
                view.addObject("housingPlatform", roomChangeVo.getHousingPlatform());
            }
        }
        if (!StringUtils.isNullOrEmpty(roomChangeVo.getStatus())) {
            view.addObject("status", roomChangeVo.getStatus());
        }

        if (!StringUtils.isNullOrEmpty(roomChangeVo.getStartDate())) {
            view.addObject("startDate", roomChangeVo.getStartDate());
        }

        if (!StringUtils.isNullOrEmpty(roomChangeVo.getEndDate())) {
            view.addObject("endDate", roomChangeVo.getEndDate());
        }

        if (!StringUtils.isNullOrEmpty(roomChangeVo.getMinArea())) {
            view.addObject("minArea", roomChangeVo.getMinArea());
        }

        if (!StringUtils.isNullOrEmpty(roomChangeVo.getMaxArea())) {
            view.addObject("maxArea", roomChangeVo.getMaxArea());
        }

        if (!StringUtils.isNullOrEmpty(roomChangeVo.getCommissionCompany())) {
            roomChangeVo.setCommissionCompany(roomChangeVo.getCommissionCompany().trim());
            if (roomChangeVo.getCommissionCompany().length() > 0) {
                view.addObject("commissionCompany", roomChangeVo.getCommissionCompany());
            }
        }

        int currentPage = 1;
        if (request.getParameter("pageNum") != null) {
            currentPage = Integer.parseInt(request.getParameter("pageNum"));
        }
        view.setViewName("ssadmin/roomChangeList");
        roomChangeVo.setPageSize(Utils.getNumPerPage());
        roomChangeVo.setPageNum(currentPage);

        //获取登录人房源项目权限
        Long userId = getAdminSession(request).getFid();
        if (null == userId) {
            view.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            view.addObject(MESSAGE_KEY, "未获取用户信息,请先登陆");
            return view;
        }
        Map<String, String> projectMap = getUserEnableProject(userId);
        List<String> list = convertProejctCode(projectMap);
        if (null == list || list.isEmpty()) {
            view.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            view.addObject(MESSAGE_KEY, "用户未分配房源项目权限");
            return view;
        }

        Map<String, Object> map = roomChangeService.queryPage(roomChangeVo, list);
        view.addObject("roomList", map.get("datas"));
        view.addObject("numPerPage", Utils.getNumPerPage());
        view.addObject("currentPage", currentPage);
        view.addObject("rel", "roomChange");
        view.addObject("totalCount", map.get("total"));

        view.addObject("chooseStatusMap", ChooseStatusEnum.getDescMap());
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
                modelAndView.addObject(STATUS_CODE_KEY, SUCCESS_CODE_NUM);
                modelAndView.addObject(MESSAGE_KEY, "删除成功");
            } else {
                modelAndView.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
                modelAndView.addObject(MESSAGE_KEY, "删除失败");
            }

            return modelAndView;
        }
        modelAndView.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
        modelAndView.addObject(MESSAGE_KEY, "请选择需要删除的记录");
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
        modelAndView.addObject(STATUS_CODE_KEY, SUCCESS_CODE_NUM);
        modelAndView.addObject(MESSAGE_KEY, "下载成功");
        modelAndView.addObject(CALLBACK_TYPE_KEY, "closeCurrent");
        return modelAndView;
    }

    /**
     * 修改单个房源信息
     *
     * @param roomChange
     * @return
     */
    @RequestMapping("/updateRoomChangeById")
    @SysLog(code = ModuleConstont.ROOM_CHANGE_OPERATION, method = "修改房源信息")
    public ModelAndView updateRoomChangeById(RoomChange roomChange) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //数据不为空
        if (StringUtils.isNullOrEmpty(roomChange) || StringUtils.isNullOrEmpty(roomChange.getId())) {
            modelAndView.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY, "修改失败,入参为空");
            return modelAndView;
        }
        //获取数据
        RoomChange roomChangeById = roomChangeService.getRoomChangeById(roomChange.getId());
        if (StringUtils.isNullOrEmpty(roomChangeById)) {
            modelAndView.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY, "修改失败,数据为空");
            return modelAndView;
        }
        Boolean status = null;
        try {
            //修改
            status = roomChangeService.updateRoomChangeById(roomChange);
        } catch (Exception e) {
            modelAndView.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY, e.getMessage());
            return modelAndView;
        }

        if (status) {
            modelAndView.addObject(STATUS_CODE_KEY, SUCCESS_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY, "修改成功");
            modelAndView.addObject(CALLBACK_TYPE_KEY, "closeCurrent");
        } else {
            modelAndView.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY, "修改失败");
        }
        return modelAndView;
    }

    @RequestMapping("/updateRemark")
    @SysLog(code = ModuleConstont.ROOM_CHANGE_OPERATION, method = "修改房源信息")
    public ModelAndView updateRemark(RoomChange roomChange) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //数据不为空
        if (StringUtils.isNullOrEmpty(roomChange) || StringUtils.isNullOrEmpty(roomChange.getId())) {
            modelAndView.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY, "修改失败,入参为空");
            return modelAndView;
        }

        Boolean status = null;
        try {
            status = roomChangeService.updateRemark(roomChange.getRemark(), roomChange.getId());
        } catch (Exception e) {
            modelAndView.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY, e.getMessage());
            return modelAndView;
        }

        if (status) {
            modelAndView.addObject(STATUS_CODE_KEY, SUCCESS_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY, "编辑成功");
            modelAndView.addObject(CALLBACK_TYPE_KEY, "closeCurrent");
        } else {
            modelAndView.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY, "编辑失败");
        }
        return modelAndView;
    }

    /**
     * 点房
     *
     * @param roomChangeVo
     * @return
     */
    @RequestMapping("/addChooseRoom")
    @SysLog(code = ModuleConstont.ROOM_CHANGE_OPERATION, method = "点选房源")
    public ModelAndView addChooseRoom(RoomChangeVo roomChangeVo) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //数据不为空
        if (StringUtils.isNullOrEmpty(roomChangeVo) || StringUtils.isNullOrEmpty(roomChangeVo.getId())) {
            modelAndView.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY, "点房失败,入参为空");
            return modelAndView;
        }
        if (StringUtils.isNullOrEmpty(roomChangeVo.getStatus()) && roomChangeVo.getStatus() == 0) {
            modelAndView.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY, "点房失败,状态错误");
            return modelAndView;
        }
        if (StringUtils.isNullOrEmpty(roomChangeVo.getChooseDate())) {
            modelAndView.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY, "点房时间为空");
            return modelAndView;
        }
        if (StringUtils.isNullOrEmpty(roomChangeVo.getCommissionCompany())) {
            modelAndView.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY, "代办公司为空");
            return modelAndView;
        }
        if (StringUtils.isNullOrEmpty(roomChangeVo.getChoosePeople())) {
            modelAndView.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY, "点房人为空");
            return modelAndView;
        }
        ResultVO resultVo = roomChangeService.addChooseRoom(roomChangeVo);
        if (resultVo.getSuccess()) {
            modelAndView.addObject(CALLBACK_TYPE_KEY, "closeCurrent");
            modelAndView.addObject(STATUS_CODE_KEY, SUCCESS_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY, "点房成功");
        } else {
            modelAndView.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY, resultVo.getMessage());
        }
        return modelAndView;
    }

    /**
     * 点房修改
     *
     * @param roomChangeVo
     * @return
     */
    @RequestMapping("/updateChooseRoom")
    @SysLog(code = ModuleConstont.ROOM_CHANGE_OPERATION, method = "点选房源修改")
    public ModelAndView updateChooseRoom(RoomChangeVo roomChangeVo) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //数据不为空
        if (StringUtils.isNullOrEmpty(roomChangeVo) || StringUtils.isNullOrEmpty(roomChangeVo.getId())) {
            modelAndView.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY, "点房失败,入参为空");
            return modelAndView;
        }
        ResultVO resultVo = roomChangeService.updateChooseRoom(roomChangeVo);
        if (resultVo.getSuccess()) {
            modelAndView.addObject(CALLBACK_TYPE_KEY, "closeCurrent");
            modelAndView.addObject(STATUS_CODE_KEY, SUCCESS_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY, "修改点房信息成功");
        } else {
            modelAndView.addObject(STATUS_CODE_KEY, resultVo.getCode());
            modelAndView.addObject(MESSAGE_KEY, resultVo.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping("/toChooseRoomPage")
    public ModelAndView toChooseRoomPage(Long id, String url, String postUrl) {
        ModelAndView view = new ModelAndView();
        view.setViewName(url);
        RoomChange roomChange = roomChangeService.getRoomChangeById(id);
        if (roomChange != null) {
            view.addObject("roomChange", roomChange);
            if (postUrl.equals("add")) {
                view.addObject("postUrl", "addChooseRoom");
            } else {
                view.addObject("postUrl", "updateChooseRoom");
            }
        }
        view.addObject("chooseStatusMap", ChooseStatusEnum.getDescMap());
        return view;
    }

    @RequestMapping("/exportExacle")
    public void exportExacle(HttpServletResponse response, HttpServletRequest request) throws Exception {
        //获取登录人房源项目权限
        Long userId = getAdminSession(request).getFid();
        if (null == userId) {
            throw new Exception("未登录,获取不到用户信息");
        }
        Map<String, String> projectMap = getUserEnableProject(userId);
        List<String> list = convertProejctCode(projectMap);
        if (null == list || list.size() < 1) {
            throw new Exception("用户未分配房源项目权限");
        }
        List<RoomChangeExport> roomChanges = roomChangeService.selectAll(list);
        ExcelUtil.writeExcel(response, roomChanges,
                "表格导出-" + DateHelper.getCurrentDateYearMonthDayHourMinuteSecond() + ".xlsx",
                "sheet1", new RoomChangeExport());
    }

    @RequestMapping("initAdd")
    public ModelAndView initAdd(String url, HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        view.setViewName(url);
        Long userId = getAdminSession(request).getFid();
        Map<String, String> projectMap = getUserEnableProject(userId);
        view.addObject("projectMap", projectMap);
        return view;
    }

    /**
     * 批量修改房源项目页面
     */
    @RequestMapping("/batchUpdateItemPage")
    public ModelAndView batchUpdateItemPage(String ids, String url, HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        view.setViewName(url);
        int result = 0;
        if (StringUtils.isNullOrEmpty(ids)) {
            view.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            view.addObject(MESSAGE_KEY, "请选择需要修改的记录");
        } else {
            Long userId = getAdminSession(request).getFid();
            Map<String, String> projectMap = getUserEnableProject(userId);
            view.addObject("projectMap", projectMap);
            view.addObject("ids", ids);
        }
        return view;
    }

    /**
     * 批量修改房源项目
     */
    @RequestMapping("/batchUpdateItem")
    @SysLog(code = ModuleConstont.ROOM_CHANGE_OPERATION, method = "调拨房源")
    public ModelAndView batchUpdateItem(String ids, String itemCode) {
        ModelAndView view = new ModelAndView();
        view.setViewName("ssadmin/comm/ajaxDone");
        int result = 0;
        if (!StringUtils.isNullOrEmpty(ids)) {
            String[] idArr = ids.split(",");
            for (String idStr : idArr) {
                Long id = Long.valueOf(idStr);
                result = roomChangeService.updateRoomChangeItemById(id, itemCode);
                if (result == 0) {
                    break;
                }
            }
            if (result > 0) {
                view.addObject(STATUS_CODE_KEY, SUCCESS_CODE_NUM);
                view.addObject(MESSAGE_KEY, "修改成功");
                view.addObject(CALLBACK_TYPE_KEY, "closeCurrent");
            } else {
                view.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
                view.addObject(MESSAGE_KEY, "修改失败");
            }

            return view;
        }
        view.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
        view.addObject(MESSAGE_KEY, "请选择需要修改的记录");
        return view;
    }

    /**
     * 房源交房通知书预览
     *
     * @param id 房源ID
     * @return
     */
    @RequestMapping("/to-notice-preview")
    public ModelAndView toNoticePreview(Long id) {
        ModelAndView view = new ModelAndView();
        view.setViewName("ssadmin/detailNotice");
        view.addObject("id", id);

        //根据房源ID查询数据
        PaymentNoticeVO vo = stylusPrintService.getDetailNotice(id);
        if (StringUtils.isNullOrEmpty(vo)) {
            view.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            view.addObject(MESSAGE_KEY, "未签约或数据异常");
        } else {
            view.addObject("vo", vo);
        }
        return view;
    }
}
