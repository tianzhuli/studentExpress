$(document).ready(function(){
	$(".list").css("display","none");
	$(".result-wrap").css("display","none");
	$.ajax({
		 type: "post",
		 url: "/studentExpress/managerCheck.do",
		 dataType: "json",
	success: function (simuData) {
		if (!simuData){	
			window.location.href="login.html";
		}
	
	},
});
});