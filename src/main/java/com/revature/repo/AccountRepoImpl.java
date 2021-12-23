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
import com.revature.models.AccountType;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class AccountRepoImpl implements AccountRepo{
	private static AccountStatusRepo readstatus=new AccountStatusRepoImpl();
	private static AccountTypeRepo  readtype=new AccountTypeRepoImpl();
	private static UserRepo userRepo=new UserRepoImpl();
	//private static RoleRepo roleRepo= new RoleRepoImpl();
	
	@Override
	public List<Account> findAll() {
		try(Connection con=ConnectionUtil.getConnection()){
			String sql="SELECT * FROM Account;";
			Statement statement=con.createStatement();
			ResultSet result=statement.executeQuery(sql);
			
			List<Account> list=new ArrayList<>();
			
			while(result.next()) {
				Account a= new Account(
				result.getInt("accountId"),
				result.getDouble("balance"),
				readstatus.findById(result.getInt("statusId")),
				readtype.findById(result.getInt("typeId")),
				userRepo.findById(result.getInt("accountUser"))
				);
				list.add(a);
			}
			return list;
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public boolean addAccount(Account account) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			//There is no chance for sql injection with just an integer so this is safe. 
			String sql = "INSERT INTO Account (balance, statusId, typeId, accountUser)"
					+ "	VALUES (?, ?, ?, ?);";

			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;
		
			
			statement.setDouble(++index, account.getBalance());
			statement.setInt(++index, account.getStatus().getStatusId());
			statement.setInt(++index, account.getType().getTypeId());
			statement.setInt(++index, account.getUser().getUserId());
			
			
//			if(account.getStatus()!=null) {
//				statement.setInt(++index, account.getStatus().getStatusId());
//			}else {
//			statement.setString(++index, null);
//			}
//			if(account.getType()!=null) {			
//			statement.setInt(++index, account.getType().getTypeId());
//			}else {
//			statement.setString(++index, null);
//			}if(account.getUser()!=null) {
//				statement.setInt(++index, account.getUser().getUserId());
//			}else {
//			statement.setString(++index,null);
//		}
			
			
			statement.execute();
			return true;


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	@Override
	public boolean addFullAccount(Account account) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			//There is no chance for sql injection with just an integer so this is safe. 
			String sql = "BEGIN;"
					+"INSERT INTO Users(userId,username,password,firstName,lastName,email,roleId)"
					+ "VALUES(?,?,?,?,?,?,?);"
					+"INSERT INTO AccountStatus(statusId,status)"
					+"VALUES(?,?);"
					+ "INSERT INTO AccountType(typeId,type)"
					+ "VALUES(?,?);"
					+ "INSERT INTO Account (balance, statusId, typeId, accountUser)"
					+ "	VALUES (?, ?, ?, ?);"
					+"COMMIT;";

			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			User user=account.getUser();
			AccountStatus as= account.getStatus();
			AccountType at= account.getType();
			
			int index = 0;
			statement.setInt(++index, user.getUserId());
			statement.setString(++index, user.getUsername());
			statement.setString(++index, user.getPassword());
			statement.setString(++index, user.getFirstName());
			statement.setString(++index,user.getLastNmae());
			statement.setString(++index, user.getEmail());
			statement.setInt(++index, user.getRole().getRoleId());
			statement.setInt(++index, as.getStatusId());
			statement.setString(++index, as.getStatus());
			statement.setInt(++index, at.getTypeId());
			statement.setString(++index, at.getType());
			statement.setDouble(++index, account.getBalance());
			statement.setInt(++index, account.getStatus().getStatusId());
			statement.setInt(++index, account.getType().getTypeId());
			statement.setInt(++index, account.getUser().getUserId());
			
			
			statement.execute();
			return true;


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public Account findById(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM Account WHERE accountId = " + id + ";";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			Account a = null;

			while (result.next()) {
				a = new Account(
						result.getInt("accountId"),
						result.getDouble("balance"),
						readstatus.findById(result.getInt("statusId")),
						readtype.findById(result.getInt("typeId")),
						userRepo.findById(result.getInt("accountUser"))
						);
				return a;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Account account) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			// There is no chance for sql injection with just an integer so this is safe.
			String sql = "UPDATE Account "
					+ "SET balance = ?, "
					+ "statusId = ?, "
					+ "typeId = ?, "
					+ "accountUser = ? "
					+ "WHERE accountId = ?; ";
		

			PreparedStatement statement = conn.prepareStatement(sql);

			int index = 0;
			statement.setDouble(++index, account.getBalance());
//			statement.setInt(++index, account.getStatus().getStatusId());
//			statement.setInt(++index, account.getType().getTypeId());
//			statement.setInt(++index, account.getUser().getUserId());
			
			if (account.getStatus() != null) {
				statement.setInt(++index, account.getStatus().getStatusId());
			} else {
				statement.setString(++index, null);
			}
			if (account.getType() != null) {
				statement.setInt(++index, account.getType().getTypeId());
			} else {
				statement.setString(++index, null);
			}
			if (account.getUser() != null) {
				statement.setInt(++index, account.getUser().getUserId());
			} else {
				statement.setString(++index, null);
			}

			statement.setInt(++index, account.getAccountId());


			statement.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteAccount(int id) {
		try(Connection conn=ConnectionUtil.getConnection()){
			String sql="DELETE FROM Account WHERE accountId="+id+";";
			
			Statement statement=conn.createStatement();
			statement.execute(sql);
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public List<Account> findAccountByStatus(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM Account WHERE statusId = " + id + ";";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			List<Account> list=new ArrayList<>();
			
			while(result.next()) {
				Account a= new Account(
				result.getInt("accountId"),
				result.getDouble("balance"),
				readstatus.findById(result.getInt("statusId")),
				readtype.findById(result.getInt("typeId")),
				userRepo.findById(result.getInt("accountUser"))
				);
				list.add(a);
			}
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean withdraw(Account account, double withdraw) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			// There is no chance for sql injection with just an integer so this is safe.
			String sql = "UPDATE Account "
					+ "SET balance = ?, "
					+ "statusId = ?, "
					+ "typeId = ?, "
					+ "accountUser = ? "
					+ "WHERE accountId = ?; ";
		

			PreparedStatement statement = conn.prepareStatement(sql);

			int index = 0;
			statement.setDouble(++index, account.getBalance());
//			statement.setInt(++index, account.getStatus().getStatusId());
//			statement.setInt(++index, account.getType().getTypeId());
//			statement.setInt(++index, account.getUser().getUserId());
			
			if (account.getStatus() != null) {
				statement.setInt(++index, account.getStatus().getStatusId());
			} else {
				statement.setString(++index, null);
			}
			if (account.getType() != null) {
				statement.setInt(++index, account.getType().getTypeId());
			} else {
				statement.setString(++index, null);
			}
			if (account.getUser() != null) {
				statement.setInt(++index, account.getUser().getUserId());
			} else {
				statement.setString(++index, null);
			}

			statement.setInt(++index, account.getAccountId());


			statement.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deposit(Account a, double deposit) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean transfer(Account account, Account acTwo) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			// There is no chance for sql injection with just an integer so this is safe.
			String sql = "BEGIN"
					+"UPDATE Account "
					+ "SET balance = ?, "
					+ "statusId = ?, "
					+ "typeId = ?, "
					+ "accountUser = ? "
					+ "WHERE accountId = ?; "
					
					+"UPDATE Account "
					+ "SET balance = ?, "
					+ "statusId = ?, "
					+ "typeId = ?, "
					+ "accountUser = ? "
					+ "WHERE accountId = ?; "
					+ "COMMIT;";
					
					
		

			PreparedStatement statement = conn.prepareStatement(sql);

			int index = 0;
			statement.setDouble(++index, account.getBalance());
			statement.setInt(++index, account.getStatus().getStatusId());
			statement.setInt(++index, account.getType().getTypeId());
			statement.setInt(++index, account.getUser().getUserId());
			statement.setInt(++index, account.getAccountId());
			
			statement.setDouble(++index, acTwo.getBalance());
			statement.setInt(++index, acTwo.getStatus().getStatusId());
			statement.setInt(++index, acTwo.getType().getTypeId());
			statement.setInt(++index, acTwo.getUser().getUserId());
			statement.setInt(++index, acTwo.getAccountId());


			statement.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
