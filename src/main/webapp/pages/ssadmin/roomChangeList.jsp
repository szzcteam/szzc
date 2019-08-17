<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="comm/include.inc.jsp" %>
<form id="pagerForm" method="post" action="ssadmin/roomChange/queryPage.html">
    <input type="hidden" name="name" value="${name}"/>
    <input type="hidden" name="number" value="${number}"/>
    <input type="hidden" name="choosePeople" value="${choosePeople}"/>
    <input type="hidden" name="assignedProject" value="${assignedProject}"/>
    <input type="hidden" name="housingPlatform" value="${housingPlatform}"/>

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
                    <td>房源项目：<input type="text" name="name" value="${name}"
                                   size="20"/>
                    </td>
                    <td>房号：<input type="text" name="number" value="${number}"
                                    size="10"/>
                    </td>
                    <td>点房人：<input type="text" name="choosePeople" value="${choosePeople}"
                                    size="10"/>
                    </td>
                    <td>分配征收项目：<input type="text" name="assignedProject" value="${assignedProject}"
                                      size="20"/>
                    </td>
                    <td>提供房源平台：<input type="text" name="housingPlatform" value="${housingPlatform}"
                                      size="20"/>
                    </td>
                </tr>
            </table>
            <div class="subBar">
                <ul>
                    <li>
                        <div class="buttonActive">
                            <div class="buttonContent">
                                <button type="submit">查询</button>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </form>
</div>
<div class="pageContent">
    <div class="panelBar" style="height: 30px;">
        <ul class="toolBar">
            <!-- 新增 -->
            <shiro:hasPermission name="ssadmin/roomChange/importExcel.html">
                <li><a class="edit"
                       href="ssadmin/roomChange/download.html"
                       target="dwzExport" postType="navTab"><span>下载模板</span>
                </a></li>
                <li><a class="add"
                       href="ssadmin/goProtocolJSP.html?url=ssadmin/addRoomChange"
                       height="300" width="700" target="dialog" rel="addProtocol"><span>上传房源</span>
                </a></li>
            </shiro:hasPermission>
            <!-- 修改 -->
            <shiro:hasPermission name="ssadmin/roomChange/updateRoomChangeById.html">
                <li><a class="edit"
                       href="ssadmin/roomChange/getRoomChangeById.html?url=ssadmin/updateRoomChange&id={sid_user}"
                       height="350" width="800" target="dialog" rel="updateRoomChange"><span>修改</span>
                </a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="ssadmin/roomChange/batchDelete.html">
                <li><a class="delete"
                       href="ssadmin/roomChange/batchDelete.html"
                       target="selectedTodo" rel="ids" postType="string"
                       title="确定要删除吗?"><span>批量删除</span>
                </a></li>
            </shiro:hasPermission>
        </ul>
    </div>
    <table class="table" width="100%" layoutH="138">
        <thead>
        <tr>
            <th width="15"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
            <th width="20">序号</th>
            <th width="80">分配征收项目</th>
            <th width="80">提供房源平台</th>
            <th width="60">房源项目</th>
            <th width="60">房号</th>
            <th width="60">面积&nbsp;(M<sup>2</sup>)</th>
            <th width="50">单价&nbsp;(元/M<sup>2</sup>)</th>
            <th width="50">总价&nbsp;(元)</th>
            <th width="50">点房人</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${roomList}" var="room" varStatus="num">
            <tr target="sid_user" rel="${room.id}">
                <td><input name="ids" value="${room.id}" type="checkbox"></td>
                <td>${num.index +1}</td>
                <td>${room.assignedProject}</td>
                <td>${room.housingPlatform}</td>
                <td>${room.name}</td>
                <td>${room.number}</td>
                <td>${room.unitPrice}</td>
                <td>${room.area}</td>
                <td>${room.totalPrice}</td>
                <td>${room.choosePeople}</td>
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
