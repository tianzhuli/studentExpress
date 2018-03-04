package com.me.util;

import java.sql.Timestamp;
import java.util.Iterator;


import net.sf.json.JSONObject;

public class JsonUtil {

	public static String jsonToTimestamp(JSONObject jsonObject) {
		String year = String.valueOf((Integer.parseInt(jsonObject.getString("year"))+1900));
		// 月份重0开始，所以要加1，当小于10月时，为了显示2位的月份，所以补0
		String month = Integer.parseInt(jsonObject.getString("month")) + 1 < 10 ? "0"
				+ (Integer.parseInt(jsonObject.getString("month")) + 1)
				: jsonObject.getString("month") ;
		String date = Integer.parseInt(jsonObject.getString("date")) < 10 ? "0"
				+ jsonObject.getString("date") : jsonObject.getString("date");
		String hour = Integer.parseInt(jsonObject.getString("hours")) < 10 ? "0"
				+ jsonObject.getString("hours")
				: jsonObject.getString("hours");
		String minute = Integer.parseInt(jsonObject.getString("minutes")) < 10 ? "0"
				+ jsonObject.getString("minutes")
				: jsonObject.getString("minutes");
		String second = Integer.parseInt(jsonObject.getString("seconds")) < 10 ? "0"
				+ jsonObject.getString("seconds")
				: jsonObject.getString("seconds");
		return year + "-" + month + "-" + date + " " + hour + ":" + minute
				+ ":" + second;
	}
	
	
	public static String jsonToDate(JSONObject jsonObject) {
		String year = String.valueOf((Integer.parseInt(jsonObject.getString("year"))+1900));
		// 月份重0开始，所以要加1，当小于10月时，为了显示2位的月份，所以补0
		int month = Integer.parseInt(jsonObject.getString("month")) + 1;
		int date = Integer.parseInt(jsonObject.getString("date")); 
		String hour = Integer.parseInt(jsonObject.getString("hours")) < 10 ? "0"
				+ jsonObject.getString("hours")
				: jsonObject.getString("hours");
		String minute = Integer.parseInt(jsonObject.getString("minutes")) < 10 ? "0"
				+ jsonObject.getString("minutes")
				: jsonObject.getString("minutes");
		String second = Integer.parseInt(jsonObject.getString("seconds")) < 10 ? "0"
				+ jsonObject.getString("seconds")
				: jsonObject.getString("seconds");
		return year + "年" + month + "月" + date + "日 " + hour + ":" + minute
				+ ":" + second;
	}
	
	public static JSONObject jsonToBean(JSONObject jsonObject){
		Iterator iterator = jsonObject.keys();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String value = jsonObject.getString(key);
				if (value.contains("{")) {
					String jsonString = JsonUtil.jsonToTimestamp(JSONObject
							.fromObject(value));
					jsonObject.put(key, Timestamp.valueOf(jsonString));
				}
		}
		return jsonObject;
		
	}
}
