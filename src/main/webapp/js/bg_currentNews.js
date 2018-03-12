var newsContent1;
var newsContent2;
var newsContent3;
var newsTitle="";
var newsContent="";
var newsContents;
$(document).ready(function(){
	funNewsSelectAll();
});

function funNewsSelectAll(){
		$.ajax({
			type:"post",
			url:"/newsSelectCurrentNews",
			async:true,
			timeout:10000,
			data:"whichNum="+1,
			dataType:"json",
			success:function(msg){
				console.log(msg);
				var msgSize = msg.length;
				var tContent="";
				newsContents =new Array();
				for(var i = 0; i < msg.length; i++) {
					tContent="";
					tContent+="<h1>";
					tContent+=msg[i].title;
					tContent+="</h1>";
					tContent+="<p>";
					tContent+=msg[i].content;
					tContent+="</p>";
					newsContents[i]=tContent;
				}
/*				newsContent1="";
				newsContent1+="<h1>";
				newsContent1+=msg.title;
				newsContent1+="</h1>";
				newsContent1+="<p>";
				newsContent1+=msg.content;
				newsContent1+="</p>";*/
				console.log(newsContents);
				newsContent1=newsContents.length>=1?newsContents[0]:"";
				newsContent2=newsContents.length>=2?newsContents[1]:"";
				newsContent3=newsContents.length>=3?newsContents[2]:"";
				$("#newsContent1").html(newsContent1);
				$("#newsContent2").html(newsContent2);
				$("#newsContent3").html(newsContent3);
				
				newsTitle = msg[0].title;
				newsContent = msg[0].content;
/*				$("#newsTitle").html(newsTitle);
				$("#newsContent").html(newsContent);*/
			},
			error:function(msg){
				console.log(msg);
			}
		});
}

