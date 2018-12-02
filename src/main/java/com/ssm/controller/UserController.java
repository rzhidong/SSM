package com.ssm.controller;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.model.User;
import com.ssm.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private static Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	// /user/test?id=1
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(HttpServletRequest request, Model model) {
		int userId = Integer.parseInt(request.getParameter("id"));
		System.out.println("userId: " + userId);

		User user = userService.getUserById(userId);
		if (user == null) {
			user = new User();
			user.setAge(22);
			user.setPassword("222");
			user.setUserName("123456");
		}

		log.info(user.toString());

		model.addAttribute("user", user);

		return "index";
	}

	@RequestMapping("/insert")
	@ResponseBody
	public String insert() {

		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setUserName(String.valueOf(new Date()));
			user.setPassword(UUID.randomUUID().toString().replace("-", ""));
			Random random = new Random();
			user.setAge(random.nextInt(i + 10));
			log.info(user.toString());
			userService.insertUser(user);
		}

		return "insert user success " + new Date().toString();
	}
	
	@RequestMapping("update")
	@ResponseBody
	public String update(int id) {
		User user = userService.getUserById(id);
		
		if (user != null) {
			log.info(user.toString());
			user.setUserName(String.valueOf(new Date()));
			userService.updateUser(user);
			return "update user "+id+" success " + new Date().toString();
		}else if (user == null) {
			return "not such user " + id;
		} 
		
		return "update user "+id+" fail " + new Date().toString();
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public String delete(int id) {
		User user = userService.getUserById(id);
		
		if (user != null) {
			log.info(user.toString());
			userService.deleteUser(user);
			return "delete user "+id+" success " + new Date().toString();
		}else if (user == null) {
			return "not such user " + id;
		} 
		
		return "delete user "+id+" fail " + new Date().toString();
	}

}
