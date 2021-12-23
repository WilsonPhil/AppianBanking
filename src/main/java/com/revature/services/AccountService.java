package com.revature.services;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.repo.AccountRepo;
import com.revature.repo.AccountRepoImpl;

public class AccountService {
	
	 AccountRepo ac=new AccountRepoImpl();
		
		public List<Account> findAll(){
			
			List<Account> list=ac.findAll();
			
			return list;
		}

		public boolean addAccount(Account a) {
			return ac.addAccount(a);
			
		}
		public boolean addFullAccount(Account a) {
			return ac.addFullAccount(a);
		}
		public Account findById(int id) {
			return ac.findById(id);
		}
		
		public List<Account> findByStatus(int id) {
			
			List<Account> list= ac.findAccountByStatus(id);
			return list;
		}
		
		public boolean updateAccount(Account a) {
			return ac.update(a);
		}
		
		public boolean updatePartialAccount(Account a) {
			if (a.getAccountId() == 0) {
				return false;
			}

			Account aData = findById(a.getAccountId());

			if (a.getBalance() == 0) {
				a.setBalance(aData.getBalance());
			}
			if (a.getStatus() == null) {
				a.setStatus(aData.getStatus());
			}
			if (a.getType() == null) {
				a.setType(aData.getType());
			}
			if (a.getUser() == null) {
				a.setUser(aData.getUser());
			}
			
			return ac.update(a);

		}
		
		public boolean withdraw(Account a) {
			if (a.getAccountId() == 0) {
				return false;
			}

			Account aData = findById(a.getAccountId());

			if(aData.getBalance()-a.getBalance()>0) {
				a.setBalance(aData.getBalance()-a.getBalance());
			}
			if (a.getStatus() == null) {
				a.setStatus(aData.getStatus());
			}
			if (a.getType() == null) {
				a.setType(aData.getType());
			}
			if (a.getUser() == null) {
				a.setUser(aData.getUser());
			}
			
			return ac.update(a);

		}
		
		public boolean deposit(Account a) {
			if (a.getAccountId() == 0) {
				return false;
			}

			Account aData = findById(a.getAccountId());

			if(a.getBalance()>=0) {
				a.setBalance(aData.getBalance()+a.getBalance());
			}
			if (a.getStatus() == null) {
				a.setStatus(aData.getStatus());
			}
			if (a.getType() == null) {
				a.setType(aData.getType());
			}
			if (a.getUser() == null) {
				a.setUser(aData.getUser());
			}
			
			return ac.update(a);

		}
		
		public boolean transfer(Account a,Account b) {
			
			
			
			return ac.transfer(a, b);
		}
		
	

		public boolean deleteAccount(int id) {
			return ac.deleteAccount(id);
		}
		
		
		
		
		
	
	

}
