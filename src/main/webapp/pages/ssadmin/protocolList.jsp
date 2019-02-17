<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="comm/include.inc.jsp"%>
<form id="pagerForm" method="post" action="ssadmin/protocolList.html">
    <input type="hidden" name="status" value="${param.status}"> <input
        type="hidden" name="keywords" value="${keywords}" /> <input
        type="hidden" name="pageNum" value="${currentPage}" /> <input
        type="hidden" name="numPerPage" value="${numPerPage}" /> <input
        type="hidden" name="orderField" value="${param.orderField}" />
</form>


<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);"
          action="ssadmin/protocolList.html" method="post">
        <div class="searchBar">

            <table class="searchContent">
                <tr>
                    <td>KEY：<input type="text" name="keywords" value="${keywords}"
                                   size="60" />
                    </td>
                </tr>
            </table>
            <div class="subBar">
                <ul>
                    <li><div class="buttonActive">
                        <div class="buttonContent">
                            <button type="submit">查询</button>
                        </div>
                    </div></li>
                </ul>
            </div>
        </div>
    </form>
</div>
<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
            <shiro:hasPermission name="ssadmin/addSwapHouse.html">
                <li><a class="add"
                       href="ssadmin/goProtocolJSP.html?url=ssadmin/addSwapHouse&uid={sid_user}"
                       height="400" width="800" target="dialog" rel="updateSystemArgs"><span>新增产权调换协议</span>
                </a></li>
                <li><a class="edit"
                       href="ssadmin/goProtocolJSP.html?url=ssadmin/updateSwapHouse&uid={sid_user}"
                       height="400" width="800" target="dialog" rel="updateSystemArgs"><span>修改产权调换协议</span>
                </a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="ssadmin/addRmbRecompense.html">
                <li><a class="add"
                       href="ssadmin/goProtocolJSP.html?url=ssadmin/addRmbRecompense&uid={sid_user}"
                       height="400" width="800" target="dialog" rel="updateSystemArgs"><span>新增货币补偿协议</span>
                </a></li>
                <li><a class="edit"
                       href="ssadmin/goProtocolJSP.html?url=ssadmin/addSwapHouse&uid={sid_user}"
                       height="400" width="800" target="dialog" rel="updateSystemArgs"><span>修改货币补偿协议</span>
                </a></li>
            </shiro:hasPermission>

            <shiro:hasPermission name="ssadmin/addProtocolNotice.html">
                <li><a class="add"
                       href="ssadmin/goProtocolJSP.html?url=ssadmin/addSwapHouse&uid={sid_user}"
                       height="400" width="800" target="dialog" rel="updateSystemArgs"><span>新增手续通知单</span>
                </a></li>
                <li><a class="edit"
                       href="ssadmin/goProtocolJSP.html?url=ssadmin/addSwapHouse&uid={sid_user}"
                       height="400" width="800" target="dialog" rel="updateSystemArgs"><span>修改手续通知单</span>
                </a></li>
            </shiro:hasPermission>


            <shiro:hasPermission name="ssadmin/addSettleAccounts.html">
                <li><a class="add"
                       href="ssadmin/goProtocolJSP.html?url=ssadmin/addSwapHouse&uid={sid_user}"
                       height="400" width="800" target="dialog" rel="updateSystemArgs"><span>新增补偿资金结算单</span>
                </a></li>
                <li><a class="edit"
                       href="ssadmin/goProtocolJSP.html?url=ssadmin/addSwapHouse&uid={sid_user}"
                       height="400" width="800" target="dialog" rel="updateSystemArgs"><span>修改补偿资金结算单</span>
                </a></li>
            </shiro:hasPermission>
        </ul>
    </div>
    <table class="table" width="100%" layoutH="138">
        <thead>
        <tr>
            <th width="20">序号</th>
            <th width="60">被征收人</th>
            <th width="60">电话</th>
            <th width="60">状态</th>
            <th width="60">付(收)款手续通知单</th>
            <th width="60">货币补偿协议</th>
            <th width="60">产权调换协议</th>
            <th width="60">补偿资金结算单</th>
        </tr>
        </thead>
        <tbody>


        <tr target="sid_user" rel="1">
            <td width="20">1</td>
            <td width="60">张三</td>
            <td width="60">13075474895</td>
            <td width="60">已完成</td>
            <td width="60">√</td>
            <td width="60">√</td>
            <td width="60">√</td>
            <td width="60"></td>
        </tr>

        <tr target="sid_user" rel="2">
            <td width="20">2</td>
            <td width="60">李四</td>
            <td width="60">13075474895</td>
            <td width="60">已完成</td>
            <td width="60">√</td>
            <td width="60">√</td>
            <td width="60">√</td>
            <td width="60"></td>
        </tr>


        <tr target="sid_user" rel="3">
            <td width="20">1</td>
            <td width="60">王五</td>
            <td width="60">13077654895</td>
            <td width="60">未完成</td>
            <td width="60">√</td>
            <td width="60"></td>
            <td width="60">√</td>
            <td width="60"></td>
        </tr>



        <%--<c:forEach items="${systemArgsList}" var="systemArgs" varStatus="num">
            <tr target="sid_user" rel="${systemArgs.fid}">
                <td>${num.index +1}</td>
                <td>${systemArgs.fkey}</td>
                <td>${systemArgs.fvalue_s}</td>
                <td>${systemArgs.fdescription}</td>
            </tr>
        </c:forEach>--%>
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
