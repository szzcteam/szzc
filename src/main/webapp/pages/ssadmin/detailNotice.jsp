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
    <title>交房通知书预览</title>
    <script src="${oss_url}/static/ssadmin/js/js/jquery-1.7.2.js" type="text/javascript"></script>
    <script src="${oss_url}/static/ssadmin/js/lodop_assets/layer/layer.js" type="text/javascript"></script>
    <script src="${oss_url}/static/ssadmin/js/lodop_assets/my.js" type="text/javascript"></script>
    <script src="${oss_url}/static/ssadmin/js/lodop_assets/lodop/LodopFuncs.js" type="text/javascript"></script>

    <style type="text/css">
        .rows_div {
            border-bottom:1px solid black;
            display:inline-block;
            text-align: center;
            height: 30px;
        }
        .font-size-16{
            font-size: 16px;
            line-height: 40px;
        }
    </style>
</head>

<body style="overflow-y: auto;font-size: 13px;">
<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>
    <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
</object>
<!--总的div-->
<div class="pageContent" style="margin-left: 15%;width: 1000px;">
    <div class="pageFormContent nowrap" layoutH="97">
        <dl>
            <div style="text-align: right;margin-right: 13%;">
                <input type="hidden" value="${id}" id="recordId">
                <button type="button" id="print" style="width: 70px;height: 26px;" onclick="eventPrint()">打印</button>
            </div>
        </dl>
        <dl>
            <div style="text-align: center;font-size: 26px;">
                <div class="rows_div" style="width: 200px;">${vo.name}&nbsp;</div>&nbsp;交房通知书
            </div>
        </dl>
        <dl style="margin-left: 10%;">
            <div class="font-size-16">台账号：${vo.cardNo}</div>
            <div class="font-size-16">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 姓名：<div class="rows_div" style="width: 150px;">${vo.houseOwner}</div>
                （身份证号：<div class="rows_div" style="width: 230px;">${vo.identityNo}</div>），
                系 <div class="rows_div" style="width: 200px;">${vo.name}</div> <br/>
                被征收人， 所点安置房为：<div class="rows_div" style="width: 230px;"> ${vo.ridgepole} &nbsp;</div>栋
                <div class="rows_div" style="width: 70px;"> ${vo.unit} &nbsp;</div>单元
                <div class="rows_div" style="width: 70px;">${vo.floor} &nbsp;</div>楼
                <div class="rows_div" style="width: 70px;">${vo.mark}&nbsp;</div>号房，
                建筑面
                <br/>
                积<div class="rows_div" style="width: 60px;">${vo.area}&nbsp;</div> M<sup>2</sup>，
                总房款大写：
                <div class="rows_div" style="width: 60px;">${vo.payParm8}${vo.payParm7}${vo.payParm6}&nbsp;</div>拾
                <div class="rows_div" style="width: 40px;">${vo.payParm5}&nbsp;</div>万
                <div class="rows_div" style="width: 40px;">${vo.payParm4}&nbsp;</div>仟
                <div class="rows_div" style="width: 40px;">${vo.payParm3}&nbsp;</div>佰
                <div class="rows_div" style="width: 40px;">${vo.payParm2}&nbsp;</div>拾
                <div class="rows_div" style="width: 40px;">${vo.payParm1}&nbsp;</div>元
                （￥<div class="rows_div" style="width: 145px;">${vo.sumRbm}</div>）整，<br/>
                补偿协议已抵扣房款<div class="rows_div" style="width: 145px;">${vo.transferRmb}&nbsp;</div>元，
                需补缴房款 <div class="rows_div" style="width: 120px;"> ${vo.lessDifference}&nbsp;</div> 元
                （需退还房款 <div class="rows_div" style="width: 120px;">${vo.difference}&nbsp;</div> 元）。
            </div>
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
        LODOP.SET_PRINT_PAGESIZE(1,0,0, "A3");
        // LODOP.SET_PRINT_STYLE("Angle",270);
        //设置纯文本打印的文字大小
        LODOP.SET_PRINT_STYLE("FontSize",11);
        var recordId = $("#recordId").val();
        $.ajax({
            url: "ssadmin/stylusPrint/notice-print.html",
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