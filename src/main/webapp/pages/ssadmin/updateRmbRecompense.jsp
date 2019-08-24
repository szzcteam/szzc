<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="comm/include.inc.jsp" %>
<h2 class="contentTitle">修改货币补偿协议</h2>
<script type="text/javascript" src="${oss_url}/static/ssadmin/js/business/add_rmbRecompense.js"/>



<style>
    .samll_input_text {
        width: 50px;
    }

</style>

<div class="pageContent" id="rmbRecompenseDiv">

    <form method="post" action="ssadmin/RmbRecompense/update.html"
          class="pageForm required-validate" enctype="multipart/form-data"
          onsubmit="return iframeCallback(this, dialogAjaxDone);">

        <div class="pageFormContent" layoutH="97">
            <fieldset>
                <legend>协议信息</legend>
                <dl style="width: 100%;">
                    <dt>编号：</dt>
                    <dd>
                        <input type="text" name="cardNo" value="${rmbRecom.cardNo}" maxlength="50" size="20" style="margin-left: 0px;"/>
                    </dd>
                    <dt>&nbsp;</dt>
                    <dd>&nbsp;</dd>
                </dl>
                <dl>
                    <dt>征收部门(甲方):</dt>
                    <dd>
                        <input type="text" name="mngOffice" value="${rmbRecom.mngOffice}" maxlength="255" size="40" style="margin-left: 0px;"/>
                    </dd>
                </dl>
                <dl>
                    <dt>代办单位:</dt>
                    <dd>
                        <input type="text" name="agencyCompany" value="${rmbRecom.agencyCompany}" maxlength="255" size="40" style="margin-left: 0px;"/>
                    </dd>
                </dl>
                <dl>
                    <dt>被征收人(乙方):</dt>
                    <dd>
                        <input type="text" name="houseOwner" value="${rmbRecom.houseOwner}" maxlength="50" size="40" style="margin-left: 0px;"/>
                    </dd>
                </dl>
                <dl>
                    <dt>身份证号:</dt>
                    <dd>
                        <input type="text" name="identityNo" value="${rmbRecom.identityNo}" maxlength="255" size="40" style="margin-left: 0px;"/>
                    </dd>
                </dl>
                <%--<dl>
                    <dt>补偿方案:</dt>
                    <dd>
                        <input type="text" name="recompensePlan" size="40"/>
                    </dd>
                </dl>--%>
            </fieldset>
            <br/><br/>
            <fieldset>
                <legend>被征收房屋情况</legend>
                <dl style="width: 100%;height: 75px;">
                    <dd style="width: 95%;line-height: 26px;">
                        1、被征收房屋地址：<input type="text" name="address" value="${rmbRecom.address}" maxlength="255"  size="40" style="margin-left: 0px;clear: both;float: none;"/>、
                        证载建筑面积：<input type="text" name="certifiedArea" value="${rmbRecom.certifiedArea}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;M<sup>2</sup>、
                        房屋权属份额：<input type="text" name="proportion" value="${rmbRecom.proportion}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;%、
                        证载房屋用途：<input type="text" name="useing" value="${rmbRecom.useing}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>、
                        房屋所有权证号：<input type="text" name="houseOwnerNumber" value="${rmbRecom.houseOwnerNumber}" style="margin-left: 0px;clear: both;float: none;width: 110px;"/>、
                        国有土地使用权证号：<input type="text" name="publicOwnerNumber"  value="${rmbRecom.publicOwnerNumber}" style="margin-left: 0px;clear: both;float: none;width: 110px;"/>、
                        房屋价值评估单价：<input type="text" name="assessPrice"   value="${rmbRecom.assessPrice}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>、
                        用于经营的实际面积：<input type="text" name="valueCompensateBusinessArea"  value="${rmbRecom.valueCompensateBusinessArea}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;M<sup>2</sup>、
                        住宅改为经营门面补助系数：<input type="text" name="valueCompensateRate"  value="${rmbRecom.valueCompensateRate}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;%
                    </dd>
                </dl>
                <dl style="width: 100%;height: 52px;">
                    <dd style="width: 95%;line-height: 26px;">
                        2、未登记的合法建筑面积：<input type="text" name="noRegisterLegalArea" value="${rmbRecom.noRegisterLegalArea}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;M<sup>2</sup>、
                        房屋用途：<input type="text" name="noRegisterUseing" value="${rmbRecom.noRegisterUseing}"  class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>、
                        房屋评估单价：<input type="text" name="noRegisterAssessPrice" value="${rmbRecom.noRegisterAssessPrice}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;元、
                        补偿比例：<input type="text" name="noRegisterProportion" value="${rmbRecom.noRegisterProportion}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;%、
                        经营面积：<input type="text" name="noRegisterBusinessArea" value="${rmbRecom.noRegisterBusinessArea}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;M<sup>2</sup>、
                        住宅改为经营门面补助系数：<input type="text" name="noRegisterRate" value="${rmbRecom.noRegisterRate}"  class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;%
                    </dd>
                </dl>
                <dl style="width: 100%;height: 52px;">
                    <dd style="width: 95%;line-height: 26px;">
                        3、历史遗留无证房面积：<input type="text" name="historyLegacyArea" value="${rmbRecom.historyLegacyArea}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;M<sup>2</sup>、
                        房屋用途：<input type="text" name="historyUseing" value="${rmbRecom.historyUseing}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>、
                        房屋评估单价：<input type="text" name="historyAssessPrice" value="${rmbRecom.historyAssessPrice}" class="samll_input_text"  style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;元、
                        补偿比例：<input type="text" name="historyProportion" value="${rmbRecom.historyProportion}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;%、
                        经营面积： <input type="text" name="historyBusinessArea" value="${rmbRecom.historyBusinessArea}"  class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;M<sup>2</sup>、
                        住宅改为经营门面补助系数： <input type="text" name="historyRate" value="${rmbRecom.historyRate}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;%
                    </dd>
                </dl>
            </fieldset>
            <br/><br/>
            <fieldset>
                <legend>征收补偿款项</legend>
                <dl>
                    <dt>1、有证房屋补偿金额:</dt>
                    <dd>
                        <input type="text" name="valueCompensate" value="${rmbRecom.valueCompensate}" size="20" style="margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>未登记的合法建筑补偿金额:</dt>
                    <dd>
                        <input type="text" name="noRegisterLegal" value="${rmbRecom.noRegisterLegal}"  size="20" style="margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl style="width: 100%;">
                    <dt>历史遗留无证房屋补偿金额:</dt>
                    <dd>
                        <input type="text" name="historyLegacy" value="${rmbRecom.historyLegacy}" size="20" style="margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>2、装修补偿:</dt>
                    <dd style="width: 300px;">
                        单价：<input type="text" name="decorationCompensateUnitPrice" value="${rmbRecom.decorationCompensateUnitPrice}"  class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;元&nbsp;&nbsp;&nbsp;&nbsp;
                        总价：<input type="text" name="decorationCompensate" value="${rmbRecom.decorationCompensate}" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>3、搬迁补偿(搬家费):</dt>
                    <dd>
                        <input type="text" name="moveHouseFee" value="${rmbRecom.moveHouseFee}" size="20" style="margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>4、临时安置补偿:</dt>
                    <dd style="width: 300px;">
                        <%--  <input type="text" name="interimArea" maxlength="50" class="samll_input_text" style="clear: both;float: none;"/>&nbsp;&nbsp;M<sup>2</sup>&nbsp;&nbsp;×&nbsp;&nbsp;--%>
                        标准：<input type="text" name="interimPrice" value="${rmbRecom.interimPrice}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元/M<sup>2</sup>&nbsp;&nbsp;&nbsp;&nbsp;
                        <%--<input type="text" name="interimMonth" maxlength="50" class="samll_input_text" style="clear: both;float: none;"/>&nbsp;&nbsp;个月--%>
                        总额：<input type="text" name="interimFee" value="${rmbRecom.interimFee}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>5、保底面积补偿:</dt>
                    <dd>
                        <input type="text" name="guarantee" value="${rmbRecom.guarantee}" size="20" style="margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl style="width: 100%;">
                    <dt>6、停产停业损失补偿:</dt>
                    <dd>
                        <input type="text" name="suspendBusinessFee" value="${rmbRecom.suspendBusinessFee}" size="20" style="margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl style="width: 100%;height: 235px;">
                    <dt>7、附属设施补偿:</dt>
                    <dd style="line-height: 26px;width: 80%;" id="subsidiary_item">
                        (1)、水表迁移费&nbsp;&nbsp;<input type="text" name="moveWaterMeterFee" value="${rmbRecom.moveWaterMeterFee}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元<br/>
                        (2)、电表迁移费&nbsp;&nbsp;<input type="text" name="moveAmmeterFee" value="${rmbRecom.moveAmmeterFee}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元<br/>
                        (3)、空调移机费&nbsp;&nbsp;<input type="text" name="moveAirConditioningFee"value="${rmbRecom.moveAirConditioningFee}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元<br/>
                        (4)、太阳能热水器&nbsp;&nbsp;<input type="text" name="solarHeater" value="${rmbRecom.solarHeater}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        其他热水器&nbsp;&nbsp;<input type="text" name="otherHeater" value="${rmbRecom.otherHeater}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元<br/>
                        (5)、管道燃气&nbsp;&nbsp;<input type="text" name="gasFee" value="${rmbRecom.gasFee}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元<br/>
                        (6)、构筑物：结构设计内封闭阳台&nbsp;&nbsp;<input type="text"  name="structureBalcony" value="${rmbRecom.structureBalcony}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元、
                        外挑搭建&nbsp;&nbsp;<input type="text" name="structureBuild" value="${rmbRecom.structureBuild}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元、
                        暗楼&nbsp;&nbsp;<input type="text" name="structureDark" value="${rmbRecom.structureDark}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元、
                        夹层（假层）&nbsp;&nbsp;<input type="text" name="structureMezzanine" value="${rmbRecom.structureMezzanine}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元、
                        楼顶搭建&nbsp;&nbsp;<input type="text" name="structureRoof" value="${rmbRecom.structureRoof}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/> &nbsp;&nbsp;元<br/>
                        (7)、其他&nbsp;&nbsp;<input type="text" name="affiliatedOther" value="${rmbRecom.affiliatedOther}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/> &nbsp;&nbsp;元<br/>
                        小计：<input type="text" name="subtotal" value="${rmbRecom.subtotal}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;元。<br/>
                    </dd>
                </dl>
                <dl style="width: 100%;height: 130px;">
                    <dt>8、征收补助:</dt>
                    <dd style="line-height: 26px;width: 80%;" >
                        (1)、改变房屋用途补助&nbsp;&nbsp;<input type="text" name="changeCompensate" value="${rmbRecom.changeCompensate}" style="clear: both;float: none;margin-left: 0px;width: 110px;"/>&nbsp;&nbsp;元<br/>
                        (2)、生活困难补助：低保补助&nbsp;&nbsp;<input type="text" name="basicLivingSubsidy" value="${rmbRecom.basicLivingSubsidy}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元、
                        残疾补助&nbsp;&nbsp;<input type="text" name="disabilitySubsidy" value="${rmbRecom.disabilitySubsidy}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元、
                        重症补助&nbsp;&nbsp;<input type="text" name="diseaseSubsidy" value="${rmbRecom.diseaseSubsidy}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元、
                        失独补助&nbsp;&nbsp;<input type="text" name="noChild" value="${rmbRecom.noChild}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元、
                        烈士家庭补助&nbsp;&nbsp;<input type="text" name="martyr" value="${rmbRecom.martyr}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元、
                        小计：<input type="text" name="lifeCompensate" value="${rmbRecom.lifeCompensate}" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元<br/>
                        (3)、货币补偿补助&nbsp;&nbsp;<input type="text" name="rmbCompensate" value="${rmbRecom.rmbCompensate}" style="clear: both;float: none;margin-left: 0px;width: 110px;"/>&nbsp;&nbsp;元<br/>
                        (4)、小户型住房困难补助&nbsp;<input type="text" name="smallAreaReward" value="${rmbRecom.smallAreaReward}" style="clear: both;float: none;margin-left: 0px;width: 110px;"/>&nbsp;&nbsp;元<br/>
                    </dd>
                </dl>

                <dl>
                    <dt>9、按期签约搬迁奖励:</dt>
                    <dd>
                        <input type="text" name="moveReward" value="${rmbRecom.moveReward}"  size="20" style="margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>10、其他:</dt>
                    <dd>
                        <input type="text" name="otherFee" value="${rmbRecom.otherFee}" size="20" style="margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl style="width: 100%">
                    <dt>&nbsp;</dt>
                    <dd style="line-height: 26px;width: 80%;">
                        以上征收补偿、补助及奖励等款项总金额合计人民币：<input type="text" name="sumRbm"  value="${rmbRecom.sumRbm}"  style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;大写：<input type="text" name="upperRmb" value="${rmbRecom.upperRmb}"  style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 200px;margin-left: 0px;"/>
                    </dd>
                </dl>
            </fieldset>
            <br/><br/>
            <fieldset>
                <legend>签约、搬迁及付款</legend>
                <dl style="width: 100%;">
                    <dd style="width: 90%;line-height: 26px;margin-left: 5px;">
                        乙方应于<input type="text" name="beforeDay" value="${rmbRecom.beforeDay}"  style="margin-left: 0px;width: 20px;clear: both;float: none;"/>日内搬迁腾空房屋内物品，并结清房屋水、电等费用后移交给甲方
                    </dd>
                </dl>
            </fieldset>
            <br/>
            <fieldset>
                <legend>其它约定</legend>
                <dl>
                    <dt>约定明细1:</dt>
                    <dd>
                        <input type="text" name="otherTermsOne" value="${rmbRecom.otherTermsOne}"  maxlength="255"    size="40" style="margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;
                    </dd>
                </dl>
                <dl>
                    <dt>约定明细2:</dt>
                    <dd>
                        <input type="text" name="otherTermsTwo" value="${rmbRecom.otherTermsTwo}" maxlength="255"    size="40" style="margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;
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
                            <input type="hidden" name="id" value="${rmbRecom.id}">
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