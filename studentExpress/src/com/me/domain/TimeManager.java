package com.me.domain;

import java.sql.Timestamp;

public class TimeManager {
	private String userID;
	private Timestamp upper;
	private Timestamp down;
	private Timestamp timeType;
	public Timestamp getTimeType() {
		return timeType;
	}
	public void setTimeType(Timestamp timeType) {
		this.timeType = timeType;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public Timestamp getUpper() {
		return upper;
	}
	public void setUpper(Timestamp upper) {
		this.upper = upper;
	}
	public Timestamp getDown() {
		return down;
	}
	public void setDown(Timestamp down) {
		this.down = down;
	}
	
}
