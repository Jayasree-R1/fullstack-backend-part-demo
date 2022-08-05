package com.example.demo0522.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;

import com.example.demo0522.dao.UserRepository;
import com.example.demo0522.entity.User;
import com.example.demo0522.model.UserBean;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;


@Service 
//@Component
//@Repository
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	//@Autowired
	//RestTemplate restTemplate;
	
	public UserBean getDetails(Long userId) {	
		
 		Optional<User> userOptional = userRepository.findById(userId);
		User user=userOptional.isPresent()?userOptional.get():null;
		UserBean userBean = new UserBean();
		if(user!=null) {
			
			userBean.setFirstName(user.getFirstName());
			userBean.setLastName(user.getLastName());
			userBean.setQualification(user.getQualification());
			userBean.setuserId(user.getUserId());
			userBean.setAddress(user.getAddress());
			userBean.setStatus(user.getStatus());
			return userBean;
		}
		//UserBean userBean = getOtherServiceUserDetails(userId);
		return userBean;
		
	}
	/*@HystrixCommand(fallbackMethod = "fallbackGetOtherServiceUserDetails", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1")
			})
	public  UserBean getOtherServiceUserDetails(Long userId){
		String url="http://localhost:8080/demo/userDetails/"+userId;
		
		ResponseEntity<UserBean> result=restTemplate.exchange(url, HttpMethod.GET, null, UserBean.class);
		
		UserBean userBean=result.getBody();
		return userBean;
		
	}

	public  UserBean fallbackGetOtherServiceUserDetails(){
		UserBean userBean=new UserBean();
		userBean.setStatus("400");
		return userBean;
	}*/

	public UserBean saveUser(UserBean userBean) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setFirstName(userBean.getFirstName());
		user.setLastName(userBean.getLastName());
		user.setQualification(userBean.getQualification());
		user.setAddress(userBean.getAddress());
		user.setStatus(userBean.getStatus());
		
		userRepository.save(user);
		userBean.setuserId(user.getUserId());
		return userBean;  
	}
	
	public UserBean updateUser(UserBean userBean) {
		// TODO Auto-generated method stub
		
		Optional<User> userOptional = userRepository.findById(userBean.getuserId());
		User user=userOptional.isPresent()?userOptional.get():null;
		if(user!=null) {
			user.setFirstName(userBean.getFirstName());
			user.setLastName(userBean.getLastName());
			user.setQualification(userBean.getQualification());
			user.setAddress(userBean.getAddress());
			user.setStatus(userBean.getStatus());
			userRepository.save(user);
		}
		return userBean;
	}

	public List<UserBean> getDetailsList() {
		// TODO Auto-generated method stub
		List<User> userList = (List<User>) userRepository.findAll();	
		List<UserBean> userBeanList = new ArrayList();
		for(User user:userList) {
			UserBean userBean = new UserBean();
			userBean.setFirstName(user.getFirstName());
			userBean.setLastName(user.getLastName());
			userBean.setQualification(user.getQualification());
			userBean.setuserId(user.getUserId());
			userBean.setAddress(user.getAddress());
			userBean.setStatus(user.getStatus());
			userBeanList.add(userBean);
		}
		return userBeanList;
	}

	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		userRepository.deleteById(userId);
		
	}

	 

}
