package com.hjbello.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.hjbello.domain")
@EntityScan(basePackages = "com.hjbello.domain")
public class AppLauncher {

	public static void main(String[] args) {
		SpringApplication.run(AppLauncher.class, args);
	}
}
