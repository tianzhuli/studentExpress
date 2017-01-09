/**
 * 
 */
package com.me.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.me.service.LoginService;
import com.me.util.SessionToken;

/**
 * @author Administrator
 * 
 */
@Controller
public class LoginController {

	@Autowired
	private LoginService userService;

	@RequestMapping(value = "/loginCheck.do")
	public void loginCheck(
			HttpServletRequest request, // ModelAndView
			LoginCommand loginCommand, HttpServletResponse response,
			HttpSession session) throws IOException {
/*		JSONObject data = new JSONObject();
		System.out.println(loginCommand.getUserName()
				+ loginCommand.getPassword() + loginCommand.getToken()
				+ (SessionToken.sessionToken.get(loginCommand.getUserName())));
		if (loginCommand.getPassword() != null) {
			boolean isRight = userService.hasMatchLogin(loginCommand);

			if (isRight) {
				response.setContentType("text/html");
				response.setCharacterEncoding("utf-8");
				data.put("state", "firstsuccess");
				data.put("token", session.getId());
				System.out.println(session.getId());
				SessionToken.sessionToken.put(loginCommand.getUserName(),
						session.getId());

			} else {
				data.put("state", "error");
				data.put("error", "用户名或密码错误");
			}
		}

		else if ((SessionToken.sessionToken.get(loginCommand.getUserName()))
				.equals(loginCommand.getToken())
				&& (loginCommand.getToken() != null)
				&& (loginCommand.getUserName() != null)) {
			data.put("state", "success");
		} else {
			data.put("state", "timeout");
		}

		OutputStream outputStream = response.getOutputStream();
		outputStream.write(data.toString().getBytes());
		outputStream.flush();
		outputStream.close();*/
		request.setCharacterEncoding("UTF-8");
		JSONObject resultDataJsonObject=new JSONObject();
		System.out.println(loginCommand.getUserId()
				+ loginCommand.getPassword() + loginCommand.getToken()
				+ loginCommand.getRole());
		if (loginCommand.getRole().equals("普通用户")) {
			resultDataJsonObject=userService.loginGetJson(loginCommand, session);
		}
		else if (loginCommand.getRole().equals("抢单人员")) {
			resultDataJsonObject=userService.loginExpressGetJson(loginCommand, session);
		} 
		//System.out.println(resultDataJsonObject);
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		/*OutputStream outputStream = response.getOutputStream();*/
		
//		outputStream.write(resultDataJsonObject.toString().getBytes());
//		outputStream.flush();
//		outputStream.close();
		PrintWriter out=response.getWriter();
		out.print(resultDataJsonObject);
		out.flush();
		out.close();

	}

	@RequestMapping(value = "/index.do")
	public String loginPage() {
		System.out.println("hehe");
		return "login";
	}
}
