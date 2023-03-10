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
	
	public Users getUser (String username, String password){
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
			 
			 if (rs.next()){
			 	u.setUserId(rs.getInt("USER_ID"));
			 	u.setRoleId(rs.getInt("ROLE_ID"));
				u.setUsername(rs.getString("USERNAME"));
				u.setPassword(rs.getString("PASSWORD"));
				u.setEmail(rs.getString("EMAIL"));
			 }
			 
		}  
		
		catch (SQLException se) {
			System.out.println(se);
		} 
		
		finally {
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
			} 
			
			catch (SQLException se) { System.out.println(se); }
		}
		return u;
	}
	
	public String updateUser(String email, String pass) {
		String u = "";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			
			 DBConnect db = new DBConnect (server, "ORCL", dbUsername, dbPassword);
			 conn = db.getConnection();
			 System.out.println("Updating to server");
			 
			 st = conn.createStatement();
			 
			 if(email != "" && pass != "" ) {
				 rs = st.executeQuery("SELECT * FROM users WHERE EMAIL = '" + email + "' AND PASSWORD = '" + pass + "'");
			 }
			 
			 else if (email != "" && pass == "") {
				 rs = st.executeQuery("SELECT * FROM users WHERE EMAIL = '" + email + "'");
			 }
			 
			 
			 else if(email == "" && pass != "") {
				 rs = st.executeQuery("SELECT * FROM users WHERE EMAIL = '" + email + "'");
			 }
			 
			 if (rs.next()){
			 	
				 try {
					 
					 
				 }
				 catch(Exception sq ) {
					 System.out.println(sq);
				 }
				 
			 }
		}  
		
		catch (SQLException se) {
			System.out.println(se);
		} 
		
		finally {
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
			} 
			
			catch (SQLException se) { System.out.println(se); }
		}
		
		
		return u;
		
	}
	
	
	public String createUser(Users u) {
		String msg = "";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		int uid;
		
		try {
			
			 DBConnect db = new DBConnect (server, "ORCL", dbUsername, dbPassword);
			 conn = db.getConnection();
			 st = conn.createStatement();
			 rs = st.executeQuery("SELECT MAX(USER_ID) AS MUS FROM USERS");
			 
			 if (rs.next()){
					uid = rs.getInt("MUS") + 1;
				}
			 else {
				uid = 1;
			 }
			 
			 String addQuery = "INSERT INTO USERS VALUES (" + uid + ", " + u.getRoleId() + ", '"+u.getUsername()+"', '"+u.getPassword()+"', '" + u.getEmail() + "')";
			 
			 try {
					st.executeUpdate(addQuery);
					
					msg = "New Account Added";
					
				}
				
			catch (Exception ex) {
				/*
				 * dispatcher = request.getRequestDispatcher("pages/error.jsp");
				 * request.setAttribute("message", "Something went wrong bro");
				 * request.setAttribute("page", "'/RegLog/pages/registration.jsp'");
				 * dispatcher.forward(request, response);
				 */
				msg = "Account Failed to be added";
			}
			 
		}
		catch (Exception e) {
			System.out.println("MAY EXCEPTION");
			msg = "Di ata maka konek";
		}
		
		return msg;
	}
	
	
	public String forgotUser(String username, String email) {
		String msg = "";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String np = "";
		try {
			
			DBConnect db = new DBConnect (server, "ORCL", dbUsername, dbPassword);
			conn = db.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM USERS WHERE USERNAME = '" + username + "' AND EMAIL = '" + email + "'");
			 
			if (rs.next()){
							
			}
			 else {
				
			 }
			 
			 String forgotQuery = "";
			 try {
					st.executeUpdate(forgotQuery);
					
					msg = "New Account Added";
					
				}
				
			catch (Exception ex) {
				/*
				 * dispatcher = request.getRequestDispatcher("pages/error.jsp");
				 * request.setAttribute("message", "Something went wrong bro");
				 * request.setAttribute("page", "'/RegLog/pages/registration.jsp'");
				 * dispatcher.forward(request, response);
				 */
				msg = "Password failed to update";
			}
			 
		}
		catch (Exception e) {
			System.out.println("MAY EXCEPTION");
			msg = "Di ata maka konek";
		}
		
		return msg;
	}
}
