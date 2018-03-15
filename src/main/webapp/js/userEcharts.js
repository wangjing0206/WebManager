function countToCharts(msg){
    var optionForSex;
    var myChartForSex;
    var optionForGroup;
    var myChartForGroup;
    var optionForRole;
    var myChartForRole;
    var xAxisDataForGroup=new Array();
    var seriesDataForGroup=new Array();
    var xAxisDataForSex=new Array();
    var seriesDataForSex=new Array();
    var xAxisDataForRole=new Array();
    var seriesDataForRole=new Array();
    $('#userForSex').show();
    $('#userForGroup').show();
    $('#userForRole').show();
    for(var i=1;i<msg.length;i++){
        if(msg[i].key=='sex'){
            xAxisDataForSex.push(msg[i].value+'('+msg[i].count+')人');
            seriesDataForSex.push(msg[i].count);
        }else if(msg[i].key=='groupId'){
            xAxisDataForGroup.push(msg[i].value+'('+msg[i].count+')人');
            seriesDataForGroup.push(msg[i].count);
        } else if(msg[i].key=='roleId'){
            xAxisDataForRole.push(msg[i].value+'('+msg[i].count+')人');
            seriesDataForRole.push(msg[i].count);
        }
    }
    // 路径配置
    require.config({
        paths: {
            echarts: 'http://echarts.baidu.com/build/dist'
        }
    });
    // 加载使用
    // 使用
    require(
        [
            'echarts',
            'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            myChartForSex = ec.init(document.getElementById('userForSex'));
            myChartForGroup = ec.init(document.getElementById('userForGroup'));
            myChartForRole = ec.init(document.getElementById('userForRole'));
            optionForSex = {
                tooltip: {
                    show: true
                },
                legend: {
                    data:['人员分布（性别）']
                },
                xAxis : [
                    {
                        type : 'category',
                        data :  xAxisDataForSex
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        "name":"按性别分布",
                        "type":"bar",
                        "data":seriesDataForSex
                    }
                ]
            };
            optionForGroup = {
                tooltip: {
                    show: true
                },
                legend: {
                    data:['人员分布（部门）']
                },
                xAxis : [
                    {
                        type : 'category',
                        data :  xAxisDataForGroup
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        "name":"按部门分布",
                        "type":"bar",
                        "data":seriesDataForGroup
                    }
                ]
            };
            optionForRole = {
                tooltip: {
                    show: true
                },
                legend: {
                    data:['人员分布（权限）']
                },
                xAxis : [
                    {
                        type : 'category',
                        data :  xAxisDataForRole
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        "name":"按权限分布",
                        "type":"bar",
                        "data":seriesDataForRole
                    }
                ]
            };
            // 为echarts对象加载数据
            debugger

            myChartForSex.setOption(optionForSex);
            myChartForGroup.setOption(optionForGroup);
            myChartForRole.setOption(optionForRole);
            $('#userForSex').show();
            $('#userForGroup').hide();
            $('#userForRole').hide();
        }
    );
}