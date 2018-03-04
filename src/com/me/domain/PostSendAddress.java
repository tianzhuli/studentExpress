package com.me.domain;

public class PostSendAddress {
	private int postAddressID;
	private String userID;
	private String postProvince;
	private String postCity;
	private String postDistrict;
	private String postDetail;
	private String postCode;
	private String postName;
	private String postTel;
	private boolean postIsDefault;

	public boolean isPostIsDefault() {
		return postIsDefault;
	}

	public void setPostIsDefault(boolean postIsDefault) {
		this.postIsDefault = postIsDefault;
	}

	public int getPostAddressID() {
		return postAddressID;
	}

	public void setPostAddressID(int postAddressID) {
		this.postAddressID = postAddressID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPostProvince() {
		return postProvince;
	}

	public void setPostProvince(String postProvince) {
		this.postProvince = postProvince;
	}

	public String getPostCity() {
		return postCity;
	}

	public void setPostCity(String postCity) {
		this.postCity = postCity;
	}

	public String getPostDistrict() {
		return postDistrict;
	}

	public void setPostDistrict(String postDistrict) {
		this.postDistrict = postDistrict;
	}

	public String getPostDetail() {
		return postDetail;
	}

	public void setPostDetail(String postDetail) {
		this.postDetail = postDetail;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getPostTel() {
		return postTel;
	}

	public void setPostTel(String postTel) {
		this.postTel = postTel;
	}

}
