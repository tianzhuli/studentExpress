package com.me.dao;

import org.springframework.stereotype.Repository;

import com.me.domain.PostReceiveAddress;

@Repository
public interface PostReceiveAddressDaoXml {
	public int insertPostReceiveAddress(PostReceiveAddress postReceiveAddress);
	public int updatePostReceiveAddress(PostReceiveAddress postReceiveAddress);
}
