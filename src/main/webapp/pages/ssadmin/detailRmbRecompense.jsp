<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.me.szzc.utils.CustomizedPropertyConfigurer" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
    String path = request.getContextPath();
    String accessProtocol = CustomizedPropertyConfigurer.getContextProperty("access_protocol").toString();
    String basePath = accessProtocol + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>货币补偿协议预览</title>

    <script src="${oss_url}/static/ssadmin/js/js/jquery-1.7.2.js" type="text/javascript"></script>
    <script src="${oss_url}/static/ssadmin/js/lodop_assets/layer/layer.js" type="text/javascript"></script>
    <script src="${oss_url}/static/ssadmin/js/lodop_assets/my.js" type="text/javascript"></script>
    <script src="${oss_url}/static/ssadmin/js/lodop_assets/lodop/LodopFuncs.js" type="text/javascript"></script>


    <style type="text/css">
        .rows_div {
            border-bottom:1px solid black;
            display:inline-block;
            text-align: center;
        }
    </style>



</head>

<body style="overflow-y: auto;font-size: 13px;">
<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>
    <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
</object>
<!--总的div-->
<div style="width: 98%;height:842px;margin: 0px auto;padding-top: 20px;">
    <dl>
        <div style="text-align: right;margin-right: 13%;">
            <input type="hidden" value="${rmbRecom.id}" id="recordId">
            <button type="button" id="print" style="width: 70px;height: 26px;" onclick="eventPrint()">打印</button>
        </div>
    </dl>
    <!--左侧页-->
    <div style="width:47%;height:100%;float: left;padding-right: 15px">
        <div style="text-align: left;">协议编号：<div style="width: 100px;" class="rows_div">${rmbRecom.cardNo}</div></div>
        <h2 style="text-align: center">个人房屋货币补偿征收协议</h2>
        <div>
            <table style="height: 55px;margin-top: 20px;text-align: left;">
                <tr>
                    <td style="width: 330px;">征收部门（甲方）：武汉市武昌区城区改造更新局</td>
                    <td>代办单位：<div style="width: 208px;" class="rows_div">${rmbRecom.agencyCompany}</div></td>
                </tr>
                <c:choose>
                    <c:when test="${rmbRecom.projectCode == 'B001001'}">
                        <tr>
                            <td>被征收人（乙方）：<div style="width: 175px;" class="rows_div">${rmbRecom.twoHouseOwner}</div></td>
                            <td>身份证号：<div style="width: 208px;" class="rows_div">${rmbRecom.twoIdentityNo}</div></td>
                        </tr>
                        <tr>
                            <td>承 租 人（丙方）：<div style="width: 175px;" class="rows_div">${rmbRecom.thireHouseOwner}</div></td>
                            <td>身份证号：<div style="width: 208px;" class="rows_div">${rmbRecom.thireIdentityNo}</div></td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td>被征收人、公有房屋承租人（乙方）：<div style="width: 90px;" class="rows_div">${rmbRecom.houseOwner}</div></td>
                            <td>身份证号：<div style="width: 208px;" class="rows_div">${rmbRecom.identityNo}</div></td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </table>
        </div>
        <div style="line-height: 26px;text-align: left;">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;武汉市武昌区人民政府于${rmbRecom.govYear}年${rmbRecom.govMonth}月${rmbRecom.govDay}日作出《房屋征收决定》（武昌征决字[${rmbRecom.adjuLetter}] ${rmbRecom.adjuNum}号），
            并于${rmbRecom.noticeYear}年${rmbRecom.noticeMonth}月${rmbRecom.noticeDay}日在房屋征收范围内公告，
            乙方房屋在征收范围内。依据《国有土地上房屋征收与补偿条例》等相关规定，按照房屋征收补偿方案，甲、乙双方经平等协商，
            就房屋征收补偿事宜达成协议如下：
        </div>
        <div style="font-size: 14px;text-align: left;line-height: 26px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第一条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;被征收房屋情况</b></div>
        <div style="text-align: left;line-height: 26px;">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            1、乙方被征收房屋位于<div style="width: 320px;" class="rows_div">${rmbRecom.address}</div>，
            证载建筑面积为<div style="width: 80px;" class="rows_div">${rmbRecom.certifiedArea}</div>㎡，
            房屋权属份额<div style="width: 50px;" class="rows_div">${rmbRecom.proportion}</div>%，
            证载房屋用途<div style="width: 80px;" class="rows_div">${rmbRecom.useing}</div>，
            房屋所有权证号<div style="width: 250px;" class="rows_div">${rmbRecom.houseOwnerNumber}</div>，
            国有土地使用证号<div style="width: 220px;" class="rows_div">${rmbRecom.publicOwnerNumber}</div>，
            房屋价值评估单价为<div style="width: 120px;" class="rows_div">${rmbRecom.assessPrice}</div>元/㎡；
            其中：用于经营的实际面积<div style="width: 50px;" class="rows_div">${rmbRecom.valueCompensateBusinessArea}</div> ㎡，
            住宅改为经营门面的补助系数为<div style="width: 50px;" class="rows_div">${rmbRecom.valueCompensateRate}</div> %。
            <br/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            2、未经登记的合法建筑面积<div style="width: 80px;" class="rows_div">${rmbRecom.noRegisterLegalArea}</div>㎡，
            房屋用途为<div style="width: 80px;" class="rows_div">${rmbRecom.noRegisterUseing}</div>，
            房屋价值评估单价为<div style="width: 120px;" class="rows_div">${rmbRecom.noRegisterAssessPrice}</div>元/㎡，
            补偿比例为<div style="width: 80px;" class="rows_div">${rmbRecom.noRegisterProportion}</div>%；
            其中：用于经营的面积<div style="width: 80px;" class="rows_div">${rmbRecom.noRegisterBusinessArea}</div>㎡，
            住宅改为经营门面的补助系数为<div style="width: 80px;" class="rows_div">${rmbRecom.noRegisterRate}</div>%。
            <br/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            3、历史遗留无证房屋面积<div style="width: 80px;" class="rows_div">${rmbRecom.historyLegacyArea}</div>㎡，
            实际用途为<div style="width: 80px;" class="rows_div">${rmbRecom.historyUseing}</div>，
            房屋价值评估单价为<div style="width: 120px;" class="rows_div">${rmbRecom.historyAssessPrice}</div>元/㎡，
            补偿比例为<div style="width: 80px;" class="rows_div">${rmbRecom.historyProportion}</div>%；
            其中：用于经营的面积<div style="width: 80px;" class="rows_div">${rmbRecom.historyBusinessArea}</div>㎡，
            住宅改为经营门面的补助系数为<div style="width: 80px;" class="rows_div">${rmbRecom.historyRate} </div>%。
        </div>
        <div style="font-size: 14px;text-align: left;line-height: 26px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第二条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;征收补偿款项</b></div>
        <div style="text-align: left;line-height: 26px;">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            1、房屋价值补偿：
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ⑴有证房屋补偿为<div style="width: 80px;" class="rows_div">${rmbRecom.valueCompensate}</div>元
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ⑵未经登记的合法建筑补偿为<div style="width: 80px;" class="rows_div">${rmbRecom.noRegisterLegal}</div>元
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ⑶历史遗留无证房屋补偿为<div style="width: 80px;" class="rows_div">${rmbRecom.historyLegacy}</div>元
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            2、装修补偿：经协商/评估，装修补偿单价为<div style="width: 80px;" class="rows_div">${rmbRecom.decorationCompensateUnitPrice}</div>元/㎡，
            补偿总价<div style="width: 80px;" class="rows_div">${rmbRecom.decorationCompensate}</div>元
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            3、房屋搬迁补偿（搬家费）：<div style="width: 80px;" class="rows_div">${rmbRecom.moveHouseFee}</div>元
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            4、临时安置补偿：经评估，临时安置补偿费标准为<div style="width: 80px;" class="rows_div">${rmbRecom.interimPrice}</div>元/㎡，临时安置补偿金额为<div style="width: 80px;" class="rows_div">${rmbRecom.interimFee}</div>元（即：被征收房屋面积*评估单价*月份）
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            5、保底补偿：如果被征收房屋面积（有证面积、未登记合法建筑面积与历史遗留无证房屋按规定打折后的建筑面积合并计算)小于40㎡且是唯一住房时，按40㎡给予保底补偿，即保底面积补偿金额为<div style="width: 80px;" class="rows_div">${rmbRecom.guarantee}</div>元
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            6、停产停业损失补偿：<div style="width: 80px;" class="rows_div">${rmbRecom.suspendBusinessFee}</div>元
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            7、附属设施补偿：
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ⑴水表迁移费<div style="width: 100px;" class="rows_div">${rmbRecom.moveWaterMeterFee}</div>元
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ⑵电表迁移费<div style="width: 100px;" class="rows_div">${rmbRecom.moveAmmeterFee}</div>元
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ⑶空调移机<div style="width: 100px;" class="rows_div">${rmbRecom.moveAirConditioningFee}</div>元
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ⑷热 水 器：太阳能热水器<div style="width: 100px;" class="rows_div">${rmbRecom.solarHeater}</div>元；其他热水器 <div style="width: 100px;" class="rows_div">${rmbRecom.otherHeater}</div>元
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ⑸管道燃气<div style="width: 100px;" class="rows_div">${rmbRecom.gasFee}</div>元
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ⑹构筑物：结构设计内封闭阳台<div style="width: 80px;" class="rows_div">${rmbRecom.structureBalcony}</div>元，
            外挑搭建<div style="width: 80px;" class="rows_div">${rmbRecom.structureBuild}</div>元，
            暗楼<div style="width: 80px;" class="rows_div">${rmbRecom.structureDark}</div>元，
            夹层（假层）<div style="width: 80px;" class="rows_div">${rmbRecom.structureMezzanine}</div>元，
            楼顶搭建<div style="width: 80px;" class="rows_div">${rmbRecom.structureRoof}</div>元<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ⑺其  他<div style="width: 80px;" class="rows_div">${rmbRecom.affiliatedOther}</div>元
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            小  计：<div style="width: 100px;" class="rows_div">${rmbRecom.subtotal}</div>元
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            8、征收补助：
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ⑴改变房屋用途补助为<div style="width: 100px;" class="rows_div">${rmbRecom.changeCompensate}</div>元
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
    </div>

    <!--右侧页-->
    <div style="width:47%;height:100%;float: left;padding-left: 15px">
        <div style="text-align: left;line-height: 26px;">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ⑵生活困难补助：最低生活保障补助<div style="width: 100px;" class="rows_div">${rmbRecom.basicLivingSubsidy}</div>元；
            残疾人补助 <div style="width: 100px;" class="rows_div">${rmbRecom.disabilitySubsidy}</div>元；重症病人补助
                <div style="width: 100px;" class="rows_div">${rmbRecom.diseaseSubsidy}</div>元；
                失独家庭补助<div style="width: 100px;" class="rows_div">${rmbRecom.noChild}</div>元；
                烈士家庭补助<div style="width: 100px;" class="rows_div">${rmbRecom.martyr}</div>元；
                小计：<div style="width: 100px;" class="rows_div">${rmbRecom.lifeCompensate}</div>元
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ⑶货币补偿补助为<div style="width: 100px;" class="rows_div">${rmbRecom.rmbCompensate}</div>元
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ⑷小户型住房困难补助为<div style="width: 100px;" class="rows_div">${rmbRecom.smallAreaReward}</div>元
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            9、征收奖励：按期签约搬迁奖励<div style="width: 100px;" class="rows_div">${rmbRecom.moveReward}</div>元
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            10、其他<div style="width: 100px;" class="rows_div">${rmbRecom.otherFee}</div>元
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            以上征收补偿、补助及奖励等款项总金额合计人民币<div style="width: 100px;" class="rows_div">${rmbRecom.sumRbm}</div> 元
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            大写人民币：<div style="width: 300px;" class="rows_div">${rmbRecom.upperRmb}</div>
        </div>

        <c:choose>
            <c:when test="${rmbRecom.projectCode == 'B001001' or rmbRecom.projectCode == 'B001002'}">
                <div style="font-size: 14px;text-align: left;line-height: 26px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第三条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签约、搬迁及付款</b></div>
                <div style="text-align: left;line-height: 26px;">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    1、甲、乙（丙方）双方签订本协议时，乙方（丙方）应提交被征收房屋所有权证、国有土地使用证以及其他相关资料给甲方，甲方应于本协议签订之日起，
                    经会计机构审核出具审计报告${rmbRecom.afterDayAudit}个工作日并在乙方完成被征收房屋的注销登记后，
                    将房屋征收补偿的总金额支付给乙方（丙方），支付方式将补偿款存入甲方在银行设定的乙方（丙方）名下的储蓄账户。
                    <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    2、乙方（丙方）保证向甲方所提供的所有产权书证及其他相关资料真实、合法；乙方（丙方）未隐瞒被征收房屋的产权纠纷或抵押担保等状况，如因隐瞒或提供资料不实产生的一切责任由乙方（丙方）承担。
                    <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    3、乙方（丙方）应于<div style="width: 60px;" class="rows_div">${rmbRecom.beforeDay}</div>日内搬迁腾空房屋内物品，并结清房屋水、电等费用后移交给甲方。
                </div>
                <div style="font-size: 14px;text-align: left;line-height: 26px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第四条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其它约定</b></div>
                <div style="text-align: left;line-height: 26px;">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    1、<div style="width: 500px;text-align: left;" class="rows_div">${rmbRecom.otherTermsOne}</div>
                    <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    2、<div style="width: 500px;text-align: left;" class="rows_div">${rmbRecom.otherTermsTwo}</div>
                </div>
                <div style="font-size: 14px;text-align: left;line-height: 26px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第五条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;争议的处理</b></div>
                <div style="text-align: left;line-height: 26px;">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    因履行本协议发生争议时，由甲、乙（丙方）双方协商解决；协商不成的，可依法申请行政复议、行政诉讼。
                </div>
                <div style="font-size: 14px;text-align: left;line-height: 26px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第六条</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本协议经乙方（丙方）签字按手印、甲方签字加盖公章之后生效。</div>
                <div style="font-size: 14px;text-align: left;line-height: 26px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第七条</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本协议一式六份，甲方执五份，乙方（丙方）执一份。</div>
                <div>
                    <table style="height: 100px;width: 80%;margin-top: 20px;text-align: right;">
                        <tr>
                            <td style="width: 50%;">甲    方（盖章）：  </td>
                            <td style="width: 50%;">乙      方（签字）：</td>
                        </tr>
                        <tr>
                            <td>代办单位（签字盖章）：</td>
                            <td>乙方委托代理人（签字）： </td>
                        </tr>
                        <tr>
                            <td>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        </tr>
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <div style="font-size: 14px;text-align: left;line-height: 26px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第三条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签约、搬迁及付款</b></div>
                <div style="text-align: left;line-height: 26px;">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    1、甲、乙双方签订本协议时，乙方应提交被征收房屋所有权证、国有土地使用证以及其他相关资料给甲方，甲方应于本协议签订之日起，经会计机构审核出具审计报告并完成征收工作相关手续后15个工作日内，将房屋征收补偿的总金额支付给乙方，支付方式为甲方将补偿款存入甲方在银行设定的乙方名下的储蓄账户。
                    <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    2、乙方保证向甲方所提供的所有产权书证及其他相关资料真实、合法；乙方未隐瞒被征收房屋的产权纠纷或抵押担保等状况，如因隐瞒或提供资料不实产生的一切责任由乙方承担。
                    <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    3、乙方应于<div style="width: 60px;" class="rows_div">${rmbRecom.beforeDay}</div>日内搬迁腾空房屋内物品，并结清房屋水、电等费用后移交给甲方。
                </div>
                <div style="font-size: 14px;text-align: left;line-height: 26px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第四条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其它约定</b></div>
                <div style="text-align: left;line-height: 26px;">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    1、约定：<div style="width: 500px;text-align: left;" class="rows_div">${rmbRecom.otherTermsOne}</div>
                    <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    2、约定：<div style="width: 500px;text-align: left;" class="rows_div">${rmbRecom.otherTermsTwo}</div>
                </div>
                <div style="font-size: 14px;text-align: left;line-height: 26px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第五条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;争议的处理</b></div>
                <div style="text-align: left;line-height: 26px;">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    因履行本协议发生争议时，由甲、乙双方协商解决；协商不成的，可依法申请行政复议或提起行政诉讼。
                </div>
                <div style="font-size: 14px;text-align: left;line-height: 26px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第六条</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本协议经乙方签字按手印、甲方签字加盖公章之后生效。</div>
                <div style="font-size: 14px;text-align: left;line-height: 26px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第七条</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本协议一式陆份，甲方执伍份，乙方执壹份。</div>
                <div>
                    <table style="height: 100px;width: 80%;margin-top: 20px;text-align: right;">
                        <tr>
                            <td style="width: 50%;">甲    方（盖章）：  </td>
                            <td style="width: 50%;">乙      方（签字）：</td>
                        </tr>
                        <tr>
                            <td>代办单位（签字盖章）：</td>
                            <td>乙方委托代理人（签字）： </td>
                        </tr>
                        <tr>
                            <td>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        </tr>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>


    </div>
</div>



<script type="text/javascript">
    //var LODOP = document.getElementById("LODOP_OB");
    function eventPrint() {
        var LODOP = getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
        //获取打印机
        if (!LODOP) {
            return false;
        }

        //初始化对象
        LODOP.PRINT_INIT("");
        /**
         * 1---纵(正)向打印，固定纸张；
         2---横向打印，固定纸张；
         3---纵(正)向打印，宽度固定，高度按打印内容的高度自适应；
         0(或其它)----打印方向由操作者自行选择或按打印机缺省设置；
         **/
        // LODOP.SET_PRINT_PAGESIZE(0,"595in","841in", "A4");
        LODOP.SET_PRINT_PAGESIZE(1,0,0, "A3");
        LODOP.SET_PRINT_STYLE("Angle",270);
        //设置纯文本打印的文字大小
        LODOP.SET_PRINT_STYLE("FontSize",11);
        var recordId = $("#recordId").val();
        $.ajax({
            url: "ssadmin/stylusPrint/rmbRecompense-print.html",
            type: "post",
            data: {"id": recordId},
            async: false,
            dataType: "json",
            success: function (data) {
                console.log(data);
                for (var i = 0; i < data.length; i++) {
                    var map = data[i];
                    var top  = map["y"];
                    var left  = map["x"];
                    var height =  map["height"];
                    var width =  map["width"];
                    var content = map["data"];
                    top = new Number(top);
                    left = new Number(left);
                    height = new Number(height);
                    width = new Number(width);

                    LODOP.ADD_PRINT_TEXT(top,left,width,height,content);
                }

            }
        });






        //预览
        LODOP.PREVIEW();
    }

    $(document).ready(function () {

    });
</script>

</body>

</html>