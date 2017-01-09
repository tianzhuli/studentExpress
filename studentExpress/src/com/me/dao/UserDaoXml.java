package com.me.dao;

import org.springframework.stereotype.Repository;

import com.me.domain.ConsumerUser;

@Repository
public interface UserDaoXml {
	
	public int countAll(ConsumerUser consumerUser);
	
	public int updateUser(ConsumerUser consumerUser);
}
