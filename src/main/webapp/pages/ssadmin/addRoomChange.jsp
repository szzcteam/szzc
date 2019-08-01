<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="comm/include.inc.jsp"%>
<h2 class="contentTitle">上传</h2>


<div class="pageContent">
	
	<form method="post" action="ssadmin/updateSecurity.html" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">
			<dl>
				<dt>房源excel文件：</dt>
				<dd>
					<input type="file" name="file" id="file" size="30" />
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="save()">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close" id="room_change_close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>

<script type="text/javascript" src="${oss_url}/static/ssadmin/js/ajaxfileupload.js"></script>
<script type="text/javascript">
//上传
function save(){
	var fileId = "file";
	var filePath = $("#file").val();
	if (!filePath) {
		alert("请选择要上传的房源文件");
		return;
	}
	var extStart = filePath.lastIndexOf(".");
	var ext = filePath.substring(extStart, filePath.length).toLowerCase();
	console.log("文件后缀:" + ext);
	if (ext != ".xls" && ext != ".xlsx") {
		alert("文件格式错误，仅支持excel文件");
		return;
	}

	$.ajaxFileUpload({
		url: "/ssadmin/roomChange/importExcel.html",
		type: "post",
		data: {
			"code": "0"
		},
		secureuri: false,
		fileElementId: fileId,
		dataType: "json",
		success: function (data, status) {
			console.log(data);
			if (data.statusCode == 200) {
				//关闭框
				$("#room_change_close").click();
				alert("上传成功");
			}else{
				alert("上传失败");
			}
		},
		error: function (data, status, e) {
			alert("上传错误");
		}
	});

}

</script>
