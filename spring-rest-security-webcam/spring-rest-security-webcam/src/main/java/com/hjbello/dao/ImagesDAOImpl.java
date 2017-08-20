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
import org.sqlite.SQLiteDataSource;
 
@Repository("imagesDAO")
public class ImagesDAOImpl implements ImagesDAO {
	final static Logger logger = LoggerFactory.getLogger(ImagesDAOImpl.class);

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

	public  void save(TImages tImages) {
		String sql = "INSERT INTO images (filename, date_recorded, username, user_ip) "
				+ "values (?,?,?,?)";
		
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1,tImages.getFilename());
			pstmt.setTimestamp(2,new java.sql.Timestamp(tImages.getDateRecorded().getTime()));
			pstmt.setString(3,tImages.getUsername());
			pstmt.setString(4,tImages.getUserIp());

			pstmt.executeUpdate();
			logger.info("Image recorded in database");
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
	}


}
