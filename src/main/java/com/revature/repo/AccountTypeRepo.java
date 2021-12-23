package com.revature.repo;

import java.util.List;

import com.revature.models.AccountType;

public interface AccountTypeRepo {
	
public List<AccountType>findAll();
	
	AccountType findById(int id);
	

}
