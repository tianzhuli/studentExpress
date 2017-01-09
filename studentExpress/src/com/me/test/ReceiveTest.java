package com.me.test;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.me.dao.ReceiveOrderDao;
import com.me.dao.ReceiveOrderDaoXml;
import com.me.service.ImproveInformtionService;
import com.me.web.GetReceiveOrderCommand;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class ReceiveTest {

	@Autowired
	private ReceiveOrderDao receiveOrderDao;
	
	@Autowired
	private ReceiveOrderDaoXml receiveOrderDaoXml;
	
	@Autowired
	private ImproveInformtionService improveInformtionService;
	
	/*@Test
	public void test(){
//		System.out.println(receiveOrderDao.selectByState("13594039472"));
		GetReceiveOrderCommand getReceiveOrderCommand=new GetReceiveOrderCommand();
		getReceiveOrderCommand.setDeliverID("");
		System.out.println(receiveOrderDaoXml.selectReceiveOrder(getReceiveOrderCommand));
	}*/
	
	@Test
	public void test(){
	/*	GetReceiveOrderCommand getReceiveOrderCommand=new GetReceiveOrderCommand();
		getReceiveOrderCommand.setUserID("13594039472");
		System.out.println(receiveOrderDaoXml.selectReceiveOrderView(getReceiveOrderCommand));*/
		//List<Map> maps=receiveOrderDao.selectByStateExpress("15025316896");
//		int sys=Integer.valueOf((String) maps.get(0).get("count"));
//		System.out.println(sys);
	/*	JSONArray jsonArray=JSONArray.fromObject(maps);
		int i=Integer.valueOf(String.valueOf(jsonArray.getJSONObject(0).getLong("count")));
		System.out.println(i);*/
		improveInformtionService.updateExpressOrder();
	}
	
}
