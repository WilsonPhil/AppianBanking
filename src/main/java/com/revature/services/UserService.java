package com.revature.services;

import java.util.List;

import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.repo.UserRepo;
import com.revature.repo.UserRepoImpl;



public class UserService {
	
	 UserRepo ur=new UserRepoImpl();
		
		public List<User> findAll(){
			
			List<User> list=ur.findAll();
			
			return list;
		}
		
		public User findById(int id) {
			return ur.findById(id);
			
		}
		
		public boolean addUser(User user) {
			return ur.addUser(user);
		}
		
		
		public User findByUsername(String username) {
			return ur.findByUsername(username);
			
		}
		public boolean updateUser(User u) {
			return ur.update(u);
		}

		public boolean loginVerification(UserDTO u) {
			UserRepoImpl userRepo=new UserRepoImpl();
			User userRequest=userRepo.findByUsername(u.username);
			System.out.println(u);
			System.out.println(userRequest);
			
			if((userRequest.getPassword()!=null )&&(u.password.equals(userRequest.getPassword()))){
				return true;
			}
			return false;
		}
		
		

}
