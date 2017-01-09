$(document).ready(function(){

		$.ajax({
			type: "post",
			url: "/studentExpress/loginCheck.do",
			dataType: "json",
			data: {"userName":"lisi","password":123456},
			error:function(data){
			alert("shibai");},
			success: function (simuData) {
				alert(JSON.stringify(simuData));
			},
		});

	
	
});
		