$(document).ready(function () {

    //是3个页面在一起，所以区分用户名；  产权调换协议
    $("#swapHouseDiv input[name='houseOwner']").eq(0).blur(function () {
       swapHouseObj.houseNameSync($(this).val());
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


});


var swapHouseObj = {

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
                        $("#swapHouseDiv input[name='address']").eq(0).val(data.address);
                        //用途s
                        $("#swapHouseDiv input[name='useing']").eq(0).val(data.useing);
                        //建筑面积
                        $("#swapHouseDiv input[name='checkInArea']").eq(0).val(data.checkInArea);
                        //评估机构评估的补偿金额
                        $("#swapHouseDiv input[name='valueCompensate']").eq(0).val(data.valueCompensate);
                        //室内装修补偿
                        $("#swapHouseDiv input[name='decorationCompensate']").eq(0).val(data.decorationCompensate);
                        //构建物
                        $("#swapHouseDiv input[name='structureCompensate']").eq(0).val(data.structureCompensate).change();
                        //电表迁移费
                        $("#swapHouseDiv input[name='moveAmmeterFee']").eq(0).val(data.moveAmmeterFee).change();
                        //水表迁移费
                        $("#swapHouseDiv input[name='moveWaterMeterFee']").eq(0).val(data.moveWaterMeterFee).change();
                        //空调移机费
                        $("#swapHouseDiv input[name='moveAirConditioningFee']").eq(0).val(data.moveAirConditioningFee).change();
                        //管道煤气拆装补偿
                        $("#swapHouseDiv input[name='gasFee']").eq(0).val(data.gasFee).change();
                        //热水器拆装补偿
                        $("#swapHouseDiv input[name='hotWaterCompensate']").eq(0).val(data.hotWaterCompensate).change();
                        //搬家费
                        $("#swapHouseDiv input[name='moveHouseFee']").eq(0).val(data.moveHouseFee);
                        //临时安置补偿(过渡费)
                        var calcInterimFee = data.calcInterimFee;
                        var calcInterimFeeArr = calcInterimFee.split("*");
                        $("#swapHouseDiv input[name='interimArea']").eq(0).val(calcInterimFeeArr[0]);
                        $("#swapHouseDiv input[name='interimPrice']").eq(0).val(calcInterimFeeArr[1]);
                        $("#swapHouseDiv input[name='interimMonth']").eq(0).val(calcInterimFeeArr[2]);
                        $("#swapHouseDiv input[name='interimFee']").eq(0).val(data.interimFee);
                        //停产停业损失补偿
                        $("#swapHouseDiv input[name='suspendBusinessFee']").eq(0).val(data.suspendBusinessFee);
                        //生活困难补助
                        $("#swapHouseDiv input[name='lifeCompensate']").eq(0).val(data.lifeCompensate);
                        //住改商补助
                        $("#swapHouseDiv input[name='changeCompensate']").eq(0).val(data.changeCompensate);
                        //搬迁奖励
                        $("#swapHouseDiv input[name='moveReward']").eq(0).val(data.moveReward);
                        //未登记建筑面积
                        var calcNoCheckCompensate = data.calcNoCheckCompensate;
                        if(calcNoCheckCompensate) {
                            var calcNoCheckCompensateArr = calcNoCheckCompensate.split("*");
                            $("#swapHouseDiv input[name='noCheckArea']").eq(0).val(calcNoCheckCompensateArr[0]);
                        }

                    }
                });
            }
        });


    }

}