package com.me.szzc.utils;

import com.alibaba.fastjson.JSONObject;
import com.me.szzc.enums.GovernmentEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 微信小程序权限安全映射
 *
 * @author luwei
 * @date 2020/8/13
 */
@Slf4j
public class WxSecurityMappingUtils {

    public static void main(String[] args) {
        String scopeName = "粮道街";
        Set<String> list = WxSecurityMappingUtils.getProjectByScope(scopeName);
        System.out.println(JSONObject.toJSONString(list));
    }


    //根据名称，获取管辖权限下的所有项目列表
    public static Set<String> getProjectByScope(String scopeName) {
        if (StringUtils.isBlank(scopeName)) {
            log.error("查询项目列表时，参数为空");
            return Collections.emptySet();
        }

        log.info("查询项目列表, 参数:" + scopeName);
        Set<String> set = new HashSet<>();
        //1、如果是片区，则直接返回
        AreaMark areaMark = AreaMark.allAreaMap.get(scopeName);
        if (areaMark != null) {
            log.info("仅有片区权限,name:{}", areaMark.name);
            set.add(areaMark.name);
            return set;
        }

        //2、其它部门或街道
        ProjectMark projectMark = ProjectMark.allNameMap.get(scopeName);
        if (projectMark == null) {
            log.info("参数错误，非片区非平台" + scopeName);
            return set;
        }

        if(projectMark.getReadProject() != null && projectMark.getReadProject().length > 0){
            set.addAll(Arrays.asList(projectMark.getReadProject()));
        }

        getChild(projectMark.getCode(), set);
        return set;
    }


    /**根据父元素获取所有嵌套的子元素**/
    private static void getChild(String parentCode, Set<String> set) {
        for (ProjectMark gov : ProjectMark.allList) {
            if (gov.getParentCode() == null) {
                continue;
            }
            if (!gov.getParentCode().equals(parentCode)) {
                continue;
            }
            if (gov.getReadProject() != null && gov.getReadProject().length > 0) {
                set.addAll(Arrays.asList(gov.getReadProject()));
            }
            getChild(gov.getCode(), set);
        }
    }


    //项目权限标识
    public enum ProjectMark {

        ROOT("root", "admin", null),

        WCQ("wcq", "武昌区", ProjectMark.ROOT.getCode()),


        BJSWQ("A001", "滨江", ProjectMark.WCQ.getCode(), new String[]{AreaMark.MLJ_B.name, AreaMark.MLJ_C.name}),
        GCC("B001", "古城", ProjectMark.WCQ.getCode(), new String[]{AreaMark.XCH.name, AreaMark.ZYC_E.name, AreaMark.ZYC_W.name,
                AreaMark.QNGZA.name, AreaMark.QNGZB.name, AreaMark.QNGZC.name}),
        HZJRC("C001", "金融城", ProjectMark.WCQ.getCode(), new String[]{AreaMark.DZRGS.name}),
        WCJSJ("D001", "建设局", ProjectMark.WCQ.getCode(), new String[]{AreaMark.WCEFC.name}),

        WC_STREET("WCQ_001", "街道二次分级", ProjectMark.WCQ.getCode()),

        ZY_STREET("WCQ_Z001", "紫阳街", ProjectMark.WC_STREET.getCode(), new String[]{AreaMark.MLJ_B.name, AreaMark.MLJ_C.name}),
        YWLLOW_TOWER_STREE("WCQ_Z002", "黄鹤楼街", ProjectMark.WC_STREET.getCode(), new String[]{AreaMark.ZYC_E.name, AreaMark.ZYC_W.name}),
        ZHL_STREET("WCQ_Z003", "中华路街", ProjectMark.WC_STREET.getCode(), new String[]{AreaMark.XCH.name, AreaMark.QNGZA.name, AreaMark.QNGZB.name, AreaMark.QNGZC.name}),
        SGH_STREET("WCQ_Z004", "·水果湖街", ProjectMark.WC_STREET.getCode(), new String[]{AreaMark.DZRGS.name}),
        BSZ_STREET("WCQ_Z005", "·白沙洲街", ProjectMark.WC_STREET.getCode(), new String[]{AreaMark.WCEFC.name}),
        LDJ_STREET("WCQ_Z006", "粮道街", ProjectMark.WC_STREET.getCode(), new String[]{AreaMark.QNGZA.name, AreaMark.QNGZC.name}),
        ;

        //唯一code
        private String code;

        //权限名称
        private String name;

        //上级code
        private String parentCode;

        //允许读取的项目信息
        private String[] readProject;

        private static final Map<String, ProjectMark> allNameMap = new HashMap<>();
        private static final List<ProjectMark> allList = new ArrayList<>();

        static {
            for (ProjectMark mark : ProjectMark.values()) {
                allNameMap.put(mark.getName(), mark);
                allList.add(mark);
            }
        }

        ProjectMark(String code, String name, String parentCode) {
            this.code = code;
            this.name = name;
            this.parentCode = parentCode;
        }

        ProjectMark(String code, String name, String parentCode, String[] readProject) {
            this.code = code;
            this.name = name;
            this.parentCode = parentCode;
            this.readProject = readProject;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public String getParentCode() {
            return parentCode;
        }

        public String[] getReadProject() {
            return readProject;
        }
    }


    //项目、片区
    public enum AreaMark {

        MLJ_B(1L, GovernmentEnum.MLJ.getCode(), "明伦街B片"),
        MLJ_C(3L, GovernmentEnum.MLJ.getCode(), "明伦街C片"),
        ZYC_E(5L, GovernmentEnum.ZYC.getCode(), "紫阳村西片"),
        ZYC_W(4L, GovernmentEnum.ZYC.getCode(), "紫阳村东片"),

        XCH(7L, GovernmentEnum.XCH.getCode(), "西城壕项目"),
        DZRGS(6L, GovernmentEnum.DZRGS.getCode(), "电车二公司项目"),
        WCEFC(8L, GovernmentEnum.WCEFC.getCode(), "武船二分厂项目"),


        JYQC(10L, GovernmentEnum.JYQC.getCode(), "积玉桥C片"),

        QNGZA(12L, GovernmentEnum.QNGZ.getCode(), "千年古轴A片"),
        QNGZB(13L, GovernmentEnum.QNGZB.getCode(), "千年古轴B片"),
        QNGZC(14L, GovernmentEnum.QNGZC.getCode(), "千年古轴C片"),

        ;
        public static final Map<String, AreaMark> allAreaMap = new HashMap<>();

        static {
            for (AreaMark mark : AreaMark.values()) {
                allAreaMap.put(mark.name, mark);
            }
        }


        //映射到程序里面的 片区 ID
        private Long mpCode;

        //映射到的项目code
        private String mpProjectCode;

        //返回给小程序的名字
        private String name;

        AreaMark(Long mpCode, String mpProjectCode, String name) {
            this.mpCode = mpCode;
            this.mpProjectCode = mpProjectCode;
            this.name = name;
        }

        public Long getMpCode() {
            return mpCode;
        }

        public String getMpProjectCode() {
            return mpProjectCode;
        }
    }

}
