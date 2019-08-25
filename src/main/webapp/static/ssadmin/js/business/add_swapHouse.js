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

    //生活困难小计
    $("#swapHouseDiv input[name='basicLivingSubsidy'],#swapHouseDiv input[name='disabilitySubsidy'],#swapHouseDiv input[name='diseaseSubsidy']," +
        "#swapHouseDiv input[name='noChild'],#swapHouseDiv input[name='martyr']").on("blur change", function () {
        var basicLivingSubsidy = $("#swapHouseDiv input[name='basicLivingSubsidy']").eq(0).val();
        var disabilitySubsidy = $("#swapHouseDiv input[name='disabilitySubsidy']").eq(0).val();
        var diseaseSubsidy = $("#swapHouseDiv input[name='diseaseSubsidy']").eq(0).val();
        var noChild = $("#swapHouseDiv input[name='noChild']").eq(0).val();
        var martyr = $("#swapHouseDiv input[name='martyr']").eq(0).val();
        var calc_lifeCompensate = basicLivingSubsidy + "+" + disabilitySubsidy + "+" + diseaseSubsidy + "+" + noChild + "+" + martyr;
        $("#swapHouseDiv input[name='lifeCompensate']").eq(0).val(eval(calc_lifeCompensate)).change();
    });

    //附属设施，小计
    $("#swapSubsidiary_item input[type='text']").each(function (i, obj) {
        var nameTxt = $(obj).prop("name");
        //除小计之外的框，都绑定事件
        if(nameTxt != "subtotal") {
            $(obj).on("blur change", function () {
                var subtotal = 0;
                //框框求和
                $("#swapSubsidiary_item input").each(function (i, objt) {
                    var nameTxt1 = $(objt).prop("name");
                    if(nameTxt1 != 'subtotal' ) {
                        var v = $(objt).val() || 0;
                        subtotal += new Number(v);
                    }
                });
                $("#swapSubsidiary_item input[name='subtotal']").val(subtotal).change();
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
    compensateItem[1]= "noRegisterLegal";
    compensateItem[2]= "historyLegacy";
    compensateItem[3]= "decorationCompensate";
    compensateItem[4]= "moveHouseFee";
    compensateItem[5]= "interimFee";
    compensateItem[6]= "guarantee";
    compensateItem[7]= "suspendBusinessFee";
    compensateItem[8]= "subtotal";
    compensateItem[9]= "changeCompensate";
    compensateItem[10]= "lifeCompensate";
    compensateItem[11]= "rmbCompensate";
    compensateItem[12]= "buildingAreaFee";
    compensateItem[13]= "moveReward";
    compensateItem[14]= "otherFee";
    for(var i=0;i<compensateItem.length;i++){
        var nameText = compensateItem[i];
        $("#swapHouseDiv input[name='"+nameText+"']").eq(0).on("blur change", function () {
            swapHouseObj.summaryNum();
        });
    }



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
                        //证载建筑面积
                        $("#swapHouseDiv input[name='certifiedArea']").eq(0).val(data.certifiedArea);
                        //证载房屋用途
                        $("#swapHouseDiv input[name='useing']").eq(0).val(data.useing);
                        //房屋价值评估单价
                        var calcValueCompensateArr = data.calcValueCompensate.split("*");
                        $("#swapHouseDiv input[name='assessPrice']").eq(0).val(calcValueCompensateArr[1]);
                        //用于经营的实际面积  == 对应结算单第8条的，住改商面积
                        if(data.changeCompensate != null && data.changeCompensate > 0 ){
                            var calcChangeCompensate = data.calcChangeCompensate.split("*");
                            $("#swapHouseDiv input[name='valueCompensateBusinessArea']").eq(0).val(calcChangeCompensate[0]);
                            $("#swapHouseDiv input[name='valueCompensateRate']").eq(0).val(eval(calcChangeCompensate[2]*100));
                        }

                        //未登记的合法建筑面积
                        $("#swapHouseDiv input[name='noRegisterLegalArea']").eq(0).val(data.noRegisterLegalArea);
                        //评估单价
                        if(data.noRegisterLegal != null && data.noRegisterLegal >0){
                            var calcNoRegisterLegal = data.calcNoRegisterLegal.split("*");
                            $("#swapHouseDiv input[name='noRegisterAssessPrice']").eq(0).val(calcNoRegisterLegal[1]);
                        }
                        //补偿比例
                        // $("#swapHouseDiv input[name='noRegisterProportion']").eq(0).val(data.assessPrice);

                        //历史遗留建筑面积
                        $("#swapHouseDiv input[name='historyLegacyArea']").eq(0).val(data.historyLegacyArea);
                        //评估单价
                        if(data.historyLegacy != null && data.historyLegacy >0){
                            var calcHistoryLegacy = data.calcHistoryLegacy.split("*");
                            $("#swapHouseDiv input[name='historyAssessPrice']").eq(0).val(calcHistoryLegacy[1]);
                        }

                        //补偿比例
                        // $("#swapHouseDiv input[name='historyProportion']").eq(0).val(data.assessPrice);

                        //有证房屋补偿金额
                        $("#swapHouseDiv input[name='valueCompensate']").eq(0).val(data.valueCompensate);
                        $("#swapHouseDiv input[name='noRegisterLegal']").eq(0).val(data.noRegisterLegal);
                        $("#swapHouseDiv input[name='historyLegacy']").eq(0).val(data.historyLegacy);

                        //室内装修补偿
                        if(data.decorationCompensate != null && data.decorationCompensate > 0){
                            var calcDecorationCompensate = data.calcDecorationCompensate.split("*");
                            $("#swapHouseDiv input[name='decorationCompensateUnitPrice']").eq(0).val(calcDecorationCompensate[1]);
                            $("#swapHouseDiv input[name='decorationCompensate']").eq(0).val(data.decorationCompensate)
                        }
                        ;
                        //搬家费
                        $("#swapHouseDiv input[name='moveHouseFee']").eq(0).val(data.moveHouseFee);
                        //临时安置补偿(过渡费)
                        var calcInterimFee = data.calcInterimFee;
                        var calcInterimFeeArr = calcInterimFee.split("*");
                        // $("#swapHouseDiv input[name='interimArea']").eq(0).val(calcInterimFeeArr[0]);
                        $("#swapHouseDiv input[name='interimPrice']").eq(0).val(calcInterimFeeArr[1]);
                        // $("#swapHouseDiv input[name='interimMonth']").eq(0).val(calcInterimFeeArr[2]);
                        $("#swapHouseDiv input[name='interimFee']").eq(0).val(data.interimFee);

                        //保底
                        $("#swapHouseDiv input[name='guarantee']").eq(0).val(data.guarantee);

                        //停产停业损失补偿
                        $("#swapHouseDiv input[name='suspendBusinessFee']").eq(0).val(data.suspendBusinessFee);

                        //附属设施开始
                        //水表迁移费
                        $("#swapHouseDiv input[name='moveWaterMeterFee']").eq(0).val(data.moveWaterMeterFee);
                        //电表迁移费
                        $("#swapHouseDiv input[name='moveAmmeterFee']").eq(0).val(data.moveAmmeterFee);
                        //空调移机费
                        $("#swapHouseDiv input[name='moveAirConditioningFee']").eq(0).val(data.moveAirConditioningFee);
                        //热水器拆装补偿
                        if(data.hotWaterCompensate != null && data.hotWaterCompensate > 0){
                            var hot_water_index = data.calcHotWaterCompensate.indexOf("+");
                            //太阳能热水器
                            var hot_d_calc = data.calcHotWaterCompensate.substring(hot_water_index+1, data.calcHotWaterCompensate.length);
                            $("#swapHouseDiv input[name='solarHeater']").eq(0).val(eval(hot_d_calc));
                            //其他  电热水器
                            var hot_value = data.calcHotWaterCompensate.substring(0, hot_water_index);
                            $("#swapHouseDiv input[name='otherHeater']").eq(0).val(eval(hot_value));
                        }

                        //管道煤气拆装补偿
                        $("#swapHouseDiv input[name='gasFee']").eq(0).val(data.gasFee);
                        //构建物
                        $("#swapHouseDiv input[name='structureBalcony']").eq(0).val(data.structureBalcony);
                        $("#swapHouseDiv input[name='structureBuild']").eq(0).val(data.structureBuild);
                        $("#swapHouseDiv input[name='structureDark']").eq(0).val(data.structureDark);
                        $("#swapHouseDiv input[name='structureMezzanine']").eq(0).val(data.structureMezzanine);
                        $("#swapHouseDiv input[name='structureRoof']").eq(0).val(data.structureRoof);
                        $("#swapHouseDiv input[name='affiliatedOther']").eq(0).val(data.affiliatedOther);
                        //附属设施结束，触发一次小计求和
                        $("#swapHouseDiv input[name='moveWaterMeterFee']").eq(0).change();

                        //住改商补助
                        $("#swapHouseDiv input[name='changeCompensate']").eq(0).val(data.changeCompensate);
                        //生活困难补助
                        if(data.lifeCompensate != null && data.lifeCompensate > 0){
                            $("#swapHouseDiv input[name='lifeCompensate']").eq(0).val(data.lifeCompensate);
                            var lifeCompensateArr = data.calcLifeCompensate.split("+");
                            $("#swapHouseDiv input[name='diseaseSubsidy']").val(lifeCompensateArr[0]);
                            $("#swapHouseDiv input[name='disabilitySubsidy']").val(lifeCompensateArr[1]);
                            $("#swapHouseDiv input[name='basicLivingSubsidy']").val(lifeCompensateArr[2])
                            $("#swapHouseDiv input[name='martyr']").val(lifeCompensateArr[3]);
                            $("#swapHouseDiv input[name='noChild']").val(lifeCompensateArr[4]);
                        }

                        //货币补偿补助
                        $("#swapHouseDiv input[name='rmbCompensate']").eq(0).val(data.rmbCompensate);
                        //建筑面积补助
                        $("#swapHouseDiv input[name='buildingAreaFee']").eq(0).val(data.buildingAreaFee);
                        //小户型困难补助
                        $("#swapHouseDiv input[name='smallAreaReward']").eq(0).val(data.smallAreaReward);
                        //按期签约搬迁奖励
                        $("#swapHouseDiv input[name='moveReward']").eq(0).val(data.moveReward);
                        //其他
                        $("#swapHouseDiv input[name='otherFee']").eq(0).val(data.otherFee);

                        //触发所有条款求和
                        swapHouseObj.summaryNum();

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
    summaryNum:function () {
        var valueCompensate = $("#swapHouseDiv  input[name='valueCompensate']").eq(0).val() || 0;
        var noRegisterLegal = $("#swapHouseDiv  input[name='noRegisterLegal']").eq(0).val() || 0;
        var historyLegacy = $("#swapHouseDiv  input[name='historyLegacy']").eq(0).val() || 0;
        var decorationCompensate = $("#swapHouseDiv  input[name='decorationCompensate']").eq(0).val() || 0;
        var moveHouseFee = $("#swapHouseDiv  input[name='moveHouseFee']").eq(0).val() || 0;
        var interimFee = $("#swapHouseDiv  input[name='interimFee']").eq(0).val() || 0;
        var guarantee = $("#swapHouseDiv  input[name='guarantee']").eq(0).val() || 0;
        var suspendBusinessFee = $("#swapHouseDiv  input[name='suspendBusinessFee']").eq(0).val() || 0;
        //附属设施，求和了
        var subtotal = $("#swapHouseDiv  input[name='subtotal']").eq(0).val() || 0;
        var changeCompensate = $("#swapHouseDiv  input[name='changeCompensate']").eq(0).val() || 0;
        //生活困难补助求和了
        var lifeCompensate = $("#swapHouseDiv  input[name='lifeCompensate']").eq(0).val() || 0;
        var rmbCompensate = $("#swapHouseDiv  input[name='rmbCompensate']").eq(0).val() || 0;
        var buildingAreaFee = $("#swapHouseDiv  input[name='buildingAreaFee']").eq(0).val() || 0;
        var moveReward = $("#swapHouseDiv  input[name='moveReward']").eq(0).val() || 0;
        var otherFee = $("#swapHouseDiv  input[name='otherFee']").eq(0).val() || 0;

        var sumRbm_calc = valueCompensate + "+" + noRegisterLegal + "+" + historyLegacy + "+" + decorationCompensate + "+" +
            moveHouseFee + "+" + interimFee + "+" + guarantee + "+" + suspendBusinessFee + "+" + subtotal + "+" + changeCompensate + "+" +
            lifeCompensate + "+" + rmbCompensate + "+" + buildingAreaFee + "+" + moveReward + "+" + otherFee;
        var sumRbm = eval(sumRbm_calc);
        $("#swapHouseDiv  input[name='sumRbm']").eq(0).val(sumRbm).change();
    }

}