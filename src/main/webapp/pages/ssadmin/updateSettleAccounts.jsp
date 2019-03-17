<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="comm/include.inc.jsp" %>
<h2 class="contentTitle">修改资金结算单</h2>

<script type="text/javascript" src="${oss_url}/static/ssadmin/js/business/add_settleAccounts.js"/>
<script type="text/javascript" src="${oss_url}/static/ssadmin/js/business/update_settleAccounts.js"/>
<link href="${oss_url}/static/ssadmin/css/add_settleAccounts.css" rel="stylesheet" type="text/css" />

<div class="pageContent">

    <form method="post" action="ssadmin/settleAccounts/update.html"
          class="pageForm required-validate" enctype="multipart/form-data"
          onsubmit="return iframeCallback(this, dialogAjaxDone);">

        <div class="pageFormContent nowrap" layoutH="97">
            <dl>
                <dt style="width: 80px;">项目名称:</dt>
                <dt style="width: 300px;">
                    <input type="text" name="projectName" maxlength="50" class="required" size="40" value="${settleAccounts.projectName}"/>
                </dt>
                <dt style="width: 40px;">编号：</dt>
                <dt>
                    <input type="text" name="cardNo" maxlength="50" class="required" size="10" value="${settleAccounts.cardNo}"/>
                </dt>
            </dl>
            <dl>
                <table  border="1" class="tableInfo">
                    <tr>
                        <td colspan="3">被征收人</td>
                        <td colspan="2" style="width: 100px;"><input type="text" name="houseOwner" class="none_border" value="${settleAccounts.houseOwner}"></td>
                        <td style="width: 60px;">电话</td>
                        <td style="width: 100px;"><input type="text" name="phone" class="none_border" value="${settleAccounts.phone}"></td>
                        <td style="width: 60px;">房屋用途</td>
                        <td style="width: 60px;">建筑面积</td>
                        <td style="width: 60px;">套内面积</td>
                        <td style="width: 80px;">房屋评估单价</td>
                    </tr>
                    <tr>
                        <td colspan="3">公房承租人</td>
                        <td colspan="2"><input type="text" name="lessee" class="none_border" value="${settleAccounts.lessee}"></td>
                        <td>电话</td>
                        <td><input type="text" name="lesseePhone" class="none_border"  value="${settleAccounts.lesseePhone}"></td>
                        <td><input type="text" name="useing" class="samll_input_text"  value="${settleAccounts.useing}"></td>
                        <td><input type="text" name="checkInArea" class="samll_input_text"  value="${settleAccounts.checkInArea}"></td>
                        <td><input type="text" name="inArea" class="samll_input_text"  value="${settleAccounts.inArea}"></td>
                        <td><input type="text" name="assessPrice" class="samll_input_text"  value="${settleAccounts.assessPrice}"></td>
                    </tr>
                    <tr>
                        <td colspan="5">被征收房屋地址</td>
                        <td colspan="6"><input type="text" name="address" class="none_border" value="${settleAccounts.address}" style="width: 400px;"></td>
                    </tr>
                    <tr>
                        <td style="width: 35px;">序号</td>
                        <td colspan="4">补偿内容</td>
                        <td colspan="3">计算公式</td>
                        <td colspan="2">金额(元)</td>
                        <td>备注</td>
                    </tr>
                    <tr>
                        <td>一</td>
                        <td colspan="4">证载房屋价值补偿</td>
                        <td colspan="3"><input type="text" name="calcValueCompensate" value="${settleAccounts.calcValueCompensate}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="valueCompensate" value="${settleAccounts.valueCompensate}" class="none_border width_110px"></td>
                        <td><input type="text" name="valueCompensateBz" value="${settleAccounts.valueCompensateBz}"  class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>二</td>
                        <td colspan="4">装修折旧补偿</td>
                        <td colspan="3"><input type="text" name="calcDecorationCompensate" value="${settleAccounts.calcDecorationCompensate}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="decorationCompensate" value="${settleAccounts.decorationCompensate}" class="none_border width_110px"></td>
                        <td><input type="text" name="decorationCompensateBz" value="${settleAccounts.decorationCompensateBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>三</td>
                        <td colspan="4" class="td_left">搬迁补偿（搬家费）</td>
                        <td colspan="3"><input type="text" name="calcMoveHouseFee" value="${settleAccounts.calcMoveHouseFee}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="moveHouseFee" value="${settleAccounts.moveHouseFee}" class="none_border width_110px"></td>
                        <td><input type="text" name="moveHouseFeeBz" value="${settleAccounts.moveHouseFeeBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>四</td>
                        <td colspan="4" class="td_left">临时安置补偿（过渡费）</td>
                        <td colspan="3">
                            <input type="text" name="calcInterimFee" value="${settleAccounts.calcInterimFee}" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="interimFee" value="${settleAccounts.interimFee}" class="none_border width_110px"></td>
                        <td><input type="text" name="interimFeeBz" value="${settleAccounts.interimFeeBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td rowspan="7">五</td>
                        <td rowspan="7" style="width: 35px;">房屋附属设施补偿</td>
                        <td colspan="3" class="td_left">1、水表迁移费</td>
                        <td colspan="3">
                            <select id="water_meter_main" class="select_fix">
                                <option value="0*${waterMeterMain}">请选择</option>
                                <c:forEach begin="1" end="10" var="idx">
                                    <option value="${idx}*${waterMeterMain}">${idx}&nbsp;主表</option>
                                </c:forEach>
                            </select>
                            <select id="water_meter_sub" class="select_fix">
                                <option value="0*${waterMeterSub}">请选择</option>
                                <c:forEach begin="1" end="10" var="idx">
                                    <option value="${idx}*${waterMeterSub}">${idx}&nbsp;副表</option>
                                </c:forEach>
                            </select>
                            <!--隐藏存储表单提交值-->
                            <input type="hidden" name="calcMoveWaterMeterFee" value="${settleAccounts.calcMoveWaterMeterFee}"  class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="moveWaterMeterFee" value="${settleAccounts.moveWaterMeterFee}" class="none_border width_110px"></td>
                        <td><input type="text" name="moveWaterMeterFeeBz" value="${settleAccounts.moveWaterMeterFeeBz}"  class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="3" class="td_left">2、电表迁移费</td>
                        <td colspan="3">
                            <select id="ammeter_main" class="select_fix">
                                <option value="0*${ammeterMain}">请选择</option>
                                <c:forEach begin="1" end="10" var="idx">
                                    <option value="${idx}*${ammeterMain}">${idx}&nbsp;主表</option>
                                </c:forEach>
                            </select>
                            <select id="ammeter_sub" class="select_fix">
                                <option value="0*${ammeterSub}">请选择</option>
                                <c:forEach begin="1" end="10" var="idx">
                                    <option value="${idx}*${ammeterSub}">${idx}&nbsp;副表</option>
                                </c:forEach>
                            </select>
                            <!--隐藏存储表单提交值-->
                            <input type="hidden" name="calcMoveAmmeterFee" value="${settleAccounts.calcMoveAmmeterFee}" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="moveAmmeterFee" value="${settleAccounts.moveAmmeterFee}" class="none_border width_110px"></td>
                        <td><input type="text" name="moveAmmeterFeeBz" value="${settleAccounts.moveAmmeterFeeBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="3" class="td_left">3、空调移机费</td>
                        <td colspan="3">
                            <select id="air_conditioner_shutter" class="select_fix">
                                <option value="0*${airConditionerShutter}">请选择</option>
                                <c:forEach begin="1" end="10" var="idx">
                                    <option value="${idx}*${airConditionerShutter}">${idx}&nbsp;窗机</option>
                                </c:forEach>
                            </select>
                            <select id="air_conditioner_hang" class="select_fix">
                                <option value="0*${airConditionerHang}">请选择</option>
                                <c:forEach begin="1" end="10" var="idx">
                                    <option value="${idx}*${airConditionerHang}">${idx}&nbsp;挂机</option>
                                </c:forEach>
                            </select>
                            <select id="air_conditioner_cabinet" class="select_fix">
                                <option value="0*${airConditionerCabinet}">请选择</option>
                                <c:forEach begin="1" end="10" var="idx">
                                    <option value="${idx}*${airConditionerCabinet}">${idx}&nbsp;柜机</option>
                                </c:forEach>
                            </select>
                            <!--隐藏存储表单提交值-->
                            <input type="hidden" name="calcMoveAirConditioningFee" value="${settleAccounts.calcMoveAirConditioningFee}" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="moveAirConditioningFee" value="${settleAccounts.moveAirConditioningFee}" class="none_border width_110px"></td>
                        <td><input type="text" name="moveAirConditioningFeeBz" value="${settleAccounts.moveAirConditioningFeeBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="3" class="td_left">4、热水器拆装费</td>
                        <td colspan="3"><input type="text" name="calcHotWaterCompensate" value="${settleAccounts.calcHotWaterCompensate}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="hotWaterCompensate" value="${settleAccounts.hotWaterCompensate}" class="none_border width_110px"></td>
                        <td><input type="text" name="hotWaterCompensateBz" value="${settleAccounts.hotWaterCompensateBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="3" class="td_left">5、管道煤气拆装费</td>
                        <td colspan="3"><input type="text" name="calcGasFee" value="${settleAccounts.calcGasFee}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="gasFee" value="${settleAccounts.gasFee}" class="none_border width_110px"></td>
                        <td><input type="text" name="gasFeeBz" value="${settleAccounts.gasFeeBz}" class="none_border width_70px"></td>
                    </tr>
                   <tr>
                       <td colspan="3" class="td_left">6、构建物补偿</td>
                       <td colspan="3"><input type="text" name="calcStructureCompensate" value="${settleAccounts.calcStructureCompensate}" class="none_border width_220px"></td>
                       <td colspan="2"><input type="text" name="structureCompensate" value="${settleAccounts.structureCompensate}" class="none_border width_110px"></td>
                       <td><input type="text" name="structureCompensateBz" value="${settleAccounts.structureCompensateBz}" class="none_border width_70px"></td>
                   </tr>
                    <tr>
                        <td colspan="3" class="td_left">7、其他<input type="text" name="affiliatedOtherDesc" value="${settleAccounts.affiliatedOtherDesc}" class="none_border" style="margin-top: 3px;width: 110px;float: none;">&nbsp;</td>
                        <td colspan="3"><input type="text" name="calcAffiliatedOther" value="${settleAccounts.calcAffiliatedOther}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="affiliatedOther" value="${settleAccounts.affiliatedOther}" class="none_border width_110px"></td>
                        <td><input type="text" name="affiliatedOtherBz" value="${settleAccounts.affiliatedOtherBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>六</td>
                        <td colspan="4" class="td_left">未登记建筑房屋补偿</td>
                        <td colspan="3"><input type="text" name="calcNoCheckCompensate" value="${settleAccounts.calcNoCheckCompensate}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="noCheckCompensate" value="${settleAccounts.noCheckCompensate}" class="none_border width_110px"></td>
                        <td><input type="text" name="noCheckCompensateBz" value="${settleAccounts.noCheckCompensateBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>七</td>
                        <td colspan="4" class="td_left">货币补偿补助</td>
                        <td colspan="3"><input type="text" name="calcRmbCompensate" value="${settleAccounts.calcRmbCompensate}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="rmbCompensate" value="${settleAccounts.rmbCompensate}" class="none_border width_110px"></td>
                        <td><input type="text" name="rmbCompensateBz" value="${settleAccounts.rmbCompensateBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>八</td>
                        <td colspan="4" class="td_left">生活困难补助</td>
                        <td colspan="3"><input type="text" name="calcLifeCompensate" value="${settleAccounts.calcLifeCompensate}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="lifeCompensate" value="${settleAccounts.lifeCompensate}" class="none_border width_110px"></td>
                        <td><input type="text" name="lifeCompensateBz" value="${settleAccounts.lifeCompensateBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>九</td>
                        <td colspan="4" class="td_left">住改商补助</td>
                        <td colspan="3"><input type="text" name="calcChangeCompensate" value="${settleAccounts.calcChangeCompensate}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="changeCompensate" value="${settleAccounts.changeCompensate}" class="none_border width_110px"></td>
                        <td><input type="text" name="changeCompensateBz" value="${settleAccounts.changeCompensateBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>十</td>
                        <td colspan="4" class="td_left">建筑面积补助</td>
                        <td colspan="3"><input type="text" name="calcBuildingAreaFee" value="${settleAccounts.calcBuildingAreaFee}"  class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="buildingAreaFee" value="${settleAccounts.buildingAreaFee}"  class="none_border width_110px"></td>
                        <td><input type="text" name="buildingAreaFeeBz" value="${settleAccounts.buildingAreaFeeBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>十一</td>
                        <td colspan="4" class="td_left">停产停业损失补偿</td>
                        <td colspan="3"><input type="text" name="calcSuspendBusinessFee"  value="${settleAccounts.calcSuspendBusinessFee}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="suspendBusinessFee"  value="${settleAccounts.suspendBusinessFee}" class="none_border width_110px"></td>
                        <td><input type="text" name="suspendBusinessFeeBz"  value="${settleAccounts.suspendBusinessFeeBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>十二</td>
                        <td colspan="4" class="td_left">不可移动设备设施补偿</td>
                        <td colspan="3"><input type="text" name="calcNoMoveCompensate" value="${settleAccounts.calcNoMoveCompensate}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="noMoveCompensate" value="${settleAccounts.noMoveCompensate}" class="none_border width_110px"></td>
                        <td><input type="text" name="noMoveCompensateBz" value="${settleAccounts.noMoveCompensateBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>十三</td>
                        <td colspan="4" class="td_left">搬迁奖励</td>
                        <td colspan="3"><input type="text" name="calcMoveReward" value="${settleAccounts.calcMoveReward}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="moveReward" value="${settleAccounts.moveReward}" class="none_border width_110px"></td>
                        <td><input type="text" name="moveRewardBz" value="${settleAccounts.moveRewardBz}"  class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>十四</td>
                        <td colspan="4" class="td_left">货币搬迁奖励</td>
                        <td colspan="3"><input type="text" name="calcRmbMoveReward" value="${settleAccounts.calcRmbMoveReward}"  class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="rmbMoveReward" value="${settleAccounts.rmbMoveReward}"  class="none_border width_110px"></td>
                        <td><input type="text" name="rmbMoveRewardBz" value="${settleAccounts.rmbMoveRewardBz}"  class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>十五</td>
                        <td colspan="4" class="td_left">小面积住房搬迁奖励</td>
                        <td colspan="3"><input type="text" name="calcSmallAreaReward" value="${settleAccounts.calcSmallAreaReward}"  class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="smallAreaReward" value="${settleAccounts.smallAreaReward}"  class="none_border width_110px"></td>
                        <td><input type="text" name="smallAreaRewardBz" value="${settleAccounts.smallAreaRewardBz}"  class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>十六</td>
                        <td colspan="4" class="td_left">其他<input type="text" name="otherDesc" value="${settleAccounts.otherDesc}"  class="none_border width_110px" style="clear: both;float: none;"></td>
                        <td colspan="3"><input type="text" name="calcOther" value="${settleAccounts.calcOther}"  class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="otherRmb" value="${settleAccounts.otherRmb}"  class="none_border width_110px"></td>
                        <td><input type="text" name="otherBz" value="${settleAccounts.otherBz}"  class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="5">被征收房屋补偿合计</td>
                        <td colspan="3"><input type="text" name="calcSumCompensate" value="${settleAccounts.calcSumCompensate}"   class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="sumCompensate" value="${settleAccounts.sumCompensate}"  class="none_border width_110px"></td>
                        <td><input type="text" name="sumCompensateBz" value="${settleAccounts.sumCompensateBz}"  class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td rowspan="3">仅产权调换方式</td>
                        <td colspan="3" rowspan="2">产权调换房价款</td>
                        <td style="width: 100px;">建筑面积</td>
                        <td colspan="3" style="font-size: xx-small;">其中<input type="text" name="calcm" value="${settleAccounts.calcm}"  class="none_border" style="clear: both;float:none;width: 40px;"> m<sup>2</sup>
                            与被征收房屋(<input type="text" name="price" value="${settleAccounts.price}"  class="none_border" style="clear: both;float:none;width: 50px;">元)置换互不补差价</td>
                        <td rowspan="2" colspan="2"><input type="text" name="houseMoney" value="${settleAccounts.houseMoney}"  class="none_border width_110px" style="height: 40px;"></td>
                        <td rowspan="2"><small style="line-height: 20px;font-size: xx-small;">置换建筑面积=被征收房屋套内面积*调换房系数</small></td>
                    </tr>
                    <tr>
                        <td><input type="text" name="changeArea" class="none_border width_70px" value="${settleAccounts.changeArea}" style="clear: both;float:none;width: 50px;"></td>
                        <td colspan="3"><input type="text" name="calcChangeArea" value="${settleAccounts.calcChangeArea}"  class="none_border width_220px"></td>
                    </tr>
                    <tr>
                        <td colspan="4">已抵扣安置房款</td>
                        <td colspan="3"><input type="text" name="calcDeduction" value="${settleAccounts.calcDeduction}"  class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="deduction" value="${settleAccounts.deduction}"  class="none_border width_110px"></td>
                        <td><input type="text" name="deductionBz" value="${settleAccounts.deductionBz}"  class="none_border width_70px"></td>
                    </tr>
                    <tr>
                       <td colspan="5">应付合计</td>
                        <td colspan="2"><input type="text" name="payTotal" value="${settleAccounts.payTotal}"  class="none_border" style="width: 170px;"></td>
                        <td>应收合计</td>
                        <td colspan="3"><input type="text" name="receiveTotal" value="${settleAccounts.receiveTotal}"  class="none_border" style="width: 170px;"></td>
                    </tr>
                    <tr>
                        <td colspan="8">实际支付金额(大写)：<input type="text" name="payMoney" value="${settleAccounts.payMoney}"  class="none_border" style="width: 320px;clear: both;float: none;"></td>
                        <td colspan="2" rowspan="2">被征收人签字</td>
                        <td rowspan="2"></td>
                    </tr>
                    <tr>
                        <td colspan="8">实际应收金额(大写)：<input type="text" name="receiveMoney" value="${settleAccounts.receiveMoney}"  class="none_border" style="width: 320px;clear: both;float: none;"></td>
                    </tr>
                </table>
            </dl>

        </div>

        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <!--  主键Id -->
                            <input type="hidden" name="id" value="${settleAccounts.id}">
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
    function customvalidXxx(element) {
        if ($(element).val() == "xxx")
            return false;
        return true;
    }

</script>
