function nodeToCharts(msg){
    var nodeOption;
    var nodeChart;
    var nodeAxisData=new Array();
    var nodeSeriesData=new Array();
    //var nodeSeriesName=new Array();
    for(var i=0;i<msg.length;i++){
        if (msg[i].laststoptime == null) {
            msg[i].laststoptime = new Date().getTime();
        }
            nodeAxisData.push('节点:'+msg[i].nodeid+'('+runTime(msg[i].laststoptime,msg[i].laststarttime)+')');
            //nodeSeriesName.push(msg[i].nodeid+'运行时间'+runTime(msg[i].laststoptime,msg[i].laststarttime));
            nodeSeriesData.push(runTimeDay(msg[i].laststoptime,msg[i].laststarttime));
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
            nodeChart = ec.init(document.getElementById('nodeChart'));
            nodeOption = {
                tooltip: {
                    show: true
                },
                legend: {
                    data:['节点运行时间（天）']
                },
                xAxis : [
                    {
                        type : 'category',
                        data :  nodeAxisData
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        "name":'运行时间',
                        "type":"bar",
                        "data":nodeSeriesData
                    }
                ]
            };
            // 为echarts对象加载数据
            nodeChart.setOption(nodeOption);
        }
    );
}
$(document).ready(function() {
    //加载页面就查询
    nodeSelectAll();
});

function nodeSelectAll(){
    $.ajax({
        type:"get",
        url:"/nodeSelectAll",
        async:true,
        timeout:10000,
        data:"whichNum="+1,
        dataType:"json",
        success:function(msg){
            console.log(msg);
            nodeToCharts(msg);
        },
        error:function(msg){
            console.log(msg);
        }
    });
}
function runTimeDay(stopTime,startTime) {
    if (stopTime == null) {
        stopTime = new Date().getTime();
    } else {
        stopTime = new Date(stopTime).getTime();
    }
    startTime = new Date(startTime).getTime();
    var runTimeDay = (stopTime - startTime)/(24*3600*1000);
    return runTimeDay;
}
function runTime(stopTime,startTime){
    if(stopTime==null){
        stopTime=new Date().getTime();
    }else{
        stopTime=new Date(stopTime).getTime();
    }
    startTime=new Date(startTime).getTime();
    var runTime=stopTime-startTime;
    //计算出相差天数
    var days=Math.floor(runTime/(24*3600*1000));
    //计算出小时数
    var leave1=runTime%(24*3600*1000);
    //计算天数后剩余的毫秒数
    var hours=Math.floor(leave1/(3600*1000));
    //计算相差分钟数
    var leave2=leave1%(3600*1000);
    //计算小时数后剩余的毫秒数
    var minutes=Math.floor(leave2/(60*1000));
    //计算相差秒数
    var leave3=leave2%(60*1000);
    //计算分钟数后剩余的毫秒数
    var seconds=Math.round(leave3/1000);
    return days+"天"+hours+"小时"+minutes+"分";
}