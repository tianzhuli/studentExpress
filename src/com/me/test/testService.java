package com.me.test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.me.dao.PostOrderDao;
import com.me.dao.PostOrderDaoXml;
import com.me.dao.ReceiveAddressDao;
import com.me.dao.ReceiveOrderDao;
import com.me.dao.ReceiveOrderDaoXml;
import com.me.dao.UserDaoXml;
import com.me.domain.ConsumerUser;
import com.me.domain.PostOrder;
import com.me.domain.ReceiveAddress;
import com.me.domain.ReceiveOrder;
import com.me.service.ImproveInformtionService;
import com.me.service.InitializeService;
import com.me.service.LoginService;
import com.me.service.ReceiveAddressService;
import com.me.service.ReceiveOrderService;
import com.me.util.CreateOrderId;
import com.me.web.GetPostOrderCommand;
import com.me.web.ImproveCommand;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class testService {
	
	@Autowired
	private LoginService userService;
	
	@Autowired
	private InitializeService initializeService;
	
	@Autowired
	private ImproveInformtionService improveInformtionService;
	

	@Autowired
	private ReceiveAddressService receiveAdressService;
	
	@Autowired
	private ReceiveOrderService receiveOrderService;
	
	@Autowired
	private ReceiveAddressDao receiveAddressDao;
	
	@Autowired
	private ReceiveOrderDao receiveOrderDao;
	
	@Autowired
	private ReceiveOrderDaoXml receiveOrderDaoXml;
	
	@Autowired
	private PostOrderDaoXml postOrderDaoXml;
	
	@Autowired
	private PostOrderDao postOrderDao;
	/*@Test
	public void test() {
		fail("Not yet implemented");
	}*/
	/*@Test
	public void hasMatch(){
		System.out.println(initializeService.systemInformationSend("2011"));
		
	}*/
	/*@Test
	public void update(){
		ImproveCommand improveCommand=new ImproveCommand();
		improveCommand.setExuserID("15025316896");
		improveCommand.setItem("exuserEmail");
		improveCommand.setValue("123");
		improveInformtionService.updateUser(improveCommand);
	}*/

	/*@Test
	public void test(){
		ConsumerUser consumerUser=new ConsumerUser(); 
		consumerUser.setUserState("未激活");
		System.out.println(improveInformtionService.getCount(consumerUser));
	}*/
	/*@Test
	public void test(){
		ReceiveAdress receiveAdress=new ReceiveAdress();
		receiveAdress.setUserID("13594039472");
		receiveAdress.setReceiveCampus("A区");
		receiveAdress.setReceiveBuilding("8舍");
		receiveAdress.setReceiveBuildno("706");
		receiveAdress.setReceiveContent("重庆大学A区8舍706");receiveAddressID," +
			"userID,orderRemark,orderPlaceTime,predictReceiveTime,orderState
		receiveAdress.setReceiveName("陈波");
		receiveAdress.setReceiveTel("15023245869");
		System.out.println(receiveAdressService.createRecieveAddress(receiveAdress));
	}*/
	
	/*@Test
	public void test(){
		ReceiveOrder receiveOrder=new ReceiveOrder();
		receiveOrder.setOrderID(CreateOrderId.createOrderId());
		receiveOrder.setOrderPrice("11.5");
		receiveOrder.setOrderWeight("1kg");
		receiveOrder.setExpressageNo("154421512511");
		receiveOrder.setExpressageContent("你的快递.......");
		receiveOrder.setUserID("13594039472");
		receiveOrder.setOrderRemark("易碎");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		receiveOrder.setOrderPlaceTime(Timestamp.valueOf(simpleDateFormat
				.format(new Date())));
		receiveOrder.setPredictReceiveTime("all");
		receiveOrder.setOrderState("待处理");
		List<ReceiveAddress> list=receiveAddressDao.findByUserId("13594039472");
		receiveOrder.setReceiveAddressID(list.get(0).getReceiveAddressID());
		System.out.println(receiveOrderService.createReceiveOrder(receiveOrder));
	}
	*/
	@Test
	public void test(){
		PostOrder postOrder=new PostOrder();/*
		receiveOrder.setPostorderPayTime(Timestamp.valueOf("2016-12-20 12:12:12"));
		receiveOrder.setPostorderID("201607071528041b920749f50e42eebec19d2262c02f88");
		GetPostOrderCommand getPostOrderCommand=new GetPostOrderCommand();
		getPostOrderCommand.setUserID("13594039472");
		getPostOrderCommand.setPostorderState("待处理");
		JSONArray jsonArray=JSONArray.fromObject(postOrderDaoXml.selectPostOrder(getPostOrderCommand));
		JSONObject jsonObject=JSONObject.fromObject(postOrderDaoXml.selectPostOrder(getPostOrderCommand).get(0));
		System.out.println(postOrderDaoXml.selectPostOrder(getPostOrderCommand).get(0));
		System.out.println(jsonArray.toString());
		System.out.println(jsonObject.toString());*/
		/*postOrder=postOrderDao.selectByOneIdOrder("201607071528041b920749f50e42eebec19d2262c02f88");
		JSONObject jsonObject=JSONObject.fromObject(postOrder);
		System.out.println(jsonObject.toString());*/
		Field[] fields=postOrder.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			System.out.println(fields[i].getName());
		}
	}
}
