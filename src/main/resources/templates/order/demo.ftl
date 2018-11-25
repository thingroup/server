<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Demo</title>
    <!-- 引入 ECharts 文件 -->
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts-all-3.js"></script>
</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;position:absolute;top:50%;left: 50%;margin-top: -200px;margin-left: -300px;"></div>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));//main是<div id="main" style="width: 600px;height:400px;"></div>的id

    // 指定图表的配置项和数据
    var option = {
        title : {
            text: '店铺订单销量实时统计',
            subtext: '店铺订单销量占比饼状图',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: [
               <#list results as result>
                "${result.canteenName}",
            </#list>
            ]
        },
        series : [
            {
                name: '订单数量',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[
                    <#list results as result>
                    {
                    value:
                    ${result.orderNum}
                     ,name:
                    "${result.canteenName}"
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


    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
</body>
</html>
