<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="comm/include.inc.jsp" %>
<%--<h2 class="contentTitle">添加资金结算单</h2>--%>
<script type="text/javascript" src="${oss_url}/static/ssadmin/js/business/add_settleAccounts_zyc.js"/>
<link href="${oss_url}/static/ssadmin/css/add_settleAccounts.css" rel="stylesheet" type="text/css" />

<div class="pageContent">

    <form method="post" action="ssadmin/settleAccounts/add.html"
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
                <dt>
                    <select name="areaId" style="width: 170px;height: 21px;">
                        <option value="">请选择</option>
                        <c:forEach items="${areaList}" var="area" varStatus="num">
                            <option value="${area.id}" <c:if test="${num.index == 0 }">selected="selected"</c:if>>${area.name}</option>
                        </c:forEach>
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
                        <td colspan="5">被拆迁人</td>
                        <td colspan="2" style="width: 100px;"><input type="text" name="houseOwner" class="none_border"></td>
                        <td style="width: 60px;">电话</td>
                        <td style="width: 550px;" colspan="2"><input type="text" name="phone" class="none_border"></td>
                        <td style="width: 80px;">房屋用途</td>
                        <td colspan="2">建筑面积</td>
                        <td style="width: 80px;">套内面积</td>
                    </tr>
                    <tr>
                        <td colspan="5">公房承租人</td>
                        <td colspan="2"><input type="text" name="lessee" class="none_border"></td>
                        <td>电话</td>
                        <td colspan="2"><input type="text" name="lesseePhone" class="none_border"></td>
                        <td><input type="text" name="useing" class="none_border" style="width: 50px;"></td>
                        <td colspan="2"><input type="text" name="checkInArea" class="none_border width_110px" ></td>
                        <td><input type="text" name="inArea" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="7">被拆迁房屋地址</td>
                        <td colspan="7"><input type="text" name="address" class="none_border" style="width: 400px;"></td>
                    </tr>
                    <%--<tr>
                        <td colspan="7">被征收人身份证号</td>
                        <td colspan="7"><input type="text" name="identityNo" class="none_border" style="width: 400px;"></td>
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
                            <input type="text" name="calcValueCompensateArea"  placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcValueCompensatePrice"  placeholder="单价" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcValueCompensateProportion"  placeholder="比例" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcValueCompensateOther" placeholder="其他公式" size="21" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏计算公式-->
                            <input type="hidden" name="calcValueCompensate" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="valueCompensate" class="none_border width_110px"></td>
                        <td><input type="text" name="valueCompensateBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="5" class="td_left">2、未经登记的合法建筑补偿</td>
                        <td colspan="4">
                            <input type="text" name="calcNoRegisterLegalArea"  placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcNoRegisterLegalPrice"  placeholder="单价" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcNoRegisterLegalProportion" placeholder="比例" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcNoRegisterLegalOther" placeholder="其他公式" size="21" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏计算公式-->
                            <input type="hidden" name="calcNoRegisterLegal" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="noRegisterLegal" class="none_border width_110px"></td>
                        <td><input type="text" name="noRegisterLegalBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="5" class="td_left">3、历史遗留无证房补偿</td>
                        <td colspan="4">
                            <input type="text" name="calcHistoryLegacyArea" placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcHistoryLegacyPrice" placeholder="单价" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcHistoryLegacyProportion" placeholder="比例" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcHistoryLegacyOther" placeholder="其他公式" size="21" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏计算公式-->
                            <input type="hidden" name="calcHistoryLegacy" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="historyLegacy" class="none_border width_110px"></td>
                        <td><input type="text" name="historyLegacyBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>二</td>
                        <td colspan="6" class="td_left">装修折旧补偿</td>
                        <td colspan="4">
                            <input type="text" name="calcDecorationCompensateArea" placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcDecorationCompensatePrice" placeholder="折旧单价" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcDecorationCompensateOther" placeholder="其他公式" size="21" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏计算公式-->
                            <input type="hidden" name="calcDecorationCompensate" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="decorationCompensate" class="none_border width_110px"></td>
                        <td><input type="text" name="decorationCompensateBz" class="none_border width_70px"></td>
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
                            <input type="text" name="calcMoveHouseFee" class="required none_border width_110px" style="margin-top: 2px;">
                        </td>
                        <td colspan="2"><input type="text" name="moveHouseFee" class="none_border width_110px"></td>
                        <td><input type="text" name="moveHouseFeeBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>四</td>
                        <td colspan="6" class="td_left">临时安置补偿（过渡费）</td>
                        <td colspan="4">
                            <input type="text" name="calcInterimFeeArea" placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcInterimFeePrice" placeholder="金额" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcInterimFeeMonth" placeholder="月数" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcInterimFeeOther" placeholder="其他公式" size="21" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏计算公式-->
                            <input type="hidden" name="calcInterimFee" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="interimFee" class="none_border width_110px"></td>
                        <td><input type="text" name="interimFeeBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>五</td>
                        <td colspan="6" class="td_left">保底补偿</td>
                        <td colspan="4">
                            <select name="sel_guarantee" class="select_fix" style="width: 120px;">
                                <option value="0">无</option>
                                <option value="${selGuarantee}">保底</option>
                            </select>
                            <input type="text" name="calcGuarantee" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="guarantee" class="none_border width_110px"></td>
                        <td><input type="text" name="guaranteeBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>六</td>
                        <td colspan="6" class="td_left">停产停业损失补偿</td>
                        <td colspan="4">
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
                        <td rowspan="10">七</td>
                        <td rowspan="10" style="width: 35px;">附属设施补偿</td>
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
                            <input type="hidden" name="calcMoveWaterMeterFee" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="moveWaterMeterFee" class="none_border width_110px"></td>
                        <td><input type="text" name="moveWaterMeterFeeBz" class="none_border width_70px"></td>
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
                            <input type="hidden" name="calcMoveAmmeterFee" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="moveAmmeterFee" class="none_border width_110px"></td>
                        <td><input type="text" name="moveAmmeterFeeBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="5" class="td_left">3、空调移机费</td>
                        <td colspan="4">
                            <select id="air_conditioner_shutter" class="select_fix">
                                <option value="0*${zycAirConditionerShutter}">窗机选择</option>
                                <c:forEach begin="1" end="10" var="idx">
                                    <option value="${idx}*${zycAirConditionerShutter}">${idx}&nbsp;窗机</option>
                                </c:forEach>
                            </select>
                            <select id="air_conditioner_hang" class="select_fix">
                                <option value="0*${zycAirConditionerHang}">挂机选择</option>
                                <c:forEach begin="1" end="10" var="idx">
                                    <option value="${idx}*${zycAirConditionerHang}">${idx}&nbsp;挂机</option>
                                </c:forEach>
                            </select>
                            <select id="air_conditioner_cabinet" class="select_fix">
                                <option value="0*${zycAirConditionerCabinet}">柜机选择</option>
                                <c:forEach begin="1" end="10" var="idx">
                                    <option value="${idx}*${zycAirConditionerCabinet}">${idx}&nbsp;柜机</option>
                                </c:forEach>
                            </select>
                            <!--隐藏存储表单提交值-->
                            <input type="hidden" name="calcMoveAirConditioningFee" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="moveAirConditioningFee" class="none_border width_110px"></td>
                        <td><input type="text" name="moveAirConditioningFeeBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="5" class="td_left">4、热水器拆装费</td>
                        <td colspan="4">
                            <!--电热水器选择-->
                            <select name="sel_water_heater" class="select_fix" style="width: 155px">
                                <option value="0*${zycWaterHeater}">请选择电热水器数量</option>
                                <c:forEach begin="1" end="10" var="idx">
                                    <option value="${idx}*${zycWaterHeater}">${idx}&nbsp;个</option>
                                </c:forEach>
                            </select>

                            <!--太阳能热水器 是固定的-->
                            <label style="width: 105px;margin-top: 2px;margin-left: 20px;"><input type="checkbox" name="calcHotWaterCompensateMoney" value="${zycSolarWaterHeaters}"/>太阳能热水器</label>
                            <!--隐藏存储表单提交值-->
                            <input type="hidden" name="calcHotWaterCompensate" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="hotWaterCompensate" class="none_border width_110px"></td>
                        <td><input type="text" name="hotWaterCompensateBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="5" class="td_left">5、管道煤气拆装费</td>
                        <td colspan="4"><input type="text" name="calcGasFee" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="gasFee" class="none_border width_110px"></td>
                        <td><input type="text" name="gasFeeBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="2" rowspan="5" class="td_left">6、构建物补偿</td>
                        <td colspan="3" class="td_left">（1）结构内阳台</td>
                        <td colspan="4">
                            <input type="text" name="structureBalconyArea" class="none_border" placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="structureBalconyPrice" class="none_border" placeholder="单价" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="structureBalconyProportion" class="none_border" placeholder="比例" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏存储值-->
                            <input type="hidden" name="calcStructureBalcony" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="structureBalcony" class="none_border width_110px"></td>
                        <td><input type="text" name="structureBalconyBz" class="none_border width_70px"></td>
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
                        <td colspan="3" class="td_left">（2）暗楼</td>
                        <td colspan="4">
                            <input type="text" name="structureDarkArea" class="none_border" placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="structureDarkPrice" class="none_border" placeholder="单价" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="structureDarkProportion" class="none_border" placeholder="比例" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏存储值-->
                            <input type="hidden" name="calcStructureDark" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="structureDark" class="none_border width_110px"></td>
                        <td><input type="text" name="structureDarkBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="3" class="td_left">（3）夹层（假层）</td>
                        <td colspan="4">
                            <input type="text" name="structureMezzanineArea" class="none_border" placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="structureMezzaninePrice" class="none_border" placeholder="单价" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="structureMezzanineProportion" class="none_border" placeholder="比例" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏存储值-->
                            <input type="hidden" name="calcStructureMezzanine" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="structureMezzanine" class="none_border width_110px"></td>
                        <td><input type="text" name="structureMezzanineBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="3" class="td_left">（4）外挑搭建（或棚子）</td>
                        <td colspan="4">
                            <input type="text" name="structureBuildArea" class="none_border" placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="structureBuildPrice" class="none_border" placeholder="单价" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="structureBuildProportion" class="none_border" placeholder="比例" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏存储值-->
                            <input type="hidden" name="calcStructureBuild" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="structureBuild" class="none_border width_110px"></td>
                        <td><input type="text" name="structureBuildBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="3" class="td_left">（5）楼顶搭建（简易房）</td>
                        <td colspan="4">
                            <input type="text" name="structureRoofArea" class="none_border" placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="structureRoofPrice" class="none_border" placeholder="单价" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="structureRoofProportion" class="none_border" placeholder="比例" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏存储值-->
                            <input type="hidden" name="calcStructureRoof" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="structureRoof" class="none_border width_110px"></td>
                        <td><input type="text" name="structureRoofBz" class="none_border width_70px"></td>
                    </tr>
                     <%--<tr>
                         <td colspan="3" class="td_left">（6）其他<input type="text" name="affiliatedOtherDesc" class="none_border" style="margin-top: 3px;width: 80px;float: none;">&nbsp;</td>
                         <td colspan="4"><input type="text" name="calcAffiliatedOther" class="none_border width_220px"></td>
                         <td colspan="2"><input type="text" name="affiliatedOther" class="none_border width_110px"></td>
                         <td><input type="text" name="affiliatedOtherBz" class="none_border width_70px"></td>
                     </tr>--%>
                    <tr>
                        <td>八</td>
                        <td colspan="6" class="td_left">房屋改变用途补助</td>
                        <td colspan="4">
                            <input type="text" name="calcChangeCompensateArea" class="none_border" placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcChangeCompensatePrice" class="none_border" placeholder="实际用途单价" size="8" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcChangeCompensateOldPrice" class="none_border" placeholder="旧房单价" size="6" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcChangeCompensateProportion" class="none_border" placeholder="比例" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcChangeCompensateOther" class="none_border"  size="21" style="margin-top: 2px;margin-left: 5px;"/>
                            <!--隐藏存储值-->
                            <input type="hidden" name="calcChangeCompensate" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="changeCompensate" class="none_border width_110px"></td>
                        <td><input type="text" name="changeCompensateBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>九</td>
                        <td colspan="6" class="td_left">生活困难补助</td>
                        <td colspan="4" id="lifeCalcTd" class="td_left">
                            <input type="checkbox" name="diseaseSubsidy" value="${diseaseSubsidy}"/>重症&nbsp;&nbsp;
                            <input type="checkbox" name="disabilitySubsidy" value="${disabilitySubsidy}"/>残疾&nbsp;&nbsp;
                            <input type="checkbox" name="basicLivingSubsidy" value="${basicLivingSubsidy}"/>低保&nbsp;&nbsp;
                            <input type="checkbox" name="martyr" value="${martyr}"/>烈士家庭&nbsp;&nbsp;
                            <input type="checkbox" name="noChild" value="${noChild}"/>失独&nbsp;&nbsp;
                            <!--隐藏选择值-->
                            <input type="hidden" name="calcLifeCompensate" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="lifeCompensate" class="none_border width_110px"></td>
                        <td><input type="text" name="lifeCompensateBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>十</td>
                        <td colspan="6" class="td_left">货币补偿补助（或产权调换补助）</td>
                        <td colspan="4" id="rmbCompensate_td1">
                            <input type="hidden" name="rmbCompensateProportion" value="${rmbCompensateProportion}"/>
                            <%--<input type="text" name="calcRmbCompensateArea" class="none_border" placeholder="面积" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcRmbCompensatePrice" class="none_border" placeholder="单价" size="5" style="margin-top: 2px;margin-left: 5px;"/>
                            <input type="text" name="calcRmbCompensateProportion" class="none_border" placeholder="比例" value="${rmbCompensateProportion}" size="5" style="margin-top: 2px;margin-left: 5px;"/>--%>
                            <!--隐藏计算公式-->
                            <input type="text" name="calcRmbCompensate" class="none_border width_220px">
                        </td>
                        <td colspan="2" id="rmbCompensate_td2"><input type="text" name="rmbCompensate" class="none_border width_110px"></td>
                        <td id="rmbCompensate_td3"><input type="text" name="rmbCompensateBz" class="none_border width_70px"></td>
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
                        <td>十一</td>
                        <td colspan="6" class="td_left">小户型住房困难补助(选择货币补偿时)</td>
                        <td colspan="4"><input type="text" name="calcSmallAreaReward" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="smallAreaReward" class="none_border width_110px"></td>
                        <td><input type="text" name="smallAreaRewardBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>十二</td>
                        <td colspan="6" class="td_left">建筑面积补助</td>
                        <td colspan="4"><input type="text" name="calcBuildingAreaFee" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="buildingAreaFee" class="none_border width_110px"></td>
                        <td><input type="text" name="buildingAreaFeeBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>十三</td>
                        <td colspan="6" class="td_left">征收奖励</td>
                        <td colspan="4">
                            <input type="hidden" name="rewardRmbProportion" value="${rewardRmbProportion}"/>
                            <input type="hidden" name="rewardSwapProportion" value="${rewardSwapProportion}"/>
                            <input type="text" name="calcMoveReward" class="none_border width_220px">
                        </td>
                        <td colspan="2"><input type="text" name="moveReward" class="none_border width_110px"></td>
                        <td><input type="text" name="moveRewardBz" class="none_border width_70px"></td>
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
                        <td colspan="6" class="td_left">其他<input type="text" name="otherDesc" class="none_border width_110px" style="clear: both;float: none;"></td>
                        <td colspan="4"><input type="text" name="calcOther" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="otherRmb" class="none_border width_110px"></td>
                        <td><input type="text" name="otherBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="7">被征收房屋补偿合计</td>
                        <td colspan="4"><input type="text" name="calcSumCompensate" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="sumCompensate" class="none_border width_110px"></td>
                        <td><input type="text" name="sumCompensateBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td rowspan="4">选择产权调换方式</td>
                        <td colspan="4" rowspan="2">产权调换房屋情况</td>
                        <td colspan="2" style="width: 100px;">安置房名称、房号</td>
                        <td colspan="4">
                            <input type="text" name="newHouseName" class="none_border" style="width: 400px;">
                        </td>
                        <%--<td colspan="3" style="font-size: xx-small;">其中<input type="text" name="calcm" class="none_border" style="clear: both;float:none;width: 40px;"> m<sup>2</sup>
                            与被征收房屋(<input type="text" name="price" class="none_border" style="clear: both;float:none;width: 50px;">元)置换互不补差价</td>--%>
                        <td rowspan="2" colspan="2"><input type="text" name="houseMoney" class="none_border width_110px" style="height: 40px;"></td>
                        <td rowspan="2"><input type="text" name="houseMoneyBz" class="none_border width_70px" style="height: 40px;"></td>
                    </tr>
                    <tr style="height: 30px;">
                        <td colspan="2">安置房面积、单价、总价</td>
                        <td colspan="4" class="td_left">
                            <div style="height:26px;margin-bottom:2px;margin-top: 2px;">
                            1、面积<input type="text" name="swapArea1" class="width_50px" style="float: none;margin-left: 5px;">
                            &nbsp;M<sup>2</sup>×单价
                            <input type="text" name="swapPrice1" class=" width_60px" style="float: none;margin-left: 5px;">元/M<sup>2</sup>
                            ＝<input type="text" name="swapMoney1" style="float: none;margin-left: 5px;width: 100px;">元
                            </div>
                            <div style="height:26px;margin-bottom:2px;margin-top: 2px;">
                               2、面积<input type="text" name="swapArea2" class="width_50px" style="float: none;margin-left: 5px;">
                                &nbsp;M<sup>2</sup>×单价
                                <input type="text" name="swapPrice2" class=" width_60px" style="float: none;margin-left: 5px;">元/M<sup>2</sup>
                                ＝<input type="text" name="swapMoney2" style="float: none;margin-left: 5px;width: 100px;">元
                            </div>
                            <div style="height:26px;margin-bottom:2px;margin-top: 2px;">
                                3、面积<input type="text" name="swapArea3" class="width_50px" style="float: none;margin-left: 5px;">
                                &nbsp;M<sup>2</sup>×单价
                                <input type="text" name="swapPrice3" class=" width_60px" style="float: none;margin-left: 5px;">元/M<sup>2</sup>
                                ＝<input type="text" name="swapMoney3" style="float: none;margin-left: 5px;width: 100px;">元
                            </div>
                           <%-- <div style="height:26px;margin-bottom:2px;margin-top: 2px;">
                                4、面积<input type="text" name="swapArea4" class="width_50px" style="float: none;margin-left: 5px;">
                                &nbsp;M<sup>2</sup>×单价
                                <input type="text" name="swapPrice4" class=" width_60px" style="float: none;margin-left: 5px;">元/M<sup>2</sup>
                                ＝<input type="text" name="swapMoney4" style="float: none;margin-left: 5px;width: 100px;">元
                            </div>
                            <div style="height:26px;margin-bottom:2px;margin-top: 2px;">
                                5、面积<input type="text" name="swapArea5" class="width_50px" style="float: none;margin-left: 5px;">
                                &nbsp;M<sup>2</sup>×单价
                                <input type="text" name="swapPrice5" class=" width_60px" style="float: none;margin-left: 5px;">元/M<sup>2</sup>
                                ＝<input type="text" name="swapMoney5" style="float: none;margin-left: 5px;width: 100px;">元
                            </div>--%>
                        </td>
                    </tr>
                    <%-- <tr>
                         <td><input type="text" name="changeArea" class="none_border width_70px" style="clear: both;float:none;width: 50px;"></td>
                         <td colspan="3"><input type="text" name="calcChangeArea" class="none_border width_220px"></td>
                     </tr>--%>
                    <tr>
                        <td colspan="6">已抵扣安置房款</td>
                        <td colspan="4"><%--<input type="text" name="calcDeduction" class="none_border width_220px">--%></td>
                        <td colspan="2"><input type="text" name="deduction" class="none_border width_110px"></td>
                        <td><input type="text" name="deductionBz" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="6">应付（应收）款</td>
                        <td colspan="4"><input type="text" name="payTotal" class="none_border" style="width: 170px;"></td>
                        <%--  <td>应收合计</td>
                          <td colspan="3"><input type="text" name="receiveTotal" class="none_border" style="width: 170px;"></td>--%>
                        <td rowspan="2">经办人签字</td>
                        <td rowspan="2" colspan="2" style="white-space:nowrap;">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td colspan="11" class="td_left">实际支付金额(大写)：<input type="text" name="payMoney" class="none_border" style="width: 320px;clear: both;float: none;"></td>
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


