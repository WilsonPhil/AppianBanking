package com.revature.repo;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;

public interface AccountRepo {
	
	public List<Account>findAll();
	Account findById(int id);
	public boolean addAccount(Account a);
	public boolean addFullAccount(Account a);
	public List<Account> findAccountByStatus(int id);
	boolean update(Account a);
	boolean deleteAccount(int id);
	boolean withdraw(Account a, double withdraw);
	boolean deposit(Account a, double deposit);
	boolean transfer(Account acOne,Account acTwo);

}
