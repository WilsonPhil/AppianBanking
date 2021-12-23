package com.revature.repo;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;

public interface UserRepo {
	
	public List<User>findAll();
	User findById(int id);
	boolean update(User user);
	User deleteById(int id);
	User findByUsername(String username);
	boolean addUser(User user);

}
