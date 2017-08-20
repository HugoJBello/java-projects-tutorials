package com.hjbello;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

/**
 * {@link ItemReader} with hard-coded input data.
 */

@Component("readerStep2")
public class NoOpItemReaderStep2 implements ItemReader<String> {
	
	private static final Log log = LogFactory.getLog(NoOpItemReaderStep2.class);

	private int index = 0;
	
	/**
	 * Reads next record from input
	 */
	public String read() throws Exception {
		if (index < 1) {
			log.info("------------------------------------------");
			log.info("Inside step 2");
			log.info("------------------------------------------");
			index++;
			return "done";
		}
		else {
			return null;
		}
		
	}

}
