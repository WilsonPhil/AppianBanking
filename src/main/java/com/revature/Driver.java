package com.revature;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.AccountStatus;
import com.revature.models.AccountType;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repo.UserRepoImpl;
import com.revature.services.AccountService;
import com.revature.services.AccountStatusService;
import com.revature.services.AccountTypeService;
import com.revature.services.RoleService;
import com.revature.services.UserService;

public class Driver {
	private static RoleService RService=new RoleService();
	private static AccountStatusService ASService=new AccountStatusService();
	private static AccountTypeService TService=new AccountTypeService();
	private static UserService UService=new UserService();
	private static AccountService AService=new AccountService();

	public static void main(String[] args) {
		
		List<Role> list=RService.findAll();
		
		for(Role r:list) {
			System.out.println(r);
		}
		
		System.out.println("==================================================");
		
		List<AccountStatus> statuslist=ASService.findAll();
		
		for(AccountStatus s:statuslist) {
			System.out.println(s);
		}
		
		System.out.println("=====================================================");
		
		List<AccountType> typelist=TService.findAll();
		
		for(AccountType T:typelist) {
			System.out.println(T);
		}
		
		System.out.println("=========================================================");
		
		List<User> userlist=UService.findAll();
		
		for(User U:userlist) {
			System.out.println(U);
		}
		System.out.println("===========================================================");
		
		List<Account> accountlist=AService.findAll();
		
		for(Account A:accountlist) {
			System.out.println(A);
		}
		
		UserRepoImpl uR=new UserRepoImpl();
		System.out.println(uR.findByUsername("philipwilson"));
		
		
		
	}

}
