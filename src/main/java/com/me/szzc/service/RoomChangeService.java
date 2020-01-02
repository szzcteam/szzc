package com.me.szzc.service;

import com.me.szzc.constant.Constant;
import com.me.szzc.dao.RoomChangeMapper;
import com.me.szzc.enums.ChooseStatusEnum;
import com.me.szzc.pojo.RoomChangeExport;
import com.me.szzc.pojo.dto.ChooseHouseDTO;
import com.me.szzc.pojo.entity.RoomChange;
import com.me.szzc.pojo.vo.ResultVO;
import com.me.szzc.pojo.vo.RoomChangeVo;
import com.me.szzc.utils.DateHelper;
import com.me.szzc.utils.StringUtils;
import com.me.szzc.utils.excle.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

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
}
