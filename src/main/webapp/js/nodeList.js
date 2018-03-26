var tbodyNode;
var option;
var nodechart;
var nodeInsert=
"<form role='form' id='frmNode' name='frmNode'>"+
"<div class='form-group'>"+
"<label for='nodeid'>节点号</label>"+
"<input type='text' class='form-control' id='nodeid' name='nodeid' value='' />"+
"</div>"+
"<div class='form-group'>"+
"<label for='nodename'>节点名称</label>"+
"<input type='text' class='form-control' id='nodename' name='nodename' value='' />"+
"</div>"+
"<div class='form-group'>"+
"<label for='configid'>配置</label>"+
"<input type='text' class='form-control' id='configid' name='configid' value='' />"+
"</div>"+
"<div class='form-group'>"+
"<label for='ipaddress'>网址</label>"+
"<input type='text' class='form-control' id='ipaddress' name='ipaddress' value='' />"+
"</div>"+
"<div class='form-group'>"+
"<label for='status'>状态</label>"+
"<select class='form-control' id='status' name='status'>"+
"<option value='0' selected>Off Line</option>"+
"<option value='1'>Running</option>"+
"</select>"+
"</div>"+
"<div class='form-group'>"+
"<label for='laststarttime'>上次启动时间</label>"+
"<input type='datetime-local' class='form-control' id='laststarttime' name='laststarttime' value='2018-03-13T00:00:00' />"+
"</div>"+
"<div class='form-group'>"+
"<label for='laststoptime'>上次停止时间</label>"+
"<input type='datetime-local' class='form-control' id='laststoptime' name='laststoptime' value=''/>"+
"</div>"+
"<input type='hidden' id='cid' name='id'/>"+
"</form>";
function success() {
    SimplePop.alert("操作成功",{
        type: "right",
    });
    funNodeSelectAll();
}
function error(){
    SimplePop.alert("操作失败",{
        type: "error",
    });
    funNodeSelectAll();
}
$("#btnNodeInsert").click(function(){
    SimplePop.prompt(nodeInsert,{
        type: "",
        title: "添加节点",
        cancel: function(){

        },
        confirm: function(){
            debugger
            $.ajax({
                type:"post",
                url:"/nodeInsert",
                async:true,
                timeout:10000,
                data:$("#frmNode").serialize(),
                success:function(msg){
                    console.log(msg);
                    if(msg==1){
                        success();
                    }else{
                        error();
                    }
                },
                error:function(msg){
                    error();
                    console.log(msg);
                }
            });
        }
    });
});
function funNodeDelete(id){
    SimplePop.confirm("确定删除此节点吗？",{
        type: "confirm",
        cancel: function(){

        },
        confirm: function(){
            $.ajax({
                type:"get",
                url:"/nodeDelete",
                async:true,
                timeout:10000,
                data:"id="+id,
                dataType:"json",
                success:function(msg){
                    console.log(msg);
                    if(msg==1){
                        success();
                    }else{
                        error();
                    }
                },
                error:function(msg){
                    console.log(msg);
                    error();
                }
            });
        }
    });
}
/*function funNodeUpdate(id,nodeid,nodename,configid,ipaddress,status,laststarttime,laststoptime){
    SimplePop.prompt(nodeInsert,{
        type: "",
        title: "修改节点",
        cancel: function(){

        },
        confirm: function(){
            $.ajax({
                type:"post",
                url:"/nodeUpdate",
                async:true,
                timeout:10000,
                data:$("#frmNode").serialize(),
                success:function(msg){
                    console.log(msg);
                    if(msg==1){
                        success();
                    }else{
                        error();
                    }
                },
                error:function(msg){
                    error();
                    console.log(msg);
                }
            });
        }
    });
    nodeUpdate(id,nodeid,nodename,configid,ipaddress,status,laststarttime,laststoptime);
function nodeUpdate(id,nodeid,nodename,configid,ipaddress,status,laststarttime,laststoptime){
    debugger
    console.log(id)
    $("#nodeid").val(nodeid)
    $("#nodename").val(nodename)
    $("#configid").val(configid)
    $("#ipaddress").val(ipaddress)
    $("#status").val(status)
    $("#laststarttime").val(decodeURIComponent(laststarttime).substr(0,19).replace(" ","T"))
    $("#laststoptime").val(laststoptime==null?'':decodeURIComponent(laststoptime).substr(0,19).replace(" ","T"))
    $("#cid").val(id)
}}*/

function funNodeUpdate(id,nodeid,nodename,configid,ipaddress,status,laststarttime,laststoptime){
    console.log(id)
    $("#nodeid").val(nodeid)
    $("#nodename").val(nodename)
    $("#configid").val(configid)
    $("#ipaddress").val(ipaddress)
    $("#status").val(status)
    $("#laststarttime").val(decodeURIComponent(laststarttime).substr(0,19).replace(" ","T"))
    $("#laststoptime").val(laststoptime==null?'':decodeURIComponent(laststoptime).substr(0,19).replace(" ","T"))
    $("#cid").val(id)
    $('#nodeUpdate').modal()
}
$('#btnNodeInsert').click(function(){
    $('#nodeInsert').modal('hide')
    $.ajax({
        type:"post",
        url:"/nodeInsert",
        async:true,
        timeout:10000,
        data:$("#frmNodeInsert").serialize(),
        success:function(msg){
            console.log(msg);
            if(msg==1){
                success();
            }else{
                error();
            }
        },
        error:function(msg){
            error();
            console.log(msg);
        }
    });
});
$('#btnNodeUpdate').click(function(){
    $('#nodeUpdate').modal('hide')
    $.ajax({
        type:"post",
        url:"/nodeUpdate",
        async:true,
        timeout:10000,
        data:$("#frmNodeUpdate").serialize(),
        success:function(msg){
            console.log(msg);
            if(msg==1){
                success();
            }else{
                error();
            }
        },
        error:function(msg){
            error();
            console.log(msg);
        }
    });
});
function funNodeSelectAll(){
    $.ajax({
        type:"get",
        url:"/nodeSelectAll",
        async:true,
        timeout:10000,
        data:"whichNum="+1,
        dataType:"json",

        success:function(msg){
            console.log(msg);
            tbodyNode="";
            for (var i = 0; i < msg.length; i++) {
                tbodyNode+="<tr class='info'>";
                tbodyNode+="<td>";
                tbodyNode+=msg[i].nodeid;
                tbodyNode+="</td>";
                tbodyNode+="<td>";
                tbodyNode+=msg[i].nodename;
                tbodyNode+="</td>";
                tbodyNode+="<td>";
                tbodyNode+=msg[i].configid;
                tbodyNode+="</td>";
                tbodyNode+="<td>";
                tbodyNode+=msg[i].ipaddress
                tbodyNode+="</td>";
                tbodyNode+="<td>";
                tbodyNode+=msg[i].status==1?'Running':'Off Line';
                tbodyNode+="</td>";
                tbodyNode+="<td>";
                tbodyNode+=msg[i].laststarttime;
                tbodyNode+="</td>";
                tbodyNode+="<td>";
                tbodyNode+=msg[i].laststoptime==null?'---':msg[i].laststoptime;
                tbodyNode+="</td>";
                tbodyNode+="<td>";
                tbodyNode+="<a href='javascript:void(0)' onclick=funNodeUpdate("+ msg[i].id+ "," + msg[i].nodeid+ ",'"+ msg[i].nodename+"',"+ msg[i].configid+",'"+ msg[i].ipaddress+"','"+ msg[i].status+"','"+
                    encodeURIComponent(msg[i].laststarttime)+ "','" + encodeURIComponent(msg[i].laststoptime)+"')>更新</ a>";tbodyNode+="</td>";
                tbodyNode+="</td>";
                tbodyNode+="<td>";
                tbodyNode+="<a href='javascript:void(0)' onclick=funNodeDelete("+msg[i].id+")>删除</a>";
                tbodyNode+="</td>";
                tbodyNode+="</tr>";
            }
            $("#tbodyNode").html(tbodyNode);
        },
        error:function(msg){
            console.log(msg);
        }
    });

}
$(document).ready(function(){
    funNodeSelectAll();
    //select all
    $("#btnNodeSelectAll").click(function(){
        funNodeSelectAll();
    });
    websocket.onmessage = function (event) {
        onMessage(event);
    }
});

function onMessage(event){
    if(event.data=="Insert Successfully"){
        // window.alert("Web Socket Receive Insert Message！");
        option.series[0].data[0]=option.series[0].data[0]+1;
        nodechart.setOption(option);
    }
    else if(event.data=="Delete Successfully"){
        // window.alert("Web Socket Receive Delete Message！");
        option.series[0].data[0]=option.series[0].data[0]-1;
        nodechart.setOption(option);
    }
    else
        window.alert(event.data);
}


