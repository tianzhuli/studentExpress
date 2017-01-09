/**
 * 
 */
package com.me.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.dao.ExpressUserDao;
import com.me.dao.SystemInformationDao;
import com.me.dao.UserDao;
import com.me.dao.UsersDao;
import com.me.dao.WalletDao;
import com.me.domain.ConsumerUser;
import com.me.domain.ExpressPerson;
import com.me.domain.Wallet;
import com.me.util.DataCache;
import com.me.util.ImageDown;
import com.me.util.SessionToken;
import com.me.web.LoginCommand;

/**
 * @author Administrator
 * 
 */
@Service
public class LoginService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private ExpressUserDao expressUserDao;

	@Autowired
	private InitializeService initializeService;

	@Autowired
	private WalletDao walletDao;

	@Autowired
	private SystemInformationDao systemInformationDao;

	public boolean hasMatchLogin(LoginCommand loginCommand) {
		int macth = usersDao.countUserLogin(loginCommand);
		// user.setUserName(loginCommand.getUserName());
		return macth > 0;
	}

	public JSONObject loginGetJson(LoginCommand loginCommand, HttpSession session) {
		JSONObject data = new JSONObject();
		System.out.println(loginCommand.getUserId() + loginCommand.getPassword() + loginCommand.getToken()
				+ (SessionToken.sessionToken.get(loginCommand.getUserId())));
		int macth = usersDao.countUserLogin(loginCommand);
		if (loginCommand.getPassword() != null) {
			if (macth > 0) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
				data.put("state", "notokensuccess");
				data.put("token", session.getId() + "_" + simpleDateFormat.format(new Date()));
				data.put("systemInformation", systemInformationDao.sendInformationBytype("登录").getInformationContent());
				/* System.out.println(session.getId()); */
				synchronized (SessionToken.tokenLock) {
					SessionToken.sessionToken.put(loginCommand.getUserId(), session.getId() + "_" + simpleDateFormat.format(new Date()));
				}
				ConsumerUser consumerUser = usersDao.findById(loginCommand.getUserId());
				data.put("userState", consumerUser.getUserState());
				DataCache.userOnlineList.add(loginCommand.getUserId());
				int wallet = walletDao.selectById(consumerUser.getUserID());
				if ((consumerUser.getUserLoginTimes() == 0) && (wallet == 0)) {
					if (initializeService.initializeUser(consumerUser.getUserID())) {
						data.put("initialize", "success");
					} else {
						data.put("initialize", "error");
					}
				} else {
					consumerUser.setUserLoginTimes(consumerUser.getUserLoginTimes() + 1);
					usersDao.UpdateLoginTimes(consumerUser);
					Wallet wallet2 = initializeService.selectWalletById(consumerUser.getUserID());
					data.put("exremainingSum", wallet2.getExremainingSum());
					data.put("userIntegration", consumerUser.getUserIntegration());
					data.put("discountCouponSum", wallet2.getDiscountCouponSum());
					if (consumerUser.getUserImageUrl() != null) {
						String base64ImageString = ImageDown.readImage(ImageDown.findFileSavePathByFileName(consumerUser.getUserImageUrl(),
								ImageDown.saveRootPath) + "\\" + consumerUser.getUserImageUrl());
						data.put("image", base64ImageString);
					} else {
						data.put("image", "null");
					}
					if (consumerUser.getUserState().equals("未激活")) {
						data.put("userName", consumerUser.getUserID());
					} else {
						data.put("userName", consumerUser.getUserNickname());
					}

				}
				/*
				 * List<Map> resultMapList=expressUserDao.countAllUser();
				 * JSONArray jsonArray=JSONArray.fromObject(resultMapList);
				 * System.out.println(jsonArray.toString()); for (int i = 0; i <
				 * resultMapList.size(); i++) { Map
				 * resultMap=resultMapList.get(i); JSONObject
				 * jsonObject=JSONObject.fromObject(resultMap);
				 * System.out.println(jsonObject.toString()); Iterator
				 * enties=resultMap.entrySet().iterator(); while
				 * (enties.hasNext()) { Map.Entry map = (Map.Entry)
				 * enties.next();
				 * System.out.println("key:value="+map.getKey()+":"
				 * +map.getValue()); } Map<String String> map=new
				 * Hash<String,String>; Iterator <Map.Entry<String,String>>
				 * entries=map.entrySet().iterator;
				 * 
				 * 
				 * }
				 */

			} else {
				data.put("state", "error");
				data.put("error", "用户名或密码错误");
			}
		}

		else {
			String sessionToken = "";
			synchronized (SessionToken.tokenLock) {
				sessionToken = SessionToken.sessionToken.get(loginCommand.getUserId());
			}
			if (sessionToken.equals(loginCommand.getToken()) && SessionToken.compare(sessionToken) && (loginCommand.getToken() != null)
					&& (loginCommand.getUserId() != null)) {
				data.put("state", "success");
				ConsumerUser consumerUser = usersDao.findById(loginCommand.getUserId());
				data.put("userState", consumerUser.getUserState());
				consumerUser.setUserLoginTimes(consumerUser.getUserLoginTimes() + 1);
				usersDao.UpdateLoginTimes(consumerUser);
				DataCache.userOnlineList.add(loginCommand.getUserId());
				Wallet wallet2 = initializeService.selectWalletById(consumerUser.getUserID());
				data.put("exremainingSum", wallet2.getExremainingSum());
				data.put("userIntegration", consumerUser.getUserIntegration());
				data.put("discountCouponSum", wallet2.getDiscountCouponSum());
				if (consumerUser.getUserImageUrl() != null) {
					String base64ImageString = ImageDown.readImage(ImageDown.findFileSavePathByFileName(consumerUser.getUserImageUrl(), ImageDown.saveRootPath)
							+ "\\" + consumerUser.getUserImageUrl());
					data.put("image", base64ImageString);
				} else {
					data.put("image", "null");
				}
				if (consumerUser.getUserState().equals("未激活")) {
					data.put("userName", consumerUser.getUserID());
				} else {
					data.put("userName", consumerUser.getUserNickname());
				}

			} else {
				data.put("state", "timeout");
			}
		}
		return data;
	}

	/*
	 * 抢单人员的登录初始化，第一次登录会初始化钱包信息，后面登录用token来验证，
	 * 如果token过期了就会回复timeout。从而要新的登录名和密码，然后生成新的token
	 */
	public JSONObject loginExpressGetJson(LoginCommand loginCommand, HttpSession session) {
		JSONObject resultJsonData = new JSONObject();
		System.out.println(loginCommand.getUserId() + loginCommand.getPassword() + loginCommand.getToken()
				+ (SessionToken.expresssessionToken.get(loginCommand.getUserId())));
		int macth = expressUserDao.countUserLogin(loginCommand);
		if (loginCommand.getPassword() != null) {
			if (macth > 0) {
				resultJsonData.put("state", "notokensuccess");
				resultJsonData.put("systemInformation", systemInformationDao.sendInformationBytype("登录").getInformationContent());
				resultJsonData.put("token", session.getId());
				System.out.println(session.getId());
				SessionToken.expresssessionToken.put(loginCommand.getUserId(), session.getId());
				Map expressPersonMap = expressUserDao.findById(loginCommand.getUserId());
				ExpressPerson expressPerson = expressUserDao.findOneExpressPerson(loginCommand.getUserId());
				DataCache.expressPersonOnlineMap.put(expressPerson.getExuserID(), expressPersonMap);
				resultJsonData.put("userState", expressPerson.getExuserState());
				int wallet = walletDao.selectById(expressPerson.getExuserID());
				if ((expressPerson.getExuserLoginTimes() == 0) && (wallet == 0)) {
					if (initializeService.initializeUser(expressPerson.getExuserID())) {
						resultJsonData.put("initialize", "success");
					} else {
						resultJsonData.put("initialize", "error");
					}
				} else {
					expressPerson.setExuserLoginTimes(expressPerson.getExuserLoginTimes() + 1);
					expressUserDao.updateExpLoginTimes(expressPerson);
					Wallet wallet2 = initializeService.selectWalletById(expressPerson.getExuserID());
					resultJsonData.put("exremainingSum", wallet2.getExremainingSum());
					resultJsonData.put("discountCouponSum", wallet2.getDiscountCouponSum());
					if (expressPerson.getExuserImageUrl() != null) {
						String base64ImageString = ImageDown.readImage(ImageDown.findFileSavePathByFileName(expressPerson.getExuserImageUrl(),
								ImageDown.saveRootPath) + "\\" + expressPerson.getExuserImageUrl());
						resultJsonData.put("image", base64ImageString);
					} else {
						resultJsonData.put("image", "null");
					}
					if (expressPerson.getExuserState().equals("未激活")) {
						resultJsonData.put("userName", expressPerson.getExuserID());
					} else {
						resultJsonData.put("userName", expressPerson.getExuserNickname());
					}

				}

			} else {
				resultJsonData.put("state", "error");
				resultJsonData.put("error", "用户名或密码错误");
			}
		}

		else if ((SessionToken.expresssessionToken.get(loginCommand.getUserId())).equals(loginCommand.getToken()) && (loginCommand.getToken() != null)
				&& (loginCommand.getUserId() != null)) {
			resultJsonData.put("state", "success");
			Map expressPersonMap = expressUserDao.findById(loginCommand.getUserId());
			ExpressPerson expressPerson = expressUserDao.findOneExpressPerson(loginCommand.getUserId());
			DataCache.expressPersonOnlineMap.put(expressPerson.getExuserID(), expressPersonMap);
			resultJsonData.put("userState", expressPerson.getExuserState());
			expressPerson.setExuserLoginTimes(expressPerson.getExuserLoginTimes() + 1);
			expressUserDao.updateExpLoginTimes(expressPerson);
			Wallet wallet2 = initializeService.selectWalletById(expressPerson.getExuserID());
			resultJsonData.put("exremainingSum", wallet2.getExremainingSum());
			resultJsonData.put("discountCouponSum", wallet2.getDiscountCouponSum());
			if (expressPerson.getExuserImageUrl() != null) {
				String base64ImageString = ImageDown.readImage(ImageDown.findFileSavePathByFileName(expressPerson.getExuserImageUrl(), ImageDown.saveRootPath)
						+ "\\" + expressPerson.getExuserImageUrl());
				resultJsonData.put("image", base64ImageString);
			} else {
				resultJsonData.put("image", "null");
			}
			if (expressPerson.getExuserState().equals("未激活")) {
				resultJsonData.put("userName", expressPerson.getExuserID());
			} else {
				resultJsonData.put("userName", expressPerson.getExuserNickname());
			}

		} else {
			resultJsonData.put("state", "timeout");
		}
		return resultJsonData;
	}
}
