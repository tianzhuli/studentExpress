package com.me.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.dao.ExpressUserDao;
import com.me.dao.PostOrderDao;
import com.me.dao.PostOrderDaoXml;
import com.me.domain.PostOrder;
import com.me.domain.ReceiveOrder;
import com.me.util.CreateOrderId;
import com.me.util.DataCache;
import com.me.web.GetPostOrderCommand;
import com.me.web.GetReceiveOrderCommand;

@Service
public class PostOrderService {
	@Autowired
	private PostOrderDao postOrderDao;

	@Autowired
	private PostOrderDaoXml postOrderDaoXml;
	
	@Autowired
	private ExpressUserDao expressUserDao;

	public boolean updatePostOrder(PostOrder postOrder) {
		if (postOrder.getPostorderCompleteTime()!=null) {
			expressUserDao.updateExpressPersonTotal(postOrder.getDeliverID());
		}
		return postOrderDaoXml.updatePostOrder(postOrder) > 0;
	}

	public JSONObject insertPostOrder(PostOrder postOrder) {
		JSONObject jsonObject = new JSONObject();
		try {
			postOrder.setPostorderID(CreateOrderId.createPostOrderId());
			postOrderDao.insertPostOrder(postOrder);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonObject.put("orderID", "error");
			return jsonObject;
		}
		jsonObject.put("orderID", postOrder.getPostorderID());
		return jsonObject;
	}

	public JSONObject deleteOrder(String postorderID) {
		JSONObject jsonObject = new JSONObject();
		try {
			if (DataCache.postorderList.contains(postorderID)) {
				DataCache.postordermap.remove(postorderID);
				DataCache.postorderList.remove(postorderID);
			}
			postOrderDao.deleteOrder(postorderID);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonObject.put("orderID", "error");
			return jsonObject;
		}
		jsonObject.put("orderID", "success");
		return jsonObject;
	}

	public List<Map> selectByState(String userID) {
		return postOrderDao.selectByState(userID);
	}

	public int selectNumState(PostOrder postOrder) {
		return postOrderDao.selectNumState(postOrder);
	}

	public List<Map> selectPostOrder(GetPostOrderCommand getPostOrderCommand) {
		return postOrderDaoXml.selectPostOrder(getPostOrderCommand);
	}

	public List<Map> selectByStateExpress(String deliverID) {
		return postOrderDao.selectByStateExpress(deliverID);
	} // 抢单人员的订单情况

	// 抢单人员各类型订单详情
	public List<PostOrder> selectOrderByStateExpress(
			GetPostOrderCommand getPostOrderCommand) {
		return postOrderDao.selectOrderByStateExpress(getPostOrderCommand);
	}

	public int selectOrderContentExpress(GetPostOrderCommand getPostOrderCommand) {
		return postOrderDao.selectOrderContentExpress(getPostOrderCommand);
	}

	public JSONObject insertPostOrderDynamic(PostOrder postOrder) { // 动态生成订单
		JSONObject jsonObject = new JSONObject();
		try {
			postOrder.setPostorderID(CreateOrderId.createPostOrderId());
			postOrderDaoXml.insertPostOrder(postOrder);
			DataCache.postorderList.add(postOrder.getPostorderID());
			DataCache.postordermap.put(postOrder.getPostorderID(), postOrder);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonObject.put("orderID", "error");
			return jsonObject;
		}
		jsonObject.put("orderID", postOrder.getPostorderID());
		return jsonObject;
	}

	public List<Map> selectPostOrderView(GetPostOrderCommand getPostOrderCommand) {
		return postOrderDaoXml.selectPostOrderView(getPostOrderCommand);
	}

	public List<Map> selectPostAddressView(
			GetPostOrderCommand getPostOrderCommand) {
		return postOrderDaoXml.selectPostAddressView(getPostOrderCommand);
	}
	
	public List<PostOrder> selectALLDataCache(){
		try {
			return postOrderDao.selectALLDataCache();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
}
