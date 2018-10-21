package com.hjbello.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.sqlite.SQLiteDataSource;
 
@Repository("recordActivityDAO")
public class RecordActivityDAOImpl implements RecordActivityDAO {
	final static Logger logger = LoggerFactory.getLogger(RecordActivityDAOImpl.class);
	
	@Autowired
	SQLiteDataSource dataSource;
	
	//for more info: http://www.sqlitetutorial.net/sqlite-java/sqlite-jdbc-driver/
	public Connection connect() {
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			logger.info("Connected to database");
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		return conn;
	}

	public  void save(TAppActivityLog tAppActivityLog) {
		String sql = "INSERT INTO app_activity_log (username, user_ip, date_accessed,photos_sent) "
				+ "values (?,?,?,?)";

		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1,tAppActivityLog.getUsername());
			pstmt.setString(2,tAppActivityLog.getUserIp());
			pstmt.setTimestamp(3,new java.sql.Timestamp(tAppActivityLog.getDateAccessed().getTime()));
			pstmt.setString(4,tAppActivityLog.getPhotosSent());

			pstmt.executeUpdate();
			logger.info("Activity recorded");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}


}
