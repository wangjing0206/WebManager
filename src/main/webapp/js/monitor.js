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
            	var cellClass="";
            	var img="";
            	if(msg[i].status==1){
            		cellClass="success";
                    img="<image src='../img/on.png'/>";
            	}else{
            		cellClass="danger";
                    img="<image src='../img/off.png'/>";
            	}
                //tbody0+="<tr class='"+cellClass+"'>";
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
                tbody0+=img;
                tbody0+=status;
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
        funNodeSelectAll();
});
