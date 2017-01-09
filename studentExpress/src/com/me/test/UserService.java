package com.me.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//import sun.misc.BASE64Encoder;

import com.me.dao.UsersDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class UserService {
	@Autowired
	private UsersDao usersDao;
	
	@Test
	public void test(){
		String fileName=usersDao.findById("13594039472").getUserImageUrl();
		String saveRootPath="D:\\Program Files\\Apache Software Foundation\\Tomcat 8.0\\webapps\\studentExpress\\WEB-INF\\Image";
		String pathString=findFileSavePathByFileName(fileName, saveRootPath);
		System.out.println(pathString + "\\" + fileName);
		String base64=readImage(pathString + "\\" + fileName);
		System.out.println(base64);
	}
	
	public String findFileSavePathByFileName(String filename,
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
	
	public static String readImage(String str_FileName) {
		BufferedInputStream bis = null;
		byte[] bytes = null;
		try {
			try {
				bis = new BufferedInputStream(new FileInputStream(str_FileName));
				bytes = new byte[bis.available()];
				bis.read(bytes);
			} finally {
				bis.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(Base64.encodeBase64Chunked(bytes));
	}
	
}
