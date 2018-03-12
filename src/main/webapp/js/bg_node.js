
var tbody0="";
function funNodeSelectAll(){
//		window.alert("我是菜鸟我怕谁");
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
					tbody0+="<a href='javascript:void(0)' onclick=funCalendarUpdate()>更新</a>";
					tbody0+="</td>";
					
					tbody0+="<td>";	
					tbody0+="<a href='javascript:void(0)' onclick=funCalendarDelete()>删除</a>";
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

$(document).ready(function(){
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
            	if(msg=1)
             		window.alert("add OK")           		
        	},
			error:function(msg){
				console.log(msg);
			}
    	});
		return false;
	});
	
	//delete
	//update
	//select all
	$("#btnNodeSelectAll").click(function(){
		funNodeSelectAll();
	});
});
