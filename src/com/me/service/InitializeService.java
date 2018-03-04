package com.me.service;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.dao.SystemInformationDao;
import com.me.dao.WalletDao;
import com.me.domain.ConsumerUser;
import com.me.domain.Wallet;

@Service
public class InitializeService {

	@Autowired
	private WalletDao walletDao;

	@Autowired
	private SystemInformationDao systemInformationDao;

	public boolean initializeUser(String userID) {
		Wallet wallet = new Wallet();
		wallet.setWalletID(userID);
		wallet.setDiscountCouponSum(0);
		wallet.setBankcardSum(0);
		wallet.setExremainingSum("0");
		try {
			walletDao.insertWallet(wallet);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public JSONArray systemInformationSend(String managerID) {
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(systemInformationDao.sendInformation(managerID));
		return jsonArray;

	}

	public Wallet selectWalletById(String walletID) {
		return walletDao.selectWalletById(walletID);
	}

}
