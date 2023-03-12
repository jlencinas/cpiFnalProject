package com.cpi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cpi.model.DBConnect;
import com.cpi.model.Users;

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
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			DBConnect db = new DBConnect (server, "ORCL", dbUsername, dbPassword);
			conn = db.getConnection();
			st = conn.createStatement();
			System.out.println("Checking server for user and email");
			
			try {
				rs = st.executeQuery("SELECT * FROM users WHERE USERNAME = '" + username + "' AND EMAIL = '" + email + "'");
				
				if(rs.next()) {
					String forgotQuery = "UPDATE USERS SET PASSWORD = '" + pwd + "' WHERE USERNAME = '" + username + "'" ;
					
					try {
						st.executeUpdate(forgotQuery);
						msg = "Password Changed Successfully!";
						st.close();
						conn.close();
					}
					
					catch (Exception exc) {
						System.out.println("Di na update yung pass men");
						msg = "Di gumana men pag change sa pass";
					}
					
				}
				
				else {
					msg = "Walang account na ganun men";
				}
			}
			catch(Exception ex) {
				System.out.println("Di ka totoong tao");
				msg = "Di ka totoong tao";
			}
		}
		catch(Exception e) {
			System.out.println("Di Makakonek");
			msg = "Walang connection";
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
			
			System.out.println("Updating to server");
			
			if(newemail != "" && newpass != "") {
				String updateQuery = "UPDATE USERS SET PASSWORD = '" + newpass + "', EMAIL = '" +newemail + "' WHERE USER_ID = "+u.getUserId();
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
				String updateQuery = "UPDATE USERS SET PASSWORD = '" + newpass + "' WHERE USER_ID = "+u.getUserId();
				
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
				String updateQuery = "UPDATE USERS SET EMAIL = '" + newemail + "' WHERE USER_ID = "+u.getUserId();
				try {
					st.executeUpdate(updateQuery);
					msg = "Updated Email Successfully";
					st.close();
					conn.close();
				}
				catch (Exception e) {
					System.out.println("DI MAUPDATE YUNG PASS");
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
