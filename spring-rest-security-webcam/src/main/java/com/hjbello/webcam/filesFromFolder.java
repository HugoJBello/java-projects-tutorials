package com.hjbello.webcam;


import java.io.File;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
 

public class filesFromFolder {
	private File folder =  new File("C:/captured_photos");
	private  ArrayList<File> listOfFiles = new ArrayList<File>();
	private  ArrayList<String> listOfFilesString = new ArrayList<String>();


    @PostConstruct
	public void addFilesToList(final File folder) {
 			for (final File fileEntry : folder.listFiles()) {
 				// we make sure that we are considering accessible folders only. We are using recursion here
				if (fileEntry.isDirectory() && fileEntry.canRead()) {
					addFilesToList(fileEntry);
				} else {
					this.listOfFiles.add(fileEntry);
					this.listOfFilesString.add(fileEntry.getName());
				}
			}
		 
	}
	public filesFromFolder (File inputFolder){
		this.folder = inputFolder;
		this.addFilesToList(this.folder);
	}
	public filesFromFolder (String inputFolderString){
		this.folder = new File(inputFolderString);
		this.addFilesToList(this.folder);

	}
	public File getFolder() {
		return folder;
	}
	public void setFolder(File folder) {
		this.folder = folder;
	}
	public ArrayList<File> getListOfFiles() {
		return listOfFiles;
	}
	public void setListOfFiles(ArrayList<File> listOfFiles) {
		this.listOfFiles = listOfFiles;
	}

	public ArrayList<String> getListOfFilesString() {
		return listOfFilesString;
	}

	public void setListOfFilesString(ArrayList<String> listOfFilesString) {
		this.listOfFilesString = listOfFilesString;
	}

}
