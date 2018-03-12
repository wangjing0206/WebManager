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
        url:"/userLogin",
        async:true,
        timeout:10000,
        data:$("#frmLogin").serialize(),
        success:function(msg){
            $('#numHtml').html('');
            $('#passwordHtml').html('*');
            console.log(msg);
            if (msg==1) {
                loginSuccess();
            }else{
                $('#passwordHtml').html("用户名或密码不正确");
            }
        },
        error:function(msg){

            console.log(msg)
        }
    });

}
function loginSuccess(){
    window.location.href="../index.html";
}