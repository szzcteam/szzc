package com.me.szzc.service;

import com.me.szzc.enums.ChooseStatusEnum;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by bbfang on 2019/8/23.
 */
@Service
public class DictionaryService {

    public List<Map<String, Object>> getChooseRoomStatusEnum() {
        return ChooseStatusEnum.queryAll();
    }

}
