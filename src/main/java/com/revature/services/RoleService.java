package com.revature.services;

import java.util.List;

import com.revature.models.Role;
import com.revature.repo.RoleRepo;
import com.revature.repo.RoleRepoImpl;

public class RoleService {
	 RoleRepo rolerepo=new RoleRepoImpl();
	
	public List<Role> findAll(){
		
		List<Role> list=rolerepo.findAll();
		
		return list;
	}
	
	
	public Role findRoleById(int Id) {
		return rolerepo.findRoleById(Id);
	}

}
