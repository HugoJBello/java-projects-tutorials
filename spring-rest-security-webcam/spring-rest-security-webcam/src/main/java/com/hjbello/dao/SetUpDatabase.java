package com.hjbello.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SetUpDatabase{
	
	final static Logger logger = LoggerFactory.getLogger(SetUpDatabase.class);
	
	public void createTables (){
		String sqlCreateTableAppActivityLog = "CREATE TABLE IF NOT EXISTS app_activity_log (\n"+
				"log_id  INTEGER PRIMARY KEY AUTOINCREMENT,\n"+
				"username VARCHAR(45) not null,\n"+
				"user_ip VARCHAR(45),\n"+
				"photos_sent VARCHAR(100),\n"+
				"date_accessed DATETIME);";

		String sqlCreateTableImages = "CREATE TABLE IF NOT EXISTS images (\n"+
				"image_id  INTEGER PRIMARY KEY AUTOINCREMENT,\n"+
				"filename VARCHAR(100),\n"+
				"date_recorded DATETIME,\n"+
				"username VARCHAR(45) not null,\n"+
				"user_ip VARCHAR(45));";
		try {
			Connection conn= this.connect();

			Statement stmt1 = conn.createStatement();
			stmt1.execute(sqlCreateTableAppActivityLog);
			logger.info("Activity log table created (if it does not exist)");

			Statement stmt2 = conn.createStatement();
			stmt2.execute(sqlCreateTableImages);
			logger.info("Images table created (if it does not exist)");

		} catch (Exception e) {

		}
	}
	
	public Connection connect() {
		// SQLite connection string
		String dbFile = System.getProperty("user.home") + "/sqlite_databases/webcam_controller_database.db";
		String url = "jdbc:sqlite:"+dbFile;
		createDBFolder();

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
			logger.info("Connected to database");

		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		return conn;
	}
	
	public void createDBFolder(){
		String path = System.getProperty("user.home")	+ "/sqlite_databases/";
		File directory = new File(path);
		if(!directory.exists()){
			directory.mkdir();
		}
	}
}
