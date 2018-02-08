package com.huashu.myStruts.framework.service.impl;

import javax.servlet.http.HttpServletRequest;

import com.huashu.myStruts.framework.annotation.MyAgent;
import com.huashu.myStruts.framework.annotation.Service;
import com.huashu.myStruts.framework.dao.UserDao;
import com.huashu.myStruts.framework.model.User;
import com.huashu.myStruts.framework.service.UserService;
@Service("serServiceImpl")
public class UserServiceImpl implements UserService {
	@MyAgent("userDao")
	private UserDao userDao;
	
	@Override
	public int saveUser(HttpServletRequest req) {
		User user = new User();
		user.setIdcard(req.getAttribute("Idcard").toString());
		user.setIdType(req.getAttribute("idType").toString());
		user.setMobile(req.getAttribute("mobile").toString());
		user.setName(req.getAttribute("name").toString());
		return userDao.saveUser(user);
	}

	@Override
	public int checkUser(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return 0;
	}

}
