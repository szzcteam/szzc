<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="comm/include.inc.jsp" %>
<form id="pagerForm" method="post" action="ssadmin/protocolList.html">
    <input type="hidden" name="signingStatus" value="${signingStatus}">
    <input type="hidden" name="address" value="${address}"/>
    <input type="hidden" name="houseOwner" value="${houseOwner}"/>
    <input type="hidden" name="areaId" value="${areaId}"/>
    <input type="hidden" name="startDate" value="${startDate}" />
    <input type="hidden" name="endDate" value="${endDate}" />
    <input type="hidden" name="compensateType" value="${compensateType}" />

    <input type="hidden" name="pageNum" value="${currentPage}"/>
    <input type="hidden" name="numPerPage" value="${numPerPage}"/>
    <input type="hidden" name="orderField" value="${param.orderField}"/>
</form>


<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);"
          action="ssadmin/protocolList.html" method="post">
        <div class="searchBar">

            <table class="searchContent">
                <tr>
                    <td>
                        片&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;区：
                        <select name="areaId" style="width: 150px;">
                            <option value="">请选择</option>
                            <c:forEach items="${areaList}" var="area" varStatus="num">
                                <option value="${area.id}" <c:if test="${areaId==area.id }">selected="selected"</c:if>>${area.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>被征收人：<input type="text" name="houseOwner" value="${houseOwner}" size="20"/></td>
                    <td>地址：<input type="text" name="address" value="${address}" size="40"/></td>
                    <td>签约状态：
                        <select name="signingStatus" style="width: 100px;">
                            <option value="">请选择</option>
                            <c:forEach items="${signingStatusMap}" var="item">
                                <option value="${item.key}" <c:if test="${signingStatus ==  item.key}">selected</c:if>>${item.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>签约时间：<input type="text" name="startDate" class="date"
                                    readonly="true" value="${startDate }"/>
                    </td>
                    <td>
                        至&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="text" name="endDate" class="date"
                               readonly="true" value="${endDate }"/>
                    </td>
                    <td>
                        协议类型：
                        <select name="compensateType" style="width: 100px;">
                            <option value="">请选择</option>
                            <c:forEach items="${compensateTypeMap}" var="item">
                                <option value="${item.key}" <c:if test="${compensateType ==  item.key}">selected</c:if>>${item.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <div class="buttonActive">
                            <div class="buttonContent">
                                <button type="submit">查询</button>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>
<div class="pageContent">
    <div class="panelBar" style="height: 30px;">
        <ul class="toolBar">
            <!-- 新增 -->
            <shiro:hasPermission name="ssadmin/addProtocol.html">
                <li><a class="add"
                       href="ssadmin/goProtocolJSP.html?url=ssadmin/addProtocol"
                       height="550" width="1000" target="dialog" rel="addProtocol"><span>新增</span>
                </a></li>
               <%-- <li><a class="add"
                       href="ssadmin/goProtocolJSP.html?url=ssadmin/addSwapHouse"
                       height="400" width="800" target="dialog" rel="addSwapHouse"><span>新增产权调换</span>
                </a></li>
                <li><a class="add"
                       href="ssadmin/goProtocolJSP.html?url=ssadmin/addRmbRecompense"
                       height="400" width="800" target="dialog" rel="addRmbRecompense"><span>新增货币补偿</span>
                </a></li>--%>
               <%-- <li><a class="add"
                       href="ssadmin/goProtocolJSP.html?url=ssadmin/addNotice"
                       height="600" width="800" target="dialog" rel="addNotice"><span>新增手续通知单</span>
                </a></li>--%>
                <%--<li><a class="add"
                       href="ssadmin/goProtocolJSP.html?url=ssadmin/addSettleAccounts"
                       height="400" width="800" target="dialog" rel="addSettleAccounts"><span>新增结算单</span>
                </a></li>--%>
            </shiro:hasPermission>
            <!-- 修改 -->
            <shiro:hasPermission name="ssadmin/updateProtocol.html">
                <li><a class="edit"
                       href="ssadmin/selectSwapHouseByHouseOwner.html?url=ssadmin/updateSwapHouse&idMore={sid_user}"
                       height="550" width="1000" target="dialog" rel="updateSwapHouse"><span>修改产权调换</span>
                </a></li>
                <li><a class="edit"
                       href="ssadmin/RmbRecompense/query.html?url=ssadmin/updateRmbRecompense&idMore={sid_user}"
                       height="550" width="1000" target="dialog" rel="updateRmbRecompense"><span>修改货币补偿</span>
                </a></li>
                <%--<li><a class="edit"
                       href="ssadmin/notice/query.html?url=ssadmin/updateNotice&houseOwner={sid_user}"
                       height="600" width="800" target="dialog" rel="updateNotice"><span>修改手续通知单</span>
                </a></li>--%>
                <li><a class="edit"
                       href="ssadmin/settleAccounts/query.html?url=ssadmin/updateSettleAccounts&idMore={sid_user}"
                       height="550" width="1000" target="dialog" rel="updateSettleAccounts"><span>修改结算单</span>
                </a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="ssadmin/deleteProtocol.html">
                <li><a class="delete"
                       href="ssadmin/deleteSwapHouse.html?idMore={sid_user}"
                       target="ajaxTodo" title="确定要删除产权调换协议吗?"><span>删除产权调换</span>
                </a></li>
                <li><a class="delete"
                       href="ssadmin/RmbRecompense/delete.html?idMore={sid_user}"
                       target="ajaxTodo" title="确定要删除货币补偿协议吗?"><span>删除货币补偿</span>
                </a></li>
                <%--<li><a class="delete"
                       href="/ssadmin/notice/delete.html?houseOwner={sid_user}"
                       target="ajaxTodo" title="确定要删除通知单吗?"><span>删除通知单</span>
                </a></li>--%>
                <li><a class="delete"
                       href="ssadmin/settleAccounts/delete.html?idMore={sid_user}"
                       target="ajaxTodo" title="确定要删除结算单吗?"><span>删除结算单</span>
                </a></li>
            </shiro:hasPermission>

            <shiro:hasPermission name="ssadmin/exportProtocol.html">
                <%--<li><a class="icon"
                       href="ssadmin/exportSwapHouse.html?idMore={sid_user}"
                       target="dwzOnlyExport" targetType="navTab"><span>导出产权调换</span>
                </a></li>
                <li><a class="icon"
                       href="ssadmin/exportRmbRecompense.html?idMore={sid_user}"
                       target="dwzOnlyExport" targetType="navTab"><span>导出货币补偿</span>
                </a></li>--%>
                <%--<li><a class="icon"
                       href="ssadmin/exportNotice.html?houseOwner={sid_user}"
                       target="dwzOnlyExport" targetType="navTab"><span>导出通知单</span>
                </a></li>--%>
                <%--<li><a class="icon"
                       href="ssadmin/exportSettleAccounts.html?idMore={sid_user}"
                       target="dwzOnlyExport" targetType="navTab"><span>导出结算单</span>
                </a></li>--%>
            </shiro:hasPermission>

            <shiro:hasPermission name="ssadmin/settleAccounts/signing.html">
                <li><a class="edit"
                       href="ssadmin/settleAccounts/signing.html?idMore={sid_user}&status=0"
                       target="ajaxTodo" title="确定将状态修改为“未签约”吗?"><span>未签约</span>
                </a></li>
                <li><a class="edit"
                       href="ssadmin/settleAccounts/signing.html?idMore={sid_user}&status=1"
                       target="ajaxTodo" title="确定签约完成了吗?"><span>已签约</span>
                </a></li>
                <li><a class="edit"
                       href="ssadmin/settleAccounts/signing.html?idMore={sid_user}&status=2"
                       target="ajaxTodo" title="确定已经过审核?"><span>已过审</span>
                </a></li>
            </shiro:hasPermission>
        </ul>
    </div>
    <table class="table" width="100%" layoutH="138">
        <thead>
        <tr>
            <th width="20">序号</th>
            <th width="80">片区</th>
            <th width="60">被征收人</th>
            <th width="60">地址</th>
            <th width="60">电话</th>
            <th width="50">签约状态</th>
            <%--<th width="60">付(收)款手续通知单</th>--%>
            <th width="50">产权调换协议</th>
            <th width="50">货币补偿协议</th>
            <th width="50">结算单</th>
            <th width="50">签约时间</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${protocolList}" var="protocol" varStatus="num">
            <tr target="sid_user" rel="${protocol.settleAccountId},${protocol.rmbRecompenseId},${protocol.swapHouseId}">
                <td>${num.index +1}</td>
                <td>${protocol.areaName}</td>
                <td>${protocol.name}</td>
                <td>${protocol.address}</td>
                <td>${protocol.phone}</td>
                <!--签约状态开始-->
                <c:choose>
                    <c:when test="${protocol.signingStatus == 0}">
                        <td style="color: red">${protocol.signingStatusDesc}</td>
                    </c:when>
                    <c:when test="${protocol.signingStatus == 1}">
                        <td style="color: green"> ${protocol.signingStatusDesc}</td>
                    </c:when>
                    <c:when test="${protocol.signingStatus == 2}">
                        <td style="color: purple"> ${protocol.signingStatusDesc}</td>
                    </c:when>
                    <c:otherwise>
                        <td>${protocol.signingStatusDesc}</td>
                    </c:otherwise>
                </c:choose>
                <!--签约状态结束-->
               <%-- <td><c:if test="${protocol.noticeFlag == true}">√</c:if></td>--%>
                <td style="text-align: center;">
                    <c:if test="${protocol.swapHouseId > 0 }">
                        <a href="ssadmin/settleAccounts/preview.html?id=${protocol.settleAccountId}" target="_blank" style="text-decoration: none;color: #1e88e5;" title="预览">
                            √
                        </a>
                    </c:if>
                </td>
                <td style="text-align: center;">
                    <c:if test="${protocol.rmbRecompenseId > 0 }">
                        <a href="ssadmin/RmbRecompense/preview.html?id=${protocol.rmbRecompenseId}" target="_blank" style="text-decoration: none;color: #1e88e5;" title="预览">
                            √
                        </a>
                    </c:if>
                </td>
                <td style="text-align: center;">
                    <c:if test="${protocol.settleAccountId >0 }">
                        <a href="ssadmin/settleAccounts/preview.html?id=${protocol.settleAccountId}" target="_blank" style="text-decoration: none;color: #1e88e5;" title="预览">
                            √
                        </a>
                    </c:if></td>
                <td>${protocol.createDateStr}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="panelBar">
        <div class="pages">
            <span>总共: ${totalCount}条</span>
        </div>
        <div class="pagination" targetType="navTab" totalCount="${totalCount}"
             numPerPage="${numPerPage}" pageNumShown="5"
             currentPage="${currentPage}"></div>
    </div>
</div>
