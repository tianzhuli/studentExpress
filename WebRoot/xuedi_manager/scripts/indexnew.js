$(document).ready(function(){
	$(".list").css("display","none");
	$(".result-wrap").css("display","none");
   	$("#tupian").click(function(){
   		$(".result-wrap").css("display","none");
   		$(".list").css("display","block");
   		$(".first").remove();
   	 $.ajax({
         url:"/studentExpress/activeExpress.do",
         type:"post",
         dataType: "json",
         success:function(imageConten){
             //alert("查询成功");
        	 var StringContent='';
     		for(ima in imageConten){
     		StringContent = StringContent+'<div class="first"><div class="imagelist" id="imagelistid">'  
                                                     +'<a class="link" href='
                                                     +imageConten[ima].exuserStuimageUrl  
                                                     +' target="_blank">' 
                                                     +'<div class="image" href=""><img width="180px" height="130px" '
                                                     +'src='+imageConten[ima].exuserStuimageUrl+' alt="用户ID：'+imageConten[ima].exuserID+"\""+'>' 
                                                     +'</div><h7>用户ID：'+imageConten[ima].exuserID+'</h7><h6>学号：'+imageConten[ima].exuserStuno+'</h6><h6>身份证：'+imageConten[ima].exuserCardID+'</h6></a>'
                                                     +'<span class="playli">管理员意见:</span><br>'
                                                     +'<select name="mark" size="1" onchange="check(this,'+imageConten[ima].exuserID+')"><option value="select">选择</option><option value="active">激活</option><option value="reject">驳回</option>'
                                                     +'</select>'
                                                 +'</div>' 
                                             +'</div>';	
     	}
     		$(".list").append(StringContent);
         },
         error: function(XMLHttpRequest, textStatus, errorThrown) { 
         	alert(XMLHttpRequest.status); 
         	alert(XMLHttpRequest.readyState); 
         	alert(textStatus);   
         	}
     });  
   		/*var imageConten=[{'exuserID':'13584938483','exuserName':'张三','exuserStuno':'20141414156','exuserCardID':'5003932881928383838','exuserStuimageUrl':'/studentExpress/ImageStu/xuesheng1.jpg'},
	{'exuserID':'13584938483','exuserName':'张三','exuserStuno':'20141414156','exuserCardID':'5003932881928383838','exuserStuimageUrl':'/studentExpress/ImageStu/xuesheng2.jpg'},
	{'exuserID':'13584938483','exuserName':'张三','exuserStuno':'20141414156','exuserCardID':'5003932881928383838','exuserStuimageUrl':'/studentExpress/ImageStu/xuesheng3.jpg'}];*/
		
   	})
	$("#gonggao").click(function(){
		$(".list").css("display","none");
		$(".result-wrap").css("display","block");
	})
  $(".fabu").click(function(){
      var form = new FormData(document.getElementById("myform"));
       $.ajax({
                url:"/studentExpress/insertInformation.do",
                type:"post",
                data:form,
                dataType: "json",
                processData:false,
                contentType:false,
                success:function(outdata){
                    alert("上传成功");
                    $("input[type=reset]").trigger("click");
                    //alert(outdata);
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) { 
                	alert(XMLHttpRequest.status); 
                	alert(XMLHttpRequest.readyState); 
                	alert(textStatus);   
                	alert("上传失败！！");}
            });  
  })
  
     	$("#gonggaomanager").click(function(){
   		$(".result-wrap").css("display","none");
   		$(".list").css("display","block");
   		$(".first").remove();
   	 $.ajax({
         url:"/studentExpress/selecInformation.do",
         type:"post",
         dataType: "json",
         success:function(imageConten){
        	 var StringContent='';
     		for(ima in imageConten){
     		StringContent = StringContent+'<div class="first"><div class="imagelist" id="imagelistid">'  
                                                     +'<a class="link" href='
                                                     +imageConten[ima].informationUrl  
                                                     +' target="_blank">' 
                                                     +'<div class="image" href=""><img width="180px" height="130px" '
                                                     +'src='+imageConten[ima].exuserStuimageUrl+' alt="用户ID：'+imageConten[ima].informationTitle+"\""+'>' 
                                                     +'</div><h7>公告名称：'+imageConten[ima].informationTitle+'</h7><h6>类型：'+imageConten[ima].informationType+'</h6><h6>内容：'+imageConten[ima].informationContent+'</h6><h6>发布时间：'+imageConten[ima].informationTime+'</h6></a>'
                                                     +'<span class="playli">管理员意见:</span><br>'
                                                     +'<select name="mark" size="1" onchange="updateInformation(this,'+imageConten[ima].systemInformationID+')"><option value="select">选择</option><option value="delete">删除</option><option value="update">更新</option>'
                                                     +'</select>'
                                                 +'</div>' 
                                             +'</div>';	
     	}
     		$(".list").append(StringContent);
         },
         error: function(XMLHttpRequest, textStatus, errorThrown) { 
         	alert(XMLHttpRequest.status); 
         	alert(XMLHttpRequest.readyState); 
         	alert(textStatus);   
         	}
     });  
   		/*var imageConten=[{'exuserID':'13584938483','exuserName':'张三','exuserStuno':'20141414156','exuserCardID':'5003932881928383838','exuserStuimageUrl':'/studentExpress/ImageStu/xuesheng1.jpg'},
	{'exuserID':'13584938483','exuserName':'张三','exuserStuno':'20141414156','exuserCardID':'5003932881928383838','exuserStuimageUrl':'/studentExpress/ImageStu/xuesheng2.jpg'},
	{'exuserID':'13584938483','exuserName':'张三','exuserStuno':'20141414156','exuserCardID':'5003932881928383838','exuserStuimageUrl':'/studentExpress/ImageStu/xuesheng3.jpg'}];*/
		
   	})
});