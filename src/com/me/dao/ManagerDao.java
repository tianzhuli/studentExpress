package com.me.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.me.domain.Manager;

@Repository
public interface ManagerDao {
	@Select("select count(*) from manager_user where managerID=#{managerID} and managerPassword=#{managerPassword}")
	public int ManagerLogin(Manager manager);
}
