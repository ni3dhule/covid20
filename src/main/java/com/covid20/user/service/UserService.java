package com.covid20.user.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid20.user.model.User;
import com.covid20.user.repository.UserRepositoryImpl;
import com.covid20.user.util.Utils;

@Service
public class UserService {

	@Autowired
	UserRepositoryImpl userRepo;
	
	Logger log = LoggerFactory.getLogger(UserService.class);

	public User save(User uUser) {
		
		log.info(" saving user object inside service method !!! ");
		String strPasswordSalt = "";
		strPasswordSalt = UUID.randomUUID().toString();
		uUser.setPasswordSalt(strPasswordSalt);
		uUser.setPasswordHash(Utils.encrypt(uUser.getPassword(), strPasswordSalt));
		System.out.println(System.currentTimeMillis());
		uUser.setCreatedOn(new Timestamp(new java.util.Date().getTime()));
		uUser.setPassword("");
		return userRepo.save(uUser) ;
	}

	public Optional<User> getUser(int iUserId) {
		return userRepo.findById(iUserId); 
	}

	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return (List<User>) userRepo.findAll() ;
	}
	
	public boolean activateUser(int iUserId) {
		// TODO Auto-generated method stub
		return userRepo.activateUser(iUserId);
	}

	public boolean deactivateUser(int iUserId) {
		// TODO Auto-generated method stub
		return userRepo.deactivateUser(iUserId);
	}
	
	public boolean isUserActive(int iUserId) {
		// TODO Auto-generated method stub
		return userRepo.getUserStatus(iUserId) ? true : false;
	}
	
	public String getUserStatus(int iUserId) {
		// TODO Auto-generated method stub
		boolean bStatus = userRepo.getUserStatus(iUserId); 
		log.info("Status : [ "+(bStatus ? "Active" : "Inactive") +" ] for [ userId : "+iUserId +" ] " );
		return bStatus ? "Active" : "Inactive";
	}
	
}
