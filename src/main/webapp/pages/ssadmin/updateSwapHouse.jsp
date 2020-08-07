<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="comm/include.inc.jsp" %>
<h2 class="contentTitle">修改产权调换协议</h2>

<script type="text/javascript" src="${oss_url}/static/ssadmin/js/business/add_swapHouse.js"/>
<style>
    .samll_input_text {
        width: 50px;
    }
</style>

<div class="pageContent" id="swapHouseDiv">

    <form method="post" action="ssadmin/updateSwapHouse.html"
          class="pageForm required-validate" enctype="multipart/form-data"
          onsubmit="return iframeCallback(this, dialogAjaxDone);">

        <div class="pageFormContent" layoutH="97">
            <fieldset>
                <legend>协议信息</legend>
                <dl>
                    <dt>编号：</dt>
                    <dd>
                        <input type="text" name="cardNo" value="${swapHouse.cardNo}" maxlength="50" size="20" style="margin-left: 0px;"/>
                    </dd>
                </dl>
               <%-- <dl>
                    <dt>征收部门(甲方):</dt>
                    <dd>
                        <input type="text" name="mngOffice" value="${swapHouse.mngOffice}" maxlength="255" size="40" style="margin-left: 0px;"/>
                    </dd>
                </dl>--%>
                <dl>
                    <dt>代办单位:</dt>
                    <dd>
                        <input type="text" name="agencyCompany" value="${swapHouse.agencyCompany}" maxlength="255" size="40" style="margin-left: 0px;"/>
                    </dd>
                </dl>
                <dl>
                    <dt>被征收人(乙方):</dt>
                    <dd>
                        <input type="text" name="houseOwner" value="${swapHouse.houseOwner}" maxlength="50" size="40" style="margin-left: 0px;"/>
                        <input type="hidden" name="isLesseeFlag" value="${swapHouse.isLesseeFlag}"/>
                    </dd>
                </dl>
                <dl>
                    <dt>身份证号:</dt>
                    <dd>
                        <input type="text" name="identityNo" value="${swapHouse.identityNo}" maxlength="255" size="40" style="margin-left: 0px;"/>
                    </dd>
                </dl>
                <dl>
                    <dt>所属片区:</dt>
                    <dt>
                        <select name="areaId" style="width: 170px;height: 21px;">
                            <option value="">请选择</option>
                            <c:forEach items="${areaList}" var="area">
                                <option value="${area.id}" <c:if test="${swapHouse.areaId==area.id }">selected="selected"</c:if>>${area.name}</option>
                            </c:forEach>
                        </select>
                    </dt>
                </dl>
            </fieldset>
            <br/><br/>
            <fieldset>
                <legend>诀字信息</legend>
                <dl style="width: 100%;height: 26px;">
                    <dd style="width: 95%;line-height: 26px;">
                        人民政府于
                        <input type="text" name="govYear" value="${adjudication.govYear}"  class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>年
                        <input type="text" name="govMonth" value="${adjudication.govMonth}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>月
                        <input type="text" name="govDay"  value="${adjudication.govDay}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>日作出《房屋征收决定》
                        （武昌征决字[<input type="text" name="adjuLetter" value="${adjudication.adjuLetter}"  class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>]
                        第<input type="text" name="adjuNum"  value="${adjudication.adjuNum}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>号），并于
                        <input type="text" name="noticeYear" value="${adjudication.noticeYear}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>年
                        <input type="text" name="noticeMonth" value="${adjudication.noticeMonth}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>月
                        <input type="text" name="noticeDay"  value="${adjudication.noticeDay}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>日公告
                    </dd>
                </dl>
            </fieldset>
            <br/><br/>
            <fieldset>
                <legend>被征收房屋情况</legend>
                <dl style="width: 100%;height: 75px;">
                    <dd style="width: 95%;line-height: 26px;">
                        1、被征收房屋地址：<input type="text" name="address" value="${swapHouse.address}" maxlength="255"  size="40" style="margin-left: 0px;clear: both;float: none;"/>、
                        证载建筑面积：<input type="text" name="certifiedArea" value="${swapHouse.certifiedArea}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;M<sup>2</sup>、
                        房屋权属份额：<input type="text" name="proportion" value="${swapHouse.proportion}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;%、
                        证载房屋用途：<input type="text" name="useing" value="${swapHouse.useing}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>、
                        房屋所有权证号：<input type="text" name="houseOwnerNumber" value="${swapHouse.houseOwnerNumber}" style="margin-left: 0px;clear: both;float: none;width: 180px;"/>、
                        国有土地使用权证号：<input type="text" name="publicOwnerNumber"  value="${swapHouse.publicOwnerNumber}" style="margin-left: 0px;clear: both;float: none;width: 180px;"/>、
                        房屋价值评估单价：<input type="text" name="assessPrice"   value="${swapHouse.assessPrice}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>、
                        用于经营的实际面积：<input type="text" name="valueCompensateBusinessArea"  value="${swapHouse.valueCompensateBusinessArea}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;M<sup>2</sup>、
                        住宅改为经营门面补助系数：<input type="text" name="valueCompensateRate"  value="${swapHouse.valueCompensateRate}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;%
                    </dd>
                </dl>
                <dl style="width: 100%;height: 52px;">
                    <dd style="width: 95%;line-height: 26px;">
                        2、未登记的合法建筑面积：<input type="text" name="noRegisterLegalArea" value="${swapHouse.noRegisterLegalArea}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;M<sup>2</sup>、
                        房屋用途：<input type="text" name="noRegisterUseing" value="${swapHouse.noRegisterUseing}"  class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>、
                        房屋评估单价：<input type="text" name="noRegisterAssessPrice" value="${swapHouse.noRegisterAssessPrice}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;元、
                        补偿比例：<input type="text" name="noRegisterProportion" value="${swapHouse.noRegisterProportion}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;%、
                        经营面积：<input type="text" name="noRegisterBusinessArea" value="${swapHouse.noRegisterBusinessArea}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;M<sup>2</sup>、
                        住宅改为经营门面补助系数：<input type="text" name="noRegisterRate" value="${swapHouse.noRegisterRate}"  class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;%
                    </dd>
                </dl>
                <dl style="width: 100%;height: 52px;">
                    <dd style="width: 95%;line-height: 26px;">
                        3、历史遗留无证房面积：<input type="text" name="historyLegacyArea" value="${swapHouse.historyLegacyArea}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;M<sup>2</sup>、
                        房屋用途：<input type="text" name="historyUseing" value="${swapHouse.historyUseing}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>、
                        房屋评估单价：<input type="text" name="historyAssessPrice" value="${swapHouse.historyAssessPrice}" class="samll_input_text"  style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;元、
                        补偿比例：<input type="text" name="historyProportion" value="${swapHouse.historyProportion}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;%、
                        经营面积： <input type="text" name="historyBusinessArea" value="${swapHouse.historyBusinessArea}"  class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;M<sup>2</sup>、
                        住宅改为经营门面补助系数： <input type="text" name="historyRate" value="${swapHouse.historyRate}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;%
                    </dd>
                </dl>
            </fieldset>
            <br/><br/>
            <fieldset>
                <legend>征收补偿款项</legend>
                <dl>
                    <dt>1、有证房屋补偿金额:</dt>
                    <dd>
                        <input type="text" name="valueCompensate" value="${swapHouse.valueCompensate}" size="20" style="margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>未登记的合法建筑补偿金额:</dt>
                    <dd>
                        <input type="text" name="noRegisterLegal" value="${swapHouse.noRegisterLegal}"  size="20" style="margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl style="width: 100%;">
                    <dt>历史遗留无证房屋补偿金额:</dt>
                    <dd>
                        <input type="text" name="historyLegacy" value="${swapHouse.historyLegacy}" size="20" style="margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>2、装修补偿:</dt>
                    <dd style="width: 300px;">
                        单价：<input type="text" name="decorationCompensateUnitPrice" value="${swapHouse.decorationCompensateUnitPrice}"  class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;元&nbsp;&nbsp;&nbsp;&nbsp;
                        总价：<input type="text" name="decorationCompensate" value="${swapHouse.decorationCompensate}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>3、搬迁补偿(搬家费):</dt>
                    <dd>
                        <input type="text" name="moveHouseFee" value="${swapHouse.moveHouseFee}" size="20" style="margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>4、临时安置补偿:</dt>
                    <dd style="width: 300px;">
                        <%--  <input type="text" name="interimArea" maxlength="50" class="samll_input_text" style="clear: both;float: none;"/>&nbsp;&nbsp;M<sup>2</sup>&nbsp;&nbsp;×&nbsp;&nbsp;--%>
                        标准：<input type="text" name="interimPrice" value="${swapHouse.interimPrice}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元/M<sup>2</sup>&nbsp;&nbsp;&nbsp;&nbsp;
                        <%--<input type="text" name="interimMonth" maxlength="50" class="samll_input_text" style="clear: both;float: none;"/>&nbsp;&nbsp;个月--%>
                        总额：<input type="text" name="interimFee" value="${swapHouse.interimFee}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>5、保底面积补偿:</dt>
                    <dd>
                        <input type="text" name="guarantee" value="${swapHouse.guarantee}" size="20" style="margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl style="width: 100%;">
                    <dt>6、停产停业损失补偿:</dt>
                    <dd>
                        <input type="text" name="suspendBusinessFee" value="${swapHouse.suspendBusinessFee}" size="20" style="margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl style="width: 100%;height: 235px;">
                    <dt>7、附属设施补偿:</dt>
                    <dd style="line-height: 26px;width: 80%;" id="swapSubsidiary_item">
                        (1)、水表迁移费&nbsp;&nbsp;<input type="text" name="moveWaterMeterFee" value="${swapHouse.moveWaterMeterFee}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元<br/>
                        (2)、电表迁移费&nbsp;&nbsp;<input type="text" name="moveAmmeterFee" value="${swapHouse.moveAmmeterFee}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元<br/>
                        (3)、空调移机费&nbsp;&nbsp;<input type="text" name="moveAirConditioningFee"value="${swapHouse.moveAirConditioningFee}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元<br/>
                        (4)、太阳能热水器&nbsp;&nbsp;<input type="text" name="solarHeater" value="${swapHouse.solarHeater}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        其他热水器&nbsp;&nbsp;<input type="text" name="otherHeater" value="${swapHouse.otherHeater}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元<br/>
                        (5)、管道燃气&nbsp;&nbsp;<input type="text" name="gasFee" value="${swapHouse.gasFee}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元<br/>
                        (6)、构筑物：结构设计内封闭阳台&nbsp;&nbsp;<input type="text"  name="structureBalcony" value="${swapHouse.structureBalcony}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元、
                        外挑搭建&nbsp;&nbsp;<input type="text" name="structureBuild" value="${swapHouse.structureBuild}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元、
                        暗楼&nbsp;&nbsp;<input type="text" name="structureDark" value="${swapHouse.structureDark}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元、
                        夹层（假层）&nbsp;&nbsp;<input type="text" name="structureMezzanine" value="${swapHouse.structureMezzanine}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元、
                        楼顶搭建&nbsp;&nbsp;<input type="text" name="structureRoof" value="${swapHouse.structureRoof}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/> &nbsp;&nbsp;元<br/>
                        (7)、其他&nbsp;&nbsp;<input type="text" name="affiliatedOther" value="${swapHouse.affiliatedOther}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/> &nbsp;&nbsp;元<br/>
                        小计：<input type="text" name="subtotal" value="${swapHouse.subtotal}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;元。<br/>
                    </dd>
                </dl>
                <dl style="width: 100%;height: 130px;">
                    <dt>8、征收补助:</dt>
                    <dd style="line-height: 26px;width: 80%;" >
                        (1)、改变房屋用途补助&nbsp;&nbsp;<input type="text" name="changeCompensate" value="${swapHouse.changeCompensate}" style="clear: both;float: none;margin-left: 0px;width: 110px;"/>&nbsp;&nbsp;元<br/>
                        (2)、生活困难补助：低保补助&nbsp;&nbsp;<input type="text" name="basicLivingSubsidy" value="${swapHouse.basicLivingSubsidy}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元、
                        残疾补助&nbsp;&nbsp;<input type="text" name="disabilitySubsidy" value="${swapHouse.disabilitySubsidy}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元、
                        重症补助&nbsp;&nbsp;<input type="text" name="diseaseSubsidy" value="${swapHouse.diseaseSubsidy}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元、
                        失独补助&nbsp;&nbsp;<input type="text" name="noChild" value="${swapHouse.noChild}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元、
                        烈士家庭补助&nbsp;&nbsp;<input type="text" name="martyr" value="${swapHouse.martyr}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元、
                        小计：<input type="text" name="lifeCompensate" value="${swapHouse.lifeCompensate}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元<br/>
                        (3)、建筑面积补助&nbsp;<input type="text" name="buildingAreaFee" value="${swapHouse.buildingAreaFee}" style="clear: both;float: none;margin-left: 0px;width: 110px;"/>&nbsp;&nbsp;元<br/>
                        (4)、产权调换补助&nbsp;&nbsp;<input type="text" name="rmbCompensate" value="${swapHouse.rmbCompensate}" style="clear: both;float: none;margin-left: 0px;width: 110px;"/>&nbsp;&nbsp;元<br/>
                    </dd>
                </dl>

                <dl>
                    <dt>9、按期签约搬迁奖励:</dt>
                    <dd>
                        <input type="text" name="moveReward" value="${swapHouse.moveReward}"  size="20" style="margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>10、其他:</dt>
                    <dd>
                        <input type="text" name="otherFee" value="${swapHouse.otherFee}" size="20" style="margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl style="width: 100%">
                    <dt>&nbsp;</dt>
                    <dd style="line-height: 26px;width: 80%;">
                        以上征收补偿、补助及奖励等款项总金额合计人民币：<input type="text" name="sumRbm"  value="${swapHouse.sumRbm}"  style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;大写：<input type="text" name="upperRmb" value="${swapHouse.upperRmb}"  style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 200px;margin-left: 0px;"/>
                    </dd>
                </dl>
            </fieldset>
            <br/><br/>
            <fieldset>
                <legend>产权调换房屋基本信息</legend>
                <dl style="width: 100%;height: 85px;">
                    <dd style="width: 90%;line-height: 26px;margin-left: 5px;">
                        乙方选定&nbsp;&nbsp;<input type="text" name="newHouseAddress" maxlength="50" value="${swapHouse.newHouseAddress}"   class="bottom_border"  size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 200px;"/>、
                        <input type="text" name="seat" maxlength="255" value="${swapHouse.seat}"   class="bottom_border"  size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 40px;"/>&nbsp;&nbsp;栋、
                        <input type="text" name="unit" maxlength="255" value="${swapHouse.unit}"   class="bottom_border"  size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 40px;"/>&nbsp;&nbsp;单元、
                        <input type="text" name="floors" maxlength="255" value="${swapHouse.floors}"   class="bottom_border"  size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 40px;"/>&nbsp;&nbsp;层、
                        <input type="text" name="houseNumber" maxlength="255"  value="${swapHouse.houseNumber}"  class="bottom_border"  size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 40px;"/>&nbsp;&nbsp;号房屋。
                        该房屋面积&nbsp;&nbsp;<input type="text" name="coveredArea"  maxlength="50" value="${swapHouse.coveredArea}"   class="bottom_border"  size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 100px;"/>&nbsp;&nbsp;平方米，
                        单价&nbsp;&nbsp;<input type="text" name="price" maxlength="50" value="${swapHouse.price}"    class="bottom_border"  size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 100px;"/>&nbsp;&nbsp;元/平方米，
                        预估房屋总价人民币&nbsp;&nbsp;<input type="text" name="totalPrice" maxlength="50" value="${swapHouse.totalPrice}"    class="bottom_border"  size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 160px;"/>&nbsp;&nbsp;元
                        （大写：&nbsp;&nbsp;<input type="text" name="upperTotalPrice" maxlength="50" value="${swapHouse.upperTotalPrice}"   class="bottom_border"  style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 180px;"/>），
                        交房时间为&nbsp;&nbsp;<input type="text" name="years" maxlength="50" value="${swapHouse.years}"   class="bottom_border"  size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 60px;"/>&nbsp;&nbsp;年
                        &nbsp;&nbsp;<input type="text" name="months" maxlength="50" value="${swapHouse.months}"   class="bottom_border"  size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 50px;"/>&nbsp;&nbsp;月。
                    </dd>
                </dl>

            </fieldset>
            <br/><br/>
            <fieldset>
                <legend>房款结算和支付期限</legend>
                <dl style="width: 100%;height: 85px;">
                    <dd style="width: 90%;line-height: 26px;margin-left: 5px;">
                        乙方同意将房屋征收补偿款&nbsp;<input type="text" name="transferRmb" value="${swapHouse.transferRmb}" style="margin-left: 0px;clear: both;float: none;width: 80px;margin-left: 0px;"/>元由甲方支付给产权调换房建设单位，
                        如房屋征收补偿款总额大于预购房款，其差额部分&nbsp;&nbsp;<input type="text" name="difference" maxlength="255" value="${swapHouse.difference}"   class=" bottom_border"  size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 120px;"/>&nbsp;&nbsp;元
                        （大写：&nbsp;&nbsp;<input type="text" name="upperDifference" maxlength="255"   class=" bottom_border" value="${swapHouse.upperDifference}"   size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 160px;"/>&nbsp;&nbsp;）
                        经会计机构审核出具审计报告后，由甲方一次性支付给乙方；如房屋征收补偿总额小于预购房款，其差额部分&nbsp;&nbsp;
                        <input type="text" name="lessDifference" maxlength="255" value="${swapHouse.lessDifference}"   class=" bottom_border"  size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 120px;"/>&nbsp;&nbsp;元
                        （大写：&nbsp;&nbsp;<input type="text" name="upperLessDifference" maxlength="255"   class=" bottom_border"  size="20" value="${swapHouse.upperLessDifference}"  style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 160px;"/>&nbsp;&nbsp;）
                        待办理入住手续时，据实结算由乙方一次性支付给甲方。
                    </dd>
                </dl>
            </fieldset>
            <br/><br/>
            <fieldset>
                <legend>过渡方式和过渡期限</legend>
                <dl style="width: 100%;height: 52px">
                    <dd style="width: 90%;line-height: 26px;margin-left: 5px;">
                        乙方选择自行过渡，过渡期限自签订本协议并搬迁完毕之日起
                        <input type="text" name="moveMonth" value="${swapHouse.moveMonth}" style="margin-left: 0px;width: 100px;clear: both;float: none;"/>个月;
                        临时安置补偿费以实际搬迁过渡时间据实计算，逾期按增加50%的标准由甲方支付乙方；如点选多套房屋的，则按最先还建的安置房时间计算临时安置补偿费。
                    </dd>
                </dl>
            </fieldset>
            <br/><br/>
            <fieldset style="height: 85px;">
                <legend>签约、搬迁及付款</legend>
                <dl style="width: 100%;">
                    <dd style="width: 90%;line-height: 26px;margin-left: 5px;">
                        1、会计机构审核出具审计报告<input type="text" name="afterDayAudit" value="${swapHouse.afterDayAudit}"  style="margin-left: 0px;width: 20px;clear: both;float: none;"/>个工作日后，甲方将房屋征收补偿的总金额支付给乙方
                    </dd>
                    <dd style="width: 90%;line-height: 26px;margin-left: 5px;">
                        2、乙方应于<input type="text" name="beforeDay" value="${swapHouse.beforeDay}" style="margin-left: 0px;width: 20px;clear: both;float: none;"/>日内搬迁腾空房屋内物品，并结清房屋水、电等费用后移交给甲方
                    </dd>
                </dl>
            </fieldset>
            <fieldset style="height: 120px;">
                <legend>其它约定</legend>
                <dl>
                    <dt>约定明细:</dt>
                    <dd>
                        <textarea name="otherTermsOne" maxlength="255"  rows="5" cols="40">${swapHouse.otherTermsOne}</textarea>
                    </dd>
                </dl>
            </fieldset>

        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <!--  主键Id -->
                            <input type="hidden" name="id" value="${swapHouse.id}">
                            <button type="submit">保存</button>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="button">
                        <div class="buttonContent">
                            <button type="button" class="close">取消</button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </form>

</div>


<script type="text/javascript">
    $(document).ready(function(){
        $("input[type='text']").each(function (i, obj) {
            var obj_value = $(obj).val();
            //判断是否是数字，仅数字做转换处理，非数字直接忽略
            if(isNaN(obj_value)){
                return;
            }
            if (obj_value && obj_value.indexOf(".00") != -1) {
                obj_value = Math.round(obj_value);
                $(obj).val(obj_value);
            }
        });
    });
</script>