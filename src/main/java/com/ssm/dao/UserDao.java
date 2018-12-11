package com.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.ssm.model.User;

public interface UserDao {
	int deleteByPrimaryKey(Integer id);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);
	
	@Select({"SELECT id,user_name userName,password,age FROM user_t"})
	List<User> getAllUsers();
}