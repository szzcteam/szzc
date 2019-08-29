<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>结算单预览</title>

    <script src="${oss_url}/static/ssadmin/js/js/jquery-1.7.2.js" type="text/javascript"></script>
    <link href="${oss_url}/static/ssadmin/js/themes/css/core.css" rel="stylesheet"
          type="text/css"/>

    <style type="text/css">
        table input{
            margin-left: 5px;
            float: left;
        }

        .tableInfo {
            width: 860px;
            min-height: 25px;
            line-height: 28px;
            text-align: center;
            border-color: #a2bac0;
            border-collapse: collapse;
        }

        .tableInfo tr td {
            border: 1px solid #a2bac0;
            line-height: 28px;
        }

        .td_left {
            text-align: left;
            padding-left: 5px;
        }
        input {
            font-size: 12px;
        }

    </style>
    
    <script type="text/javascript">
        $(document).ready(function () {
            $("#print").click(function () {
                var recordId = $("#recordId").val();
                $.ajax({
                    url: "ssadmin/stylusPrint/settleAccounts.html",
                    type: "post",
                    data: {"id": recordId},
                    dataType: "json",
                    success: function (data) {
                        console.log(data);
                    }
                });
            });
        });
    </script>

</head>

<body style="overflow-y: auto;">
<div class="pageContent" style="margin-left: 15%;width: 1000px;">

        <div class="pageFormContent nowrap" layoutH="97">
            <dl>
                <div style="text-align: right;margin-right: 13%;">
                    <input type="hidden" value="${settleAccounts.id}" id="recordId">
                    <button type="button" id="print" style="width: 70px;height: 26px;">打印</button>
                </div>
            </dl>
            <dl>
                <div style="margin-top:10px; margin-left: 30px;font-size: 18px;">
                    ${settleAccounts.projectName}
                        项目房屋征收补偿资金结算单
                </div>
            </dl>
            <dl style="margin-bottom: -10px;">
                <dt style="width: 450px;">
                   <%-- ${settleAccounts.createDate}--%>
                </dt>
                <dt style="width: 40px;">编号：</dt>
                <dt style="width: 30px;margin-left: -20px;">
                    ${settleAccounts.cardNo}
                </dt>
            </dl>
            <dl>
                <table  border="1" class="tableInfo">
                    <tr>
                        <td colspan="5">被征收人</td>
                        <td colspan="2" style="width: 100px;">${settleAccounts.houseOwner}</td>
                        <td style="width: 60px;">电话</td>
                        <td style="width: 100px;">${settleAccounts.phone}</td>
                        <td style="width: 80px;">房屋用途</td>
                        <td style="width: 100px;">建筑面积</td>
                        <td colspan="2" style="width: 249px;">未经登记建筑面积</td>
                        <td style="width: 80px;">房屋评估单价</td>
                    </tr>
                    <tr>
                        <td colspan="5">公房承租人</td>
                        <td colspan="2">${settleAccounts.lessee}</td>
                        <td>电话</td>
                        <td>${settleAccounts.lesseePhone}</td>
                        <td>${settleAccounts.useing}</td>
                        <td>
                            ${settleAccounts.checkInArea}
                        </td>
                        <td colspan="2">${settleAccounts.noCheckinArea}</td>
                        <td>${settleAccounts.assessPrice}</td>
                    </tr>
                    <tr>
                        <td colspan="7">被征收房屋地址</td>
                        <td colspan="7">${settleAccounts.address}</td>
                    </tr>
                  <%--  <tr>
                        <td colspan="7">被征收人身份证号</td>
                        <td colspan="7"><input type="text" name="identityNo" value="${settleAccounts.identityNo}" class="none_border" style="width: 400px;"></td>
                    </tr>--%>
                    <tr>
                        <td style="width: 35px;">序号</td>
                        <td colspan="6">补偿内容</td>
                        <td colspan="4">计算公式</td>
                        <td colspan="2">金额(元)</td>
                        <td>备注</td>
                    </tr>
                    <tr>
                        <td rowspan="3">一</td>
                        <td rowspan="3">房屋价值补偿</td>
                        <td colspan="5" class="td_left">1、有证房屋补偿</td>
                        <td colspan="4">
                            ${settleAccounts.calcValueCompensate}
                        </td>
                        <td colspan="2">${settleAccounts.valueCompensate}</td>
                        <td>${settleAccounts.valueCompensateBz}</td>
                    </tr>
                    <tr>
                        <td colspan="5" class="td_left">2、未经登记的合法建筑补偿</td>
                        <td colspan="4">
                            ${settleAccounts.calcNoRegisterLegal}
                        </td>
                        <td colspan="2">${settleAccounts.noRegisterLegal}</td>
                        <td>${settleAccounts.noRegisterLegalBz}</td>
                    </tr>
                    <tr>
                        <td colspan="5" class="td_left">3、历史遗留无证房补偿</td>
                        <td colspan="4">
                            ${settleAccounts.calcHistoryLegacy}
                        </td>
                        <td colspan="2">${settleAccounts.historyLegacy}</td>
                        <td>${settleAccounts.historyLegacyBz}</td>
                    </tr>
                    <tr>
                        <td>二</td>
                        <td colspan="6" class="td_left">装修补偿</td>
                        <td colspan="4">
                            ${settleAccounts.calcDecorationCompensate}
                        </td>
                        <td colspan="2">${settleAccounts.decorationCompensate}</td>
                        <td>${settleAccounts.decorationCompensateBz}</td>
                    </tr>
                    <tr>
                        <td>三</td>
                        <td colspan="6" class="td_left">房屋搬迁补偿（搬家费）</td>
                        <td colspan="4">
                            ${settleAccounts.calcMoveHouseFee}
                        </td>
                        <td colspan="2">${settleAccounts.moveHouseFee}</td>
                        <td>${settleAccounts.moveHouseFeeBz}</td>
                    </tr>
                    <tr>
                        <td>四</td>
                        <td colspan="6" class="td_left">临时安置补偿（过渡费）</td>
                        <td colspan="4">
                            ${settleAccounts.calcInterimFee}
                        </td>
                        <td colspan="2">${settleAccounts.interimFee}</td>
                        <td>${settleAccounts.interimFeeBz}</td>
                    </tr>
                    <tr>
                        <td>五</td>
                        <td colspan="6" class="td_left">保底补偿</td>
                        <td colspan="4">
                            ${settleAccounts.calcGuarantee}
                        </td>
                        <td colspan="2">${settleAccounts.guarantee}</td>
                        <td>${settleAccounts.guaranteeBz}</td>
                    </tr>
                    <tr>
                        <td>六</td>
                        <td colspan="6" class="td_left">停产停业损失补偿</td>
                        <td colspan="4">
                            ${settleAccounts.calcSuspendBusinessFee}
                        </td>
                        <td colspan="2">${settleAccounts.suspendBusinessFee}</td>
                        <td>${settleAccounts.suspendBusinessFeeBz}</td>
                    </tr>
                    <tr>
                        <td rowspan="11">七</td>
                        <td rowspan="11" style="width: 35px;">附属设施补偿</td>
                        <td colspan="5" class="td_left">1、水表迁移费</td>
                        <td colspan="4">
                            ${settleAccounts.calcMoveWaterMeterFee}
                        </td>
                        <td colspan="2">${settleAccounts.moveWaterMeterFee}</td>
                        <td>${settleAccounts.moveWaterMeterFeeBz}</td>
                    </tr>
                    <tr>
                        <td colspan="5" class="td_left">2、电表迁移费</td>
                        <td colspan="4">
                            ${settleAccounts.calcMoveAmmeterFee}
                        </td>
                        <td colspan="2">${settleAccounts.moveAmmeterFee}</td>
                        <td>${settleAccounts.moveAmmeterFeeBz}</td>
                    </tr>
                    <tr>
                        <td colspan="5" class="td_left">3、空调移机费</td>
                        <td colspan="4">
                            ${settleAccounts.calcMoveAirConditioningFee}
                        </td>
                        <td colspan="2">${settleAccounts.moveAirConditioningFee}</td>
                        <td>${settleAccounts.moveAirConditioningFeeBz}</td>
                    </tr>
                    <tr>
                        <td colspan="5" class="td_left">4、热水器拆装费</td>
                        <td colspan="4">
                            ${settleAccounts.calcHotWaterCompensate}
                        </td>
                        <td colspan="2">${settleAccounts.hotWaterCompensate}</td>
                        <td>${settleAccounts.hotWaterCompensateBz}</td>
                    </tr>
                    <tr>
                        <td colspan="5" class="td_left">5、管道煤气拆装费</td>
                        <td colspan="4">${settleAccounts.calcGasFee}</td>
                        <td colspan="2">${settleAccounts.gasFee}</td>
                        <td>${settleAccounts.gasFeeBz}</td>
                    </tr>
                    <tr>
                        <td colspan="2" rowspan="6" class="td_left">6、构建物补偿</td>
                        <td colspan="3" class="td_left">（1）结构内阳台</td>
                        <td colspan="4">
                            ${settleAccounts.calcStructureBalcony}
                        </td>
                        <td colspan="2">${settleAccounts.structureBalcony}</td>
                        <td>${settleAccounts.structureBalconyBz}</td>
                    </tr>
                    <tr>
                        <td colspan="3" class="td_left">（2）外挑搭建</td>
                        <td colspan="4">
                            ${settleAccounts.calcStructureBuild}
                        </td>
                        <td colspan="2">${settleAccounts.structureBuild}</td>
                        <td>${settleAccounts.structureBuildBz}</td>
                    </tr>
                    <tr>
                        <td colspan="3" class="td_left">（3）暗楼</td>
                        <td colspan="4">
                            ${settleAccounts.calcStructureDark}
                        </td>
                        <td colspan="2">${settleAccounts.structureDark}</td>
                        <td>${settleAccounts.structureDarkBz}</td>
                    </tr>
                    <tr>
                        <td colspan="3" class="td_left" style="white-space:nowrap;">（4）夹层（假层）</td>
                        <td colspan="4">
                            ${settleAccounts.calcStructureMezzanine}
                        </td>
                        <td colspan="2">${settleAccounts.structureMezzanine}</td>
                        <td>${settleAccounts.structureMezzanineBz}</td>
                    </tr>
                    <tr>
                        <td colspan="3" class="td_left">（5）楼顶搭建</td>
                        <td colspan="4">
                            ${settleAccounts.calcStructureRoof}
                        </td>
                        <td colspan="2">${settleAccounts.structureRoof}</td>
                        <td>${settleAccounts.structureRoofBz}</td>
                    </tr>
                    <tr>
                        <td colspan="3" class="td_left">（6）其他&nbsp;&nbsp;${settleAccounts.affiliatedOtherDesc}</td>
                        <td colspan="4">
                            ${settleAccounts.calcAffiliatedOther}
                        </td>
                        <td colspan="2">${settleAccounts.affiliatedOther}</td>
                        <td>${settleAccounts.affiliatedOtherBz}</td>
                    </tr>
                    <tr>
                        <td>八</td>
                        <td colspan="6" class="td_left">改变房屋用途补助</td>
                        <td colspan="4">
                            ${settleAccounts.calcChangeCompensate}
                        </td>
                        <td colspan="2">${settleAccounts.changeCompensate}</td>
                        <td>${settleAccounts.changeCompensateBz}</td>
                    </tr>
                    <tr>
                        <td>九</td>
                        <td colspan="6" class="td_left">货币补偿补助（或产权调换补助）</td>
                        <td colspan="4" id="rmbCompensate_td1">
                            ${settleAccounts.calcRmbCompensate}
                        </td>
                        <td colspan="2" id="rmbCompensate_td2">${settleAccounts.rmbCompensate}</td>
                        <td id="rmbCompensate_td3">${settleAccounts.rmbCompensateBz}</td>
                    </tr>
                    <tr>
                        <td>十</td>
                        <td colspan="6" class="td_left">小户型住房困难补助(选择货币补偿时)</td>
                        <td colspan="4">${settleAccounts.calcSmallAreaReward}</td>
                        <td colspan="2">${settleAccounts.smallAreaReward}</td>
                        <td>${settleAccounts.smallAreaRewardBz}</td>
                    </tr>
                    <tr>
                        <td>十一</td>
                        <td colspan="6" class="td_left">生活困难补助</td>
                        <td colspan="4" id="lifeCalcTd">
                            ${settleAccounts.calcLifeCompensate}
                        </td>
                        <td colspan="2">${settleAccounts.lifeCompensate}</td>
                        <td>${settleAccounts.lifeCompensateBz}</td>
                    </tr>
                    <tr>
                        <td>十二</td>
                        <td colspan="6" class="td_left">奖励</td>
                        <td colspan="4">
                            ${settleAccounts.calcMoveReward}
                        </td>
                        <td colspan="2">${settleAccounts.moveReward}</td>
                        <td>${settleAccounts.moveRewardBz}</td>
                    </tr>
                    <tr>
                        <td>十三</td>
                        <td colspan="6" class="td_left">建筑面积补助</td>
                        <td colspan="4">${settleAccounts.calcBuildingAreaFee}</td>
                        <td colspan="2">${settleAccounts.buildingAreaFee}</td>
                        <td>${settleAccounts.buildingAreaFeeBz}</td>
                    </tr>
                    <tr>
                        <td>十四</td>
                        <td colspan="6" class="td_left">其他&nbsp;&nbsp;&nbsp;&nbsp;${settleAccounts.otherDesc}</td>
                        <td colspan="4">${settleAccounts.calcOther}</td>
                        <td colspan="2">${settleAccounts.otherRmb}</td>
                        <td>${settleAccounts.otherBz}</td>
                    </tr>
                    <tr>
                        <td colspan="7">被征收房屋补偿合计</td>
                        <td colspan="4">${settleAccounts.calcSumCompensate}</td>
                        <td colspan="2">${settleAccounts.sumCompensate}</td>
                        <td>${settleAccounts.sumCompensateBz}</td>
                    </tr>
                    <tr>
                        <td rowspan="4">仅产权调换方式</td>
                        <td colspan="5">产权调换房价单价</td>
                        <td style="width: 100px;">建筑面积</td>
                        <td colspan="4" rowspan="2">
                            <br/>
                            <label style="width: 300px;">${settleAccounts.swapMoney1}</label>
                            <label style="width: 300px;">${settleAccounts.swapMoney2}</label>
                            <label style="width: 300px;">${settleAccounts.swapMoney3}</label>
                            <label style="width: 300px;">${settleAccounts.swapMoney4}</label>
                            <label style="width: 300px;">${settleAccounts.swapMoney5}</label>
                        </td>
                        <%--<td colspan="3" style="font-size: xx-small;">其中<input type="text" name="calcm" class="none_border" style="clear: both;float:none;width: 40px;"> m<sup>2</sup>
                            与被征收房屋(<input type="text" name="price" class="none_border" style="clear: both;float:none;width: 50px;">元)置换互不补差价</td>--%>
                        <td rowspan="2" colspan="2">${settleAccounts.houseMoney}</td>
                        <td rowspan="2">${settleAccounts.houseMoneyBz}</td>
                    </tr>
                    <tr style="height: 30px;">
                        <td colspan="5">
                            <label style="width: 70px;">${settleAccounts.swapPrice1}</label>
                            <label style="width: 70px;">${settleAccounts.swapPrice2}</label>
                            <label style="width: 70px;">${settleAccounts.swapPrice3}</label>
                            <label style="width: 70px;">${settleAccounts.swapPrice4}</label>
                            <label style="width: 70px;">${settleAccounts.swapPrice5}</label>
                        </td>
                        <td>
                            <label style="width: 70px;">${settleAccounts.swapArea1}</label>
                            <label style="width: 70px;">${settleAccounts.swapArea2}</label>
                            <label style="width: 70px;">${settleAccounts.swapArea3}</label>
                            <label style="width: 70px;">${settleAccounts.swapArea4}</label>
                            <label style="width: 70px;">${settleAccounts.swapArea5}</label>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6">已抵扣安置房款</td>
                        <td colspan="4"><%--<input type="text" name="calcDeduction" class="none_border width_220px">--%></td>
                        <td colspan="2">${settleAccounts.deduction}</td>
                        <td>${settleAccounts.deductionBz}</td>
                    </tr>
                    <tr>
                        <td colspan="6">应付（收）款</td>
                        <td colspan="4">${settleAccounts.payTotal}</td>
                        <%--  <td>应收合计</td>
                          <td colspan="3"><input type="text" name="receiveTotal" class="none_border" style="width: 170px;"></td>--%>
                        <td rowspan="2">经办人签字</td>
                        <td rowspan="2" colspan="2" style="white-space:nowrap;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td colspan="11" class="td_left">实际支付金额(大写)：${settleAccounts.payMoney}</td>
                    </tr>
                    <%-- <tr>
                         <td colspan="8">实际应收金额(大写)：<input type="text" name="receiveMoney" class="none_border" style="width: 320px;clear: both;float: none;"></td>
                     </tr>--%>
                </table>
            </dl>

        </div>


</div>
</body>

</html>