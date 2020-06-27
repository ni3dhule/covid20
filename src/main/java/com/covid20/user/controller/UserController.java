package com.covid20.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covid20.user.model.Person;
import com.covid20.user.model.User;
import com.covid20.user.service.UserService;
import com.covid20.user.util.Utils;

@RestController
@RequestMapping("/api/user/*")
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	public UserController() {
		log.warn("inside the constructor of user controller !!!");
	}
	
	@GetMapping("show")
	public HashMap<String, Object> showUser() {
		log.warn("inside the user show method...");
		HashMap<String, Object> userMap = new HashMap<>();
		userMap.put("userId", 1010);
		userMap.put("username", "Sam Willis");
		userMap.put("password", "password@123");
		userMap.put("userGender", "Male");
		userMap.put("userDob", new Date());
		userMap.put("userSalary", "2,50,000");
		
		String secreteKey = UUID.randomUUID().toString();
		userMap.put("secreteKey", secreteKey);
		
		String encPass = Utils.encrypt("password@123", secreteKey);
		
		userMap.put("encPass", encPass);
		String decPass = Utils.decrypt(encPass, secreteKey);
		
		userMap.put("decPass", decPass);
		
		log.warn("user object -> [ "+userMap+"]");
		
		return userMap;
	}
	
	@GetMapping("getUser/{userId}")
	public Optional<User> getUser(@PathVariable("userId") int iUserId) {
		return userService.getUser(iUserId);
	}
	
	@GetMapping("getUsers")
	public List<User> getUsers() {
		return userService.getUsers();
	}
	
	@PostMapping("save")
	public User saveUser(@RequestBody User user) {
		System.out.println("User :=> "+user);
		return userService.save(user);
	}
	
	@PostMapping(value = "delete")
	public User deleteUser() {
		
		log.warn("inside the delete method !!!");
		User u = new User();
		
		return u;
	}
	
	@PostMapping(value = "person")
	public Person savePerson(@RequestBody Person person) {
		log.warn("User :=> "+person);
		return person;
	}
	
	/*@GetMapping(value = "getUserStatus/{userId}")
	public boolean getUserStatus(@PathVariable("userId") int iUserId) {
		log.warn("User :=> "+iUserId);
		return userService.getUserStatus(iUserId);
	}*/
	
	@GetMapping(value = "getUserStatus/{userId}")
	public String getUserStatus(@PathVariable("userId") int iUserId) {
		log.warn("User :=> "+iUserId);
		return userService.getUserStatus(iUserId);
	}
	
	@PostMapping(value = "activate/{userId}")
	public boolean activateUser(@PathVariable("userId") int iUserId) {
		log.warn("User :=> "+iUserId);
		return userService.activateUser(iUserId);
	}
	
	@PostMapping(value = "deactivate/{userId}")
	public boolean deactivateUser(@PathVariable("userId") int iUserId) {
		log.warn("User :=> "+iUserId);
		return userService.deactivateUser(iUserId);
	}
	
}
