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
    SimplePop.alert("请确认信息填写是否正确",{
        type: "info",
    });
}
var userUpdateHtml=
    "<form role='form' id='frmUserUpdate' name='frmUserUpdate'>"+
    "<div class='form-group'>"+
    "<label for='num'><span id='numInfo'></span>工号</label><input type='text' class='form-control'onblur='checkNum()' id='num' name='num'/>"+
    "</div>"+
    "<div class='form-group'>"+
    "<label for='userName'><span id='nameInfo'>姓名<span id='nameInfo'></label><input type='text' class='form-control' id='userName' name='userName'/>"+
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
    "<input id='idToUpdate' name='id' type='hidden'>"+
    "<input id='numToUpdate' type='hidden'>"+
    "</form>";
function userUpdate(id){
    SimplePop.prompt(userUpdateHtml,{
        type: "",
        title: "修改用户",
        cancel: function(){

        },
        confirm: function(){
            var num=$('#num').val();
            var userName=$('#userName').val();
            var numInfo=$('#numInfo').html();
            if(num==''||userName==''||numInfo=='工号已存在!'){
                if(num==''){
                    $('#numInfo').html('请填写工号！');
                    $('#numInfo').css('color','red');
                }
                if(userName==''){
                    $('#nameInfo').html('请填写姓名！');
                    $('#nameInfo').css('color','red');
                }
                exit(1);
            }
            $.ajax({
                type:"post",
                url:"/userUpdate",
                async:true,
                timeout:10000,
                data:$("#frmUserUpdate").serialize(),
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
    userSelectOne(id);
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
    $('#idToUpdate').val(msg.id);
    $('#num').val(msg.num);
    $('#numToUpdate').val(msg.num);
    $('#userName').val(msg.userName);
    $('#tel').val(msg.tel);
    $('#sex').val(msg.sex);
    $('#groupId').val(msg.groupId);
    $('#roleId').val(msg.roleId);
}

