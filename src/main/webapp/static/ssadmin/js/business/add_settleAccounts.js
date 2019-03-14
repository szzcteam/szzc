$(document).ready(function(){

    //房屋评估单价失去焦点
    $("input[name='assessPrice']").eq(0).blur(function () {
        settleAccountObj.changePriceArea();
    });

    //建筑面积失去焦点
    $("input[name='checkInArea']").eq(0).blur(function () {
        settleAccountObj.changePriceArea();
    });

    //房屋价值补偿计算公式失去焦点
    $("input[name='calcValueCompensate']").eq(0).blur(function () {
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

    $("input[type='text']").each(function (i, obj) {
        var name = $(obj).attr("name");
        for(var i=0;i<sumCompensateNameArr.length;i++) {
            if(name == sumCompensateNameArr[i]) {
                $("input[name='"+name+"']").eq(0).on("blur change", function () {
                    settleAccountObj.fullSumCompensate();
                });
            }
        }
    });

});


var settleAccountObj = {

    //改变房屋单价、建筑面积
    changePriceArea:function(){
        //房屋评估单价
        var assessPrice = $("input[name='assessPrice']").eq(0).val();
        //建筑面积
        var checkInArea = $("input[name='checkInArea']").eq(0).val();

        if(!assessPrice){
            return;
        }

        if(!checkInArea){
            return;
        }

        //房屋价值补偿
        var calcValueCompensate = checkInArea + "*" + assessPrice;
        $("input[name='calcValueCompensate']").eq(0).val(calcValueCompensate);
        var valueCompensate = eval(calcValueCompensate);
        $("input[name='valueCompensate']").eq(0).val(valueCompensate).change();
    },

    //改变房屋价值补偿计算公式
    changeCalcValueCompensate:function () {
        var calcValueCompensate = $("input[name='calcValueCompensate']").eq(0).val();
        var valueCompensate = eval(calcValueCompensate);
        $("input[name='valueCompensate']").eq(0).val(valueCompensate)
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
    }



};