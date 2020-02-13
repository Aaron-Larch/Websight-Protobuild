package com.webbuild.javabrains;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"com.webbuild.javabrains"}) //Declare all required packages
public class ApplicationStartUp extends SpringBootServletInitializer{

	//http://localhost:8084/login
	//https://localhost:8443/HomePage
	public static void main(String[] args) {
		//the run spring server command
		SpringApplication.run(ApplicationStartUp.class, args);
	}
}
