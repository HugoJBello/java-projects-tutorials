package com.hjbello.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {		
		logger.info("Accessing home page");
		return "home";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homePage(Model model) {		
		logger.info("Accessing home page");
		return "home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		logger.info("Accessing login page");
		return "login";
	}
	
	@RequestMapping(value = "/application", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN"})
	public String application(Model model) {
		logger.info("Accessing application page");
		return "application";
	}
	
	 
}
