package com.cpi.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cpi.model.AuditOrderDetails;
import com.cpi.model.DBConnect;

public class AuditOrderDetailsDao {
	private static final String dbUsername = "CALANDRIA";
	private static final String dbPassword = "calandria";
	private static final String server = "training-db.cosujmachgm3.ap-southeast-1.rds.amazonaws.com";
	
	public static void setAuditOrderDetails(AuditOrderDetails auditorderdetails) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			DBConnect db = new DBConnect (server, "ORCL", dbUsername, dbPassword);
			conn = db.getConnection();
			System.out.println("Connected to server");
			 
			
			String sql = "INSERT INTO AUDITORDER VALUES(?, ?, ?, ?, ?, TO_DATE('?', 'MM-DD-YYYY'));";
			st = conn.prepareStatement(sql);
			st.setInt(1, auditorderdetails.getItemChangeID());
			st.setString(2, auditorderdetails.getUsername());
			st.setInt(3, auditorderdetails.getItemID());
			st.setInt(4, auditorderdetails.getOldQuantity());
			st.setInt(5, auditorderdetails.getNewQuantity());
			st.setDate(6, (Date) auditorderdetails.getChangeDate());
			
			st.executeUpdate();
			 
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
	}
}
