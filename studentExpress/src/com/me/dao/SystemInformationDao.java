package com.me.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.me.domain.SystemInformation;

@Repository
public interface SystemInformationDao {

	@Select("select * from system_information where managerID=#{managerID} order by informationTime desc limit 0,1")
	public SystemInformation sendInformation(String managerID);

	@Select("select * from system_information where informationType=#{informationType} order by informationTime desc limit 0,1")
	public SystemInformation sendInformationBytype(String informationType);

	@Insert("insert into system_information(managerID,informationTitle,informationTime,informationContent,informationType,informationUrl) values(#{managerID},#{informationTitle},#{informationTime},#{informationContent},#{informationType},#{informationUrl})")
	public int insertSystemInformation(SystemInformation systemInformation);
	
	@Select("select * from system_information")
	public List<Map> selectAllInformation();
	
	@Delete("delete from system_information where systemInformationID=#{systemInformationID}")
	public int deleteInformation(int systemInformationID);
}
