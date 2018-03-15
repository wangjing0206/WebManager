


function funNodeUpdate(id,nodeid,nodename,configid,ipaddress,status,laststarttime,laststoptime){
    console.log(id)
    $("#nodeid").val(nodeid)
    $("#nodename").val(nodename)
    $("#configid").val(configid)
    $("#ipaddress").val(ipaddress)
    $("#status").val(status)
    $("#laststarttime").val(decodeURIComponent(laststarttime).substr(0,19).replace(" ","T"))
    $("#laststoptime").val(decodeURIComponent(laststoptime).substr(0,19).replace(" ","T"))
    $("#cid").val(id)
    $("#btnNodeUpdate").show()
    $("#btnNodeInsert").hide()

}

function funNodeDelete(id){
	window.alert(id)

	$.ajax({
		type:"get",
		url:"/nodeDelete",
		async:true,
		timeout:10000,
		//data:$("#frmNode").serialize(),
		data:"id="+id,
		dataType:"json",
		
		success:function(msg){
			console.log(msg);
            funNodeSelectAll();
		},
		error:function(msg){
			console.log(msg);
		}
	});

}

var tbody0="";
function funNodeSelectOne(){
	$.ajax({
		type:"get",
		url:"/nodeSelectOne",
		async:true,
		timeout:10000,
		data:$("#frmNode").serialize(),
		//data:"id=2",
		dataType:"json",
		
		success:function(msg){
			console.log(msg);
			tbody0="";
            	tbody0+="<tr class='info'>";

            	tbody0+="<td>";
            	tbody0+=msg.nodeid;
            	tbody0+="</td>";
              	
              	tbody0+="<td>";
			 	tbody0+=msg.nodename;
			 	tbody0+="</td>";
              
			 	tbody0+="<td>";
			 	tbody0+=msg.configid;
			 	tbody0+="</td>";
              
			 	tbody0+="<td>";
			 	tbody0+=msg.ipaddress
			 	tbody0+="</td>";
              
			 	tbody0+="<td>";
			 	tbody0+=msg.status;
			 	tbody0+="</td>";
              
			 	tbody0+="<td>";
			 	tbody0+=msg.laststarttime.subsubstr(0,19);
			 	tbody0+="</td>";
              
			 	tbody0+="<td>";
			 	tbody0+=msg.laststoptime.subsubstr(0,19);
			 	tbody0+="</td>";
              
			 	tbody0+="<td>";
			 	tbody0+="<a href='javascript:void(0)' onclick=funNodeUpdate("+msg.id+")>更新</a>";
			 	tbody0+="</td>";
			
			 	tbody0+="<td>";
			 	tbody0+="<a href='javascript:void(0)' onclick=funNodeDelete("+msg.id+")>删除</a>";
			 	tbody0+="</td>";

            	tbody0+="</tr>";

			$("#tbody0").html(tbody0);
		},
		error:function(msg){
			console.log(msg);
		}
	});
}

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
			tbody0="";
			for (var i = 0; i < msg.length; i++) {
				tbody0+="<tr class='info'>";

				tbody0+="<td>";	
				tbody0+=msg[i].nodeid;
				tbody0+="</td>";

				tbody0+="<td>";	
				tbody0+=msg[i].nodename;
				tbody0+="</td>";

				tbody0+="<td>";	
				tbody0+=msg[i].configid;
				tbody0+="</td>";

				tbody0+="<td>";	
				tbody0+=msg[i].ipaddress
				tbody0+="</td>";

				tbody0+="<td>";	
				tbody0+=msg[i].status;
				tbody0+="</td>";

				tbody0+="<td>";	
				tbody0+=msg[i].laststarttime;
				tbody0+="</td>";

				tbody0+="<td>";	
				tbody0+=msg[i].laststoptime;
				tbody0+="</td>";

				tbody0+="<td>";	
//				tbody0+="<a href='javascript:void(0)' onclick=funNodeUpdate("+msg[i].id+")>更新</a>";
                tbody0+="<a href='javascript:void(0)' onclick=funNodeUpdate("+ msg[i].id+ "," + msg[i].nodeid+ ",'"+ msg[i].nodename+"',"+ msg[i].configid+",'"+ msg[i].ipaddress+"','"+ msg[i].status+"','"+
					encodeURIComponent(msg[i].laststarttime)+ "','" + encodeURIComponent(msg[i].laststarttime)+"')>更新</ a>";tbody0+="</td>";

				tbody0+="</td>";
				
				tbody0+="<td>";	
				tbody0+="<a href='javascript:void(0)' onclick=funNodeDelete("+msg[i].id+")>删除</a>";
				tbody0+="</td>";

				tbody0+="</tr>";					
			}
			$("#tbody0").html(tbody0);
		},
		error:function(msg){
			console.log(msg);
		}
	});

}

var option;
var myChart;

function funInit(){
	$("#btnNodeInsert").show()
	$("#btnNodeSelectAll").show()
	$("#btnNodeUpdate").hide()

//	option.series[0].data[0]=100;
//	// 为echarts对象加载数据 
//  myChart.setOption(option); 

}


$(document).ready(function(){

	funInit();

	//update
	$("#btnNodeUpdate").click(function(){
		console.log($("#frmNode").serialize())
        $.ajax({
            type:"post",
            url:"/nodeUpdate",
            async:true,
            timeout:10000,
            data:$("#frmNode").serialize(),
            success:function(msg){
            	console.log(msg);
            	if(msg=1){
             		window.alert("Update OK")
             		//重新调用网多
             		funNodeSelectAll();
             		//调用初始化
             		funInit();
             	}
        	},
			error:function(msg){
				console.log(msg);
			}
    	});
		return false;
	
	});

	//insert
	$("#frmNode").submit(function(){
		console.log($("frmNode").serialize())
        $.ajax({
            type:"post",
            url:"/nodeInsert",
            async:true,
            timeout:10000,
            data:$("#frmNode").serialize(),
            success:function(msg){
            	console.log(msg);
            	if(msg==1){
             		window.alert("add OK")           		
             		funNodeSelectAll();
            	}
        	},
			error:function(msg){
				console.log(msg);
			}
    	});
		return false;
	});
	
	//delete
	//update
	//select one
	$("#btnNodeSelectOne").click(function(){
		funNodeSelectOne();
	});
	
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
		window.alert("Web Socket Receive Insert Message！");
		option.series[0].data[0]=option.series[0].data[0]+1;
		myChart.setOption(option);
	}
	else if(event.data=="Delete Successfully"){
		window.alert("Web Socket Receive Delete Message！");
		option.series[0].data[0]=option.series[0].data[0]-1;
		myChart.setOption(option);
	}
	else	
		window.alert(event.data);
}


