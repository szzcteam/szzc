$(document).ready(function(){

    //房屋评估单价失去焦点
    $("input[name='assessPrice']").eq(0).blur(function () {
        settleAccountObj.changePriceArea();
    });

    //建筑面积失去焦点
    $("input[name='checkInArea']").eq(0).blur(function () {
        settleAccountObj.changePriceArea();
    });

    //房屋价值补偿失去焦点
    $("input[name='calcValueCompensate']").eq(0).blur(function () {
        settleAccountObj.changeCalcValueCompensate();
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
        $("input[name='valueCompensate']").eq(0).val(valueCompensate)
    },

    //改变房屋价值补偿计算公式
    changeCalcValueCompensate:function () {
        var calcValueCompensate = $("input[name='calcValueCompensate']").eq(0).val();
        var valueCompensate = eval(calcValueCompensate);
        $("input[name='valueCompensate']").eq(0).val(valueCompensate)
    }

};