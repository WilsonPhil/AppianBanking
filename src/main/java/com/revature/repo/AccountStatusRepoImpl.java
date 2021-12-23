package com.revature.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.AccountStatus;

import com.revature.util.ConnectionUtil;

public class AccountStatusRepoImpl implements AccountStatusRepo{
	
	
	@Override
	public List<AccountStatus> findAll() {
try(Connection con=ConnectionUtil.getConnection()){
			
			String sql="SELECT* FROM AccountStatus;";
			Statement statement=con.createStatement();
			ResultSet result=statement.executeQuery(sql);
			
			List<AccountStatus> list=new ArrayList<>();
			
			while(result.next()) {
				
				AccountStatus status=new AccountStatus();
				status.setStatusId(result.getInt("statusId"));
				status.setStatus(result.getString("status"));
				
				list.add(status);
			}
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public AccountStatus findById(int id) {
try(Connection con=ConnectionUtil.getConnection()){
			
			String sql="SELECT* FROM AccountStatus where statusId=?;";
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setInt(1,id);
			ResultSet result=statement.executeQuery();
			
			AccountStatus status=new AccountStatus();
			
			while(result.next()) {
				status.setStatusId(result.getInt("statusId"));
				status.setStatus(result.getString("status"));
			}
			return status;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	


}
