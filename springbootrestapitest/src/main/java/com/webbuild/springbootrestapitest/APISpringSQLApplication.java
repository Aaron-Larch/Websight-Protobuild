package com.webbuild.springbootrestapitest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"com.webbuild.springbootrestapitest"})
public class APISpringSQLApplication extends SpringBootServletInitializer {
    
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(APISpringSQLApplication.class);
    }
	
	//http://localhost:8083/login
	public static void main(String[] args) {
		SpringApplication.run(APISpringSQLApplication.class, args);
	}
}
