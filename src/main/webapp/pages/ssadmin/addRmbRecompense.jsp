<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="comm/include.inc.jsp" %>
<h2 class="contentTitle">添加货币补偿协议</h2>


<style>
    .samll_input_text {
        width: 50px;
    }


</style>

<div class="pageContent">

    <form method="post" action="ssadmin/RmbRecompense/add.html"
          class="pageForm required-validate" enctype="multipart/form-data"
          onsubmit="return iframeCallback(this, dialogAjaxDone);">

        <div class="pageFormContent nowrap" layoutH="97">
            <dl>
                <dt>编号：</dt>
                <dd>
                    <input type="text" name="cardNo" maxlength="50" class="required" size="40"/>
                </dd>
            </dl>
            <dl>
                <dt>征收部门(甲方):</dt>
                <dd>
                    <input type="text" name="mngOffice" maxlength="50" class="required" size="40"/>
                </dd>
            </dl>
            <dl>
                <dt>被征收人(乙方):</dt>
                <dd>
                    <input type="text" name="houseOwner" maxlength="50" class="required" size="20"/>
                </dd>
            </dl>

            <fieldset>
                <legend>被征收房屋基本信息</legend>
                <dl>
                    <dt>被征收房屋地址:</dt>
                    <dd>
                        <input type="text" name="address" maxlength="50" class="required" size="40"/>
                    </dd>
                </dl>
                <dl>
                    <dt>房屋所有权证号：</dt>
                    <dd>
                        <input type="text" name="houseOwnerNumber" maxlength="50" class="required" size="30"/>
                    </dd>
                </dl>
                <dl>
                    <dt>国有土地使用权证:</dt>
                    <dd>
                        <input type="text" name="publicOwnerNumber" maxlength="50" class="required" size="30"/>
                    </dd>
                </dl>

                <dl>
                    <dt>房屋权属份额：</dt>
                    <dd>
                        <input type="text" name="proportion" maxlength="50" class="required" size="20"/>&nbsp;&nbsp;%
                    </dd>
                </dl>
                <dl>
                    <dt>房屋用途:</dt>
                    <dd>
                        <input type="text" name="useing" maxlength="50" class="required" size="20"/>
                    </dd>
                </dl>
                <dl>
                    <dt>登记建筑面积:</dt>
                    <dd>
                        <input type="text" name="checkInArea" maxlength="50" class="required" size="20"/>
                    </dd>
                </dl>
                <dl>
                    <dt>&nbsp;</dt>
                    <dd style="line-height: 26px;">
                        其中：住宅面积&nbsp;&nbsp;<input type="text" name="residenceArea" maxlength="10"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;平方米，
                        经营面积&nbsp;&nbsp;<input type="text" name="operateArea" maxlength="10"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;平方米，
                        办公面积面积&nbsp;&nbsp;<input type="text" name="officeArea" maxlength="10"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;平方米，
                        生产面积&nbsp;&nbsp;<input type="text" name="produceArea" maxlength="10"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;平方米，
                        其他面积&nbsp;&nbsp;<input type="text" name="otherArea" maxlength="10"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;平方米

                    </dd>
                </dl>

                <dl>
                    <dt>未登记建筑面积:</dt>
                    <dd>
                        <input type="text" name="noCheckArea" maxlength="50" size="20"/>&nbsp;&nbsp;&nbsp;&nbsp;平方米
                    </dd>
                </dl>
            </fieldset>
            <br/><br/>
            <fieldset>
                <legend>房屋征收补偿款项</legend>
                <dl>
                    <dt>1、评估机构评估补偿金额:</dt>
                    <dd>
                        <input type="text" name="valueCompensate" maxlength="50" class="required" size="20"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>2、室内装修补偿:</dt>
                    <dd>
                        <input type="text" name="decorationCompensate" maxlength="50" class="required" size="20"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>3、房屋附属设施补偿:</dt>
                    <dd style="line-height: 26px;">
                        构建物补偿&nbsp;&nbsp;<input type="text" name="structureCompensate" maxlength="50"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;元，
                        电话移机费&nbsp;&nbsp;<input type="text" name="movePhoneFee" maxlength="50"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;元，
                        有线电视复装费&nbsp;&nbsp;<input type="text" name="tvFee" maxlength="50"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;元，
                        电表迁移费&nbsp;&nbsp;<input type="text" name="moveAmmeterFee" maxlength="50"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;元，
                        水表迁移费&nbsp;&nbsp;<input type="text" name="moveWaterMeterFee" maxlength="50"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;元，
                        宽带网补偿&nbsp;&nbsp;<input type="text" name="wifiFee" maxlength="50"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;元，
                        空调移机费&nbsp;&nbsp;<input type="text" name="moveAirConditioningFee" maxlength="50"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;元，
                        管道煤气拆装补偿&nbsp;&nbsp;<input type="text" name="gasFee" maxlength="50"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;元，
                        热水器拆装补偿&nbsp;&nbsp;<input type="text" name="hotWaterCompensate" maxlength="50"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;元，
                        小计：&nbsp;&nbsp;<input type="text" name="subtotal" maxlength="50"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;元。
                    </dd>
                </dl>

                <dl>
                    <dt>4、搬迁补偿(搬家费):</dt>
                    <dd>
                        <input type="text" name="moveHouseFee" maxlength="50" class="required" size="20"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>5、临时安置补偿(过渡费):</dt>
                    <dd>
                        <input type="text" name="interimPrice" maxlength="50" class="samll_input_text" style="clear: both;float: none;"/>&nbsp;&nbsp;元/月×
                        <input type="text" name="interimMonth" maxlength="50" class="samll_input_text" style="clear: both;float: none;"/>&nbsp;&nbsp;个月
                        =<input type="text" name="interimFee" maxlength="50" class="required" style="clear: both;float: none;"/>&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>6、停产停业损失补偿:</dt>
                    <dd>
                        <input type="text" name="suspendBusinessFee" maxlength="50"  size="20"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>7、货币补偿补助:</dt>
                    <dd>
                        <input type="text" name="rmbCompensate" maxlength="50"  size="20"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>8、生活困难补助:</dt>
                    <dd>
                        <input type="text" name="lifeCompensate" maxlength="50"  size="20"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>9、住改商补助:</dt>
                    <dd>
                        <input type="text" name="changeCompensate" maxlength="50"  size="20"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>10、搬迁奖励:</dt>
                    <dd>
                        <input type="text" name="moveReward" maxlength="50"  class="required"  size="20"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>11、封闭阳台:</dt>
                    <dd>
                        <input type="text" name="closeBalcony" maxlength="50"  size="20"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>12、未登记建筑:</dt>
                    <dd>
                        <input type="text" name="noCheckCompensate" maxlength="50"  size="20"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>13、其他:</dt>
                    <dd>
                        <input type="text" name="otherFee" maxlength="50"  size="20"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>&nbsp;</dt>
                    <dd style="width: 95%;">
                        以上1-13项统计人民币&nbsp;&nbsp;<input type="text" name="sumRbm" maxlength="50"   class="required"  size="20" style="clear: both;float: none;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;大写：<input type="text" name="upperRmb" maxlength="50"   class="required bottom_border"  size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 200px;"/>
                    </dd>
                </dl>

            </fieldset>
            <br/><br/>
            <fieldset>
                <legend>其它约定</legend>
                <dl>
                    <dt>约定明细1:</dt>
                    <dd>
                        <input type="text" name="otherTermsOne" maxlength="255"    size="40"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>约定明细2:</dt>
                    <dd>
                        <input type="text" name="otherTermsTwo" maxlength="255"    size="40"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
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

                            <button type="button" onclick="vfull()">默认填充值</button>
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
    function vfull() {
        var inputTexts = $("input[type='text']");
        $.each(inputTexts, function (index, obj) {
            $(obj).val("123");
        });
    }
</script>
