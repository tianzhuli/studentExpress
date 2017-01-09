package com.me.web;

public class GetReceiveOrderCommand {

	private String deliverID;
	private String userID;
	private String orderState;
	private String orderID;
	
	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getDeliverID() {
		return deliverID;
	}

	public void setDeliverID(String deliverID) {
		this.deliverID = deliverID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

}
