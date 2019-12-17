package com.me.szzc.service;

import com.me.szzc.dao.RoomChangeMapper;
import com.me.szzc.enums.ChooseStatusEnum;
import com.me.szzc.pojo.RoomChangeExport;
import com.me.szzc.pojo.dto.ChooseHouseDTO;
import com.me.szzc.pojo.entity.RoomChange;
import com.me.szzc.pojo.vo.ResultVo;
import com.me.szzc.pojo.vo.RoomChangeVo;
import com.me.szzc.utils.DateHelper;
import com.me.szzc.utils.StringUtils;
import com.me.szzc.utils.excle.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bbfang on 2019/7/27.
 */
@Service
public class RoomChangeService {
    @Autowired
    private RoomChangeMapper roomChangeMapper;

    @Transactional
    public ResultVo importExcle(MultipartFile file, String itemCode) {
        List<Object> list = null;
        try {
            list = ExcelUtil.readExcel(file, new RoomChange(), 1);
        } catch (Exception e) {
            return ResultVo.error(1001, "读取Excle文件异常");
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
        return ResultVo.success("导入成功");
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

    /**
     * 房源信息条件分页查询
     *
     * @param numPerPage      每页条数(后台默认40)
     * @param currentPage     当前页数
     * @param name            房源項目
     * @param number          房号
     * @param choosePeople    点房人
     * @param assignedProject 分配征收项目(片区)
     * @param housingPlatform 提供房源平台
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

    public synchronized ResultVo addChooseRoom(RoomChangeVo roomChangeVo) {
        RoomChange roomChange = roomChangeMapper.getRoomChangeById(roomChangeVo.getId());
        if (StringUtils.isNullOrEmpty(roomChange)) {
            return ResultVo.error(300, "无该房源信息");
        }
        if (roomChange.getStatus() != 0) {
            return ResultVo.error(300, "该房源已签");
        }
        roomChangeMapper.addChooseRoom(roomChangeVo);
        return ResultVo.success("点房成功");
    }

    public synchronized ResultVo updateChooseRoom(RoomChangeVo roomChangeVo) {
        RoomChange roomChange = roomChangeMapper.getRoomChangeById(roomChangeVo.getId());
        if (StringUtils.isNullOrEmpty(roomChange)) {
            return ResultVo.error(300, "无该房源信息");
        }
        if (roomChange.getStatus() == 0) {
            return ResultVo.error(300, "该房源未签,不允许修改");
        }
        if (!StringUtils.isNullOrEmpty(roomChangeVo.getStatus()) && roomChangeVo.getStatus() == 0) {
            roomChangeMapper.updateChooseRoomBy0(roomChangeVo);
        } else {
            roomChangeMapper.updateChooseRoomNot0(roomChangeVo);
        }
        return ResultVo.success("修改点房成功");
    }

    public List<RoomChangeExport> selectAll() {
        List<RoomChangeExport> roomChangeExports = roomChangeMapper.selectAll();
        for (RoomChangeExport roomChangeExport : roomChangeExports) {
            roomChangeExport.setStatusName(ChooseStatusEnum.getText(roomChangeExport.getStatus()));
            if (roomChangeExport.getChooseDate() != null) {
                roomChangeExport.setChooseDateString(DateHelper.date2String(roomChangeExport.getChooseDate(), DateHelper.DateFormatType.YearMonthDay_HourMinuteSecond));
            }
        }
        return roomChangeExports;
    }
}
