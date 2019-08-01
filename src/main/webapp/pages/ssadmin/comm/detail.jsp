<%@ page pageEncoding="UTF-8"%>
<div class="accordion" fillSpace="sidebar">


	<shiro:hasPermission name="dataManage">
		<div class="accordionHeader">
			<h2>
				<span>Folder</span>征收资料管理
			</h2>
		</div>
		<div class="accordionContent">
			<ul class="tree treeFolder">
				<shiro:hasPermission name="/ssadmin/protocolList.html">
					<li><a href="/ssadmin/protocolList.html" target="navTab"
						   rel="coinSurveyList">农讲所片协议管理</a></li>
				</shiro:hasPermission>
				<shiro:hasPermission name="/ssadmin/roomChange/queryPage.html">
					<li><a href="/ssadmin/roomChange/queryPage.html" target="navTab"
						   rel="roomChange">房源管理</a></li>
				</shiro:hasPermission>
			</ul>
		</div>
	</shiro:hasPermission>

	<shiro:hasPermission name="system">
		<div class="accordionHeader">
			<h2>
				<span>Folder</span>系统管理
			</h2>
		</div>
		<div class="accordionContent">
			<ul class="tree treeFolder">
				<shiro:hasPermission name="ssadmin/systemArgsList.html">
					<li><a href="ssadmin/systemArgsList.html" target="navTab"
						rel="systemArgsList">系统参数列表</a></li>
				</shiro:hasPermission>
				<shiro:hasPermission name="ssadmin/securityList.html">
					<li><a
						href="ssadmin/goSecurityJSP.html?url=ssadmin/securityTreeList&treeId=1"
						target="navTab" rel="securityTreeList">权限列表</a>
					</li>
				</shiro:hasPermission>
				<shiro:hasPermission name="ssadmin/roleList.html">
					<li><a href="ssadmin/roleList.html" target="navTab"
						rel="roleList">角色列表</a>
					</li>
				</shiro:hasPermission>
				<shiro:hasPermission name="ssadmin/adminList.html">
					<li><a href="ssadmin/adminList.html" target="navTab"
						rel="adminList">管理员列表</a></li>
				</shiro:hasPermission>

				<shiro:hasPermission name="ssadmin/sysOperatorlog.html">
					<li><a href="ssadmin/sysOperatorlog.html" target="navTab"
						   rel="sysOperatorlog">后台操作日志</a></li>
				</shiro:hasPermission>

			</ul>
		</div>
	</shiro:hasPermission>

</div>