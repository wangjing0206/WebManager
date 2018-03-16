var tbody0;
var pbody;
function userSelectAll(whichNum){
    $('#userUpdate').hide();
    $('#userInsert').show();
    if($('#whichNum').val()==whichNum){
        return false;
    }
    $('#whichNum').val(whichNum);
			$.ajax({
			type:"get",
			url:"/userSelectAllFromFastJson",
			async:true,
			timeout:10000,
			data:"whichNum="+whichNum,
			dataType:"json",
			success:function(msg){
				console.log(msg);
				tbody0=" ";
				for (var i = 0; i < msg.length; i++) {
					tbody0+="<tr class='info'>";
					tbody0+="<td>";	
					tbody0+=msg[i].num==null?"---":msg[i].num;
					tbody0+="</td>";
					tbody0+="<td>";	
					tbody0+=msg[i].userName==null?"---":msg[i].userName;
					tbody0+="</td>";
					tbody0+="<td>";	
					tbody0+=msg[i].sex==1?"男":"女";
					tbody0+="</td>";
					tbody0+="<td>";	
					tbody0+=msg[i].tel==null?"---":msg[i].tel;
					tbody0+="</td>";
					tbody0+="<td>";	
					tbody0+=msg[i].groupName==null?"---":msg[i].groupName;
					tbody0+="</td>";
                    tbody0+="<td>";
                    tbody0+=msg[i].roleName==null?"---":msg[i].roleName;
                    tbody0+="</td>";
					tbody0+="<td>";
					tbody0+="<a href='javascript:void(0)' style='color: blue' onclick='userSelectOne("+msg[i].id+")'>更新</a>";
					tbody0+="</td>";
					tbody0+="<td>";
					tbody0+="<a href='javascript:void(0)' style='color: red' onclick='userDelete("+msg[i].id+")'>删除</a>";
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
	//加载页面就查询
    getCount();
    userSelectAll(1);
	//insert
	$("#frmUser").submit(function(){
			$.ajax({
			type:"post",
			url:"/userInsert",
			async:true,
			timeout:10000,
			data:$("#frmUser").serialize(),
			success:function(msg){
				console.log(msg);
                if (msg==1) {
                    window.alert("添加成功");
                    userSelectAll(1);
                    getCount();
                }
			},
			error:function(msg){
                window.alert("添加失败");
				console.log(msg)
			}
		});
		return false;
	});
	//delete
	//update
	//selectOne
	//selectAll
	$("#userSelectAll").click(function(){
        $('#num1').val("");
        $('#userName1').val("");
        $('#groupName1').val("");
        $('#roleName1').val("");
        $('#whichNum').val("");
		userSelectAll(1);
	});
    $("#userSearchAll").click(function(){
        $('#userUpdate').hide();
        $('#userInsert').show();
        $.ajax({
            type:"post",
            url:"/userSearchAll",
            async:true,
            timeout:10000,
            data:$("#frmSearch").serialize()+"&whichNum="+1,
            success:function(msg){
                console.log(msg);
                tbody0=" ";
                for (var i = 0; i < msg.length; i++) {
                    tbody0+="<tr class='info'>";
                    tbody0+="<td>";
                    tbody0+=msg[i].num==null?"---":msg[i].num;
                    tbody0+="</td>";
                    tbody0+="<td>";
                    tbody0+=msg[i].userName==null?"---":msg[i].userName;
                    tbody0+="</td>";
                    tbody0+="<td>";
                    tbody0+=msg[i].sex==1?"男":"女";
                    tbody0+="</td>";
                    tbody0+="<td>";
                    tbody0+=msg[i].tel==null?"---":msg[i].tel;
                    tbody0+="</td>";
                    tbody0+="<td>";
                    tbody0+=msg[i].groupName==null?"---":msg[i].groupName;
                    tbody0+="</td>";
                    tbody0+="<td>";
                    tbody0+=msg[i].roleName==null?"---":msg[i].roleName;
                    tbody0+="</td>";
                    tbody0+="<td>";
                    tbody0+="<a href='javascript:void(0)' style='color: blue' onclick='userSelectOne("+msg[i].id+")'>更新</a>";
                    tbody0+="</td>";
                    tbody0+="<td>";
                    tbody0+="<a href='javascript:void(0)' style='color: red' onclick='userDelete("+msg[i].id+")'>删除</a>";
                    tbody0+="</td>";
                    tbody0+="</tr>";
                }

                $("#tbody0").html(tbody0);
            },
            error:function(msg){
                console.log(msg);
            }
        });
    });
    $("#userUpdate").click(function(){
        userUpdate();
    });
});
function userSearchAll(){
    $('#userUpdate').hide();
    $('#userInsert').show();
    $.ajax({
        type:"post",
        url:"/userSearchAll",
        async:true,
        timeout:10000,
        data:$("#frmSearch").serialize(),
        dataType:"json",
        success:function(msg){
            console.log(msg);
            tbody0=" ";
            for (var i = 0; i < msg.length; i++) {
                tbody0+="<tr class='info'>";
                tbody0+="<td>";
                tbody0+=msg[i].num==null?"---":msg[i].num;
                tbody0+="</td>";
                tbody0+="<td>";
                tbody0+=msg[i].userName==null?"---":msg[i].userName;
                tbody0+="</td>";
                tbody0+="<td>";
                tbody0+=msg[i].sex==1?"男":"女";
                tbody0+="</td>";
                tbody0+="<td>";
                tbody0+=msg[i].tel==null?"---":msg[i].tel;
                tbody0+="</td>";
                tbody0+="<td>";
                tbody0+=msg[i].groupName==null?"---":msg[i].groupName;
                tbody0+="</td>";
                tbody0+="<td>";
                tbody0+=msg[i].roleName==null?"---":msg[i].roleName;
                tbody0+="</td>";
                tbody0+="<td>";
                tbody0+="<a href='javascript:void(0)' style='color: blue' onclick='userSelectOne("+msg[i].id+")'>更新</a>";
                tbody0+="</td>";
                tbody0+="<td>";
                tbody0+="<a href='javascript:void(0)' style='color: red' onclick='userDelete("+msg[i].id+")'>删除</a>";
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
function userDelete(id){
	if(confirm("确定删除此用户吗？")){
        $.ajax({
            type:"get",
            url:"/userDelete",
            async:true,
            timeout:10000,
            data:"id="+id,
            success:function(msg){
                console.log(msg);
                if (msg==1) {
                    window.alert("删除成功");
                    userSelectAll(1);
                    getCount();
                }
            },
            error:function(msg){
                window.alert("删除失败");
                console.log(msg)
            }
        });
	}
}

function userSelectOne(id){
    $.ajax({
        type:"get",
        url:"/userSelectOne",
        async:true,
        timeout:10000,
        data:"id="+id,
        success:function(msg){
            console.log(msg);
            infoToPage(msg);
        },
        error:function(msg){
            console.log(msg)
        }
    });
}
function infoToPage(msg){
    $('#userInsert').hide();
    $('#userUpdate').show();
	$('#id').val(msg.id);
    $('#num').val(msg.num);
    $('#userName').val(msg.userName);
    $('#tel').val(msg.tel);
    $('#sex').val(msg.sex);
    $('#groupId').val(msg.groupId);
    $('#roleId').val(msg.roleId);
}
function userUpdate(){
    $.ajax({
        type:"post",
        url:"/userUpdate",
        async:true,
        timeout:10000,
        data:$("#frmUser").serialize(),
        success:function(msg){
            console.log(msg);
            if (msg==1) {
                window.alert("修改成功");
                userSelectAll(1);
                getCount();
            }
        },
        error:function(msg){
            window.alert("修改失败");
            console.log(msg)
        }
    });
}
function getCount(){
    debugger
    $.ajax({
        type:"get",
        url:"/getCount",
        async:true,
        timeout:10000,
        data:{},
        success:function(msg){
            console.log(msg);
            countToPage(msg);
        },
        error:function(msg){
            console.log(msg)
        }
    });
}
function userSelectAllPageUp(){
    var whichNum=$('#whichNum').val()-1>0?$('#whichNum').val()-1:1;
    userSelectAll(whichNum);
}
function userSelectAllPageDown(){
    var page=$('#count').val();
    var whichNum=parseInt($('#whichNum').val())+1;
    if(whichNum>page){
        whichNum=page;
    }
    userSelectAll(whichNum);
}
function countToPage(msg){
    pbody="<ul class='pagination'><li><a href='javascript:void(0)' onclick='userSelectAll(1)'>首页</a></li><li><a href='javascript:void(0)' onclick='userSelectAllPageUp()'>上一页</a></li>";
    var page=Math.ceil(msg[0].count/10);
    $('#count').val(page);
    if (page>0) {
        for(var i=1;i<page+1;i++){
            pbody+="<li><a href='javascript:void(0)' onclick='userSelectAll("+i+")'>"+i+"</a></li>";
            if(i==10){
                break;
            }
        }
    }else {
        pbody+="<li><a href='javascript:void(0)' onclick='userSelectAll("+1+")'>1</a></li>";
    }
    pbody+="<li><a href='javascript:void(0)' onclick='userSelectAllPageDown()'>下一页</a></li><li><a href='javascript:void(0)' onclick='userSelectAll("+page+")'>尾页</a></li></ul>";
    $("#pbody").html(pbody);
}