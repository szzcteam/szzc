$(document).ready(function () {
    $("#rmbRecompenseDiv input[name='houseOwner']").eq(0).blur(function () {
        var houseOwner = $("#rmbRecompenseDiv input[name='houseOwner']").eq(0).val();
        var address = $("#rmbRecompenseDiv input[name='address']").eq(0).val();
        rmbRecompenseObj.houseNameSync(houseOwner, address);
    });

    $("#rmbRecompenseDiv input[name='address']").eq(0).blur(function () {
        var houseOwner = $("#rmbRecompenseDiv input[name='houseOwner']").eq(0).val();
        var address = $("#rmbRecompenseDiv input[name='address']").eq(0).val();
        rmbRecompenseObj.houseNameSync(houseOwner, address);
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

    //补偿款项绑定事件
    var compensateItem = new Array();
    compensateItem[0]= "valueCompensate";
    compensateItem[1]= "decorationCompensate";
    compensateItem[2]= "subtotal";
    compensateItem[3]= "moveHouseFee";
    compensateItem[4]= "interimFee";
    compensateItem[5]= "suspendBusinessFee";
    compensateItem[6]= "rmbCompensate";
    compensateItem[7]= "lifeCompensate";
    compensateItem[8]= "changeCompensate";
    compensateItem[9]= "moveReward";
    compensateItem[10]= "closeBalcony";
    compensateItem[11]= "noCheckCompensate";
    compensateItem[12]= "otherFee";
    for(var i=0;i<compensateItem.length;i++){
        var nameText = compensateItem[i];
        $("#rmbRecompenseDiv input[name='"+nameText+"']").eq(0).on("blur change", function () {
            rmbRecompenseObj.summary13Num();
        });
    }

    //临时安置过渡费3小框，绑定事件
    $("#rmbRecompenseDiv input[name='interimArea']").eq(0).on("blur change", function () {
        rmbRecompenseObj.fullInterimFee();
    });
    $("#rmbRecompenseDiv input[name='interimPrice']").eq(0).on("blur change", function () {
        rmbRecompenseObj.fullInterimFee();
    });
    $("#rmbRecompenseDiv input[name='interimMonth']").eq(0).on("blur change", function () {
        rmbRecompenseObj.fullInterimFee();
    });


});



var rmbRecompenseObj = {

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

                        rmbRecompenseObj.summary13Num();
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
    },

    //汇总13项之和
    summary13Num:function () {
        var valueCompensate = $("#rmbRecompenseDiv  input[name='valueCompensate']").eq(0).val() || 0;
        var valueCompensate_num = new Number(valueCompensate);

        var decorationCompensate = $("#rmbRecompenseDiv  input[name='decorationCompensate']").eq(0).val() || 0;
        var decorationCompensate_num = new Number(decorationCompensate);

        var subtotal = $("#rmbRecompenseDiv  input[name='subtotal']").eq(0).val() || 0;
        var subtotal_num = new Number(subtotal);

        var moveHouseFee = $("#rmbRecompenseDiv  input[name='moveHouseFee']").eq(0).val() || 0;
        var moveHouseFee_num = new Number(moveHouseFee);

        var interimFee = $("#rmbRecompenseDiv  input[name='interimFee']").eq(0).val() || 0;
        var interimFee_num = new Number(interimFee);

        var suspendBusinessFee = $("#rmbRecompenseDiv  input[name='suspendBusinessFee']").eq(0).val() || 0;
        var suspendBusinessFee_num = new Number(suspendBusinessFee);

        var rmbCompensate = $("#rmbRecompenseDiv  input[name='rmbCompensate']").eq(0).val() || 0;
        var rmbCompensate_num = new Number(rmbCompensate);

        var lifeCompensate = $("#rmbRecompenseDiv  input[name='lifeCompensate']").eq(0).val() || 0;
        var lifeCompensate_num = new Number(lifeCompensate);

        var changeCompensate = $("#rmbRecompenseDiv  input[name='changeCompensate']").eq(0).val() || 0;
        var changeCompensate_num = new Number(changeCompensate);

        var moveReward = $("#rmbRecompenseDiv  input[name='moveReward']").eq(0).val() || 0;
        var moveReward_num = new Number(moveReward);

        var closeBalcony = $("#rmbRecompenseDiv  input[name='closeBalcony']").eq(0).val() || 0;
        var closeBalcony_num = new Number(closeBalcony);

        var noCheckCompensate = $("#rmbRecompenseDiv  input[name='noCheckCompensate']").eq(0).val() || 0;
        var noCheckCompensate_num = new Number(noCheckCompensate);

        var otherFee = $("#rmbRecompenseDiv  input[name='otherFee']").eq(0).val() || 0;
        var otherFee_num = new Number(otherFee);

        var sumRbm = valueCompensate_num + decorationCompensate_num + subtotal_num +
            moveHouseFee_num + interimFee_num + suspendBusinessFee_num +rmbCompensate_num+
            lifeCompensate_num + changeCompensate_num + moveReward_num +
            closeBalcony_num + noCheckCompensate_num + otherFee_num;
        $("#rmbRecompenseDiv  input[name='sumRbm']").eq(0).val(sumRbm).change();
    },

    //临时安置补偿，利用3小框，得到计算公式
    fullInterimFee: function () {
        var calcInterimFeeArea = $("#rmbRecompenseDiv input[name='interimArea']").eq(0).val() || 0;
        var calcInterimFeePrice = $("#rmbRecompenseDiv input[name='interimPrice']").eq(0).val() || 0;
        var calcInterimFeeMonth = $("#rmbRecompenseDiv input[name='interimMonth']").eq(0).val() || 0;
        var calcInterimFee = calcInterimFeeArea + "*" + calcInterimFeePrice + "*" + calcInterimFeeMonth;
        console.log("临时过渡费计算公式:" + calcInterimFee);
        var interimFee = Math.round(eval(calcInterimFee));
        $("#rmbRecompenseDiv input[name='interimFee']").eq(0).val(interimFee).change();
    },

}