package com.hospital.base.business.auth.login;

public interface SecurityService {
	String findLoggedInUsername();

    void autoLogin(String username, String password);
}
