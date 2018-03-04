package com.me.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SessionToken {
	public static Object tokenLock = new Object();
	public static Map<String, String> sessionToken = new HashMap<String, String>(); // token值为sessionID和时间戳
	public static Map<String, String> expresssessionToken = new HashMap<String, String>();

	public static boolean compare(String token) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		String tokenTimer = token.split("_")[1];
		Calendar calendar = Calendar.getInstance();
		String now = simpleDateFormat.format(calendar.getTime());
		System.out.println(now);
		long days = 0;
		try {
			long diff = simpleDateFormat.parse(now).getTime() - simpleDateFormat.parse(tokenTimer).getTime();
			days = diff / (1000 * 60 * 60 * 24);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (days >= 15) {
			return false;
		} else {
			return true;
		}
	}

	public static void main(String[] arg0) {
		System.out.println(SessionToken.compare("1234_2016-10-14 11:15:20"));
	}
}
