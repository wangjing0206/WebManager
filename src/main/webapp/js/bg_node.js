

        var frmData = {  
            id: "id",  
            nodeid: "nodeid"  
        };  

function funNodeUpdate(id){
		window.alert(id)
		frmData.id=id
		frmData.nodeid=id
        util.fillFormData("#frmNode", formData);
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
			},
			error:function(msg){
				console.log(msg);
			}
		});

}

var tbody0="";
function funNodeSelectOne(){
		//window.alert("我是菜鸟我怕谁");
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
				 	tbody0+=msg.laststarttime;
				 	tbody0+="</td>";
                  
				 	tbody0+="<td>";
				 	tbody0+=msg.laststoptime;
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
					tbody0+="<a href='javascript:void(0)' onclick=funNodeUpdate("+msg[i].id+")>更新</a>";
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
	//select one
	$("#btnNodeSelectOne").click(function(){
		funNodeSelectOne();
	});
	
	//select all
	$("#btnNodeSelectAll").click(function(){
		funNodeSelectAll();
	});
});
