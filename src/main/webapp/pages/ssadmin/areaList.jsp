<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="comm/include.inc.jsp" %>
<form id="pagerForm" method="post" action="ssadmin/area/queryPage.html">
    <input type="hidden" name="name" value="${name}"/>

    <input type="hidden" name="pageNum" value="${currentPage}"/>
    <input type="hidden" name="numPerPage" value="${numPerPage}"/>
    <input type="hidden" name="orderField" value="${param.orderField}"/>
</form>


<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);"
          action="ssadmin/area/queryPage.html" method="post">
        <div class="searchBar">

            <table class="searchContent">
                <tr>
                    <td>片区名称：<input type="text" name="name" value="${name}"
                                   size="40"/>
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
                       href="ssadmin/area/initAdd.html?url=ssadmin/addArea"
                       height="400" width="700" target="dialog" rel="addProtocol"><span>新增</span>
                </a></li>
            </shiro:hasPermission>
            <!-- 修改 -->
            <shiro:hasPermission name="ssadmin/area/update.html">
                <li><a class="edit"
                       href="ssadmin/area/initUpdate.html?url=ssadmin/updateArea&id={sid_user}"
                       height="350" width="800" target="dialog" rel="updateRoomChange"><span>修改</span>
                </a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="ssadmin/area/enable.html">
                <li><a class="edit" href="ssadmin/area/updateStatus.html?id={sid_user}&status=0" target="ajaxTodo" title="确定要启用吗?"><span>启用</span>
                </a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="ssadmin/area/disable.html">
                <li><a class="edit" href="ssadmin/area/updateStatus.html?id={sid_user}&status=1" target="ajaxTodo" title="确定要禁用吗?"><span>禁用</span>
                </a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="ssadmin/area/delete.html">
                <li><a class="delete" href="ssadmin/area/delete.html?id={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span>
                </a></li>
            </shiro:hasPermission>
        </ul>
    </div>
    <table class="table" width="100%" layoutH="138">
        <thead>
        <tr>
            <th width="30">序号</th>
            <th>片区名称</th>
            <th>片区状态</th>
            <th>最后一次操作时间</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${areaList}" var="area" varStatus="num">
            <tr target="sid_user" rel="${area.id}">
                <td>${num.index +1}</td>
                <td>${area.name}</td>
                <td>${area.statusDesc}</td>
                <td>${area.modifiedDateStr}</td>
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
