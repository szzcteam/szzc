
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../comm/include.inc.jsp" %>
<!-- 引入echart.js -->
<script type="text/javascript" src="${oss_url}/static/ssadmin/js/chart/echarts.js"/>
<script type="text/javascript" src="${oss_url}/static/ssadmin/js/business/report/data_chart.js"/>

<div class="pageHeader">

        <div class="searchBar">

            <table class="searchContent">
                <tr>
                    <td>
                        片&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;区：
                        <select name="areaId" id="areaId" style="width: 150px;height: 21px;">
                            <option value="">全部</option>
                            <c:forEach items="${areaList}" var="area" varStatus="num">
                                <option value="${area.id}" <c:if test="${areaId==area.id }">selected="selected"</c:if>>${area.name}</option>
                            </c:forEach>
                        </select>
                    </td>

                    <%--<td>签约状态：
                        <select name="signingStatus" style="width: 100px;height: 21px;">
                            <option value="">全部</option>
                            <c:forEach items="${signingStatusMap}" var="item">
                                <option value="${item.key}" <c:if test="${signingStatus ==  item.key}">selected</c:if>>${item.value}</option>
                            </c:forEach>
                        </select>
                    </td>--%>
                    <td>签约时间：<input type="text" name="startDate" class="date"
                                    readonly="true" value="${startDate }"/>
                    </td>
                    <td>
                        至&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="text" name="endDate" class="date"
                               readonly="true" value="${endDate }"/>
                    </td>

                    <td>
                        <div class="buttonActive">
                            <div class="buttonContent">
                                <button type="button" id="btn_query">查询</button>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
        </div>

</div>
<div class="pageContent" style="width: 100%;height: 600px;">


<div style="width:700px;height:450px;float:left;" id="chart-line"></div>
</div>

