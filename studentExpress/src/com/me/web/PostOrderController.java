package com.me.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.me.domain.PostOrder;
import com.me.domain.PostReceiveAddress;
import com.me.domain.PostSendAddress;
import com.me.domain.ReceiveAddress;
import com.me.service.PostAddressService;
import com.me.service.PostOrderService;
import com.me.util.DataCache;

@Controller
public class PostOrderController {

	@Autowired
	private PostOrderService postOrderService;

	@Autowired
	private PostAddressService postAddressService;

	@RequestMapping(value = "/addPostOrder.do")
	public void addPostOrder(HttpServletRequest request, PostOrder postOrder,
			HttpServletResponse response) throws IOException {
		JSONObject resultDataJsonObject = postOrderService
				.insertPostOrderDynamic(postOrder);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(resultDataJsonObject);
		out.flush();
		out.close();

	}

	@RequestMapping(value = "/addPostSendAddress.do")
	public void addPostSendAddress(HttpServletRequest request,
			HttpServletResponse response, PostSendAddress postSendAddress)
			throws Exception {

		JSONObject resultDataJsonObject = new JSONObject();
		try {
			int postSendAddressID = postAddressService
					.insertPostSendAddress(postSendAddress);
			resultDataJsonObject.put("key", postSendAddressID);
			resultDataJsonObject.put("state", "successs");
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

	@RequestMapping(value = "/addPostReceiveAddress.do")
	public void addPostReceiveAddress(HttpServletRequest request,
			HttpServletResponse response, PostReceiveAddress postReceiveAddress)
			throws Exception {
		JSONObject resultDataJsonObject = new JSONObject();
		try {
			int postReceiveAddressID = postAddressService
					.insertPostReceiveAddressXml(postReceiveAddress);
			resultDataJsonObject.put("key", postReceiveAddressID);
			resultDataJsonObject.put("state", "successs");
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

	@RequestMapping(value = "/updatePostOrder.do")
	public void updatePostOrder(HttpServletRequest request,
			PostOrder postOrder, HttpServletResponse response) throws Exception {
		JSONObject resultDataJsonObject = new JSONObject();
		try {
			if (postOrderService.updatePostOrder(postOrder)) {
				resultDataJsonObject.put("state", "successs");
			} else {
				resultDataJsonObject.put("state", "error");
			}
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

	@RequestMapping(value = "/getUserPostOrder.do")
	// 按照订单号、一定条件来获取订单的信息
	public void getUserPostOrder(HttpServletRequest request,
			GetPostOrderCommand getPostOrderCommand,
			HttpServletResponse response) throws Exception {
		JSONArray jsonArray = new JSONArray();
		JSONObject resultDataJsonObject = new JSONObject();
		try {
			jsonArray = JSONArray.fromObject(postOrderService
					.selectPostOrder(getPostOrderCommand));
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

	@RequestMapping(value = "/getPostOrderByView.do")
	// 有抢单人员的订单
	public void getPostOrderByView(HttpServletRequest request,
			GetPostOrderCommand getPostOrderCommand,
			HttpServletResponse response) throws IOException {
		JSONArray jsonArray = new JSONArray();
		JSONObject resultDataJsonObject = new JSONObject();
		try {
			jsonArray = JSONArray.fromObject(postOrderService
					.selectPostOrderView(getPostOrderCommand));
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

	@RequestMapping(value = "/getPostOrderInByView.do")
	// 对于无抢单人员的订单
	public void getPostOrderInAddressByView(HttpServletRequest request,
			GetPostOrderCommand getPostOrderCommand,
			HttpServletResponse response) throws IOException {
		JSONArray jsonArray = new JSONArray();
		JSONObject resultDataJsonObject = new JSONObject();
		try {
			jsonArray = JSONArray.fromObject(postOrderService
					.selectPostAddressView(getPostOrderCommand));
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

	@RequestMapping(value = "/getUserPostOrderState.do")
	// 返回count数字，long类型
	public void getUserPostOrderState(HttpServletRequest request,
			GetPostOrderCommand getPostOrderCommand,
			HttpServletResponse response) throws Exception {
		JSONArray jsonArray = new JSONArray();
		JSONObject resultDataJsonObject = new JSONObject();
		try {
			jsonArray = JSONArray.fromObject(postOrderService
					.selectByState(getPostOrderCommand.getUserID()));
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

	@RequestMapping(value = "/deletePostOrder.do")
	public void deletePostOrder(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		JSONObject resultDataJsonObject = new JSONObject();
		String postOrderID = request.getParameter("postOrderID");
		resultDataJsonObject = postOrderService.deleteOrder(postOrderID);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(resultDataJsonObject);
		out.flush();
		out.close();
	}

	@RequestMapping(value = "/deletePostSendAddress.do")
	public void deletePostSendAddress(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		JSONObject resultDataJsonObject = new JSONObject();
		int postAddressID = Integer.valueOf(request
				.getParameter("postAddressID"));
		resultDataJsonObject = postAddressService
				.deletePostSendAddress(postAddressID);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(resultDataJsonObject);
		out.flush();
		out.close();
	}

	@RequestMapping(value = "/deletePostReceiveAddress.do")
	public void deletePostReceiveAddress(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		JSONObject resultDataJsonObject = new JSONObject();
		int receiveAddressID = Integer.valueOf(request
				.getParameter("receiveAddressID"));
		resultDataJsonObject = postAddressService
				.deletePostReceiveAddress(receiveAddressID);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(resultDataJsonObject);
		out.flush();
		out.close();
	}

	@RequestMapping(value = "/getPostReceiveAddressById.do")
	public void getPostReceiveAddressById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String userID = request.getParameter("userID");
		List<PostReceiveAddress> postReceiveAddresses = null;
		JSONArray jsonArray = null;
		try {
			postReceiveAddresses = postAddressService
					.findPostReceiveAddressesByUserId(userID);
			jsonArray = JSONArray.fromObject(postReceiveAddresses);
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

	@RequestMapping(value = "/getPostSendAddressById.do")
	public void getPostSendAddressById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String userID = request.getParameter("userID");
		List<PostSendAddress> postSendAddresses = null;
		JSONArray jsonArray = null;
		try {
			postSendAddresses = postAddressService
					.selectPostSendAddressesByUserId(userID);
			jsonArray = JSONArray.fromObject(postSendAddresses);
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

	@RequestMapping(value = "/seizePostOrder.do")
	public void seizePostOrder(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		String postorderID = request.getParameter("postorderID");
		String exuserID = request.getParameter("exuserID");
		PostOrder postOrder = DataCache.seizePostOrder(postorderID, exuserID);
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (postOrder != null) {
			try {
				postOrderService.updatePostOrder(postOrder);
				out.print("success");
				out.flush();
				out.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				out.print("error");
				out.flush();
				out.close();
			}

		} else {
			out.print("null");
			out.flush();
			out.close();
		}

	}

	@RequestMapping(value = "/sendPostOrder.do")
	public void sendOrder(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = null;
		try {
			jsonArray = DataCache.sendPostOrderToExpress();
			if (jsonArray == null) {
				out.print("null");
				out.flush();
				out.close();
			} else {
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
