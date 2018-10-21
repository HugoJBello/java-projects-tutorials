package com.hjbello.dao;

import java.sql.Connection;

public interface ImagesDAO {
	public Connection connect();
	
	public void save(TImages tImages);

}
