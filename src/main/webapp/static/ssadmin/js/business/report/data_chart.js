
var chartOperation = {

    getPieData:function(){
        //获取片区
        var areaId = $("#areaId").val();
        var areaIdArr = new Array();
        if(areaId){
            areaIdArr.push(areaId);
        }else{
            //是空的，选择的是全部
            var len = $("#areaId option").length;
            for(var i=0;i<len;i++){
                var area_value = $("#areaId").get(0).options[i].value;
                if(!area_value){
                    continue;
                }

                areaIdArr.push(new Number(area_value));
            }
        }

        console.log("获取到的片区信息,areaIdArr:" +JSON.stringify(areaIdArr));
        //获取时间
        var startDate = $("input[name='startDate']").eq(0).val();
        var endDate = $("input[name='endDate']").eq(0).val();

        var pieData = null;

        var pieParams = {
            "startDate": startDate,
            "endDate": endDate,
            "areaIdList": areaIdArr
        }

        $.ajax({
            url: "ssadmin/report/get-pie.html",
            type: "post",
            data: JSON.stringify(pieParams),
            dataType: "json",
            contentType:"application/json",
            async: false,
            success:function (data) {
                pieData = data;
                console.log("data:" + JSON.stringify(data));
            }
        });

        //设置模型
        var option = {
            backgroundColor: "transparent",
            title: {
                text: '签约状态',
                subtext: '纯属虚构',
                left: 'center'
            },
            tooltip: {
                show:true,
                trigger: 'item',
                formatter: function (params) {
                    console.log("aa:" + params);
                    return '{a} <br/>{b} : {c} ({d}%)';
                }
            },
            legend: {
                type: 'scroll',
                orient: 'vertical',
                left:10,
                right: 10,
                top: 10,
                bottom: 10
            },
            series: [
                {
                    name: '姓名',
                    type: 'pie',
                    radius: '25%',  //饼图的半径大小
                    center: ['50%', '50%'],  //饼图的中心点位置，以容器的宽、高作为参考。第一个值是宽度，第二个是高度。
                    data: pieData.seriesData,
                    /*emphasis: {
                        itemStyle: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }*/
                    itemStyle:{
                        normal:{
                            label:{
                                show:true,
                                formatter: '{b}:{c} ({d}%)'
                            },
                            lableLine:{
                                show:true
                            }
                        }
                    }

                }
            ]
        };

        var chartMain = $("#chartMain").get(0);
        //获取图形容器
        var mychart = echarts.init(chartMain);
        //使用指定的配置项和数据 显示图表
        mychart.setOption(option);
    }

};

$(document).ready(function () {

    //将查询按钮绑定事件
    $("#btn_query").on("click", function () {
        chartOperation.getPieData();
    });

    //触发事件
    $("#btn_query").click();

});






