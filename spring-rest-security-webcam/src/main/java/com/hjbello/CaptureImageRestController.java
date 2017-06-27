package com.hjbello;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hjbello.webcam.DetectMotion;
 
 
@RestController
public class CaptureImageRestController {
 
    @RequestMapping("/")
    public String welcome() {//Welcome page, non-rest
        return "Welcome to RestTemplate Example.";
    }
 
    @RequestMapping("/captureStopIn/{seconds}")
    public ResponseEntity<CapturedMovement> captureGet(@PathVariable String seconds) {//REST Endpoint.
    	RequestCapture request = new RequestCapture();
    	request.setSeconds(Integer.parseInt(seconds));
        
        return capturePost(request);
    }
    
    @RequestMapping(value = "/capture/", method = RequestMethod.POST)
    public ResponseEntity<CapturedMovement> capturePost(@RequestBody RequestCapture request)  {//REST Endpoint.
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
    	
    	CapturedMovement response = new CapturedMovement();
    	response.setImagesPath(imagesPath);
    	response.setImagesBase64(imagesBase64);
    	response.setDateOfCapture(date.toString());
        return new ResponseEntity<CapturedMovement>(response, HttpStatus.OK);
    }
}
