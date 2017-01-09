package com.me.dao;

import org.springframework.stereotype.Repository;

import com.me.domain.ReceiveAddress;

@Repository
public interface ReceiveAddressDaoXml {

	public int insertReceiveAddress(ReceiveAddress receiveAddress);
	public int updateReceiveAddress(ReceiveAddress receiveAddress);

}
