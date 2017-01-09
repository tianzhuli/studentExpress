package com.me.domain;

import java.sql.Timestamp;

public class PostOrder {
	private String postorderID;
	private String postorderPrice;
	private String postorderWeight;
	private String postexpressageNo;
	private String expressWay;
	private String postorderPayWayID;
	private int postAddressID;
	private int receiveAddressID;
	private String deliverID;
	private String userID;
	private String postorderRemark;
	private Timestamp postorderPlaceTime;
	private Timestamp postorderPayTime;
	private String predictReceiveTime;
	private String postorderState;
	private Timestamp postorderReceiveTime;
	private Timestamp postorderCompleteTime;
	private String postorderEvaluateLevel;
	private String postorderEvaluateContent;
	private String postorderSendAddressContent;
	private String postorderReceiveAddressContent;
	
	public String getPostorderSendAddressContent() {
		return postorderSendAddressContent;
	}

	public void setPostorderSendAddressContent(String postorderSendAddressContent) {
		this.postorderSendAddressContent = postorderSendAddressContent;
	}

	public String getPostorderReceiveAddressContent() {
		return postorderReceiveAddressContent;
	}

	public void setPostorderReceiveAddressContent(
			String postorderReceiveAddressContent) {
		this.postorderReceiveAddressContent = postorderReceiveAddressContent;
	}

	public String getPostorderID() {
		return postorderID;
	}

	public void setPostorderID(String postorderID) {
		this.postorderID = postorderID;
	}

	public String getPostorderPrice() {
		return postorderPrice;
	}

	public void setPostorderPrice(String postorderPrice) {
		this.postorderPrice = postorderPrice;
	}

	public String getPostorderWeight() {
		return postorderWeight;
	}

	public void setPostorderWeight(String postorderWeight) {
		this.postorderWeight = postorderWeight;
	}

	public String getPostexpressageNo() {
		return postexpressageNo;
	}

	public void setPostexpressageNo(String postexpressageNo) {
		this.postexpressageNo = postexpressageNo;
	}

	public String getExpressWay() {
		return expressWay;
	}

	public void setExpressWay(String expressWay) {
		this.expressWay = expressWay;
	}

	public String getPostorderPayWayID() {
		return postorderPayWayID;
	}

	public void setPostorderPayWayID(String postorderPayWayID) {
		this.postorderPayWayID = postorderPayWayID;
	}

	public int getPostAddressID() {
		return postAddressID;
	}

	public void setPostAddressID(int postAddressID) {
		this.postAddressID = postAddressID;
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

	public String getPostorderRemark() {
		return postorderRemark;
	}

	public void setPostorderRemark(String postorderRemark) {
		this.postorderRemark = postorderRemark;
	}

	public Timestamp getPostorderPlaceTime() {
		return postorderPlaceTime;
	}

	public void setPostorderPlaceTime(Timestamp postorderPlaceTime) {
		this.postorderPlaceTime = postorderPlaceTime;
	}

	public Timestamp getPostorderPayTime() {
		return postorderPayTime;
	}

	public void setPostorderPayTime(Timestamp postorderPayTime) {
		this.postorderPayTime = postorderPayTime;
	}

	public String getPredictReceiveTime() {
		return predictReceiveTime;
	}

	public void setPredictReceiveTime(String predictReceiveTime) {
		this.predictReceiveTime = predictReceiveTime;
	}

	public String getPostorderState() {
		return postorderState;
	}

	public void setPostorderState(String postorderState) {
		this.postorderState = postorderState;
	}

	public Timestamp getPostorderReceiveTime() {
		return postorderReceiveTime;
	}

	public void setPostorderReceiveTime(Timestamp postorderReceiveTime) {
		this.postorderReceiveTime = postorderReceiveTime;
	}

	public Timestamp getPostorderCompleteTime() {
		return postorderCompleteTime;
	}

	public void setPostorderCompleteTime(Timestamp postorderCompleteTime) {
		this.postorderCompleteTime = postorderCompleteTime;
	}

	public String getPostorderEvaluateLevel() {
		return postorderEvaluateLevel;
	}

	public void setPostorderEvaluateLevel(String postorderEvaluateLevel) {
		this.postorderEvaluateLevel = postorderEvaluateLevel;
	}

	public String getPostorderEvaluateContent() {
		return postorderEvaluateContent;
	}

	public void setPostorderEvaluateContent(String postorderEvaluateContent) {
		this.postorderEvaluateContent = postorderEvaluateContent;
	}

}
