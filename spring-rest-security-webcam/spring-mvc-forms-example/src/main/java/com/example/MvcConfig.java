package com.example;

   
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

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