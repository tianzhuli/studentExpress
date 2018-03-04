package com.me.domain;

public class Wallet {
	private String walletID;
	private String exremainingSum;
	private int discountCouponSum;
	private int bankcardSum;

	public void setWalletID(String walletID) {
		this.walletID = walletID;
	}

	public String getWalletID() {
		return walletID;
	}

	public String getExremainingSum() {
		return exremainingSum;
	}

	public void setExremainingSum(String exremainingSum) {
		this.exremainingSum = exremainingSum;
	}

	public int getDiscountCouponSum() {
		return discountCouponSum;
	}

	public void setDiscountCouponSum(int discountCouponSum) {
		this.discountCouponSum = discountCouponSum;
	}

	public int getBankcardSum() {
		return bankcardSum;
	}

	public void setBankcardSum(int bankcardSum) {
		this.bankcardSum = bankcardSum;
	}

}
