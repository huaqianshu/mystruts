package com.huashu.myStruts.framework.action;

import javax.servlet.http.HttpServletRequest;

import com.huashu.myStruts.framework.annotation.MyAgent;
import com.huashu.myStruts.framework.dao.UserDao;
import com.huashu.myStruts.framework.dao.impl.UserDaoImpl;
import com.huashu.myStruts.framework.model.User;
import com.huashu.myStruts.framework.service.UserService;

public class LoginAction {
	
	public String singin(HttpServletRequest req){
		User user = new User();
		user.setIdcard(req.getAttribute("Idcard").toString());
		user.setIdType(req.getAttribute("idType").toString());
		user.setMobile(req.getAttribute("mobile").toString());
		user.setName(req.getAttribute("name").toString());
		UserDao userDao = new UserDaoImpl();
		int res = userDao.saveUser(user);
		if(res==0)
			return "success";
		return "fail";
	}
	public String checkuser(HttpServletRequest req){
		//String mobile = (String) req.getAttribute("mobile");
		//UserDao userDao = new UserDaoImpl();
		//int res = userDao.checkUser(mobile);
		//if(res==0)
		//	return "success";
		return "fail";
	}
}
