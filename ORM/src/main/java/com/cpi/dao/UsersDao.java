package com.cpi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cpi.model.DBConnect;
import com.cpi.model.Users;

/**
 * @author Jan Christian Buan
 *
 */
public class UsersDao {
	
	private static final String dbUsername = "CALANDRIA";
	private static final String dbPassword = "calandria";
	private static final String server = "training-db.cosujmachgm3.ap-southeast-1.rds.amazonaws.com";
	
	public Users getUser(String username, String password)
	{
		Users u = new Users();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			
			 DBConnect db = new DBConnect (server, "ORCL", dbUsername, dbPassword);
			 conn = db.getConnection();
			 System.out.println("Connected to server");
			 
			 st = conn.createStatement();
			 rs = st.executeQuery("SELECT * FROM users WHERE USERNAME = '" + username + "' AND PASSWORD = '" + password + "'");
			 
			 if (rs.next())
			 {
			 	u.setUserId(rs.getInt("USER_ID"));
			 	u.setRoleId(rs.getInt("ROLE_ID"));
				u.setUsername(rs.getString("USERNAME"));
				u.setPassword(rs.getString("PASSWORD"));
				u.setEmail(rs.getString("EMAIL"));
			 }
			 
		}  catch (SQLException se) {
			System.out.println(se);
		} finally {
			
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				System.out.println(se);
			}
		}
		return u;
	}
}
