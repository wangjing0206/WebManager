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
            	var status="";
            	if(msg[i].status==1){
            		cellClass="success";
                    status="OnLine";
            	}else{
            		cellClass="danger";
                    status="OffLine";
            	}
                tbody0+="<tr class='"+cellClass+"'>";

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
