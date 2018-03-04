package com.me.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.me.domain.Wallet;
import com.me.service.ImproveInformtionService;
import com.me.util.CalculateLnglat;
import com.me.util.DataCache;

@Controller
public class ImproveInformationController {

	@Autowired
	private ImproveInformtionService improveInformtionService;

	@RequestMapping(value = "/improve.do")
	// 更新清单人员信息，一项一项的更新
	public void improveExpressUser(HttpServletRequest request,
			ImproveCommand improveCommand, HttpServletResponse response,
			HttpSession session) throws IOException {
		JSONObject resultDataJsonObject = new JSONObject();
		if (improveInformtionService.updateExpressUser(improveCommand) > 0) {
			resultDataJsonObject.put("state", "success");
		} else {
			resultDataJsonObject.put("state", "error");
		}
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

	@RequestMapping(value = "/updateUser.do")
	// 更新普通用户，一项一项的更新
	public void updateUser(HttpServletRequest request,
			ImproveCommand improveCommand, HttpServletResponse response,
			HttpSession session) throws IOException {
		JSONObject resultDataJsonObject = new JSONObject();
		if (improveInformtionService.updateUser(improveCommand)) {
			resultDataJsonObject.put("state", "success");
		} else {
			resultDataJsonObject.put("state", "error");
		}
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(resultDataJsonObject);
		out.flush();
		out.close();
	}

	@RequestMapping(value = "/updateWallet.do")
	public void updateWallet(HttpServletRequest request, Wallet wallet,
			HttpServletResponse response) throws IOException {
		JSONObject jsonObject = improveInformtionService.updateWallet(wallet);
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonObject);
		out.flush();
		out.close();
	}

	@RequestMapping(value = "/addLocationInfor.do")
	public void addLocationInfor(HttpServletRequest request,
			LocationCommand locationCommand, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (locationCommand.getRole().equals("抢单人员")) {
			Map<String, Double> map = new HashMap<String, Double>();
			map.put("lat", locationCommand.getLat());
			map.put("lng", locationCommand.getLng());
			DataCache.userLocationMap.put(locationCommand.getUserId(), map);
			out.print("success");
			out.flush();
			out.close();
		}
		if (locationCommand.getRole().equals("普通用户")) {
			List<Map<String, Double>> list = DataCache.getAllInstance(
					locationCommand, DataCache.userLocationMap);
			JSONArray jsonArray = JSONArray.fromObject(list);
			/*
			 * for (int i = 0; i < list.size(); i++) { for(Entry<String, Double>
			 * entry:list.get(i).entrySet()){
			 * out.print(list.get(i).get(entry.getKey()));
			 * out.print(list.get(i).get(entry.getValue())); } }
			 */
			out.print(jsonArray);
			out.flush();
			out.close();
		}

	}

	@RequestMapping(value = "/getAllExuser.do")
	public void getAllExuser(HttpServletRequest httpServletRequest,
			HttpServletResponse response) throws Exception {
			JSONArray jsonArray=new JSONArray();
		if (improveInformtionService.getAllExuser() != null) {
			jsonArray = improveInformtionService.getAllExuser();
		}
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonArray);
		out.flush();
		out.close();
	}

}
