package com.me.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.dao.ExpressUserDao;
import com.me.dao.ReceiveOrderDao;
import com.me.dao.ReceiveOrderDaoXml;
import com.me.domain.ReceiveOrder;
import com.me.util.CreateOrderId;
import com.me.util.DataCache;
import com.me.web.GetOrderStateCommand;
import com.me.web.GetReceiveOrderCommand;

@Service
public class ReceiveOrderService {

	@Autowired
	private ReceiveOrderDao receiveOrderDao;

	@Autowired
	private ReceiveOrderDaoXml receiveOrderDaoXml;
	
	@Autowired
	private ExpressUserDao expressUserDao;

	public JSONObject createReceiveOrder(ReceiveOrder receiveOrder) {
		JSONObject jsonObject = new JSONObject();
		try {
			receiveOrder.setOrderID(CreateOrderId.createOrderId());
			receiveOrderDao.createReceiveOrder(receiveOrder);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonObject.put("orderID", "error");
			return jsonObject;
		}
		jsonObject.put("orderID", receiveOrder.getOrderID());
		return jsonObject;

	}
	
	public JSONObject createReceiveOrderDynamic(ReceiveOrder receiveOrder) {
		JSONObject jsonObject = new JSONObject();
		try {
			receiveOrder.setOrderID(CreateOrderId.createOrderId());
			receiveOrderDaoXml.insertReceiveOrder(receiveOrder);
			DataCache.orderList.add(receiveOrder.getOrderID());
			DataCache.ordermap.put(receiveOrder.getOrderID(), receiveOrder);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonObject.put("orderID", "error");
			return jsonObject;
		}
		jsonObject.put("orderID", receiveOrder.getOrderID());
		return jsonObject;

	}

	public JSONObject updateReceiveOrder(ReceiveOrder receiveOrder) {
		JSONObject jsonObject = new JSONObject();
		try {
			if (receiveOrder.getOrderCompleteTime()!=null) {
				expressUserDao.updateExpressPersonTotal(receiveOrder.getDeliverID());
			}
			receiveOrderDaoXml.updateReceiveOrder(receiveOrder);
		} catch (Exception e) {
			// TODO: handle exception
			jsonObject.put("state", "error");
			return jsonObject;
		}
		jsonObject.put("state", "success");
		return jsonObject;
	}

	
	/*
	 * 获取某一个人的所有订单信息，map形式展现
	 */
	public List<Map> findReceiveOrder(String userID) {
		return receiveOrderDao.selectByState(userID);

	}

	public int findByOrder(GetOrderStateCommand getOrderStateCommand) {
		return receiveOrderDao.selectNumState(getOrderStateCommand);                   //查询普通用户某一状态订单下的数量
	}
	
	public List<ReceiveOrder> selectOrderState(GetOrderStateCommand getOrderStateCommand){
		return receiveOrderDao.selectOrderState(getOrderStateCommand);
	}
	
	public List<Map> selectByStateExpress(String deliverID){
		return receiveOrderDao.selectByStateExpress(deliverID);
	}             //抢单人员的订单情况
	
	  //抢单人员各类型订单详情
	public List<ReceiveOrder> selectOrderByStateExpress(GetOrderStateCommand getOrderStateCommand){
		return receiveOrderDao.selectOrderByStateExpress(getOrderStateCommand);
	}
	
	
	public int selectOrderContentExpress(GetOrderStateCommand getOrderStateCommand){
		return receiveOrderDao.selectOrderContentExpress(getOrderStateCommand);
	}
	
	
	public int deleteOrder(String orderID){
		if (DataCache.orderList.contains(orderID)) {
			DataCache.orderList.remove(orderID);
			DataCache.ordermap.remove(orderID);
		}
		return receiveOrderDao.deleteOrder(orderID);
	}
	
	
	public ReceiveOrder selectByOneIdOrder(String orderID){
		return receiveOrderDao.selectByOneIdOrder(orderID);
	}
		
	public List<Map> selectReceiveOrder(GetReceiveOrderCommand getReceiveOrderCommand ){
		return receiveOrderDaoXml.selectReceiveOrder(getReceiveOrderCommand);                   //联合查询返回的是无接单人员信息的订单
	} //获取普通用户或者抢单人员的订单
	
	
	public List<Map> selectReceiveOrderView(GetReceiveOrderCommand getReceiveOrderCommand){
		return receiveOrderDaoXml.selectReceiveOrderView(getReceiveOrderCommand);                //操作视图返回的是有接单人员信息的订单
	}//视图的方式获得信息
	
	public List<ReceiveOrder> selectALLDataCache(){
		try {
			return receiveOrderDao.selectALLDataCache();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}
}
