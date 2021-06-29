package com.me.szzc.service;

import com.me.szzc.dao.RoomChangeMapper;
import com.me.szzc.pojo.entity.RoomChange;
import com.me.szzc.pojo.vo.RoomChangeVo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 2021-06-14 21:55
 * <p>
 * 点房监听器
 *
 * @author yaoyy
 */
@Component
public class ChooseRoomListener implements ApplicationListener<ChooseRoomEvent> {

    private final RestTemplate restTemplate;
    private final RoomChangeMapper roomChangeMapper;

    @Autowired
    public ChooseRoomListener(RestTemplate restTemplate, RoomChangeMapper roomChangeMapper) {
        this.restTemplate = restTemplate;
        this.roomChangeMapper = roomChangeMapper;
    }

    /**
     * 调用红日的接口，把点房信息传过去
     */
    @Override
    public void onApplicationEvent(ChooseRoomEvent event) {
        RoomChangeVo vo = event.getVo();
        RoomChange roomChange = roomChangeMapper.getRoomChangeById(vo.getId());
        Dto dto = new Dto().setProjectName(roomChange.getAssignedProject())
                .setPropertyName(roomChange.getName())
                .setNowRoomNo(roomChange.getNumber())
                .setHouseholdName(roomChange.getChoosePeople())
                .setState(stateMapping.get(roomChange.getStatus()))
                .setIdCard(roomChange.getIdcard())
                .setAuditingCompany(roomChange.getAuditFirm());
        ResponseEntity<Integer> resp = restTemplate.postForEntity("http://127.0.0.1:8080/rsh/api/external/choose_listing", dto, Integer.class);
        Integer code = resp.getBody();
        if (code == null || code != 1) {
            throw new RuntimeException("点房时的远程调用失败，请手动更新远程系统或重新点房！");
        }
    }

    Map<Integer, Integer> stateMapping = new HashMap<>(8);
    {
        stateMapping.put(0, 2);
        stateMapping.put(1, 5);
        stateMapping.put(2, 0);
        stateMapping.put(4, 5);
    }

    @Data
    @Accessors(chain = true)
    static class Dto {
        // 项目名
        private String projectName;
        // 楼盘名
        private String propertyName;
        // 房号
        private String nowRoomNo;
        // 房源状态
        private Integer state;
        // 点房人姓名
        private String householdName;
        // 点房人身份证号
        private String idCard;
        // 审计公司
        private String auditingCompany;
    }
}
