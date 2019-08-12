<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="comm/include.inc.jsp"%>
<h2 class="contentTitle">上传</h2>


<div class="pageContent">
	
	<form method="post" action="ssadmin/roomChange/importExcel.html" class="pageForm required-validate"
		  enctype="multipart/form-data" onsubmit="return iframeCallback(this,dialogAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">
			<dl>
				<dt>房源excel文件：</dt>
				<dd>
					<input type="file" name="file" id="file" class="inputStyle" />
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close" id="room_change_close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>


