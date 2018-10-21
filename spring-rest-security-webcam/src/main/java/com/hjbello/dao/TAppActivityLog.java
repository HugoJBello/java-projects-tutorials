package com.hjbello.dao;

import java.util.Date;

public class TAppActivityLog {
	private String username;
	private String userIp;
	private String photosSent;
	private Date dateAccessed;
	
	public TAppActivityLog(String username, String userIp, String photosSent, Date dateAccessed) {
		super();
		this.username = username;
		this.userIp = userIp;
		this.photosSent = photosSent;
		this.dateAccessed = dateAccessed;
	}
	public TAppActivityLog() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public String getPhotosSent() {
		return photosSent;
	}
	public void setPhotosSent(String photosSent) {
		this.photosSent = photosSent;
	}
	public Date getDateAccessed() {
		return dateAccessed;
	}
	public void setDateAccessed(Date dateAccessed) {
		this.dateAccessed = dateAccessed;
	}

}
