<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../comm/include.inc.jsp" %>

<div class="pageContent" style="overflow:hidden;">
    <div style="width: 50%;float: left;">
        <fieldset>
            <legend style="color: green;">房源统计</legend>
            <!-- 房源统计 -->
            <table class="table" width="100%">
                <thead>
                <tr>
                    <th>项目名称</th>
                    <th>房源总数</th>
                    <th>空置数</th>
                    <th>已签数</th>
                    <th>预留数</th>
                    <th>作废数</th>
                </tr>
                </thead>
                <c:forEach items="${roomList}" var="room" varStatus="num">
                    <tr>
                        <td>${room.projectName}</td>
                        <td>${room.totalHouse}</td>
                        <td>${room.emptys}</td>
                        <td>${room.signed}</td>
                        <td>${room.prior}</td>
                        <td>${room.invalid}</td>
                    </tr>
                </c:forEach>
            </table>
        </fieldset>
    </div>
    <div style="width: 49%;float: left;">
    <!-- 签约状态统计 -->
        <fieldset>
            <legend style="color: #0086b3;">签约状态统计</legend>
            <table class="table" width="100%">
                <thead>
                <tr>
                    <th>片区名称</th>
                    <th>未签约(人/次)</th>
                    <th>已签约(人/次)</th>
                    <th>已过审(人/次)</th>
                </tr>
                </thead>
                <c:forEach items="${statusList}" var="status" varStatus="num">
                    <tr>
                        <td>${status.name}</td>
                        <td>${status.noSigned}</td>
                        <td>${status.completer}</td>
                        <td>${status.audit}</td>
                    </tr>
                </c:forEach>
            </table>
        </fieldset>
    </div>

        <fieldset style="padding-top: 10px;">
            <legend style="color: red">协议金额统计-（单位：万元）</legend>
            <table class="table" width="100%" >
                <thead>
                <tr>
                    <th style="width: 160px;">片区名称</th>
                    <th style="width: 120px;">房屋价值补偿</th>
                   <%-- <th>有证补偿</th>
                    <th>未登记合法建筑</th>
                    <th>历史遗留无证房</th>--%>
                    <th style="width: 120px;">装修折旧</th>
                    <th style="width: 100px;">搬家费</th>
                    <th style="width: 100px;">安置费</th>
                    <th style="width: 100px;">水表迁移</th>
                    <th style="width: 100px;">电表迁移</th>
                    <th style="width: 100px;">空调移机</th>
                    <th style="width: 100px;">热水器拆装</th>
                    <th style="width: 100px;">燃气拆装</th>
                    <th style="width: 100px;">构建物补偿</th>
                   <%-- <th>停产停业损失补助</th>--%>
                  <%--  <th>结构内阳台金额</th>
                    <th>外挑搭建金额</th>
                    <th>暗楼金额</th>
                    <th>夹层金额</th>
                    <th>楼顶搭建金额</th>
                    <th>附属金额</th>--%>
                    <th style="width: 120px;">住改商补助</th>
                    <th style="width: 100px;">货币补偿</th>
                    <th style="width: 120px;">小面积搬迁奖励</th>
                    <th style="width: 120px;">生活困难补助</th>
                    <th style="width: 100px;">搬迁奖励</th>
                    <th style="width: 100px;">保底补偿</th>
                    <th style="width: 120px;">建筑面积补助</th>
                    <th>其他</th>
                </tr>
                </thead>
                <c:forEach items="${moneyList}" var="protocol" varStatus="num">
                    <tr>
                        <td>${protocol.name}</td>
                        <td>${protocol.valueCompensateMoney}</td>
                        <td>${protocol.decorationCompensate}</td>
                        <td>${protocol.moveHouseFee}</td>
                        <td>${protocol.interimFee}</td>
                        <td>${protocol.moveWaterMeterFee}</td>
                        <td>${protocol.moveAmmeterFee}</td>
                        <td>${protocol.moveAirConditioningFee}</td>
                        <td>${protocol.hotWaterCompensate}</td>
                        <td>${protocol.gasFee}</td>
                        <td>${protocol.structureCompensate}</td>
                        <td>${protocol.changeCompensate}</td>
                        <td>${protocol.rmbCompensate}</td>
                        <td>${protocol.smallAreaReward}</td>
                        <td>${protocol.lifeCompensate}</td>
                        <td>${protocol.moveReward}</td>
                        <td>${protocol.guarantee}</td>
                        <td>${protocol.buildingAreaFee}</td>
                        <td>${protocol.otherRmb}</td>
                    </tr>
                </c:forEach>
            </table>
        </fieldset>

</div>