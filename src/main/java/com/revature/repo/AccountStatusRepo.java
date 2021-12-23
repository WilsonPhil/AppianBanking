package com.revature.repo;

import java.util.List;


import com.revature.models.AccountStatus;

public interface AccountStatusRepo {
public List<AccountStatus>findAll();
	
	AccountStatus findById(int id);

	
	

}
