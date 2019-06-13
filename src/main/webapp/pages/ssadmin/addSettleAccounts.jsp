<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="comm/include.inc.jsp" %>
<%--<h2 class="contentTitle">添加资金结算单</h2>--%>
<script type="text/javascript" src="${oss_url}/static/ssadmin/js/business/add_settleAccounts.js"/>
<link href="${oss_url}/static/ssadmin/css/add_settleAccounts.css" rel="stylesheet" type="text/css" />

<div class="pageContent">

    <form method="post" action="ssadmin/settleAccounts/add.html"
          class="pageForm required-validate" enctype="multipart/form-data"
          onsubmit="return iframeCallback(this, dialogAjaxDone);">

        <div class="pageFormContent nowrap" layoutH="97">
            <dl>
                <dt style="width: 80px;">补偿方式：</dt>
                <dt>
                    <select name="choose_type" style="width: 100px;margin-top: 2px;" class="required" >
                        <option value="">请选择</option>
                        <option value="${moveHouseRmb}">货币补偿</option>
                        <option value="${moveHouseSwap}">产权调换</option>
                    </select>
                </dt>
            </dl>
            <dl>
                <dt style="width: 80px;">项目名称:</dt>
                <dt style="width: 300px;">
                    <input type="text" name="projectName" maxlength="50" class="required" size="40"/>
                </dt>
                <dt style="width: 40px;">编号：</dt>
                <dt>
                    <input type="text" name="cardNo" maxlength="50" class="required" size="10"/>
                </dt>
            </dl>
            <dl>
                <table  border="1" class="tableInfo">
                    <tr>
                        <td colspan="3">被征收人</td>
                        <td colspan="2" style="width: 100px;"><input type="text" name="houseOwner" class="none_border"></td>
                        <td style="width: 60px;">电话</td>
                        <td style="width: 100px;"><input type="text" name="phone" class="none_border"></td>
                        <td style="width: 60px;">房屋用途</td>
                        <td style="width: 60px;">建筑面积</td>
                        <td style="width: 60px;">套内面积</td>
                        <td style="width: 80px;">房屋评估单价</td>
                    </tr>
                    <tr>
                        <td colspan="3">公房承租人</td>
                        <td colspan="2"><input type="text" name="lessee" class="none_border"></td>
                        <td>电话</td>
                        <td><input type="text" name="lesseePhone" class="none_border"></td>
                        <td><input type="text" name="useing" class="samll_input_text"></td>
                        <td><input type="text" name="checkInArea" class="samll_input_text" ></td>
                        <td><input type="text" name="inArea" class="samll_input_text" ></td>
                        <td><input type="text" name="assessPrice" class="samll_input_text"></td>
                    </tr>
                    <tr>
                        <td colspan="5">被征收房屋地址</td>
                        <td colspan="6"><input type="text" name="address" class="none_border" style="width: 400px;"></td>
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
                        <td colspan="3">
                            <input type="text" name="calcValueCompensateArea" class="required none_border" placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcValueCompensatePrice" class="required none_border" placeholder="单价" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcValueCompensateProportion" class="required none_border" placeholder="比例" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏计算公式-->
                            <input type="hidden" name="calcValueCompensate" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="valueCompensate" class="none_border width_110px"></td>
                        <td><input type="text" name="valueCompensateBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>二</td>
                        <td colspan="4">装修折旧补偿</td>
                        <td colspan="3">
                            <input type="text" name="calcDecorationCompensateArea" class="required none_border" placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcDecorationCompensatePrice" class="required none_border" placeholder="折旧单价" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏计算公式-->
                            <input type="hidden" name="calcDecorationCompensate" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="decorationCompensate" class="none_border width_110px"></td>
                        <td><input type="text" name="decorationCompensateBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>三</td>
                        <td colspan="4" class="td_left">搬迁补偿（搬家费）</td>
                        <td colspan="3">
                            <select name="calcMoveHouseFee" class="select_fix" style="width: 80px;">
                                <option value="">请选择</option>
                                <option value="${moveHouseRmb}">货币补偿</option>
                                <option value="${moveHouseSwap}">产权调换</option>
                            </select>
                        </td>
                        <td colspan="2"><input type="text" name="moveHouseFee" class="none_border width_110px"></td>
                        <td><input type="text" name="moveHouseFeeBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>四</td>
                        <td colspan="4" class="td_left">临时安置补偿（过渡费）</td>
                        <td colspan="3">
                            <input type="text" name="calcInterimFeeArea" class="required none_border" placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcInterimFeePrice" class="required none_border" placeholder="金额" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcInterimFeeMonth" class="required none_border" placeholder="月数" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏计算公式-->
                            <input type="hidden" name="calcInterimFee" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="interimFee" class="none_border width_110px"></td>
                        <td><input type="text" name="interimFeeBz" class="none_border width_70px"></td>
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
                            <input type="hidden" name="calcMoveWaterMeterFee" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="moveWaterMeterFee" class="none_border width_110px"></td>
                        <td><input type="text" name="moveWaterMeterFeeBz" class="none_border width_70px"></td>
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
                            <input type="hidden" name="calcMoveAmmeterFee" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="moveAmmeterFee" class="none_border width_110px"></td>
                        <td><input type="text" name="moveAmmeterFeeBz" class="none_border width_70px"></td>
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
                            <input type="hidden" name="calcMoveAirConditioningFee" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="moveAirConditioningFee" class="none_border width_110px"></td>
                        <td><input type="text" name="moveAirConditioningFeeBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="3" class="td_left">4、热水器拆装费</td>
                        <td colspan="3">
                            <select id="water_heater_type" class="select_fix">
                                <option value="0">请选择</option>
                                <option value="1">电热水器</option>
                                <option value="2">太阳能热水器</option>
                            </select>
                            <!--电热水器选择-->
                            <select id="sel_water_heater" class="select_fix" style="display: none">
                                <option value="0*${waterHeater}">请选择数量</option>
                                <c:forEach begin="1" end="10" var="idx">
                                    <option value="${idx}*${waterHeater}">${idx}&nbsp;个</option>
                                </c:forEach>
                            </select>
                            <!--太阳能热水器-->
                            <input type="text" name="calcHotWaterCompensateMoney" class="none_border" placeholder="金额" size="5" style="margin-top: 2px;margin-left: 5px;display: none"/>
                            <input type="text" name="calcHotWaterCompensateConvert" value="${solarWaterHeaters}" class="none_border" placeholder="折算比例" size="5" style="margin-top: 2px;margin-left: 5px;display: none"/>
                            <!--隐藏存储表单提交值-->
                            <input type="hidden" name="calcHotWaterCompensate" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="hotWaterCompensate" class="none_border width_110px"></td>
                        <td><input type="text" name="hotWaterCompensateBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="3" class="td_left">5、管道煤气拆装费</td>
                        <td colspan="3"><input type="text" name="calcGasFee" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="gasFee" class="none_border width_110px"></td>
                        <td><input type="text" name="gasFeeBz" class="none_border width_70px"></td>
                    </tr>
                   <tr>
                       <td colspan="3" class="td_left">6、构建物补偿</td>
                       <td colspan="3">
                            <!--无烟灶台选择-->
                           <label style="width: 55px;margin-top: 3px;">无烟灶台: </label>
                           <select id="sel_stove" class="select_fix" style="">
                               <option value="0*${smokeFreeStove}">请选择数量</option>
                               <c:forEach begin="1" end="5" var="idx">
                                   <option value="${idx}*${smokeFreeStove}">${idx}&nbsp;个</option>
                               </c:forEach>
                           </select><br/>
                           <!--暗楼选择-->
                           <label style="width: 55px;">暗&nbsp;&nbsp;&nbsp;&nbsp;楼: </label>
                           <input type="text" name="calcStructureCompensateArea" class="none_border" placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;margin-bottom: 3px;"/>
                           <input type="text" name="calcStructureCompensatePrice" class="none_border" placeholder="单价" size="5" style="margin-top: 2px;margin-left: 5px;margin-bottom: 3px;"/>
                           <!--隐藏存储表单提交值-->
                           <input type="hidden" name="calcStructureCompensate" class="none_border width_220px">
                       </td>
                       <td colspan="2"><input type="text" name="structureCompensate" class="none_border width_110px"></td>
                       <td><input type="text" name="structureCompensateBz" class="none_border width_70px"></td>
                   </tr>
                    <tr>
                        <td colspan="3" class="td_left">7、其他<input type="text" name="affiliatedOtherDesc" class="none_border" style="margin-top: 3px;width: 110px;float: none;">&nbsp;</td>
                        <td colspan="3"><input type="text" name="calcAffiliatedOther" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="affiliatedOther" class="none_border width_110px"></td>
                        <td><input type="text" name="affiliatedOtherBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>六</td>
                        <td colspan="4" class="td_left">未登记建筑房屋补偿</td>
                        <td colspan="3">
                            <input type="text" name="calcNoCheckCompensateArea" class="none_border" placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcNoCheckCompensatePrice" class="none_border" placeholder="单价" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcNoCheckCompensateProportion" class="none_border" placeholder="比例" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏计算公式-->
                            <input type="hidden" name="calcNoCheckCompensate" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="noCheckCompensate" class="none_border width_110px"></td>
                        <td><input type="text" name="noCheckCompensateBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>七</td>
                        <td colspan="4" class="td_left">货币补偿补助</td>
                        <td colspan="3" id="rmbCompensate_td1">
                            <input type="text" name="calcRmbCompensateArea" class="none_border" placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcRmbCompensatePrice" class="none_border" placeholder="单价" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcRmbCompensateProportion" class="none_border" placeholder="比例" value="${rmbCompensateProportion}" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏计算公式-->
                            <input type="hidden" name="calcRmbCompensate" class="none_border width_220px">
                        </td>
                        <td colspan="2" id="rmbCompensate_td2"><input type="text" name="rmbCompensate" class="none_border width_110px"></td>
                        <td id="rmbCompensate_td3"><input type="text" name="rmbCompensateBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>八</td>
                        <td colspan="4" class="td_left">生活困难补助</td>
                        <td colspan="3" id="lifeCalcTd">
                            <input type="checkbox" name="diseaseSubsidy" value="${diseaseSubsidy}"/>重症&nbsp;&nbsp;
                            <input type="checkbox" name="disabilitySubsidy" value="${disabilitySubsidy}"/>残疾&nbsp;&nbsp;
                            <input type="checkbox" name="basicLivingSubsidy" value="${basicLivingSubsidy}"/>低保&nbsp;&nbsp;
                            <!--隐藏选择值-->
                            <input type="hidden" name="calcLifeCompensate" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="lifeCompensate" class="none_border width_110px"></td>
                        <td><input type="text" name="lifeCompensateBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>九</td>
                        <td colspan="4" class="td_left">住改商补助</td>
                        <td colspan="3">
                            <input type="text" name="calcChangeCompensateArea" class="none_border" placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcChangeCompensatePrice" class="none_border" placeholder="单价" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcChangeCompensateProportion" class="none_border" placeholder="比例" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏存储值-->
                            <input type="hidden" name="calcChangeCompensate" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="changeCompensate" class="none_border width_110px"></td>
                        <td><input type="text" name="changeCompensateBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>十</td>
                        <td colspan="4" class="td_left">建筑面积补助</td>
                        <td colspan="3"><input type="text" name="calcBuildingAreaFee" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="buildingAreaFee" class="none_border width_110px"></td>
                        <td><input type="text" name="buildingAreaFeeBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>十一</td>
                        <td colspan="4" class="td_left">停产停业损失补偿</td>
                        <td colspan="3">
                            <select name="sel_calcSuspendBusinessFee" class="select_fix" style="width: 120px;">
                                <option value="0">请选择</option>
                                <option value="${suspendBusinessProportion}">生产经营性用房</option>
                            </select>
                            <input type="hidden" name="calcSuspendBusinessFee" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="suspendBusinessFee" class="none_border width_110px"></td>
                        <td><input type="text" name="suspendBusinessFeeBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>十二</td>
                        <td colspan="4" class="td_left">不可移动设备设施补偿</td>
                        <td colspan="3"><input type="text" name="calcNoMoveCompensate" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="noMoveCompensate" class="none_border width_110px"></td>
                        <td><input type="text" name="noMoveCompensateBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>十三</td>
                        <td colspan="4" class="td_left">搬迁奖励</td>
                        <td colspan="3">
                            <select name="calcMoveReward" class="select_fix" style="width: 120px;">
                                <option value="">请选择</option>
                                <option value="${oneMoveReward}">第一奖励期</option>
                                <option value="${twoMoveReward}">第二奖励期</option>
                                <option value="${threeMoveReward}">第三奖励期</option>
                            </select>
                        </td>
                        <td colspan="2"><input type="text" name="moveReward" class="none_border width_110px"></td>
                        <td><input type="text" name="moveRewardBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>十四</td>
                        <td colspan="4" class="td_left">货币搬迁奖励</td>
                        <td colspan="3" id="rmbMoveReward_td1">
                            <input type="text" name="calcRmbMoveRewardArea" class="none_border" placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcRmbMoveRewardPrice" class="none_border" placeholder="单价" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcRmbMoveRewardProportion" class="none_border" placeholder="比例" value="${rmbMoveRewardProportion}" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏计算公式-->
                            <input type="hidden" name="calcRmbMoveReward" class="none_border width_220px">
                        </td>
                        <td colspan="2" id="rmbMoveReward_td2"><input type="text" name="rmbMoveReward" class="none_border width_110px"></td>
                        <td id="rmbMoveReward_td3"><input type="text" name="rmbMoveRewardBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>十五</td>
                        <td colspan="4" class="td_left">小面积住房搬迁奖励</td>
                        <td colspan="3"><input type="text" name="calcSmallAreaReward" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="smallAreaReward" class="none_border width_110px"></td>
                        <td><input type="text" name="smallAreaRewardBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>十六</td>
                        <td colspan="4" class="td_left">其他<input type="text" name="otherDesc" class="none_border width_110px" style="clear: both;float: none;"></td>
                        <td colspan="3"><input type="text" name="calcOther" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="otherRmb" class="none_border width_110px"></td>
                        <td><input type="text" name="otherBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="5">被征收房屋补偿合计</td>
                        <td colspan="3"><input type="text" name="calcSumCompensate" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="sumCompensate" class="none_border width_110px"></td>
                        <td><input type="text" name="sumCompensateBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td rowspan="3">仅产权调换方式</td>
                        <td colspan="3" rowspan="2">产权调换房价款</td>
                        <td style="width: 100px;">建筑面积</td>
                        <td colspan="3" style="font-size: xx-small;">其中<input type="text" name="calcm" class="none_border" style="clear: both;float:none;width: 40px;"> m<sup>2</sup>
                            与被征收房屋(<input type="text" name="price" class="none_border" style="clear: both;float:none;width: 50px;">元)置换互不补差价</td>
                        <td rowspan="2" colspan="2"><input type="text" name="houseMoney" class="none_border width_110px" style="height: 40px;"></td>
                        <td rowspan="2"><small style="line-height: 20px;font-size: xx-small;">置换建筑面积=被征收房屋套内面积*调换房系数</small></td>
                    </tr>
                    <tr>
                        <td><input type="text" name="changeArea" class="none_border width_70px" style="clear: both;float:none;width: 50px;"></td>
                        <td colspan="3"><input type="text" name="calcChangeArea" class="none_border width_220px"></td>
                    </tr>
                    <tr>
                        <td colspan="4">已抵扣安置房款</td>
                        <td colspan="3"><input type="text" name="calcDeduction" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="deduction" class="none_border width_110px"></td>
                        <td><input type="text" name="deductionBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                       <td colspan="5">应付合计</td>
                        <td colspan="2"><input type="text" name="payTotal" class="none_border" style="width: 170px;"></td>
                        <td>应收合计</td>
                        <td colspan="3"><input type="text" name="receiveTotal" class="none_border" style="width: 170px;"></td>
                    </tr>
                    <tr>
                        <td colspan="8">实际支付金额(大写)：<input type="text" name="payMoney" class="none_border" style="width: 320px;clear: both;float: none;"></td>
                        <td colspan="2" rowspan="2">被征收人签字</td>
                        <td rowspan="2"></td>
                    </tr>
                    <tr>
                        <td colspan="8">实际应收金额(大写)：<input type="text" name="receiveMoney" class="none_border" style="width: 320px;clear: both;float: none;"></td>
                    </tr>
                </table>
            </dl>

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


<script type="text/javascript">
    function customvalidXxx(element) {
        if ($(element).val() == "xxx")
            return false;
        return true;
    }

</script>
