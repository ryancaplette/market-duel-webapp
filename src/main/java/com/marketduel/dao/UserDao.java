package com.marketduel.dao;

import com.marketduel.model.User;

public interface UserDao {

	User getUserbyUsername(String username);
	
	void registerUser(User user);
}
