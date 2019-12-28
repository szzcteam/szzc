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

        .span_underline{
            text-decoration: underline;
            min-width: 40px;
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
                    <td></td>
                </tr>
                <tr>
                    <td>被征收人（乙方）：<div style="width: 90px;" class="rows_div">${rmbRecom.houseOwner}</div></td>
                    <td>身份证号：<div style="width: 208px;" class="rows_div">${rmbRecom.identityNo}</div></td>
                </tr>
                <tr>
                    <td>委托代理人（乙方）：<div style="width: 90px;" class="rows_div">${rmbRecom.consignName}</div></td>
                    <td>身份证号：<div style="width: 208px;" class="rows_div">${rmbRecom.consignIdentityNo}</div></td>
                </tr>

            </table>
        </div>
        <div style="line-height: 26px;text-align: left;">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;武汉市武昌区人民政府于${rmbRecom.govYear}年${rmbRecom.govMonth}月${rmbRecom.govDay}日作出《房屋征收决定》（武昌征决字[${rmbRecom.adjuLetter}] ${rmbRecom.adjuNum}号），
            并于${rmbRecom.noticeYear}年${rmbRecom.noticeMonth}月${rmbRecom.noticeDay}日在房屋征收范围内公告，
            乙方房屋在征收范围内。依据《国有土地上房屋征收与补偿条例》等相关规定，按照房屋征收补偿方案，甲、乙双方经平等协商，
            就房屋征收补偿事宜达成协议如下：
        </div>
        <div style="font-size: 14px;text-align: left;line-height: 26px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一、&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;被征收房屋基本情况</b></div>
        <div style="text-align: left;line-height: 26px;">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            乙方被征收房屋位于<span class="span_underline">&nbsp;&nbsp;${rmbRecom.address}</span>，
            房屋所有权证号<span class="span_underline">&nbsp;&nbsp;${rmbRecom.houseOwnerNumber}</span>&nbsp;&nbsp;，
            国有土地使用权证号<span class="span_underline">&nbsp;&nbsp;${rmbRecom.publicOwnerNumber}</span>&nbsp;&nbsp;，
            房屋权属份额<span class="span_underline">${rmbRecom.proportion}</span>%，
            证载房屋用途<span class="span_underline">${rmbRecom.useing}</span>，
            登记建筑面积<span class="span_underline">${rmbRecom.certifiedArea}</span>㎡，
            （其中：
            住宅面积&nbsp;&nbsp;<span class="span_underline">${rmbRecom.residenceArea}</span>&nbsp;&nbsp;M<sup>2</sup>，
            经营面积&nbsp;&nbsp;<span class="span_underline">${rmbRecom.valueCompensateBusinessArea}</span>&nbsp;&nbsp;M<sup>2</sup>，
            办公面积&nbsp;&nbsp;<span class="span_underline">${rmbRecom.officeArea}</span>&nbsp;&nbsp;M<sup>2</sup>，
            生产面积&nbsp;&nbsp;<span class="span_underline">${rmbRecom.produceArea}</span>&nbsp;&nbsp;M<sup>2</sup>，
            其他面积&nbsp;&nbsp;<span class="span_underline">${rmbRecom.otherArea}</span>&nbsp;&nbsp;M<sup>2</sup>），
            未登记建筑面积：<span class="span_underline">${rmbRecom.noRegisterLegalArea}</span>&nbsp;&nbsp;M<sup>2</sup> ,
            被征收房屋价值评估单价：<span class="span_underline">${rmbRecom.assessPrice}</span>&nbsp;元/M<sup>2</sup>
        </div>
        <div style="font-size: 14px;text-align: left;line-height: 26px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;二、&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;乙方选择货币补偿</b></div>
        <div style="font-size: 14px;text-align: left;line-height: 26px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;三、&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;房屋征收补偿款项</b></div>
        <div style="text-align: left;line-height: 26px;">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            甲方支付给乙方的房屋征收补偿、 补助及奖励等款项如下：
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            1、房屋价值补偿： 经依法选定的房地产价格评估机构评估， 补偿金额为<div style="width: 80px;" class="rows_div">${rmbRecom.valueCompensate}</div>元。
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            2、房屋室内装饰装修补偿：<div style="width: 80px;" class="rows_div">${rmbRecom.decorationCompensate}</div>元
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            3、房屋附属设施补偿：
            构建物补偿<div style="width: 80px;" class="rows_div">${rmbRecom.structureCompensate}</div>元、
            电表迁移费<div style="width: 80px;" class="rows_div">${rmbRecom.moveAmmeterFee}</div>元、
            水表迁移费<div style="width: 80px;" class="rows_div">${rmbRecom.moveWaterMeterFee}</div>元、
            空调移机费<div style="width: 80px;" class="rows_div">${rmbRecom.moveAirConditioningFee}</div>元、
            管道煤气拆装补偿<div style="width: 80px;" class="rows_div">${rmbRecom.gasFee}</div>元、
            热水器拆装补偿<div style="width: 80px;" class="rows_div">${rmbRecom.hotWaterCompensate}</div>元。
            小计:<div style="width: 80px;" class="rows_div">${rmbRecom.subtotal}</div>元。
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            4、搬迁补偿（搬家费）：<div style="width: 80px;" class="rows_div">${rmbRecom.moveHouseFee}</div>元。
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            5、临时安置补偿（过渡费）：<div style="width: 80px;" class="rows_div">${rmbRecom.interimPrice}</div>元/月
            ×<div style="width: 80px;" class="rows_div">${rmbRecom.interimMonth}</div>个月
            &nbsp;＝&nbsp;<div style="width: 80px;" class="rows_div">${rmbRecom.interimFee}</div>元。
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            6、停产停业损失补偿：<div style="width: 80px;" class="rows_div">${rmbRecom.suspendBusinessFee}</div>元。
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            7、货币补偿补助：<div style="width: 100px;" class="rows_div">${rmbRecom.rmbCompensate}</div>元。
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            8、生活困难补助：<div style="width: 100px;" class="rows_div">${rmbRecom.lifeCompensate}</div>元。
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            9、改变房屋用途补助：<div style="width: 100px;" class="rows_div">${rmbRecom.changeCompensate}</div>元
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            10、搬迁奖励：<div style="width: 100px;" class="rows_div">${rmbRecom.moveReward}</div>元
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            11、封闭阳台：<div style="width: 100px;" class="rows_div">${rmbRecom.structureBalcony}</div>元
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            12、未登记建筑：<div style="width: 100px;" class="rows_div">${rmbRecom.noRegisterLegal}</div>元
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            13、其他：<div style="width: 480px;font-size: 9px" class="rows_div">${rmbRecom.otherFeeDetail}</div>元
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            以上 1-13 项总计人民币<div style="width: 100px;" class="rows_div">${rmbRecom.sumRbm}</div>元 （大写：<div style="width: 200px;" class="rows_div">${rmbRecom.upperRmb}</div>）。
        </div>

    </div>

    <!--右侧页-->
    <div style="width:47%;height:100%;float: left;padding-left: 15px">
        <div style="font-size: 14px;text-align: left;line-height: 26px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;四、&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支付期限</b>&nbsp;甲方在本协议书约定的补偿款项经会计机构审核出具审计报告后 7 个工作日内一次性全额支付给乙方</div>
        <div style="font-size: 14px;text-align: left;line-height: 26px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;五&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;搬迁期限及要求</b></div>
        <div style="text-align: left;line-height: 26px;">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            1、乙方签署本协议时， 应将被征收房屋所有权证、 国有土地使用权证以及其他相关证照资料一并交付给甲方。 乙方同意由甲方办理其房屋所有权证、 国有土地使有权证注销登记手续。
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            乙方保证： 向甲方所提供的所有产权书证及其他相关证明材料， 均属客观、 真实， 否则乙方愿承担一切法律责任。 乙方未隐瞒被征收房屋的产权纠纷或抵押担保等状况， 如有隐瞒或提供材料不实， 产生的法律责任由乙方承担。
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            2、乙方领取本协议书后 10 日内搬迁完毕， 搬迁时不得损坏房屋结构及附属设施， 并完好地移交给甲方。 乙方确认： 逾期未移交的， 乙方委托甲方自行处理。
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            3、被征收房屋腾空交付前的水费、 电费、 管道煤气费、 物业管理费、 电信、 有线电视等相关费用， 均由乙方负责缴纳。 未缴清的， 乙方委托甲方从补偿款中代缴。
        </div>
        <div style="font-size: 14px;text-align: left;line-height: 26px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;六&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其它约定</b></div>
        <div style="text-align: left;line-height: 26px;">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            1、 ${rmbRecom.buyHouse}&nbsp;&nbsp;乙方申请购买东龙世纪花园（暂定名）
            <span class="span_underline">${rmbRecom.coveredArea}</span>平方米商品房一套
            （申购序号<span class="span_underline">${rmbRecom.buySerialNumber}</span>）。
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ${rmbRecom.noBuyHouse}&nbsp;&nbsp;乙方不购买东龙世纪花园（暂定名） 商品房。
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            2、约定：<span class="span_underline">${rmbRecom.otherTermsOne}</span>
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            3、约定：<span class="span_underline">${rmbRecom.otherTermsTwo}</span>
        </div>
        <div style="font-size: 14px;text-align: left;line-height: 26px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;七&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;甲乙双方必须履行协议约定的义务， 如因不履行协议约定义务或履行协议发生争议， 由甲乙双方协商解决， 协商不成的， 任何一方均有权向有管辖权的人民法院提起诉讼。</b></div>
        <div style="font-size: 14px;text-align: left;line-height: 26px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;八&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本协议经甲、 乙双方签字盖章后生效。</b></div>
        <div style="font-size: 14px;text-align: left;line-height: 26px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;九&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本协议一式陆份， 甲乙双方各执壹份， 其余报有关部门备案。</b></div>
        <div style="font-size: 14px;text-align: left;line-height: 26px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;十&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;未尽事宜</b></div>
        <div style="text-align: left;line-height: 26px;">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            本协议书未尽事宜， 按国有土地上房屋征收相关法律法规规定执行， 未作出明确规定的， 由甲、 乙双方另行协商后签订补充协议， 补充协议与本协议书具有同等法律效力。
        </div>
        <div style="font-size: 14px;text-align: left;line-height: 26px;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;十一&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;附件效力</b></div>
        <div style="text-align: left;line-height: 26px;">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            本协议书附件与本协议书具有同等法律效力。 本协议书及其附件内容， 空格部分填写的文字与印刷文字具有同等效力。
        </div>
        <div>
            <table style="height: 100px;width: 80%;margin-top: 20px;text-align: right;">
                <tr>
                    <td style="width: 40%;">甲    方（盖章）：  </td>
                    <td style="width: 60%;">&nbsp;</td>
                </tr>
                <tr>
                    <td>实施单位（盖章）：</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td>项目负责人（签字）：</td>
                    <td>乙      方（签字）：</td>
                </tr>
                <tr>
                    <td>经办人（签字）：</td>
                    <td>&nbsp; </td>
                </tr>
                <tr>
                    <td>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;</td>
                </tr>
            </table>
        </div>

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