package com.cpi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cpi.model.DBConnect;
import com.cpi.model.Users;


import java.util.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.activation.*;

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
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM users WHERE USERNAME = '" + username + "' AND PASSWORD = '" + password + "' AND STATUS != 'DISABLED'");
			 
			if (rs.next()){
				u.setUserId(rs.getInt("USER_ID"));
				u.setRoleId(rs.getInt("ROLE_ID"));
				u.setUsername(rs.getString("USERNAME"));
				u.setPassword(rs.getString("PASSWORD"));
				u.setEmail(rs.getString("EMAIL"));
				u.setStatus(rs.getString("STATUS"));
			}
			
			else {
				u.setStatus("DISABLED");
			}
			
			st.close();
			conn.close();
					 
		}  
				
		catch (SQLException se) {
			System.out.println(se);
		} 
				
		return u;
	}

	//password randomizer
	static String randPwd(int x) {
		String pwd = "";
		String alpnum = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
			
		for(int y = 0; y < x; y++) {
			int index = (int)(alpnum.length()* Math.random());
			pwd += alpnum.charAt(index);
		}
		
		return pwd;
	} 
	
	
	public String forgotUser(String username, String email) {
		String msg = "";
		String pwd = randPwd(12);
		
		//setup email
		String to = email;
		String from = "ibcalandria@gmail.com";
		String host = "localhost";
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(properties);
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			DBConnect db = new DBConnect (server, "ORCL", dbUsername, dbPassword);
			conn = db.getConnection();
			st = conn.createStatement();
			System.out.println("Checking Database For Existing User");
			
			try {
				rs = st.executeQuery("SELECT * FROM users WHERE USERNAME = '" + username + "' AND EMAIL = '" + email + "'");
				
				if(rs.next()) {
					String forgotQuery = "UPDATE USERS SET PASSWORD = '" + pwd + "' WHERE USERNAME = '" + username + "'" ;
					
					try {
						st.executeUpdate(forgotQuery);
						msg = "Password Changed Successfully!";
						st.close();
						conn.close();
						
						try {
							MimeMessage message = new MimeMessage(session);
							message.setFrom(new InternetAddress(from));
							message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
							message.setSubject("This is the Subject Line!");
							message.setText("This is actual message");
							Transport.send(message);
							System.out.println("Sent message successfully....");
						}
						catch(Exception exce) {
							System.out.println("Email not sent");
							msg += "<br/>Email not sent";
						}
					}
					
					catch (Exception exc) {
						System.out.println("Di na update yung pass men");
						msg = "Di gumana men pag change sa pass";
					}
					
				}
				
				else {
					msg = "Account Does Not Exist";
				}
			}
			catch(Exception ex) {
				System.out.println("Di ka totoong tao");
				msg = "User Doesn't Exist";
			}
		}
		catch(Exception e) {
			System.out.println("Di Makakonek");
			msg = "No Connection to Database";
		}
		
		return msg + "<br/> New Password : " + pwd;
	}
	
	
	public String updateUser(Users u, String newpass, String newemail) {
		String msg = "";
		Connection conn = null;
		Statement st = null;
		
		try {
			
			DBConnect db = new DBConnect (server, "ORCL", dbUsername, dbPassword);
			conn = db.getConnection();
			st = conn.createStatement();
			String updateQuery;
			System.out.println("Updating to server");
			
			if(newemail != "" && newpass != "") {
				updateQuery = "UPDATE USERS SET PASSWORD = '" + newpass + "', EMAIL = '" +newemail + "' WHERE USER_ID = "+ u.getUserId();
				try {
					st.executeUpdate(updateQuery);
					msg = "Updated Email and Pass Successfully";
					st.close();
					conn.close();
				}
				catch (Exception e) {
					System.out.println("DI MAUPDATE YUNG EMAIL AT PASS");
					msg = "Update PWD and Email Failed";
				}
			}
			
			else if(newemail == "" && newpass != ""){
				updateQuery = "UPDATE USERS SET PASSWORD = '" + newpass + "' WHERE USER_ID = "+u.getUserId();
				
				try {
					st.executeUpdate(updateQuery);
					msg = "Updated Password Successfully";
					st.close();
					conn.close();
				}
				
				catch (Exception e) {
					System.out.println("DI MAUPDATE YUNG PASS");
					msg = "Update Password Failed";
				}
			}
			
			else if(newemail != "" && newpass == ""){
				updateQuery = "UPDATE USERS SET EMAIL = '" + newemail + "' WHERE USER_ID = "+u.getUserId();
				try {
					st.executeUpdate(updateQuery);
					msg = "Updated Email Successfully";
					st.close();
					conn.close();
				}
				catch (Exception e) {
					System.out.println("DI MAUPDATE YUNG EMAIL");
					msg = "Update Email Failed";
				}
			}
		}  
			
		catch (SQLException se) {
			System.out.println(se);
		} 
			
		return msg;
	}
	
	public void editUser(int uid, String status) {
		Connection conn = null;
		Statement st = null;
			
		try {
				
			DBConnect db = new DBConnect (server, "ORCL", dbUsername, dbPassword);
			conn = db.getConnection();
			st = conn.createStatement();
			
			System.out.println("Updating to server");
			
			String editQuery = "UPDATE USERS SET STATUS = '" + status + "' WHERE USER_ID = " + uid;
			 
			try {
				System.out.println("Nirarun yung query");
				st.executeUpdate(editQuery);
				st.close();
				conn.close();
			}
			catch(Exception ex) {
				System.out.println("Di men ma update");
				System.out.println(ex);
				
			}
			 
		}  
		
		catch (Exception e) {
			System.out.println("Di makakonek sa db men");
			System.out.println(e);
		} 
			
			
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
			 
			String addQuery = "INSERT INTO USERS VALUES (" + uid + ", " + u.getRoleId() + ", '"+u.getUsername()+"', '"+u.getPassword()+"', '" + u.getEmail() + "', 'ENABLED')";
			 
			try {
				st.executeUpdate(addQuery);
				msg = "New Account Added";
				st.close();
				conn.close();		
			}
				
			catch (Exception ex) {
				msg = "Account Failed to be added";
				st.close();
				conn.close();
			}
			 
		}
		
		catch (Exception e) {
			System.out.println("MAY EXCEPTION");
			msg = "Di maka konek";
		}
				
		return msg;
	}
}
