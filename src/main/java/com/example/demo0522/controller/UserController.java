package com.example.demo0522.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo0522.model.UserBean;
import com.example.demo0522.service.PersonService;
import com.example.demo0522.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	@Autowired 
	UserService userService;
	
	@Autowired  
	PersonService personService;
	
	@GetMapping("/application")
	public String getApplication() {
		return "demo Application";
	}
    
	@GetMapping("/getDetails/{userId}")
	public UserBean getDetails(@PathVariable Long userId) {		
		return userService.getDetails(userId);
	}
	@GetMapping("/getDetailsList")
	public List<UserBean> getDetailsList() {
		
		//Below is static
		/*UserBean u1 = new UserBean();
		List<UserBean> list = new ArrayList<>();
		u1.setFirstName("Joe");
		u1.setLastName("Jonas");
		u1.setQualification("CA");
		UserBean u2 = new UserBean();
		u2.setFirstName("Camila");
		u2.setLastName("Vasq");
		u2.setQualification("CMA");
		list.add(u1);
		list.add(u2);
		return list;*/ 

		return userService.getDetailsList(); 
		
	}
	@GetMapping("/application/{userId}")
	public UserBean getApplication(@PathVariable Long userId)  {
		return userService.getDetails(userId);
	}
	
	@GetMapping("/application/{userId}/{name}")
	public String getApplication(@PathVariable Long userId,@PathVariable String name) {
		return "demo Application "+userId+" "+name;
	}
	@PostMapping("/saveUser")
	public UserBean saveUser(@RequestBody UserBean userBean) {
		return userService.saveUser(userBean);
	}
	@PutMapping("/updateUser")
	public UserBean updateUser(@RequestBody UserBean userBean) {
		return userService.updateUser(userBean);
	}
	@DeleteMapping("/deletUser/{userId}")
	public UserBean deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
		UserBean u = new UserBean();
		u.setStatus("Deleted Successfully");
		return  u;
	}
	
}
