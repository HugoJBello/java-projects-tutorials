
package com.hjbello.tests.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.apache.log4j.lf5.PassingLogRecordFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.hjbello.config.AppConfiguration;
import com.hjbello.dao.RecordActivityDAOImpl;
import com.hjbello.dao.TAppActivityLog;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfiguration.class})


public class RecordActivityTest {

	@Autowired
	RecordActivityDAOImpl dao;
	
	
	@Test
	public void test() {
		TAppActivityLog tAppActivityLog = new TAppActivityLog();
		tAppActivityLog.setDateAccessed(new Date());
		tAppActivityLog.setUsername("test_user");
		
		
		try {
			dao.save(tAppActivityLog);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Problem inserting data in database");
		}
	}

}
