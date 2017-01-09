package com.me.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.me.domain.ConsumerUser;
import com.me.domain.User;
import com.me.web.ImproveCommand;
import com.me.web.LoginCommand;
import com.me.web.RegisterCommand;

@Repository
public interface UsersDao {
	
	@Select("select count(*) from consumer_user where userID=#{userName} and userPassword=#{passWord}")
	public int count(User user);
	//public int count(User user);
	
	
	@Select("select count(*) from consumer_user where userID=#{userId} and userPassword=#{password}")
	public int countUserLogin(LoginCommand loginCommand);
	
	@Insert("insert into consumer_user(userID,userPassword,userRegisterTime,userLoginTimes,userState,userIntegration,userQualityRating," +
			"remainingSum) values (#{userID},#{userPassword},#{userRegisterTime},#{userLoginTimes},#{userState}," +
			"#{userIntegration},#{userQualityRating},#{remainingSum})")
	public int insertUser(ConsumerUser consumerUser);
	
	@Select("select count(*) from consumer_user where userID=#{userID}")
	public int countUserRegister(RegisterCommand registerCommand);
	
	@Select("select * from consumer_user where userID=#{userID}")
	public ConsumerUser findById(String userID);
	
	@Update("update consumer_user set userLoginTimes=#{userLoginTimes} where userID=#{userID}")
	public int UpdateLoginTimes(ConsumerUser consumerUser);
	
	@Update("update consumer_user set ${item}=#{value} where  userID=#{exuserID}")
	public int updateUser(ImproveCommand improveCommand);
	
	
}
