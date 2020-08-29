package com.me.szzc.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.me.szzc.constant.Constant;
import com.me.szzc.dao.RoomChangeMapper;
import com.me.szzc.enums.ChooseStatusEnum;
import com.me.szzc.enums.GovernmentEnum;
import com.me.szzc.pojo.RoomChangeExport;
import com.me.szzc.pojo.dto.ChooseHouseDTO;
import com.me.szzc.pojo.dto.RoomChangeNumDTO;
import com.me.szzc.pojo.entity.RoomChange;
import com.me.szzc.pojo.vo.ResultVO;
import com.me.szzc.pojo.vo.RoomChangeVo;
import com.me.szzc.utils.DateHelper;
import com.me.szzc.utils.StringHelper;
import com.me.szzc.utils.StringUtils;
import com.me.szzc.utils.excle.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bbfang on 2019/7/27.
 */
@Service
public class RoomChangeService {
    @Autowired
    private RoomChangeMapper roomChangeMapper;

    @Transactional(rollbackFor = Exception.class)
    public ResultVO importExcle(MultipartFile file, String itemCode) {
        List<Object> list = null;
        try {
            list = ExcelUtil.readExcel(file, new RoomChange(), 1);
        } catch (Exception e) {
            return ResultVO.error(Constant.ERROR_CODE, "读取Excle文件异常");
        }
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                continue;
            }
            RoomChange roomChange = (RoomChange) list.get(i);
            roomChange.setItemCode(itemCode);
            Integer integer = roomChangeMapper.selectRoomChangeByParam(roomChange);
            if (integer > 0) {
                throw new RuntimeException("第" + i + "条数据已存在");
            }
            int x = roomChangeMapper.insertRoomChange(roomChange);
            if (x < 1) {
                throw new RuntimeException("第" + i + "条数据存储异常");
            }
        }
        return ResultVO.success("导入成功");
    }

    public Integer deleteRoomChange(Long id) {
        return roomChangeMapper.deleteRoomChange(id);
    }

    public RoomChange getRoomChangeById(Long id) {
        RoomChange roomChange = roomChangeMapper.getRoomChangeById(id);
        if (StringUtils.isNullOrEmpty(roomChange)) {
            String choosePeople = roomChange.getChoosePeople();
            if (!StringUtils.isEmpty(choosePeople)) {
                roomChange.setChoosePeople(choosePeople.replace(",", " "));
            }
        }
        return roomChange;
    }

    public boolean chooseRoom(ChooseHouseDTO chooseHouseDTO) {
        String seat = chooseHouseDTO.getSeat() + "-";
        String unit = chooseHouseDTO.getUnit() + "-";
        String floors = chooseHouseDTO.getFloors() + "";
        String houseNumber = String.format("%02d", chooseHouseDTO.getHouseNumber());
        String number = "G" + seat + unit + floors + houseNumber;
        Integer integer = roomChangeMapper.updateChooseRoom(chooseHouseDTO.getHouseOwner(),
                chooseHouseDTO.getNewHouseAddress(), number);
        return integer > 0 ? true : false;
    }

    public boolean updateRoomChangeById(RoomChange roomChange) {
        //获取房号全
        String number = roomChange.getNumber();
        if (!StringUtils.isNullOrEmpty(number)) {
            //判断是否重复
            List<RoomChange> roomChanges = roomChangeMapper.selectRoomChange(roomChange);
            if (StringUtils.isNullOrEmpty(roomChanges) && roomChanges.size() > 0) {
                throw new RuntimeException("片区项目(" + roomChange.getName() + "),房号(" + number + ")已存在");
            }
        }
        return roomChangeMapper.updateRoomChange(roomChange) > 0 ? true : false;
    }

    public boolean updateRemark(String remark, Long id) {
        RoomChange roomChangeById = roomChangeMapper.getRoomChangeById(id);
        if (roomChangeById == null) {
            return false;
        }

        //重置备注值
        if (StringUtils.isNullOrEmpty(remark)) {
            roomChangeById.setRemark("");
        } else {
            roomChangeById.setRemark(remark);
        }

        return roomChangeMapper.updateRoomChange(roomChangeById) > 0 ? true : false;
    }

    /**
     * 房源信息条件分页查询
     *
     * @return
     */
    public Map<String, Object> queryPage(RoomChangeVo roomChangeVo, List<String> list) {
        roomChangeVo.setItemCodeList(list);
        Integer count = roomChangeMapper.getCount(roomChangeVo);
        Integer start = (roomChangeVo.getPageNum() - 1) * roomChangeVo.getPageSize();
        roomChangeVo.setStart(start);
        List<RoomChange> roomChanges = roomChangeMapper.queryPage(roomChangeVo);
        for (RoomChange roomChange : roomChanges) {
            String choosePeopleListStr = roomChange.getChoosePeople();
            if (!StringUtils.isNullOrEmpty(choosePeopleListStr)) {
                roomChange.setChoosePeople(choosePeopleListStr.replace(",", " "));
            }
        }
        Map<String, Object> map = new HashMap();
        map.put("total", count);
        map.put("datas", roomChanges);
        return map;
    }

    public synchronized ResultVO addChooseRoom(RoomChangeVo roomChangeVo) {
        RoomChange roomChange = roomChangeMapper.getRoomChangeById(roomChangeVo.getId());
        if (StringUtils.isNullOrEmpty(roomChange)) {
            return ResultVO.error(Constant.ERROR_CODE, "无该房源信息");
        }
        if (roomChange.getStatus() != 0) {
            return ResultVO.error(Constant.ERROR_CODE, "该房源已签");
        }
        roomChangeMapper.addChooseRoom(roomChangeVo);
        return ResultVO.success("点房成功");
    }

    public synchronized ResultVO updateChooseRoom(RoomChangeVo roomChangeVo) {
        RoomChange roomChange = roomChangeMapper.getRoomChangeById(roomChangeVo.getId());
        if (StringUtils.isNullOrEmpty(roomChange)) {
            return ResultVO.error(Constant.ERROR_CODE, "无该房源信息");
        }
        if (roomChange.getStatus() == 0) {
            return ResultVO.error(Constant.ERROR_CODE, "该房源未签,不允许修改");
        }
        if (!StringUtils.isNullOrEmpty(roomChangeVo.getStatus()) && roomChangeVo.getStatus() == 0) {
            roomChangeMapper.updateChooseRoomBy0(roomChangeVo);
        } else {
            roomChangeMapper.updateChooseRoomNot0(roomChangeVo);
        }
        return ResultVO.success("修改点房成功");
    }

    public List<RoomChangeExport> selectAll(List<String> list) {
        List<RoomChangeExport> roomChangeExports = roomChangeMapper.selectAll(list);
        for (RoomChangeExport roomChangeExport : roomChangeExports) {
            roomChangeExport.setStatusName(ChooseStatusEnum.getText(roomChangeExport.getStatus()));
            if (roomChangeExport.getChooseDate() != null) {
                roomChangeExport.setChooseDateString(DateHelper.date2String(roomChangeExport.getChooseDate(), DateHelper.DateFormatType.YearMonthDay_HourMinuteSecond));
            }
        }
        return roomChangeExports;
    }

    /**
     * 房源数量统计
     **/
    public List<RoomChangeNumDTO> countRoomNum() {
        List<RoomChangeNumDTO> list = roomChangeMapper.countNum();
        if (list == null || list.isEmpty()) {
            return list;
        }
        for (RoomChangeNumDTO dto : list) {
            dto.setProjectName(GovernmentEnum.getNameByCode(dto.getProjectCode()));
        }
        return list;
    }

    /**
     * 统计各项目的房源套数信息
     **/
    public JSONArray countAreaNumByProjectCode(String projectCode) {
        List<RoomChangeNumDTO> list = roomChangeMapper.countAreaNumByProjectCode(projectCode);
        //先以房源名称做拆分
        Map<String, List<RoomChangeNumDTO>> houseNameMap = new HashMap<>();
        list.forEach(dto -> {
            String projectName = dto.getProjectName();
            List<RoomChangeNumDTO> roomList = houseNameMap.get(projectName);
            if (roomList == null || roomList.isEmpty()) {
                roomList = new ArrayList<>();
            }

            //面积存在中文‘暂无’，临时将中文处理成1
            if (StringUtils.isBlank(dto.getArea()) || (!StringHelper.isDouble(dto.getArea()) && !StringHelper.isInteger(dto.getArea()))) {
                dto.setArea("1");
            }

            roomList.add(dto);
            houseNameMap.put(projectName, roomList);
        });

        /**
         * 循环每个房源名称，将面积归类
         * '保利新武昌':{80:55,90:120,100:200,110:77}//,保利新武昌房源分配明细，面积（80表示面积80多，不到90），套数
         * '明伦街':{n:350}//n表示没有面积的房屋
         */
        JSONArray jsonArray = new JSONArray();
        for (Map.Entry<String, List<RoomChangeNumDTO>> entry : houseNameMap.entrySet()) {
            String name = entry.getKey();
            Map<Integer, Integer> areaMap = new HashMap();
            List<RoomChangeNumDTO> dataList = entry.getValue();
            dataList.forEach(dto -> {
                //字符转整数
                int area = new BigDecimal(dto.getArea()).intValue();
                //归类 的值
                area = area / 10 * 10;
                //同类型的，套数相加
                Integer num = areaMap.get(area);
                if (num == null) {
                    num = 0;
                }
                num = num + dto.getNum();
                //重新覆盖
                areaMap.put(area, num);
            });

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(name, areaMap);
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }

    public Integer updateRoomChangeItemById(Long id, String itemCode) {
        String assignedProject = GovernmentEnum.getNameByCode(itemCode);
        return roomChangeMapper.updateRoomChangeItemById(id, itemCode, assignedProject);
    }
}
