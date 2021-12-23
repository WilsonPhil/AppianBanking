package com.revature.services;



import java.util.List;

import com.revature.models.AccountStatus;
import com.revature.repo.AccountStatusRepo;
import com.revature.repo.AccountStatusRepoImpl;




public class AccountStatusService {
	
	 AccountStatusRepo readRepo=new AccountStatusRepoImpl();
		
		public List<AccountStatus> findAll(){
			
			List<AccountStatus> list=readRepo.findAll();
			
			return list;
		}
		
		
		public AccountStatus findById(int Id) {
			return  readRepo.findById(Id);
		}

}
