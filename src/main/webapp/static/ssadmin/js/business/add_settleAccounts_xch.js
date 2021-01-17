$(document).ready(function(){
    //有证房屋-单价失去焦点
    $("input[name='calcValueCompensatePrice']").eq(0).blur(function () {
        var assessPrice = $(this).val() || 0;
        //未经登记的合法建筑补偿
        $("input[name='calcNoRegisterLegalPrice']").val(assessPrice).change();
        //历史遗留无证房补偿
        $("input[name='calcHistoryLegacyPrice']").val(assessPrice).change();
        //货币补偿补助
        $("input[name='calcRmbCompensatePrice']").val(assessPrice).change();
        //货币搬迁奖励
        $("input[name='calcRmbMoveRewardPrice']").val(assessPrice).change();
        //住改商：旧房单价
        $("input[name='calcChangeCompensateOldPrice']").val(assessPrice).change();
        //住改商补助触发计算
        settleAccountObj.calcChangeCompensates();
    });

    //建筑面积失去焦点
    $("input[name='checkInArea']").eq(0).blur(function () {
        var checkInArea = $(this).val() || 0;
        //填充价值补偿的面积
        $("input[name='calcValueCompensateArea']").val(checkInArea).change();
    });

    //货币补偿补助3小框，失去焦点，重新计算公式
   /* $("input[name='calcRmbCompensateArea'],input[name='calcRmbCompensatePrice'],input[name='calcRmbCompensateProportion']").on("blur change", function () {
        settleAccountObj.fullCalcRmbCompensate();
    });*/

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
    $("input[name='calcInterimFeeArea'],input[name='calcInterimFeePrice'],input[name='calcInterimFeeMonth'],input[name='calcInterimFeeOther']").on("blur change", function () {
        settleAccountObj.fullCalcInterimFee();
    });

    //装修折旧 2小框，失去焦点，重新计算公式
    $("input[name='calcDecorationCompensateArea'],input[name='calcDecorationCompensatePrice'],input[name='calcDecorationCompensateOther']").on("blur change", function () {
        settleAccountObj.fullCalcDecoration();
    });

    //房屋价值补偿-有证房屋补偿，3小框，失去焦点，重新计算公式
    $("input[name='calcValueCompensateArea'],input[name='calcValueCompensatePrice'],input[name='calcValueCompensateProportion'],input[name='calcValueCompensateOther']").on("blur change", function () {
        settleAccountObj.fullCalcValueCompensate();
        settleAccountObj.fullDecorationArea();
    });

    //计算公式失去焦点
    $("input[name='calcValueCompensate']").eq(0).on("blur change", function () {
        settleAccountObj.changeCalcValueCompensate();
    });

    //房屋价值补偿-未经登记的合法建筑补偿
    $("input[name='calcNoRegisterLegalArea'],input[name='calcNoRegisterLegalPrice'],input[name='calcNoRegisterLegalProportion'],input[name='calcNoRegisterLegalOther']").on("blur change", function () {
        settleAccountObj.fullCalcNoRegisterLegal();
        settleAccountObj.fullDecorationArea();
    });

    //计算公式失去焦点
    $("input[name='calcNoRegisterLegal']").eq(0).on("blur change", function () {
        settleAccountObj.changeCalcNoRegisterLegal();
    });

    //房屋价值补偿-历史遗留无证房补偿
    $("input[name='calcHistoryLegacyArea'],input[name='calcHistoryLegacyPrice'],input[name='calcHistoryLegacyProportion'],input[name='calcHistoryLegacyOther']").on("blur change", function () {
        settleAccountObj.fullCalcHistoryLegacy();
        settleAccountObj.fullDecorationArea();
    });

    //计算公式失去焦点
    $("input[name='calcHistoryLegacy']").eq(0).on("blur change", function () {
        settleAccountObj.changeCalcHistoryLegacy();
    });


    //给所有参与求和的元素，绑定级联变动事件
    //参与被征收房屋补偿合计的金额框
    var sumCompensateNameArr = new Array();
    sumCompensateNameArr[0] = "valueCompensate";   //有证房屋补偿
    sumCompensateNameArr[1] = "noRegisterLegal";   //未经登记的合法建筑补偿
    sumCompensateNameArr[2] = "historyLegacy";   //历史遗留无证房补偿
    sumCompensateNameArr[3] = "decorationCompensate";  //装修折旧补偿
    sumCompensateNameArr[4] = "moveHouseFee";  //搬家费
    sumCompensateNameArr[5] = "interimFee";  //过渡费
    sumCompensateNameArr[6] = "guarantee";  //保底补偿
    sumCompensateNameArr[7] = "suspendBusinessFee";  //停产停业损失补偿
    sumCompensateNameArr[8] = "moveWaterMeterFee";  //水表迁移费
    sumCompensateNameArr[9] = "moveAmmeterFee";   //电表迁移费
    sumCompensateNameArr[10] = "moveAirConditioningFee";  //空调移机费
    sumCompensateNameArr[11] = "hotWaterCompensate";  //热水器拆装费
    sumCompensateNameArr[12] = "gasFee";  //管道煤气拆装费
    sumCompensateNameArr[13] = "structureBalcony";   //结构内阳台
    sumCompensateNameArr[14] = "structureBuild";   //外挑搭建
    sumCompensateNameArr[15] = "structureDark";   //暗楼
    sumCompensateNameArr[16] = "structureMezzanine";   //夹层
    sumCompensateNameArr[17] = "structureRoof";   //楼顶搭建
    sumCompensateNameArr[18] = "affiliatedOther"; //房屋附属设施补偿-其他
    sumCompensateNameArr[19] = "changeCompensate";   //住改商补助
    sumCompensateNameArr[20] = "rmbCompensate";   //货币补偿补助
    sumCompensateNameArr[21] = "smallAreaReward";  //小面积住房搬迁奖励
    sumCompensateNameArr[22] = "lifeCompensate";   //生活困难补助
    sumCompensateNameArr[23] = "moveReward";  //货币搬迁奖励
    sumCompensateNameArr[24] = "buildingAreaFee";  //建筑面积补助
    // sumCompensateNameArr[17] = "noMoveCompensate";  //不可移动设备设施补偿
    sumCompensateNameArr[25] = "otherRmb";  //其他
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
    $("#ammeter_main,#ammeter_sub,#ammeter_sa,#ammeter_time").on("change", function () {
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

    //保底补偿下拉框
    $("select[name='sel_guarantee']").eq(0).change(function () {
        var guarantee_area = $(this).val();
        if(guarantee_area == 0){
            $("input[name='calcGuarantee']").eq(0).val("0").change();
        }else{
            var calcValueCompensateArea = $("input[name='calcValueCompensateArea']").eq(0).val() || 0;
            var assessPrice = $("input[name='calcValueCompensatePrice']").eq(0).val() || 0;

            var calcNoRegisterLegalArea = $("input[name='calcNoRegisterLegalArea']").eq(0).val() || 0;
            /*var calcHistoryLegacyArea = $("input[name='calcHistoryLegacyArea']").eq(0).val() || 0;
            var calcHistoryLegacyProportion = $("input[name='calcHistoryLegacyProportion']").eq(0).val() || 1;*/

            if(calcValueCompensateArea == 0 || assessPrice == 0){
                alert("进行保底补偿之前，请先填写有证房屋建筑面积、单价");
                $(this).val(0);
                return;
            }
            //var guarantee_money_calc = "(" + guarantee_area + "-" + calcValueCompensateArea + "-" + calcNoRegisterLegalArea + "-" + calcHistoryLegacyArea + "*" + calcHistoryLegacyProportion + ")" + "*" + assessPrice;
            var guarantee_money_calc = "(" + guarantee_area;
            if (calcValueCompensateArea > 0) {
                guarantee_money_calc += "-" + calcValueCompensateArea;
            }
            if (calcNoRegisterLegalArea > 0) {
                guarantee_money_calc += "-" + calcNoRegisterLegalArea;
            }
          /*  if (calcHistoryLegacyArea > 0) {
                guarantee_money_calc += "-" + calcHistoryLegacyArea + "*" + calcHistoryLegacyProportion;
            }*/
            guarantee_money_calc += ")" + "*" + assessPrice;
            if(eval(guarantee_money_calc) <= 0){
                console.log("保底金额必须大于0，当前公式: " + guarantee_money_calc);
                $("input[name='calcGuarantee']").eq(0).val("0").change();
            }else{
                $("input[name='calcGuarantee']").eq(0).val(guarantee_money_calc).change();
            }
        }
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

    //奖励计算公式
    $("input[name='calcMoveReward']").eq(0).on("blur change", function () {
        settleAccountObj.runCalc("calcMoveReward", "moveReward");
    });

    //过渡费公式，绑定失去焦点、变更事件，计算金额
    $("input[name='calcInterimFee']").eq(0).on("blur change", function () {
        settleAccountObj.runCalc("calcInterimFee", "interimFee");
    });

    //搬家费 选择
    $("select[name='sel_calcMoveHouseFee']").eq(0).change(function () {
        settleAccountObj.fullCalcMoveHouseFee();
    });
    //搬家费计算公式
    $("input[name='calcMoveHouseFee']").eq(0).on("blur change", function () {
        settleAccountObj.runCalc("calcMoveHouseFee", "moveHouseFee");
    });


    /*$("select[name='calcMoveHouseFee']").eq(0).change(function () {
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
    });*/
    //热水器拆装费
    $("input[name='calcHotWaterCompensate']").eq(0).on("blur change", function () {
        settleAccountObj.runCalc("calcHotWaterCompensate", "hotWaterCompensate");
    });
    //管道煤气拆装费
    $("input[name='calcGasFee']").eq(0).on("blur change", function () {
        settleAccountObj.runCalc("calcGasFee", "gasFee");
    });
    //构建物补偿
    $("input[name='calcStructureBalcony']").eq(0).on("blur change", function () {
        settleAccountObj.runCalc("calcStructureBalcony", "structureBalcony");
    });
    $("input[name='calcStructureBuild']").eq(0).on("blur change", function () {
        settleAccountObj.runCalc("calcStructureBuild", "structureBuild");
    });
    $("input[name='calcStructureDark']").eq(0).on("blur change", function () {
        settleAccountObj.runCalc("calcStructureDark", "structureDark");
    });
    $("input[name='calcStructureMezzanine']").eq(0).on("blur change", function () {
        settleAccountObj.runCalc("calcStructureMezzanine", "structureMezzanine");
    });
    $("input[name='calcStructureRoof']").eq(0).on("blur change", function () {
        settleAccountObj.runCalc("calcStructureRoof", "structureRoof");
    });
    /* $("input[name='calcStructureCompensate']").eq(0).on("blur change", function () {
         settleAccountObj.runCalc("calcStructureCompensate", "structureCompensate");
     });*/
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
        var martyrFlag = $("input[name='martyr']").eq(0).prop("checked");
        var noChildFlag = $("input[name='noChild']").eq(0).prop("checked");

        var calcLifeCompensate = "";
        console.log("生活困难补助选择：" + diseaseSubsidyFlag + "  " + disabilitySubsidyFlag + " " + basicLivingSubsidyFlag + "  " + martyrFlag + "  " + noChildFlag);
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
        if(martyrFlag) {
            calcLifeCompensate = calcLifeCompensate + "+" + $("input[name='martyr']").eq(0).val() || 0;
        }else{
            calcLifeCompensate =  calcLifeCompensate + "+" + 0;
        }
        if(noChildFlag) {
            calcLifeCompensate = calcLifeCompensate + "+" + $("input[name='noChild']").eq(0).val() || 0;
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
    //保底补偿
    $("input[name='calcGuarantee']").eq(0).on("blur change", function () {
        settleAccountObj.runCalc("calcGuarantee", "guarantee");
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

    //左上角补偿方式选择，级联 “奖励”的计算，切换选择时，公式不一样
    $("input[name='compensateType']").on("click", function () {
        settleAccountObj.calcMoveReward();
        settleAccountObj.calcRmbCompensate();
        settleAccountObj.calcSmallAreaReward();
    });


    //构建物补偿-阳台
    $("input[name='structureBalconyArea'],input[name='structureBalconyPrice'],input[name='structureBalconyProportion']").on("blur change", function () {
        settleAccountObj.calcStructureBalcony();
    });

    //构建物补偿-外挑搭建
    $("input[name='structureBuildArea'],input[name='structureBuildPrice'],input[name='structureBuildProportion']").on("blur change", function () {
        settleAccountObj.calcStructureBuild();
    });

    //构建物补偿-暗楼
    $("input[name='structureDarkArea'],input[name='structureDarkPrice'],input[name='structureDarkProportion']").on("blur change", function () {
        settleAccountObj.calcStructureDark();
    });

    //构建物补偿-夹层
    $("input[name='structureMezzanineArea'],input[name='structureMezzaninePrice'],input[name='structureMezzanineProportion']").on("blur change", function () {
        settleAccountObj.calcStructureMezzanine();
    });

    //构建物补偿-楼顶搭建
    $("input[name='structureRoofArea'],input[name='structureRoofPrice'],input[name='structureRoofProportion']").on("blur change", function () {
        settleAccountObj.calcStructureRoof();
    });

    /* //无烟灶台选择数量
     $("#sel_stove").on("change", function () {
         settleAccountObj.calcDarkBuilding();
     });

     //构建物：暗楼面积
     $("input[name='calcStructureCompensateArea']").eq(0).on("blur change", function () {
         settleAccountObj.calcDarkBuilding();
     });

     //构建物：暗楼单价
     $("input[name='calcStructureCompensatePrice']").eq(0).on("blur change", function () {
         settleAccountObj.calcDarkBuilding();
     });*/

    //电热水器选择数量
    $("select[name='sel_water_heater']").on("change", function () {
        settleAccountObj.calcHotWater();
    });

    //太阳能热水器：金额
    $("input[name='calcHotWaterCompensateMoney']").eq(0).on("blur change", function () {
        settleAccountObj.calcHotWater();
    });

    //太阳能热水器：折算比例
    $("input[name='calcHotWaterCompensateConvert']").eq(0).on("blur change", function () {
        settleAccountObj.calcHotWater();
    });

    //住改商补助
    $("input[name='calcChangeCompensateArea'],input[name='calcChangeCompensatePrice'],input[name='calcChangeCompensateProportion']," +
        "input[name='calcChangeCompensateOldPrice'],input[name='calcChangeCompensateOther']").on("blur change", function () {
        settleAccountObj.calcChangeCompensates();
    });

    //产权交换单价
    $("input[name='swapPrice1'],input[name='swapPrice2'],input[name='swapPrice3'],input[name='swapPrice4'],input[name='swapPrice5']").on("blur change", function () {
        settleAccountObj.calcSwapMoney();
    });

    //产权交换面积
    $("input[name='swapArea1'],input[name='swapArea2'],input[name='swapArea3'],input[name='swapArea4'],input[name='swapArea5']").on("blur change", function () {
        settleAccountObj.calcSwapMoney();
    });

    //产权交换公式
    $("input[name='swapMoney1'],input[name='swapMoney2'],input[name='swapMoney3'],input[name='swapMoney4'],input[name='swapMoney5']").on("blur change", function () {
        settleAccountObj.calcHouseMoney();
    });

    //抵扣金额失去焦点
    $("input[name='deduction']").on("blur change", function () {
        settleAccountObj.changeDeduction();
    });

    //新房名称失去焦点
    /*$("input[name='newHouseName']").on("blur", function () {
        var newHouseAddress =  $(this).val();
        if(!newHouseAddress){
            return;
        }
        console.log("安置房地址："+newHouseAddress);
        var reg = /^.+[0-9]+栋[0-9]+单元[0-9]+层[0-9]+号$/;
        if(!reg.test(newHouseAddress)){
            alertMsg.error("安置房名称、房号错误，请按标准格式填写，示例:金地太阳城8栋1单元9层1号");
            $(this).val("");
        }

    });*/


});


var settleAccountObj = {

    //汇总求合计的所有元素
    getSumElementArr: function () {
        //参与被征收房屋补偿合计的金额框
        var sumCompensateNameArr = new Array();
        sumCompensateNameArr[0] = "valueCompensate";   //有证房屋补偿
        sumCompensateNameArr[1] = "noRegisterLegal";   //未经登记的合法建筑补偿
        sumCompensateNameArr[2] = "historyLegacy";   //历史遗留无证房补偿
        sumCompensateNameArr[3] = "decorationCompensate";  //装修折旧补偿
        sumCompensateNameArr[4] = "moveHouseFee";  //搬家费
        sumCompensateNameArr[5] = "interimFee";  //过渡费
        sumCompensateNameArr[6] = "guarantee";  //保底补偿
        sumCompensateNameArr[7] = "suspendBusinessFee";  //停产停业损失补偿
        sumCompensateNameArr[8] = "moveWaterMeterFee";  //水表迁移费
        sumCompensateNameArr[9] = "moveAmmeterFee";   //电表迁移费
        sumCompensateNameArr[10] = "moveAirConditioningFee";  //空调移机费
        sumCompensateNameArr[11] = "hotWaterCompensate";  //热水器拆装费
        sumCompensateNameArr[12] = "gasFee";  //管道煤气拆装费
        sumCompensateNameArr[13] = "structureBalcony";   //结构内阳台
        sumCompensateNameArr[14] = "structureBuild";   //外挑搭建
        sumCompensateNameArr[15] = "structureDark";   //暗楼
        sumCompensateNameArr[16] = "structureMezzanine";   //夹层
        sumCompensateNameArr[17] = "structureRoof";   //楼顶搭建
        sumCompensateNameArr[18] = "affiliatedOther"; //房屋附属设施补偿-其他
        sumCompensateNameArr[19] = "changeCompensate";   //住改商补助
        sumCompensateNameArr[20] = "rmbCompensate";   //货币补偿补助
        sumCompensateNameArr[21] = "smallAreaReward";  //小面积住房搬迁奖励
        sumCompensateNameArr[22] = "lifeCompensate";   //生活困难补助
        sumCompensateNameArr[23] = "moveReward";  //货币搬迁奖励
        sumCompensateNameArr[24] = "buildingAreaFee";  //建筑面积补助
        // sumCompensateNameArr[17] = "noMoveCompensate";  //不可移动设备设施补偿
        sumCompensateNameArr[25] = "otherRmb";  //其他
        return sumCompensateNameArr;
    },

    //填充房屋价值补偿-有证计算公式
    fullCalcValueCompensate: function () {

        var area = $("input[name='calcValueCompensateArea']").eq(0).val() || 0;
        var price = $("input[name='calcValueCompensatePrice']").eq(0).val() || 0;
        var proportion = $("input[name='calcValueCompensateProportion']").eq(0).val();
        var other = $("input[name='calcValueCompensateOther']").eq(0).val();
        var calc = area + "*" + price;
        if (proportion) {
            calc += "*" + proportion;
        }
        if (other) {
            calc += "+" + other;
        }
        //覆盖公式中的单价
        $("input[name='calcValueCompensate']").eq(0).val(calc).change();
    },


    //改变房屋价值补偿-有证计算公式
    changeCalcValueCompensate: function () {
        var calcValueCompensate = $("input[name='calcValueCompensate']").eq(0).val() || 0;
        var valueCompensate = calcObj.roundLastNum(eval(calcValueCompensate));
        $("input[name='valueCompensate']").eq(0).val(valueCompensate).change();
        settleAccountObj.calcMoveReward();
        settleAccountObj.calcRmbCompensate();
        settleAccountObj.calcSmallAreaReward();
    },

    //填充房屋价值补偿-未经登记合法
    fullCalcNoRegisterLegal: function () {

        var area = $("input[name='calcNoRegisterLegalArea']").eq(0).val() || 0;
        var price = $("input[name='calcNoRegisterLegalPrice']").eq(0).val() || 0;
        var proportion = $("input[name='calcNoRegisterLegalProportion']").eq(0).val();
        var other = $("input[name='calcNoRegisterLegalOther']").eq(0).val();
        var calc = area + "*" + price;
        if (proportion) {
            calc += "*" + proportion;
        }
        if (other) {
            calc += "+" + other;
        }
        console.log("未经登记合法计算公式：" + calc);
        //覆盖公式中的单价
        $("input[name='calcNoRegisterLegal']").eq(0).val(calc).change();
    },

    //改变房屋价值补偿-未登记合法计算公式
    changeCalcNoRegisterLegal: function () {
        var calcNoRegisterLegal = $("input[name='calcNoRegisterLegal']").eq(0).val() || 0;
        var noRegisterLegal = calcObj.roundLastNum(eval(calcNoRegisterLegal));
        console.log("运行未登记合法计算公式: " + noRegisterLegal);
        $("input[name='noRegisterLegal']").eq(0).val(noRegisterLegal).change();
        settleAccountObj.calcMoveReward();
        settleAccountObj.calcRmbCompensate();
        settleAccountObj.calcSmallAreaReward();
    },


    //填充房屋价值补偿-历史遗留
    fullCalcHistoryLegacy: function () {
        var area = $("input[name='calcHistoryLegacyArea']").eq(0).val() || 0;
        var price = $("input[name='calcHistoryLegacyPrice']").eq(0).val() || 0;
        var proportion = $("input[name='calcHistoryLegacyProportion']").eq(0).val();
        var other = $("input[name='calcHistoryLegacyOther']").eq(0).val();
        var calc = area + "*" + price;
        if (proportion) {
            calc += "*" + proportion;
        }
        if (other) {
            calc += "+" + other;
        }
        //覆盖公式中的单价
        $("input[name='calcHistoryLegacy']").eq(0).val(calc).change();
    },

    //改变房屋价值补偿-历史遗留计算公式
    changeCalcHistoryLegacy: function () {
        var calcHistoryLegacy = $("input[name='calcHistoryLegacy']").eq(0).val() || 0;
        var historyLegacy = calcObj.roundLastNum(eval(calcHistoryLegacy));
        $("input[name='historyLegacy']").eq(0).val(historyLegacy).change();
        settleAccountObj.calcMoveReward();
        settleAccountObj.calcRmbCompensate();
    },

    //填充被征收房屋补偿合计
    fullSumCompensate: function () {
        var sumCompensateNameArr = settleAccountObj.getSumElementArr();
        var sumCompensate = 0;
        for (var i = 0; i < sumCompensateNameArr.length; i++) {
            var nameText = sumCompensateNameArr[i];
            var num = $("input[name='" + nameText + "']").eq(0).val();
            sumCompensate += new Number(num);
        }
        var lastSumCompensate = calcObj.getSumCompensate(sumCompensate);
        $("input[name='sumCompensate']").eq(0).val(lastSumCompensate).change();
    },

    //填充应收、应付款
    fullPayReceive: function () {
        //旧房金额
        var sumCompensate = $("input[name='sumCompensate']").eq(0).val() || 0;
        sumCompensate = new Number(sumCompensate);
        //新房金额
        var houseMoney = $("input[name='houseMoney']").eq(0).val() || 0;
        houseMoney = new Number(houseMoney);

        //已抵扣安置房款= 旧房金额<新房金额，则抵扣款是旧房金额，反之，就是新房金额
        var money = 0;
        if (sumCompensate > houseMoney) {
            //应付
            money = sumCompensate - houseMoney;
            $("input[name='deduction']").eq(0).val(houseMoney).change();
        } else if (sumCompensate < houseMoney) {
            //应收
            money = houseMoney - sumCompensate;
            $("input[name='deduction']").eq(0).val(sumCompensate).change();
        } else if (sumCompensate == houseMoney) {
            $("input[name='deduction']").eq(0).val(sumCompensate).change();
        }

        money = calcObj.getPayTotal(money);
        $("input[name='payTotal']").eq(0).val(money);
        var payTotalChinese = Araia_To_Chinese(money);
        $("input[name='payMoney']").eq(0).val(payTotalChinese);

    },
    //改变抵扣款时，重新计算应付应收
    changeDeduction: function () {
        //旧房金额
        var sumCompensate = $("input[name='sumCompensate']").eq(0).val() || 0;
        sumCompensate = new Number(sumCompensate);

        var deduction = $("input[name='deduction']").eq(0).val() || 0;
        deduction = new Number(deduction);

        var money = Math.abs(sumCompensate - deduction);
        money = calcObj.getPayTotal(money);
        $("input[name='payTotal']").eq(0).val(money);
        var payTotalChinese = Araia_To_Chinese(money);
        $("input[name='payMoney']").eq(0).val(payTotalChinese);
    },


    //计算水表
    fullWaterMeter: function () {
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
    fullAmmeter: function () {
        //获取主表
        var ammeterMain = $("#ammeter_main").val() || 0;
        var ammeterMainFee = eval(ammeterMain);
        //获取副表
        var ammeterSub = $("#ammeter_sub").val() || 0;
        var ammeterSubFee = eval(ammeterSub);
        //三相表
        var ammeterSa = $("#ammeter_sa").val() || 0;
        var ammeterSaFee = eval(ammeterSa);
        //分时表
        var ammeterTime = $("#ammeter_time").val() || 0;
        var ammeterTimeFee = eval(ammeterTime);
        var moveAmmeterFee = ammeterMainFee + ammeterSubFee + ammeterSaFee + ammeterTimeFee;
        //电表迁移费
        $("input[name='moveAmmeterFee']").eq(0).val(moveAmmeterFee).change();
        //电表迁移费计算公式
        $("input[name='calcMoveAmmeterFee']").eq(0).val(ammeterMain + "+" + ammeterSub + "+" + ammeterSa + "+" + ammeterTime);
    },
    //计算空调移机费
    fullAirConditioner: function () {
        //获取窗机
        var airConditionerShutter = $("#air_conditioner_shutter").val() || 0;
        var airConditionerShutterFee = eval(airConditionerShutter);

        //获取挂机
        var airConditionerHang = $("#air_conditioner_hang").val() || 0;
        var airConditionerHangFee = eval(airConditionerHang);

        //获取柜机
        var airConditionerCabinet = $("#air_conditioner_cabinet").val() || 0;
        var airConditionerCabinetFee = eval(airConditionerCabinet);

        var moveAirConditioningFee = airConditionerCabinetFee + airConditionerShutterFee + airConditionerHangFee;

        $("input[name='moveAirConditioningFee']").eq(0).val(moveAirConditioningFee).change();
        $("input[name='calcMoveAirConditioningFee']").eq(0).val(airConditionerShutter + "+" + airConditionerHang + "+" + airConditionerCabinet);
    },
    //运行装修补偿的计算公式，得到折旧费用
    calcDecorationCompensate: function () {
        var calcDecorationCompensate = $("input[name='calcDecorationCompensate']").eq(0).val() || 0;
        var decorationCompensate = calcObj.roundLastNum(eval(calcDecorationCompensate));
        $("input[name='decorationCompensate']").eq(0).val(decorationCompensate).change();
    },
    /**
     * 普通框，执行计算公式
     * @param calcObjName  计算公式框名称
     * @param moneyObjName  金额框名称
     */
    runCalc: function (calcObjName, moneyObjName) {
        var calcText = $("input[name='" + calcObjName + "']").eq(0).val() || 0;
        var money = calcObj.roundLastNum(eval(calcText));
        $("input[name='" + moneyObjName + "']").eq(0).val(money).change();
    },
    //装修折旧补偿，利用2小框数字，得到计算公式
    fullCalcDecoration: function () {
        var calcDecorationCompensateArea = $("input[name='calcDecorationCompensateArea']").eq(0).val() || 0;
        var calcDecorationCompensatePrice = $("input[name='calcDecorationCompensatePrice']").eq(0).val() || 0;
        var calcDecorationCompensateOther = $("input[name='calcDecorationCompensateOther']").eq(0).val();
        var calc = calcDecorationCompensateArea + "*" + calcDecorationCompensatePrice;
        if (calcDecorationCompensateOther) {
            calc = calc + "+" + calcDecorationCompensateOther;
        }
        $("input[name='calcDecorationCompensate']").eq(0).val(calc).change();

    },
    //临时安置补偿，利用3小框，得到计算公式
    fullCalcInterimFee: function () {
        var calcInterimFeeArea = $("input[name='calcInterimFeeArea']").eq(0).val() || 0;
        var calcInterimFeePrice = $("input[name='calcInterimFeePrice']").eq(0).val() || 0;
        var calcInterimFeeMonth = $("input[name='calcInterimFeeMonth']").eq(0).val() || 0;
        //其它是个计算公式
        var calcInterimFeeOther = $("input[name='calcInterimFeeOther']").eq(0).val() || 0;
        var calcInterimFee = calcInterimFeeArea + "*" + calcInterimFeePrice + "*" + calcInterimFeeMonth;
        if (calcInterimFeeOther != 0) {
            calcInterimFee = calcInterimFee + "+" + calcInterimFeeOther;
        }
        $("input[name='calcInterimFee']").eq(0).val(calcInterimFee).change();
    },
    //未登记面积补偿，利用3小框，得到计算公式
    fullCalcNoCheckCompensate: function () {
        var calcNoCheckCompensateArea = $("input[name='calcNoCheckCompensateArea']").eq(0).val() || 0;
        var calcNoCheckCompensatePrice = $("input[name='calcNoCheckCompensatePrice']").eq(0).val() || 0;
        var calcNoCheckCompensateProportion = $("input[name='calcNoCheckCompensateProportion']").eq(0).val() || 0;
        var calcNoCheckCompensate = calcNoCheckCompensateArea + "*" + calcNoCheckCompensatePrice + "*" + calcNoCheckCompensateProportion;
        $("input[name='calcNoCheckCompensate']").eq(0).val(calcNoCheckCompensate).change();
    },
    //货币补偿补助，利用3小框，得到计算公式
    /*fullCalcRmbCompensate:function () {
        var calcRmbCompensateArea = $("input[name='calcRmbCompensateArea']").eq(0).val() || 0;
        var calcRmbCompensatePrice = $("input[name='calcRmbCompensatePrice']").eq(0).val() || 0;
        var calcRmbCompensateProportion = $("input[name='calcRmbCompensateProportion']").eq(0).val() || 0;
        var calcRmbCompensate = calcRmbCompensateArea + "*" + calcRmbCompensatePrice + "*" + calcRmbCompensateProportion;
        $("input[name='calcRmbCompensate']").eq(0).val(calcRmbCompensate).change();
    },*/
    //货币搬迁奖励，利用3小框，得到计算公式
    fullCalcRmbMoveReward: function () {
        var calcRmbMoveRewardArea = $("input[name='calcRmbMoveRewardArea']").eq(0).val() || 0;
        var calcRmbMoveRewardPrice = $("input[name='calcRmbMoveRewardPrice']").eq(0).val() || 0;
        var calcRmbMoveRewardProportion = $("input[name='calcRmbMoveRewardProportion']").eq(0).val() || 0;
        var calcRmbMoveReward = calcRmbMoveRewardArea + "*" + calcRmbMoveRewardPrice + "*" + calcRmbMoveRewardProportion;
        $("input[name='calcRmbMoveReward']").eq(0).val(calcRmbMoveReward).change();
    },

    //构建物：无烟灶台、暗楼 补偿
    /*calcDarkBuilding:function () {
        //无烟灶台
        var stove = $("select[id='sel_stove']").val();
        //暗楼
        var calcStructureCompensateArea = $("input[name='calcStructureCompensateArea']").eq(0).val() || 0;
        var calcStructureCompensatePrice = $("input[name='calcStructureCompensatePrice']").eq(0).val() || 0;
        //计算公式，格式：1*500+20*1350，+前面的是无烟灶台，后面的是暗楼
        var calcStructureCompensate = stove + "+" + calcStructureCompensateArea + "*" + calcStructureCompensatePrice;
        console.log("构建物计算公式：" + calcStructureCompensate);
        $("input[name='calcStructureCompensate']").eq(0).val(calcStructureCompensate).change();
    },*/

    //热水器拆装费计算
    calcHotWater: function () {
        //烧电  得到的格式：1*200
        var water_heater_calc = $("select[name='sel_water_heater']").eq(0).val()

        //太阳能
        var calcHotWaterCompensateMoney = 0;
        var hotWaterFlag = $("input[name='calcHotWaterCompensateMoney']").eq(0).prop('checked');
        if(hotWaterFlag){
            calcHotWaterCompensateMoney =  $("input[name='calcHotWaterCompensateMoney']").eq(0).val();
        }

        var calcHotWaterCompensate = water_heater_calc + "+" + calcHotWaterCompensateMoney;

        console.log("热水器拆装计算公式：" + calcHotWaterCompensate);
        $("input[name='calcHotWaterCompensate']").eq(0).val(calcHotWaterCompensate).change();
    },

    //住改商补助计算
    calcChangeCompensates: function () {
        //面积
        var calcChangeCompensateArea = $("input[name='calcChangeCompensateArea']").eq(0).val() || 0;
        //新房单价
        var calcChangeCompensatePrice = $("input[name='calcChangeCompensatePrice']").eq(0).val() || 0;
        //比例
        var calcChangeCompensateProportion = $("input[name='calcChangeCompensateProportion']").eq(0).val() || 0;
        //获取旧房单价
        var calcChangeCompensateOldPrice = $("input[name='calcChangeCompensateOldPrice']").eq(0).val() || 0;
        //获取其他
        var calcChangeCompensateOther = $("input[name='calcChangeCompensateOther']").eq(0).val() || 0;

        //公式：面积*(新房单价-旧房单价)*比例
        var calcChangeCompensate = calcChangeCompensateArea + "*" + "(" + calcChangeCompensatePrice + "-" + calcChangeCompensateOldPrice + ")" + "*" + calcChangeCompensateProportion;
        if (calcChangeCompensateOther != 0) {
            calcChangeCompensate = calcChangeCompensate + "+" + calcChangeCompensateOther
        }
        console.log("住改商计算公式: " + calcChangeCompensate);
        $("input[name='calcChangeCompensate']").eq(0).val(calcChangeCompensate).change();
    },

    //未经登记建筑面积=未经登记合法面积+历史遗留面积
    /* fullNoCheckinArea:function () {
         var noRegisterLegalArea = $("input[name='noRegisterLegalArea']").eq(0).val() || 0;
         var historyLegacyArea = $("input[name='historyLegacyArea']").eq(0).val() || 0;
         var area = new Number(noRegisterLegalArea) + new Number(historyLegacyArea);
         var tempArea = area + "";
         if (tempArea.indexOf(".") != -1) {
             area = area.toFixed(2);
         }
         $("input[name='noCheckinArea']").eq(0).val(area);
     },*/
    //建筑面积=有证面积+未经登记合法面积+历史遗留面积
    /*fullCheckInArea:function () {
        var certifiedArea = $("input[name='certifiedArea']").eq(0).val() || 0;
        var noRegisterLegalArea = $("input[name='noRegisterLegalArea']").eq(0).val() || 0;
        var historyLegacyArea = $("input[name='historyLegacyArea']").eq(0).val() || 0;
        var area = new Number(certifiedArea) + new Number(noRegisterLegalArea) + new Number(historyLegacyArea);
        //做小数或整数处理
        var tempArea = area + "";
        if (tempArea.indexOf(".") != -1) {
            //是有小数位的，则保留2位，防止有很多位小数
            area = area.toFixed(2);
        }
        $("input[name='checkInArea']").eq(0).val(area);

        //填充装修折旧的面积
        $("input[name='calcDecorationCompensateArea']").val(area).change();
    },*/
    //搬家费：下拉框改变
    fullCalcMoveHouseFee: function () {
        var sel_calcMoveHouseFee = $("select[name='sel_calcMoveHouseFee']").eq(0).val();
        if (sel_calcMoveHouseFee > 0) {
            $("input[name='calcMoveHouseFee']").eq(0).val(sel_calcMoveHouseFee).change();
        } else {
            $("input[name='calcMoveHouseFee']").eq(0).val("").change();
            $("input[name='calcMoveHouseFee']").eq(0).prop("placeholder", "请输入搬家费");
        }
    },
    //构建物计算公式-阳台
    calcStructureBalcony: function () {
        var area = $("input[name='structureBalconyArea']").eq(0).val() || 0;
        var price = $("input[name='structureBalconyPrice']").eq(0).val() || 0;
        var proportion = $("input[name='structureBalconyProportion']").eq(0).val() || 0;
        var calc = area + "*" + price;
        if (proportion != 0) {
            calc += "*" + proportion;
        }
        console.log("构建物补偿-阳台，公式：" + calc);
        $("input[name='calcStructureBalcony']").eq(0).val(calc).change();

    },
    //构建物补偿-外挑搭建
    calcStructureBuild: function () {
        var area = $("input[name='structureBuildArea']").eq(0).val() || 0;
        var price = $("input[name='structureBuildPrice']").eq(0).val() || 0;
        var proportion = $("input[name='structureBuildProportion']").eq(0).val() || 0;
        var calc = area + "*" + price;
        if (proportion != 0) {
            calc += "*" + proportion;
        }
        console.log("构建物补偿-外挑搭建，公式：" + calc);
        $("input[name='calcStructureBuild']").eq(0).val(calc).change();
    },
    //构建物补偿-暗楼
    calcStructureDark: function () {
        var area = $("input[name='structureDarkArea']").eq(0).val() || 0;
        var price = $("input[name='structureDarkPrice']").eq(0).val() || 0;
        var proportion = $("input[name='structureDarkProportion']").eq(0).val() || 0;
        var calc = area + "*" + price;
        if (proportion != 0) {
            calc += "*" + proportion;
        }
        console.log("构建物补偿-暗楼，公式：" + calc);
        $("input[name='calcStructureDark']").eq(0).val(calc).change();
    },
    //构建物补偿-夹层
    calcStructureMezzanine: function () {
        var area = $("input[name='structureMezzanineArea']").eq(0).val() || 0;
        var price = $("input[name='structureMezzaninePrice']").eq(0).val() || 0;
        var proportion = $("input[name='structureMezzanineProportion']").eq(0).val() || 0;
        var calc = area + "*" + price;
        if (proportion != 0) {
            calc += "*" + proportion;
        }
        console.log("构建物补偿-夹层，公式：" + calc);
        $("input[name='calcStructureMezzanine']").eq(0).val(calc).change();
    },
    //构建物补偿-楼顶搭建
    calcStructureRoof: function () {
        var area = $("input[name='structureRoofArea']").eq(0).val() || 0;
        var price = $("input[name='structureRoofPrice']").eq(0).val() || 0;
        var proportion = $("input[name='structureRoofProportion']").eq(0).val() || 0;
        var calc = area + "*" + price;
        if (proportion != 0) {
            calc += "*" + proportion;
        }
        console.log("构建物补偿-楼顶搭建，公式：" + calc);
        $("input[name='calcStructureRoof']").eq(0).val(calc).change();
    },
    //奖励计算公式
    calcMoveReward: function () {
        var compensateType = $("input[name='compensateType']:checked").val();

        //有证计算公式不为空时，进行计算
        var calcValueCompensate = $("input[name='calcValueCompensate']").eq(0).val() || 0;
        if (eval(calcValueCompensate) == 0) {
            $("input[name='calcMoveReward']").eq(0).val("").change();
            return;
        }

        //比例
        var proportion = 0;
        if (compensateType == 0) {
            proportion = $("input[name='rewardRmbProportion']").eq(0).val() || 0;
        } else if (compensateType == 1) {
            proportion = $("input[name='rewardSwapProportion']").eq(0).val() || 0;
        }

        var calcMoveReward = settleAccountObj.extReward(proportion);

        console.log("西城壕奖励计算公式：" + calcMoveReward);
        $("input[name='calcMoveReward']").eq(0).val(calcMoveReward).change();

    },
    //计算小户型住房困难补助
    calcSmallAreaReward: function () {
        //仅选择货币时有值，选产权调换的没有
        var compensateType = $("input[name='compensateType']:checked").val();
        if (compensateType == 1) {
            $("input[name='calcSmallAreaReward']").eq(0).val("").change();
            return;
        }

        var calcValueCompensateArea = $("input[name='calcValueCompensateArea']").eq(0).val() || 0;
        var calcNoRegisterLegalArea = $("input[name='calcNoRegisterLegalArea']").eq(0).val() || 0;
        var sumArea = new Number(calcValueCompensateArea) + new Number(calcNoRegisterLegalArea);
        sumArea = Math.round(sumArea * 1000) / 1000;
        //不足40，按40计算
        if (sumArea < 40) {
            sumArea = 40;
        }

        //根据面积，计算比例
        var rate = funSmallAreaRate(sumArea);
        //超出小户型限定，没有补偿，置为空
        if (rate <= 0) {
            $("input[name='calcSmallAreaReward']").eq(0).val("").change();
            return;
        }
        //(有证面积*单价+未登记面积*单价)*比例
        var calcStr = settleAccountObj.extReward(rate);
        $("input[name='calcSmallAreaReward']").eq(0).val(calcStr).change();

    },
    //货币补偿补助公式
    calcRmbCompensate: function () {
        var compensateType = $("input[name='compensateType']:checked").val();
        //产权调换的，则让人工填写
        if (compensateType == 1) {
            $("input[name='calcRmbCompensate']").eq(0).val("").change();
            return;
        }

        //有证计算公式不为空时，进行计算
        var calcValueCompensate = $("input[name='calcValueCompensate']").eq(0).val() || 0;
        if (eval(calcValueCompensate) == 0) {
            $("input[name='calcRmbCompensate']").eq(0).val("").change();
            return;
        }

        //比例
        var proportion = $("input[name='rmbCompensateProportion']").eq(0).val() || 0;

        //开始拼接公式
        var calcRmbCompensate = settleAccountObj.extReward(proportion);

        console.log("货币补偿补助公式：" + calcRmbCompensate);
        $("input[name='calcRmbCompensate']").eq(0).val(calcRmbCompensate).change();
    },
    //根据产权交换的单价、面积， 拼计算公式
    calcSwapMoney: function () {
        var swap_price1 = $("input[name='swapPrice1']").eq(0).val() || 0;
        var swap_price2 = $("input[name='swapPrice2']").eq(0).val() || 0;
        var swap_price3 = $("input[name='swapPrice3']").eq(0).val() || 0;
      /*  var swap_price4 = $("input[name='swapPrice4']").eq(0).val() || 0;
        var swap_price5 = $("input[name='swapPrice5']").eq(0).val() || 0;*/

        var swap_area1 = $("input[name='swapArea1']").eq(0).val() || 0;
        var swap_area2 = $("input[name='swapArea2']").eq(0).val() || 0;
        var swap_area3 = $("input[name='swapArea3']").eq(0).val() || 0;
       /* var swap_area4 = $("input[name='swapArea4']").eq(0).val() || 0;
        var swap_area5 = $("input[name='swapArea5']").eq(0).val() || 0;*/

        if (swap_price1 != 0 && swap_area1 != 0) {
            var swap_money1 = new Number(eval(swap_price1)) * new Number(swap_area1);
            // $("input[name='swapMoney1']").eq(0).val(swap_money1).change();
            swap_money1 = calcObj.roundLastNum(swap_money1);
            $("input[name='swapMoney1']").eq(0).val(swap_money1).change();
        } else {
            $("input[name='swapMoney1']").eq(0).val("").change();
        }

        if (swap_price2 != 0 && swap_area2 != 0) {
            var swap_money2 = new Number(eval(swap_price2)) * new Number(swap_area2);
            swap_money2 = calcObj.roundLastNum(swap_money2);
            $("input[name='swapMoney2']").eq(0).val(swap_money2).change();
        } else {
            $("input[name='swapMoney2']").eq(0).val("").change();
        }

        if (swap_price3 != 0 && swap_area3 != 0) {
            var swap_money3 = new Number(eval(swap_price3)) * new Number(swap_area3);
            swap_money3 = calcObj.roundLastNum(swap_money3);
            $("input[name='swapMoney3']").eq(0).val(swap_money3).change();
        } else {
            $("input[name='swapMoney3']").eq(0).val("").change();
        }

      /*  if (swap_price4 != 0 && swap_area4 != 0) {
            var swap_money4 = new Number(swap_price4) * new Number(swap_area4);
            $("input[name='swapMoney4']").eq(0).val(swap_money4).change();
        } else {
            $("input[name='swapMoney4']").eq(0).val("").change();
        }

        if (swap_price5 != 0 && swap_area5 != 0) {
            var swap_money5 = new Number(swap_price5) * new Number(swap_area5);
            $("input[name='swapMoney5']").eq(0).val(swap_money5).change();
        } else {
            $("input[name='swapMoney5']").eq(0).val("").change();
        }*/

    },
    //产权交换计算公式执行
    calcHouseMoney: function () {
        var swap_money1 = $("input[name='swapMoney1']").eq(0).val() || 0;
        var swap_money2 = $("input[name='swapMoney2']").eq(0).val() || 0;
        var swap_money3 = $("input[name='swapMoney3']").eq(0).val() || 0;
        var swap_money4 = $("input[name='swapMoney4']").eq(0).val() || 0;
        var swap_money5 = $("input[name='swapMoney5']").eq(0).val() || 0;

        var houseMoney = calcObj.roundLastNum(eval(swap_money1)) + calcObj.roundLastNum(eval(swap_money2))
            + calcObj.roundLastNum(eval(swap_money3)) + calcObj.roundLastNum(eval(swap_money4))
            + calcObj.roundLastNum(eval(swap_money5));
        houseMoney = calcObj.roundLastNum(houseMoney);
        $("input[name='houseMoney']").eq(0).val(houseMoney).change();

    },
    //将有证、未经登记、2项面积求和，赋值：装修补偿面积、临时安置面积
    fullDecorationArea: function () {
        var calcValueCompensateArea = $("input[name='calcValueCompensateArea']").eq(0).val() || 0;
        var calcNoRegisterLegalArea = $("input[name='calcNoRegisterLegalArea']").eq(0).val() || 0;
        /*var calcHistoryLegacyArea = $("input[name='calcHistoryLegacyArea']").eq(0).val() || 0;
        var calcHistoryLegacyProportion = $("input[name='calcHistoryLegacyProportion']").eq(0).val() || 1;
        var sumArea = new Number(calcValueCompensateArea) + new Number(calcNoRegisterLegalArea) + (new Number(calcHistoryLegacyArea) * new Number(calcHistoryLegacyProportion));*/
        var sumArea = new Number(calcValueCompensateArea) + new Number(calcNoRegisterLegalArea);
        //将计算后得面积保留2位小数  兼容武船二分厂，3位需求
        sumArea = Math.round(sumArea*1000)/1000;
        console.log("西城壕装修补偿、临时安置面积" + sumArea);
        $("input[name='calcDecorationCompensateArea']").eq(0).val(sumArea).change();
        //2020-11-07 面积小于60平时，临时安置面积按60平计算
        if (sumArea < 60) {
            $("input[name='calcInterimFeeArea']").eq(0).val(60).change();
        } else {
            $("input[name='calcInterimFeeArea']").eq(0).val(sumArea).change();
        }
    },
    //根据面积、特殊计算的奖励
    extReward: function (proportion) {
        var calcStr = "";

        //金额
        var valueCompensate = $("input[name='valueCompensate']").eq(0).val() || 0;
        var noRegisterLegal = $("input[name='noRegisterLegal']").eq(0).val() || 0;
        if (valueCompensate == 0 && noRegisterLegal == 0) {
            return calcStr;
        }

        //面积
        var calcValueCompensateArea = $("input[name='calcValueCompensateArea']").eq(0).val() || 0;
        var calcNoRegisterLegalArea = $("input[name='calcNoRegisterLegalArea']").eq(0).val() || 0;

        //单价
        var calcValueCompensatePrice = $("input[name='calcValueCompensatePrice']").eq(0).val() || 0;
        var calcNoRegisterLegalPrice = $("input[name='calcNoRegisterLegalPrice']").eq(0).val() || 0;

        //其他公式
        var calcValueCompensateOther = $("input[name='calcValueCompensateOther']").eq(0).val() || 0;
        var calcNoRegisterLegalOther = $("input[name='calcNoRegisterLegalOther']").eq(0).val() || 0;

        //完整公式
        var calcValueCompensate = $("input[name='calcValueCompensate']").eq(0).val();
        var calcNoRegisterLegal = $("input[name='calcNoRegisterLegal']").eq(0).val();

        //存在2个公式
        if (valueCompensate > 0 && noRegisterLegal > 0) {
            //不存在其他公式
            if (calcValueCompensateOther == 0 && calcNoRegisterLegalOther == 0) {
                //2个面积和，小于40，则40*有证单价*比例;  反之，和大于40，则（有证面积*单价+未登记面积*单价）*比例
                var sumArea = new Number(calcValueCompensateArea) + new Number(calcNoRegisterLegalArea);
                sumArea = Math.round(sumArea * 1000) / 1000;
                if (sumArea < 40) {
                    calcStr = "40*" + calcValueCompensatePrice + "*" + proportion;
                } else {
                    calcStr = "(" + calcValueCompensateArea + "*" + calcValueCompensatePrice + "+" + calcNoRegisterLegalArea + "*" + calcNoRegisterLegalPrice + ")*" + proportion;
                }
            } else {
                //有其他公式，则使用2个公式和*比例
                calcStr = "(" + calcValueCompensate + "+" + calcNoRegisterLegal + ")*" + proportion;
            }
        } else if (valueCompensate > 0) {
            //只有 有证
            if (calcValueCompensateOther != 0) {
                //有证存在其他公式
                calcStr = "(" + calcValueCompensate + ")*" + proportion;
            } else {
                //有证是默认公式
                var areaNum = new Number(calcValueCompensateArea);
                if (areaNum < 40) {
                    areaNum = 40;
                }
                calcStr = "(" + areaNum + "*" + calcValueCompensatePrice + ")*" + proportion;
            }
        } else if (noRegisterLegal > 0) {
            //只有 未登记
            if (calcNoRegisterLegalOther != 0) {
                //未登记存在其他公式
                calcStr = "(" + calcNoRegisterLegal + ")*" + proportion;
            } else {
                //未登记是默认公式
                var areaNum = new Number(calcNoRegisterLegalArea);
                if (areaNum < 40) {
                    areaNum = 40;
                }
                calcStr = "(" + areaNum + "*" + calcNoRegisterLegalPrice + ")*" + proportion;
            }
        }

        return calcStr;

    }


};


var calcObj = {

    roundLastNum: function (num) {
        var projectCode = $("#projectCode").val();
        //文昌门保留2位小数
        if (projectCode == "B001007") {
            return funMath(num, 2, "round");
        } else {
            //其它片区，整数
            return funMath(num, 0, "round")
        }
    },
    //得到总的被征收房屋合计
    getSumCompensate: function (num) {
        var projectCode = $("#projectCode").val();
        if (projectCode == "B001007") {
            //文昌门：单项金额保留2位小数，求和后，要四舍五入保留整数
            return funMath(num, 0, "round");
        } else {
            //其它片区是整数，求和后，还是整数无需操作
            return num;
        }
    },
    //得到应收应付
    getPayTotal: function (num) {
        var projectCode = $("#projectCode").val();
        if (projectCode == "B001007") {
            //文昌门：带小数位的相减，结果保留2位小数
            return funMath(num, 2, "round");
        } else {
            //其它片区是整数，相减后，还是整数无需操作
            return num;
        }
    }

}