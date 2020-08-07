<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="comm/include.inc.jsp" %>
<h2 class="contentTitle">修改手续通知单</h2>

<style>
    .tableInfo {
        width: 750px;
        min-height: 25px;
        line-height: 28px;
        text-align: center;
        border-color: #a2bac0 ;
        border-collapse: collapse;
    }

    .tableInfo tr td {
        border: 1px solid #a2bac0;
        line-height: 28px;
    }

    .td_left{
        text-align: left;
        padding-left: 5px;
    }

    .samll_input_text {
        width: 50px;
        border: none;
    }

    .none_border {
        border: none;
    }

    .width_70px {
        width: 70px;
    }

    .width_110px {
        width: 110px;
    }

    .width_220px{
        width: 220px;
    }

    .width_185px{
        width: 185px;
    }

</style>

<div class="pageContent">

    <form method="post" action="ssadmin/notice/update.html"
          class="pageForm required-validate" enctype="multipart/form-data"
          onsubmit="return iframeCallback(this, dialogAjaxDone);">

        <div class="pageFormContent nowrap" layoutH="97">
            <dl>
                <dt style="width: 80px;">项目名称:</dt>
                <dt style="width: 300px;">
                    <input type="text" name="projectName" maxlength="50" class="required" size="40" value="${notice.projectName}"/>
                </dt>
                <dt style="width: 40px;">编号：</dt>
                <dt>
                    <input type="text" name="cardNo" maxlength="50" class="required" size="10" value="${notice.cardNo}"/>
                </dt>
            </dl>
            <dl>
                <table  border="1" class="tableInfo">
                    <tr>
                        <td colspan="3">被征收人</td>
                        <td colspan="2" style="width: 100px;"><input type="text" name="houseOwner" value="${notice.houseOwner}" class="none_border"></td>
                        <td style="width: 60px;">电话</td>
                        <td style="width: 100px;"><input type="text" name="phone" value="${notice.phone}" class="none_border"></td>
                        <td style="width: 60px;">房屋用途</td>
                        <td style="width: 60px;">建筑面积</td>
                        <td style="width: 60px;">产权比例</td>
                        <td style="width: 80px;">房屋评估单价</td>
                    </tr>
                    <tr>
                        <td colspan="3">公房承租人</td>
                        <td colspan="2"><input type="text" name="lessee" value="${notice.lessee}" class="none_border"></td>
                        <td>电话</td>
                        <td><input type="text" name="lesseePhone" value="${notice.lesseePhone}"  class="none_border"></td>
                        <td><input type="text" name="useing"  value="${notice.useing}" class="samll_input_text"></td>
                        <td><input type="text" name="checkInArea" value="${notice.checkInArea}"  class="samll_input_text" ></td>
                        <td><input type="text" name="proportion" value="${notice.proportion}"   class="samll_input_text" ></td>
                        <td><input type="text" name="assessPrice" value="${notice.assessPrice}" class="samll_input_text"></td>
                    </tr>
                    <tr>
                        <td colspan="5">个人身份证号或单位账号</td>
                        <td colspan="6"><input type="text" name="identityNo" value="${notice.identityNo}" class="none_border" style="width: 400px;"></td>
                    </tr>
                    <tr>
                        <td colspan="5">被征收房屋地址</td>
                        <td colspan="6"><input type="text" name="address" value="${notice.address}" class="none_border" style="width: 400px;"></td>
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
                        <td colspan="4">房屋价值补偿</td>
                        <td colspan="3"><input type="text" name="calcValueCompensate"   value="${notice.calcValueCompensate}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="valueCompensate"  value="${notice.valueCompensate}" class="none_border width_110px"></td>
                        <td><input type="text" name="valueCompensateBz"  value="${notice.valueCompensateBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>二</td>
                        <td colspan="4">室内装饰装修补偿</td>
                        <td colspan="3"><input type="text" name="calcDecorationCompensate"  value="${notice.calcDecorationCompensate}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="decorationCompensate"  value="${notice.decorationCompensate}" class="none_border width_110px"></td>
                        <td><input type="text" name="decorationCompensateBz"  value="${notice.decorationCompensateBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td rowspan="9">三</td>
                        <td rowspan="9" style="width: 35px;">房屋附属设施补偿</td>
                        <td colspan="3" class="td_left">1、构建物补偿</td>
                        <td colspan="3"><input type="text" name="calcStructureCompensate" value="${notice.calcStructureCompensate}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="structureCompensate" value="${notice.structureCompensate}" class="none_border width_110px"></td>
                        <td><input type="text" name="structureCompensateBz" value="${notice.structureCompensateBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="3" class="td_left">2、电表迁移费</td>
                        <td colspan="3"><input type="text" name="calcMoveAmmeterFee" value="${notice.calcMoveAmmeterFee}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="moveAmmeterFee" value="${notice.moveAmmeterFee}" class="none_border width_110px"></td>
                        <td><input type="text" name="moveAmmeterFeeBz" value="${notice.moveAmmeterFeeBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="3" class="td_left">3、水表迁移费</td>
                        <td colspan="3"><input type="text" name="calcMoveWaterMeterFee" value="${notice.calcMoveWaterMeterFee}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="moveWaterMeterFee" value="${notice.moveWaterMeterFee}" class="none_border width_110px"></td>
                        <td><input type="text" name="moveWaterMeterFeeBz" value="${notice.moveWaterMeterFeeBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="3" class="td_left">4、空调移机费</td>
                        <td colspan="3"><input type="text" name="calcMoveAirConditioningFee"  value="${notice.calcMoveAirConditioningFee}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="moveAirConditioningFee" value="${notice.moveAirConditioningFee}" class="none_border width_110px"></td>
                        <td><input type="text" name="moveAirConditioningFeeBz" value="${notice.moveAirConditioningFeeBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="3" class="td_left">5、管道煤气拆装补偿</td>
                        <td colspan="3"><input type="text" name="calcGasFee" value="${notice.calcGasFee}"  class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="gasFee" value="${notice.gasFee}"  class="none_border width_110px"></td>
                        <td><input type="text" name="gasFeeBz" value="${notice.gasFeeBz}"  class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="3" class="td_left">6、热水器拆装补偿</td>
                        <td colspan="3"><input type="text" name="calcHotWaterCompensate" value="${notice.calcHotWaterCompensate}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="hotWaterCompensate" value="${notice.hotWaterCompensate}" class="none_border width_110px"></td>
                        <td><input type="text" name="hotWaterCompensateBz" value="${notice.hotWaterCompensateBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="3"><input type="text" name="affiliated1Desc" value="${notice.affiliated1Desc}" class="none_border width_185px" style="margin-top: 3px;">&nbsp;</td>
                        <td colspan="3"><input type="text" name="calcAffiliated1Desc" value="${notice.calcAffiliated1Desc}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="affiliated1Rmb" value="${notice.affiliated1Rmb}" class="none_border width_110px"></td>
                        <td><input type="text" name="affiliated1Bz" value="${notice.affiliated1Bz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="3"><input type="text" name="affiliated2Desc"  value="${notice.affiliated2Desc}" class="none_border width_185px" style="margin-top: 3px;">&nbsp;</td>
                        <td colspan="3"><input type="text" name="calcAffiliated2Desc"   value="${notice.calcAffiliated2Desc}"  class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="affiliated2Rmb"  value="${notice.affiliated2Rmb}"  class="none_border width_110px"></td>
                        <td><input type="text" name="affiliated2Bz"  value="${notice.affiliated2Bz}"  class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="3"><input type="text" name="affiliated3Desc" value="${notice.affiliated3Desc}"   class="none_border width_185px" style="margin-top: 3px;">&nbsp;</td>
                        <td colspan="3"><input type="text" name="calcAffiliated3Desc" value="${notice.calcAffiliated3Desc}"   class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="affiliated3Rmb" value="${notice.affiliated3Rmb}"   class="none_border width_110px"></td>
                        <td><input type="text" name="affiliated3Bz" value="${notice.affiliated3Bz}"   class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>四</td>
                        <td colspan="4" class="td_left">搬迁补偿（搬家费）</td>
                        <td colspan="3"><input type="text" name="calcMoveHouseFee" value="${notice.calcMoveHouseFee}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="moveHouseFee" value="${notice.moveHouseFee}" class="none_border width_110px"></td>
                        <td><input type="text" name="moveHouseFeeBz" value="${notice.moveHouseFeeBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>五</td>
                        <td colspan="4" class="td_left">临时安置补偿（过渡费）</td>
                        <td colspan="3"><input type="text" name="calcInterimFee" value="${notice.calcInterimFee}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="interimFee" value="${notice.interimFee}" class="none_border width_110px"></td>
                        <td><input type="text" name="interimFeeBz" value="${notice.interimFeeBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>六</td>
                        <td colspan="4" class="td_left">停产停业损失补偿</td>
                        <td colspan="3"><input type="text" name="calcSuspendBusinessFee" value="${notice.calcSuspendBusinessFee}"  class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="suspendBusinessFee" value="${notice.suspendBusinessFee}"  class="none_border width_110px"></td>
                        <td><input type="text" name="suspendBusinessFeeBz" value="${notice.suspendBusinessFeeBz}"  class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>七</td>
                        <td colspan="4" class="td_left">货币补偿补助</td>
                        <td colspan="3"><input type="text" name="calcRmbCompensate"  value="${notice.calcRmbCompensate}"  class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="rmbCompensate"  value="${notice.rmbCompensate}" class="none_border width_110px"></td>
                        <td><input type="text" name="rmbCompensateBz"  value="${notice.rmbCompensateBz}"  class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>八</td>
                        <td colspan="4" class="td_left">生活困难补助</td>
                        <td colspan="3"><input type="text" name="calcLifeCompensate" value="${notice.calcLifeCompensate}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="lifeCompensate" value="${notice.lifeCompensate}" class="none_border width_110px"></td>
                        <td><input type="text" name="lifeCompensateBz" value="${notice.lifeCompensateBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>九</td>
                        <td colspan="4" class="td_left">住改商补助</td>
                        <td colspan="3"><input type="text" name="calcChangeCompensate" value="${notice.calcChangeCompensate}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="changeCompensate" value="${notice.changeCompensate}"class="none_border width_110px"></td>
                        <td><input type="text" name="changeCompensateBz" value="${notice.changeCompensateBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>十</td>
                        <td colspan="4" class="td_left">搬迁奖励</td>
                        <td colspan="3"><input type="text" name="calcMoveReward" value="${notice.calcMoveReward}"  class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="moveReward" value="${notice.moveReward}"  class="none_border width_110px"></td>
                        <td><input type="text" name="moveRewardBz" value="${notice.moveRewardBz}"  class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>十一</td>
                        <td colspan="4" class="td_left">封闭阳台</td>
                        <td colspan="3"><input type="text" name="calcCloseBalcony" value="${notice.calcCloseBalcony}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="closeBalcony" value="${notice.closeBalcony}"  class="none_border width_110px"></td>
                        <td><input type="text" name="closeBalconyBz" value="${notice.closeBalconyBz}"  class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>十二</td>
                        <td colspan="4" class="td_left">未登记建筑</td>
                        <td colspan="3"><input type="text" name="calcNoCheckCompensate"  value="${notice.calcNoCheckCompensate}"  class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="noCheckCompensate"  value="${notice.noCheckCompensate}"  class="none_border width_110px"></td>
                        <td><input type="text" name="noCheckCompensateBz"  value="${notice.noCheckCompensateBz}"  class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td>十三</td>
                        <td colspan="4" class="td_left">其他<input type="text" name="otherDesc"  value="${notice.otherDesc}"  class="none_border width_110px" style="clear: both;float: none;"></td>
                        <td colspan="3"><input type="text" name="calcOther"  value="${notice.calcOther}"  class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="otherRmb"  value="${notice.otherRmb}"  class="none_border width_110px"></td>
                        <td><input type="text" name="otherBz"  value="${notice.otherBz}"  class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td colspan="5">应付款合计</td>
                        <td colspan="3"><input type="text" name="calcPayTotal" value="${notice.calcPayTotal}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="payTotal" value="${notice.payTotal}" class="none_border width_110px"></td>
                        <td><input type="text" name="payTotalBz" value="${notice.payTotalBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td rowspan="3">仅产权调换方式</td>
                        <td colspan="3">产权调换房屋应收价款</td>
                        <td style="width: 140px;">建筑面积<input type="text" name="changeArea" value="${notice.changeArea}" class="none_border width_70px" style="clear: both;float:none;width: 50px;"> m<sup>2</sup></td>
                        <td colspan="3"><input type="text" name="calcChangeRmb" value="${notice.calcChangeRmb}" class="none_border width_220px"></td>
                        <td colspan="2"><input type="text" name="changeRmb" value="${notice.changeRmb}" class="none_border width_110px"></td>
                        <td><input type="text" name="changeRmbBz" value="${notice.changeRmbBz}" class="none_border width_70px"></td>
                    </tr>
                    <tr>
                        <td rowspan="2" colspan="3">实际结算</td>
                        <td>收款</td>
                        <td colspan="3"><input type="text" name="realReceiveTotal" value="${notice.realReceiveTotal}" class="none_border width_220px"></td>
                        <td rowspan="3">被征收人签字</td>
                        <td rowspan="3" colspan="2">&nbsp;</td>
                    </tr>
                    <tr>
                        <td>付款</td>
                        <td colspan="3"><input type="text" name="realPayTotal" value="${notice.realPayTotal}" class="none_border width_220px"></td>
                    </tr>
                    <tr>
                        <td colspan="4">实际金额(大写)</td>
                        <td colspan="4"><input type="text" name="realPayMoney" value="${notice.realPayMoney}" class="none_border" style="width: 335px;"></td>
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
                            <input type="hidden" name="id" value="${notice.id}">
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
