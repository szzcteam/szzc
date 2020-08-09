<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="comm/include.inc.jsp"%>
<h2 class="contentTitle">编辑备注</h2>
<div class="pageContent">
	
	<form method="post" action="ssadmin/settleAccounts/updateRemark.html" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">
			<dl>
				<dt>被征收人：</dt>
				<dd>
					${houseOwner}
				</dd>
			</dl>
			<dl>
				<dt>被征收房屋地址：</dt>
				<dd>
					${settleAccounts.address}
				</dd>
			</dl>
			<dl>
				<dt>协议编号：</dt>
				<dd>
					${settleAccounts.cardNo}
				</dd>
			</dl>
			<dl>
				<dt>备注：</dt>
				<dd>
					<textarea name="remark" maxlength="255"  rows="5" cols="40">${remark}</textarea>
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<input type="hidden" name="id" value="${settleAccounts.id}"/>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>

