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
    <title>坐标</title>
    <script src="${oss_url}/static/ssadmin/js/js/jquery-1.7.2.js" type="text/javascript"></script>
    <script src="${oss_url}/static/ssadmin/js/lodop_assets/layer/layer.js" type="text/javascript"></script>
    <script src="${oss_url}/static/ssadmin/js/lodop_assets/my.js" type="text/javascript"></script>
    <script src="${oss_url}/static/ssadmin/js/lodop_assets/lodop/LodopFuncs.js" type="text/javascript"></script>


</head>

<body style="overflow-y: auto;font-size: 13px;">
<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>
    <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
</object>

选择纸张：<select id="qt" style="width: 120px;height: 26px;">
    <option value="A4">A4</option>
    <option value="A3">A3</option>
</select>
<button type="button" id="print" style="width: 70px;height: 26px;" onclick="eventPrint()">打印</button>



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
        var qt = $("#qt").val();
        if (qt == "A4") {
            LODOP.SET_PRINT_PAGESIZE(1,0,0, "A4");

            // 横轴
            for (var i = 10; i < 750; i=i+30 ) {
                var top  = 10;
                var left  = i;
                var height =  26
                var width =  30;
                var content = "1";
                top = new Number(top);
                left = new Number(left);
                height = new Number(height);
                width = new Number(width);
                LODOP.ADD_PRINT_TEXT(top,left,width,height,content);
            }
            // 竖轴
            for (var i = 10; i < 1100; i=i+30 ) {
                var top  =  i;
                var left  = 10;
                var height =  26
                var width =  30;
                var content = "1";
                top = new Number(top);
                left = new Number(left);
                height = new Number(height);
                width = new Number(width);
                LODOP.ADD_PRINT_TEXT(top,left,width,height,content);
            }


        } else if (qt == "A3") {
            LODOP.SET_PRINT_PAGESIZE(1,0,0, "A3");
            LODOP.SET_PRINT_STYLE("Angle",270);

            // 横轴
            for (var i = 10; i < 1100; i=i+30 ) {
                var top  = 10;
                var left  = i;
                var height =  26
                var width =  30;
                var content = "1";
                top = new Number(top);
                left = new Number(left);
                height = new Number(height);
                width = new Number(width);
                LODOP.ADD_PRINT_TEXT(top,left,width,height,content);
            }

            // 竖轴
            for (var i = 10; i < 1500; i=i+50 ) {
                var top  =  i;
                var left  = 10;
                var height =  26
                var width =  30;
                var content = "1";
                top = new Number(top);
                left = new Number(left);
                height = new Number(height);
                width = new Number(width);
                LODOP.ADD_PRINT_TEXT(top,left,width,height,content);
            }

        } else{
            console.log("暂不支持的纸张 " +qt);
        }

        //设置纯文本打印的文字大小
        LODOP.SET_PRINT_STYLE("FontSize",11);

        //预览
        LODOP.PREVIEW();
    }

    $(document).ready(function () {

    });
</script>



</body>

</html>