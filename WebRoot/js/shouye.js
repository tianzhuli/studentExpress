
$(document).ready(function(){
	
  monitorShow();    
  function  monitorShow(){
	  if('WebSocket' in window){
         try{
        	Montior_dataPara = new WebSocket("ws://" + location.host + "/waterSpringVersion/echo");
        }catch (e){
        	alert("浏览器不支持WebSocket!");
        }
    }else{
    	alert("浏览器不支持WebSocket!");
    }
	  
	  function dataSend(){
		  var flag1 = {'state':'SvgData',
				    'system':'10',
				    	'id':["101_1","301_1","302_1",
				    	      "302_2","303_1","303_2",
				    	      "304_1","304_2","305_1",
				    	      "305_2","306_1","306_2",
				    	      "307_1","307_2","308_1",
				    	      "308_2","201_1","201_2",
				    	      "201_3","202_1","202_2",
				    	      "202_3","211_1","211_2",
				    	      "211_3","212_1","212_2",
				    	      "212_3","309_1","309_2","102_1"
				    	      ]};
	    Montior_dataPara.send(JSON.stringify(flag1));
	  }
	  Montior_dataPara.onopen = function(simuRealData){
		  alert("hehe");
		  dataSend();
	};
	Montior_dataPara.onerror=function(simuRealData){};
	Montior_dataPara.onmessage = function(simuRealData){
	
		var simuRealDataJson=eval('('+simuRealData.data+')');
		alert(JSON.stringify(simuRealDataJson));
	  
	     /*setTimeout(dataSend, 1000);*/
	 };					
	 Montior_dataPara.onclose = function (simuRealData){};	
	};

	});



  
		
 	
