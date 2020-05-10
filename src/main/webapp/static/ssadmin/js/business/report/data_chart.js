var chartOperation = {

    //获取曲线图数据
    getLineData: function (areaIdArr, startDate, endDate) {

        //查询条件
        var pieParams = {
            "startDate": startDate,
            "endDate": endDate,
            "areaIdList": areaIdArr
        }

        //饼图数据
        var pieData = null;
        $.ajax({
            url: "ssadmin/report/get-pie.html",
            type: "post",
            data: JSON.stringify(pieParams),
            dataType: "json",
            contentType: "application/json",
            async: false,
            success: function (data) {
                pieData = data;
                console.log("data:" + JSON.stringify(data));
            }
        });

        //折线图数据
        var lineData = null;
        $.ajax({
            url: "ssadmin/report/get-settleAccount-line.html",
            type: "post",
            data: JSON.stringify(pieParams),
            dataType: "json",
            contentType: "application/json",
            async: false,
            success: function (data) {
                lineData = data;
                console.log("data-line:" + JSON.stringify(data));
            }
        });

        var chartMain = $("#chart-line").get(0);

        //设置模型参数
        var option_line = {
            title: {
                text: "征收情况",
                left: "center"
            },
            grid: {
                bottom: "13%"
            },
            legend: {
                data: [
                    {name: "签约户数"},
                    {name: "应付金额"},
                ],
                selected: {
                    "签约户数": true
                },
                bottom: 0
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross'
                },
                formatter: function (p, t, f) {
                    var s = '截至' + p[0].value[0] + '<br />累计<br />';
                    for (var i = 0, iL = p.length; i < iL; i++) {
                        if (p[i].seriesName == '签约户数') {
                            s += '签约' + p[i].value[1] + '户' + p[i].value[2] + '㎡<br />'
                        } else if (p[i].seriesName == '应付金额') {
                            s += '应付金额' + p[i].value[1] + '亿元<br />'
                        }
                    }
                    return s;
                }
            },
            xAxis: {
                type: 'time',
                name: '时     间',
                nameLocation: 'middle',
                nameGap: 25,
                splitLine: {
                    show: false
                },
                axisLabel: {
                    formatter: function (value, index) {
                        // 格式化成月/日，只在第一个刻度显示年份
                        var date = new Date(value);
                        var texts = [(date.getMonth() + 1), date.getDate()];
                        if (index === 0) {
                            texts.unshift(date.getFullYear());
                        }
                        return texts.join('/');
                    }
                }
            },
            yAxis: [
                {
                    type: 'value',
                    name: '资  金 / 亿  元',
                    position: 'left',
                    nameLocation: 'middle',
                    nameGap: 25,
                    axisLabel: {
                        rotate: 90
                    },
                    axisLine: {
                        lineStyle: {
                            color: 'green'
                        }
                    },
                    splitLine: {
                        show: false
                    }
                }, {
                    type: 'value',
                    name: '户  数',
                    position: 'right',
                    nameLocation: 'middle',
                    nameGap: 25,
                    axisLabel: {
                        rotate: 90
                    },
                    axisLine: {
                        lineStyle: {
                            color: 'red'
                        }
                    },
                    splitLine: {
                        show: false
                    }
                }
            ],
            series: [
                {
                    name: "签约户数",
                    yAxisIndex: 1,
                    data: lineData.map(i => [i.date, i.num, i.area]),
                    type: 'line',
                    /*itemStyle: {
                        color: '#F00',
                        opacity: 0.5
                    },//*/
                    lineStyle: {
                        //type: 'dashed',
                        opacity: 0.6
                    },
                    symbol: 'none',//'triangle'
                },
                {
                    name: "应付金额",
                    yAxisIndex: 0,
                    //data: linedata.map(function(i){return [i[0],i[5]]}),
                    data: lineData.map(i => [i.date, i.payTotal]),
                    type: 'line',
                    /*itemStyle: {
                        color: 'green'
                    }//*/
                    symbol: 'none'
                },
                {
                    name: '补偿方式',
                    z:9,
                    type: 'pie',
                    selectedMode: 'single',
                    radius: [0, '40px'],
                    center: [chartMain.clientWidth*0.17+50,chartMain.clientHeight*0.1+30],
                  /*  label: {
                        formatter:'{b}:{d}%',
                        position: 'inner',//outside
                        fontSize: 8
                    },*/
                    labelLine: {
                        show: true
                    },
                    data: pieData.seriesData,
                   /* itemStyle:{
                        opacity: 0.4
                    }*/
                    itemStyle: {
                        normal: {
                            label: {
                                show: true,
                                formatter: '{b}:{c} ({d}%)'
                            },
                            lableLine: {
                                show: true
                            }
                        }
                    }
                }
            ]
        }


        //获取图形容器
        var mychart = echarts.init(chartMain);
        //使用指定的配置项和数据 显示图表
        mychart.setOption(option_line);

    }
};

$(document).ready(function () {

    //将查询按钮绑定事件
    $("#btn_query").on("click", function () {

        //获取片区
        var areaId = $("#areaId").val();
        var areaIdArr = new Array();
        if (areaId) {
            areaIdArr.push(areaId);
        } else {
            //是空的，选择的是全部
            var len = $("#areaId option").length;
            for (var i = 0; i < len; i++) {
                var area_value = $("#areaId").get(0).options[i].value;
                if (!area_value) {
                    continue;
                }

                areaIdArr.push(new Number(area_value));
            }
        }

        console.log("获取到的片区信息,areaIdArr:" + JSON.stringify(areaIdArr));
        //获取时间
        var startDate = $("input[name='startDate']").eq(0).val();
        var endDate = $("input[name='endDate']").eq(0).val();

        //获取折线图
        chartOperation.getLineData(areaIdArr, startDate, endDate);

    });

    //触发事件
    $("#btn_query").click();

});






