package com.hjbello.dao;

import java.sql.Connection;

public interface RecordActivityDAO {
	public Connection connect();
	
	public void save(TAppActivityLog tAppActivityLog);

}
