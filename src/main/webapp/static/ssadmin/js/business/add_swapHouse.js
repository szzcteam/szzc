$(document).ready(function () {

    $("#swapHouseDiv input[name='houseOwner']").eq(0).blur(function () {
        var houseOwner = $("#swapHouseDiv input[name='houseOwner']").eq(0).val();
        var address = $("#swapHouseDiv input[name='address']").eq(0).val();
        swapHouseObj.houseNameSync(houseOwner, address);
    });

    $("#swapHouseDiv input[name='address']").eq(0).blur(function () {
        var houseOwner = $("#swapHouseDiv input[name='houseOwner']").eq(0).val();
        var address = $("#swapHouseDiv input[name='address']").eq(0).val();
        swapHouseObj.houseNameSync(houseOwner, address);
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


    //12项之和，转大写
    $("#swapHouseDiv input[name='sumRbm']").eq(0).on("blur change", function () {
        swapHouseObj.sumRmbToUpper();
    });

    //支付期限，差额
    $("#swapHouseDiv  input[name='difference']").eq(0).on("blur change", function () {
        swapHouseObj.differenceToUpper();
    });

    $("#swapHouseDiv  input[name='lessDifference']").eq(0).on("blur change", function () {
        swapHouseObj.lessDifferenceToUpper();
    });

    //调换房金额
    $("#swapHouseDiv  input[name='totalPrice']").eq(0).on("blur change", function () {
        swapHouseObj.totalPriceToUpper();
    });

    //调换房面积、金额触发事件
    $("#swapHouseDiv  input[name='coveredArea']").eq(0).on("blur change", function () {
        swapHouseObj.calcNewHouseMoney();
    });
    $("#swapHouseDiv  input[name='price']").eq(0).on("blur change", function () {
        swapHouseObj.calcNewHouseMoney();
    });

    //补偿款项绑定事件
    var compensateItem = new Array();
    compensateItem[0]= "valueCompensate";
    compensateItem[1]= "decorationCompensate";
    compensateItem[2]= "subtotal";
    compensateItem[3]= "moveHouseFee";
    compensateItem[4]= "interimFee";
    compensateItem[5]= "suspendBusinessFee";
    compensateItem[6]= "lifeCompensate";
    compensateItem[7]= "changeCompensate";
    compensateItem[8]= "moveReward";
    compensateItem[9]= "closeBalcony";
    compensateItem[10]= "noCheckCompensate";
    compensateItem[11]= "otherFee";
    for(var i=0;i<compensateItem.length;i++){
        var nameText = compensateItem[i];
        $("#swapHouseDiv input[name='"+nameText+"']").eq(0).on("blur change", function () {
            swapHouseObj.summary12Num();
        });
    }

    //临时安置过渡费3小框，绑定事件
    $("#swapHouseDiv input[name='interimArea']").eq(0).on("blur change", function () {
        swapHouseObj.fullInterimFee();
    });
    $("#swapHouseDiv input[name='interimPrice']").eq(0).on("blur change", function () {
        swapHouseObj.fullInterimFee();
    });
    $("#swapHouseDiv input[name='interimMonth']").eq(0).on("blur change", function () {
        swapHouseObj.fullInterimFee();
    });



});


var swapHouseObj = {

    //被征收人失去焦点，查询结算单
    houseNameSync: function (houseName, address) {
        if(!houseName){
            return;
        }

        if(!address){
            return;
        }

        //ajax请求结算单数据，进行协议填充
        $.ajax({
            url: "ssadmin/settleAccounts/detail.html",
            type: "post",
            data: {"houseOwner": houseName, "address": address},
            dataType: "json",
            success: function (data) {
                console.log(data);
                if (data == null) {
                    console.log("查询结算单信息为空，姓名:" + houseName);
                    return;
                }
                alertMsg.confirm("检测到 "+houseName + ",地址: "+address+" 有【结算单】，是否使用结算单数据进行填充？", {
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

                        //应付
                        $("#swapHouseDiv input[name='difference']").eq(0).val(data.payTotal);
                        $("#swapHouseDiv input[name='upperDifference']").eq(0).val(data.payMoney);

                        //应收
                        $("#swapHouseDiv input[name='lessDifference']").eq(0).val(data.receiveTotal);
                        $("#swapHouseDiv input[name='upperLessDifference']").eq(0).val(data.receiveMoney);

                        //触发一下求和
                        swapHouseObj.summary12Num();

                    }
                });
            }
        });


    },

    //统计12项，人民币之和转大写
    sumRmbToUpper:function () {
        var rmb = $("#swapHouseDiv input[name='sumRbm']").eq(0).val() || 0;
        var rmb_upper = Araia_To_Chinese(rmb);
        $("#swapHouseDiv input[name='upperRmb']").eq(0).val(rmb_upper);
    },

    //支付期限，差额大写
    differenceToUpper: function () {
        var difference = $("#swapHouseDiv  input[name='difference']").eq(0).val();
        var rmb_upper = Araia_To_Chinese(difference);
        $("#swapHouseDiv input[name='upperDifference']").eq(0).val(rmb_upper);
    },

    //支付期限，差额2
    lessDifferenceToUpper:function () {
        var lessDifference = $("#swapHouseDiv  input[name='lessDifference']").eq(0).val();
        var rmb_upper = Araia_To_Chinese(lessDifference);
        $("#swapHouseDiv input[name='upperLessDifference']").eq(0).val(rmb_upper);
    },

    //调换房，金额
    totalPriceToUpper:function () {
        var totalPrice = $("#swapHouseDiv  input[name='totalPrice']").eq(0).val();
        var rmb_upper = Araia_To_Chinese(totalPrice);
        $("#swapHouseDiv input[name='upperTotalPrice']").eq(0).val(rmb_upper);
    },

    //调换房金额计算
    calcNewHouseMoney:function () {
        var coveredArea = $("#swapHouseDiv  input[name='coveredArea']").eq(0).val() || 0;
        var price = $("#swapHouseDiv  input[name='price']").eq(0).val() || 0;
        var money = coveredArea + "*" + price;
        money = Math.round(eval(money));
        $("#swapHouseDiv  input[name='totalPrice']").eq(0).val(money).change();

    },

    //汇总12项之和
    summary12Num:function () {
        var valueCompensate = $("#swapHouseDiv  input[name='valueCompensate']").eq(0).val() || 0;
        var valueCompensate_num = new Number(valueCompensate);

        var decorationCompensate = $("#swapHouseDiv  input[name='decorationCompensate']").eq(0).val() || 0;
        var decorationCompensate_num = new Number(decorationCompensate);

        var subtotal = $("#swapHouseDiv  input[name='subtotal']").eq(0).val() || 0;
        var subtotal_num = new Number(subtotal);

        var moveHouseFee = $("#swapHouseDiv  input[name='moveHouseFee']").eq(0).val() || 0;
        var moveHouseFee_num = new Number(moveHouseFee);

        var interimFee = $("#swapHouseDiv  input[name='interimFee']").eq(0).val() || 0;
        var interimFee_num = new Number(interimFee);

        var suspendBusinessFee = $("#swapHouseDiv  input[name='suspendBusinessFee']").eq(0).val() || 0;
        var suspendBusinessFee_num = new Number(suspendBusinessFee);

        var lifeCompensate = $("#swapHouseDiv  input[name='lifeCompensate']").eq(0).val() || 0;
        var lifeCompensate_num = new Number(lifeCompensate);

        var changeCompensate = $("#swapHouseDiv  input[name='changeCompensate']").eq(0).val() || 0;
        var changeCompensate_num = new Number(changeCompensate);

        var moveReward = $("#swapHouseDiv  input[name='moveReward']").eq(0).val() || 0;
        var moveReward_num = new Number(moveReward);

        var closeBalcony = $("#swapHouseDiv  input[name='closeBalcony']").eq(0).val() || 0;
        var closeBalcony_num = new Number(closeBalcony);

        var noCheckCompensate = $("#swapHouseDiv  input[name='noCheckCompensate']").eq(0).val() || 0;
        var noCheckCompensate_num = new Number(noCheckCompensate);

        var otherFee = $("#swapHouseDiv  input[name='otherFee']").eq(0).val() || 0;
        var otherFee_num = new Number(otherFee);

        var sumRbm = valueCompensate_num + decorationCompensate_num + subtotal_num +
            moveHouseFee_num + interimFee_num + suspendBusinessFee_num +
            lifeCompensate_num + changeCompensate_num + moveReward_num +
            closeBalcony_num + noCheckCompensate_num + otherFee_num;
        $("#swapHouseDiv  input[name='sumRbm']").eq(0).val(sumRbm).change();
    },

    //临时安置补偿，利用3小框，得到计算公式
    fullInterimFee: function () {
        var calcInterimFeeArea = $("#swapHouseDiv input[name='interimArea']").eq(0).val() || 0;
        var calcInterimFeePrice = $("#swapHouseDiv input[name='interimPrice']").eq(0).val() || 0;
        var calcInterimFeeMonth = $("#swapHouseDiv input[name='interimMonth']").eq(0).val() || 0;
        var calcInterimFee = calcInterimFeeArea + "*" + calcInterimFeePrice + "*" + calcInterimFeeMonth;
        console.log("临时过渡费计算公式:" + calcInterimFee);
        var interimFee = Math.round(eval(calcInterimFee));
        $("#swapHouseDiv input[name='interimFee']").eq(0).val(interimFee).change();
    },

}