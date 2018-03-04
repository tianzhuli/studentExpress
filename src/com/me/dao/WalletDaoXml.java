package com.me.dao;

import org.springframework.stereotype.Repository;

import com.me.domain.Wallet;

@Repository
public interface WalletDaoXml {
	public int updateWallet(Wallet wallet);
}
