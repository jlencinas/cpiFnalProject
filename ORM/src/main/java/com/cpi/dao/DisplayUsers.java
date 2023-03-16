package com.cpi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cpi.model.DBConnect;
import com.cpi.model.Users;

public class DisplayUsers {
private static final String dbUsername = "CALANDRIA";
private static final String dbPassword = "calandria";
private static final String server = "training-db.cosujmachgm3.ap-southeast-1.rds.amazonaws.com";

public static List<Users> getUsers(int uid) throws ClassNotFoundException {
	List<Users> userAccounts = new ArrayList<>();
	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
			
	try {	
		DBConnect db = new DBConnect (server, "ORCL", dbUsername, dbPassword);
		conn = db.getConnection();
		st = conn.createStatement();
		rs = st.executeQuery("SELECT * FROM USERS WHERE USER_ID != " + uid + " ORDER BY USER_ID");
						
		while (rs.next()) {
			Users u = new Users();
			u.setUserId(rs.getInt("USER_ID"));
			u.setRoleId(rs.getInt("ROLE_ID"));
			u.setUsername(rs.getString("USERNAME"));
			u.setPassword(rs.getString("PASSWORD"));
			u.setEmail(rs.getString("EMAIL"));
			u.setStatus(rs.getString("STATUS"));
			userAccounts.add(u);
		}
			
		rs.close();
		st.close();
		conn.close();
	}
	catch(SQLException e) {
		e.printStackTrace();
		System.out.println("Di ka men nakonek sa server eh");
	}
		return userAccounts;
	}
	
}
