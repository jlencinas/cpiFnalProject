package com.cpi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cpi.model.DBConnect;
import com.cpi.model.Users;

public class GetUserDetails {
	private static final String dbUsername = "CALANDRIA";
	private static final String dbPassword = "calandria";
	private static final String server = "training-db.cosujmachgm3.ap-southeast-1.rds.amazonaws.com";
	private static DBConnect db;

	public GetUserDetails() {
		
	}

	public static List<Users> getUsers(int page, int rows) throws ClassNotFoundException {
		List<Users> userAccounts = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int offset = (page - 1) * rows;

		try {
			DBConnect db = new DBConnect(server, "ORCL", dbUsername, dbPassword);
			conn = db.getConnection();
			ps = conn.prepareStatement("SELECT * FROM USERS ORDER BY USER_ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
			ps.setInt(1, offset);
			ps.setInt(2, rows);
			rs = ps.executeQuery();

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
			ps.close();
			conn.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Di ka men nakonek sa server eh");
		}

		return userAccounts;
	}

	public static int getTotalUserCount() {
		
		int total = 0;
		try {
			DBConnect db = new DBConnect(server, "ORCL", dbUsername, dbPassword);
			Connection conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM users");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}

}