package com.cpi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cpi.model.DBConnect;
import com.cpi.model.Users;

/*import java.util.*;
=======

import java.util.*;
>>>>>>> refs/remotes/origin/master
import jakarta.mail.*;
import jakarta.mail.internet.*;
<<<<<<< HEAD
import jakarta.activation.*;*/

/*import java.io.UnsupportedEncodingException;
import java.util.Date;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;*/

/*import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import jakarta.activation.*;*/

public class UsersDao {

	private static final String dbUsername = "CALANDRIA";
	private static final String dbPassword = "calandria";
	private static final String server = "training-db.cosujmachgm3.ap-southeast-1.rds.amazonaws.com";

	public Users getUser(String username, String password) {
		Users u = new Users();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {

			DBConnect db = new DBConnect(server, "ORCL", dbUsername, dbPassword);
			conn = db.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM users WHERE USERNAME = '" + username + "' AND PASSWORD = '" + password
					+ "' AND STATUS != 'DISABLED'");

			if (rs.next()) {
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

		// setup email
		/*
		 * String to = email; String from = "ibcalandria@gmail.com"; String host =
		 * "localhost";
		 * 
		 * Properties properties = System.getProperties();
		 * properties.setProperty("mail.smtp.host", host); Session session =
		 * Session.getDefaultInstance(properties);
		 */

		/*
		 * String smtpHostServer = "smtp.example.com"; String toEmail = email;
		 * Properties props = System.getProperties(); props.put("mail.smtp.host",
		 * smtpHostServer); Session session = Session.getInstance(props, null); String
		 * subject = "SimpleEmail Testing Subject"; String body =
		 * "SimpleEmail Testing Body";
		 */

		
		//setup email
		/*String to = email;
		String from = "ibcalandria@gmail.com";
		String host = "localhost";
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(properties);*/
		
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
					System.out.println("Updating Database");
					try {
						st.executeUpdate(forgotQuery);
						msg = "Password Changed Successfully!";
						st.close();
						conn.close();

						// email sending
						/*
						 * try { MimeMessage message = new MimeMessage(session); message.setFrom(new
						 * InternetAddress(from)); message.addRecipient(Message.RecipientType.TO, new
						 * InternetAddress(to)); message.setSubject("This is the Subject Line!");
						 * message.setText("This is actual message"); Transport.send(message);
						 * System.out.println("Sent message successfully...."); } catch(Exception exce)
						 * { System.out.println("Email not sent"); msg += "<br/>Email not sent"; }
						 */

						
						  try {
						  
								/*
								 * MimeMessage message = new MimeMessage(session);
								 * message.addHeader("Content-type", "text/HTML; charset=UTF-8");
								 * message.addHeader("format", "flowed");
								 * message.addHeader("Content-Transfer-Encoding", "8bit");
								 * 
								 * message.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));
								 * message.setReplyTo(InternetAddress.parse("no_reply@example.com", false));
								 * message.setSubject(subject, "UTF-8"); message.setText(body, "UTF-8");
								 * message.setSentDate(new Date());
								 * message.setRecipients(Message.RecipientType.TO,
								 * InternetAddress.parse(toEmail, false));
								 * System.out.println("Message is ready");
								 * 
								 * Transport.send(message);
								 */
						  
							  sendMail(email);
							  msg += "<br/>Email sent";
						  
						  }
						  
						  catch(Exception exce) { 
							  System.out.println("Email not sent"); 
							  msg += "<br/>Email not sent"; 
						  }
						 

						
						try {
							/*MimeMessage message = new MimeMessage(session);
							message.setFrom(new InternetAddress(from));
							message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
							message.setSubject("This is the Subject Line!");
							message.setText("This is actual message");
							Transport.send(message);
							System.out.println("Sent message successfully....");*/
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
			} catch (Exception ex) {
				System.out.println("Di ka totoong tao");
				msg = "User Doesn't Exist";
			}
		} catch (Exception e) {
			System.out.println("Di Makakonek");
			msg = "No Connection to Database";
		}

		return msg + "<br/> New Password : " + pwd;
	}

	public static void sendMail(String recipient) throws Exception {
		/*
		 * System.out.println("Creating Properties"); Properties properties = new
		 * Properties(); properties.put("mail.smtp.auth", "true");
		 * properties.put("mail.smtp.starttls.enable", "true");
		 * properties.put("mail.smtp.host", "smtp.gmail.com");
		 * properties.put("mail.smtp.port", "587");
		 * 
		 * final String myAccountEmail = "ivannbenedict.calandria.cics@ust.edu.ph";
		 * final String myPassword = "SystemCrash";
		 * System.out.println("Creating Session"); Session session =
		 * Session.getInstance(properties, new Authenticator() {
		 * 
		 * @Override protected PasswordAuthentication getPasswordAuthentication() {
		 * return new PasswordAuthentication(myAccountEmail, myPassword); } });
		 * System.out.println("Sending Message"); Message message =
		 * prepareMessage(session, myAccountEmail, recipient); Transport.send(message);
		 * System.out.println("EMAIL SENT SUCCESS");
		 */
	}

	/*
	 * private static Message prepareMessage(Session session, String myAccountEmail,
	 * String recipient) { Message message = new MimeMessage(session); try {
	 * message.setFrom(new InternetAddress(myAccountEmail));
	 * message.setRecipient(Message.RecipientType.TO, new
	 * InternetAddress(recipient)); message.setSubject("Email Trial Using Java");
	 * message.setText("HOPE IT WORKS"); return message; } catch (Exception e) {
	 * System.out.println("Email is not working"); e.printStackTrace(); } return
	 * null; }
	 */

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
            DBConnect db = new DBConnect (server, "ORCL", dbUsername, dbPassword);
            conn = db.getConnection();
            st = conn.createStatement();
            System.out.println("Updating to server");
            String disableQuery = "UPDATE USERS SET STATUS = '" + status + "' WHERE USER_ID = " + uid;
            try {
                System.out.println("Run Able Query");
                st.executeUpdate(disableQuery);
                st.close();
                conn.close();
            }
            catch(Exception ex) {
                System.out.println("Cant Update Status");
                System.out.println(ex);
            }
            
        }
        catch(Exception e) {
        	System.out.println("NO DB CONNECTION");
        }
	}

	
	public void editUser(int uid, String status) {

		Connection conn = null;
		Statement st = null;

		try {

			DBConnect db = new DBConnect(server, "ORCL", dbUsername, dbPassword);
			conn = db.getConnection();
			st = conn.createStatement();

			System.out.println("Updating to server");


			String disableQuery = "UPDATE USERS SET STATUS = '" + status + "' WHERE USER_ID = " + uid;


			
			String editQuery = "UPDATE USERS SET STATUS = '" + status + "' WHERE USER_ID = " + uid;			 

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
			System.out.println("Di makakonek sa db men");
			System.out.println(e);
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

			} 
			catch (Exception exc) {
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

			String addQuery = "INSERT INTO USERS VALUES (" + uid + ", " + u.getRoleId() + ", '" + u.getUsername()
					+ "', '" + u.getPassword() + "', '" + u.getEmail() + "', 'ENABLED')";

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
