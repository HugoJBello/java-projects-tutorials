package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
  
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
		logger.info(".....");
		return "home";
	}
	
	@RequestMapping(value = "/send", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView form2(@RequestParam("id") String id) {
	    System.out.println(id);
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("home2");
	    mv.addObject("id", id);
		return mv;
	    }

}
