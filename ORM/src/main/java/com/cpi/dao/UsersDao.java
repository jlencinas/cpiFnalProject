package com.cpi.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.cpi.model.DBConnect;
import com.cpi.model.Users;

public class UsersDao {

	private static final String dbUsername = "CALANDRIA";
	private static final String dbPassword = "calandria";
	private static final String server = "training-db.cosujmachgm3.ap-southeast-1.rds.amazonaws.com";

	public Users getUser(String username) {
		Users u = new Users();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			DBConnect db = new DBConnect(server, "ORCL", dbUsername, dbPassword);
			conn = db.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM users WHERE USERNAME = '" + username + "'");

			if (rs.next()) {
				u.setUserId(rs.getInt("USER_ID"));
				u.setRoleId(rs.getInt("ROLE_ID"));
				u.setUsername(rs.getString("USERNAME"));
				u.setPassword(rs.getString("PASSWORD"));
				u.setEmail(rs.getString("EMAIL"));
				u.setStatus(rs.getString("STATUS"));
			}

			else {
				u.setUserId(0);
			}

			st.close();
			conn.close();
		}

		catch (SQLException se) {
			System.out.println(se);
		}

		return u;
	}

	// password randomizer
	static String randPwd(int x) {
		String pwd = "";
		String alpnum = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";

		for (int y = 0; y < x; y++) {
			int index = (int) (alpnum.length() * Math.random());
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
			DBConnect db = new DBConnect(server, "ORCL", dbUsername, dbPassword);
			conn = db.getConnection();
			st = conn.createStatement();
			System.out.println("Checking Database For Existing User");

			try {
				rs = st.executeQuery(
						"SELECT * FROM users WHERE USERNAME = '" + username + "' AND EMAIL = '" + email + "'");

				if (rs.next()) {
					String forgotQuery = "UPDATE USERS SET PASSWORD = '" + pwd + "' WHERE USERNAME = '" + username
							+ "'";
					System.out.println("Updating Password");
					try {
						st.executeUpdate(forgotQuery);
						msg = "Password Changed Successfully!<br/> Check your email";
						st.close();
						conn.close();
						sendMail(email, pwd);
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
			catch (Exception ex) {
				System.out.println("User Does Not Exist");
				msg = "User Doesn't Exist";
			}
		} catch (Exception e) {
			System.out.println("Di Makakonek");
			msg = "No Connection to Database";
		}

		return msg;
	}

	public static void sendMail(String recipient, String text) throws Exception {
		String to = recipient;
		String content = text;
		String from = "markjewel02@gmail.com";
		String host = "smtp.gmail.com";
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.enable", "false");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("markjewel02@gmail.com", "anrnelfirlyxjztb");
            }
        });
        try {
            // Create a default MimeMessage object.            
        	MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header.            
        	message.setFrom(new InternetAddress(from));
            // Set To: header field of the header.            
        	message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // Set Subject: header field            
        	message.setSubject("Password Changed!");
            // Now set the actual message            
        	message.setText("This is your new password: "+ content);
            System.out.println("Sending Email");
            // Send message            
            Transport.send(message);
            System.out.println("Email Sent");
        } 
        catch (MessagingException mex) {
            mex.printStackTrace();
        }
	}

	public String updateUser(Users u, String newpass, String newemail) {
		String msg = "";
		Connection conn = null;
		Statement st = null;

		try {

			DBConnect db = new DBConnect(server, "ORCL", dbUsername, dbPassword);
			conn = db.getConnection();
			st = conn.createStatement();
			String updateQuery;
			System.out.println("Updating to server");

			if (newemail != "" && newpass != "") {
				updateQuery = "UPDATE USERS SET PASSWORD = '" + newpass + "', EMAIL = '" + newemail
						+ "' WHERE USER_ID = " + u.getUserId();
				try {
					st.executeUpdate(updateQuery);
					msg = "Updated Email and Pass Successfully";
					st.close();
					conn.close();
				} catch (Exception e) {
					System.out.println("DI MAUPDATE YUNG EMAIL AT PASS");
					msg = "Update PWD and Email Failed";
				}
			}

			else if (newemail == "" && newpass != "") {
				updateQuery = "UPDATE USERS SET PASSWORD = '" + newpass + "' WHERE USER_ID = " + u.getUserId();

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

			else if (newemail != "" && newpass == "") {
				updateQuery = "UPDATE USERS SET EMAIL = '" + newemail + "' WHERE USER_ID = " + u.getUserId();
				try {
					st.executeUpdate(updateQuery);
					msg = "Updated Email Successfully";
					st.close();
					conn.close();
				} catch (Exception e) {
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

	public void disableUser(int uid, String status) {
		Connection conn = null;
		Statement st = null;
		try {
			DBConnect db = new DBConnect(server, "ORCL", dbUsername, dbPassword);
			conn = db.getConnection();
			st = conn.createStatement();
			System.out.println("Updating to server");
			String disableQuery = "UPDATE USERS SET STATUS = '" + status + "' WHERE USER_ID = " + uid;
			try {
				System.out.println("Run Able Query");
				st.executeUpdate(disableQuery);
				st.close();
				conn.close();
			} catch (Exception ex) {
				System.out.println("Cant Update Status");
				System.out.println(ex);
			}

		} catch (Exception e) {
			System.out.println("NO DB CONNECTION");
		}
	}

	public void editUser(int uid, int roleid) {
		Connection conn = null;
		Statement st = null;

		try {

			DBConnect db = new DBConnect(server, "ORCL", dbUsername, dbPassword);
			conn = db.getConnection();
			st = conn.createStatement();

			System.out.println("Updating to server");

			String editQuery = "UPDATE USERS SET ROLE_ID = " + roleid + " WHERE USER_ID = " + uid;

			try {
				System.out.println("Run Edit Query");

				System.out.println("Nirarun yung query");

				st.executeUpdate(editQuery);
				st.close();
				conn.close();

			} catch (Exception exc) {
				System.out.println("Cant Update ROLE_ID ");

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
        PreparedStatement ps = null;
        ResultSet rs = null;
        int uid;

        try {

            DBConnect db = new DBConnect(server, "ORCL", dbUsername, dbPassword);
            conn = db.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("SELECT MAX(USER_ID) AS MUS FROM USERS");

            if (rs.next()) {
                uid = rs.getInt("MUS") + 1;
            } else {
                uid = 1;
            }

            String addQuery = "INSERT INTO USERS VALUES (?, ?, ?, ?, ?, ?)";

            try {
                ps = conn.prepareStatement(addQuery);
                ps.setInt(1, uid);
                ps.setInt(2, u.getRoleId());
                ps.setString(3, u.getUsername());
                ps.setString(4, u.getPassword());
                ps.setString(5, u.getEmail());
                ps.setString(6, "ENABLED");
                ps.executeUpdate();
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
            System.out.println("Cant Connect To Database");
            msg = "Cant Connect To Database";
        }

        return msg;
    }
}

