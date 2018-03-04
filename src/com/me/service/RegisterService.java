package com.me.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.dao.ExpressUserDao;
import com.me.dao.UsersDao;
import com.me.domain.ConsumerUser;
import com.me.domain.ExpressPerson;
import com.me.web.RegisterCommand;

@Service
public class RegisterService {

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private ExpressUserDao expressUserDao;

	public boolean registerConsumer(RegisterCommand registerCommand) {
		ConsumerUser consumerUser = new ConsumerUser();
		consumerUser.setUserID(registerCommand.getUserID());
		consumerUser.setUserPassword(registerCommand.getUserPassword());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		consumerUser.setUserRegisterTime(Timestamp.valueOf(simpleDateFormat
				.format(new Date())));
		consumerUser.setUserLoginTimes(0);
		consumerUser.setUserState("未激活");
		consumerUser.setUserIntegration("0");
		consumerUser.setUserQualityRating("*");
		consumerUser.setRemainingSum("0");
		// System.out.println(consumerUser.getUserID());
		int isright = usersDao.insertUser(consumerUser);
		return isright > 0;
	}

	public boolean checkUserIsExist(RegisterCommand registerCommand) {
		return usersDao.countUserRegister(registerCommand) > 0;
	}

	public boolean registerExpressPerson(RegisterCommand registerCommand) {																			
		ExpressPerson expressPerson = new ExpressPerson();
		expressPerson.setExuserID(registerCommand.getUserID());
		expressPerson.setExuserPassword(registerCommand.getUserPassword());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		expressPerson.setExuserRegisterTime(Timestamp.valueOf(simpleDateFormat
				.format(new Date())));
		expressPerson.setExuserLoginTimes(0);
		expressPerson.setExuserState("未激活");
		expressPerson.setExuserQualityRating("*");
		expressPerson.setExuserYearOrder(0);
		expressPerson.setExuserMonthOrder(0);
		expressPerson.setExremainingSum("0");
		expressPerson.setExuserDayOrder(0);
		expressPerson.setExuserTotalOrder(0);
		int result = expressUserDao.insertExpressUser(expressPerson);
		return result > 0;

	}
	
	public boolean checkExpressUserIsExist(RegisterCommand registerCommand) {
		return expressUserDao.countUser(registerCommand) > 0;
	}

}
