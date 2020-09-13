<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="comm/include.inc.jsp" %>
<%--<h2 class="contentTitle">添加货币补偿协议</h2>--%>
<script type="text/javascript" src="${oss_url}/static/ssadmin/js/business/add_rmbRecompense_drnc.js"/>

<style>
    .samll_input_text {
        width: 50px;
    }

</style>

<div class="pageContent" id="rmbRecompenseDiv">

    <form method="post" action="ssadmin/RmbRecompense/add.html"
          class="pageForm required-validate" enctype="multipart/form-data"
          onsubmit="return iframeCallback(this, dialogAjaxDone);">

        <div class="pageFormContent" layoutH="97">
            <fieldset>
                <legend>协议信息</legend>
                <dl>
                    <dt>编号：</dt>
                    <dd>
                        <input type="text" name="cardNo" maxlength="50" size="20" style="margin-left: 0px;"/>
                    </dd>
                </dl>
               <%-- <dl>
                    <dt>征收部门(甲方):</dt>
                    <dd>
                        <input type="text" name="mngOffice" maxlength="255" size="40" style="margin-left: 0px;"/>
                    </dd>
                </dl>--%>
                <%--<dl>
                    <dt>代办单位:</dt>
                    <dd>
                        <input type="text" name="agencyCompany" maxlength="255" size="40" style="margin-left: 0px;"/>
                    </dd>
                </dl>--%>
                <dl>
                    <dt>所属片区:</dt>
                    <dd>
                        <select name="areaId" style="width: 170px;height: 21px;">
                            <option value="">请选择</option>
                            <c:forEach items="${areaList}" var="area" varStatus="num">
                                <option value="${area.id}" <c:if test="${num.index == 0 }">selected="selected"</c:if>>${area.name}</option>
                            </c:forEach>
                        </select>
                    </dd>
                </dl>
                <dl>
                    <dt>被征收人(乙方):</dt>
                    <dd>
                        <input type="text" name="houseOwner" maxlength="50" size="40" style="margin-left: 0px;"/>
                        <input type="hidden" name="isLesseeFlag"/>
                    </dd>
                </dl>
                <dl>
                    <dt>身份证号:</dt>
                    <dd>
                        <input type="text" name="identityNo" maxlength="255" size="40" style="margin-left: 0px;"/>
                    </dd>
                </dl>
                <dl>
                    <dt>委托代理人(乙方):</dt>
                    <dd>
                        <input type="text" name="consignName" maxlength="50" size="40" style="margin-left: 0px;"/>
                    </dd>
                </dl>
                <dl>
                    <dt>身份证号:</dt>
                    <dd>
                        <input type="text" name="consignIdentityNo" maxlength="255" size="40" style="margin-left: 0px;"/>
                    </dd>
                </dl>
            </fieldset>
            <br/><br/>
            <fieldset>
                <legend>诀字信息</legend>
                <dl style="width: 100%;height: 26px;">
                    <dd style="width: 95%;line-height: 26px;">
                        人民政府于
                        <input type="text" name="govYear"  class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>年
                        <input type="text" name="govMonth"  class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>月
                        <input type="text" name="govDay"  class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>日作出《房屋征收决定》
                        （武昌征决字[<input type="text" name="adjuLetter"  class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>]
                        第<input type="text" name="adjuNum"  class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>号），并于
                        <input type="text" name="noticeYear"  class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>年
                        <input type="text" name="noticeMonth"  class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>月
                        <input type="text" name="noticeDay"  class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>日公告
                    </dd>
                </dl>
            </fieldset>
            <br/><br/>
            <fieldset>
                <legend>被征收房屋情况</legend>
                <dl style="width: 100%;height: 75px;">
                    <dd style="width: 95%;line-height: 26px;">
                        被征收房屋地址：<input type="text" name="address"  maxlength="255"  size="40" style="margin-left: 0px;clear: both;float: none;"/>、
                        房屋所有权证号：<input type="text" name="houseOwnerNumber" style="margin-left: 0px;clear: both;float: none;width: 110px;"/>、
                        国有土地使用权证号：<input type="text" name="publicOwnerNumber"  style="margin-left: 0px;clear: both;float: none;width: 110px;"/>、
                        房屋权属份额：<input type="text" name="proportion" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;%、
                        房屋用途：<input type="text" name="useing" class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>、
                        登记建筑面积：<input type="text" name="certifiedArea"  class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;M<sup>2</sup>，
                        （其中：
                        住宅面积&nbsp;&nbsp;<input type="text" name="residenceArea"   class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;M<sup>2</sup>，
                        经营面积&nbsp;&nbsp;<input type="text" name="valueCompensateBusinessArea"  class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;M<sup>2</sup>，
                        办公面积&nbsp;&nbsp;<input type="text" name="officeArea"   class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;M<sup>2</sup>，
                        生产面积&nbsp;&nbsp;<input type="text" name="produceArea"   class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;M<sup>2</sup>，
                        其他面积&nbsp;&nbsp;<input type="text" name="otherArea"   class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;M<sup>2</sup>），
                        未登记建筑面积：<input type="text" name="noRegisterLegalArea"   class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;M<sup>2</sup>、
                        房屋价值评估单价：<input type="text" name="assessPrice"   class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;。
                        <%--住宅改为经营门面补助系数：<input type="text" name="valueCompensateRate"  class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;%--%>
                    </dd>
                </dl>
            </fieldset>
            <br/><br/>
            <fieldset>
                <legend>征收补偿款项</legend>
                <dl>
                    <dt>1、房屋价值补偿金额:</dt>
                    <dd>
                        <input type="text" name="valueCompensate" size="20" style="margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <%--<dl>
                    <dt>未登记的合法建筑补偿金额:</dt>
                    <dd>
                        <input type="text" name="noRegisterLegal"  size="20" style="margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl style="width: 100%;">
                    <dt>历史遗留无证房屋补偿金额:</dt>
                    <dd>
                        <input type="text" name="historyLegacy" size="20" style="margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>--%>
                <dl>
                    <dt>2、室内装饰装修补偿:</dt>
                    <dd style="width: 300px;">
                        <%--单价：<input type="text" name="decorationCompensateUnitPrice"  class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;元&nbsp;&nbsp;&nbsp;&nbsp;--%>
                        <%--总价：--%><input type="text" name="decorationCompensate" size="20" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl style="width: 100%;height: 185px;">
                    <dt>3、附属设施补偿:</dt>
                    <dd style="line-height: 26px;width: 80%;">
                        (1)、水表迁移费&nbsp;&nbsp;<input type="text" name="moveWaterMeterFee" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元<br/>
                        (2)、电表迁移费&nbsp;&nbsp;<input type="text" name="moveAmmeterFee" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元<br/>
                        (3)、空调移机费&nbsp;&nbsp;<input type="text" name="moveAirConditioningFee" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元<br/>
                        (4)、热水器拆装补偿&nbsp;&nbsp;<input type="text" name="hotWaterCompensate" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元&nbsp;<br/>
                        <div style="display: none;">
                        &nbsp;&nbsp;<input type="text" name="solarHeater" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        其他热水器&nbsp;&nbsp;<input type="text" name="otherHeater" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元<br/>
                        </div>
                        (5)、管道煤气拆装补偿&nbsp;&nbsp;<input type="text" name="gasFee" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元<br/>
                        (6)、构筑物补偿&nbsp;&nbsp;<input type="text" name="structureCompensate" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元<br/>
                        <div style="display: none;">
                            <%--阳台在第11条有存储--%>
                        <%--结构设计内封闭阳台&nbsp;&nbsp;<input type="text" name="structureBalcony" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元、--%>
                        外挑搭建&nbsp;&nbsp;<input type="text" name="structureBuild" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元、
                        暗楼&nbsp;&nbsp;<input type="text" name="structureDark" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元、
                        夹层（假层）&nbsp;&nbsp;<input type="text" name="structureMezzanine" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元、
                        楼顶搭建&nbsp;&nbsp;<input type="text" name="structureRoof" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/> &nbsp;&nbsp;元<br/>
                        </div>
                       <%-- (7)、其他&nbsp;&nbsp;<input type="text" name="affiliatedOther" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/> &nbsp;&nbsp;元<br/>--%>
                        小计：<input type="text" name="subtotal" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;元。<br/>
                    </dd>
                </dl>
                <dl>
                    <dt>4、搬迁补偿(搬家费):</dt>
                    <dd>
                        <input type="text" name="moveHouseFee" size="20" style="margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>5、临时安置补偿:</dt>
                    <dd style="width: 300px;">
                        <input type="hidden" name="interimArea" maxlength="50" class="samll_input_text" style="clear: both;float: none;"/><%--&nbsp;&nbsp;M<sup>2</sup>&nbsp;&nbsp;×&nbsp;&nbsp;--%>
                        <input type="text" name="interimPrice" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元/M<sup>2</sup>&nbsp;&nbsp;
                        <input type="text" name="interimMonth" maxlength="50" class="samll_input_text" style="clear: both;float: none;margin-left: 5px;"/>&nbsp;&nbsp;个月
                        ＝<input type="text" name="interimFee" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元
                    </dd>
                </dl>
                <%--<dl style="display: none;">
                    <dt>5、保底面积补偿:</dt>
                    <dd>
                        <input type="text" name="guarantee" size="20" style="margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>--%>
                <dl>
                    <dt>6、停产停业损失补偿:</dt>
                    <dd>
                        <input type="text" name="suspendBusinessFee" size="20" style="margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>7、货币补偿补助</dt>
                    <dd>
                        <input type="text" name="rmbCompensate" size="20" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>8、生活困难补助</dt>
                    <dd>
                        <input type="text" name="lifeCompensate" size="20" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                        <div style="display: none;">
                            低保补助&nbsp;&nbsp;<input type="text" name="basicLivingSubsidy" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元、
                            残疾补助&nbsp;&nbsp;<input type="text" name="disabilitySubsidy" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元、
                            重症补助&nbsp;&nbsp;<input type="text" name="diseaseSubsidy" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元、
                            失独补助&nbsp;&nbsp;<input type="text" name="noChild" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元、
                            烈士家庭补助&nbsp;&nbsp;<input type="text" name="martyr" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元、
                        </div>
                    </dd>
                </dl>
                <dl>
                    <dt>9、改变房屋用途补助</dt>
                    <dd >
                        <input type="text" name="changeCompensate" size="20" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;元<br/>
                        <%--小户型住房困难补助&nbsp;<input type="text" name="smallAreaReward" style="clear: both;float: none;margin-left: 0px;width: 110px;"/>&nbsp;&nbsp;元<br/>--%>
                    </dd>
                </dl>

                <dl>
                    <dt>10、按期签约搬迁奖励:</dt>
                    <dd>
                        <input type="text" name="moveReward"   size="20" style="margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>11、封闭阳台:</dt>
                    <dd>
                        <input type="text" name="structureBalcony"   size="20" style="margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl style="width: 100%">
                    <dt>12、未登记建筑:</dt>
                    <dd>
                        <input type="text" name="noRegisterLegal"   size="20" style="margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl style="width: 100%">
                    <dt>13、其他:</dt>
                    <dd style="line-height: 26px;width: 80%;">
                        金额：<input type="text" name="otherFee" class="samll_input_text" style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元，
                        明细：<input type="text" name="otherFeeDetail" style="clear: both;float: none;margin-left: 0px;width: 500px;"/>
                    </dd>
                </dl>
                <dl style="width: 100%">
                    <dt>&nbsp;</dt>
                    <dd style="line-height: 26px;width: 80%;">
                        以上征收补偿、补助及奖励等款项总金额合计人民币：<input type="text" name="sumRbm"    style="clear: both;float: none;margin-left: 0px;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;大写：<input type="text" name="upperRmb"  style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 200px;margin-left: 0px;"/>
                    </dd>
                </dl>
            </fieldset>
            <br/><br/>
            <%--<fieldset style="height: 85px;">
                <legend>签约、搬迁及付款</legend>
                <dl style="width: 100%;">
                    <dd style="width: 90%;line-height: 26px;margin-left: 5px;">
                        1、会计机构审核出具审计报告<input type="text" name="afterDayAudit" value="${rmbAfterDayAudit}"  style="margin-left: 0px;width: 20px;clear: both;float: none;"/>个工作日后，甲方将房屋征收补偿的总金额支付给乙方
                    </dd>
                    <dd style="width: 90%;line-height: 26px;margin-left: 5px;">
                        2、乙方应于<input type="text" name="beforeDay" style="margin-left: 0px;width: 20px;clear: both;float: none;"/>日内搬迁腾空房屋内物品，并结清房屋水、电等费用后移交给甲方
                    </dd>
                </dl>
            </fieldset>
            <br/>--%>
            <fieldset style="height: 180px">
                <legend>其它约定</legend>
                <dl style="width: 100%;height: 52px;">
                    <div style="height: 26px;">&nbsp;&nbsp;&nbsp;<input type="radio" name="isTradeHouse" value="true" style="cursor: pointer;"/>&nbsp;
                        乙方申请购买东龙世纪花园（暂定名）， <input type="text" name="coveredArea"   size="10" style="margin-left: 0px;clear: both;float: none;"/>&nbsp;&nbsp;M<sup>2</sup>&nbsp;
                        商品房一套（申购序号&nbsp;<input type="text" name="buySerialNumber"  class="samll_input_text" style="margin-left: 0px;clear: both;float: none;"/>）。</div>
                    <div style="height: 26px;">&nbsp;&nbsp;&nbsp;<input type="radio" name="isTradeHouse" value="false" style="cursor: pointer;"/>&nbsp;
                        乙方不购买东龙世纪花园（暂定名）商品房。</div>
                </dl>
                <div id="buyHouse" style="display: none;">
                    <dl style="width: 100%;height: 52px;line-height: 26px;">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        自愿将本协议的第三条第1点“房屋价值补偿”金额中的
                        <input type="text" name="transferRmb"  style="margin-left: 0px;clear: both;float: none;width:70px;"/>元，
                        作为申购房款的对应价格预购抵扣，最终甲方向乙方支付的补偿款项为人民币
                        <input type="text" name="difference"  class="samll_input_text" style="margin-left: 0px;clear: both;float: none;width:70px;"/>
                        （大写<input type="text" name="upperDifference"  class="samll_input_text" style="margin-left: 0px;clear: both;float: none;width:220px;"/>）<br/>
                    </dl>
                </div>
                <div id="noBuyHouse" style="display: none;height: 100px;">
                    <dl style="width: 50%">
                        <dt>约定明细1:</dt>
                        <dd>
                            <textarea name="otherTermsOne" maxlength="255"  rows="5" cols="40"></textarea>
                        </dd>
                    </dl>
                    <dl style="width: 50%">
                        <dt>约定明细2:</dt>
                        <dd>
                            <textarea name="otherTermsTwo" maxlength="255"  rows="5" cols="40"></textarea>
                        </dd>
                    </dl>
                </div>
            </fieldset>

        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
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



