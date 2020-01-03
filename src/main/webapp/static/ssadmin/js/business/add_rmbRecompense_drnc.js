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

 /*   //生活困难小计
    $("#rmbRecompenseDiv input[name='basicLivingSubsidy'],#rmbRecompenseDiv input[name='disabilitySubsidy'],#rmbRecompenseDiv input[name='diseaseSubsidy']," +
        "#rmbRecompenseDiv input[name='noChild'],#rmbRecompenseDiv input[name='martyr']").on("blur change", function () {
        var basicLivingSubsidy = $("#rmbRecompenseDiv input[name='basicLivingSubsidy']").eq(0).val();
        var disabilitySubsidy = $("#rmbRecompenseDiv input[name='disabilitySubsidy']").eq(0).val();
        var diseaseSubsidy = $("#rmbRecompenseDiv input[name='diseaseSubsidy']").eq(0).val();
        var noChild = $("#rmbRecompenseDiv input[name='noChild']").eq(0).val();
        var martyr = $("#rmbRecompenseDiv input[name='martyr']").eq(0).val();
        var calc_lifeCompensate = basicLivingSubsidy + "+" + disabilitySubsidy + "+" + diseaseSubsidy + "+" + noChild + "+" + martyr;
        $("#rmbRecompenseDiv input[name='lifeCompensate']").eq(0).val(eval(calc_lifeCompensate)).change();
    });*/

    //附属设施项，失去焦点时，触发求和事件
    $("#rmbRecompenseDiv input[name='moveWaterMeterFee'],#rmbRecompenseDiv input[name='moveAmmeterFee'],#rmbRecompenseDiv input[name='moveAirConditioningFee'],#rmbRecompenseDiv input[name='hotWaterCompensate'],#rmbRecompenseDiv input[name='gasFee'], #rmbRecompenseDiv input[name='structureCompensate']").on("blur change", function () {
        rmbRecompenseObj.calcSubtotal();
    });



    //13项之和，转大写
    $("#rmbRecompenseDiv input[name='sumRbm']").eq(0).on("blur change", function () {
        rmbRecompenseObj.sumRmbToUpper();
    });

    //申购新房差额转大写
    $("#rmbRecompenseDiv input[name='difference']").eq(0).on("blur change", function () {
        var rmb = $(this).val() || 0;
        var upperDifference = Araia_To_Chinese(rmb);
        $("#rmbRecompenseDiv input[name='upperDifference']").eq(0).val(upperDifference);
    });

    //补偿款项绑定事件
    var compensateItem = new Array();
    compensateItem[0]= "valueCompensate";
    compensateItem[1]= "decorationCompensate";
    compensateItem[2]= "subtotal";
    compensateItem[3]= "moveHouseFee";
    compensateItem[4]= "interimPrice";
    compensateItem[5]= "interimMonth";
    compensateItem[6]= "interimFee";
    compensateItem[7]= "suspendBusinessFee";
    compensateItem[8]= "rmbCompensate";
    compensateItem[9]= "lifeCompensate";
    compensateItem[10]= "changeCompensate";
    compensateItem[11]= "moveReward";
    compensateItem[12]= "structureBalcony";
    compensateItem[13]= "noRegisterLegal";
    compensateItem[14]= "otherFee";
    for(var i=0;i<compensateItem.length;i++){
        var nameText = compensateItem[i];
        $("#rmbRecompenseDiv input[name='"+nameText+"']").eq(0).on("blur change", function () {
            rmbRecompenseObj.summaryNum();
        });
    }

    //其他约定，切换显示
    $("#rmbRecompenseDiv input[name='isTradeHouse']").on("change", function () {
        var isTradeHouse = $("#rmbRecompenseDiv input[name='isTradeHouse']:checked").val();
        console.log("是否购买新房: " + isTradeHouse);
        if(isTradeHouse == "true"){
            $("#rmbRecompenseDiv #buyHouse").css("display","inline");
            $("#rmbRecompenseDiv #noBuyHouse").css("display","none");
        }else if(isTradeHouse == "false"){
            $("#rmbRecompenseDiv #buyHouse").css("display","none");
            $("#rmbRecompenseDiv #noBuyHouse").css("display","inline");
        }
    });

    //修改时，进入初始化页面有值
    var initIsBuy = $("#rmbRecompenseDiv input[name='isTradeHouse']:checked").val();
    if(initIsBuy != undefined){
        if(initIsBuy){
            $("#rmbRecompenseDiv input[name='isTradeHouse']").eq(0).change();
        }else if(!initIsBuy){
            $("#rmbRecompenseDiv input[name='isTradeHouse']").eq(1).change();
        }
    }


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
            data: {"houseOwner": houseName, "address": address, "businessFlag": 2},
            dataType: "json",
            success: function (data) {
                if (data == null) {
                    console.log("查询结算单信息为空，姓名:" + houseName);
                    return;
                }
                alertMsg.confirm("检测到 "+houseName + ",地址: "+address+" 有【结算单】，是否使用结算单数据进行填充？", {
                    okCall: function(){
                        //其它公式，特殊提醒
                        var noticeFlag = false;
                        var noticeMsg = "";
                        //编号
                        $("#rmbRecompenseDiv input[name='cardNo']").eq(0).val(data.cardNo);
                        //承租人标识
                        if (data.houseOwner != null && data.houseOwner != "") {
                            $("#rmbRecompenseDiv input[name='isLesseeFlag']").eq(0).val(false);
                        } else if (data.lessee != null && data.lessee != "") {
                            $("#rmbRecompenseDiv input[name='isLesseeFlag']").eq(0).val(true);
                        }
                        //片区
                        $("#rmbRecompenseDiv select[name='areaId']").eq(0).val(data.areaId);
                        //被征收房屋地址
                        $("#rmbRecompenseDiv input[name='address']").eq(0).val(data.address);
                        //证载建筑面积
                        $("#rmbRecompenseDiv input[name='certifiedArea']").eq(0).val(data.certifiedArea);
                        //证载房屋用途
                        $("#rmbRecompenseDiv input[name='useing']").eq(0).val(data.useing);
                        //房屋价值评估单价
                        var calcValueCompensateArr = data.calcValueCompensate.split("*");
                        $("#rmbRecompenseDiv input[name='assessPrice']").eq(0).val(calcValueCompensateArr[1]);
                        //证载补偿比例
                        var valueProportion = calcValueCompensateArr[2];
                        if(valueProportion != undefined){
                            valueProportion = new Number(valueProportion) * 100;
                            $("#rmbRecompenseDiv input[name='proportion']").eq(0).val(valueProportion);
                        }else{
                            $("#rmbRecompenseDiv input[name='proportion']").eq(0).val("100");
                        }

                        //用于经营的实际面积  == 对应结算单第8条的，住改商面积
                        if(data.changeCompensate != null && data.changeCompensate > 0 ){
                            var calcChangeCompensate = data.calcChangeCompensate.split("*");
                            $("#rmbRecompenseDiv input[name='valueCompensateBusinessArea']").eq(0).val(calcChangeCompensate[0]);
                            /*$("#rmbRecompenseDiv input[name='valueCompensateRate']").eq(0).val(eval(calcChangeCompensate[2]*100));*/
                        }

                        //未登记的合法建筑面积:电二公司=未登记+遗留的
                        var noRegisterLegalArea = 0;
                        if(data.noRegisterLegalArea > 0){
                            noRegisterLegalArea += data.noRegisterLegalArea;
                        }
                        if(data.historyLegacyArea > 0){
                            noRegisterLegalArea += data.historyLegacyArea;
                        }
                        $("#rmbRecompenseDiv input[name='noRegisterLegalArea']").eq(0).val(noRegisterLegalArea);
                        //评估单价
                       /* if(data.noRegisterLegal != null && data.noRegisterLegal >0){
                            var calcNoRegisterLegal = data.calcNoRegisterLegal.split("*");
                            var price = calcNoRegisterLegal[1];
                            if (isNaN(price)) {
                                noticeFlag = true;
                                noticeMsg = noticeMsg + "<br/>未登记建筑补偿有独立公式计算";
                                price = price.substring(0, price.indexOf("+"));
                            }
                            $("#rmbRecompenseDiv input[name='noRegisterAssessPrice']").eq(0).val(price);
                            //补偿比例
                            var proportion = calcNoRegisterLegal[2];
                            if(proportion != undefined){
                                proportion = new Number(proportion) * 100;
                                $("#rmbRecompenseDiv input[name='noRegisterProportion']").eq(0).val(proportion);
                            }
                        }*/


                        //历史遗留建筑面积
                       /* $("#rmbRecompenseDiv input[name='historyLegacyArea']").eq(0).val(data.historyLegacyArea);
                        //评估单价
                        if(data.historyLegacy != null && data.historyLegacy >0){
                            var calcHistoryLegacy = data.calcHistoryLegacy.split("*");
                            var price = calcHistoryLegacy[1];
                            if (isNaN(price)) {
                                noticeFlag = true;
                                noticeMsg = noticeMsg + "<br/>历史遗留建筑补偿有独立公式计算";
                                price = price.substring(0, price.indexOf("+"));
                            }
                            $("#rmbRecompenseDiv input[name='historyAssessPrice']").eq(0).val(price);
                            //补偿比例
                            var proportion = calcHistoryLegacy[2];
                            if(proportion != undefined){
                                proportion = new Number(proportion) * 100;
                                $("#rmbRecompenseDiv input[name='historyProportion']").eq(0).val(proportion);
                            }
                        }*/


                        //有证房屋补偿金额
                        $("#rmbRecompenseDiv input[name='valueCompensate']").eq(0).val(data.valueCompensate);

                        //未登记补偿金额：电二公司=未登记金额+遗留金额
                        var noRegisterLegal = 0;
                        if(data.noRegisterLegal > 0 ){
                            noRegisterLegal += data.noRegisterLegal;
                        }
                        if(data.historyLegacy > 0 ){
                            noRegisterLegal += data.historyLegacy;
                        }
                        $("#rmbRecompenseDiv input[name='noRegisterLegal']").eq(0).val(noRegisterLegal);
                        /*$("#rmbRecompenseDiv input[name='historyLegacy']").eq(0).val(data.historyLegacy);*/

                        //室内装修补偿
                        if(data.decorationCompensate != null && data.decorationCompensate > 0){
                           /* var calcDecorationCompensate = data.calcDecorationCompensate.split("*");
                            //默认最后一个乘以的就是单价
                            var price = calcDecorationCompensate[calcDecorationCompensate.length-1];
                            console.log("装修补偿单价:"+price + "  ，公式:" + data.calcDecorationCompensate);
                            if(isNaN(price)){
                                noticeFlag = true;
                                noticeMsg = noticeMsg + "<br/>室内装修补偿有独立公式计算";
                                //有其它公式, 格式：10*10+其它公式
                                var otherCalc = price.substring(price.indexOf("+")+1, price.length);
                                var otherCalcArr = otherCalc.split("*");
                                $("#rmbRecompenseDiv input[name='decorationCompensateUnitPrice']").eq(0).val(otherCalcArr[otherCalcArr.length - 1]);
                            }else{
                                //是个数字，没有其它公式
                                $("#rmbRecompenseDiv input[name='decorationCompensateUnitPrice']").eq(0).val(price);
                            }*/
                            $("#rmbRecompenseDiv input[name='decorationCompensate']").eq(0).val(data.decorationCompensate)
                        }
                        ;
                        //搬家费
                        $("#rmbRecompenseDiv input[name='moveHouseFee']").eq(0).val(data.moveHouseFee);
                        //临时安置补偿(过渡费)
                        var calcInterimFee = data.calcInterimFee;
                        var calcInterimFeeArr = calcInterimFee.split("*");
                        // $("#rmbRecompenseDiv input[name='interimArea']").eq(0).val(calcInterimFeeArr[0]);
                        $("#rmbRecompenseDiv input[name='interimPrice']").eq(0).val(calcInterimFeeArr[1]);
                        $("#rmbRecompenseDiv input[name='interimMonth']").eq(0).val(calcInterimFeeArr[2]);
                        $("#rmbRecompenseDiv input[name='interimFee']").eq(0).val(data.interimFee);

                        //保底
                        //$("#rmbRecompenseDiv input[name='guarantee']").eq(0).val(data.guarantee);

                        //停产停业损失补偿
                        $("#rmbRecompenseDiv input[name='suspendBusinessFee']").eq(0).val(data.suspendBusinessFee);

                        //附属设施开始
                        //水表迁移费
                        $("#rmbRecompenseDiv input[name='moveWaterMeterFee']").eq(0).val(data.moveWaterMeterFee);
                        //电表迁移费
                        $("#rmbRecompenseDiv input[name='moveAmmeterFee']").eq(0).val(data.moveAmmeterFee);
                        //空调移机费
                        $("#rmbRecompenseDiv input[name='moveAirConditioningFee']").eq(0).val(data.moveAirConditioningFee);
                        //热水器拆装补偿
                        $("#rmbRecompenseDiv input[name='hotWaterCompensate']").eq(0).val(data.hotWaterCompensate);
                        if(data.hotWaterCompensate != null && data.hotWaterCompensate > 0){
                            var hot_water_index = data.calcHotWaterCompensate.indexOf("+");
                            //太阳能热水器
                            var hot_d_calc = data.calcHotWaterCompensate.substring(hot_water_index+1, data.calcHotWaterCompensate.length);
                            $("#rmbRecompenseDiv input[name='solarHeater']").eq(0).val(eval(hot_d_calc));
                            //其他  电热水器
                            var hot_value = data.calcHotWaterCompensate.substring(0, hot_water_index);
                            $("#rmbRecompenseDiv input[name='otherHeater']").eq(0).val(eval(hot_value));
                        }

                        //管道煤气拆装补偿
                        $("#rmbRecompenseDiv input[name='gasFee']").eq(0).val(data.gasFee);
                        //构建物
                        $("#rmbRecompenseDiv input[name='structureBalcony']").eq(0).val(data.structureBalcony);
                        $("#rmbRecompenseDiv input[name='structureBuild']").eq(0).val(data.structureBuild);
                        $("#rmbRecompenseDiv input[name='structureDark']").eq(0).val(data.structureDark);
                        $("#rmbRecompenseDiv input[name='structureMezzanine']").eq(0).val(data.structureMezzanine);
                        $("#rmbRecompenseDiv input[name='structureRoof']").eq(0).val(data.structureRoof);
                        // $("#rmbRecompenseDiv input[name='affiliatedOther']").eq(0).val(data.affiliatedOther);

                        //构建物补偿金额 = 除阳台外的其他几项
                        var structureCompensate = "0";
                        if (data.structureBuild > 0) {
                            structureCompensate += "+" + data.structureBuild;
                        }
                        if (data.structureDark > 0) {
                            structureCompensate += "+" + data.structureDark;
                        }
                        if (data.structureMezzanine > 0) {
                            structureCompensate += "+" + data.structureMezzanine;
                        }
                        if (data.structureRoof > 0) {
                            structureCompensate += "+" + data.structureRoof;
                        }
                        console.log("构建物补偿: " + structureCompensate);
                        structureCompensate = eval(structureCompensate);
                        $("#rmbRecompenseDiv input[name='structureCompensate']").eq(0).val(structureCompensate);
                        //附属设施结束，触发一次小计求和
                        //TODO
                        // $("#rmbRecompenseDiv input[name='moveWaterMeterFee']").eq(0).change();

                        //住改商补助
                        $("#rmbRecompenseDiv input[name='changeCompensate']").eq(0).val(data.changeCompensate);
                        //生活困难补助
                        if(data.lifeCompensate != null && data.lifeCompensate > 0){
                            $("#rmbRecompenseDiv input[name='lifeCompensate']").eq(0).val(data.lifeCompensate);
                            var lifeCompensateArr = data.calcLifeCompensate.split("+");
                            $("#rmbRecompenseDiv input[name='diseaseSubsidy']").val(lifeCompensateArr[0]);
                            $("#rmbRecompenseDiv input[name='disabilitySubsidy']").val(lifeCompensateArr[1]);
                            $("#rmbRecompenseDiv input[name='basicLivingSubsidy']").val(lifeCompensateArr[2])
                            $("#rmbRecompenseDiv input[name='martyr']").val(lifeCompensateArr[3]);
                            $("#rmbRecompenseDiv input[name='noChild']").val(lifeCompensateArr[4]);
                            //生活困难求和
                            $("#rmbRecompenseDiv input[name='lifeCompensate']").eq(0).val(data.lifeCompensate);
                        }

                        //货币补偿补助
                        $("#rmbRecompenseDiv input[name='rmbCompensate']").eq(0).val(data.rmbCompensate);
                        //小户型困难补助
                        // $("#rmbRecompenseDiv input[name='smallAreaReward']").eq(0).val(data.smallAreaReward);
                        //按期签约搬迁奖励
                        $("#rmbRecompenseDiv input[name='moveReward']").eq(0).val(data.moveReward);
                        //其他
                        // $("#rmbRecompenseDiv input[name='otherFee']").eq(0).val(data.otherRmb);
                        //其他 = 保底+小面积补助+历史遗留+其他
                        var otherFee = 0;
                        var otherFeeDetail = "";
                        if(data.guarantee > 0){
                            otherFee += data.guarantee;
                            otherFeeDetail += "保底:" + data.guarantee + "  ";
                        }
                        if(data.smallAreaReward > 0){
                            otherFee += data.smallAreaReward;
                            otherFeeDetail += "小户型:" + data.smallAreaReward + "  ";
                        }

                        if(data.otherRmb > 0){
                            otherFee += data.otherRmb;
                            otherFeeDetail += data.otherDesc + ":" + data.otherRmb + "  ";
                        }
                        if(data.buildingAreaFee > 0 ){
                            otherFee += data.buildingAreaFee;
                            otherFeeDetail += "建筑面积补助:" + data.buildingAreaFee + "  ";
                        }


                        otherFeeDetail = otherFeeDetail.trim();
                        $("#rmbRecompenseDiv input[name='otherFee']").eq(0).val(otherFee);
                        $("#rmbRecompenseDiv input[name='otherFeeDetail']").eq(0).val(otherFeeDetail);

                        rmbRecompenseObj.calcSubtotal();

                        //触发所有条款求和
                        rmbRecompenseObj.summaryNum();

                        //决字信息
                        if (data.adjudication != null) {
                            $("#rmbRecompenseDiv input[name='govYear']").eq(0).val(data.adjudication.govYear);
                            $("#rmbRecompenseDiv input[name='govMonth']").eq(0).val(data.adjudication.govMonth);
                            $("#rmbRecompenseDiv input[name='govDay']").eq(0).val(data.adjudication.govDay);
                            $("#rmbRecompenseDiv input[name='adjuLetter']").eq(0).val(data.adjudication.adjuLetter);
                            $("#rmbRecompenseDiv input[name='adjuNum']").eq(0).val(data.adjudication.adjuNum);
                            $("#rmbRecompenseDiv input[name='noticeYear']").eq(0).val(data.adjudication.noticeYear);
                            $("#rmbRecompenseDiv input[name='noticeMonth']").eq(0).val(data.adjudication.noticeMonth);
                            $("#rmbRecompenseDiv input[name='noticeDay']").eq(0).val(data.adjudication.noticeDay);
                        }


                        //新房面积
                        if(data.deduction > 0 ){
                            $("#rmbRecompenseDiv input[name='isTradeHouse']").eq(0).prop("checked", "checked").change();
                        }else{
                            $("#rmbRecompenseDiv input[name='isTradeHouse']").eq(1).prop("checked", "checked").change();
                        }

                        var coveredArea = "";
                        if (data.swapArea1 > 0) {
                            coveredArea += data.swapArea1 + ",";
                        }
                        if (data.swapArea2 > 0) {
                            coveredArea += data.swapArea2 + ",";
                        }
                        if (data.swapArea3 > 0) {
                            coveredArea += data.swapArea3 + ",";
                        }
                        //去掉最后一个逗号
                        if (coveredArea.substring(coveredArea.length - 1, coveredArea.length) == ",") {
                            coveredArea = coveredArea.substring(0, coveredArea.length - 1);
                        }
                        $("#rmbRecompenseDiv input[name='coveredArea']").eq(0).val(coveredArea);

                        //新房抵扣安置房款
                        if(data.deduction > 0){
                            $("#rmbRecompenseDiv input[name='transferRmb']").eq(0).val(data.deduction);
                        }
                        //应付  旧房金额>新房金额
                        console.log("旧房金额: " + data.sumCompensate + "  新房金额" +data.houseMoney );
                        $("#rmbRecompenseDiv input[name='difference']").eq(0).val(data.payTotal).blur();

                        noticeMsg = noticeMsg + "<br/>请人工核对!";
                        if(noticeFlag){
                            alertMsg.warn(noticeMsg);
                        }

                    }
                });
            }
        });


    },
    //统计附属设施小计
    calcSubtotal:function(){
        var moveWaterMeterFee = $("#rmbRecompenseDiv input[name='moveWaterMeterFee']").eq(0).val() || 0;
        var moveAmmeterFee = $("#rmbRecompenseDiv input[name='moveAmmeterFee']").eq(0).val() || 0;
        var moveAirConditioningFee = $("#rmbRecompenseDiv input[name='moveAirConditioningFee']").eq(0).val() || 0;
        var hotWaterCompensate = $("#rmbRecompenseDiv input[name='hotWaterCompensate']").eq(0).val() || 0;
        var gasFee = $("#rmbRecompenseDiv input[name='gasFee']").eq(0).val() || 0;
        var structureCompensate = $("#rmbRecompenseDiv input[name='structureCompensate']").eq(0).val() || 0;
        var subtotal = moveWaterMeterFee + "+" + moveAmmeterFee + "+" + moveAirConditioningFee +
            "+" + hotWaterCompensate + "+" + gasFee + "+" + structureCompensate;
        console.log("统计附属设施小计: " + subtotal);
        $("#rmbRecompenseDiv input[name='subtotal']").eq(0).val(eval(subtotal));

    },

    //统计13项，人民币之和转大写
    sumRmbToUpper:function () {
        var rmb = $("#rmbRecompenseDiv input[name='sumRbm']").eq(0).val() || 0;
        var rmb_upper = Araia_To_Chinese(rmb);
        $("#rmbRecompenseDiv input[name='upperRmb']").eq(0).val(rmb_upper);
    },

    //所有条款求和
    summaryNum:function () {

        var valueCompensate = $("#rmbRecompenseDiv  input[name='valueCompensate']").eq(0).val() || 0;
        var noRegisterLegal = $("#rmbRecompenseDiv  input[name='noRegisterLegal']").eq(0).val() || 0;
        var historyLegacy = $("#rmbRecompenseDiv  input[name='historyLegacy']").eq(0).val() || 0;
        var decorationCompensate = $("#rmbRecompenseDiv  input[name='decorationCompensate']").eq(0).val() || 0;
        var moveHouseFee = $("#rmbRecompenseDiv  input[name='moveHouseFee']").eq(0).val() || 0;
        var interimFee = $("#rmbRecompenseDiv  input[name='interimFee']").eq(0).val() || 0;
        var guarantee = $("#rmbRecompenseDiv  input[name='guarantee']").eq(0).val() || 0;
        var suspendBusinessFee = $("#rmbRecompenseDiv  input[name='suspendBusinessFee']").eq(0).val() || 0;
        //附属设施，求和了
        var subtotal = $("#rmbRecompenseDiv  input[name='subtotal']").eq(0).val() || 0;
        var changeCompensate = $("#rmbRecompenseDiv  input[name='changeCompensate']").eq(0).val() || 0;
        //生活困难补助求和了
        var lifeCompensate = $("#rmbRecompenseDiv  input[name='lifeCompensate']").eq(0).val() || 0;
        var rmbCompensate = $("#rmbRecompenseDiv  input[name='rmbCompensate']").eq(0).val() || 0;
        var smallAreaReward = $("#rmbRecompenseDiv  input[name='smallAreaReward']").eq(0).val() || 0;
        var moveReward = $("#rmbRecompenseDiv  input[name='moveReward']").eq(0).val() || 0;

        //封闭阳台
        var structureBalcony = $("#rmbRecompenseDiv  input[name='structureBalcony']").eq(0).val() || 0;
        var otherFee = $("#rmbRecompenseDiv  input[name='otherFee']").eq(0).val() || 0;

        var sumRbm_calc = valueCompensate + "+" + noRegisterLegal + "+" + historyLegacy + "+" + decorationCompensate + "+" +
            moveHouseFee + "+" + interimFee + "+" + guarantee + "+" + suspendBusinessFee + "+" + subtotal + "+" + changeCompensate + "+" +
            lifeCompensate + "+" + rmbCompensate + "+" + smallAreaReward + "+" + moveReward + "+" + otherFee + "+" + structureBalcony;
        var sumRbm = eval(sumRbm_calc);
        $("#rmbRecompenseDiv  input[name='sumRbm']").eq(0).val(sumRbm).change();
    }



}