$(document).ready(function(){

});
function success() {
    SimplePop.alert("操作成功",{
        type: "right",
    });
    getCount();
    userSearchAll(1);
}
function error(){
    SimplePop.alert("操作失败",{
        type: "error",
    });
    getCount();
    userSearchAll(1);
}
function info(){
    alert("请确认信息填写是否正确")
}
var userInsertHtml=
    "<form role='form' id='frmUserInsert' name='frmUserInsert'>"+
    "<div class='form-group'>"+
    "<label for='num'><span id='numInfo'></span>工号</label><input type='text' class='form-control' onblur='checkNum()' id='num' name='num'/>"+
    "</div>"+
    "<div class='form-group'>"+
    "<label for='userPassword'>密码</label><input type='text' class='form-control' id='userPassword' name='userPassword'/>"+
    "</div>"+
    "<div class='form-group'>"+
    "<label for='userName'><span id='nameInfo'></span>姓名</label><input type='text' class='form-control' id='userName' name='userName'/>"+
    "</div>"+
    "<div class='form-group'>"+
    "<label for='sex' >性别</label>"+
    "<select class='form-control' id='sex' name='sex'>"+
    "<option value='1' selected >男</option>"+
    "<option value='0'>女</option>"+
    "</select>"+
    "</div>"+
    "<div class='form-group'>"+
    "<label for='tel'>电话<span style='color: #CC0000' id='telInfo'></label><input type='tel' class='form-control' id='tel' name='tel'/>"+
    "</div>"+
    "<div class='form-group'>"+
    "<label for='groupId'>部门</label>"+
    "<select class='form-control' id='groupId' name='groupId'>"+
    "<option value='1'>研发部</option>"+
    "<option value='2' selected>生产部</option>"+
    "<option value='3' >后勤部</option>"+
    "</select>"+
    "</div>"+
    "<div class='form-group'>"+
    "<label for='roleId' >权限</label>"+
    "<select class='form-control' id='roleId' name='roleId'>"+
    "<option value='1' selected >管理员</option>"+
    "<option value='2'>普通用户</option>"+
    "</select>"+
    "</div>"+
    "</form>";
$("#userInsert").click(function(){
    SimplePop.prompt(userInsertHtml,{
        type: "",
        title: "添加用户",
        cancel: function(){

        },
        confirm: function(){
            var num=$('#num').val();
            var userName=$('#userName').val();
            var numInfo=$('#numInfo').html();
            debugger
            if(num!=''&&userName!=''&&numInfo!='工号已存在!'){
                $.ajax({
                    type:"post",
                    url:"/userInsert",
                    async:true,
                    timeout:10000,
                    data:$("#frmUserInsert").serialize(),
                    success:function(msg){
                        console.log(msg);
                        if(msg==1){
                            $('#whichNum').val(0);
                            $('#num1').val("");
                            $('#userName1').val("");
                            $('#groupName1').val("");
                            $('#roleName1').val("");
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
            }else{
                if(num==''){
                    $('#numInfo').html('请填写工号！');
                    $('#numInfo').css('color','red');
                }
                if(userName==''){
                    $('#nameInfo').html('请填写姓名！');
                    $('#nameInfo').css('color','red');
                }
                exit('asda');
            }
        }
    });
});
