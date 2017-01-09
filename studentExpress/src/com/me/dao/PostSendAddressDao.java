package com.me.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.me.domain.PostSendAddress;

@Repository
public interface PostSendAddressDao {

	@Insert("insert into send_express_address_sender(postAddressID,userID,postProvince,postCity,postDistrict," +
			"postDetail,postName,postTel,postCode,postIsDefault) values(#{postAddressID},#{userID},#{postProvince},#{postCity},#{postDistrict}," +
			"#{postDetail},#{postName},#{postTel},#{postCode},#{postIsDefault})")
	public int insertPostAddress(PostSendAddress postSendAddress);
	
	@Select("select * from send_express_address_sender where postAddressID=#{postAddressID}")
	public PostSendAddress selectById(int postAddressID);
	
	@Select("select * from send_express_address_sender where userID=#{userID}")
	public List<PostSendAddress> selectByUserId(String userID);
	
	@Delete("delete from send_express_address_sender where  postAddressID=#{postAddressID}")
	public int deletePostSendAddress(int postAddressID);
	
	@Update("update send_express_address_sender set userID=Null Where postAddressID=#{postAddressID}")
	public int deleteByUpdate(int postAddressID);
	
	@Update("update send_express_address_sender set postIsDefault=0 where userID=#{userID} and postIsDefault=1")
	public int updateDefaultAddress(String userID);
}
