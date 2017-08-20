package com.hjbello.controllers;

import com.github.sarxos.webcam.Webcam;

public class SystemInfo {

	private String operativeSystem;
	private String camInfo;
	private String systemCamUser;
	private String resolution;

	public void obtainInfo(){

		systemCamUser = System.getProperty("user.name");

		operativeSystem= System.getProperty("os.name").toLowerCase();

		Webcam webcam = Webcam.getDefault();
		if (webcam != null) {
			camInfo= webcam.getName();
		} else {
			camInfo="No webcam detected";
		}
		webcam.close();
		webcam.getLock().disable();
	}

	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getOperativeSystem() {
		return operativeSystem;
	}
	public void setOperativeSystem(String operativeSystem) {
		this.operativeSystem = operativeSystem;
	}
	public String getCamInfo() {
		return camInfo;
	}
	public void setCamInfo(String camInfo) {
		this.camInfo = camInfo;
	}
	public String getSystemCamUser() {
		return systemCamUser;
	}
	public void setSystemCamUser(String systemCamUser) {
		this.systemCamUser = systemCamUser;
	}
	public SystemInfo(String operativeSystem, String camInfo, String systemCamUser) {
		super();
		this.operativeSystem = operativeSystem;
		this.camInfo = camInfo;
		this.systemCamUser = systemCamUser;
	}
	public SystemInfo() {
		super();
	}



}
