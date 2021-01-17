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

    //生活困难小计
    $("#rmbRecompenseDiv input[name='basicLivingSubsidy'],#rmbRecompenseDiv input[name='disabilitySubsidy'],#rmbRecompenseDiv input[name='diseaseSubsidy']," +
        "#rmbRecompenseDiv input[name='noChild'],#rmbRecompenseDiv input[name='martyr']").on("blur change", function () {
        var basicLivingSubsidy = $("#rmbRecompenseDiv input[name='basicLivingSubsidy']").eq(0).val();
        var disabilitySubsidy = $("#rmbRecompenseDiv input[name='disabilitySubsidy']").eq(0).val();
        var diseaseSubsidy = $("#rmbRecompenseDiv input[name='diseaseSubsidy']").eq(0).val();
        var noChild = $("#rmbRecompenseDiv input[name='noChild']").eq(0).val();
        var martyr = $("#rmbRecompenseDiv input[name='martyr']").eq(0).val();
        var calc_lifeCompensate = basicLivingSubsidy + "+" + disabilitySubsidy + "+" + diseaseSubsidy + "+" + noChild + "+" + martyr;
        $("#rmbRecompenseDiv input[name='lifeCompensate']").eq(0).val(eval(calc_lifeCompensate)).change();
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
    compensateItem[12]= "smallAreaReward";
    compensateItem[13]= "moveReward";
    compensateItem[14]= "otherFee";
    for(var i=0;i<compensateItem.length;i++){
        var nameText = compensateItem[i];
        $("#rmbRecompenseDiv input[name='"+nameText+"']").eq(0).on("blur change", function () {
            rmbRecompenseObj.summaryNum();
        });
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
                console.log(data);
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
                        var valueCompensateTwoIndex = data.calcValueCompensate.indexOf("+");
                        if(valueCompensateTwoIndex != -1){
                            data.calcValueCompensate = data.calcValueCompensate.substr(0, valueCompensateTwoIndex)
                        }
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
                            //存在加号，就是2个公式，取第一个公式的比例即可
                            var addFlagIndex = data.calcChangeCompensate.indexOf("+");
                            if (addFlagIndex != -1) {
                                data.calcChangeCompensate = data.calcChangeCompensate.substring(0, addFlagIndex);
                                console.log("房屋改变用途补助存在其他公式，第一个公式是:" + data.calcChangeCompensate);
                            }
                            var calcChangeCompensate = data.calcChangeCompensate.split("*");
                            $("#rmbRecompenseDiv input[name='valueCompensateBusinessArea']").eq(0).val(calcChangeCompensate[0]);
                            $("#rmbRecompenseDiv input[name='valueCompensateRate']").eq(0).val(eval(calcChangeCompensate[2]*100));
                        }

                        //未登记的合法建筑面积
                        $("#rmbRecompenseDiv input[name='noRegisterLegalArea']").eq(0).val(data.noRegisterLegalArea);
                        //评估单价
                        if (data.noRegisterLegal != null && data.noRegisterLegal > 0) {
                            var noRegisterTwoIndex = data.calcNoRegisterLegal.indexOf("+");
                            if (noRegisterTwoIndex != -1) {
                                noticeFlag = true;
                                noticeMsg = noticeMsg + "<br/>未登记建筑补偿有独立公式计算";
                                data.calcNoRegisterLegal = data.calcNoRegisterLegal.substring(0, noRegisterTwoIndex);
                            }
                            var calcNoRegisterLegalArr = data.calcNoRegisterLegal.split("*");
                            var price = calcNoRegisterLegalArr[1];
                            $("#rmbRecompenseDiv input[name='noRegisterAssessPrice']").eq(0).val(price);

                            var noRegisterProp = calcNoRegisterLegalArr[2];
                            if (noRegisterProp != undefined) {
                                noRegisterProp = new Number(noRegisterProp) * 100;
                                $("#rmbRecompenseDiv input[name='noRegisterProportion']").eq(0).val(noRegisterProp);
                            } else {
                                $("#rmbRecompenseDiv input[name='noRegisterProportion']").eq(0).val("100");
                            }

                        }


                        //历史遗留建筑面积
                        $("#rmbRecompenseDiv input[name='historyLegacyArea']").eq(0).val(data.historyLegacyArea);
                        //评估单价
                        if (data.historyLegacy != null && data.historyLegacy > 0) {
                            var historyTwoIndex = data.calcHistoryLegacy.indexOf("+");
                            if (historyTwoIndex != -1) {
                                noticeFlag = true;
                                noticeMsg = noticeMsg + "<br/>历史遗留建筑补偿有独立公式计算";
                                data.calcHistoryLegacy = data.calcHistoryLegacy.substring(0, historyTwoIndex);
                            }

                            var calcHistoryLegacyArr = data.calcHistoryLegacy.split("*");
                            var price = calcHistoryLegacyArr[1];
                            $("#rmbRecompenseDiv input[name='historyAssessPrice']").eq(0).val(price);

                            //补偿比例
                            var historyProp = calcHistoryLegacyArr[2];
                            if (historyProp != undefined) {
                                historyProp = new Number(historyProp) * 100;
                                $("#rmbRecompenseDiv input[name='historyProportion']").eq(0).val(historyProp);
                            } else {
                                $("#rmbRecompenseDiv input[name='historyProportion']").eq(0).val("100");
                            }
                        }


                        //有证房屋补偿金额
                        $("#rmbRecompenseDiv input[name='valueCompensate']").eq(0).val(data.valueCompensate);
                        $("#rmbRecompenseDiv input[name='noRegisterLegal']").eq(0).val(data.noRegisterLegal);
                        $("#rmbRecompenseDiv input[name='historyLegacy']").eq(0).val(data.historyLegacy);

                        //室内装修补偿
                        if (data.decorationCompensate != null && data.decorationCompensate > 0) {
                            var decorationOtherFlag = data.calcDecorationCompensate.indexOf("+");
                            if (decorationOtherFlag != -1) {
                                //存在2个公式，取其中一个
                                data.calcDecorationCompensate = data.calcDecorationCompensate.substring(0, decorationOtherFlag);
                            }

                            var calcDecorationCompensate = data.calcDecorationCompensate.split("*");
                            //默认最后一个乘以的就是单价
                            var price = calcDecorationCompensate[calcDecorationCompensate.length - 1];
                            console.log("装修补偿单价:" + price + "  ，公式:" + data.calcDecorationCompensate);
                            $("#rmbRecompenseDiv input[name='decorationCompensateUnitPrice']").eq(0).val(price);
                            $("#rmbRecompenseDiv input[name='decorationCompensate']").eq(0).val(data.decorationCompensate)
                        }

                        //搬家费
                        $("#rmbRecompenseDiv input[name='moveHouseFee']").eq(0).val(data.moveHouseFee);
                        //临时安置补偿(过渡费)
                        if (data.interimFee != null && data.interimFee > 0) {
                            var interimFeeOtherFlag = data.calcInterimFee.indexOf("+");
                            if (interimFeeOtherFlag != -1) {
                                //存在2个公式，取其中一个
                                data.calcInterimFee = data.calcInterimFee.substring(0, interimFeeOtherFlag);
                            }
                            var calcInterimFee = data.calcInterimFee;
                            var calcInterimFeeArr = calcInterimFee.split("*");
                            $("#rmbRecompenseDiv input[name='interimArea']").eq(0).val(calcInterimFeeArr[0]);
                            $("#rmbRecompenseDiv input[name='interimPrice']").eq(0).val(calcInterimFeeArr[1]);
                            $("#rmbRecompenseDiv input[name='interimMonth']").eq(0).val(calcInterimFeeArr[2]);
                            $("#rmbRecompenseDiv input[name='interimFee']").eq(0).val(data.interimFee);
                        }

                        //保底
                        $("#rmbRecompenseDiv input[name='guarantee']").eq(0).val(data.guarantee);

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
                        if(data.hotWaterCompensate != null && data.hotWaterCompensate > 0){
                            var hot_water_index = data.calcHotWaterCompensate.indexOf("+");
                            //太阳能热水器
                            var hot_d_calc = data.calcHotWaterCompensate.substring(hot_water_index+1, data.calcHotWaterCompensate.length);
                            $("#rmbRecompenseDiv input[name='solarHeater']").eq(0).val(Math.round(eval(hot_d_calc)));
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
                        $("#rmbRecompenseDiv input[name='affiliatedOther']").eq(0).val(data.affiliatedOther);
                        //附属设施结束，触发一次小计求和
                        $("#rmbRecompenseDiv input[name='moveWaterMeterFee']").eq(0).change();

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
                        }

                        //货币补偿补助
                        $("#rmbRecompenseDiv input[name='rmbCompensate']").eq(0).val(data.rmbCompensate);
                        //小户型困难补助
                        $("#rmbRecompenseDiv input[name='smallAreaReward']").eq(0).val(data.smallAreaReward);
                        //按期签约搬迁奖励
                        $("#rmbRecompenseDiv input[name='moveReward']").eq(0).val(data.moveReward);
                        //其他
                        $("#rmbRecompenseDiv input[name='otherFee']").eq(0).val(data.otherRmb);

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

                        noticeMsg = noticeMsg + "<br/>请人工核对!";
                        if(noticeFlag){
                            alertMsg.warn(noticeMsg);
                        }

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
        var otherFee = $("#rmbRecompenseDiv  input[name='otherFee']").eq(0).val() || 0;

        var sumRbm_calc = valueCompensate + "+" + noRegisterLegal + "+" + historyLegacy + "+" + decorationCompensate + "+" +
            moveHouseFee + "+" + interimFee + "+" + guarantee + "+" + suspendBusinessFee + "+" + subtotal + "+" + changeCompensate + "+" +
            lifeCompensate + "+" + rmbCompensate + "+" + smallAreaReward + "+" + moveReward + "+" + otherFee;
        var sumRbm = eval(sumRbm_calc);
        sumRbm = Math.round(sumRbm);
        $("#rmbRecompenseDiv  input[name='sumRbm']").eq(0).val(sumRbm).change();
    }



}