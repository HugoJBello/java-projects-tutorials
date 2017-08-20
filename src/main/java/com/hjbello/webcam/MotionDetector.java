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
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamMotionDetector;
import com.hjbello.dao.ImagesDAO;
import com.hjbello.dao.ImagesDAOImpl;
import com.hjbello.dao.RecordActivityDAOImpl;
import com.hjbello.dao.TAppActivityLog;
import com.hjbello.dao.TImages;


public class MotionDetector {
	final static Logger logger = LoggerFactory.getLogger(RecordActivityDAOImpl.class);
	private static final int INTERVAL = 100; // ms

	@Autowired
	ImagesDAOImpl imagesDao;
	
	@Autowired 
	RecordActivityDAOImpl recordActivityDao;
	
	private String stopInSeconds;
	private String destinyDirectory = System.getProperty("user.home") + "/captured_photos";
	private String todaysDirectory;
	private ArrayList<String> listOfObtaiedImages = new ArrayList<String>();
	private ArrayList<String> listOfObtainedImageBase64 = new ArrayList<String>();

	public MotionDetector(String stopInSeconds) {
		super();
		this.stopInSeconds = stopInSeconds;
	}

	public void record() throws IOException {

		Webcam webcam = Webcam.getDefault();
		
		obtainDirNames();

		createTodaysSubdirectory();

		WebcamMotionDetector detector = obtainImageDetector(webcam);
		detector.setInterval(INTERVAL);
		detector.start();

		long startTime = System.currentTimeMillis();
		long endTime = startTime + Integer.parseInt(stopInSeconds)* 1000;
		while (System.currentTimeMillis() < endTime) {
			if (detector.isMotion()) {
				recordAndSaveImage(webcam);
			}
			try {
				Thread.sleep(INTERVAL * 2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		webcam.close();
		webcam.getLock().disable();
	}

	private void recordAndSaveImage(Webcam webcam) throws IOException {
		BufferedImage image = webcam.getImage();

		DateFormat dateFormat_with_hour = new SimpleDateFormat("yyyyMMdd-HH_mm_ss:SSS");
		Date date = new Date();
		
		String filename = dateFormat_with_hour.format(date).replace(":", "_") + ".png";;
		String filePath = todaysDirectory + "/" + filename;

		listOfObtaiedImages.add(filePath);
		
		listOfObtainedImageBase64.add(convertToBase64(image));
		logger.info("Photo taken and saved in " + filePath);

		try {
			BufferedOutputStream imageOutputStream = new BufferedOutputStream(
					new FileOutputStream(new File(filePath)));
			ImageIO.write(image, "PNG", imageOutputStream);
			imageOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	private void createTodaysSubdirectory() {
		
		// our directory for today will be inside a destiny directory
		createDestinyDirectory();

		File todaysDirFile = new File(todaysDirectory);
		// if the directory does not exist, create it
		if (!todaysDirFile.exists()) {
			boolean result = false;

			try {
				todaysDirFile.mkdir();
				result = true;
			} catch (SecurityException se) {
				// handle it
			}
			if (result) {
				logger.info("DIR created");
			}
		}
	}

	private void createDestinyDirectory() {
		// crating destiny folder (for all the images, today and other days) if it does not exists
		File destinyDirectorycheck = new File(destinyDirectory);
		if (!destinyDirectorycheck.exists()) {
			try {
				destinyDirectorycheck.mkdir();
				logger.info("Destiny directory created");
			} catch (SecurityException se) {
				// handle it
			}			
		}	
	}

	private WebcamMotionDetector obtainImageDetector (Webcam webcam){
		//possible ones are [176x144] [320x240] [640x480]
		//webcam.setViewSize(new Dimension(640, 480));
		webcam.setViewSize(new Dimension(640, 480));
		WebcamMotionDetector detector = new WebcamMotionDetector(webcam);
		return detector;
	}
	private void obtainDirNames(){
		DateFormat dateFormat_day = new SimpleDateFormat("yyyyMMdd");
		Date date2 = new Date();
		todaysDirectory= destinyDirectory + "/"
				+ dateFormat_day.format(date2).toString().replace(":", "_");
	}

	private static String convertToBase64(BufferedImage image) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		OutputStream b64 = new Base64OutputStream(os);
		ImageIO.write(image, "png", b64);
		String result = os.toString("UTF-8");	 
		return result;
	}

	public String getStopInSeconds() {
		return stopInSeconds;
	}

	public void setStopInSeconds(String stopInSeconds) {
		this.stopInSeconds = stopInSeconds;
	}

	public String getDestinyDirectory() {
		return destinyDirectory;
	}

	public void setDestinyDirectory(String destinyFolder) {
		this.destinyDirectory = destinyFolder;
	}
	public void setTodaysFolder(String todaysFolder) {
		this.todaysDirectory = todaysFolder;
	}
	public ArrayList<String> getListOfObtaiedImages() {
		return listOfObtaiedImages;
	}

	public void setListOfObtaiedImages(ArrayList<String> listOfObtaiedImages) {
		this.listOfObtaiedImages = listOfObtaiedImages;
	}
	public String getTodaysDirectory() {
		return todaysDirectory;
	}

	public ArrayList<String> getListOfObtainedImageBase64() {
		return listOfObtainedImageBase64;
	}

	public void setListOfObtainedImageBase64(ArrayList<String> listOfObtainedImageBase64) {
		this.listOfObtainedImageBase64 = listOfObtainedImageBase64;
	}

}
