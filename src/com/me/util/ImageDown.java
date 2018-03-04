package com.me.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

import net.sf.json.JSONObject;

//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;

public class ImageDown {

	public static final String saveRootPath = "D:\\Program Files\\Apache Software Foundation\\Tomcat 8.0\\webapps\\studentExpress\\Image";
	/*
	 * "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.0\\webapps\\studentExpress\\WEB-INF\\Image"
	 * ;
	 */
	public static String readImage(String str_FileName) {
		File file = new File(str_FileName);
		if (file.exists()) {
			BufferedInputStream bis = null;
			byte[] bytes = null;
			try {
				try {
					bis = new BufferedInputStream(new FileInputStream(
							str_FileName));
					bytes = new byte[bis.available()];
					bis.read(bytes);
				} finally {
					bis.close();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "null";
			} catch (IOException e) {
				e.printStackTrace();
			}
			return new String(Base64.encodeBase64Chunked(bytes));
		} else {
			return "null";
		}
		// return new BASE64Encoder().encodeBuffer(bytes);
	}

	public static String findFileSavePathByFileName(String filename,
			String saveRootPath) {
		int hashcode = filename.hashCode();
		int dir1 = hashcode & 0xf; // 0--15
		int dir2 = (hashcode & 0xf0) >> 4; // 0-15
		String dir = saveRootPath + "\\" + dir1 + "\\" + dir2; // upload\2\3
																// upload\3\5
		File file = new File(dir);
		if (!file.exists()) {
			// 创建目录
			file.mkdirs();
		}
		return dir;
	}

	public static void saveImage(String filename, String content) {
		try {
			DataOutputStream dos = null;
			try {
				// byte[] bs = new BASE64Decoder().decodeBuffer(content);
				byte[] bs = Base64.decodeBase64(content.getBytes());
				dos = new DataOutputStream(new BufferedOutputStream(
						new FileOutputStream(filename)));
				dos.write(bs);
			} finally {
				dos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static JSONObject getImageBase64(String imageUrl) {
		JSONObject jsonObject = new JSONObject();
		String base64ImageString = readImage(findFileSavePathByFileName(
				imageUrl, saveRootPath) + "\\" + imageUrl);
		jsonObject.put("image", base64ImageString);
		return jsonObject;
	}

}
