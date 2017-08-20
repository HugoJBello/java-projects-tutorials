package com.hjbello.dao;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public class ActivityTracker {

 	public RecordActivityDAOImpl recordActivityDao;
	
 	public ImagesDAOImpl imagesDAO;
	
 	public void updateActivity(ArrayList<String> imagesPath, String username, String userIp){
 		Date date = new Date();
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
 	}

	public ActivityTracker(RecordActivityDAOImpl recordActivityDao, ImagesDAOImpl imagesDAO) {
		super();
		this.recordActivityDao = recordActivityDao;
		this.imagesDAO = imagesDAO;
	}

}
