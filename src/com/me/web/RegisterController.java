package com.me.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.imageio.spi.RegisterableService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.me.service.RegisterService;
import com.me.util.CreateRandomMessage;
import com.me.util.DataCache;
import com.me.util.Logging;

@Controller
public class RegisterController {

	@Autowired
	private RegisterService registerService;
	
	Logging logging=new Logging();
	private static Logger logger=LoggerFactory.getLogger(RegisterController.class);
	
	@RequestMapping(value = "/getCode.do")
	public void RegisterUser(HttpServletRequest request,
			RegisterCommand registerCommand, HttpServletResponse response)
			throws IOException {
		JSONObject resultDataJsonObject = new JSONObject();
		if (registerCommand.getRole().equals("普通用户")) {

			if (registerService.checkUserIsExist(registerCommand)) {
				resultDataJsonObject.put("check", "isExist");
			} else {
				String checkCode = CreateRandomMessage.createSixRandow();
				DataCache.checkCodeMap.put(registerCommand.getUserID(),
						checkCode);
				resultDataJsonObject.put("check", checkCode);
			}
		} else if (registerCommand.getRole().equals("抢单人员")) {
			if (registerService.checkExpressUserIsExist(registerCommand)) {
				resultDataJsonObject.put("check", "isExist");
			} else {
				String checkCode = CreateRandomMessage.createSixRandow();
				DataCache.checkCodeMapExpress.put(registerCommand.getUserID(),
						checkCode);
				resultDataJsonObject.put("check", checkCode);
			}
		}
		logging.userLog(JSONObject.fromObject(registerCommand).toString()+JSONObject.fromObject(resultDataJsonObject).toString());
		logger.info(JSONObject.fromObject(registerCommand).toString()+JSONObject.fromObject(resultDataJsonObject).toString());
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		/*
		 * OutputStream outputStream = response.getOutputStream();
		 * outputStream.write(resultDataJsonObject.toString().getBytes());
		 * outputStream.flush(); outputStream.close();
		 */
		PrintWriter out = response.getWriter();
		out.print(resultDataJsonObject);
		out.flush();
		out.close();

	}

	@RequestMapping(value = "/register.do")
	public void CheckUser(HttpServletRequest request,
			RegisterCommand registerCommand, HttpServletResponse response)
			throws IOException {
		JSONObject resultDataJsonObject = new JSONObject();
		if (registerCommand.getRole().equals("普通用户")) {
			if (registerService.registerConsumer(registerCommand)) {
				resultDataJsonObject.put("registerResult", "success");
			} else {
				resultDataJsonObject.put("registerResult", "timeout");
			}

		} else if (registerCommand.getRole().equals("抢单人员")) {

			if (registerService.registerExpressPerson(registerCommand)) {
				resultDataJsonObject.put("registerResult", "success");
			} else {
				resultDataJsonObject.put("registerResult", "timeout");
			}

		}
		logging.userLog(JSONObject.fromObject(registerCommand).toString()+JSONObject.fromObject(resultDataJsonObject).toString());
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(resultDataJsonObject);
		out.flush();
		out.close();

	}

	@RequestMapping(value = "/check.do")
	public void CheckUserReturn(HttpServletRequest request,
			RegisterCommand registerCommand, HttpServletResponse response)
			throws IOException {
		JSONObject resultDataJsonObject = new JSONObject();
		String checkCode = "";
		if (registerCommand.getRole().equals("普通用户")) {
			checkCode = DataCache.checkCodeMap.get(registerCommand.getUserID());
			if (checkCode.equals(registerCommand.getCheckCode())) {				
					resultDataJsonObject.put("checkResult", "success");
					DataCache.checkCodeMap.remove(registerCommand.getUserID());
			} else {
				resultDataJsonObject.put("checkResult", "error");
			}
		} else if (registerCommand.getRole().equals("抢单人员")) {
			checkCode = DataCache.checkCodeMapExpress.get(registerCommand
					.getUserID());
			if (checkCode.equals(registerCommand.getCheckCode())) {			
					resultDataJsonObject.put("checkResult", "success");
					DataCache.checkCodeMapExpress.remove(registerCommand
							.getUserID());
			} else {
				resultDataJsonObject.put("checkResult", "error");
			}
		}
		logging.userLog(JSONObject.fromObject(registerCommand).toString()+JSONObject.fromObject(resultDataJsonObject).toString());
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		/*
		 * OutputStream outputStream = response.getOutputStream();
		 * outputStream.write(resultDataJsonObject.toString().getBytes());
		 * outputStream.flush(); outputStream.close();
		 */
		PrintWriter out = response.getWriter();
		out.print(resultDataJsonObject);
		out.flush();
		out.close();

	}
}
