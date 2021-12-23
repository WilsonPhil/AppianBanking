package com.revature.models;

import java.io.Serializable;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private int userId;//primary key
	private String username;//not null,unique
	private String password;//not null
	private String firstName;//not null
	private String lastNmae;//not null
	private String email;//not null
	private Role role;
	
	
	public User() {
		super();
	}


	public User(int userId, String username, String password, String firstName, String lastName, String email) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastNmae = lastName;
		this.email = email;
	}
	
	
	public User(int userId, String username, String password, String firstName, String lastName, String email,Role role) {
		this(userId,username,password,firstName,lastName,email);
		this.role=role;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastNmae() {
		return lastNmae;
	}


	public void setLastNmae(String lastNmae) {
		this.lastNmae = lastNmae;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastNmae == null) ? 0 : lastNmae.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + userId;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastNmae == null) {
			if (other.lastNmae != null)
				return false;
		} else if (!lastNmae.equals(other.lastNmae))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId != other.userId)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastNmae=" + lastNmae + ", email=" + email + ", role=" + role + "]";
	}
	
	
	

	
	
	
}
