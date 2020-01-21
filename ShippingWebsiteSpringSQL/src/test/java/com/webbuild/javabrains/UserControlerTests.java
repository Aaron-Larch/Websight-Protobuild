package com.webbuild.javabrains;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.webbuild.javabrains.controller.UserController;



@SpringBootTest
@AutoConfigureMockMvc
public class UserControlerTests {	
	
	@Autowired
	private MockMvc mockMvc;

    private static final String COOKIE_NAME = CookieLocaleResolver.DEFAULT_COOKIE_NAME;
	
	@BeforeEach
	public void setup() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setCookieDomain("domain");
		localeResolver.setCookieHttpOnly(true);

		//Generate Cookies t
		this.mockMvc = standaloneSetup(new UserController())
				.addInterceptors(new LocaleChangeInterceptor())
				.setLocaleResolver(localeResolver)
				.defaultRequest(get("/").param("locale", "en_US"))
				.alwaysExpect(status().isOk())
				.build();
	}

	
	@Test
	public void testUserLogin() throws Exception {
	    RequestBuilder requestBuilder = get("/login")
	            .param("username", "SharyMary")
	            .param("password", "password");
	    mockMvc.perform(requestBuilder)
	            .andDo(print())
	            .andExpect(view().name("UserInterFace/login"))
	            .andExpect(status().isOk())
	            .andExpect(cookie().exists(COOKIE_NAME));
	}
	
	@Test
	public void TestInncorectUserLogin() throws Exception {
		RequestBuilder requestBuilder = get("/login")
	            .param("username", "SharyMary")
	            .param("password", "notarealword");
		
		this.mockMvc.perform(requestBuilder)
			.andDo(print())
            .andExpect(view().name("UserInterFace/login"))
			.andExpect(status().is(405))
			.andExpect(model().attribute("error", equalTo("Your username and password is invalid.")));
	}
	
}
