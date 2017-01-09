package com.me.domain;

import java.sql.Timestamp;

public class ReceiveOrder {
	private String orderID;
	private String orderPrice;
	private String orderWeight;
	private String expressageNo;
	private String expressageContent;
	private int receiveAddressID;
	private String deliverID;
	private String userID;
	private String orderRemark;
	private Timestamp orderPlaceTime;
	private Timestamp orderPayTime;
	private String orderPayWayID;
	private String predictReceiveTime;
	private String orderState;
	private Timestamp orderReceiveTime;
	private String orderCompleteTime;
	private String orderEvaluateLevel;
	private String orderEvaluateContent;
	private String orderAddressContent;

	public String getOrderAddressContent() {
		return orderAddressContent;
	}

	public void setOrderAddressContent(String orderAddressContent) {
		this.orderAddressContent = orderAddressContent;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderWeight() {
		return orderWeight;
	}

	public void setOrderWeight(String orderWeight) {
		this.orderWeight = orderWeight;
	}

	public String getExpressageNo() {
		return expressageNo;
	}

	public void setExpressageNo(String expressageNo) {
		this.expressageNo = expressageNo;
	}

	public String getExpressageContent() {
		return expressageContent;
	}

	public void setExpressageContent(String expressageContent) {
		this.expressageContent = expressageContent;
	}

	public int getReceiveAddressID() {
		return receiveAddressID;
	}

	public void setReceiveAddressID(int receiveAddressID) {
		this.receiveAddressID = receiveAddressID;
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

	public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	public Timestamp getOrderPlaceTime() {
		return orderPlaceTime;
	}

	public void setOrderPlaceTime(Timestamp orderPlaceTime) {
		this.orderPlaceTime = orderPlaceTime;
	}

	public Timestamp getOrderPayTime() {
		return orderPayTime;
	}

	public void setOrderPayTime(Timestamp orderPayTime) {
		this.orderPayTime = orderPayTime;
	}

	public String getOrderPayWayID() {
		return orderPayWayID;
	}

	public void setOrderPayWayID(String orderPayWayID) {
		this.orderPayWayID = orderPayWayID;
	}

	public String getPredictReceiveTime() {
		return predictReceiveTime;
	}

	public void setPredictReceiveTime(String predictReceiveTime) {
		this.predictReceiveTime = predictReceiveTime;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public Timestamp getOrderReceiveTime() {
		return orderReceiveTime;
	}

	public void setOrderReceiveTime(Timestamp orderReceiveTime) {
		this.orderReceiveTime = orderReceiveTime;
	}

	public String getOrderCompleteTime() {
		return orderCompleteTime;
	}

	public void setOrderCompleteTime(String orderCompleteTime) {
		this.orderCompleteTime = orderCompleteTime;
	}

	public String getOrderEvaluateLevel() {
		return orderEvaluateLevel;
	}

	public void setOrderEvaluateLevel(String orderEvaluateLevel) {
		this.orderEvaluateLevel = orderEvaluateLevel;
	}

	public String getOrderEvaluateContent() {
		return orderEvaluateContent;
	}

	public void setOrderEvaluateContent(String orderEvaluateContent) {
		this.orderEvaluateContent = orderEvaluateContent;
	}

}
