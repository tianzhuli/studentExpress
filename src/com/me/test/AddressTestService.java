package com.me.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.me.dao.PostOrderDao;
import com.me.dao.PostReceiveAddressDao;
import com.me.dao.PostSendAddressDao;
import com.me.domain.PostOrder;
import com.me.domain.PostReceiveAddress;
import com.me.domain.PostSendAddress;
import com.me.util.CreateOrderId;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class AddressTestService {
	
	@Autowired 
	private PostReceiveAddressDao postReceiveAddressDao;
	
	@Autowired
	private PostSendAddressDao postSendAddressDao;
	
	@Autowired
	private PostOrderDao postOrderDao;
	
	@Test
	public void test(){
		PostReceiveAddress postReceiveAddress=new PostReceiveAddress();
		postReceiveAddress.setReceiveProvince("重庆市");
		postReceiveAddress.setReceiveCity("永川区");
		postReceiveAddress.setReceiveDistrict("胜利路");
		postReceiveAddress.setReceiveDetail("三号口");
		postReceiveAddress.setReceiveCode("402196");
		postReceiveAddress.setReceiveName("张三");
		postReceiveAddress.setReceiveTel("13213211232");
		postReceiveAddress.setUserID("13594039472");
		postReceiveAddressDao.insertPostReceiveAddress(postReceiveAddress);
	}

	@Test
	public void test1(){
		PostSendAddress postSendAddress=new PostSendAddress();
		postSendAddress.setPostProvince("重庆市");
		postSendAddress.setPostCity("沙坪坝区");
		postSendAddress.setPostDistrict("沙正街");
		postSendAddress.setPostDetail("174号");
		postSendAddress.setPostCode("400044");
		postSendAddress.setPostName("陈波");
		postSendAddress.setPostTel("13569875896");
		postSendAddress.setUserID("13594039472");
		postSendAddressDao.insertPostAddress(postSendAddress);
	}
	
	/*
	 * postorderID,postorderPrice,postorderWeight,postAddressID,"
			+ "receiveAddressID,userID,postorderRemark,postorderPlaceTime,predictReceiveTime,postorderState
	 */
	@Test
	public void test2(){
		PostOrder order=new PostOrder();
		order.setPostorderID(CreateOrderId.createOrderId());
		order.setPostorderWeight("5Kg");
		order.setPostorderPrice("25");
		order.setUserID("13594039472");
		order.setPostAddressID(postSendAddressDao.selectByUserId(order.getUserID()).get(0).getPostAddressID());
		order.setReceiveAddressID(postReceiveAddressDao.findByUserId(order.getUserID()).get(0).getReceiveAddressID());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		order.setPostorderPlaceTime(Timestamp.valueOf(simpleDateFormat
				.format(new Date())));
		order.setPostorderRemark("大件物品");
		order.setPredictReceiveTime("all");
		order.setPostorderState("待处理");
		postOrderDao.insertPostOrder(order);
	}
}
