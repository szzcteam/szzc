

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

});

