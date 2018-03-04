package com.me.web;

public class GetPostOrderCommand {
	private String deliverID;
	private String userID;
	private String postorderState;
	private String postorderID;
	
	public String getPostorderID() {
		return postorderID;
	}

	public void setPostorderID(String postorderID) {
		this.postorderID = postorderID;
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

	public String getPostorderState() {
		return postorderState;
	}

	public void setPostorderState(String postorderState) {
		this.postorderState = postorderState;
	}
}
