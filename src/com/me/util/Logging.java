package com.me.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Logging {
	
	Logger programLogger = Logger.getLogger(Logging.class.getName());
	FileHandler logFileHandler = null;

	/**
	 * 操作错误日志	 * @param message
	 * @author 李天柱 * @return 
	 */
	public void daoLog(String message) {
		if(logFileHandler==null){
			String logpathString = "C:/xuedi/databaseLog.log";
			try {
				logFileHandler = new FileHandler(logpathString, true);
				programLogger.setLevel(Level.ALL);
				logFileHandler.setFormatter(new SimpleFormatter());
				programLogger.addHandler(logFileHandler);
				programLogger.info("ATTENTION: "+message);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IOException e) {
				programLogger.info("日志写入错误");
			}
			logFileHandler.close();
		}
	}
	/**
	 * 用户监控日志
	 * @param message
	 * @author 李天柱	 */
	public void userLog(String message){
		String logpathString="C:/xuedi/userLog.log";
		try {
			logFileHandler = new FileHandler(logpathString, true);
			logFileHandler.setFormatter(new SimpleFormatter());
			programLogger.addHandler(logFileHandler);
			programLogger.setLevel(Level.INFO);
			programLogger.info("ATTENTION: "+message);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			programLogger.info("日志写入错误");
		}
		logFileHandler.close();
	}
	
	public void errorLog(String message){
		String logpathString="C:/xuedi/errorLog.log";
		try {
			logFileHandler = new FileHandler(logpathString, true);
			logFileHandler.setFormatter(new SimpleFormatter());
			programLogger.addHandler(logFileHandler);
			programLogger.setLevel(Level.ALL);
			programLogger.info("ATTENTION: "+message);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			programLogger.info("日志写入错误");
		}
		logFileHandler.close();
	}
}