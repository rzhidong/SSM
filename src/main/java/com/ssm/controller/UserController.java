package com.ssm.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.model.User;
import com.ssm.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private static Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	// /user/test?id=1
	@RequestMapping(value = "/get", method = RequestMethod.GET)
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
	
	@RequestMapping("/getAll")
	public String getAll(@RequestParam(required=true,defaultValue="1") Integer page,HttpServletRequest request,Model model) {
		//page默认值是1，pageSize默认是10，我写的是2 意思是从第1页开始，每页显示2条记录。
	      PageHelper.startPage(page, 10);
	      List<User> userList = userService.getAllUsers();
	      System.out.println(userList);
	      PageInfo<User> pageInfo=new PageInfo<User>(userList);
	      model.addAttribute("pageInfo", pageInfo);
	      model.addAttribute("userList",userList);
		
		
		return "list";
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
		}else {
			return "not such user " + id;
		} 
		
	}
	
	// /user/delete?id=1
	@RequestMapping("delete")
	@ResponseBody
	public String delete(int id) {
		User user = userService.getUserById(id);
		
		if (user != null) {
			log.info(user.toString());
			userService.deleteUser(user);
			return "delete user "+id+" success " + new Date().toString();
		}else{
			return "not such user " + id;
		} 
		
	}
	
	// /user/{id}
	@RequestMapping("/json/{id}")
	public ResponseEntity<User> getUserJson(@PathVariable String id,Map<String, Object> model){
		User user = userService.getUserById(Integer.parseInt(id));
		
		if (user !=null) {
			log.info(user.toString());
			
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}else {
			return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
		}
		
	}
	
	//文件上传、
    @RequestMapping(value="/upload")
    public String showUploadPage(){
        return "file";
    }
    
    @RequestMapping(value="/doUpload",method=RequestMethod.POST)
    public String doUploadFile(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws Exception{
    	if (file.isEmpty()) {
    		 return "file";
		}
    	log.info("Process file:{}",file.getOriginalFilename());
    	File Folder = new File(System.getProperty("user.dir")+"/upload/",System.currentTimeMillis()+file.getOriginalFilename());
    	System.out.println(System.getProperty("user.dir")+"/upload/");
    	System.out.println(Folder.getAbsolutePath());
    	System.out.println(request.getServletContext().getRealPath(""));
    	FileUtils.copyInputStreamToFile(file.getInputStream(),Folder);
    	return "file";
    }
	
}
