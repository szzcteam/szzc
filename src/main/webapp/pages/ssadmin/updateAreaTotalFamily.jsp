<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="comm/include.inc.jsp"%>
<script type="text/javascript" src="${oss_url}/static/ssadmin/js/business/add_area.js"/>
<h2 class="contentTitle">片区总户数信息</h2>


<div class="pageContent">

	<form method="post" action="ssadmin/area/update-total-family.html"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this,dialogAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">
			<dl>
				<dt>片区名称：</dt>
				<dd style="width: 300px;">
					${area.name}
				</dd>
			</dl>
			<dl>
				<dt>总户数：</dt>
				<dd style="width: 300px;">
					<input type="text" name="totalFamily" maxlength="5" value="${area.totalFamily}"
						   class="required number" size="20" placeholder="未知或不确定则填写0"/>
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
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


