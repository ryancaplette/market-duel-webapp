package com.marketduel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketduel.dao.UserDao;
import com.marketduel.model.LoginResult;
import com.marketduel.model.User;
import com.marketduel.util.PasswordUtil;

@Service
public class MarketDuelService {
	
	@Autowired
	private UserDao userDao;

	public User getUserbyUsername(String username) {
		return userDao.getUserbyUsername(username);
	}
	
	public LoginResult checkUser(User user) {
		LoginResult result = new LoginResult();
		User userFound = userDao.getUserbyUsername(user.getUsername());
		if(userFound == null) {
			result.setError("Invalid username!");
		} else if(!PasswordUtil.verifyPassword(user.getPassword(), userFound.getPassword())) {
			result.setError("Invalid password!");
		} else {
			result.setUser(userFound);
		}
		
		return result;
	}
	
	public void registerUser(User user) {
		user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
		userDao.registerUser(user);
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
