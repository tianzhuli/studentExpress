package com.me.domain;

import java.sql.Timestamp;

public class SystemInformation {
	private int systemInformationID;
	private String managerID;
	private String informationTitle;
	private Timestamp informationTime;
	private String informationContent;
	private String informationType;
	private String informationUrl;

	public String getInformationTitle() {
		return informationTitle;
	}

	public void setInformationTitle(String informationTitle) {
		this.informationTitle = informationTitle;
	}

	public String getInformationUrl() {
		return informationUrl;
	}

	public void setInformationUrl(String informationUrl) {
		this.informationUrl = informationUrl;
	}

	public String getInformationType() {
		return informationType;
	}

	public void setInformationType(String informationType) {
		this.informationType = informationType;
	}

	public int getSystemInformationID() {
		return systemInformationID;
	}

	public void setSystemInformationID(int systemInformationID) {
		this.systemInformationID = systemInformationID;
	}

	public String getManagerID() {
		return managerID;
	}

	public void setManagerID(String managerID) {
		this.managerID = managerID;
	}

	public Timestamp getInformationTime() {
		return informationTime;
	}

	public void setInformationTime(Timestamp informationTime) {
		this.informationTime = informationTime;
	}

	public String getInformationContent() {
		return informationContent;
	}

	public void setInformationContent(String informationContent) {
		this.informationContent = informationContent;
	}

}
