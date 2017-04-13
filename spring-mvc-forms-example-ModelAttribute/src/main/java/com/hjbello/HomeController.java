package com.hjbello;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {		
		logger.info(".....");
		
		return "home";
	}

	@RequestMapping(value = "/send-form", method=RequestMethod.GET)
	public ModelAndView formGet() {
 	    ModelAndView mv = new ModelAndView();
		Form form = new Form();
		mv.addObject("formBean", form);
		mv.setViewName("form_page");
		return mv;
	    }
	@RequestMapping(value = "/send-form", method=RequestMethod.POST)
	public ModelAndView formPost(@ModelAttribute("formBean") Form form) {
 	    ModelAndView mv = new ModelAndView();
 	    mv.addObject("text1",form.getText1());
 	    mv.addObject("text2",form.getText2());  
		mv.setViewName("response_page");
		return mv;
	    }

}
