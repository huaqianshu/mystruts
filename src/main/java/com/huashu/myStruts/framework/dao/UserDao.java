package com.huashu.myStruts.framework.dao;

import com.huashu.myStruts.framework.model.User;

public interface UserDao {
	public int saveUser(User user);
	public int checkUser(String mobile);
}
