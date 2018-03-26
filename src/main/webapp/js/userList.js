var tbodyUser;
$(document).ready(function(){
    //模糊查询取页
    getCount();
    userSearchAll(1);

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
function getCount(){
    $.ajax({
        type:"get",
        url:"/getCountForSearch",
        async:true,
        timeout:10000,
        data:$("#frmSearch").serialize(),
        success:function(msg){
            console.log(msg);
            countToPage(msg);
        },
        error:function(msg){
            console.log(msg)
        }
    });
}
function countToPage(msg){
    var totalPage=Math.ceil(msg/10);
    $('#totalPage').html(totalPage);
    $('#totalPage').val(totalPage);
    if (totalPage>0) {
        $('#thisPage').html(1);
        $('#thisPage').val(1);
        $('#noPage').html('');
        $('#pageUl').show();
    }else{
        $('#thisPage').html(0);
        $('#thisPage').val(0);
        $('#pageUl').hide();
        $('#noPage').html('查询不到符合该条件的用户信息！');
    }
}
function userSearchAll(whichNum){
    $('#thisPage').html(whichNum);
    $('#thisPage').val(whichNum);
    $.ajax({
        type:"post",
        url:"/userSearchAll",
        async:true,
        timeout:10000,
        data:$("#frmSearch").serialize()+"&whichNum="+whichNum,
        success:function(msg){
            console.log(msg);
            tbodyUser=" ";
            for (var i = 0; i < msg.length; i++) {
                tbodyUser+="<tr class='info'>";
                tbodyUser+="<td>";
                tbodyUser+=(whichNum-1)*10+1+i;
                tbodyUser+="</td>";
                tbodyUser+="<td>";
                tbodyUser+=msg[i].num==null?"---":msg[i].num;
                tbodyUser+="</td>";
                tbodyUser+="<td>";
                tbodyUser+=msg[i].userName==null?"---":msg[i].userName;
                tbodyUser+="</td>";
                tbodyUser+="<td>";
                tbodyUser+=msg[i].sex==1?"男":"女";
                tbodyUser+="</td>";
                tbodyUser+="<td>";
                tbodyUser+=msg[i].tel==null?"---":msg[i].tel;
                tbodyUser+="</td>";
                tbodyUser+="<td>";
                tbodyUser+=msg[i].groupName==null?"---":msg[i].groupName;
                tbodyUser+="</td>";
                tbodyUser+="<td>";
                tbodyUser+=msg[i].roleName==null?"---":msg[i].roleName;
                tbodyUser+="</td>";
                tbodyUser+="<td>";
                tbodyUser+="<a href='javascript:void(0)' id=userUpdate"+msg[i].id+" onclick='userUpdate("+msg[i].id+")'>更新</a>";
                tbodyUser+="</td>";
                tbodyUser+="<td>";
                tbodyUser+="<a href='javascript:void(0)'style='color: #CC0000' id=userDelete"+msg[i].id+" onclick='userDelete("+msg[i].id+")'>删除</a>";
                tbodyUser+="</td>";
                tbodyUser+="</tr>";
            }
            $("#tbodyUser").html(tbodyUser);
            pageButton();
        },
        error:function(msg){
            console.log(msg);
        }
    });
}
function userSearchAllPageUp(){
    var whichNum=$('#thisPage').val()-1;
    $('#thisPage').html(whichNum);
    $('#thisPage').val(whichNum);
    userSearchAll(whichNum);
}
function userSearchAllPageDown(){
    var whichNum=parseInt($('#thisPage').val())+1;
    $('#thisPage').html(whichNum);
    $('#thisPage').val(whichNum);
    userSearchAll(whichNum);
}
$("#btnUerSearchAll").click(function(){
    getCount();
    userSearchAll(1);
});
$("#userSelectAll").click(function(){
    $('#num1').val("");
    $('#userName1').val("");
    $('#groupName1').val("");
    $('#roleName1').val("");
    $('#goPage').val("");
    getCount();
    userSearchAll(1);
});
$("#pageToSearch").click(function(){
    var goPage=$('#goPage').val();
    var totalPage=$('#totalPage').val();
    if(goPage<1||goPage>totalPage){
        alert('查询范围(1～'+totalPage+')');
        return false;
    }
    userSearchAll(goPage);
});
function pageButton(){
    var thisPage=$('#thisPage').val();
    var totalPage=$('#totalPage').val();
    if(thisPage<2){
        $('#lastPage').hide();
        $('#nextPage').show();
    }else if(thisPage>=totalPage){
        $('#lastPage').show();
        $('#nextPage').hide();
    }else{
        $('#lastPage').show();
        $('#nextPage').show();
    }
}
function userDelete(id){
    SimplePop.confirm("确定删除此用户吗？",{
        type: "confirm",
        cancel: function(){

        },
        confirm: function(){
            $.ajax({
                type:"get",
                url:"/userDelete",
                async:true,
                timeout:10000,
                data:"id="+id,
                dataType:"json",
                success:function(msg){
                    console.log(msg);
                    if(msg==1){
                        success();
                    }else{
                        error();
                    }
                },
                error:function(msg){
                    console.log(msg);
                    error();
                }
            });
        }
    });
}
function checkNum(){
    var num=$('#num').val();
    var numToUpdate=$('#numToUpdate').val();
    if(num==numToUpdate||num==''){
        $('#numInfo').html('');
        return false;
    }
    $.ajax({
        type:"post",
        url:"/checkNum",
        async:true,
        timeout:10000,
        data:"num="+num,
        success:function(msg){
            console.log(msg);
            if (msg>=1) {
                $('#numInfo').css('color','red');
                $('#numInfo').html('工号已存在!');
                $('#num').focus();
            }else{
                $('#numInfo').css('color','green');
                $('#numInfo').html('工号可以使用!');
            }
        },
        error:function(msg){
            console.log(msg)
        }
    });
}


