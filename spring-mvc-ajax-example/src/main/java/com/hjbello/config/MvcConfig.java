package com.hjbello.config;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("com.hjbello.config")
public class MvcConfig extends WebMvcConfigurerAdapter{
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	//    @Override
	//    public void addViewControllers(ViewControllerRegistry registry) {
	//        registry.addViewController("/home").setViewName("home");
	//        registry.addViewController("/").setViewName("home");
	//        registry.addViewController("/hello").setViewName("hello");
	//        registry.addViewController("/login").setViewName("login");
	//        registry.addViewController("/403").setViewName("403");
	//    }    
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if(System.getProperty("os.name").toLowerCase().contains("windows")){
			registry.addResourceHandler("/images/**").addResourceLocations("file:///C:/");
		} else {
			//System.getProperty("user.home")
			registry.addResourceHandler("/images/**").addResourceLocations("file:/");

		}
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}   



}