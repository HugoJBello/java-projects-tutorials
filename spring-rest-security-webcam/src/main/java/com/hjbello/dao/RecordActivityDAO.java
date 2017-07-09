package com.hjbello.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 

public class RecordActivityDAO {
	final static Logger logger = LoggerFactory.getLogger(RecordActivityDAO.class);

	//for more info: http://www.sqlitetutorial.net/sqlite-java/sqlite-jdbc-driver/
	private Connection connect() {
		// SQLite connection string
		String dbFile = System.getProperty("user.home") + "/sqlite_databases/users_db.db";
		String url = "jdbc:sqlite:"+dbFile;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
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
			pstmt.setString(3,  tAppActivityLog.getDateAccessed().toString());
			pstmt.setString(4,tAppActivityLog.getPhotosSent());

			pstmt.executeUpdate();
			logger.info("Activity recorded");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}


}
