$(document).ready(function(){
	var imageConten=[{'exuserID':'13584938483','exuserName':'张三','exuserStuno':'20141414156','exuserCardID':'5003932881928383838','exuserStuimageUrl':'../images/xuesheng1.jpg'},
	{'exuserID':'13584938483','exuserName':'张三','exuserStuno':'20141414156','exuserCardID':'5003932881928383838','exuserStuimageUrl':'../images/xuesheng2.jpg'},
	{'exuserID':'13584938483','exuserName':'张三','exuserStuno':'20141414156','exuserCardID':'5003932881928383838','exuserStuimageUrl':'../images/xuesheng3.jpg'}];
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
	//alert(StringContent);
	$(".list").append(StringContent);
	
});