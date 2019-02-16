<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="comm/include.inc.jsp" %>
<h2 class="contentTitle">添加产权调换协议</h2>


<style>
    .samll_input_text {
        width: 50px;
    }


</style>

<div class="pageContent">

    <form method="post" action="ssadmin/saveSystemArgs.html"
          class="pageForm required-validate" enctype="multipart/form-data"
          onsubmit="return iframeCallback(this, dialogAjaxDone);">

        <div class="pageFormContent nowrap" layoutH="97">
            <dl>
                <dt>征收部门(甲方):</dt>
                <dd>
                    <input type="text" name="key" maxlength="50" class="required" size="40"/>
                </dd>
            </dl>
            <dl>
                <dt>被征收人(乙方):</dt>
                <dd>
                    <input type="text" name="key" maxlength="50" class="required" size="20"/>
                </dd>
            </dl>

            <fieldset>
                <legend>被征收房屋基本信息</legend>
                <dl>
                    <dt>被征收房屋地址:</dt>
                    <dd>
                        <input type="text" name="key" maxlength="50" class="required" size="40"/>
                    </dd>
                </dl>
                <dl>
                    <dt>房屋所有权证号：</dt>
                    <dd>
                        <input type="text" name="key" maxlength="50" class="required" size="30"/>
                    </dd>
                </dl>
                <dl>
                    <dt>国有土地使用权证:</dt>
                    <dd>
                        <input type="text" name="key" maxlength="50" class="required" size="30"/>
                    </dd>
                </dl>

                <dl>
                    <dt>房屋权属份额：</dt>
                    <dd>
                        <input type="text" name="key" maxlength="50" class="required" size="20"/>
                    </dd>
                </dl>
                <dl>
                    <dt>房屋用途:</dt>
                    <dd>
                        <input type="text" name="key" maxlength="50" class="required" size="20"/>
                    </dd>
                </dl>
                <dl>
                    <dt>登记建筑面积:</dt>
                    <dd>
                        <input type="text" name="key" maxlength="50" class="required" size="20"/>
                    </dd>
                </dl>
                <dl>
                    <dt>&nbsp;</dt>
                    <dd style="line-height: 26px;">
                        其中：住宅面积&nbsp;&nbsp;<input type="text" name="key" maxlength="10"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;平方米，
                        经营面积&nbsp;&nbsp;<input type="text" name="key" maxlength="10"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;平方米，
                        办公面积面积&nbsp;&nbsp;<input type="text" name="key" maxlength="10"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;平方米，
                        生产面积&nbsp;&nbsp;<input type="text" name="key" maxlength="10"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;平方米，
                        其他面积&nbsp;&nbsp;<input type="text" name="key" maxlength="10"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;平方米

                    </dd>
                </dl>

                <dl>
                    <dt>未登记建筑面积:</dt>
                    <dd>
                        <input type="text" name="key" maxlength="50" size="20"/>&nbsp;&nbsp;&nbsp;&nbsp;平方米
                    </dd>
                </dl>
            </fieldset>
            <br/><br/>
            <fieldset>
                <legend>房屋征收补偿款项</legend>
                <dl>
                    <dt>1、评估机构评估补偿金额:</dt>
                    <dd>
                        <input type="text" name="key" maxlength="50" class="required" size="20"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>2、室内装修补偿:</dt>
                    <dd>
                        <input type="text" name="key" maxlength="50" class="required" size="20"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>3、房屋附属设施补偿:</dt>
                    <dd style="line-height: 26px;">
                        构建物补偿&nbsp;&nbsp;<input type="text" name="key" maxlength="50"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;元，
                        电话移机费&nbsp;&nbsp;<input type="text" name="key" maxlength="50"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;元，
                        有线电视复装费&nbsp;&nbsp;<input type="text" name="key" maxlength="50"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;元，
                        电表迁移费&nbsp;&nbsp;<input type="text" name="key" maxlength="50"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;元，
                        水表迁移费&nbsp;&nbsp;<input type="text" name="key" maxlength="50"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;元，
                        宽带网补偿&nbsp;&nbsp;<input type="text" name="key" maxlength="50"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;元，
                        空调移机费&nbsp;&nbsp;<input type="text" name="key" maxlength="50"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;元，
                        管道煤气拆装补偿&nbsp;&nbsp;<input type="text" name="key" maxlength="50"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;元，
                        热水器拆装补偿&nbsp;&nbsp;<input type="text" name="key" maxlength="50"  class="samll_input_text" style="clear: both;float: none;"/>&nbsp;元。
                    </dd>
                </dl>

                <dl>
                    <dt>4、搬迁补偿(搬家费):</dt>
                    <dd>
                        <input type="text" name="key" maxlength="50" class="required" size="20"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>5、临时安置补偿(过渡费):</dt>
                    <dd>
                        <input type="text" name="key" maxlength="50" class="samll_input_text" style="clear: both;float: none;"/>&nbsp;&nbsp;元/月×
                        <input type="text" name="key" maxlength="50" class="samll_input_text" style="clear: both;float: none;"/>&nbsp;&nbsp;个月
                        =<input type="text" name="key" maxlength="50" class="required" style="clear: both;float: none;"/>&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>6、停产停业损失补偿:</dt>
                    <dd>
                        <input type="text" name="key" maxlength="50"  size="20"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>7、生活困难补助:</dt>
                    <dd>
                        <input type="text" name="key" maxlength="50"  size="20"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>8、住改商补助:</dt>
                    <dd>
                        <input type="text" name="key" maxlength="50"  size="20"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>9、搬迁奖励:</dt>
                    <dd>
                        <input type="text" name="key" maxlength="50"  class="required"  size="20"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>10、封闭阳台:</dt>
                    <dd>
                        <input type="text" name="key" maxlength="50"  size="20"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>11、未登记建筑:</dt>
                    <dd>
                        <input type="text" name="key" maxlength="50"  size="20"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>12、其他:</dt>
                    <dd>
                        <input type="text" name="key" maxlength="50"  size="20"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                    </dd>
                </dl>
                <dl>
                    <dt>&nbsp;</dt>
                    <dd>
                        以上1-12项统计人民币&nbsp;&nbsp;<input type="text" name="key" maxlength="50"   class="required"  size="20" style="clear: both;float: none;"/>&nbsp;&nbsp;&nbsp;&nbsp;元
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;大写：
                    </dd>
                </dl>

            </fieldset>
            <br/><br/>
            <fieldset>
                <legend>产权调换房屋基本信息</legend>
                <dl>
                    <dd style="line-height: 26px;width: 95%; padding-left: 10px;">
                        乙方选定&nbsp;&nbsp;<input type="text" name="key" maxlength="50"   class="required bottom_border"  size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 200px;"/>、
                        <input type="text" name="key" maxlength="50"   class="required bottom_border"  size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 40px;"/>&nbsp;&nbsp;栋、
                        <input type="text" name="key" maxlength="50"   class="required bottom_border"  size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 40px;"/>&nbsp;&nbsp;单元、
                        <input type="text" name="key" maxlength="50"   class="required bottom_border"  size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 40px;"/>&nbsp;&nbsp;层、
                        <input type="text" name="key" maxlength="50"   class="required bottom_border"  size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 40px;"/>&nbsp;&nbsp;号房屋。
                        该房屋面积&nbsp;&nbsp;<input type="text" name="key" maxlength="50"   class="required bottom_border"  size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 100px;"/>&nbsp;&nbsp;平方米，
                        单价&nbsp;&nbsp;<input type="text" name="key" maxlength="50"   class="required bottom_border"  size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 100px;"/>&nbsp;&nbsp;元/平方米，
                        预估房屋总价人民币&nbsp;&nbsp;<input type="text" name="key" maxlength="50"   class="required bottom_border"  size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 160px;"/>&nbsp;&nbsp;元
                        （大写：&nbsp;&nbsp;<input type="text" name="key" maxlength="50"   class="required bottom_border"  size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 160px;"/>），
                        交房时间为&nbsp;&nbsp;<input type="text" name="key" maxlength="50"   class="required bottom_border"  size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 60px;"/>&nbsp;&nbsp;年
                        &nbsp;&nbsp;<input type="text" name="key" maxlength="50"   class="required bottom_border"  size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 50px;"/>&nbsp;&nbsp;月。
                    </dd>
                </dl>

            </fieldset>
            <br/><br/>
            <fieldset>
                <legend>支付期限</legend>
                <dl>
                    <dd style="line-height: 26px;width: 95%; padding-left: 10px;">
                        如房屋征收补偿款总额大于预购房款，其差额部分&nbsp;&nbsp;<input type="text" name="key" maxlength="50"   class=" bottom_border"  size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 120px;"/>&nbsp;&nbsp;元
                        （大写：&nbsp;&nbsp;<input type="text" name="key" maxlength="50"   class=" bottom_border"  size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 160px;"/>&nbsp;&nbsp;）
                        经会计机构审核出具审计报告后7个工作日内，由甲方一次性支付给乙方；如房屋征收补偿总额小于预购房款，其差额部分&nbsp;&nbsp;<input type="text" name="key" maxlength="50"   class=" bottom_border"  size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 120px;"/>&nbsp;&nbsp;元
                        （大写：&nbsp;&nbsp;<input type="text" name="key" maxlength="50"   class=" bottom_border"  size="20" style="border-left: none;border-right: none;border-top: none;clear: both;float: none;width: 160px;"/>&nbsp;&nbsp;）
                        待办理入住手续时，据实结算由乙方一次性支付给甲方。
                    </dd>
                </dl>
            </fieldset>
            <br/><br/>
            <fieldset>
                <legend>其它约定</legend>
                <dl>
                    <dt>约定明细:</dt>
                    <dd>
                        <input type="text" name="key" maxlength="255"    size="40"/>&nbsp;&nbsp;&nbsp;&nbsp;元
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
