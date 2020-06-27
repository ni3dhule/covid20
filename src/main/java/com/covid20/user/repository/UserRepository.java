package com.covid20.user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.covid20.user.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>  {
	
	public boolean getUserStatus(int iUserId);
	public boolean activateUser(int iUserId);
	public boolean deactivateUser(int iUserId);

}
