package com.hjbello.webcam;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.codec.binary.Base64OutputStream; 
import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamMotionDetector;
import com.hjbello.dao.RecordActivityDAOImpl;

 
public class DetectMotion {
	final static Logger logger = LoggerFactory.getLogger(RecordActivityDAOImpl.class);
	private static final int INTERVAL = 100; // ms

	private Webcam webcam = Webcam.getDefault();
	private int threshold = 25;
	private int inertia = 1000; // how long motion is valid
	private String stopInSeconds;
	private String destinyFolder = System.getProperty("user.home")
			+ "/captured_photos";
	private String todaysFolder;
	private ArrayList<String> listOfObtaiedImages = new ArrayList<String>();
	private ArrayList<String> listOfObtainedImageBase64 = new ArrayList<String>();

	public DetectMotion(String stopInSeconds) {
		super();
		this.stopInSeconds = stopInSeconds;
	}

	public void obtainFolderNames(){
		DateFormat dateFormat_day = new SimpleDateFormat("yyyyMMdd");
		Date date2 = new Date();
		todaysFolder= destinyFolder + "/"
				+ dateFormat_day.format(date2).toString().replace(":", "_");
	}
	
	public static String convertToBase64(BufferedImage image) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		OutputStream b64 = new Base64OutputStream(os);
		ImageIO.write(image, "png", b64);
		String result = os.toString("UTF-8");	 
		return result;
	}
	
	public String record() throws IOException {
		ExecutorService executor = Executors.newFixedThreadPool(1);

				//possible ones are [176x144] [320x240] [640x480]
				//webcam.setViewSize(new Dimension(640, 480));
				webcam.setViewSize(new Dimension(640, 480));
				WebcamMotionDetector detector = new WebcamMotionDetector(webcam);
				detector.setInterval(INTERVAL);
				detector.start();

				

				obtainFolderNames();
				
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
						logger.info("outputFolder crated created");
					}
				}
				
				
				File todaysFolderFile = new File(todaysFolder);

				// if the directory does not exist, create it
				if (!todaysFolderFile.exists()) {
					boolean result = false;

					try {
						todaysFolderFile.mkdir();
						result = true;
					} catch (SecurityException se) {
						// handle it
					}
					if (result) {
						logger.info("DIR created");
					}
				}

				long startTime = System.currentTimeMillis();
				long endTime = startTime + Integer.parseInt(stopInSeconds)
						* 1000;
				while (System.currentTimeMillis() < endTime) {
					if (detector.isMotion()) {

						BufferedImage image = webcam.getImage();

 
						DateFormat dateFormat_with_hour = new SimpleDateFormat(
								"yyyyMMdd-HH_mm_ss:SSS");
						Date date = new Date();

						String filename = todaysFolder + "/"
								+ dateFormat_with_hour.format(date).replace(":", "_") + ".png";
						listOfObtaiedImages.add(filename);
						listOfObtainedImageBase64.add(convertToBase64(image));
						logger.info("Photo taken and saved in " + filename);

						try {
							BufferedOutputStream imageOutputStream = new BufferedOutputStream(
									new FileOutputStream(new File(filename)));
							ImageIO.write(image, "PNG", imageOutputStream);
							imageOutputStream.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

					try {
						Thread.sleep(INTERVAL * 2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			
		webcam.close();
		webcam.getLock().disable();
		return null;
	}

	public String getStopInSeconds() {
		return stopInSeconds;
	}

	public void setStopInSeconds(String stopInSeconds) {
		this.stopInSeconds = stopInSeconds;
	}

	public String getDestinyFolder() {
		return destinyFolder;
	}

	public void setDestinyFolder(String destinyFolder) {
		this.destinyFolder = destinyFolder;
	}
	public void setTodaysFolder(String todaysFolder) {
		this.todaysFolder = todaysFolder;
	}
	public ArrayList<String> getListOfObtaiedImages() {
		return listOfObtaiedImages;
	}

	public void setListOfObtaiedImages(ArrayList<String> listOfObtaiedImages) {
		this.listOfObtaiedImages = listOfObtaiedImages;
	}
	public String getTodaysFolder() {
		return todaysFolder;
	}

	public ArrayList<String> getListOfObtainedImageBase64() {
		return listOfObtainedImageBase64;
	}

	public void setListOfObtainedImageBase64(ArrayList<String> listOfObtainedImageBase64) {
		this.listOfObtainedImageBase64 = listOfObtainedImageBase64;
	}

}
