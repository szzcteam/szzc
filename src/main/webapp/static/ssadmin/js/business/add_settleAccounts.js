$(document).ready(function(){
    //房屋评估单价失去焦点
    $("input[name='assessPrice']").eq(0).blur(function () {
        var assessPrice = $(this).val() || 0;
        $("input[name='calcValueCompensatePrice']").val(assessPrice).change();
        //货币补偿补助
        $("input[name='calcRmbCompensatePrice']").val(assessPrice).change();
        //货币搬迁奖励
        $("input[name='calcRmbMoveRewardPrice']").val(assessPrice).change();
    });

    //建筑面积失去焦点
    $("input[name='checkInArea']").eq(0).blur(function () {
        var checkInArea = $(this).val() || 0;
        //填充价值补偿的面积
        $("input[name='calcValueCompensateArea']").val(checkInArea).change();
        //填充装修折旧的面积
        $("input[name='calcDecorationCompensateArea']").val(checkInArea).change();
        //填充临时安置补偿过渡费的面积
        $("input[name='calcInterimFeeArea']").val(checkInArea).change();
        //货币补偿补助
        $("input[name='calcRmbCompensateArea']").val(checkInArea).change();
        //货币搬迁奖励
        $("input[name='calcRmbMoveRewardArea']").val(checkInArea).change();
    });

    //货币补偿补助3小框，失去焦点，重新计算公式
    $("input[name='calcRmbCompensateArea']").eq(0).on("blur change", function () {
        settleAccountObj.fullCalcRmbCompensate();
    });
    $("input[name='calcRmbCompensatePrice']").eq(0).on("blur change", function () {
        settleAccountObj.fullCalcRmbCompensate();
    });
    $("input[name='calcRmbCompensateProportion']").eq(0).on("blur change", function () {
        settleAccountObj.fullCalcRmbCompensate();
    });

    //货币搬迁奖励3小框，失去焦点，重新计算公式
    $("input[name='calcRmbMoveRewardArea']").eq(0).on("blur change", function () {
        settleAccountObj.fullCalcRmbMoveReward();
    });
    $("input[name='calcRmbMoveRewardPrice']").eq(0).on("blur change", function () {
        settleAccountObj.fullCalcRmbMoveReward();
    });
    $("input[name='calcRmbMoveRewardProportion']").eq(0).on("blur change", function () {
        settleAccountObj.fullCalcRmbMoveReward();
    });


    //未登记补偿3小框，失去焦点，重新计算公式
    $("input[name='calcNoCheckCompensateArea']").eq(0).on("blur change", function () {
        settleAccountObj.fullCalcNoCheckCompensate();
    });
    $("input[name='calcNoCheckCompensatePrice']").eq(0).on("blur change", function () {
        settleAccountObj.fullCalcNoCheckCompensate();
    });
    $("input[name='calcNoCheckCompensateProportion']").eq(0).on("blur change", function () {
        settleAccountObj.fullCalcNoCheckCompensate();
    });

    //临时安置3小框，失去焦点，重新计算公式
    $("input[name='calcInterimFeeArea']").eq(0).on("blur change", function () {
        settleAccountObj.fullCalcInterimFee();
    });
    $("input[name='calcInterimFeePrice']").eq(0).on("blur change", function () {
        settleAccountObj.fullCalcInterimFee();
    });
    $("input[name='calcInterimFeeMonth']").eq(0).on("blur change", function () {
        settleAccountObj.fullCalcInterimFee();
    });

    //装修折旧 2小框，失去焦点，重新计算公式
    $("input[name='calcDecorationCompensateArea']").eq(0).on("blur change", function () {
        settleAccountObj.fullCalcDecoration();
    });
    $("input[name='calcDecorationCompensatePrice']").eq(0).on("blur change", function () {
        settleAccountObj.fullCalcDecoration();
    });

    //房屋价值补偿，3小框，失去焦点，重新计算公式
    $("input[name='calcValueCompensateArea']").eq(0).on("blur change", function () {
        settleAccountObj.fullCalcValueCompensate();
    });
    $("input[name='calcValueCompensatePrice']").eq(0).on("blur change", function () {
        settleAccountObj.fullCalcValueCompensate();
    });
    $("input[name='calcValueCompensateProportion']").eq(0).on("blur change", function () {
        settleAccountObj.fullCalcValueCompensate();
    });

    //房屋价值补偿计算公式失去焦点
    $("input[name='calcValueCompensate']").eq(0).on("blur change", function () {
        settleAccountObj.changeCalcValueCompensate();
    });

    //参与被征收房屋补偿合计的金额框
    var sumCompensateNameArr = new Array();
    sumCompensateNameArr[0] = "valueCompensate";   //证载房屋价值补偿
    sumCompensateNameArr[1] = "decorationCompensate";  //装修折旧补偿
    sumCompensateNameArr[2] = "moveHouseFee";  //搬家费
    sumCompensateNameArr[3] = "interimFee";  //过渡费
    sumCompensateNameArr[4] = "moveWaterMeterFee";  //水表迁移费
    sumCompensateNameArr[5] = "moveAmmeterFee";   //电表迁移费
    sumCompensateNameArr[6] = "moveAirConditioningFee";  //空调移机费
    sumCompensateNameArr[7] = "hotWaterCompensate";  //热水器拆装费
    sumCompensateNameArr[8] = "gasFee";  //管道煤气拆装费
    sumCompensateNameArr[9] = "structureCompensate";   //构建物补偿
    sumCompensateNameArr[10] = "affiliatedOther"; //房屋附属设施补偿-其他
    sumCompensateNameArr[11] = "noCheckCompensate";   //未登记建筑房屋补偿
    sumCompensateNameArr[12] = "rmbCompensate";   //货币补偿补助
    sumCompensateNameArr[13] = "lifeCompensate";   //生活困难补助
    sumCompensateNameArr[14] = "changeCompensate";   //住改商补助
    sumCompensateNameArr[15] = "buildingAreaFee";  //建筑面积补助
    sumCompensateNameArr[16] = "suspendBusinessFee";  //停产停业损失补偿
    sumCompensateNameArr[17] = "noMoveCompensate";  //不可移动设备设施补偿
    sumCompensateNameArr[18] = "moveReward";  //搬迁奖励
    sumCompensateNameArr[19] = "rmbMoveReward";  //货币搬迁奖励
    sumCompensateNameArr[20] = "smallAreaReward";  //小面积住房搬迁奖励
    sumCompensateNameArr[21] = "otherRmb";  //其他

    for(var i=0;i<sumCompensateNameArr.length;i++){
        var nameText = sumCompensateNameArr[i];
        $("input[name='"+nameText+"']").eq(0).on("blur change", function () {
            settleAccountObj.fullSumCompensate();
        });
    }

    //旧房金额：被征收房屋补偿合计; 绑定失去焦点、变更事件
    $("input[name='sumCompensate']").eq(0).on("blur change", function () {
        settleAccountObj.fullPayReceive();
    });

    //新房金额 ；绑定失去焦点、变更事件
    $("input[name='houseMoney']").eq(0).on("blur change", function () {
        settleAccountObj.fullPayReceive();
    });


    //水表迁移计算绑定
    $("#water_meter_main,#water_meter_sub").on("change", function(){
        settleAccountObj.fullWaterMeter();
    });

    //电表迁移计算绑定
    $("#ammeter_main,#ammeter_sub").on("change", function () {
        settleAccountObj.fullAmmeter();
    });

    //空调移机费
    $("#air_conditioner_shutter,#air_conditioner_hang,#air_conditioner_cabinet").on("change", function () {
        settleAccountObj.fullAirConditioner();
    });

    //装修折旧公式，绑定失去焦点事件， 计算金额
    $("input[name='calcDecorationCompensate']").eq(0).on("blur change", function () {
        settleAccountObj.calcDecorationCompensate();
    });


    //停产停业损失下拉选择框
    $("select[name='sel_calcSuspendBusinessFee']").eq(0).change(function () {
        var proportion = $(this).val();
        if(proportion == 0) {
            $("input[name='calcSuspendBusinessFee']").eq(0).val("0").change();
        }else{
            //第一项，价值补偿*5%
            var valueCompensate = $("input[name='valueCompensate']").eq(0).val() || 0;
            //如果价值补偿为空，则进行提示
            if (valueCompensate <= 0) {
                var simulateFlag = $("#simulate");
                if(!simulateFlag) {
                    //非结算页面
                    alertMsg.warn("证载房屋价值补偿金额为空，无法进行停产停业损失计算");
                }else{
                    alert("证载房屋价值补偿金额为空，无法进行停产停业损失计算");
                }

                $(this).val(0);
                return;
            }
            var calcSuspendBusinessFee = valueCompensate + "*" + proportion;
            $("input[name='calcSuspendBusinessFee']").eq(0).val(calcSuspendBusinessFee).change();
        }
    });

    //搬迁奖励计算公式
    $("select[name='calcMoveReward']").eq(0).change(function () {
        var moveReward = $(this).val();
        $("input[name='moveReward']").eq(0).val(moveReward).change();
    });

    //过渡费公式，绑定失去焦点、变更事件，计算金额
    $("input[name='calcInterimFee']").eq(0).on("blur change", function () {
        settleAccountObj.runCalc("calcInterimFee", "interimFee");
    });

    //搬家费
    $("select[name='calcMoveHouseFee']").eq(0).change(function () {
        var moveHouseFee = $(this).val();
        $("input[name='moveHouseFee']").eq(0).val(moveHouseFee).change();
        //级联货币补偿补助、货币搬迁奖励，当被征收人选择产权调换时，这2项隐藏
        var text =  $("select[name='calcMoveHouseFee']").eq(0).find("option:selected").text();
        if(text == "货币补偿"){

            $("#rmbCompensate_td1 input[type='text']").css("display", "inline-block");
            $("#rmbCompensate_td2 input[type='text']").css("display", "inline-block");
            $("#rmbCompensate_td3 input[type='text']").css("display", "inline-block");
            $("input[name='calcRmbCompensateProportion']").eq(0).change();

            $("#rmbMoveReward_td1 input[type='text']").css("display", "inline-block");
            $("#rmbMoveReward_td2 input[type='text']").css("display", "inline-block");
            $("#rmbMoveReward_td3 input[type='text']").css("display", "inline-block");
            $("input[name='calcRmbMoveRewardProportion']").eq(0).change();
        }else{
            //产权调换、请选择
            $("#rmbCompensate_td1 input[type='text']").css("display", "none");
            $("#rmbCompensate_td2 input[type='text']").css("display", "none");
            $("#rmbCompensate_td3 input[type='text']").css("display", "none");
            $("input[name='calcRmbMoveReward']").eq(0).val("0*0*0").change();

            $("#rmbMoveReward_td1 input[type='text']").css("display", "none");
            $("#rmbMoveReward_td2 input[type='text']").css("display", "none");
            $("#rmbMoveReward_td3 input[type='text']").css("display", "none");
            $("input[name='calcRmbMoveReward']").eq(0).val("0*0*0").change();
        }
    });
    //热水器拆装费
    $("input[name='calcHotWaterCompensate']").eq(0).on("blur change", function () {
        settleAccountObj.runCalc("calcHotWaterCompensate", "hotWaterCompensate");
    });
    //管道煤气拆装费
    $("input[name='calcGasFee']").eq(0).on("blur change", function () {
        settleAccountObj.runCalc("calcGasFee", "gasFee");
    });
    //构建物补偿
    $("input[name='calcStructureCompensate']").eq(0).on("blur change", function () {
        settleAccountObj.runCalc("calcStructureCompensate", "structureCompensate");
    });
    //附属设施---其他
    $("input[name='calcAffiliatedOther']").eq(0).on("blur change", function () {
        settleAccountObj.runCalc("calcAffiliatedOther", "affiliatedOther");
    });
    //未登记建筑补偿
    $("input[name='calcNoCheckCompensate']").eq(0).on("blur change", function () {
        settleAccountObj.runCalc("calcNoCheckCompensate", "noCheckCompensate");
    });
    //货币补偿补助
    $("input[name='calcRmbCompensate']").eq(0).on("blur change", function () {
        settleAccountObj.runCalc("calcRmbCompensate", "rmbCompensate");
    });
    //生活困难补助计算公式
    $("input[name='calcLifeCompensate']").eq(0).on("blur change", function () {
        settleAccountObj.runCalc("calcLifeCompensate", "lifeCompensate");
    });
    //生活困难补助复选框
    $("#lifeCalcTd input[type='checkbox']").on("click", function () {
        var diseaseSubsidyFlag = $("input[name='diseaseSubsidy']").eq(0).prop("checked");
        var disabilitySubsidyFlag = $("input[name='disabilitySubsidy']").eq(0).prop("checked");
        var basicLivingSubsidyFlag = $("input[name='basicLivingSubsidy']").eq(0).prop("checked");
        var calcLifeCompensate = "";
        console.log("生活困难补助选择："+diseaseSubsidyFlag + "  " + disabilitySubsidyFlag + " " + basicLivingSubsidyFlag);
        if(diseaseSubsidyFlag) {
            calcLifeCompensate = $("input[name='diseaseSubsidy']").eq(0).val() || 0;
        }else{
            calcLifeCompensate = 0;
        }
        if(disabilitySubsidyFlag) {
            calcLifeCompensate = calcLifeCompensate + "+" + $("input[name='disabilitySubsidy']").eq(0).val() || 0;
        }else{
            calcLifeCompensate = calcLifeCompensate + "+" + 0;
        }
        if(basicLivingSubsidyFlag) {
            calcLifeCompensate = calcLifeCompensate + "+" + $("input[name='basicLivingSubsidy']").eq(0).val() || 0;
        }else{
            calcLifeCompensate =  calcLifeCompensate + "+" + 0;
        }

        $("input[name='calcLifeCompensate']").val(calcLifeCompensate).change();
    });
    //住改商补助
    $("input[name='calcChangeCompensate']").eq(0).on("blur change", function () {
        settleAccountObj.runCalc("calcChangeCompensate", "changeCompensate");
    });
    //建筑面积补助
    $("input[name='calcBuildingAreaFee']").eq(0).on("blur change", function () {
        settleAccountObj.runCalc("calcBuildingAreaFee", "buildingAreaFee");
    });
    //停产停业损失补偿
    $("input[name='calcSuspendBusinessFee']").eq(0).on("blur change", function () {
        settleAccountObj.runCalc("calcSuspendBusinessFee", "suspendBusinessFee");
    });
    //不可移动设备设施补偿
    $("input[name='calcNoMoveCompensate']").eq(0).on("blur change", function () {
        settleAccountObj.runCalc("calcNoMoveCompensate", "noMoveCompensate");
    });
    //货币搬迁奖励
    $("input[name='calcRmbMoveReward']").eq(0).on("blur change", function () {
        settleAccountObj.runCalc("calcRmbMoveReward", "rmbMoveReward");
    });
    //小面积住房搬迁奖励
    $("input[name='calcSmallAreaReward']").eq(0).on("blur change", function () {
        settleAccountObj.runCalc("calcSmallAreaReward", "smallAreaReward");
    });
    //其他
    $("input[name='calcOther']").eq(0).on("blur change", function () {
        settleAccountObj.runCalc("calcOther", "otherRmb");
    });

    //左上角补偿方式选择，级联搬迁补偿(搬家费)
    $("select[name='choose_type']").eq(0).on("change", function () {
        var value = $(this).val();
        $("select[name='calcMoveHouseFee']").eq(0).val(value).change();
    });

    //构建物补偿选择
    $("#sel_structure").on("change", function () {
        var structure = $(this).val();
        if (structure == 0) {
            $("#sel_stove").css("display", "none");
            $("input[name='calcStructureCompensateArea']").eq(0).css("display", "none");
            $("input[name='calcStructureCompensatePrice']").eq(0).css("display", "none");
        } else if (structure == 1) {
            $("#sel_stove").css("display", "inline-block").change();
            $("input[name='calcStructureCompensateArea']").eq(0).css("display", "none");
            $("input[name='calcStructureCompensatePrice']").eq(0).css("display", "none");
        } else if (structure == 2) {
            $("#sel_stove").css("display", "none");
            $("input[name='calcStructureCompensateArea']").eq(0).css("display", "inline-block").change();
            $("input[name='calcStructureCompensatePrice']").eq(0).css("display", "inline-block").change();
        }
    });

    //无烟灶台选择数量
    $("#sel_stove").on("change", function () {
        var v = $(this).val() || 0;
        $("input[name='calcStructureCompensate']").eq(0).val(v).change();
    });

    //构建物：暗楼面积
    $("input[name='calcStructureCompensateArea']").eq(0).on("blur change", function () {
        settleAccountObj.calcDarkBuilding();
    });

    //构建物：暗楼单价
    $("input[name='calcStructureCompensatePrice']").eq(0).on("blur change", function () {
        settleAccountObj.calcDarkBuilding();
    });

    //热水器拆装费
    $("#water_heater_type").on("change", function () {
        var water_heater_type = $(this).val();
        if (water_heater_type == 0) {
            $("#sel_water_heater").css("display", "none");
            $("input[name='calcHotWaterCompensateMoney']").eq(0).css("display", "none");
            $("input[name='calcHotWaterCompensateConvert']").eq(0).css("display", "none");
        } else if (water_heater_type == 1) {
            $("#sel_water_heater").css("display", "inline-block").change();
            $("input[name='calcHotWaterCompensateMoney']").eq(0).css("display", "none");
            $("input[name='calcHotWaterCompensateConvert']").eq(0).css("display", "none");
        } else if (water_heater_type == 2) {
            $("#sel_water_heater").css("display", "none");
            $("input[name='calcHotWaterCompensateMoney']").eq(0).css("display", "inline-block").change();
            $("input[name='calcHotWaterCompensateConvert']").eq(0).css("display", "inline-block").change();
        }
    });

    //电热水器选择数量
    $("#sel_water_heater").on("change", function () {
        var v = $(this).val() || 0;
        $("input[name='calcHotWaterCompensate']").eq(0).val(v).change();
    });

    //太阳能热水器：金额
    $("input[name='calcHotWaterCompensateMoney']").eq(0).on("blur change", function () {
        settleAccountObj.calcHotWater();
    });

    //太阳能热水器：折算比例
    $("input[name='calcHotWaterCompensateConvert']").eq(0).on("blur change", function () {
        settleAccountObj.calcHotWater();
    });


});


var settleAccountObj = {

    //改变房屋单价、建筑面积时，填充房屋价值补偿计算公式
    fullCalcValueCompensate:function(){

        var checkInArea = $("input[name='calcValueCompensateArea']").eq(0).val() || 0;
        var assessPrice = $("input[name='calcValueCompensatePrice']").eq(0).val() || 0;
        var proportion = $("input[name='calcValueCompensateProportion']").eq(0).val() || 0;
        var calcValueCompensate = checkInArea + "*" + assessPrice + "*" + proportion;
        //覆盖公式中的单价
        $("input[name='calcValueCompensate']").eq(0).val(calcValueCompensate).change();
    },


    //改变房屋价值补偿计算公式
    changeCalcValueCompensate:function () {
        var calcValueCompensate = $("input[name='calcValueCompensate']").eq(0).val() || 0;
        var valueCompensate = Math.round(eval(calcValueCompensate));
        $("input[name='valueCompensate']").eq(0).val(valueCompensate).change();
    },

    //填充被征收房屋补偿合计
    fullSumCompensate:function(){
        //证载房屋价值补偿
        var valueCompensate = $("input[name='valueCompensate']").eq(0).val() || 0;
        var valueCompensate_num = new Number(valueCompensate);
        //装修折旧补偿
        var decorationCompensate = $("input[name='decorationCompensate']").eq(0).val() || 0;
        var decorationCompensate_num = new Number(decorationCompensate);
        //搬家费
        var moveHouseFee = $("input[name='moveHouseFee']").eq(0).val() || 0;
        var moveHouseFee_num = new Number(moveHouseFee);
        //过渡费
        var interimFee = $("input[name='interimFee']").eq(0).val() || 0;
        var interimFee_num = new Number(interimFee);
        //水表迁移费
        var moveWaterMeterFee = $("input[name='moveWaterMeterFee']").eq(0).val() || 0;
        var moveWaterMeterFee_num = new Number(moveWaterMeterFee);
        //电表迁移费
        var moveAmmeterFee = $("input[name='moveAmmeterFee']").eq(0).val() || 0;
        var moveAmmeterFee_num = new Number(moveAmmeterFee);
        //空调移机费
        var moveAirConditioningFee = $("input[name='moveAirConditioningFee']").eq(0).val() || 0;
        var moveAirConditioningFee_num = new Number(moveAirConditioningFee);
        //热水器拆装费
        var hotWaterCompensate = $("input[name='hotWaterCompensate']").eq(0).val() || 0;
        var hotWaterCompensate_num = new Number(hotWaterCompensate);
        //管道煤气拆装费
        var gasFee = $("input[name='gasFee']").eq(0).val() || 0;
        var gasFee_num = new Number(gasFee);
        //构建物补偿
        var structureCompensate = $("input[name='structureCompensate']").eq(0).val() || 0;
        var structureCompensate_num = new Number(structureCompensate);
        //房屋附属设施补偿-其他
        var affiliatedOther = $("input[name='affiliatedOther']").eq(0).val() || 0;
        var affiliatedOther_num = new Number(affiliatedOther);
        //未登记建筑房屋补偿
        var noCheckCompensate = $("input[name='noCheckCompensate']").eq(0).val() || 0;
        var noCheckCompensate_num = new Number(noCheckCompensate);
        //货币补偿补助
        var rmbCompensate = $("input[name='rmbCompensate']").eq(0).val() || 0;
        var rmbCompensate_num = new Number(rmbCompensate);
        //生活困难补助
        var lifeCompensate = $("input[name='lifeCompensate']").eq(0).val() || 0;
        var lifeCompensate_num = new Number(lifeCompensate);
        //住改商补助
        var changeCompensate = $("input[name='changeCompensate']").eq(0).val() || 0;
        var changeCompensate_num = new Number(changeCompensate);
        //建筑面积补助
        var buildingAreaFee = $("input[name='buildingAreaFee']").eq(0).val() || 0;
        var buildingAreaFee_num = new Number(buildingAreaFee);
        //停产停业损失补偿
        var suspendBusinessFee = $("input[name='suspendBusinessFee']").eq(0).val() || 0;
        var suspendBusinessFee_num = new Number(suspendBusinessFee);
        //不可移动设备设施补偿
        var noMoveCompensate = $("input[name='noMoveCompensate']").eq(0).val() || 0;
        var noMoveCompensate_num = new Number(noMoveCompensate);
        //搬迁奖励
        var moveReward = $("input[name='moveReward']").eq(0).val() || 0;
        var moveReward_num = new Number(moveReward);
        //货币搬迁奖励
        var rmbMoveReward = $("input[name='rmbMoveReward']").eq(0).val() || 0;
        var rmbMoveReward_num = new Number(rmbMoveReward);
        //小面积住房搬迁奖励
        var smallAreaReward = $("input[name='smallAreaReward']").eq(0).val() || 0;
        var smallAreaReward_num = new Number(smallAreaReward);
        //其他
        var otherRmb = $("input[name='otherRmb']").eq(0).val() || 0;
        var otherRmb_num = new Number(otherRmb);
        //合计
        var sumCompensate = valueCompensate_num + decorationCompensate_num + moveHouseFee_num + interimFee_num +
            moveWaterMeterFee_num + moveAmmeterFee_num + moveAirConditioningFee_num + hotWaterCompensate_num +
            gasFee_num + structureCompensate_num + affiliatedOther_num + noCheckCompensate_num + rmbCompensate_num
            + lifeCompensate_num + changeCompensate_num + buildingAreaFee_num + suspendBusinessFee_num + noMoveCompensate_num
            + moveReward_num + rmbMoveReward_num + smallAreaReward_num + otherRmb_num;
        $("input[name='sumCompensate']").eq(0).val(sumCompensate);
    },

    //填充应收、应付款
    fullPayReceive:function () {
        //旧房金额
        var sumCompensate = $("input[name='sumCompensate']").eq(0).val() || 0;
        sumCompensate = new Number(sumCompensate);
        //新房金额
        var houseMoney =  $("input[name='houseMoney']").eq(0).val() || 0;
        houseMoney = new Number(houseMoney);
        if(sumCompensate > houseMoney)  {
            //应付合计填写差
            var payTotal = sumCompensate - houseMoney;
            $("input[name='payTotal']").eq(0).val(payTotal);
            var payTotalChinese = Araia_To_Chinese(payTotal);
            $("input[name='payMoney']").eq(0).val(payTotalChinese);

            //应收合计填空
            $("input[name='receiveTotal']").eq(0).val("");
            $("input[name='receiveMoney']").eq(0).val("");
        }else if(sumCompensate < houseMoney){
            //应收合计填写差
            var receiveTotal = houseMoney - sumCompensate;
            $("input[name='receiveTotal']").eq(0).val(receiveTotal);
            var receiveTotalChinese = Araia_To_Chinese(receiveTotal);
            $("input[name='receiveMoney']").eq(0).val(receiveTotalChinese);

            //应付合计填写空
            $("input[name='payTotal']").eq(0).val("");
            $("input[name='payMoney']").eq(0).val("");
        }

    },


    //计算水表
    fullWaterMeter:function(){
        //获取主表
        var waterMeterMain = $("#water_meter_main").val() || 0;
        var waterMeterMainFee = eval(waterMeterMain);
        //获取副表
        var waterMeterSub = $("#water_meter_sub").val() || 0;
        var waterMeterSubFee = eval(waterMeterSub);
        var moveWaterMeterFee = waterMeterMainFee + waterMeterSubFee;
        //水表迁移费
        $("input[name='moveWaterMeterFee']").eq(0).val(moveWaterMeterFee).change();
        //水表迁移费计算公式
        $("input[name='calcMoveWaterMeterFee']").eq(0).val(waterMeterMain + "+" + waterMeterSub);
    },
    //计算电表
    fullAmmeter:function(){
        //获取主表
        var ammeterMain = $("#ammeter_main").val() || 0;
        var ammeterMainFee = eval(ammeterMain);
        //获取副表
        var ammeterSub = $("#ammeter_sub").val() || 0;
        var ammeterSubFee = eval(ammeterSub);
        var moveAmmeterFee = ammeterMainFee + ammeterSubFee;
        //电表迁移费
        $("input[name='moveAmmeterFee']").eq(0).val(moveAmmeterFee).change();
        //电表迁移费计算公式
        $("input[name='calcMoveAmmeterFee']").eq(0).val(ammeterMain + "+" + ammeterMain);
    },
    //计算空调移机费
    fullAirConditioner: function () {
        //获取窗机
        var airConditionerShutter = $("#air_conditioner_cabinet").val() || 0;
        var airConditionerShutterFee = eval(airConditionerShutter);

        //获取挂机
        var airConditionerHang = $("#air_conditioner_hang").val() || 0;
        var airConditionerHangFee = eval(airConditionerHang);

        //获取柜机
        var airConditionerCabinet = $("#air_conditioner_shutter").val() || 0;
        var airConditionerCabinetFee = eval(airConditionerCabinet);

        var moveAirConditioningFee = airConditionerCabinetFee + airConditionerShutterFee + airConditionerHangFee;

        $("input[name='moveAirConditioningFee']").eq(0).val(moveAirConditioningFee).change();
        $("input[name='calcMoveAirConditioningFee']").eq(0).val(airConditionerShutter + "+" + airConditionerHang + "+" + airConditionerCabinet);
    },
    //运行装修补偿的计算公式，得到折旧费用
    calcDecorationCompensate: function () {
        var calcDecorationCompensate = $("input[name='calcDecorationCompensate']").eq(0).val() || 0;
        var decorationCompensate = Math.round(eval(calcDecorationCompensate));
        $("input[name='decorationCompensate']").eq(0).val(decorationCompensate).change();
    },
    /**
     * 普通框，执行计算公式
     * @param calcObjName  计算公式框名称
     * @param moneyObjName  金额框名称
     */
    runCalc:function (calcObjName, moneyObjName) {
        var calcText = $("input[name='"+calcObjName+"']").eq(0).val() || 0;
        var money = Math.round(eval(calcText));
        $("input[name='"+moneyObjName+"']").eq(0).val(money).change();
    },
    //折旧补偿，利用2小框数字，得到计算公式
    fullCalcDecoration:function () {
        var calcDecorationCompensateArea = $("input[name='calcDecorationCompensateArea']").eq(0).val() || 0;
        var calcDecorationCompensatePrice = $("input[name='calcDecorationCompensatePrice']").eq(0).val() || 0;
        var calcDecorationCompensate = calcDecorationCompensateArea + "*" + calcDecorationCompensatePrice;
        $("input[name='calcDecorationCompensate']").eq(0).val(calcDecorationCompensate).change();

    },
    //临时安置补偿，利用3小框，得到计算公式
    fullCalcInterimFee: function () {
        var calcInterimFeeArea = $("input[name='calcInterimFeeArea']").eq(0).val() || 0;
        var calcInterimFeePrice = $("input[name='calcInterimFeePrice']").eq(0).val() || 0;
        var calcInterimFeeMonth = $("input[name='calcInterimFeeMonth']").eq(0).val() || 0;
        var calcInterimFee = calcInterimFeeArea + "*" + calcInterimFeePrice + "*" + calcInterimFeeMonth;
        $("input[name='calcInterimFee']").eq(0).val(calcInterimFee).change();
    },
    //未登记面积补偿，利用3小框，得到计算公式
    fullCalcNoCheckCompensate:function () {
        var calcNoCheckCompensateArea = $("input[name='calcNoCheckCompensateArea']").eq(0).val() || 0;
        var calcNoCheckCompensatePrice = $("input[name='calcNoCheckCompensatePrice']").eq(0).val() || 0;
        var calcNoCheckCompensateProportion = $("input[name='calcNoCheckCompensateProportion']").eq(0).val() || 0;
        var calcNoCheckCompensate = calcNoCheckCompensateArea + "*" + calcNoCheckCompensatePrice + "*" + calcNoCheckCompensateProportion;
        $("input[name='calcNoCheckCompensate']").eq(0).val(calcNoCheckCompensate).change();
    },
    //货币补偿补助，利用3小框，得到计算公式
    fullCalcRmbCompensate:function () {
        var calcRmbCompensateArea = $("input[name='calcRmbCompensateArea']").eq(0).val() || 0;
        var calcRmbCompensatePrice = $("input[name='calcRmbCompensatePrice']").eq(0).val() || 0;
        var calcRmbCompensateProportion = $("input[name='calcRmbCompensateProportion']").eq(0).val() || 0;
        var calcRmbCompensate = calcRmbCompensateArea + "*" + calcRmbCompensatePrice + "*" + calcRmbCompensateProportion;
        $("input[name='calcRmbCompensate']").eq(0).val(calcRmbCompensate).change();
    },
    //货币搬迁奖励，利用3小框，得到计算公式
    fullCalcRmbMoveReward: function () {
        var calcRmbMoveRewardArea = $("input[name='calcRmbMoveRewardArea']").eq(0).val() || 0;
        var calcRmbMoveRewardPrice = $("input[name='calcRmbMoveRewardPrice']").eq(0).val() || 0;
        var calcRmbMoveRewardProportion = $("input[name='calcRmbMoveRewardProportion']").eq(0).val() || 0;
        var calcRmbMoveReward = calcRmbMoveRewardArea + "*" + calcRmbMoveRewardPrice + "*" + calcRmbMoveRewardProportion;
        $("input[name='calcRmbMoveReward']").eq(0).val(calcRmbMoveReward).change();
    },

    //构建物：暗楼 补偿
    calcDarkBuilding:function () {
        var calcStructureCompensateArea = $("input[name='calcStructureCompensateArea']").eq(0).val() || 0;
        var calcStructureCompensatePrice = $("input[name='calcStructureCompensatePrice']").eq(0).val() || 0;
        var calcStructureCompensate = calcStructureCompensateArea + "*" + calcStructureCompensatePrice;
        $("input[name='calcStructureCompensate']").eq(0).val(calcStructureCompensate).change();
    },

    //太阳能热水器计算
    calcHotWater:function () {
        var calcHotWaterCompensateMoney = $("input[name='calcHotWaterCompensateMoney']").eq(0).val() || 0;
        var calcHotWaterCompensateConvert = $("input[name='calcHotWaterCompensateConvert']").eq(0).val() || 0;
        var calcHotWaterCompensate = calcHotWaterCompensateMoney + "*" + calcHotWaterCompensateConvert;
        $("input[name='calcHotWaterCompensate']").eq(0).val(calcHotWaterCompensate).change();
    }

};