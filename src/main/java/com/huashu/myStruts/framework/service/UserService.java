package com.huashu.myStruts.framework.service;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
	public int saveUser(HttpServletRequest req);
	
	public int checkUser(HttpServletRequest req);
}
