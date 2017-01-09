package com.me.web;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.me.util.DataCache;
import com.me.util.Logging;

@Controller
public class SystemController {
	Logging logging=new Logging();
	@RequestMapping(value = "/quitSystem.do")
	public void quitSystem(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String userID=request.getParameter("userID");
		String role=request.getParameter("role");
		if (role.equals("抢单人员")) {
			DataCache.expressPersonOnlineMap.remove(userID);
			if (DataCache.userLocationMap.containsKey(userID)) {
				DataCache.userLocationMap.remove(userID);
			}
		}
		else if (role.equals("普通用户")) {
			DataCache.userOnlineList.remove(userID);
		}
	}
	
	@RequestMapping(value = "/getOnlineExpress.do")
	public void getOnlineExpress(HttpServletRequest request,HttpServletResponse response){
		logging.userLog(String.valueOf(DataCache.userOnlineList.size()));
		logging.userLog(String.valueOf(DataCache.userLocationMap.size()));
	}
}
