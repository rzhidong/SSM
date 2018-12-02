package com.test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {
	
	private ApplicationContext context;
	
	{
		context = new ClassPathXmlApplicationContext("spring-mybatis.xml");
	}
	
	@Test
	public void testDataSource() {
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		try {
			System.out.println(dataSource.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
