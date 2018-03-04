package com.me.domain;

public class ReceiveAddress {
	private int receiveAddressID;
	private String userID;
	private String receiveCollege;
	private String receiveCampus;
	private String receiveBuilding;
	private String receiveBuildno;
	private String receiveContent;
	private String receiveName;
	private String receiveTel;
	private boolean receiveIsDefault;
	public boolean isReceiveIsDefault() {
		return receiveIsDefault;
	}

	public void setReceiveIsDefault(boolean receiveIsDefault) {
		this.receiveIsDefault = receiveIsDefault;
	}

	public String getReceiveCollege() {
		return receiveCollege;
	}

	public void setReceiveCollege(String receiveCollege) {
		this.receiveCollege = receiveCollege;
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

	public String getReceiveCampus() {
		return receiveCampus;
	}

	public void setReceiveCampus(String receiveCampus) {
		this.receiveCampus = receiveCampus;
	}

	public String getReceiveBuilding() {
		return receiveBuilding;
	}

	public void setReceiveBuilding(String receiveBuilding) {
		this.receiveBuilding = receiveBuilding;
	}

	public String getReceiveBuildno() {
		return receiveBuildno;
	}

	public void setReceiveBuildno(String receiveBuildno) {
		this.receiveBuildno = receiveBuildno;
	}

	public String getReceiveContent() {
		return receiveContent;
	}

	public void setReceiveContent(String receiveContent) {
		this.receiveContent = receiveContent;
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
