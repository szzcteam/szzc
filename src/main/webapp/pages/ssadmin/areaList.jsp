<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="comm/include.inc.jsp" %>
<form id="pagerForm" method="post" action="ssadmin/roomChange/queryPage.html">
    <input type="hidden" name="name" value="${name}"/>

    <input type="hidden" name="pageNum" value="${currentPage}"/>
    <input type="hidden" name="numPerPage" value="${numPerPage}"/>
    <input type="hidden" name="orderField" value="${param.orderField}"/>
</form>


<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);"
          action="ssadmin/roomChange/queryPage.html" method="post">
        <div class="searchBar">

            <table class="searchContent">
                <tr>
                    <td>片区名称：<input type="text" name="name" value="${name}"
                                   size="20"/>
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
            <shiro:hasPermission name="ssadmin/area/add.html">
                <li><a class="add"
                       href="ssadmin/goProtocolJSP.html?url=ssadmin/addArea"
                       height="300" width="700" target="dialog" rel="addProtocol"><span>新增</span>
                </a></li>
            </shiro:hasPermission>
            <!-- 修改 -->
            <shiro:hasPermission name="ssadmin/area/update.html">
                <li><a class="edit"
                       href="ssadmin/area/getRoomChangeById.html?url=ssadmin/updateRoomChange&id={sid_user}"
                       height="350" width="800" target="dialog" rel="updateRoomChange"><span>修改</span>
                </a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="ssadmin/area/enable.html">
                <li><a class="edit" href="ssadmin/area/enable.html?id={sid_user}" target="ajaxTodo" title="确定要启用吗?"><span>启用</span>
                </a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="ssadmin/area/disable.html">
                <li><a class="edit" href="ssadmin/area/disable.html?id={sid_user}" target="ajaxTodo" title="确定要禁用吗?"><span>禁用</span>
                </a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="ssadmin/area/delete.html">
                <li><a class="delete" href="ssadmin/area/delete.html" target="ajaxTodo" title="确定要删除吗?"><span>删除</span>
                </a></li>
            </shiro:hasPermission>
        </ul>
    </div>
    <table class="table" width="100%" layoutH="138">
        <thead>
        <tr>
            <th width="20">序号</th>
            <th width="80">片区名称</th>
            <th width="80">片区状态</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${areaList}" var="area" varStatus="num">
            <tr target="sid_user" rel="${area.id}">
                <td>${num.index +1}</td>
                <td>${area.name}</td>
                <td>${area.statusDesc}</td>
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
