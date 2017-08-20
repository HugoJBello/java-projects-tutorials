package com.hjbello.controllers;

import java.util.ArrayList;
import java.util.Date;


public class CapturedMovement {

	private Date dateOfCapture;

	private ArrayList<String> imagesPath;

	private ArrayList<String> imagesBase64;

	public Date getDateOfCapture() {
		return dateOfCapture;
	}

	public void setDateOfCapture(Date dateOfCapture) {
		this.dateOfCapture = dateOfCapture;
	}

	public ArrayList<String> getImagesBase64() {
		return imagesBase64;
	}

	public void setImagesBase64(ArrayList<String> imagesBase64) {
		this.imagesBase64 = imagesBase64;
	}

	public CapturedMovement(Date dateOfCapture, ArrayList<String> imagesBase64) {
		super();
		this.dateOfCapture = dateOfCapture;
		this.imagesBase64 = imagesBase64;
	}

	public CapturedMovement() {
		super();
	}

	public ArrayList<String> getImagesPath() {
		return imagesPath;
	}

	public void setImagesPath(ArrayList<String> imagesPath) {
		this.imagesPath = imagesPath;
	}
	
}
