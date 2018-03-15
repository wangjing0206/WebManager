var option;
var myChart;
$(document).ready(function() {
    //初始化图标
    initEcharts();
    funUpdateCharts();
});

function initEcharts() {
    // 路径配置
    require.config({
        paths: {
            echarts: 'http://echarts.baidu.com/build/dist'
        }
    });
    // 使用
    require(
        [
            'echarts',
            'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
        ],
        function(ec) {
            // 基于准备好的dom，初始化echarts图表
            myChart = ec.init(document.getElementById('newsChart'));

            option = {
                tooltip: {
                    show: true
                },
                legend: {
                    data: ['公告统计']
                },
                xAxis: [{
                    type: 'category',
                    data: ["总公告数", "当前公告数", "未使用公告数"]
                }],
                yAxis: [{
                    type: 'value'
                }],
                series: [{
                    "name": "公告统计",
                    "type": "bar",
                    "data": [50, 20, 30]
                }]
            };

            // 为echarts对象加载数据
            myChart.setOption(option);
            funUpdateCharts();
        }
    );
}

function funUpdateCharts() {
    $.ajax({
        type: "get",
        url: "/newsStatistic",
        async: true,
        success: function(msg) {
            console.log(msg);
            var notCurrentCount = msg[0].num;
            var currentCount = msg[1].num;
            var totalCount = currentCount + notCurrentCount;
            option.series[0].data[0] = totalCount;
            option.series[0].data[1] = currentCount;
            option.series[0].data[2] = notCurrentCount;
            myChart.setOption(option);

        },
        error: function() {
            window.alert("更新图表失败");
        }
    });
}