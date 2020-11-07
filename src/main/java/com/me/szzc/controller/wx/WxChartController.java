package com.me.szzc.controller.wx;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.me.szzc.constant.Constant;
import com.me.szzc.pojo.vo.ResultVO;
import com.me.szzc.service.RoomChangeService;
import com.me.szzc.service.SettleAccountsService;
import com.me.szzc.utils.Utils;
import com.me.szzc.utils.WxSecurityMappingUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;
import java.util.List;
import java.util.Set;

/**
 * 微信图表响应
 * @author luwei
 * @date 2020/6/1
 */
@Slf4j
@Controller
@RequestMapping(value = "/wx")
public class WxChartController {

    @Autowired
    private SettleAccountsService settleAccountsService;

    @Autowired
    private RoomChangeService roomChangeService;

    /**获取权限范围下的项目**/
    @RequestMapping(value={"/{projectScope}/get-project"}, method= RequestMethod.POST)
    @ResponseBody
    public ResultVO<Set<String>> getProject(@PathVariable("projectScope") String projectScope) throws Exception {
        if (StringUtils.isBlank(projectScope)) {
            return new ResultVO<>(false, "参数不能为空");
        }
        log.info("权限标识,projectScope:{}", projectScope);
        String mgtName = URLDecoder.decode(projectScope, Constant.UTF8);
        Set<String> set = WxSecurityMappingUtils.getProjectByScope(mgtName);
        ResultVO<Set<String>> resultRO = new ResultVO(true);
        resultRO.setData(set);
        return resultRO;
    }

    @RequestMapping(value={"/{str}/get-summary"}, method= RequestMethod.POST)
    @ResponseBody
    /**获取协议摘要**/
    public ResultVO<JSONObject> getProtocolSummary(@PathVariable("str") String str) throws Exception {
        log.info("入参,str:{}", str);
        //将字符串进行url解码
        String pwd = URLDecoder.decode(str, Constant.UTF8);
        log.info("入参进行url解码, str:{}", pwd);
        if (pwd.length() < 52) {
            return new ResultVO<>(false, "非法请求");
        }

        //前6位=用户标识
        String user = pwd.substring(0, 6);
        //接着是13位=时间戳
        String time = pwd.substring(6, 19);
        //接着是32位的md5
        String md5Value = pwd.substring(19, 51);
        //最后是项目名称
        String projectName = pwd.substring(51);
        log.info("项目名称, projectName:{}", projectName);

        //校验参数的一致性
        String paramMd5 = Utils.getMD5_32_xx(user + time + projectName + Constant.WX_SALT);
        if (!paramMd5.equals(md5Value)) {
            return new ResultVO<>(false, "非法请求");
        }

        //该请求连接1分钟有效
        boolean expire = Utils.expireURL(time);
        if (expire) {
            return new ResultVO<>(false, "请求过期");
        }

        //获取项目名称、code
        WxSecurityMappingUtils.AreaMark areaMark = WxSecurityMappingUtils.AreaMark.allAreaMap.get(projectName);
        if(areaMark == null){
            log.error("请求参数错误，名称:" + projectName);
            return new ResultVO<>(false, "非法请求");
        }

        List<List<Object>> summaryList = settleAccountsService.summaryListByWx(areaMark.getMpProjectCode(), areaMark.getMpCode());
        //获取房源
        JSONArray jsonArray = roomChangeService.countAreaNumByProjectCode(areaMark.getMpProjectCode());

        //返回数据组装
        JSONObject jsonObject = new JSONObject();

        //项目名称
        jsonObject.put("pn", projectName);
        jsonObject.put("dd", summaryList);
        jsonObject.put("hs", jsonArray);

        ResultVO resultRO = new ResultVO(true);
        resultRO.setData(jsonObject);
        return resultRO;
    }
}
