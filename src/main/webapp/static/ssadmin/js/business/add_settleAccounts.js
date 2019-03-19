$(document).ready(function(){
    //房屋评估单价失去焦点
    $("input[name='assessPrice']").eq(0).blur(function () {
        settleAccountObj.fullCalcValueCompensate();
        settleAccountObj.fullCalcMoveReward();
    });

    //建筑面积失去焦点
    $("input[name='checkInArea']").eq(0).blur(function () {
        //计算房屋价值补偿
        settleAccountObj.fullCalcValueCompensate();
        //计算搬迁奖励
        settleAccountObj.fullCalcMoveReward();
        //填充装修折旧的面积
        settleAccountObj.fullCalcObjArea("calcDecorationCompensate");
        //填充临时安置补偿过渡费的面积
        settleAccountObj.fullCalcObjArea("calcInterimFee");
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


    //搬迁奖励计算公式
    $("select[name='calcMoveReward']").eq(0).change(function () {
        var moveReward = $(this).val();
        $("input[name='moveReward']").eq(0).val(moveReward).change();
    });

    //过渡费公式，绑定失去焦点、变更事件，计算金额
    $("input[name='calcInterimFee']").eq(0).on("blur change", function () {
        settleAccountObj.calcInterimFee();
    });

    //搬家费
    $("select[name='calcMoveHouseFee']").eq(0).change(function () {
        var moveHouseFee = $(this).val();
        $("input[name='moveHouseFee']").eq(0).val(moveHouseFee).change();
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


});


var settleAccountObj = {

    //改变房屋单价、建筑面积时，填充房屋价值补偿
    fullCalcValueCompensate:function(){
        //房屋评估单价
        var assessPrice = $("input[name='assessPrice']").eq(0).val() || 0;
        console.log("单价:" + assessPrice);
        //建筑面积
        var checkInArea = $("input[name='checkInArea']").eq(0).val() || 0;
        console.log("面积:" + checkInArea);
        if(!assessPrice){
            return;
        }
        if(!checkInArea){
            return;
        }
        var tmpCalcValue = checkInArea + "*" + assessPrice;

        //房屋价值补偿
        var calcValueCompensate = $("input[name='calcValueCompensate']").eq(0).val();
        //是初次填写面积、单价
        if(!calcValueCompensate) {
            $("input[name='calcValueCompensate']").eq(0).val(tmpCalcValue).change();
            return;
        }

        //如果刚好是面积*单价
        var vArr = calcValueCompensate.split("*");
        //刚好是2个数字相乘，则也是直接覆盖，填充面积*单价
        if(vArr.length == 2) {
            $("input[name='calcValueCompensate']").eq(0).val(tmpCalcValue).change();
            return;
        }

        //是2个以上数字的相乘，则替换第一个的面积，即面积有更新
        var area_multiply_index = calcValueCompensate.indexOf("*");
        if( area_multiply_index == -1) {
            return;
        }
        var waitPriceCalc = calcValueCompensate.substring(area_multiply_index+1, calcValueCompensate.length);
        tmpCalcValue = checkInArea + calcValueCompensate.substring(area_multiply_index, calcValueCompensate.length);
        //覆盖公式中的面积
        $("input[name='calcValueCompensate']").eq(0).val(tmpCalcValue).change();

        //替换第二个的单价，即单价有更新
        var price_multiply_index = waitPriceCalc.indexOf("*");
        if( waitPriceCalc == -1) {
            return;
        }
        tmpCalcValue =  checkInArea + "*" + assessPrice +   waitPriceCalc.substring(price_multiply_index, waitPriceCalc.length);
        //覆盖公式中的单价
        $("input[name='calcValueCompensate']").eq(0).val(tmpCalcValue).change();
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
    /**
     * 填充计算对象中的面积：装修折旧补偿、临时安置补偿过渡费的面积
     * 这2个对象都是面积*某系数=金额
     * @param calcObjName  计算公式框名称
     * @param moneyName   金额框名称
     */
    fullCalcObjArea:function (calcObjName) {
        console.log("填充公式中的面积: calcObjName=" + calcObjName);
        //建筑面积
        var checkInArea = $("input[name='checkInArea']").eq(0).val();
        //获取计算公式
        var calcDecorationCompensate = $("input[name='"+calcObjName+"']").eq(0).val();
        if(!calcDecorationCompensate) {
            console.log("计算公式没有填写");
            $("input[name='"+calcObjName+"']").eq(0).val(checkInArea).change();
            return;
        }

        //已有计算公式，则替换第一个的面积，即面积有更新
        var multiply_index = calcDecorationCompensate.indexOf("*");
        console.log("装修折旧补偿公式*位置:" + multiply_index);
        if( multiply_index == -1) {
            $("input[name='"+calcObjName+"']").eq(0).val(checkInArea).change();
            return;
        }
        calcDecorationCompensate = checkInArea + calcDecorationCompensate.substring(multiply_index, calcDecorationCompensate.length);
        //覆盖公式
        $("input[name='"+calcObjName+"']").eq(0).val(calcDecorationCompensate).change();
    },
    //运行装修补偿的计算公式，得到折旧费用
    calcDecorationCompensate: function () {
        console.log("yufsafsdf");
        var calcDecorationCompensate = $("input[name='calcDecorationCompensate']").eq(0).val() || 0;
        console.log("装修折旧计算公式:" + calcDecorationCompensate);
        var decorationCompensate = Math.round(eval(calcDecorationCompensate));
        $("input[name='decorationCompensate']").eq(0).val(decorationCompensate).change();
    },
    //运行临时安置过渡费的计算公式，得到过渡费
    calcInterimFee: function () {
        var calcInterimFee = $("input[name='calcInterimFee']").eq(0).val() || 0;
        var interimFee = Math.round(eval(calcInterimFee));
        $("input[name='interimFee']").eq(0).val(interimFee).change();
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
    }

};