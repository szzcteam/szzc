<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="comm/include.inc.jsp"%>
<h2 class="contentTitle">修改<font color="red">${security.fname}</font></h2>


<div class="pageContent">
	
	<form method="post" action="ssadmin/updateSecurity.html" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">
			<dl>
				<dt>房源项目：</dt>
				<dd>
					<input type="text" name="fname" maxlength="20" class="required" size="30" value="白沙洲花园"/>
				</dd>
			</dl>
			<dl>
				<dt>房号：</dt>
				<dd>
					<input type="text" name="furl" maxlength="120" class="required" size="30" value="G8-1-1504"/>
				</dd>
			</dl>
			<dl>
				<dt>面积：</dt>
				<dd>
					<input type="text" name="fdescription" maxlength="120" class="required" size="30" value="44"/>
				</dd>
			</dl>
			<dl>
				<dt>单价：</dt>
				<dd>
					<input type="text" name="fdescription" maxlength="120" class="required" size="30" value="4324324"/>
				</dd>
			</dl>
			<dl>
				<dt>面积：</dt>
				<dd>
					<input type="text" name="fdescription"  size="30" value="4324324" disabled="disabled"/>
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>


<script type="text/javascript">
function customvalidXxx(element){
	if ($(element).val() == "xxx") return false;
	return true;
}
</script>
