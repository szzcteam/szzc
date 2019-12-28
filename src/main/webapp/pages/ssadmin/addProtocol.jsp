<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="comm/include.inc.jsp" %>
<div class="pageContent" style="overflow-x: hidden;">
    <div style="margin-bottom: 10px;padding-left: 10px;">
        协议类型：&nbsp;&nbsp;&nbsp;&nbsp;
        <select id="protocolType" onchange="changeProtocolType(this.value)">
            <option value="">请选择</option>
            <option value="1" selected>结算单</option>
            <option value="2">货币补偿协议</option>
            <option value="3">产权调换协议</option>
        </select>
    </div>
    <div id="businessPage">
        <c:choose>
            <c:when test="${projectCode != null && projectCode == 'B001001' }">
                <div id="div_settleAccounts" style="display: none;"><%@ include file="addSettleAccounts_zyc.jsp" %></div>
            </c:when>
            <c:when test="${projectCode != null && projectCode == 'B001002' }">
                <div id="div_settleAccounts" style="display: none;"><%@ include file="addSettleAccounts_xch.jsp" %></div>
            </c:when>
            <c:when test="${projectCode != null && projectCode == 'C001001' }">
                <div id="div_settleAccounts" style="display: none;"><%@ include file="addSettleAccounts_drnc.jsp" %></div>
            </c:when>
            <c:otherwise>
                <div id="div_settleAccounts" style="display: none;"><%@ include file="addSettleAccounts.jsp" %></div>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${projectCode != null && projectCode == 'C001001' }">
                <div id="div_rmbRecompense" style="display: none;"><%@ include file="addRmbRecompense_drnc.jsp" %></div>
            </c:when>
            <c:otherwise>
                <div id="div_rmbRecompense" style="display: none;"><%@ include file="addRmbRecompense.jsp" %></div>
            </c:otherwise>
        </c:choose>
        <div id="div_swapHouse" style="display: none;"><%@ include file="addSwapHouse.jsp" %></div>
    </div>
</div>

<script type="text/javascript">
    function changeProtocolType(type){
        if(type == 1) {
            $("#div_settleAccounts").css("display", "block")
            $("#div_rmbRecompense").css("display", "none")
            $("#div_swapHouse").css("display", "none")
        }else if(type == 2) {
            $("#div_settleAccounts").css("display", "none")
            $("#div_rmbRecompense").css("display", "block")
            $("#div_swapHouse").css("display", "none")
        }else if(type == 3) {
            $("#div_settleAccounts").css("display", "none")
            $("#div_rmbRecompense").css("display", "none")
            $("#div_swapHouse").css("display", "block")
        }else{
            $("#div_settleAccounts").css("display", "none")
            $("#div_rmbRecompense").css("display", "none")
            $("#div_swapHouse").css("display", "none")
        }
    }
    changeProtocolType(1);
</script>