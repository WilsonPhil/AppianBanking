package com.revature.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.AccountStatus;
import com.revature.models.AccountType;
import com.revature.util.ConnectionUtil;

public class AccountTypeRepoImpl implements AccountTypeRepo{

	@Override
	public List findAll() {
try(Connection con=ConnectionUtil.getConnection()){
			
			String sql="SELECT* FROM AccountType;";
			Statement statement=con.createStatement();
			ResultSet result=statement.executeQuery(sql);
			
			List<AccountType> list=new ArrayList<>();
			
			while(result.next()) {
				
				AccountType type=new AccountType();
				type.setTypeId(result.getInt("typeId"));
				type.setType(result.getString("type"));
				
				list.add(type);
			}
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public AccountType findById(int id) {
try(Connection con=ConnectionUtil.getConnection()){
			
			String sql="SELECT* FROM AccountType where typeId=?;";
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setInt(1,id);
			ResultSet result=statement.executeQuery();
			
			AccountType type=new AccountType();
			
			while(result.next()) {
				type.setTypeId(result.getInt("typeId"));
				type.setType(result.getString("type"));
			}
			return type;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
