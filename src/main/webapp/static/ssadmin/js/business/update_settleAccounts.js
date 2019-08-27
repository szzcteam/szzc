

$(document).ready(function () {
    console.log("页面加载完成");

    //水表
    var calcMoveWaterMeterFee = $("input[name='calcMoveWaterMeterFee']").eq(0).val() || "0";
    //水表有值，格式1*500+0*100  数量*主表价格+数量+副表价格
    var water_add_index = calcMoveWaterMeterFee.indexOf("+");
    if (water_add_index != -1) {
        var waterArr = calcMoveWaterMeterFee.split("+");
        $("#water_meter_main").eq(0).val(waterArr[0]);
        $("#water_meter_sub").val(waterArr[1]);
    }
    console.log("水表填充完成");

    //电表
    var calcMoveAmmeterFee = $("input[name='calcMoveAmmeterFee']").eq(0).val() || "0";
    var ammeter_add_index = calcMoveAmmeterFee.indexOf("+");
    if (ammeter_add_index != -1) {
        var ammeterArr = calcMoveAmmeterFee.split("+");
        $("#ammeter_main").val(ammeterArr[0]);
        $("#ammeter_sub").val(ammeterArr[1]);
        $("#ammeter_sa").val(ammeterArr[2]);
        $("#ammeter_time").val(ammeterArr[3]);
    }

    //空调
    var calcMoveAirConditioningFee = $("input[name='calcMoveAirConditioningFee']").eq(0).val() || "0";
    var air_add_index = calcMoveAirConditioningFee.indexOf("+");
    if (air_add_index != -1) {
        var airArr = calcMoveAirConditioningFee.split("+");
        $("#air_conditioner_shutter").val(airArr[0]);
        $("#air_conditioner_hang").val(airArr[1]);
        $("#air_conditioner_cabinet").val(airArr[2]);
    }

    console.log("电表、空调填充完成");
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

    //房屋价值补偿-有证，利用计算公式，填充框
    var calcValueCompensate = $("input[name='calcValueCompensate']").eq(0).val();
    if(calcValueCompensate) {
        var arr = calcValueCompensate.split("*");
        $("input[name='calcValueCompensateArea']").val(arr[0]);
        $("input[name='calcValueCompensatePrice']").val(arr[1]);
        // $("input[name='calcValueCompensateProportion']").val(arr[2]);
    }

    //房屋价值补偿-未登记，利用计算公式，填充框
    var calcNoRegisterLegal = $("input[name='calcNoRegisterLegal']").eq(0).val();
    if(calcNoRegisterLegal) {
        var arr = calcNoRegisterLegal.split("*");
        $("input[name='calcNoRegisterLegalArea']").val(arr[0]);
        $("input[name='calcNoRegisterLegalPrice']").val(arr[1]);
    }

    //房屋价值补偿-历史遗留，利用计算公式，填充框
    var calcHistoryLegacy = $("input[name='calcHistoryLegacy']").eq(0).val();
    if(calcHistoryLegacy) {
        var arr = calcHistoryLegacy.split("*");
        $("input[name='calcHistoryLegacyArea']").val(arr[0]);
        $("input[name='calcHistoryLegacyPrice']").val(arr[1]);
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
    if(calcSuspendBusinessFee != 0) {
        $("select[name='sel_calcSuspendBusinessFee']").get(0).selectedIndex = 1;
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
    if (new Number(lifeCompensateArr[3]) > 0) {
        $("input[name='martyr']").eq(0).prop("checked", true);
    }
    if (new Number(lifeCompensateArr[4]) > 0) {
        $("input[name='noChild']").eq(0).prop("checked", true);
    }

    //临时安置过渡费，利用计算公式，填充3小框
    var calcInterimFee = $("input[name='calcInterimFee']").eq(0).val();
    if(calcInterimFee) {
        var arr = "";
        //如果有+号，就是2个公式
        var firstAddIndex = calcInterimFee.indexOf("+");
        if (firstAddIndex != -1) {
            arr = calcInterimFee.substring(0, firstAddIndex).split("*");
            var two_calc = calcInterimFee.substring(firstAddIndex + 1, calcInterimFee.length);
            console.log("临时安置过渡费分割：" + two_calc);
            $("input[name='calcInterimFeeOther']").val(two_calc);
        } else {
            arr = calcInterimFee.split("*");
        }
        $("input[name='calcInterimFeeArea']").val(arr[0]);
        $("input[name='calcInterimFeePrice']").val(arr[1]);
        $("input[name='calcInterimFeeMonth']").val(arr[2]);
    }

    //保底补偿填充
    var calcGuarantee =  $("input[name='calcGuarantee']").eq(0).val() || "0";
    if(calcGuarantee != "0") {
        $("select[name='sel_guarantee']").get(0).selectedIndex = 1;
    }


    //未登记面积补偿，利用计算公式，填充3小框
    var calcNoCheckCompensate = $("input[name='calcNoCheckCompensate']").eq(0).val();
    if(calcNoCheckCompensate) {
        var arr = calcNoCheckCompensate.split("*");
        $("input[name='calcNoCheckCompensateArea']").val(arr[0]);
        $("input[name='calcNoCheckCompensatePrice']").val(arr[1]);
        $("input[name='calcNoCheckCompensateProportion']").val(arr[2]);
    }

   /* //货币补偿补助，利用计算公式，填充3小框
    var calcRmbCompensate = $("input[name='calcRmbCompensate']").eq(0).val();
    if (calcRmbCompensate) {
        var arr = calcRmbCompensate.split("*");
        $("input[name='calcRmbCompensateArea']").val(arr[0]);
        $("input[name='calcRmbCompensatePrice']").val(arr[1]);
        $("input[name='calcRmbCompensateProportion']").val(arr[2]);
    }*/

    //搬家费
    var calcMoveHouseFee = $("input[name='calcMoveHouseFee']").val();
    //如果是住宅的，则刚好可以选中
    $("select[name='sel_calcMoveHouseFee']").find("option[value='" + calcMoveHouseFee + "']").attr("selected", true);
    var moveHouseRmb = $("select[name='sel_calcMoveHouseFee']").val();
    if(moveHouseRmb == ""){
        //是非住宅的
        if(calcMoveHouseFee){
            $("select[name='sel_calcMoveHouseFee']").val(0);
        }
    }

   /* //货币搬迁奖励，利用计算公式，填充3小框
    var calcRmbMoveReward = $("input[name='calcRmbMoveReward']").eq(0).val();
    if(calcRmbMoveReward) {
        var arr = calcRmbMoveReward.split("*");
        $("input[name='calcRmbMoveRewardArea']").val(arr[0]);
        $("input[name='calcRmbMoveRewardPrice']").val(arr[1]);
        $("input[name='calcRmbMoveRewardProportion']").val(arr[2]);
    }*/


    //构建物下拉框选择：根据计算公式选择
    var calcStructureCompensate = $("input[name='calcStructureCompensate']").eq(0).val() || 0;
    if(calcStructureCompensate != null && calcStructureCompensate != "" && calcStructureCompensate != 0) {
        var overArr = calcStructureCompensate.split("+");

        //灶台
        $("#sel_stove").find("option[value='" + overArr[0] + "']").attr("selected", true);

        //暗楼
        var arr = overArr[1].split("*");
        $("input[name='calcStructureCompensateArea']").eq(0).val(arr[0]);
        $("input[name='calcStructureCompensatePrice']").eq(0).val(arr[1]);

    }


    //热水器拆装费
    var calcHotWaterCompensate = $("input[name='calcHotWaterCompensate']").eq(0).val() || 0;
    if(calcHotWaterCompensate != null && calcHotWaterCompensate != "" && calcHotWaterCompensate != 0) {
        var hot_water_index = calcHotWaterCompensate.indexOf("+");
        if (hot_water_index != -1) {
            //是2个公式，分别处理
            //电热水器
            var hot_value = calcHotWaterCompensate.substring(0, hot_water_index);
            $("select[name='sel_water_heater']").find("option[value='" + hot_value + "']").attr("selected", true);
            //太阳能热水器
            var hot_d_calc = calcHotWaterCompensate.substring(hot_water_index + 1, calcHotWaterCompensate.length);
            var arr = hot_d_calc.split("*");
            $("input[name='calcHotWaterCompensateMoney']").eq(0).val(arr[0]);
            $("input[name='calcHotWaterCompensateConvert']").eq(0).val(arr[1]);
        } else {
            //只有1个，判断是电热水器，还是太阳能
            if(optionExistValue(calcHotWaterCompensate)){
                //是电热水器
                $("select[name='sel_water_heater']").find("option[value='" + calcHotWaterCompensate + "']").attr("selected", true);
            }else{
                //是太阳能热水器
                var arr = calcHotWaterCompensate.split("*");
                $("input[name='calcHotWaterCompensateMoney']").eq(0).val(arr[0]);
                $("input[name='calcHotWaterCompensateConvert']").eq(0).val(arr[1]);
            }
        }
    }
    console.log("热水器拆装费结束");

    //住改商补助，利用计算公式，填充框
    var calcChangeCompensate = $("input[name='calcChangeCompensate']").eq(0).val() || "0";
    if(calcChangeCompensate != "0"){
        var arr = "";
        //可能会有2个公式
        var firstAddIndex = calcChangeCompensate.indexOf("+");
        if (firstAddIndex != -1) {
            arr = calcChangeCompensate.substring(0, firstAddIndex).split("*");
            var two_calc = calcChangeCompensate.substring(firstAddIndex+1, calcChangeCompensate.length);
            console.log("住改商补助分割：" + two_calc);
            $("input[name='calcChangeCompensateOther']").val(two_calc);
        }else{
            //公式：面积*(单价-评估单价)*比例
            arr = calcChangeCompensate.split("*");
        }

        if(arr[0] != 0) {
            $("input[name='calcChangeCompensateArea']").eq(0).val(arr[0]);
            var price = arr[1].substring(1, arr[1].length - 1);
            var priceArr = price.split("-");
            $("input[name='calcChangeCompensatePrice']").eq(0).val(priceArr[0]);
            $("input[name='calcChangeCompensateOldPrice']").eq(0).val(priceArr[1]);
            $("input[name='calcChangeCompensateProportion']").eq(0).val(arr[2]);
        }
    }
    console.log("住改商补助结束");

    //构建物-阳台
    var calcStructureBalcony = $("input[name='calcStructureBalcony']").eq(0).val() || "0";
    if (calcStructureBalcony != "0") {
        var arr = calcStructureBalcony.split("*");
        if(arr[0] != 0){
            $("input[name='structureBalconyArea']").eq(0).val(arr[0]);
            $("input[name='structureBalconyPrice']").eq(0).val(arr[1]);
            if (arr.length == 3) {
                $("input[name='structureBalconyProportion']").eq(0).val(arr[2]);
            }
        }
    }

    //构建物-外挑搭建
    var calcStructureBuild = $("input[name='calcStructureBuild']").eq(0).val() || "0";
    if (calcStructureBuild != "0") {
        var arr = calcStructureBuild.split("*");
        if (arr[0] != 0) {
            $("input[name='structureBuildArea']").eq(0).val(arr[0]);
            $("input[name='structureBuildPrice']").eq(0).val(arr[1]);
            if (arr.length == 3) {
                $("input[name='structureBuildProportion']").eq(0).val(arr[2]);
            }
        }
    }

    //构建物-暗楼
    var calcStructureDark = $("input[name='calcStructureDark']").eq(0).val() || "0";
    if (calcStructureDark != "0") {
        var arr = calcStructureDark.split("*");
        if (arr[0] != 0) {
            $("input[name='structureDarkArea']").eq(0).val(arr[0]);
            $("input[name='structureDarkPrice']").eq(0).val(arr[1]);
            if (arr.length == 3) {
                $("input[name='structureDarkProportion']").eq(0).val(arr[2]);
            }
        }
    }

    //构建物-夹层
    var calcStructureMezzanine = $("input[name='calcStructureMezzanine']").eq(0).val() || "0";
    if (calcStructureMezzanine != "0") {
        var arr = calcStructureMezzanine.split("*");
        if (arr[0] != 0) {
            $("input[name='structureMezzanineArea']").eq(0).val(arr[0]);
            $("input[name='structureMezzaninePrice']").eq(0).val(arr[1]);
            if (arr.length == 3) {
                $("input[name='structureMezzanineProportion']").eq(0).val(arr[2]);
            }
        }
    }

    //构建物-楼顶搭建
    var calcStructureRoof = $("input[name='calcStructureRoof']").eq(0).val() || "0";
    if (calcStructureRoof != "0") {
        var arr = calcStructureRoof.split("*");
        if (arr[0] != 0) {
            $("input[name='structureRoofArea']").eq(0).val(arr[0]);
            $("input[name='structureRoofPrice']").eq(0).val(arr[1]);
            if (arr.length == 3) {
                $("input[name='structureRoofProportion']").eq(0).val(arr[2]);
            }
        }
    }


});

//下拉框是否存在某个选项值
function optionExistValue(value, selId) {
    var isExist = false;
    var count = $('#' + selId).find('option').length;

    for (var i = 0; i < count; i++) {
        if ($('#' + selId).get(0).options[i].value == value) {
            isExist = true;
            break;
        }
    }

    return isExist;
}

