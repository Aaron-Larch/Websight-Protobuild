package com.webbuild.javabrains.service;

import com.webbuild.javabrains.model.PasswordResetToken;

//Reference call for the security layer and all added methods
public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);

	String FindUserName();

	String FindAuthentication();
	
	String validatePasswordResetToken(String token);

	PasswordResetToken passToken(String token);
}
