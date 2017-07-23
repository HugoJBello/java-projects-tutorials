package com.hjbello.config;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
 
@RestController
public class AjaxController {
	@RequestMapping(value = "/ajaxEndpoint", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<AjaxResponse> capture (@RequestBody AjaxRequest request) throws JsonProcessingException{
		System.out.println("modifying");
		AjaxResponse response = new AjaxResponse();
		response.setText1(request.getText1() + "MODIFIED");
		response.setText2(request.getText2() + "MODIFIED");
		Date date = new Date();
		response.setDate(date.toString());
 		return new ResponseEntity<AjaxResponse>(response, HttpStatus.OK);
	}

}
