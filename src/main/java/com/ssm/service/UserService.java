package com.ssm.service;

import java.util.List;

import com.ssm.model.User;

public interface UserService {
	
	public User getUserById(int userId);
	
	public int insertUser(User user);
	
	public int updateUser(User user);
	
	public int deleteUser(User user);
	
	public List<User> getAllUsers();
}
