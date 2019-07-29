package com.me.szzc.service;

import com.me.szzc.dao.RoomChangeMapper;
import com.me.szzc.pojo.entity.RoomChange;
import com.me.szzc.pojo.vo.ResultVo;
import com.me.szzc.utils.excle.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

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
                return ResultVo.error(1003, "第" + i + "条数据房号格式异常");
            }
            for (int x = 0; x < 3; x++) {
                if (x == 0) {
                    roomChange.setRidgepole(strList.get(x));
                } else if (x == 1) {
                    roomChange.setUnit(strList.get(x));
                } else if (x == 2) {
                    if (strList.get(x).length() == 3 || strList.get(x).length() == 4) {
                        String str = String.format("%04d", Integer.parseInt(strList.get(x)));
                        roomChange.setFloor(str.substring(0, 2));
                        roomChange.setMark((str.substring(2)));
                    } else {
                        return ResultVo.error(1004, "第" + i + "条数据房号格式异常");
                    }
                }
            }

            int x = roomChangeMapper.insertRoomChange(roomChange);
            if (x < 1) {
                return ResultVo.error(1002, "第" + i + "条数据存储异常");
            }
        }
        return ResultVo.success("导入成功");
    }
}
