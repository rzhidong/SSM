package com.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.ssm.dao.UserDao;
import com.ssm.model.User;
import com.ssm.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations= {"classpath:spring-mybatis.xml"})
public class TestMyBatis {
	private static Logger logger = LoggerFactory.getLogger(TestMyBatis.class);
    
	@Resource  
    private UserService userService = null;
	
	@Autowired
	private UserDao userDao;
	
	@Test  
    public void test1() {  
        User user = userService.getUserById(3);  
        System.out.println(user.getUserName());  
        logger.info("值："+user.getUserName());  
        logger.info(JSON.toJSONString(user));  
    }  
	
	@Test  
    public void test2() {  
        User user = userDao.selectByPrimaryKey(1);
        System.out.println(user.getUserName());  
        logger.info("值："+user.getUserName());  
        logger.info(JSON.toJSONString(user));  
    }  
}
