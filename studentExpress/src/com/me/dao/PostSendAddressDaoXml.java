package com.me.dao;

import org.springframework.stereotype.Repository;

import com.me.domain.PostSendAddress;

@Repository
public interface PostSendAddressDaoXml {
	public int insertSendAddress(PostSendAddress postSendAddress);
	public int updateSendAddress(PostSendAddress postSendAddress);
}
