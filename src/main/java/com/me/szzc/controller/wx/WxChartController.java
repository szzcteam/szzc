package com.me.szzc.controller.wx;

import com.me.szzc.enums.GovernmentEnum;
import com.me.szzc.pojo.ro.ResultRO;
import com.me.szzc.utils.PasswordHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 微信图表响应
 * @author luwei
 * @date 2020/6/1
 */
@Slf4j
@Controller
@RequestMapping(value = "/wx")
public class WxChartController {

    @RequestMapping(value={"/{projectScope}/get-project"}, method= RequestMethod.POST)
    @ResponseBody
    public ResultRO<List<String>> getProject(@PathVariable("projectScope") String projectScope) {
        if (StringUtils.isBlank(projectScope)) {
            return new ResultRO<>(false, "参数不能为空");
        }

        String mgtName = PasswordHelper.decode(projectScope);
        if(StringUtils.isBlank(mgtName)){
            log.error("参数错误，解密后返回空, projectScope:{}", projectScope);
            return new ResultRO<>(false, "参数错误，请检查");
        }
        List<String> list = GovernmentEnum.getProjectByScope(mgtName);
        ResultRO<List<String>> resultRO = new ResultRO(true);
        resultRO.setData(list);
        return resultRO;
    }

}
