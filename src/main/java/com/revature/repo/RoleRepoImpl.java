package com.revature.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Role;
import com.revature.util.ConnectionUtil;

public class RoleRepoImpl implements RoleRepo{

	@Override
	public List<Role> findAll() {
		try(Connection con=ConnectionUtil.getConnection()){
			
			String sql="SELECT* FROM Roles;";
			Statement statement=con.createStatement();
			ResultSet result=statement.executeQuery(sql);
			
			List<Role> list=new ArrayList<>();
			
			while(result.next()) {
				Role role=new Role();
				role.setRoleId(result.getInt("roleId"));
				role.setRole(result.getString("roles"));
				
				list.add(role);
			}
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Role findRoleById(int Id) {
try(Connection con=ConnectionUtil.getConnection()){
			
			String sql="SELECT* FROM Roles where roleId=?;";
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setInt(1,Id);
			ResultSet result=statement.executeQuery();
			
			Role role=new Role();
			
			while(result.next()) {
				role.setRoleId(result.getInt("roleId"));
				role.setRole(result.getString("roles"));
			}
			return role;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
