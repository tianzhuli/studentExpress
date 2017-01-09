package com.me.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.me.dao.ReceiveAddressDao;
import com.me.domain.PostReceiveAddress;
import com.me.domain.ReceiveAddress;
import com.me.domain.ReceiveOrder;
import com.me.service.ReceiveAddressService;
import com.me.service.ReceiveOrderService;
import com.me.util.DataCache;

@Controller
public class ReceiveOrderController {

	@Autowired
	private ReceiveOrderService receiveOrderService;

	@Autowired
	private ReceiveAddressDao receiveAddressDao;

	@Autowired
	private ReceiveAddressService receiveAddressService;

	@RequestMapping(value = "/receiveOrderUpdate.do")
	public void receiveOrder(HttpServletRequest request,
			ReceiveOrder receiveOrder, HttpServletResponse response)
			throws IOException {
		JSONObject jsonObject = receiveOrderService
				.updateReceiveOrder(receiveOrder);
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonObject);
		out.flush();
		out.close();

	}

	@RequestMapping(value = "/receiveOrderAdd.do")
	public void receiveOrderAdd(HttpServletRequest request,
			ReceiveOrder receiveOrder, HttpServletResponse response)
			throws IOException {
		JSONObject jsonObject = receiveOrderService
				.createReceiveOrderDynamic(receiveOrder);
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonObject);
		out.flush();
		out.close();
	}

	@RequestMapping(value = "/addReceiveAddress.do")
	public void addPostReceiveAddress(HttpServletRequest request,
			HttpServletResponse response, ReceiveAddress receiveAddress)
			throws Exception {
		JSONObject resultDataJsonObject = new JSONObject();
		try {
			// receiveAddressDao.insertRecieveAdress(receiveAddress);
			int key = receiveAddressService.insertAddress(receiveAddress);
			resultDataJsonObject.put("state", "successs");
			resultDataJsonObject.put("key", key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultDataJsonObject.put("state", "error");
		}

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(resultDataJsonObject);
		out.flush();
		out.close();
	}

	@RequestMapping(value = "/getUserReceiveOrder.do")
	// 按照状态或ID获取订单（代领）,没有抢单人员的头像等具体信息
	public void getUserPostOrder(HttpServletRequest request,
			GetReceiveOrderCommand getReceiveOrderCommand,
			HttpServletResponse response) throws Exception {
		JSONArray jsonArray = new JSONArray();
		JSONObject resultDataJsonObject = new JSONObject();
		try {
			jsonArray = JSONArray.fromObject(receiveOrderService
					.selectReceiveOrder(getReceiveOrderCommand));
			resultDataJsonObject.put("state", "successs");
			resultDataJsonObject.put("data", jsonArray);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultDataJsonObject.put("state", "error");
		}

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(resultDataJsonObject);
		out.flush();
		out.close();
	}

	@RequestMapping(value = "/getReceiveOrderByView.do")
	// 返回包括头像等信息
	public void getReceiveOrderByView(HttpServletRequest request,
			GetReceiveOrderCommand getReceiveOrderCommand,
			HttpServletResponse response) throws Exception {
		JSONArray jsonArray = new JSONArray();
		JSONObject resultDataJsonObject = new JSONObject();
		try {
			jsonArray = JSONArray.fromObject(receiveOrderService
					.selectReceiveOrderView(getReceiveOrderCommand));
			resultDataJsonObject.put("state", "successs");
			resultDataJsonObject.put("data", jsonArray);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultDataJsonObject.put("state", "error");
		}

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(resultDataJsonObject);
		out.flush();
		out.close();
	}

	@RequestMapping(value = "/getUserReceiveOrderState.do")                     //包含了订单数量和状态，注意count取出来的是long类型
	// 获取各类状态订单的
	public void getUserPostOrderState(HttpServletRequest request,
			GetReceiveOrderCommand getReceiveOrderCommand,
			HttpServletResponse response) throws Exception {
		JSONArray jsonArray = new JSONArray();
		JSONObject resultDataJsonObject = new JSONObject();
		try {
			jsonArray = JSONArray.fromObject(receiveOrderService
					.findReceiveOrder(getReceiveOrderCommand.getUserID()));
			resultDataJsonObject.put("state", "successs");
			resultDataJsonObject.put("data", jsonArray);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultDataJsonObject.put("state", "error");
		}

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(resultDataJsonObject);
		out.flush();
		out.close();
	}

	@RequestMapping(value = "/deleteReceiveOrder.do")
	public void deleteReceiveOrder(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String orderID = request.getParameter("orderID");
		JSONObject resultDataJsonObject = new JSONObject();
		try {
			receiveOrderService.deleteOrder(orderID);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resultDataJsonObject.put("state", "error");
		}
		resultDataJsonObject.put("state", "success");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(resultDataJsonObject);
		out.flush();
		out.close();
	}

	@RequestMapping(value = "deleteReceiveAddress.do")
	public void deleteReceiveAddress(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int addressID = Integer.valueOf(request.getParameter("addressID"));
		JSONObject resultDataJsonObject = new JSONObject();
		try {
			receiveAddressService.deleteReceiveAddress(addressID);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resultDataJsonObject.put("state", "error");
		}
		resultDataJsonObject.put("state", "success");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(resultDataJsonObject);
		out.flush();
		out.close();
	}

	@RequestMapping(value = "/getReceiveAddressById.do")
	public void getReceiveAddressById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String userID = request.getParameter("userID");
		List<ReceiveAddress> receiveAddresses = null;
		JSONArray jsonArray = null;
		try {
			receiveAddresses = receiveAddressService
					.getReceiveAddresses(userID);
			jsonArray = JSONArray.fromObject(receiveAddresses);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonArray = null;
		}
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonArray);
		out.flush();
		out.close();
	}

	@RequestMapping(value = "/seizeOrder.do")
	public void seizeOrder(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		String orderID = request.getParameter("orderID");
		String exuserID = request.getParameter("exuserID");
		ReceiveOrder receiveOrder = DataCache.seizeOrder(orderID, exuserID);
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (receiveOrder == null) {
			out.print("null");
			out.flush();
			out.close();
		}
		else {
			try {
				receiveOrderService.updateReceiveOrder(receiveOrder);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				out.print("error");
				out.flush();
				out.close();
			}
			out.print("success");
			out.flush();
			out.close();
		}
		
	}

	@RequestMapping(value = "/sendOrder.do")
	public void sendOrder(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = null;
		try {
			jsonArray = DataCache.sendOrderToExpress();
			if (jsonArray == null) {
				out.print("null");
				out.flush();
				out.close();
			}
			else {
				out.print(jsonArray);
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			out.print("error");
			out.flush();
			out.close();
		}
		
	}
	
}
