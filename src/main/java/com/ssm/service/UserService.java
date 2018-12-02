package com.ssm.service;

import com.ssm.model.User;

public interface UserService {
	
	public User getUserById(int userId);
	
	public int insertUser(User user);
	
	public int updateUser(User user);
	
	public int deleteUser(User user);
}
