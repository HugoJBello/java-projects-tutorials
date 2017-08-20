package com.hjbello.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hjbello.dao.ActivityTracker;
import com.hjbello.dao.ImagesDAOImpl;
import com.hjbello.dao.RecordActivityDAOImpl;
import com.hjbello.dao.SetUpDatabase;
import com.hjbello.dao.TAppActivityLog;
import com.hjbello.dao.TImages;
import com.hjbello.webcam.MotionDetector;

  
 
@RestController
public class AppRestController {

	@Autowired 
	SetUpDatabase setUpDatabase;
	
	@Autowired
	RecordActivityDAOImpl recordActivityDao;
	
	@Autowired
	ImagesDAOImpl imagesDAO;
	
    @PostConstruct
    public void init() { 
    	setUpDatabase.createTables();
    }
 
    @RequestMapping("/captureStopIn/{seconds}")
    public ResponseEntity<CapturedMovement> captureGet(@PathVariable String seconds,HttpServletRequest httpRequest) {//REST Endpoint.
    	RequestCapture request = new RequestCapture();
    	request.setSeconds(Integer.parseInt(seconds));
        
        return capturePost(request, httpRequest);
    }
    
    @RequestMapping(value = "/capture/", method = RequestMethod.POST)
    public ResponseEntity<CapturedMovement> capturePost(@RequestBody RequestCapture request,  HttpServletRequest httpRequest)  {//REST Endpoint.
    	
    	// First we find out the username and IP.
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	String userIp = httpRequest.getRemoteAddr();
    	
    	// We record
    	int seconds = request.getSeconds();
    	Date date = new Date();
    	MotionDetector detector = new MotionDetector("" + seconds);
    	try {
			detector.record();
		} catch (IOException e) {
			e.printStackTrace();
		}
     	ArrayList<String> imagesPath = detector.getListOfObtaiedImages();
     	ArrayList<String> imagesBase64 = detector.getListOfObtainedImageBase64(); 
    	
     	//Here we prepare the response
    	CapturedMovement response = new CapturedMovement();
    	response.setImagesPath(imagesPath);
    	response.setImagesBase64(imagesBase64);
    	response.setDateOfCapture(date);
    	
    	// we record this request in the database
//    	ActivityTracker activityTracker = new ActivityTracker(recordActivityDao, imagesDAO);
//     	activityTracker.updateActivity(imagesPath, username, userIp);
 		TAppActivityLog tAppActivityLog = new TAppActivityLog();
    	tAppActivityLog.setDateAccessed(new Date());
    	tAppActivityLog.setPhotosSent(imagesPath.toString());  	  	
    	tAppActivityLog.setUsername(username);
    	tAppActivityLog.setUserIp(userIp); 	
      	recordActivityDao.save(tAppActivityLog);  	
      	for (int index=0; index < imagesPath.size(); index++){
      		TImages tImages = new TImages(imagesPath.get(index), date, username, userIp);
      		imagesDAO.save(tImages);
      	}

        return new ResponseEntity<CapturedMovement>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/camInfo/", method = RequestMethod.GET)
    public ResponseEntity<SystemInfo> capturePost()  {
    	SystemInfo sysInfo = new SystemInfo();
    	sysInfo.obtainInfo();
        return new ResponseEntity<SystemInfo>(sysInfo, HttpStatus.OK);
    }
}
