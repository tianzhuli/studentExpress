package com.me.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.dao.ReceiveOrderDaoXml;
import com.me.domain.ReceiveOrder;

@Service
public class OrderSeizeService {
	@Autowired
	private ReceiveOrderDaoXml receiveOrderDaoXml;
	
	public void updateOrder(ReceiveOrder receiveOrder){
		receiveOrderDaoXml.updateReceiveOrder(receiveOrder);
	}
	
	
}
