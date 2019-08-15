<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="comm/include.inc.jsp"%>
<h2 class="contentTitle">修改房源信息</h2>
<div class="pageContent">
	
	<form method="post" action="ssadmin/roomChange/updateRoomChangeById.html" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">
			<dl>
				<dt>分配征收项目：</dt>
				<dd>
					<input type="text" name="assignedProject" class="required" size="30" value="${roomChange.assignedProject}"/>
				</dd>
			</dl>
			<dl>
				<dt>提供房源平台：</dt>
				<dd>
					<input type="text" name="housingPlatform" class="required" size="30" value="${roomChange.housingPlatform}"/>
				</dd>
			</dl>
			<dl>
				<dt>房源项目：</dt>
				<dd>
					<input type="text" name="name" class="required" size="30" value="${roomChange.name}"/>
				</dd>
			</dl>
			<dl>
				<dt>房号：</dt>
				<dd>
					<input type="text" name="number" maxlength="120" class="required" size="30" value="${roomChange.number}"/>
				</dd>
			</dl>
			<dl>
				<dt>面积：</dt>
				<dd>
					<input type="text" name="area" maxlength="120" class="required" size="30" value="${roomChange.area}"/>
				</dd>
			</dl>
			<dl>
				<dt>单价：</dt>
				<dd>
					<input type="text" name="unitPrice" maxlength="120" class="required" size="30" value="${roomChange.unitPrice}"/>
				</dd>
			</dl>
			<dl>
				<dt>金额：</dt>
				<dd>
					<input type="text" name="totalPrice"  size="30" value="${roomChange.totalPrice}" readonly="readonly"/>
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<input type="hidden" name="id" value="${roomChange.id}"/>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>

<script>
	$(document).ready(function () {
		$("input[name='area'],input[name='unitPrice']").on("blur change", function () {
			var area = $("input[name='area']").eq(0).val() || 0;
			var unitPrice = $("input[name='unitPrice']").eq(0).val() || 0;
			console.log("房源计算金额");
			var calcText = area + "*" + unitPrice;
			var totalPrice = Math.round(eval(calcText));
			$("input[name='totalPrice']").eq(0).val(totalPrice);
		});
	});
</script>
