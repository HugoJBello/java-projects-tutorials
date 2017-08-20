package com.hjbello.webcam;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DirectoryCreator {
	private String nameTodaysFolder;
	private File todaysFolder;
	public DirectoryCreator(){
		String destinyFolder = System.getProperty("user.home")
				+ "/captured_photos";
		DateFormat dateFormat_day = new SimpleDateFormat("yyyyMMdd");
		Date date2 = new Date();

		// crating destiny folder if it doesnt exists
		File destinyFoldercheck = new File(destinyFolder);
		if (!destinyFoldercheck.exists()) {
			boolean result = false;

			try {
				destinyFoldercheck.mkdir();
				result = true;
			} catch (SecurityException se) {
				// handle it
			}
			if (result) {
				System.out.println("outputoFolder crated created");
			}
		}

		this.todaysFolder = new File(destinyFolder + "/"
				+ dateFormat_day.format(date2).toString());
		this.nameTodaysFolder= todaysFolder.getName();
		// if the directory does not exist, create it
		if (!todaysFolder.exists()) {
			boolean result = false;

			try {
				todaysFolder.mkdir();
				result = true;
			} catch (SecurityException se) {
				// handle it
			}
			if (result) {
				System.out.println("DIR created");
			}
		}
	}
	public String getNameTodaysFolder() {
		return nameTodaysFolder;
	}
	public void setNameTodaysFolder(String nameTodaysFolder) {
		this.nameTodaysFolder = nameTodaysFolder;
	}
	public File getTodaysFolder() {
		return todaysFolder;
	}
	public void setTodaysFolder(File todaysFolder) {
		this.todaysFolder = todaysFolder;
	}
}
