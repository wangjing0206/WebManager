function shiroFilter() {
	
	$.ajax({
		type: "post",
		url: "/getLoginUserRoles",
		async: true,
		timeout: 10000,
		dataType: "json",
		success: function(roles) {
			console.log(roles);
			for(var i = 0; i < roles.length; i++) {
				console.log(roles[i]);
				if(roles[i]=="管理员"){
					$("#menuUser").show();
				}				
				if(roles[i]=="普通用户"){
					$("#menuUser").hide();
				}
			}
		},
		error: function(msg) {
			console.log(msg);
		}
	});			
}
