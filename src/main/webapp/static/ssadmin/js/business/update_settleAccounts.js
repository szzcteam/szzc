

$(document).ready(function () {

    //水表
    var calcMoveWaterMeterFee = $("input[name='calcMoveWaterMeterFee']").val() || 0;
    //水表有值，格式1*500+0*100  数量*主表价格+数量+副表价格
    var water_add_index = calcMoveWaterMeterFee.indexOf("+");
    if (water_add_index != -1) {
        var waterArr = calcMoveWaterMeterFee.split("+");
        $("#water_meter_main").val(waterArr[0]);
        $("#water_meter_sub").val(waterArr[1]);
    }


    //电表
    var calcMoveAmmeterFee = $("input[name='calcMoveAmmeterFee']").val() || 0;
    var ammeter_add_index = calcMoveAmmeterFee.indexOf("+");
    if (ammeter_add_index != -1) {
        var ammeterArr = calcMoveAmmeterFee.split("+");
        $("#ammeter_main").val(ammeterArr[0]);
        $("#ammeter_sub").val(ammeterArr[1]);
    }

    //空调
    var calcMoveAirConditioningFee = $("input[name='calcMoveAirConditioningFee']").val() || 0;
    var air_add_index = calcMoveAirConditioningFee.indexOf("+");
    if (air_add_index != -1) {
        var airArr = calcMoveAirConditioningFee.split("+");
        $("#air_conditioner_shutter").val(airArr[0]);
        $("#air_conditioner_hang").val(airArr[1]);
        $("#air_conditioner_cabinet").val(airArr[2]);
    }


    //去掉所有旧房金额框的.00， 因后台bigdecimal引起
    $("input[type='text']").each(function (i, obj) {
        var obj_value = $(obj).val();
        //判断是否是数字，仅数字做转换处理，非数字直接忽略
        if(isNaN(obj_value)){
            return;
        }
        if (obj_value && obj_value.indexOf(".00") != -1) {
            obj_value = Math.round(obj_value);
            $(obj).val(obj_value);
        }
    });

    //价值补偿，利用计算公式，填充3小框
    var calcValueCompensate = $("input[name='calcValueCompensate']").eq(0).val();
    if(calcValueCompensate) {
        var arr = calcValueCompensate.split("*");
        $("input[name='calcValueCompensateArea']").val(arr[0]);
        $("input[name='calcValueCompensatePrice']").val(arr[1]);
        $("input[name='calcValueCompensateProportion']").val(arr[2]);
    }

    //装修折旧补偿，利用计算公式，填充2小框
    var calcDecorationCompensate = $("input[name='calcDecorationCompensate']").eq(0).val();
    if(calcDecorationCompensate) {
        var arr = calcDecorationCompensate.split("*")
        $("input[name='calcDecorationCompensateArea']").val(arr[0]);
        $("input[name='calcDecorationCompensatePrice']").val(arr[1]);
    }

    //停产停业补偿，利用计算公式，选中下拉框
    var calcSuspendBusinessFee = $("input[name='calcSuspendBusinessFee']").eq(0).val();
    if(calcSuspendBusinessFee) {
        var arr = calcSuspendBusinessFee.split("*")
        $("select[name='sel_calcSuspendBusinessFee']").eq(0).val(arr[1]);
    }

    //生活困难补助复选框
    var calcLifeCompensate = $("input[name='calcLifeCompensate']").eq(0).val();
    var lifeCompensateArr = calcLifeCompensate.split("+");
    if (new Number(lifeCompensateArr[0]) > 0) {
        $("input[name='diseaseSubsidy']").eq(0).prop("checked", true);
    }
    if (new Number(lifeCompensateArr[1]) > 0) {
        $("input[name='disabilitySubsidy']").eq(0).prop("checked", true);
    }
    if (new Number(lifeCompensateArr[2]) > 0) {
        $("input[name='basicLivingSubsidy']").eq(0).prop("checked", true);
    }

    //临时安置过渡费，利用计算公式，填充3小框
    var calcInterimFee = $("input[name='calcInterimFee']").eq(0).val();
    if(calcInterimFee) {
        var arr = calcInterimFee.split("*");
        $("input[name='calcInterimFeeArea']").val(arr[0]);
        $("input[name='calcInterimFeePrice']").val(arr[1]);
        $("input[name='calcInterimFeeMonth']").val(arr[2]);
    }

    //未登记面积补偿，利用计算公式，填充3小框
    var calcNoCheckCompensate = $("input[name='calcNoCheckCompensate']").eq(0).val();
    if(calcNoCheckCompensate) {
        var arr = calcNoCheckCompensate.split("*");
        $("input[name='calcNoCheckCompensateArea']").val(arr[0]);
        $("input[name='calcNoCheckCompensatePrice']").val(arr[1]);
        $("input[name='calcNoCheckCompensateProportion']").val(arr[2]);
    }

    //货币补偿补助，利用计算公式，填充3小框
    var calcRmbCompensate = $("input[name='calcRmbCompensate']").eq(0).val();
    if (calcRmbCompensate) {
        var arr = calcRmbCompensate.split("*");
        $("input[name='calcRmbCompensateArea']").val(arr[0]);
        $("input[name='calcRmbCompensatePrice']").val(arr[1]);
        $("input[name='calcRmbCompensateProportion']").val(arr[2]);
    }

    //货币搬迁奖励，利用计算公式，填充3小框
    var calcRmbMoveReward = $("input[name='calcRmbMoveReward']").eq(0).val();
    if(calcRmbMoveReward) {
        var arr = calcRmbMoveReward.split("*");
        $("input[name='calcRmbMoveRewardArea']").val(arr[0]);
        $("input[name='calcRmbMoveRewardPrice']").val(arr[1]);
        $("input[name='calcRmbMoveRewardProportion']").val(arr[2]);
    }

    //搬家费，根据搬家类型选择让货币补偿补助、货币搬迁奖励是否显示
    $("select[name='calcMoveHouseFee']").eq(0).change();

    //构建物下拉框选择：根据计算公式选择
    var calcStructureCompensate = $("input[name='calcStructureCompensate']").eq(0).val() || 0;
    if(calcStructureCompensate != null && calcStructureCompensate != "" && calcStructureCompensate != 0 && calcStructureCompensate.indexOf("0*") == -1) {
        var stove_price = $("#sel_stove").attr("price");
        var stove_flag = calcStructureCompensate.indexOf(stove_price);
        console.log("构建物判断:" +stove_flag + "公式:" + calcStructureCompensate);
        //灶台
        if(stove_flag != -1) {
            $("#sel_stove").find("option[value='"+calcStructureCompensate+"']").attr("selected",true);
            $("#sel_structure").val(1).change();
        }else{
            //暗楼
            var arr = calcStructureCompensate.split("*");
            $("input[name='calcStructureCompensateArea']").eq(0).val(arr[0]);
            $("input[name='calcStructureCompensatePrice']").eq(0).val(arr[1]);
            $("#sel_structure").val(2).change();
        }
    }


});

