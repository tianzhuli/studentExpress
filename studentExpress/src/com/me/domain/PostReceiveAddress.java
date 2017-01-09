package com.me.domain;

public class PostReceiveAddress {
	private int receiveAddressID;
	private String userID;
	private String receiveProvince;
	private String receiveCity;
	private String receiveDistrict;
	private String receiveDetail;
	private String receiveCode;
	private String receiveName;
	private String receiveTel;
	private boolean receiveIsDefault;
	
	public boolean isReceiveIsDefault() {
		return receiveIsDefault;
	}

	public void setReceiveIsDefault(boolean receiveIsDefault) {
		this.receiveIsDefault = receiveIsDefault;
	}
	public int getReceiveAddressID() {
		return receiveAddressID;
	}
	public void setReceiveAddressID(int receiveAddressID) {
		this.receiveAddressID = receiveAddressID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getReceiveProvince() {
		return receiveProvince;
	}
	public void setReceiveProvince(String receiveProvince) {
		this.receiveProvince = receiveProvince;
	}
	public String getReceiveCity() {
		return receiveCity;
	}
	public void setReceiveCity(String receiveCity) {
		this.receiveCity = receiveCity;
	}
	public String getReceiveDistrict() {
		return receiveDistrict;
	}
	public void setReceiveDistrict(String receiveDistrict) {
		this.receiveDistrict = receiveDistrict;
	}
	public String getReceiveDetail() {
		return receiveDetail;
	}
	public void setReceiveDetail(String receiveDetail) {
		this.receiveDetail = receiveDetail;
	}
	public String getReceiveCode() {
		return receiveCode;
	}
	public void setReceiveCode(String receiveCode) {
		this.receiveCode = receiveCode;
	}
	public String getReceiveName() {
		return receiveName;
	}
	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	public String getReceiveTel() {
		return receiveTel;
	}
	public void setReceiveTel(String receiveTel) {
		this.receiveTel = receiveTel;
	}
	
}
