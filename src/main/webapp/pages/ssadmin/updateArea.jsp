<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="comm/include.inc.jsp"%>
<script type="text/javascript" src="${oss_url}/static/ssadmin/js/business/add_area.js"/>
<h2 class="contentTitle">修改片区及权限</h2>


<div class="pageContent">

	<form method="post" action="ssadmin/area/update.html"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this,dialogAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">
			<dl>
				<dt>片区名称：</dt>
				<dd>
					<input type="text" name="name" maxlength="20" value="${area.name}"
						class="required" size="50" />
				</dd>
			</dl>
			<dl>
				<dt>总户数：</dt>
				<dd>
					<input type="text" name="totalFamily" maxlength="5" value="${area.totalFamily}"
						   class="required number" size="20" placeholder="未知或不确定则填写0"/>
				</dd>
			</dl>
			<dl>
				<dt>管辖项目：</dt>
				<dd>
					<select name="projectCode" style="width: 200px;height: 22px;">
						<c:forEach items="${projectMap}" var="item">
							<option value="${item.key}" <c:if test="${item.key == area.projectCode }">selected="selected"</c:if>>${item.value}</option>
						</c:forEach>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>片区下的角色：</dt>
				<dd>
					<c:forEach items="${roleList}" var="role">
						<div style="width: 200px;height: 26px;word-break: break-word; float:left;">
							<input type="checkbox" value="${role.fid}" name="roleCbx" <c:if test="${role.selFlag == true }">checked="checked"</c:if>/>${role.fname}&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
					</c:forEach>
				</dd>
			</dl>

		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<input type="hidden" name="roleIds" value="${roleIds}">
							<input type="hidden" name="id" value="${area.id}">
							<button type="submit">保存</button>
						</div>
					</div>
				</li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>

</div>


