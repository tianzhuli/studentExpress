package com.me.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.me.domain.Wallet;

@Repository
public interface WalletDao {
	
	@Insert("insert into wallet_user(walletID,exremainingSum,discountCouponSum,bankcardSum) values (#{walletID}," +
			"#{exremainingSum},#{discountCouponSum},#{bankcardSum})")
	public int insertWallet(Wallet wallet);
	
	@Select("select count(*) from wallet_user where walletID=#{walletID}")
	public int selectById(String walletID);
	
	@Select("select * from wallet_user where walletID=#{walletID}")
	public Wallet selectWalletById(String walletID);
}
