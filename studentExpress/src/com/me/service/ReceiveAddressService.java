package com.me.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.dao.ReceiveAddressDao;
import com.me.dao.ReceiveAddressDaoXml;
import com.me.dao.ReceiveOrderDaoXml;
import com.me.domain.ReceiveAddress;

@Service
public class ReceiveAddressService {
	
	@Autowired
	private ReceiveAddressDao recieveAdressDao;
	
	@Autowired
	private ReceiveAddressDaoXml receiveAddressDaoXml;
	public boolean createRecieveAddress(ReceiveAddress receiveAdress){
		return recieveAdressDao.insertRecieveAdress(receiveAdress)>0;
	}
	
	public ReceiveAddress getReceiveAddress(int receiveAddressID){
		return recieveAdressDao.findById(receiveAddressID);		
	}
	
	public List<ReceiveAddress> getReceiveAddresses(String userID){
		return recieveAdressDao.findByUserId(userID);
	}
	
	public int insertAddress(ReceiveAddress receiveAddress){
		if (receiveAddress.isReceiveIsDefault()) {
			recieveAdressDao.updateDefault(receiveAddress.getUserID());
		}
		receiveAddressDaoXml.insertReceiveAddress(receiveAddress);
		return receiveAddress.getReceiveAddressID();
	}
	
	public int deleteReceiveAddress(int receiveAddressID){
		return recieveAdressDao.deleteByUpdate(receiveAddressID);           //通过将用户名置为空来调整显示
	}
}
