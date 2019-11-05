package com.webbuild.javabrains;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"com.webbuild.javabrains"})
public class ApplicationStartUp extends SpringBootServletInitializer{

	//http://localhost:8084/Shipping/home
	public static void main(String[] args) {
		//the run spring server command
		SpringApplication.run(ApplicationStartUp.class, args);
	}

}
