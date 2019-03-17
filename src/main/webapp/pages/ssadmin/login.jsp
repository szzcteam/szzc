<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="comm/include.inc.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${requestScope.constant['webinfo'].ftitle }</title>
<link href="${oss_url}/static/ssadmin/js/themes/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
<c:if test="${error != null}">
alert("${error}") ;
</c:if>

	//模拟结算
	function simulate(area) {
		window.open("/front/simulate.html?area="+area)
	}

</script>
</head>
<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<a href="/" style="font-size: 36px;">协议管理<%--<img src="${oss_url}/static/ssadmin/js/themes/default/images/login_logo.gif" />--%></a>
			</h1>
			<div class="login_headerContent">
				<div class="navList">

				</div>
				<h2 class="login_title" style="padding: 0px;text-align: center;"><img src="${oss_url}/static/ssadmin/js/themes/default/images/login_title.png" style="width: 96px;height: 96px;"/></h2>
			</div>
		</div>
		<script type="text/javascript">
			function validate(f){
				f.src = "/servlet/ValidateImageServlet?"+Math.random() ;
			}
		</script>
		<div id="login_content">
			<div class="loginForm">
				<form action="/ssadmin/95afee23-e2ab-11e6-bddf-005056ab18e8.html" method="post">
					<p>
						<label>用户名：</label>
						<input type="text" name="name" size="20" class="login_input" value="luwei"/>
					</p>
					<p>
						<label>密码：</label>
						<input type="password" name="password" size="20" class="login_input" value="luwei"/>
					</p>
					<p>
						<label>验证码：</label>
						<input class="code" type="text" size="5" name="vcode" />
						<span><img src="/servlet/ValidateImageServlet" alt="" width="75" height="24" onclick="validate(this);"/></span>
					</p>
					<div class="login_bar">
						<input class="sub" type="submit" value=" " />
					</div>
				</form>
			</div>
			<div class="login_banner"><img src="${oss_url}/static/ssadmin/js/themes/default/images/login_banner.jpg" /></div>
			<div class="login_main">
				<ul class="helpList">

				</ul>
				<div class="login_inner">
					<p>HTTPS高级安全加密协议，客户资料全加密传输，防止通过网络泄漏 ……</p>
					<p>模拟结算： <select id="pro_select" onchange="simulate(this.value)">
						<option value="">请选择</option>
						<option value="1">农讲所片区结算</option>
					</select></p>
				</div>
			</div>
		</div>
		<div id="login_footer">
			${requestScope.constant['webinfo'].fcopyRights }
		</div>
	</div>
</body>
</html>
