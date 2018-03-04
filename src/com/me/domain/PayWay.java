package com.me.domain;

public class PayWay {
	private int orderPayWayID;
	private String userID;
	private String orderPayWay;
	private String payAccount;

	public int getOrderPayWayID() {
		return orderPayWayID;
	}

	public void setOrderPayWayID(int orderPayWayID) {
		this.orderPayWayID = orderPayWayID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getOrderPayWay() {
		return orderPayWay;
	}

	public void setOrderPayWay(String orderPayWay) {
		this.orderPayWay = orderPayWay;
	}

	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

}
