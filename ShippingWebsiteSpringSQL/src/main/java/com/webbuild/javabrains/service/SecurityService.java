package com.webbuild.javabrains.service;

//Reference call for the security layer and all added methods
public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);

	String FindUserName();

	String FindAuthentication();
}
