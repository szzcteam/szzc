<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="comm/include.inc.jsp" %>
<form id="pagerForm" method="post" action="ssadmin/roomChange/queryPage.html">
    <input type="hidden" name="name" value="${name}"/>
    <input type="hidden" name="number" value="${number}"/>
    <input type="hidden" name="choosePeople" value="${choosePeople}"/>
    <input type="hidden" name="assignedProject" value="${assignedProject}"/>
    <input type="hidden" name="status" value="${status}"/>
    <input type="hidden" name="startDate" value="${startDate}"/>
    <input type="hidden" name="endDate" value="${endDate}"/>
    <input type="hidden" name="minArea" value="${minArea}"/>
    <input type="hidden" name="maxArea" value="${maxArea}"/>

    <input type="hidden" name="housingPlatform" value="${housingPlatform}"/>
    <input type="hidden" name="commissionCompany" value="${commissionCompany}"/>


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
                                    size="20"/>
                    </td>
                    <td>点房人：<input type="text" name="choosePeople" value="${choosePeople}"
                                    size="20"/>
                    </td>
                    <td>分配征收项目：<input type="text" name="assignedProject" value="${assignedProject}"
                                      size="20"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        点房状态：
                        <select name="status" style="width: 150px;">
                            <option value="">全部</option>
                            <c:forEach items="${chooseStatusMap}" var="item">
                                <option value="${item.key}" <c:if test="${status ==  item.key}">selected</c:if>>${item.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>点房时间：<input type="text" name="startDate" class="date"
                                    readonly="true" value="${startDate }"/>
                    </td>
                    <td>
                        至&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="text" name="endDate" class="date"
                               readonly="true" value="${endDate }"/>
                    </td>
                    <td>面积：<input type="text" name="minArea"
                                 size="10"   value="${minArea }"/>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="text" name="maxArea"
                               size="10"  value="${maxArea }"/>
                    </td>
                </tr>
                <tr>
                    <td>提供房源平台：<input type="text" name="housingPlatform" value="${housingPlatform}"
                                      size="20"/>
                    </td>
                    <td>代办公司：<input type="text" name="commissionCompany" value="${commissionCompany}"
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
            <shiro:hasPermission name="ssadmin/roomChange/importExcel.html">
                <li><a class="edit"
                       href="ssadmin/roomChange/download.html"
                       target="dwzExport" postType="navTab"><span>下载模板</span>
                </a></li>
                <li><a class="add"
                       href="ssadmin/goPageJsp.html?url=ssadmin/addRoomChange"
                       height="300" width="700" target="dialog" rel="addProtocol"><span>上传房源</span>
                </a></li>
            </shiro:hasPermission>
            <!-- 修改 -->
            <shiro:hasPermission name="ssadmin/roomChange/updateRoomChangeById.html">
                <li><a class="edit"
                       href="ssadmin/roomChange/getRoomChangeById.html?url=ssadmin/updateRoomChange&id={sid_user}"
                       height="350" width="800" target="dialog" rel="updateRoomChange"><span>修改房源信息</span>
                </a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="ssadmin/roomChange/addChooseRoom.html">
                <li><a class="edit"
                       href="ssadmin/roomChange/toChooseRoomPage.html?url=ssadmin/updateChooseRoom&id={sid_user}&postUrl=add"
                       height="350" width="800" target="dialog" rel="updateRoomChange"><span>新增点房</span>
                </a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="ssadmin/roomChange/updateChooseRoom.html">
                <li><a class="edit"
                       href="ssadmin/roomChange/toChooseRoomPage.html?url=ssadmin/updateChooseRoom&id={sid_user}&postUrl=update"
                       height="350" width="800" target="dialog" rel="updateRoomChange"><span>修改点房</span>
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
            <th width="50">点房时间</th>
            <th width="50">代办公司</th>
            <th width="50">状态</th>
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
                <td>${room.area}</td>
                <td>${room.unitPrice}</td>
                <td>${room.totalPrice}</td>
                <td>${room.choosePeople}</td>
                <td>${room.chooseDateStr}</td>
                <td>${room.commissionCompany}</td>
                <td>${room.statusDesc}</td>
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
