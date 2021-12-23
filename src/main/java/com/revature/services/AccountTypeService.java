package com.revature.services;



import java.util.List;

import com.revature.models.AccountType;
import com.revature.repo.AccountTypeRepo;
import com.revature.repo.AccountTypeRepoImpl;

public class AccountTypeService {
	
	AccountTypeRepo readRepo=new AccountTypeRepoImpl();
	
	public List<AccountType> findAll(){
		
		List<AccountType> list=readRepo.findAll();
		
		return list;
	}
	
	
	public AccountType findById(int Id) {
		return  readRepo.findById(Id);
	}
}
