package com.hjbello;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hjbello.dao.RecordActivityDAO;
import com.hjbello.dao.TAppActivityLog;
import com.hjbello.webcam.DetectMotion;
 
 
@RestController
public class CaptureImageRestController {
 
    @RequestMapping("/")
    public String welcome() {//Welcome page, non-rest
        return "Welcome to RestTemplate Example.";
    }
 
    @RequestMapping("/captureStopIn/{seconds}")
    public ResponseEntity<CapturedMovement> captureGet(@PathVariable String seconds,HttpServletRequest httpRequest) {//REST Endpoint.
    	RequestCapture request = new RequestCapture();
    	request.setSeconds(Integer.parseInt(seconds));
        
        return capturePost(request, httpRequest);
    }
    
    @RequestMapping(value = "/capture/", method = RequestMethod.POST)
    public ResponseEntity<CapturedMovement> capturePost(@RequestBody RequestCapture request,  HttpServletRequest httpRequest)  {//REST Endpoint.
    	int seconds = request.getSeconds();
    	Date date = new Date();
    	DetectMotion detector = new DetectMotion("" + seconds);
    	try {
			detector.record();
		} catch (IOException e) {
			e.printStackTrace();
		}
     	ArrayList<String> imagesPath = detector.getListOfObtaiedImages();
     	ArrayList<byte[]> imagesBase64 = detector.getListOfObtainedImageBase64(); 
    	
     	//Here we prepare the response
    	CapturedMovement response = new CapturedMovement();
    	response.setImagesPath(imagesPath);
    	response.setImagesBase64(imagesBase64);
    	response.setDateOfCapture(date.toString());
    	
    	// we record this request in the database
    	TAppActivityLog tAppActivityLog = new TAppActivityLog();
    	tAppActivityLog.setDateAccessed(new Date());
    	tAppActivityLog.setPhotosSent(imagesPath.toString());  	
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	tAppActivityLog.setUsername(username);
    	tAppActivityLog.setUserIp(httpRequest.getRemoteAddr());
    	
    	RecordActivityDAO recordActivityDao = new 	RecordActivityDAO();
      	recordActivityDao.save(tAppActivityLog);

        return new ResponseEntity<CapturedMovement>(response, HttpStatus.OK);
    }
}
