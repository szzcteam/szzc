<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="comm/include.inc.jsp" %>
<h2 class="contentTitle">添加手续通知单</h2>

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


</style>

<div class="pageContent">

    <form method="post" action="ssadmin/addSwapHouse.html"
          class="pageForm required-validate" enctype="multipart/form-data"
          onsubmit="return iframeCallback(this, dialogAjaxDone);">

        <div class="pageFormContent nowrap" layoutH="97">
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
                        <td colspan="2" style="width: 100px;"></td>
                        <td style="width: 60px;">电话</td>
                        <td style="width: 100px;"></td>
                        <td style="width: 60px;">房屋用途</td>
                        <td style="width: 60px;">建筑面积</td>
                        <td style="width: 60px;">产权比例</td>
                        <td style="width: 80px;">房屋评估单价</td>
                    </tr>
                    <tr>
                        <td colspan="3">公房承租人</td>
                        <td colspan="2"></td>
                        <td>电话</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td colspan="5">个人身份证号或单位账号</td>
                        <td colspan="6"></td>
                    </tr>
                    <tr>
                        <td colspan="5">被征收房屋地址</td>
                        <td colspan="6"></td>
                    </tr>
                    <tr>
                        <td style="width: 35px;">序号</td>
                        <td colspan="4">补偿内容</td>
                        <td colspan="3">计算公式</td>
                        <td colspan="2">金额</td>
                        <td>备注</td>
                    </tr>
                    <tr>
                        <td>一</td>
                        <td colspan="4">房屋价值补偿</td>
                        <td colspan="3"></td>
                        <td colspan="2"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>二</td>
                        <td colspan="4">室内装饰装修补偿</td>
                        <td colspan="3"></td>
                        <td colspan="2"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td rowspan="9">三</td>
                        <td rowspan="9" style="width: 35px;">房屋附属设施补偿</td>
                        <td colspan="3" class="td_left">1、构建物补偿</td>
                        <td colspan="3"></td>
                        <td colspan="2"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td colspan="3" class="td_left">2、电表迁移费</td>
                        <td colspan="3"></td>
                        <td colspan="2"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td colspan="3" class="td_left">3、水表迁移费</td>
                        <td colspan="3"></td>
                        <td colspan="2"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td colspan="3" class="td_left">4、空调移机费</td>
                        <td colspan="3"></td>
                        <td colspan="2"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td colspan="3" class="td_left">5、管道煤气拆装补偿</td>
                        <td colspan="3"></td>
                        <td colspan="2"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td colspan="3" class="td_left">6、热水器拆装补偿</td>
                        <td colspan="3"></td>
                        <td colspan="2"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td colspan="3">&nbsp;</td>
                        <td colspan="3"></td>
                        <td colspan="2"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td colspan="3">&nbsp;</td>
                        <td colspan="3"></td>
                        <td colspan="2"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td colspan="3">&nbsp;</td>
                        <td colspan="3"></td>
                        <td colspan="2"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>四</td>
                        <td colspan="4" class="td_left">搬迁补偿（搬家费）</td>
                        <td colspan="3"></td>
                        <td colspan="2"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>五</td>
                        <td colspan="4" class="td_left">临时安置补偿（过渡费）</td>
                        <td colspan="3"></td>
                        <td colspan="2"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>六</td>
                        <td colspan="4" class="td_left">停产停业损失补偿</td>
                        <td colspan="3"></td>
                        <td colspan="2"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>七</td>
                        <td colspan="4" class="td_left">货币补偿补助</td>
                        <td colspan="3"></td>
                        <td colspan="2"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>八</td>
                        <td colspan="4" class="td_left">生活困难补助</td>
                        <td colspan="3"></td>
                        <td colspan="2"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>九</td>
                        <td colspan="4" class="td_left">住改商补助</td>
                        <td colspan="3"></td>
                        <td colspan="2"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>十</td>
                        <td colspan="4" class="td_left">搬迁奖励</td>
                        <td colspan="3"></td>
                        <td colspan="2"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>十一</td>
                        <td colspan="4" class="td_left">封闭阳台</td>
                        <td colspan="3"></td>
                        <td colspan="2"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>十二</td>
                        <td colspan="4" class="td_left">未登记建筑</td>
                        <td colspan="3"></td>
                        <td colspan="2"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>十三</td>
                        <td colspan="4" class="td_left">其他</td>
                        <td colspan="3"></td>
                        <td colspan="2"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td colspan="5">应付款合计</td>
                        <td colspan="3"></td>
                        <td colspan="2"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td rowspan="3">仅产权调换方式</td>
                        <td colspan="3">产权调换房屋应收价款</td>
                        <td style="width: 140px;">建筑面积&nbsp; m<sup>2</sup></td>
                        <td colspan="3"></td>
                        <td colspan="2"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td rowspan="2" colspan="3">实际结算</td>
                        <td>收款</td>
                        <td colspan="3">&nbsp;</td>
                        <td rowspan="3">被征收人签字</td>
                        <td rowspan="3" colspan="2">&nbsp;</td>
                    </tr>
                    <tr>
                        <td>付款</td>
                        <td colspan="3">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="4">实际金额(大写)</td>
                        <td colspan="4"></td>
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
