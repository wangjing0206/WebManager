function userLogin() {
    var num=$('#num').val();
    var password=$('#password').val();
    if(num==null||num==""){
        $('#numHtml').html('请输入工号');
        return false;
    }
    if(password==null||password==""){
        $('#numHtml').html('');
        $('#passwordHtml').html('请输入密码');
        return false;
    }
    $.ajax({
        type:"post",
        /*url:"/userLogin",*/
        url:"/userAuthLogin",
        async:true,
        timeout:10000,
        data:$("#frmLogin").serialize(),
        success:function(msg){
            $('#numHtml').html('');
            $('#passwordHtml').html('');
            console.log(msg);
            var jsonData = JSON.parse(msg);   
            console.log(jsonData.msg);
            /*if (msg==1) {*/
           	if (jsonData.msg=="登录成功") {
                loginSuccess(jsonData);
            }else{
                $('#passwordHtml').html("用户名或密码不正确");
            }
        },
        error:function(msg){

            console.log(msg)
        }
    });

}
function loginSuccess(jsonData){
    var lStorage=window.localStorage;
    lStorage.setItem('id',jsonData.user.id);
    lStorage.setItem('userName',jsonData.user.userName);
    lStorage.setItem('num',jsonData.user.num);
    lStorage.setItem('tel',jsonData.user.tel);
    lStorage.setItem('sex',jsonData.user.sex);
    lStorage.setItem('roleId',jsonData.user.roleId);
    lStorage.setItem('roleName',jsonData.user.roleName);
    lStorage.setItem('groupId',jsonData.user.groupId);
    lStorage.setItem('groupName',jsonData.user.groupName);
    window.location.href="../index.html";
}