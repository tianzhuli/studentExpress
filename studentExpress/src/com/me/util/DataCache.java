package com.me.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.me.domain.ExpressPerson;
import com.me.domain.PostOrder;
import com.me.domain.ReceiveOrder;
import com.me.service.OrderSeizeService;
import com.me.web.LocationCommand;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.regexp.internal.recompile;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DataCache {
	
	public static Map<String, String> checkCodeMap = new HashMap<String, String>();
	public static Map<String, String> checkCodeMapExpress = new HashMap<String, String>();
	public static List<String> userOnlineList=new ArrayList<String>();
	public static Map<String, Map> expressPersonOnlineMap = new HashMap<String, Map>(); // 在线的抢单人员
	public static Map<String, Map<String, Double>> userLocationMap = new HashMap<String, Map<String, Double>>(); // 将抢单人员的位置信息存到内存
	public static List<String> orderList = new ArrayList<String>(); // 所有的代领订单ID，方便做搜索
	public static Map<String, ReceiveOrder> ordermap = new HashMap<String, ReceiveOrder>();
	public static List<String> postorderList = new ArrayList<String>(); // 所有的代领订单ID，方便做搜索
	public static Map<String, PostOrder> postordermap = new HashMap<String, PostOrder>();

	public static double getInstance(LocationCommand locationCommand,
			String userID) {
		double lat = 0;
		double lng = 0;
		Map<String, Double> userLocation = userLocationMap.get(userID);
		for (Entry<String, Double> entry : userLocation.entrySet()) {
			String key = entry.getKey();
			if (key.equals("lat")) {
				lat = entry.getValue();
			} else {
				lng = entry.getValue();
			}
		}

		return CalculateLnglat.getDistance(locationCommand.getLat(),
				locationCommand.getLng(), lat, lng);
	}

	public static List<Map<String, Double>> getAllInstance(
			LocationCommand locationCommand,
			Map<String, Map<String, Double>> userLocation) {
		List<Map<String, Double>> list = new ArrayList<Map<String, Double>>();
		for (String key : userLocation.keySet()) {
			Map<String, Double> map = new HashMap<String, Double>();
			map.put(key, getInstance(locationCommand, key));
			list.add(map);
		}
		return list;
	}

	public static synchronized ReceiveOrder seizeOrder(String orderId, String exuserId) {
		ReceiveOrder receiveOrder = new ReceiveOrder();
		System.out.println(Thread.currentThread().getName());
		if (!DataCache.orderList.contains(orderId)) {
			return null;
		}
		try {
			receiveOrder = DataCache.ordermap.get(orderId);
			receiveOrder.setDeliverID(exuserId);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			receiveOrder.setOrderState("已接单");
			receiveOrder.setOrderReceiveTime(Timestamp.valueOf(simpleDateFormat.format(new Date())));
			DataCache.ordermap.remove(orderId);
			DataCache.orderList.remove(orderId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return receiveOrder;

	}
	
	public static synchronized PostOrder seizePostOrder(String postorderId, String exuserId) {
		PostOrder postOrder = new PostOrder();
		System.out.println(Thread.currentThread().getName());
		if (!DataCache.postorderList.contains(postorderId)) {
			return null;
		}
		try {
			postOrder = DataCache.postordermap.get(postorderId);
			postOrder.setDeliverID(exuserId);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			postOrder.setPostorderState("已接单");
			postOrder.setPostorderReceiveTime(Timestamp.valueOf(simpleDateFormat.format(new Date())));
			DataCache.postordermap.remove(postorderId);
			DataCache.postorderList.remove(postorderId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return postOrder;

	}

	public static JSONArray sendOrderToExpress() {
		JSONArray jsonArray=new JSONArray();
		Random random=new Random();
		if (orderList.size()>0) {
			boolean[]  bool = new boolean[orderList.size()];
			 int randInt = 0;
			 int sun=orderList.size()<3?orderList.size():3;
			for (int i = 0; i<sun; i++) {//orderList.size()?i<orderList.size():i < 3
				do {
					if (orderList.size()>0) {
						randInt=random.nextInt(orderList.size());
						System.out.println(randInt);						
					}
					else {
						break;
					}					
				} while (bool[randInt]);
				bool[randInt]=true;
				ReceiveOrder receiveOrder=DataCache.ordermap.get(DataCache.orderList.get(randInt));
				jsonArray.add(receiveOrder);
			}
			return jsonArray;
		}
		return null;
	}
	
	
	public static JSONArray sendOrderToExpressnew() {
		JSONArray jsonArray=new JSONArray();
		Random random=new Random();
		if (orderList.size()>0) {
			//orderList.sort(Comparator<T>);
			 int randInt = 0;
			 int sun=orderList.size()<3?orderList.size():3;
			for (int i = 0; i<sun; i++) {//orderList.size()?i<orderList.size():i < 3	
					if (orderList.size()>0) {
						int size=orderList.size()/3;
						//System.out.println(size);
						randInt=random.nextInt(size)+size*i;
						System.out.println(randInt);
						ReceiveOrder receiveOrder=DataCache.ordermap.get(DataCache.orderList.get(randInt));
						jsonArray.add(receiveOrder);			
				} 
			}
			return jsonArray;
		}
		return null;
	}
	public static JSONArray sendPostOrderToExpress() {
		JSONArray jsonArray=new JSONArray();
		Random random=new Random();
		if (postorderList.size()>0) {
			boolean[]  bool = new boolean[postorderList.size()];
			 int randInt = 0;
			 int sun=postorderList.size()<3?postorderList.size():3;
			for (int i = 0; i<sun; i++) {
				do {
					if (postorderList.size()>0) {
						randInt=random.nextInt(postorderList.size());						
					}
					else {
						break;
					}					
				} while (bool[randInt]);
				bool[randInt]=true;
				PostOrder postOrder=DataCache.postordermap.get(DataCache.postorderList.get(randInt));
				jsonArray.add(postOrder);
			}
			return jsonArray;
		}
		return null;
	}
}