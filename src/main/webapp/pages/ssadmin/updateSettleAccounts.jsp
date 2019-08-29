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
                <dt style="width: 80px;">补偿方式：</dt>
                <dd style="color: red;width: 310px;">
                    <label><input type="radio" name="compensateType" value="0" checked="checked"/>&nbsp;货币补偿</label>
                    <label><input type="radio" name="compensateType" value="1"/>&nbsp;产权交换</label>
                </dd>
                <dt style="width: 80px;color: red;">所属片区:</dt>
                <dt style="width: 120px;">
                    <select name="areaId" style="width: 120px;">
                        <option value="">请选择</option>
                        <c:forEach items="${areaList}" var="area">
                            <option value="${area.id}" <c:if test="${settleAccounts.areaId==area.id }">selected="selected"</c:if>>${area.name}</option>
                        </c:forEach>
                    </select>
                </dt>
            </dl>
            <dl>
                <dt style="width: 80px;">项目名称:</dt>
                <dt style="width: 300px;">
                    <input type="text" name="projectName" value="${settleAccounts.projectName}" maxlength="50" class="required" size="40"/>
                </dt>
                <dt style="width: 40px;">编号：</dt>
                <dt>
                    <input type="text" name="cardNo" value="${settleAccounts.cardNo}" maxlength="50" class="required" size="10"/>
                </dt>
            </dl>
            <dl>
                <table  border="1" class="tableInfo">
                    <tr>
                        <td colspan="5">被征收人</td>
                        <td colspan="2" style="width: 100px;"><input type="text" name="houseOwner" value="${settleAccounts.houseOwner}" class="none_border"></td>
                        <td style="width: 60px;">电话</td>
                        <td style="width: 100px;"><input type="text" name="phone" value="${settleAccounts.phone}" class="none_border"></td>
                        <td style="width: 80px;">房屋用途</td>
                        <td style="width: 251px;">建筑面积</td>
                        <td colspan="2">未经登记建筑面积</td>
                        <td style="width: 80px;">房屋评估单价</td>
                    </tr>
                    <tr>
                        <td colspan="5">公房承租人</td>
                        <td colspan="2"><input type="text" name="lessee" value="${settleAccounts.lessee}" class="none_border"></td>
                        <td>电话</td>
                        <td><input type="text" name="lesseePhone" value="${settleAccounts.lesseePhone}" class="none_border"></td>
                        <td><input type="text" name="useing" value="${settleAccounts.useing}" class="none_border" style="width: 50px;"></td>
                        <td>
                            <input type="text" name="certifiedArea"  value="${settleAccounts.certifiedArea}" placeholder="有证" size="4" style="margin-left: 2px;">
                            <input type="text" name="noRegisterLegalArea"  value="${settleAccounts.noRegisterLegalArea}" placeholder="未登记合法" size="6" style="margin-left: 2px;">
                            <input type="text" name="historyLegacyArea" value="${settleAccounts.historyLegacyArea}"  placeholder="历史遗留" size="5" style="margin-left: 2px;">
                            <!-- 隐藏值 -->
                            <input type="hidden" name="checkInArea" value="${settleAccounts.checkInArea}"/>
                        </td>
                        <td colspan="2"><input type="text" name="noCheckinArea" value="${settleAccounts.noCheckinArea}" class="none_border width_110px" ></td>
                        <td><input type="text" name="assessPrice" value="${settleAccounts.assessPrice}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="7">被征收房屋地址</td>
                        <td colspan="7"><input type="text" name="address" value="${settleAccounts.address}" class="none_border" style="width: 400px;"></td>
                    </tr>
                    <%--<tr>
                        <td colspan="7">被征收人身份证号</td>
                        <td colspan="7"><input type="text" name="identityNo" value="${settleAccounts.identityNo}" class="none_border" style="width: 400px;"></td>
                    </tr>--%>
                    <tr>
                        <td style="width: 35px;">序号</td>
                        <td colspan="6">补偿内容</td>
                        <td colspan="4">计算公式</td>
                        <td colspan="2">金额(元)</td>
                        <td>备注</td>
                    </tr>
                    <tr>
                        <td rowspan="3">一</td>
                        <td rowspan="3">房屋价值补偿</td>
                        <td colspan="5" class="td_left">1、有证房屋补偿</td>
                        <td colspan="4">
                            <input type="text" name="calcValueCompensateArea"  class="required none_border" placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcValueCompensatePrice"  class="required none_border" placeholder="单价" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcValueCompensateProportion" class="none_border" placeholder="比例" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏计算公式-->
                            <input type="hidden" name="calcValueCompensate" value="${settleAccounts.calcValueCompensate}" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="valueCompensate" value="${settleAccounts.valueCompensate}" class="none_border width_110px"></td>
                        <td><input type="text" name="valueCompensateBz" value="${settleAccounts.valueCompensateBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="5" class="td_left">2、未经登记的合法建筑补偿</td>
                        <td colspan="4">
                            <input type="text" name="calcNoRegisterLegalArea" placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcNoRegisterLegalPrice" placeholder="单价" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcNoRegisterLegalProportion" placeholder="比例" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏计算公式-->
                            <input type="hidden" name="calcNoRegisterLegal" value="${settleAccounts.calcNoRegisterLegal}" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="noRegisterLegal" value="${settleAccounts.noRegisterLegal}" class="none_border width_110px"></td>
                        <td><input type="text" name="noRegisterLegalBz" value="${settleAccounts.noRegisterLegalBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="5" class="td_left">3、历史遗留无证房补偿</td>
                        <td colspan="4">
                            <input type="text" name="calcHistoryLegacyArea"  placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcHistoryLegacyPrice" placeholder="单价" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcHistoryLegacyProportion" placeholder="比例" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏计算公式-->
                            <input type="hidden" name="calcHistoryLegacy" value="${settleAccounts.calcHistoryLegacy}" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="historyLegacy" value="${settleAccounts.historyLegacy}" class="none_border width_110px"></td>
                        <td><input type="text" name="historyLegacyBz" value="${settleAccounts.historyLegacyBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>二</td>
                        <td colspan="6" class="td_left">装修补偿</td>
                        <td colspan="4">
                            <input type="text" name="calcDecorationCompensateArea"  placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcDecorationCompensatePrice" placeholder="折旧单价" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcDecorationCompensateOther" placeholder="其他公式" size="21" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏计算公式-->
                            <input type="hidden" name="calcDecorationCompensate" value="${settleAccounts.calcDecorationCompensate}" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="decorationCompensate" value="${settleAccounts.decorationCompensate}" class="none_border width_110px"></td>
                        <td><input type="text" name="decorationCompensateBz" value="${settleAccounts.decorationCompensateBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>三</td>
                        <td colspan="6" class="td_left">房屋搬迁补偿（搬家费）</td>
                        <td colspan="4">
                            <select name="sel_calcMoveHouseFee" class="select_fix" style="width: 80px;height: 21px;">
                                <option value="">请选择</option>
                                <option value="${moveHouseRmb}">住宅</option>
                                <option value="0">非住宅</option>
                            </select>
                            <input type="text" name="calcMoveHouseFee" value="${settleAccounts.calcMoveHouseFee}" class="required none_border width_110px" style="margin-top: 2px;">
                        </td>
                        <td colspan="2"><input type="text" name="moveHouseFee" value="${settleAccounts.moveHouseFee}" class="none_border width_110px"></td>
                        <td><input type="text" name="moveHouseFeeBz" value="${settleAccounts.moveHouseFeeBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>四</td>
                        <td colspan="6" class="td_left">临时安置补偿（过渡费）</td>
                        <td colspan="4">
                            <input type="text" name="calcInterimFeeArea"  placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcInterimFeePrice"  placeholder="金额" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcInterimFeeMonth"  placeholder="月数" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcInterimFeeOther" placeholder="其他公式" size="21" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏计算公式-->
                            <input type="hidden" name="calcInterimFee" value="${settleAccounts.calcInterimFee}" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="interimFee" value="${settleAccounts.interimFee}" class="none_border width_110px"></td>
                        <td><input type="text" name="interimFeeBz" value="${settleAccounts.interimFeeBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>五</td>
                        <td colspan="6" class="td_left">保底补偿</td>
                        <td colspan="4">
                            <select name="sel_guarantee" class="select_fix" style="width: 120px;">
                                <option value="0">无</option>
                                <option value="${selGuarantee}">保底</option>
                            </select>
                            <input type="text" name="calcGuarantee" value="${settleAccounts.calcGuarantee}" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="guarantee" value="${settleAccounts.guarantee}" class="none_border width_110px"></td>
                        <td><input type="text" name="guaranteeBz" value="${settleAccounts.guaranteeBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>六</td>
                        <td colspan="6" class="td_left">停产停业损失补偿</td>
                        <td colspan="4">
                            <select name="sel_calcSuspendBusinessFee" class="select_fix" style="width: 120px;">
                                <option value="0">请选择</option>
                                <option value="${suspendBusinessProportion}">生产经营性用房</option>
                            </select>
                            <input type="hidden" name="calcSuspendBusinessFee" value="${settleAccounts.calcSuspendBusinessFee}" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="suspendBusinessFee" value="${settleAccounts.suspendBusinessFee}" class="none_border width_110px"></td>
                        <td><input type="text" name="suspendBusinessFeeBz" value="${settleAccounts.suspendBusinessFeeBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td rowspan="11">七</td>
                        <td rowspan="11" style="width: 35px;">附属设施补偿</td>
                        <td colspan="5" class="td_left">1、水表迁移费</td>
                        <td colspan="4">
                            <select id="water_meter_main" class="select_fix">
                                <option value="0*${waterMeterMain}">主表选择</option>
                                <c:forEach begin="1" end="10" var="idx">
                                    <option value="${idx}*${waterMeterMain}">${idx}&nbsp;主表</option>
                                </c:forEach>
                            </select>
                            <select id="water_meter_sub" class="select_fix">
                                <option value="0*${waterMeterSub}">副表选择</option>
                                <c:forEach begin="1" end="10" var="idx">
                                    <option value="${idx}*${waterMeterSub}">${idx}&nbsp;副表</option>
                                </c:forEach>
                            </select>
                            <!--隐藏存储表单提交值-->
                            <input type="hidden" name="calcMoveWaterMeterFee" value="${settleAccounts.calcMoveWaterMeterFee}" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="moveWaterMeterFee"  value="${settleAccounts.moveWaterMeterFee}" class="none_border width_110px"></td>
                        <td><input type="text" name="moveWaterMeterFeeBz"  value="${settleAccounts.moveWaterMeterFeeBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="5" class="td_left">2、电表迁移费</td>
                        <td colspan="4">
                            <select id="ammeter_main" class="select_fix">
                                <option value="0*${ammeterMain}">独表选择</option>
                                <c:forEach begin="1" end="10" var="idx">
                                    <option value="${idx}*${ammeterMain}">${idx}&nbsp;民用独表</option>
                                </c:forEach>
                            </select>
                            <select id="ammeter_sub" class="select_fix">
                                <option value="0*${ammeterSub}">分表选择</option>
                                <c:forEach begin="1" end="10" var="idx">
                                    <option value="${idx}*${ammeterSub}">${idx}&nbsp;分表</option>
                                </c:forEach>
                            </select>
                            <select id="ammeter_sa" class="select_fix">
                                <option value="0*${ammeterSa}">三相表选择</option>
                                <c:forEach begin="1" end="10" var="idx">
                                    <option value="${idx}*${ammeterSa}">${idx}&nbsp;三相电表</option>
                                </c:forEach>
                            </select>
                            <select id="ammeter_time" class="select_fix">
                                <option value="0*${ammeterTime}">分时表选择</option>
                                <c:forEach begin="1" end="10" var="idx">
                                    <option value="${idx}*${ammeterTime}">${idx}&nbsp;分时表</option>
                                </c:forEach>
                            </select>
                            <!--隐藏存储表单提交值-->
                            <input type="hidden" name="calcMoveAmmeterFee" value="${settleAccounts.calcMoveAmmeterFee}" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="moveAmmeterFee" value="${settleAccounts.moveAmmeterFee}" class="none_border width_110px"></td>
                        <td><input type="text" name="moveAmmeterFeeBz" value="${settleAccounts.moveAmmeterFeeBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="5" class="td_left">3、空调移机费</td>
                        <td colspan="4">
                            <select id="air_conditioner_shutter" class="select_fix">
                                <option value="0*${airConditionerShutter}">窗机选择</option>
                                <c:forEach begin="1" end="10" var="idx">
                                    <option value="${idx}*${airConditionerShutter}">${idx}&nbsp;窗机</option>
                                </c:forEach>
                            </select>
                            <select id="air_conditioner_hang" class="select_fix">
                                <option value="0*${airConditionerHang}">挂机选择</option>
                                <c:forEach begin="1" end="10" var="idx">
                                    <option value="${idx}*${airConditionerHang}">${idx}&nbsp;挂机</option>
                                </c:forEach>
                            </select>
                            <select id="air_conditioner_cabinet" class="select_fix">
                                <option value="0*${airConditionerCabinet}">柜机选择</option>
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
                        <td colspan="5" class="td_left">4、热水器拆装费</td>
                        <td colspan="4">
                            <!--电热水器选择-->
                            <select name="sel_water_heater" class="select_fix" style="width: 155px">
                                <option value="0*${waterHeater}">请选择电热水器数量</option>
                                <c:forEach begin="1" end="10" var="idx">
                                    <option value="${idx}*${waterHeater}">${idx}&nbsp;个</option>
                                </c:forEach>
                            </select>

                            <!--太阳能热水器-->
                            <label style="width: 82px;margin-top: 2px;margin-left: 20px;">太阳能热水器: </label>
                            <input type="text" name="calcHotWaterCompensateMoney" class="none_border" placeholder="金额" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcHotWaterCompensateConvert" value="${solarWaterHeaters}" class="none_border" placeholder="折算比例" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏存储表单提交值-->
                            <input type="hidden" name="calcHotWaterCompensate" value="${settleAccounts.calcHotWaterCompensate}" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="hotWaterCompensate" value="${settleAccounts.hotWaterCompensate}" class="none_border width_110px"></td>
                        <td><input type="text" name="hotWaterCompensateBz" value="${settleAccounts.hotWaterCompensateBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="5" class="td_left">5、管道煤气拆装费</td>
                        <td colspan="4"><input type="text" name="calcGasFee" value="${settleAccounts.calcGasFee}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="gasFee" value="${settleAccounts.gasFee}" class="none_border width_110px"></td>
                        <td><input type="text" name="gasFeeBz" value="${settleAccounts.gasFeeBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="2" rowspan="6" class="td_left">6、构建物补偿</td>
                        <td colspan="3" class="td_left">（1）结构内阳台</td>
                        <td colspan="4">
                            <input type="text" name="structureBalconyArea" class="none_border" placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="structureBalconyPrice" class="none_border" placeholder="单价" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="structureBalconyProportion" class="none_border" placeholder="比例" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏存储值-->
                            <input type="hidden" name="calcStructureBalcony" value="${settleAccounts.calcStructureBalcony}" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="structureBalcony" value="${settleAccounts.structureBalcony}" class="none_border width_110px"></td>
                        <td><input type="text" name="structureBalconyBz" value="${settleAccounts.structureBalconyBz}" class="none_border width_70px"></td>
                        <%--<td colspan="3">
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
                        <td><input type="text" name="structureCompensateBz" class="none_border width_70px"></td>--%>
                    </tr>
                    <tr>
                        <td colspan="3" class="td_left">（2）外挑搭建</td>
                        <td colspan="4">
                            <input type="text" name="structureBuildArea" class="none_border" placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="structureBuildPrice" class="none_border" placeholder="单价" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="structureBuildProportion" class="none_border" placeholder="比例" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏存储值-->
                            <input type="hidden" name="calcStructureBuild" value="${settleAccounts.calcStructureBuild}" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="structureBuild" value="${settleAccounts.structureBuild}" class="none_border width_110px"></td>
                        <td><input type="text" name="structureBuildBz" value="${settleAccounts.structureBuildBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="3" class="td_left">（3）暗楼</td>
                        <td colspan="4">
                            <input type="text" name="structureDarkArea" class="none_border" placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="structureDarkPrice" class="none_border" placeholder="单价" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="structureDarkProportion" class="none_border" placeholder="比例" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏存储值-->
                            <input type="hidden" name="calcStructureDark" value="${settleAccounts.calcStructureDark}" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="structureDark" value="${settleAccounts.structureDark}" class="none_border width_110px"></td>
                        <td><input type="text" name="structureDarkBz" value="${settleAccounts.structureDarkBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="3" class="td_left">（4）夹层（假层）</td>
                        <td colspan="4">
                            <input type="text" name="structureMezzanineArea" class="none_border" placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="structureMezzaninePrice" class="none_border" placeholder="单价" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="structureMezzanineProportion" class="none_border" placeholder="比例" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏存储值-->
                            <input type="hidden" name="calcStructureMezzanine" value="${settleAccounts.calcStructureMezzanine}"  class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="structureMezzanine" value="${settleAccounts.structureMezzanine}"  class="none_border width_110px"></td>
                        <td><input type="text" name="structureMezzanineBz" value="${settleAccounts.structureMezzanineBz}"  class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="3" class="td_left">（5）楼顶搭建</td>
                        <td colspan="4">
                            <input type="text" name="structureRoofArea" class="none_border" placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="structureRoofPrice" class="none_border" placeholder="单价" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="structureRoofProportion" class="none_border" placeholder="比例" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏存储值-->
                            <input type="hidden" name="calcStructureRoof" value="${settleAccounts.calcStructureRoof}" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="structureRoof" value="${settleAccounts.structureRoof}" class="none_border width_110px"></td>
                        <td><input type="text" name="structureRoofBz" value="${settleAccounts.structureRoofBz}" class="none_border width_70px"></td>
                    </tr>
                     <tr>
                         <td colspan="3" class="td_left">（6）其他<input type="text" name="affiliatedOtherDesc" value="${settleAccounts.affiliatedOtherDesc}" class="none_border" style="margin-top: 3px;width: 80px;float: none;">&nbsp;</td>
                         <td colspan="4"><input type="text" name="calcAffiliatedOther" value="${settleAccounts.calcAffiliatedOther}" class="none_border width_220px"></td>
                         <td colspan="2"><input type="text" name="affiliatedOther" value="${settleAccounts.affiliatedOther}" class="none_border width_110px"></td>
                         <td><input type="text" name="affiliatedOtherBz" value="${settleAccounts.affiliatedOtherBz}" class="none_border width_70px"></td>
                     </tr>
                    <tr>
                        <td>八</td>
                        <td colspan="6" class="td_left">改变房屋用途补助</td>
                        <td colspan="4">
                            <input type="text" name="calcChangeCompensateArea"  class="none_border" placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcChangeCompensatePrice" class="none_border" placeholder="实际用途单价" size="8" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcChangeCompensateOldPrice" class="none_border" placeholder="旧房单价" size="6" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcChangeCompensateProportion" class="none_border" placeholder="比例" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcChangeCompensateOther" class="none_border"  size="21" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏存储值-->
                            <input type="hidden" name="calcChangeCompensate" value="${settleAccounts.calcChangeCompensate}" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="changeCompensate" value="${settleAccounts.changeCompensate}" class="none_border width_110px"></td>
                        <td><input type="text" name="changeCompensateBz" value="${settleAccounts.changeCompensateBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>九</td>
                        <td colspan="6" class="td_left">货币补偿补助（或产权调换补助）</td>
                        <td colspan="4" id="rmbCompensate_td1">
                            <input type="hidden" name="rmbCompensateProportion" value="${rmbCompensateProportion}"/>
                            <%--<input type="text" name="calcRmbCompensateArea" class="none_border" placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcRmbCompensatePrice" class="none_border" placeholder="单价" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcRmbCompensateProportion" class="none_border" placeholder="比例" value="${rmbCompensateProportion}" size="5" style="margin-top: 2px;margin-left: 5px;"/>--%>
                            <!--隐藏计算公式-->
                            <input type="text" name="calcRmbCompensate" value="${settleAccounts.calcRmbCompensate}" class="none_border width_220px">
                        </td>
                        <td colspan="2" id="rmbCompensate_td2"><input type="text" name="rmbCompensate" value="${settleAccounts.rmbCompensate}" class="none_border width_110px"></td>
                        <td id="rmbCompensate_td3"><input type="text" name="rmbCompensateBz" value="${settleAccounts.rmbCompensateBz}" class="none_border width_70px"></td>
                    </tr>
                    <%-- <tr>
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
                     </tr>--%>
                    <tr>
                        <td>十</td>
                        <td colspan="6" class="td_left">小户型住房困难补助(选择货币补偿时)</td>
                        <td colspan="4"><input type="text" name="calcSmallAreaReward" value="${settleAccounts.calcSmallAreaReward}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="smallAreaReward" value="${settleAccounts.smallAreaReward}" class="none_border width_110px"></td>
                        <td><input type="text" name="smallAreaRewardBz" value="${settleAccounts.smallAreaRewardBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>十一</td>
                        <td colspan="6" class="td_left">生活困难补助</td>
                        <td colspan="4" id="lifeCalcTd" class="td_left">
                            <input type="checkbox" name="diseaseSubsidy" value="${diseaseSubsidy}"/>重症&nbsp;&nbsp;
                            <input type="checkbox" name="disabilitySubsidy" value="${disabilitySubsidy}"/>残疾&nbsp;&nbsp;
                            <input type="checkbox" name="basicLivingSubsidy" value="${basicLivingSubsidy}"/>低保&nbsp;&nbsp;
                            <input type="checkbox" name="martyr" value="${martyr}"/>烈士家庭&nbsp;&nbsp;
                            <input type="checkbox" name="noChild" value="${noChild}"/>失独&nbsp;&nbsp;
                            <!--隐藏选择值-->
                            <input type="hidden" name="calcLifeCompensate" value="${settleAccounts.calcLifeCompensate}" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="lifeCompensate" value="${settleAccounts.lifeCompensate}" class="none_border width_110px"></td>
                        <td><input type="text" name="lifeCompensateBz" value="${settleAccounts.lifeCompensateBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>十二</td>
                        <td colspan="6" class="td_left">奖励</td>
                        <td colspan="4">
                            <input type="hidden" name="rewardRmbProportion" value="${rewardRmbProportion}"/>
                            <input type="hidden" name="rewardSwapProportion" value="${rewardSwapProportion}"/>
                            <input type="text" name="calcMoveReward" value="${settleAccounts.calcMoveReward}" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="moveReward" value="${settleAccounts.moveReward}" class="none_border width_110px"></td>
                        <td><input type="text" name="moveRewardBz" value="${settleAccounts.moveRewardBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>十三</td>
                        <td colspan="6" class="td_left">建筑面积补助</td>
                        <td colspan="4"><input type="text" name="calcBuildingAreaFee" value="${settleAccounts.calcBuildingAreaFee}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="buildingAreaFee" value="${settleAccounts.buildingAreaFee}" class="none_border width_110px"></td>
                        <td><input type="text" name="buildingAreaFeeBz" value="${settleAccounts.buildingAreaFeeBz}" class="none_border width_70px"></td>
                    </tr>

                    <%--<tr>
                        <td>十二</td>
                        <td colspan="4" class="td_left">不可移动设备设施补偿</td>
                        <td colspan="3"><input type="text" name="calcNoMoveCompensate" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="noMoveCompensate" class="none_border width_110px"></td>
                        <td><input type="text" name="noMoveCompensateBz" class="none_border width_70px"></td>
                    </tr>--%>

                    <%-- <tr>
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
                     </tr>--%>

                    <tr>
                        <td>十四</td>
                        <td colspan="6" class="td_left">其他<input type="text" name="otherDesc" value="${settleAccounts.otherDesc}" class="none_border width_110px" style="clear: both;float: none;"></td>
                        <td colspan="4"><input type="text" name="calcOther" value="${settleAccounts.calcOther}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="otherRmb" value="${settleAccounts.otherRmb}" class="none_border width_110px"></td>
                        <td><input type="text" name="otherBz" value="${settleAccounts.otherBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="7">被征收房屋补偿合计</td>
                        <td colspan="4"><input type="text" name="calcSumCompensate" value="${settleAccounts.calcSumCompensate}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="sumCompensate" value="${settleAccounts.sumCompensate}" class="none_border width_110px"></td>
                        <td><input type="text" name="sumCompensateBz" value="${settleAccounts.sumCompensateBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td rowspan="4">仅产权调换方式</td>
                        <td colspan="5">产权调换房价单价</td>
                        <td style="width: 100px;">建筑面积</td>
                        <td colspan="4" rowspan="2">
                            <br/>
                            <label style="width: 300px;"><input type="text" name="swapMoney1" value="${settleAccounts.swapMoney1}" class="none_border width_220px margin_top_2_buttom_2" placeholder="自动填充"/></label>
                            <label style="width: 300px;"><input type="text" name="swapMoney2" value="${settleAccounts.swapMoney2}" class="none_border width_220px margin_top_2_buttom_2" placeholder="自动填充"/></label>
                            <label style="width: 300px;"><input type="text" name="swapMoney3" value="${settleAccounts.swapMoney3}" class="none_border width_220px margin_top_2_buttom_2" placeholder="自动填充"/></label>
                            <label style="width: 300px;"><input type="text" name="swapMoney4" value="${settleAccounts.swapMoney4}" class="none_border width_220px margin_top_2_buttom_2" placeholder="自动填充"/></label>
                            <label style="width: 300px;"><input type="text" name="swapMoney5" value="${settleAccounts.swapMoney5}" class="none_border width_220px margin_top_2_buttom_2" placeholder="自动填充"/></label>
                        </td>
                        <%--<td colspan="3" style="font-size: xx-small;">其中<input type="text" name="calcm" class="none_border" style="clear: both;float:none;width: 40px;"> m<sup>2</sup>
                            与被征收房屋(<input type="text" name="price" class="none_border" style="clear: both;float:none;width: 50px;">元)置换互不补差价</td>--%>
                        <td rowspan="2" colspan="2"><input type="text" name="houseMoney" value="${settleAccounts.houseMoney}" class="none_border width_110px" style="height: 40px;"></td>
                        <td rowspan="2"><input type="text" name="houseMoneyBz" value="${settleAccounts.houseMoneyBz}" class="none_border width_70px" style="height: 40px;"></td>
                    </tr>
                    <tr style="height: 30px;">
                        <td colspan="5">
                            <label style="width: 7px;">1、</label><input type="text" name="swap_price1" value="${settleAccounts.swapPrice1}" class="none_border margin_top_2_buttom_2" placeholder="单价" style="width: 75px"/>
                            <label style="width: 7px;">2、</label><input type="text" name="swapPrice2" value="${settleAccounts.swapPrice2}" class="none_border margin_top_2_buttom_2" placeholder="选填" style="width: 75px"/>
                            <label style="width: 7px;">3、</label><input type="text" name="swapPrice3" value="${settleAccounts.swapPrice3}" class="none_border margin_top_2_buttom_2" placeholder="选填" style="width: 75px"/>
                            <label style="width: 7px;">4、</label><input type="text" name="swapPrice4" value="${settleAccounts.swapPrice4}" class="none_border margin_top_2_buttom_2" placeholder="选填" style="width: 75px"/>
                            <label style="width: 7px;">5、</label><input type="text" name="swapPrice5" value="${settleAccounts.swapPrice5}" class="none_border margin_top_2_buttom_2" placeholder="选填" style="width: 75px"/>
                        </td>
                        <td>
                            <label style="width: 7px;">1、</label><input type="text" name="swapArea1" value="${settleAccounts.swapArea1}"  class="none_border margin_top_2_buttom_2" placeholder="面积" style="width: 67px"/>
                            <label style="width: 7px;">2、</label><input type="text" name="swapArea2" value="${settleAccounts.swapArea2}" class="none_border margin_top_2_buttom_2" placeholder="选填" style="width: 67px"/>
                            <label style="width: 7px;">3、</label><input type="text" name="swapArea3" value="${settleAccounts.swapArea3}" class="none_border margin_top_2_buttom_2" placeholder="选填" style="width: 67px"/>
                            <label style="width: 7px;">4、</label><input type="text" name="swapArea4" value="${settleAccounts.swapArea4}" class="none_border margin_top_2_buttom_2" placeholder="选填" style="width: 67px"/>
                            <label style="width: 7px;">5、</label><input type="text" name="swapArea5" value="${settleAccounts.swapArea5}" class="none_border margin_top_2_buttom_2" placeholder="选填" style="width: 67px"/>
                        </td>
                    </tr>
                    <%-- <tr>
                         <td><input type="text" name="changeArea" class="none_border width_70px" style="clear: both;float:none;width: 50px;"></td>
                         <td colspan="3"><input type="text" name="calcChangeArea" class="none_border width_220px"></td>
                     </tr>--%>
                    <tr>
                        <td colspan="6">已抵扣安置房款</td>
                        <td colspan="4"><%--<input type="text" name="calcDeduction" class="none_border width_220px">--%></td>
                        <td colspan="2"><input type="text" name="deduction" value="${settleAccounts.deduction}" class="none_border width_110px"></td>
                        <td><input type="text" name="deductionBz" value="${settleAccounts.deductionBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="6">应付（收）款</td>
                        <td colspan="4"><input type="text" name="payTotal" value="${settleAccounts.payTotal}"  class="none_border" style="width: 170px;"></td>
                        <%--  <td>应收合计</td>
                          <td colspan="3"><input type="text" name="receiveTotal" class="none_border" style="width: 170px;"></td>--%>
                        <td rowspan="2">经办人签字</td>
                        <td rowspan="2" colspan="2" style="white-space:nowrap;">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td colspan="11" class="td_left">实际支付金额(大写)：<input type="text" name="payMoney" value="${settleAccounts.payMoney}" class="none_border" style="width: 320px;clear: both;float: none;"></td>
                    </tr>
                    <%-- <tr>
                         <td colspan="8">实际应收金额(大写)：<input type="text" name="receiveMoney" class="none_border" style="width: 320px;clear: both;float: none;"></td>
                     </tr>--%>
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

