package com.me.szzc.service;

import com.me.szzc.dao.RoomChangeMapper;
import com.me.szzc.pojo.dto.ChooseHouseDTO;
import com.me.szzc.pojo.entity.RoomChange;
import com.me.szzc.pojo.vo.ResultVo;
import com.me.szzc.utils.StringUtils;
import com.me.szzc.utils.excle.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
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

    @Transactional
    public ResultVo importExcle(MultipartFile file) {
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

            String number = roomChange.getNumber();
            List<String> strList = Arrays.asList(number.split("-"));
            if (strList.size() != 3) {
                throw new RuntimeException("第" + i + "条数据房号(" + number + ")格式异常");
            }
            for (int x = 0; x < 3; x++) {
                if (x == 0) {
                    roomChange.setRidgepole(strList.get(x));
                } else if (x == 1) {
                    roomChange.setUnit(strList.get(x));
                } else if (x == 2) {
                    if (strList.get(x).length() == 3 || strList.get(x).length() == 4) {
                        try {
                            String str = String.format("%04d", Integer.parseInt(strList.get(x)));
                            roomChange.setFloor(str.substring(0, 2));
                            roomChange.setMark(str.substring(2));
                        } catch (Exception e) {
                            throw new RuntimeException("第" + i + "条数据房号(" + number + ")格式异常");
                        }
                    } else {
                        throw new RuntimeException("第" + i + "条数据房号(" + number + ")格式异常");
                    }
                }
            }
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

    public Map<String, Object> queryPage(Integer pageSize, Integer pageNum, String name, String district) {
        Integer count = roomChangeMapper.getCount(name, district);
        Integer start = (pageNum - 1) * pageSize;
        List<RoomChange> roomChanges = roomChangeMapper.queryPage(start, pageSize, name, district);
        for (RoomChange roomChange : roomChanges) {
            String choosePeople = roomChange.getChoosePeople();
            if (!StringUtils.isNullOrEmpty(choosePeople)) {
                roomChange.setChoosePeople(choosePeople.replace(",", " "));
            }
        }
        Map<String, Object> map = new HashMap();
        map.put("total", count);
        map.put("datas", roomChanges);
        return map;
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
                roomChange.setMark(Integer.parseInt(roomChange.getMark()) + "");
                roomChange.setFloor(Integer.parseInt(roomChange.getFloor()) + "");
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
        List<String> strList = Arrays.asList(number.split("-"));
        if (strList.size() != 3) {
            throw new RuntimeException("数据房号(" + number + ")格式异常");
        }
        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                roomChange.setRidgepole(strList.get(i));
            } else if (i == 1) {
                roomChange.setUnit(strList.get(i));
            } else if (i == 2) {
                if (strList.get(i).length() == 3 || strList.get(i).length() == 4) {
                    try {
                        String str = String.format("%04d", Integer.parseInt(strList.get(i)));
                        roomChange.setFloor(str.substring(0, 2));
                        roomChange.setMark(str.substring(2));
                    } catch (Exception e) {
                        throw new RuntimeException("数据房号(" + number + ")格式异常");
                    }
                } else {
                    throw new RuntimeException("数据房号(" + number + ")格式异常");
                }
            }
        }
        //判断是否重复
        List<RoomChange> roomChanges = roomChangeMapper.selectRoomChange(roomChange);
        if (StringUtils.isNullOrEmpty(roomChanges) && roomChanges.size() > 0) {
            throw new RuntimeException("片区项目(" + roomChange.getName() + "),房号(" + number + ")已存在");
        }
        Integer integer = roomChangeMapper.updateRoomChange(roomChange);
        return integer > 0 ? true : false;
    }
}
