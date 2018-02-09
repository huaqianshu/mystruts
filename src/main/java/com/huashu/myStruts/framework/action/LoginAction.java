package com.huashu.myStruts.framework.action;

import javax.servlet.http.HttpServletRequest;

import com.huashu.myStruts.framework.annotation.MyAgent;
import com.huashu.myStruts.framework.service.UserService;

public class LoginAction {
	@MyAgent("serServiceImpl")
	private UserService userService;
	
	public String singin(HttpServletRequest req){
		int res = userService.saveUser(req);
		if(res==0)
			return "success";
		return "fail";
	}
	public String checkuser(HttpServletRequest req){
		int res = userService.checkUser(req);
		if(res==0)
			return "success";
		return "fail";
	}
}
