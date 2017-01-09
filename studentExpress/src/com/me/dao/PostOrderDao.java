package com.me.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.me.domain.PostOrder;
import com.me.domain.ReceiveOrder;
import com.me.web.GetOrderStateCommand;
import com.me.web.GetPostOrderCommand;
import com.me.web.GetReceiveOrderCommand;


@Repository
public interface PostOrderDao {

	@Insert("insert into send_express_order(postorderID,postorderPrice,postorderWeight,postAddressID,"
			+ "receiveAddressID,userID,postorderRemark,postorderPlaceTime,predictReceiveTime,postorderState) values"
			+ "(#{postorderID},#{postorderPrice},#{postorderWeight},"
			+ "#{postAddressID},#{receiveAddressID},#{userID},#{postorderRemark},#{postorderPlaceTime},#{predictReceiveTime},#{postorderState})")
	public int insertPostOrder(PostOrder postOrder);
	
	@Select("select postorderState as 'postorderState',count(*) as 'count' from send_express_order where userID=#{userID} group by postorderState")
	public List<Map> selectByState(String userID);
	
	@Select("select count(*) from send_express_order where userID=#{userID} and postorderState=#{postorderState}")
	public int selectNumState(PostOrder postOrder);
	
	@Delete("delete from send_express_order where postorderID=#{postorderID}")
	public int deleteOrder(String postorderID);
	
	@Select("select * from send_express_order where postorderID=#{postorderID}")
	public PostOrder selectByOneIdOrder(String postorderID);
	
	
	@Select("select postorderState as 'postorderState',count(*) as 'count' from send_express_order where deliverID=#{deliverID} group by postorderState")
	public List<Map> selectByStateExpress(String deliverID);             //抢单人员的订单情况
	
	@Select("select * from send_express_order where deliverID=#{deliverID} and postorderState=#{postorderState}")  //抢单人员各类型订单详情
	public List<PostOrder> selectOrderByStateExpress(GetPostOrderCommand getPostOrderCommand);
	
	@Select("select count(*) from send_express_order where deliverID=#{deliverID} and postorderState=#{postorderState}")
	public int selectOrderContentExpress(GetPostOrderCommand getPostOrderCommand);
	
	@Select("select * from send_express_order where postorderState='待处理'")
	public List<PostOrder> selectALLDataCache();
	
	@Select("select deliverID,count(*)as 'count' from send_express_order where postorderCompleteTime<current_timestamp() and postorderCompleteTime>date_sub(now(),interval 1 day) group by deliverID")
	public List<Map> selectCountOrder();
	
}
