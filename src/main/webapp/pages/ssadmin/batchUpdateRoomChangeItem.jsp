<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="comm/include.inc.jsp"%>
<h2 class="contentTitle">批量修改房源项目</h2>
<div class="pageContent">

    <form method="post" action="ssadmin/roomChange/batchUpdateItem.html" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone)">
        <div class="pageFormContent nowrap" layoutH="97">
            <dl>
                <dt>项目信息：</dt>
                <dd>
                    <select name="itemCode" style="width: 150px;height: 22px;">
                        <c:forEach items="${projectMap}" var="item">
                            <option value="${item.key}">${item.value}</option>
                        </c:forEach>
                    </select>
                </dd>
            </dl>
        </div>
        <div class="formBar">
            <ul>
                <input type="hidden" name="ids" value="${ids}"/>
                <li><div class="buttonActive"><div class="buttonContent"><button type="submit" onclick="getids(this)">保存</button></div></div></li>
                <li><div class="button"><div class="buttonContent"><button type="button" class="close" id="room_change_close">取消</button></div></div></li>
            </ul>
        </div>
    </form>

</div>



