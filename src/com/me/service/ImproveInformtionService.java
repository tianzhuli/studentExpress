package com.me.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.dao.ExpressUserDao;
import com.me.dao.PostOrderDao;
import com.me.dao.ReceiveOrderDao;
import com.me.dao.UserDaoXml;
import com.me.dao.UsersDao;
import com.me.dao.WalletDaoXml;
import com.me.domain.ConsumerUser;
import com.me.domain.ImproveSum;
import com.me.domain.Wallet;
import com.me.web.ImproveCommand;

@Service
public class ImproveInformtionService {
	@Autowired
	private ExpressUserDao expressUserDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private UserDaoXml userDaoXml;

	@Autowired
	private WalletDaoXml walletDaoXml;

	@Autowired
	private ReceiveOrderDao receiveOrderDao;

	@Autowired
	private PostOrderDao postOrderDao;

	public int updateExpressUser(ImproveCommand improveCommand) {
		int sum = expressUserDao.updateExpressPerson(improveCommand); // 更新快递人员信息，除了图片url之外
		return sum;

	}

	public boolean updateUser(ImproveCommand improveCommand) {
		return usersDao.updateUser(improveCommand) > 0;
	}

	public int getCount(ConsumerUser consumerUser) {
		return userDaoXml.countAll(consumerUser);
	}

	public boolean updateUserImage(String path, String userID) {
		ImproveCommand improveCommand = new ImproveCommand();
		improveCommand.setExuserID(userID);
		improveCommand.setValue(path);
		improveCommand.setItem("userImageUrl");
		return usersDao.updateUser(improveCommand) > 0;
	}

	public boolean updateExpressImage(String path, String ExpressuserID) {
		ImproveCommand improveCommand = new ImproveCommand();
		improveCommand.setExuserID(ExpressuserID);
		improveCommand.setValue(path);
		improveCommand.setItem("exuserImageUrl");
		return expressUserDao.updateExpressPerson(improveCommand) > 0;
	}

	public boolean updateExpressStuImage(String path, String ExpressuserID) {
		ImproveCommand improveCommand = new ImproveCommand();
		improveCommand.setExuserID(ExpressuserID);
		improveCommand.setValue(path);
		improveCommand.setItem("exuserStuimageUrl");
		return expressUserDao.updateExpressPerson(improveCommand) > 0;
	}

	public boolean updateExpressState(String path, String ExpressuserID) {
		ImproveCommand improveCommand = new ImproveCommand();
		improveCommand.setExuserID(ExpressuserID);
		improveCommand.setValue(path);
		improveCommand.setItem("exuserState");
		return expressUserDao.updateExpressPerson(improveCommand) > 0;
	}

	public JSONObject updateWallet(Wallet wallet) {
		JSONObject jsonObject = new JSONObject();
		try {
			walletDaoXml.updateWallet(wallet);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonObject.put("state", "error");
			return jsonObject;
		}
		jsonObject.put("state", "success");
		return jsonObject;

	}

	public void updateExpressOrder() {
		try {
			List<Map> receiveList = receiveOrderDao.selectCountOrder();
			List<Map> postList = postOrderDao.selectCountOrder();
			ImproveSum improveCommand = new ImproveSum();
			for (int i = 0; i < receiveList.size(); i++) {
				String deliverID = receiveList.get(i).get("deliverID")
						.toString();
				int count = Integer.valueOf(String.valueOf(receiveList.get(i)
						.get("count")));
				improveCommand.setItem("exuserDayOrder");
				improveCommand.setExuserID(deliverID);
				improveCommand.setValue(count);
				expressUserDao.updateExpressPersonSum(improveCommand);
				improveCommand.setItem("exuserMonthOrder");
				expressUserDao.updateExpressPersonTotalSum(improveCommand);
				improveCommand.setItem("exuserYearOrder");
				expressUserDao.updateExpressPersonTotalSum(improveCommand);
				// improveCommand.setItem("exuserTotalOrder");
				expressUserDao.updateExpressPersonTotalSum(improveCommand);
			}
			for (int i = 0; i < postList.size(); i++) {
				String deliverID = postList.get(i).get("deliverID").toString();
				int count = Integer.valueOf(String.valueOf(postList.get(i).get(
						"count")));
				improveCommand.setItem("exuserDayOrder");
				improveCommand.setExuserID(deliverID);
				improveCommand.setValue(count);
				expressUserDao.updateExpressPersonTotalSum(improveCommand);
				improveCommand.setItem("exuserMonthOrder");
				expressUserDao.updateExpressPersonTotalSum(improveCommand);
				improveCommand.setItem("exuserYearOrder");
				expressUserDao.updateExpressPersonTotalSum(improveCommand);
				// improveCommand.setItem("exuserTotalOrder");
				expressUserDao.updateExpressPersonTotalSum(improveCommand);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public JSONArray getAllExuser() {
		JSONArray jsonArray = new JSONArray();
		try {
			jsonArray = JSONArray.fromObject(expressUserDao.countAllUser());
			return jsonArray;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}
}
