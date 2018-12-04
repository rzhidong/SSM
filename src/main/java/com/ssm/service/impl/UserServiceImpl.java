package com.ssm.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.dao.UserDao;
import com.ssm.model.User;
import com.ssm.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	//@Resource
	@Autowired
	private UserDao userDao;
	
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return this.userDao.selectByPrimaryKey(userId);
	}

	public int insertUser(User user) {
		// TODO Auto-generated method stub
		return userDao.insert(user);
	}

	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateByPrimaryKey(user);
	}

	public int deleteUser(User user) {
		// TODO Auto-generated method stub
		return userDao.deleteByPrimaryKey(user.getId());
	}

}
