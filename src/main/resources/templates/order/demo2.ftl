<!DOCTYPE html>
<html lang="en">
<#include "../common/header.ftl">

<head>
    <meta charset="UTF-8">
    <title>Demo</title>
    <!-- 引入 ECharts 文件 -->
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts-all-3.js"></script>
</head>
<body>
<div id="wrapper" class="toggled">
<#include "../common/nav.ftl">
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;float: right;margin-left: 100px" ></div>
<div id="line" style="width: 600px;height:400px;margin-top: 100px" ></div>
</div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var colors = ['#5793f3', '#d14a61', '#675bba'];
    var myChart = echarts.init(document.getElementById('main'));//main是<div id="main" style="width: 600px;height:400px;"></div>的id
    var myChart2 = echarts.init(document.getElementById('line'));
    // 指定图表的配置项和数据
    var colors = ['#5793f3', '#d14a61', '#675bba'];


    var colors = ['#5793f3', '#d14a61', '#675bba'];


    var option = {
        title : {
            text: '每月订单销量全年占比',
            subtext: '订单销量占比饼状图',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data:[<#list months as month>
                "${month.month}",
            </#list>]
        },
        series : [
            {
                name: '订单数量',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[
                <#list months as month>
                    {
                        value:
                    ${month.orderNum}
                        ,name:
                            "${month.month}"
                    },
                </#list>
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    var option2 = {
        title: {
            text: '每月订单数量变化可切换统计图',
            subtext: '真实反映趋势'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['订单数量']
        },
        toolbox: {
            show: true,
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                dataView: {readOnly: false},
                magicType: {type: ['line', 'bar']},
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis:  {
            type: 'category',
            boundaryGap: false,
            data : [<#list months as month>
                "${month.month}",
            </#list>],
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value} 单'
            }
        },
        series: [
            {
                name:'订单数量',
                type:'line',
                data:[<#list months as month>
                    "${month.orderNum}",
                </#list>],
                markPoint: {
                    data: [
                        {type: 'value'},

                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                }
            },

        ]
    };



    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    myChart2.setOption(option2);
</script>
</body>
</html>
