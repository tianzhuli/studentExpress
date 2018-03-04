package com.me.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.me.domain.ReceiveOrder;
import com.me.web.GetPostOrderCommand;
import com.me.web.GetReceiveOrderCommand;

@Repository
public interface ReceiveOrderDaoXml {
	
	public int updateReceiveOrder(ReceiveOrder receiveOrder);
	
	public int insertReceiveOrder(ReceiveOrder receiveOrder);   //生成订单
	
	public List<Map> selectReceiveOrder(GetReceiveOrderCommand getReceiveOrderCommand);  //获取普通用户或者抢单人员的订单,联合查询,没有抢单人员信息，只有ID
	
	public List<Map> selectReceiveOrderView(GetReceiveOrderCommand getReceiveOrderCommand); //视图的方式获得信息
}
