package com.me.dao;

import org.springframework.stereotype.Repository;

import com.me.domain.ExpressPerson;

@Repository
public interface ExpressUserDaoXml {
	public int updateExpressUser(ExpressPerson expressPerson);
}
