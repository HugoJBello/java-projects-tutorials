package com.hjbello;

import java.util.ArrayList;
import java.util.Date;


public class CapturedMovement {

	private String dateOfCapture;

	private ArrayList<String> imagesPath;

	private ArrayList<String> imagesBase64;

	public String getDateOfCapture() {
		return dateOfCapture;
	}

	public void setDateOfCapture(String dateOfCapture) {
		this.dateOfCapture = dateOfCapture;
	}

	public ArrayList<String> getImagesBase64() {
		return imagesBase64;
	}

	public void setImagesBase64(ArrayList<String> imagesBase64) {
		this.imagesBase64 = imagesBase64;
	}

	public CapturedMovement(String dateOfCapture, ArrayList<String> imagesBase64) {
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
