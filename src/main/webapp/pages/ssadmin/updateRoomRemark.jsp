<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="comm/include.inc.jsp"%>
<h2 class="contentTitle">编辑房源备注</h2>
<div class="pageContent">
	
	<form method="post" action="ssadmin/roomChange/updateRemark.html" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">
			<dl>
				<dt>分配征收项目：</dt>
				<dd>
					${roomChange.assignedProject}
				</dd>
			</dl>
			<dl>
				<dt>提供房源平台：</dt>
				<dd>
					${roomChange.housingPlatform}
				</dd>
			</dl>
			<dl>
				<dt>房源项目：</dt>
				<dd>
					${roomChange.name}
				</dd>
			</dl>
			<dl>
				<dt>房号：</dt>
				<dd>
					${roomChange.number}
				</dd>
			</dl>
			<dl>
				<dt>预测面积：</dt>
				<dd>
					${roomChange.area}
				</dd>
			</dl>
			<dl>
				<dt>实测面积：</dt>
				<dd>
					${roomChange.realArea}
				</dd>
			</dl>
			<dl>
				<dt>单价：</dt>
				<dd>
					${roomChange.unitPrice}
				</dd>
			</dl>
			<dl>
				<dt>预测金额：</dt>
				<dd>
					${roomChange.totalPrice}
				</dd>
			</dl>
			<dl>
				<dt>实测金额：</dt>
				<dd>
					${roomChange.realTotalPrice}
				</dd>
			</dl>
			<dl>
				<dt>备注：</dt>
				<dd>
					<textarea name="remark" cols="55" rows="5">${roomChange.remark}</textarea>
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

