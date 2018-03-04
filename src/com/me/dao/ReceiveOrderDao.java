package com.me.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.me.domain.ReceiveOrder;
import com.me.web.GetOrderStateCommand;

@Repository
public interface ReceiveOrderDao {
	@Insert("insert into replacement_express_order(orderID,orderPrice,orderWeight,expressageNo,expressageContent,receiveAddressID,"
			+ "userID,orderRemark,orderPlaceTime,predictReceiveTime,orderState) values (#{orderID},#{orderPrice},#{orderWeight},#{expressageNo},"
			+ "#{expressageContent},#{receiveAddressID},#{userID},#{orderRemark},#{orderPlaceTime},#{predictReceiveTime},#{orderState})")
	public int createReceiveOrder(ReceiveOrder receiveOrder);

	@Select("select orderState as 'orderState',count(*) as 'count' from replacement_express_order where userID=#{userID} group by orderState")
	public List<Map> selectByState(String userID);

	@Select("select count(*) from replacement_express_order where userID=#{userID} and orderState=#{orderState}")
	public int selectNumState(GetOrderStateCommand getOrderStateCommand);

	@Select("select * from replacement_express_order where userID=#{userID} and orderState=#{orderState}")
	public List<ReceiveOrder> selectOrderState(
			GetOrderStateCommand getOrderStateCommand);

	@Select("select orderState as 'orderState',count(*) as 'count' from replacement_express_order where deliverID=#{deliverID} group by orderState")
	public List<Map> selectByStateExpress(String deliverID); // 抢单人员的订单情况

	@Select("select * from replacement_express_order where deliverID=#{userID} and orderState=#{orderState}")
	// 抢单人员各类型订单详情
	public List<ReceiveOrder> selectOrderByStateExpress(
			GetOrderStateCommand getOrderStateCommand);

	@Select("select count(*) from replacement_express_order where deliverID=#{userID} and orderState=#{orderState}")
	public int selectOrderContentExpress(
			GetOrderStateCommand getOrderStateCommand);

	@Delete("delete from replacement_express_order where orderID=#{orderID}")
	public int deleteOrder(String orderID);

	@Select("select * from replacement_express_order where orderID=#{orderID}")
	public ReceiveOrder selectByOneIdOrder(String orderID);

	@Select("select * from replacement_express_order where orderState='待处理'")
	public List<ReceiveOrder> selectALLDataCache();

	@Select("select deliverID,count(*)as 'count' from replacement_express_order where orderCompleteTime<current_timestamp() and orderCompleteTime>date_sub(now(),interval 1 day) group by deliverID")
	public List<Map> selectCountOrder();
}
