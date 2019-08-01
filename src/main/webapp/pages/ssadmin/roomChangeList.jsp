<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="comm/include.inc.jsp" %>
<form id="pagerForm" method="post" action="ssadmin/roomChange/queryPage.html">
    <input type="hidden" name="keywords" value="${keywords}"/>
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
                    <td>房源项目：<input type="text" name="keywords" value="${keywords}"
                                   size="60"/>
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
            <shiro:hasPermission name="ssadmin/addProtocol.html">
                <li><a class="add"
                       href="ssadmin/goProtocolJSP.html?url=ssadmin/addHouseResource"
                       height="350" width="700" target="dialog" rel="addProtocol"><span>新增</span>
                </a></li>
            </shiro:hasPermission>
            <!-- 修改 -->
            <shiro:hasPermission name="ssadmin/updateProtocol.html">
                <li><a class="edit"
                       href="ssadmin/goProtocolJSP.html?url=ssadmin/updateHouseResource&idMore={sid_user}"
                       height="350" width="700" target="dialog" rel="updateHouseResource"><span>修改</span>
                </a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="ssadmin/deleteProtocol.html">
                <li><a class="delete"
                       href="ssadmin/deleteSwapHouse.html?idMore={sid_user}"
                       target="selectedTodo" rel="ids" postType="string"
                       title="确定要删除吗?"><span>删除</span>
                </a></li>
            </shiro:hasPermission>
        </ul>
    </div>
    <table class="table" width="100%" layoutH="138">
        <thead>
        <tr>
            <th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
            <th width="20">序号</th>
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
            <tr target="sid_user" rel="1">
                <td><input name="ids" value="" type="checkbox"></td>
                <td>${num.index +1}</td>
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
