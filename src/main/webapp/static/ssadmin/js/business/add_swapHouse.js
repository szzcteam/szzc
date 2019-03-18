$(document).ready(function () {

    //是3个页面在一起，所以区分用户名；  产权调换协议
    $("input[name='houseOwner']").eq(1).blur(function () {
        swapHouseObj.houseNameSync($(this).val(), 1);
    });

});


var swapHouseObj = {

    houseNameSync: function (houseName, swapIndex) {
        if(!houseName){
            return;
        }

        //ajax请求结算单数据，进行协议填充
        $.ajax({
            url: "ssadmin/settleAccounts/detail.html",
            type: "post",
            data: {"houseOwner": houseName},
            dataType: "json",
            success: function (data) {
                console.log(data);
                if (data == null) {
                    console.log("查询结算单信息为空，姓名:" + houseName);
                    return;
                }
                alertMsg.confirm("检测到 "+houseName + " 有【结算单】，是否使用结算单数据进行填充？", {
                    okCall: function(){
                        //被征收房屋地址
                        $("input[name='address']").eq(swapIndex).val(data.address);
                        //用途
                        $("input[name='useing']").eq(swapIndex).val(data.useing);
                        //建筑面积
                        $("input[name='checkInArea']").eq(swapIndex).val(data.checkInArea);
                        //评估机构评估的补偿金额
                        $("input[name='valueCompensate']").eq(swapIndex).val(data.valueCompensate);
                        //室内装修补偿
                        $("input[name='decorationCompensate']").eq(swapIndex).val(data.decorationCompensate);
                        //构建物
                        $("input[name='structureCompensate']").eq(swapIndex).val(data.structureCompensate);
                        //电表迁移费
                        $("input[name='moveAmmeterFee']").eq(swapIndex).val(data.moveAmmeterFee);
                        //水表迁移费
                        $("input[name='moveWaterMeterFee']").eq(swapIndex).val(data.moveWaterMeterFee);
                        //空调移机费
                        $("input[name='moveAirConditioningFee']").eq(swapIndex).val(data.moveAirConditioningFee);
                        //管道煤气拆装补偿
                        $("input[name='gasFee']").eq(swapIndex).val(data.gasFee);
                        //热水器拆装补偿
                        $("input[name='hotWaterCompensate']").eq(swapIndex).val(data.hotWaterCompensate);
                        //搬家费
                        $("input[name='moveHouseFee']").eq(swapIndex).val(data.moveHouseFee);
                        //临时安置补偿(过渡费)
                        $("input[name='interimFee']").eq(swapIndex).val(data.interimFee);
                        //停产停业损失补偿
                        $("input[name='suspendBusinessFee']").eq(swapIndex).val(data.suspendBusinessFee);
                        //生活困难补助
                        $("input[name='lifeCompensate']").eq(swapIndex).val(data.lifeCompensate);
                        //住改商补助
                        $("input[name='changeCompensate']").eq(swapIndex).val(data.changeCompensate);
                        //搬迁奖励
                        $("input[name='moveReward']").eq(swapIndex).val(data.moveReward);


                    }
                });
            }
        });


    }

}