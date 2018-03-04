/**
 * 
 */
package com.me.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Administrator
 *
 */
public class CreateOrderId {
	public static String createOrderId(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmss");
		String orderId=simpleDateFormat.format(new Date()).toString()+UUID.randomUUID().toString() ;
		return orderId.replace("-", "");
		
	}
	public static String createPostOrderId(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmss");
		String orderId="p"+simpleDateFormat.format(new Date()).toString()+UUID.randomUUID().toString() ;
		return orderId.replace("-", "");
	}
}
