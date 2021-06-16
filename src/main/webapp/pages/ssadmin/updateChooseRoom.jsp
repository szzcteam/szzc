<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="comm/include.inc.jsp"%>
<h2 class="contentTitle">点房确认</h2>
<div class="pageContent">
	
	<form method="post" action="ssadmin/roomChange/${postUrl}.html" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
		<div class="pageFormContent" layoutH="97">
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
			<dl style="width: 100%;">
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
				<dt>点房人：</dt>
				<dd>
					<input type="text" name="choosePeople" value="${roomChange.choosePeople}" size="20" value=""/>
				</dd>
			</dl>
			<dl>
				<dt>身份证号码：</dt>
				<dd>
					<input type="text" name="idcard" value="${roomChange.idcard}" size="20" value=""/>
				</dd>
			</dl>
			<dl>
				<dt>点房状态：</dt>
				<dd>
					<select name="status" style="width: 150px;">
						<c:forEach items="${chooseStatusMap}" var="item">
							<option value="${item.key}" <c:if test="${roomChange.status ==  item.key}">selected</c:if>>${item.value}</option>
						</c:forEach>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>点房时间：</dt>
				<dd>
					<input type="text" name="chooseDate" class="date" value="${roomChange.chooseDateStr}" readonly="true" dateFmt="yyyy-MM-dd HH:mm:ss"/>
				</dd>
			</dl>
			<dl>
				<dt>代办公司：</dt>
				<dd>
					<input type="text" name="commissionCompany" value="${roomChange.commissionCompany}"/>
				</dd>
			</dl>
			<%--<dl>
				<dt>审计公司：</dt>
				<dd>
					<input type="text" name="auditFirm" value="${roomChange.auditFirm}"/>
				</dd>
			</dl>--%>
			<dl>
				<dt>审计公司：</dt>
				<dd>
					<select name="auditFirm" style="width: 150px;">
						<c:forEach items="${auditFirmList}" var="item">
							<option value="${item}" <c:if test="${roomChange.auditFirm ==  item}">selected</c:if>>${item}</option>
						</c:forEach>
					</select>
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

