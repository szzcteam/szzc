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
    <script src="${oss_url}/static/ssadmin/js/lodop_assets/layer/layer.js" type="text/javascript"></script>
    <script src="${oss_url}/static/ssadmin/js/lodop_assets/my.js" type="text/javascript"></script>
    <script src="${oss_url}/static/ssadmin/js/lodop_assets/lodop/LodopFuncs.js" type="text/javascript"></script>
    <link href="${oss_url}/static/ssadmin/js/themes/css/core.css" rel="stylesheet" type="text/css"/>

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
</head>

<body style="overflow-y: auto;">
<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>
    <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
</object>
<div class="pageContent" style="margin-left: 15%;width: 1000px;">

    <div class="pageFormContent nowrap" layoutH="97">
        <dl>
            <div style="text-align: right;margin-right: 13%;">
                <input type="hidden" value="${settleAccounts.id}" id="recordId">
                <button type="button" id="print" style="width: 70px;height: 26px;" onclick="eventPrint()">打印</button>
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
            <dt style="width: 60px;margin-left: -20px;">
                ${settleAccounts.cardNo}
            </dt>
        </dl>
        <dl>
            <table  border="1" class="tableInfo">
                <tr>
                    <td colspan="5">被拆迁人</td>
                    <td colspan="2" style="width: 100px;">${settleAccounts.houseOwner}</td>
                    <td style="width: 60px;">电话</td>
                    <td style="width: 100px;" colspan="2">${settleAccounts.phone}</td>
                    <td style="width: 80px;">房屋用途</td>
                    <td colspan="2">建筑面积</td>
                    <td style="width: 80px;">套内面积</td>
                </tr>
                <tr>
                    <td colspan="5">公房承租人</td>
                    <td colspan="2">${settleAccounts.lessee}</td>
                    <td>电话</td>
                    <td colspan="2">${settleAccounts.lesseePhone}</td>
                    <td>${settleAccounts.useing}</td>
                    <td colspan="2">
                        ${settleAccounts.checkInArea}
                    </td>
                    <td>${settleAccounts.inArea}</td>
                </tr>
                <tr>
                    <td colspan="7">被拆迁房屋地址</td>
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
                    <td colspan="6" class="td_left">装修折旧补偿</td>
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
                    <td rowspan="${adjunctRow}">七</td>
                    <td rowspan="${adjunctRow}" style="width: 35px;">附属设施补偿</td>
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
                    <td colspan="2" rowspan="${strustRow}" class="td_left">6、构建物补偿</td>
                    <td colspan="3" class="td_left">（1）结构内阳台</td>
                    <td colspan="4">
                        ${settleAccounts.calcStructureBalcony}
                    </td>
                    <td colspan="2">${settleAccounts.structureBalcony}</td>
                    <td>${settleAccounts.structureBalconyBz}</td>
                </tr>
                <tr>
                    <td colspan="3" class="td_left">（2）暗楼</td>
                    <td colspan="4">
                        ${settleAccounts.calcStructureDark}
                    </td>
                    <td colspan="2">${settleAccounts.structureDark}</td>
                    <td>${settleAccounts.structureDarkBz}</td>
                </tr>
                <tr>
                    <td colspan="3" class="td_left" style="white-space:nowrap;">（3）夹层（假层）</td>
                    <td colspan="4">
                        ${settleAccounts.calcStructureMezzanine}
                    </td>
                    <td colspan="2">${settleAccounts.structureMezzanine}</td>
                    <td>${settleAccounts.structureMezzanineBz}</td>
                </tr>
                <tr>
                    <td colspan="3" class="td_left">（4）外挑搭建（或棚子）</td>
                    <td colspan="4">
                        ${settleAccounts.calcStructureBuild}
                    </td>
                    <td colspan="2">${settleAccounts.structureBuild}</td>
                    <td>${settleAccounts.structureBuildBz}</td>
                </tr>
                <tr>
                    <td colspan="3" class="td_left">（5）楼顶搭建（简易房）</td>
                    <td colspan="4">
                        ${settleAccounts.calcStructureRoof}
                    </td>
                    <td colspan="2">${settleAccounts.structureRoof}</td>
                    <td>${settleAccounts.structureRoofBz}</td>
                </tr>
                <c:if test="${showAdjunctOther == true}">
                     <tr>
                         <td colspan="3" class="td_left">（6）其他&nbsp;&nbsp;${settleAccounts.affiliatedOtherDesc}</td>
                         <td colspan="4">
                             ${settleAccounts.calcAffiliatedOther}
                         </td>
                         <td colspan="2">${settleAccounts.affiliatedOther}</td>
                         <td>${settleAccounts.affiliatedOtherBz}</td>
                     </tr>
                </c:if>

                <tr>
                    <td>八</td>
                    <td colspan="6" class="td_left">房屋改变用途补助</td>
                    <td colspan="4">
                        ${settleAccounts.calcChangeCompensate}
                    </td>
                    <td colspan="2">${settleAccounts.changeCompensate}</td>
                    <td>${settleAccounts.changeCompensateBz}</td>
                </tr>
                <tr>
                    <td>九</td>
                    <td colspan="6" class="td_left">生活困难补助</td>
                    <td colspan="4" id="lifeCalcTd">
                        ${settleAccounts.calcLifeCompensate}
                    </td>
                    <td colspan="2">${settleAccounts.lifeCompensate}</td>
                    <td>${settleAccounts.lifeCompensateBz}</td>
                </tr>
                <tr>
                    <td>十</td>
                    <td colspan="6" class="td_left">货币补偿补助（或产权调换补助）</td>
                    <td colspan="4" id="rmbCompensate_td1">
                        ${settleAccounts.calcRmbCompensate}
                    </td>
                    <td colspan="2" id="rmbCompensate_td2">${settleAccounts.rmbCompensate}</td>
                    <td id="rmbCompensate_td3">${settleAccounts.rmbCompensateBz}</td>
                </tr>
                <tr>
                    <td>十一</td>
                    <td colspan="6" class="td_left">小户型住房困难补助(选择货币补偿时)</td>
                    <td colspan="4">${settleAccounts.calcSmallAreaReward}</td>
                    <td colspan="2">${settleAccounts.smallAreaReward}</td>
                    <td>${settleAccounts.smallAreaRewardBz}</td>
                </tr>
                <tr>
                    <td>十二</td>
                    <td colspan="6" class="td_left">建筑面积补助</td>
                    <td colspan="4">${settleAccounts.calcBuildingAreaFee}</td>
                    <td colspan="2">${settleAccounts.buildingAreaFee}</td>
                    <td>${settleAccounts.buildingAreaFeeBz}</td>
                </tr>
                <tr>
                    <td>十三</td>
                    <td colspan="6" class="td_left">征收奖励</td>
                    <td colspan="4">
                        ${settleAccounts.calcMoveReward}
                    </td>
                    <td colspan="2">${settleAccounts.moveReward}</td>
                    <td>${settleAccounts.moveRewardBz}</td>
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
                    <td rowspan="4">选择产权调换方式</td>
                    <td colspan="4" rowspan="2">产权调换房屋情况</td>
                    <td colspan="2" >安置房名称、房号</td>
                    <td colspan="4">
                        ${settleAccounts.newHouseName}
                    </td>
                    <td rowspan="2" colspan="2">${settleAccounts.houseMoney}</td>
                    <td rowspan="2">${settleAccounts.houseMoneyBz}</td>
                </tr>
                <tr style="height: 30px;">
                    <td colspan="2">安置房面积、单价、总价</td>
                    <td colspan="4">
                        ${settleAccounts.swapArea}
                        &nbsp;M<sup>2</sup>×&nbsp;&nbsp;
                        ${settleAccounts.swapPrice}&nbsp;&nbsp;元/M<sup>2</sup>
                        ＝${settleAccounts.swapMoney}&nbsp;&nbsp;元
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
        LODOP.SET_PRINT_PAGESIZE(1,0,0, "A4");
        //设置纯文本打印的文字大小
        LODOP.SET_PRINT_STYLE("FontSize",10);
        var recordId = $("#recordId").val();
        $.ajax({
            url: "ssadmin/stylusPrint/settleAccounts-print.html",
            type: "post",
            data: {"id": recordId},
            async: false,
            dataType: "json",
            success: function (data) {
                console.log(data);
                /**data返回的是 List<Map<String, Object>> 格式**/
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