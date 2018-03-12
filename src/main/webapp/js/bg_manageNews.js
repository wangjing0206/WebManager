var tbody0;

function funNewsSelectAll() {
	$('#btnUpdate').hide();
    $('#btnInsert').show();
	$.ajax({
		type: "post",
		url: "/newsSelectAllFromFastJson",
		async: true,
		timeout: 10000,
		data: "whichNum=" + 1,
		dataType: "json",
		success: function(msg) {
			console.log(msg);
			tbody0 = "";
			for(var i = 0; i < msg.length; i++) {
				console.log(msg[i].content);
				tbody0 += "<tr class='info'>";
				tbody0 += "<td>";
				tbody0 += msg[i].title == null ? "---" : msg[i].title;
				tbody0 += "</td>";
				tbody0 += "<td>";
				tbody0 += msg[i].currentNews==1?"是":"否";
				/*checked =  msg[i].currentNews == 1 ? "checked" : "";
				tbody0 += "<div class='checkbox'><label><input type='checkbox' "+checked+"><i class='fa fa-square-o'></i></label></div>"*/
				tbody0 += "</td>";
				tbody0 += "<td>";
				tbody0 += msg[i].createDate;
				tbody0 += "</td>";
				tbody0 += "<td>";
				tbody0 += msg[i].updateDate;
				tbody0 += "</td>";
				tbody0 += "<td>";
				tbody0 += "<a href='javascript:void(0)' style='color: blue' onclick='funUpdateForm("+msg[i].id+")'>更新</a>";
				tbody0 += "</td>";
				tbody0 += "<td>";
				tbody0 += "<a href='javascript:void(0)' onClick=funDelete(" + msg[i].id + ")>删除</a>";
				tbody0 += "</td>";
				tbody0 += "</tr>";
			}
			$("#tbody0").html(tbody0);

		},
		error: function(msg) {
			console.log(msg);
		}
	});
}

$(document).ready(function() {
	//insert
	$("#frmNews").submit(function() {

		console.log($("#frmNews").serialize());
		$.ajax({
			type: "post",
			url: "/newsInsert",
			async: true,
			timeout: 10000,
			data: $("#frmNews").serialize(),
			success: function(msg) {
				console.log(msg);
				if(msg == 1) {
					window.alert("增加成功");
					funNewsSelectAll();
				}

			},
			error: function(msg) {
				console.log(msg);
			}
		});
		return false;
	});

	$("#btnSelectAll").click(function() {
		funNewsSelectAll();
	});
	
	$("#btnUpdate").click(function() {
		funUpdate();
	});

	funNewsSelectAll();
	TinyMCEStart('#wysiwig_simple', null);

});

function funDelete(id0) {
	if(window.confirm("您确信要删除吗?")) {
		$.ajax({
			type: "get",
			url: "/newsDelete",
			data: "id=" + id0,
			async: true,
			success: function(msg) {
				if(msg ==1) {
					window.alert("删除成功");
					funNewsSelectAll();
				} else {
					window.alert("删除失败");
				}
			},
			error: function() {
				window.alert("删除出错");
			}
		});
	}
}

var idG;
//定义更新的方法
function funUpdateForm(id0) {
	//显现更新按钮
	$("#btnUpdate").show();
	$("#btnInsert").hide();
	//global value give
	idG = id0;
	$.ajax({
		type: "get",
		url: "/newsSelectById",
		data: "id=" + id0,
		async: true,
		success: function(msg) {
			console.log(msg);
			//blank value
			$('#id').val(msg.id);
			$("#title").val(msg.title);
			$("#currentNews").val(msg.currentNews);
			$("#newsContent").val(msg.content);
			
		},
		error: function() {
		}
	});
}

//定义更新的方法
function funUpdate() {
	 $.ajax({
        type:"post",
        url:"/newsUpdate",
        async:true,
        timeout:10000,
        data:$("#frmNews").serialize(),
        success:function(msg){
            console.log(msg);
            if (msg==1) {
                window.alert("修改成功");
                funNewsSelectAll();
            }
        },
        error:function(msg){
            window.alert("修改失败");
            console.log(msg)
        }
    });
}