package com.me.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.me.domain.ReceiveAddress;

@Repository
public interface ReceiveAddressDao {
	
	@Insert("insert into replacement_express_address(userID,receiveCollege,receiveCampus,receiveBuilding,receiveBuildno,receiveContent," +
			"receiveName,receiveTel,receiveIsDefault) values(#{userID},#{receiveCollege},#{receiveCampus},#{receiveBuilding},#{receiveBuildno},#{receiveContent}," +
			"#{receiveName},#{receiveTel},#{receiveIsDefault})")
	public int insertRecieveAdress(ReceiveAddress receiveAdress);
	
	@Select("select * from replacement_express_address where receiveAddressID=#{receiveAddressID}")
	public ReceiveAddress findById(int receiveAddressID);
	
	@Select("select * from replacement_express_address where userID=#{userID}")
	public List<ReceiveAddress> findByUserId(String userID);
	
	@Delete("delete from replacement_express_address where receiveAddressID=#{receiveAddressID}")
	public int deleteReceiveAddress(int receiveAddressID);
	
	@Update("update replacement_express_address set userID=Null Where receiveAddressID=#{receiveAddressID}")
	public int deleteByUpdate(int receiveAddressID);
	
	@Update("update replacement_express_address set receiveIsDefault=0 Where userID=#{userID} and receiveIsDefault=1")
	public int updateDefault(String userID);
}
