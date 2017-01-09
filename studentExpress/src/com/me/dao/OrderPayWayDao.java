package com.me.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.me.domain.OrderPayWay;

@Repository
public interface OrderPayWayDao {
	@Insert("insert into orderpayway(userID,orderPayWay,payAccount,payLimit) values(#{userID},#{orderPayWay},#{payAccount},#{payLimit})")
	public int insertOrderPayWay(OrderPayWay orderPayWay);
	
	@Select("select * from orderpayway where orderPayWayID=#{orderPayWayID}")
	public OrderPayWay selectById(String orderPayWayID);
	
	@Select("select * from orderpayway where userID=#{userID}")
	public List<OrderPayWay> selectByUserId(String userID);
}
