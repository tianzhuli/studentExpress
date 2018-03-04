package com.me.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.me.domain.PostReceiveAddress;

@Repository
public interface PostReceiveAddressDao {

	@Insert("insert into send_express_address_receive(receiveAddressID,userID,receiveProvince,receiveCity,receiveDistrict," +
			"receiveDetail,receiveName,receiveTel,receiveCode,receiveIsDefault) values(#{receiveAddressID},#{userID},#{receiveProvince},#{receiveCity}," +
			"#{receiveDistrict},#{receiveDetail},#{receiveName},#{receiveTel},#{receiveCode},#{receiveIsDefault})")
	public int insertPostReceiveAddress(PostReceiveAddress postReceiveAddress);
	
	@Select("select * from send_express_address_receive where receiveAddressID=#{receiveAddressID}")
	public PostReceiveAddress findByid(int receiveAddressID);
	
	@Select("select * from send_express_address_receive where userID=#{userID}")
	public List<PostReceiveAddress> findByUserId(String userID);
	
	@Delete("delete from send_express_address_receive where receiveAddressID=#{receiveAddressID}")
	public int deletePostReceiveAddress(int receiveAddressID);
	
	@Update("update send_express_address_receive set userID=Null Where receiveAddressID=#{receiveAddressID}")
	public int deleteByUpdate(int receiveAddressID);
	
	@Update("update send_express_address_receive set receiveIsDefault=0 Where userID=#{userID} and receiveIsDefault=1")
	public int updateDefault(String userID);
}
