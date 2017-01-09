package com.me.util;

import java.util.Random;

public class CreateRandomMessage {
	public static String createSixRandow(){
		Random random=new Random();
		StringBuffer checkBuffer=new StringBuffer();
		for (int i = 0; i < 6; i++) {
			checkBuffer.append(random.nextInt(10));
		}
		String checkCode=checkBuffer.toString();
		return checkCode;
		
	}
}
