package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	public static Connection getConnection() throws SQLException {
		
		
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url="jdbc:postgresql://appian-210419.ck4cz0egk9pc.us-east-2.rds.amazonaws.com:5432/";
		
		String username="postgres";
		String password="password";
		
		return DriverManager.getConnection(url,username,password);
		
		
		
		}
	
	

	}
