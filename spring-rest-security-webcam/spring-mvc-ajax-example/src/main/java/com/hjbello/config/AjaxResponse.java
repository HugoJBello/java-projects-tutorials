package com.hjbello.config;

public class AjaxResponse {
	private String text1;
	private String text2;
	private String date;
	
	public String getText1() {
		return text1;
	}
	public void setText1(String text1) {
		this.text1 = text1;
	}
	public String getText2() {
		return text2;
	}
	public void setText2(String text2) {
		this.text2 = text2;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public AjaxResponse(String text1, String text2, String date) {
		super();
		this.text1 = text1;
		this.text2 = text2;
		this.date = date;
	}
	public AjaxResponse() {
		super();
	}
	 

}
