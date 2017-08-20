package com.hjbello.webcam;

import javax.annotation.PostConstruct;

import com.github.sarxos.webcam.Webcam;



public class ConfigurationBean {
	String user = "";
	String os = "";
	String webcamInfo = "";
	String resolution = "";
	
 	public void init(){
		
		user = System.getProperty("user.name");
 
		os= System.getProperty("os.name").toLowerCase();
		
		Webcam webcam = Webcam.getDefault();
		if (webcam != null) {
			 webcamInfo= webcam.getName();
		} else {
			webcamInfo="No webcam detected";
		}
		webcam.close();
		webcam.getLock().disable();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getWebcamInfo() {
		return webcamInfo;
	}

	public void setWebcamInfo(String webcamInfo) {
		this.webcamInfo = webcamInfo;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

}
