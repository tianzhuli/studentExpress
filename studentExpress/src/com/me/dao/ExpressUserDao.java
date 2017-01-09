package com.me.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.me.domain.ConsumerUser;
import com.me.domain.ExpressPerson;
import com.me.domain.ImproveSum;
import com.me.web.ImproveCommand;
import com.me.web.LoginCommand;
import com.me.web.RegisterCommand;

@Repository
public interface ExpressUserDao {

	@Select("select count(*) from express_person where exuserID=#{userId} and exuserPassword=#{password}")
	public int countUserLogin(LoginCommand loginCommand);

	@Insert("insert into express_person(exuserID,exuserPassword,exuserRegisterTime,exuserLoginTimes,exuserState,exuserQualityRating," +
			"exuserYearOrder,exremainingSum,exuserMonthOrder,exuserDayOrder,exuserTotalOrder) values(#{exuserID},#{exuserPassword}," +
			"#{exuserRegisterTime},#{exuserLoginTimes},#{exuserState},#{exuserQualityRating}," +
			"#{exuserYearOrder},#{exremainingSum},#{exuserMonthOrder},#{exuserDayOrder},#{exuserTotalOrder})")
	public int insertExpressUser(ExpressPerson expressPerson);
	
	@Select("select count(*) from express_person where exuserID=#{userID}")
	public int countUser(RegisterCommand registerCommand);
	
	@Select("select * from express_person ")
	public List<Map> countAllUser();
	
	@Select("select * from express_person where exuserID=#{exuserID}")
	public Map findById(String exuserID);
	
	@Select("select * from express_person where exuserID=#{exuserID}")
	public ExpressPerson findOneExpressPerson(String exuserID);
	
	@Select("select exuserID,exuserName,exuserStuno,exuserCardID,exuserStuimageUrl from express_person where exuserState='未激活' and exuserStuimageUrl IS NOT NULL;")
	public List<Map> selectActive();
	
	@Update("update express_person set exuserLoginTimes=#{exuserLoginTimes} where exuserID=#{exuserID}")
	public int updateExpLoginTimes(ExpressPerson expressPerson);
	
	@Update("update express_person set ${item}=#{value} where  exuserID=#{exuserID}")
	public int updateExpressPerson(ImproveCommand improveCommand);
	
	@Update("update express_person set ${item}=${item}+#{value} where  exuserID=#{exuserID}")
	public int updateExpressPersonTotalSum(ImproveSum improveSum);
	
	@Update("update express_person set ${item}=#{value} where  exuserID=#{exuserID}")
	public int updateExpressPersonSum(ImproveSum improveSum);
	
	@Update("update express_person set exuserTotalOrder=exuserTotalOrder+1 where  exuserID=#{exuserID}")
	public int updateExpressPersonTotal(String exuserID);

}
