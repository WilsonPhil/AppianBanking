package com.revature.repo;

import java.util.List;

import com.revature.models.Role;

public interface RoleRepo {
	
	public List<Role> findAll();
	
	public Role findRoleById(int id);

}
