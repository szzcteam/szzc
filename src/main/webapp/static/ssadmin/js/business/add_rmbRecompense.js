$(document).ready(function () {
    $("#rmbRecompenseDiv input[name='houseOwner']").eq(0).blur(function () {
        rmbRecompenseObj.houseNameSync($(this).val());
    });


    //附属设施，小计
    $("#subsidiary_item input[type='text']").each(function (i, obj) {
        var nameTxt = $(obj).prop("name");
        //除小计之外的框，都绑定事件
        if(nameTxt != "subtotal") {
            $(obj).on("blur change", function () {
                var subtotal = 0;
                //框框求和
                $("#subsidiary_item input").each(function (i, objt) {
                    var nameTxt1 = $(objt).prop("name");
                    if(nameTxt1 != 'subtotal' ) {
                        var v = $(objt).val() || 0;
                        subtotal += new Number(v);
                    }
                });
                $("#subsidiary_item input[name='subtotal']").val(subtotal).change();
            });
        }

    });

    //13项之和，转大写
    $("#rmbRecompenseDiv input[name='sumRbm']").eq(0).on("blur change", function () {
        rmbRecompenseObj.sumRmbToUpper();
    });


});



var rmbRecompenseObj = {

    //被征收人失去焦点，查询结算单
    houseNameSync: function (houseName) {
        if(!houseName){
            return;
        }

        //ajax请求结算单数据，进行协议填充
        $.ajax({
            url: "ssadmin/settleAccounts/detail.html",
            type: "post",
            data: {"houseOwner": houseName},
            dataType: "json",
            success: function (data) {
                console.log(data);
                if (data == null) {
                    console.log("查询结算单信息为空，姓名:" + houseName);
                    return;
                }
                alertMsg.confirm("检测到 "+houseName + " 有【结算单】，是否使用结算单数据进行填充？", {
                    okCall: function(){
                        //被征收房屋地址
                        $("#rmbRecompenseDiv input[name='address']").eq(0).val(data.address);
                        //用途s
                        $("#rmbRecompenseDiv input[name='useing']").eq(0).val(data.useing);
                        //建筑面积
                        $("#rmbRecompenseDiv input[name='checkInArea']").eq(0).val(data.checkInArea);
                        //评估机构评估的补偿金额
                        $("#rmbRecompenseDiv input[name='valueCompensate']").eq(0).val(data.valueCompensate);
                        //室内装修补偿
                        $("#rmbRecompenseDiv input[name='decorationCompensate']").eq(0).val(data.decorationCompensate);
                        //构建物
                        $("#rmbRecompenseDiv input[name='structureCompensate']").eq(0).val(data.structureCompensate).change();
                        //电表迁移费
                        $("#rmbRecompenseDiv input[name='moveAmmeterFee']").eq(0).val(data.moveAmmeterFee).change();
                        //水表迁移费
                        $("#rmbRecompenseDiv input[name='moveWaterMeterFee']").eq(0).val(data.moveWaterMeterFee).change();
                        //空调移机费
                        $("#rmbRecompenseDiv input[name='moveAirConditioningFee']").eq(0).val(data.moveAirConditioningFee).change();
                        //管道煤气拆装补偿
                        $("#rmbRecompenseDiv input[name='gasFee']").eq(0).val(data.gasFee).change();
                        //热水器拆装补偿
                        $("#rmbRecompenseDiv input[name='hotWaterCompensate']").eq(0).val(data.hotWaterCompensate).change();
                        //搬家费
                        $("#rmbRecompenseDiv input[name='moveHouseFee']").eq(0).val(data.moveHouseFee);
                        //临时安置补偿(过渡费)
                        var calcInterimFee = data.calcInterimFee;
                        var calcInterimFeeArr = calcInterimFee.split("*");
                        $("#rmbRecompenseDiv input[name='interimArea']").eq(0).val(calcInterimFeeArr[0]);
                        $("#rmbRecompenseDiv input[name='interimPrice']").eq(0).val(calcInterimFeeArr[1]);
                        $("#rmbRecompenseDiv input[name='interimMonth']").eq(0).val(calcInterimFeeArr[2]);
                        $("#rmbRecompenseDiv input[name='interimFee']").eq(0).val(data.interimFee);
                        //货币补偿补助
                        $("#rmbRecompenseDiv input[name='rmbCompensate']").eq(0).val(data.rmbCompensate).change();
                        //停产停业损失补偿
                        $("#rmbRecompenseDiv input[name='suspendBusinessFee']").eq(0).val(data.suspendBusinessFee);
                        //生活困难补助
                        $("#rmbRecompenseDiv input[name='lifeCompensate']").eq(0).val(data.lifeCompensate);
                        //住改商补助
                        $("#rmbRecompenseDiv input[name='changeCompensate']").eq(0).val(data.changeCompensate);
                        //搬迁奖励
                        $("#rmbRecompenseDiv input[name='moveReward']").eq(0).val(data.moveReward);
                        //未登记建筑面积
                        var calcNoCheckCompensate = data.calcNoCheckCompensate;
                        if(calcNoCheckCompensate) {
                            var calcNoCheckCompensateArr = calcNoCheckCompensate.split("*");
                            $("#rmbRecompenseDiv input[name='noCheckArea']").eq(0).val(calcNoCheckCompensateArr[0]);
                        }

                    }
                });
            }
        });


    },
    //统计13项，人民币之和转大写
    sumRmbToUpper:function () {
        var rmb = $("#rmbRecompenseDiv input[name='sumRbm']").eq(0).val() || 0;
        var rmb_upper = Araia_To_Chinese(rmb);
        $("#rmbRecompenseDiv input[name='upperRmb']").eq(0).val(rmb_upper);
    }

}