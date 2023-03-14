package com.cpi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cpi.model.DBConnect;
import com.cpi.model.OrderDetails;

/**
 * @author Jan Christian Buan
 *
 */
public class OrderDetailsDao {

	private static final String dbUsername = "CALANDRIA";
	private static final String dbPassword = "calandria";
	private static final String server = "training-db.cosujmachgm3.ap-southeast-1.rds.amazonaws.com";
	
	public OrderDetails getOrderDetails() {
		
		OrderDetails od = new OrderDetails();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			
			 DBConnect db = new DBConnect (server, "ORCL", dbUsername, dbPassword);
			 conn = db.getConnection();
			 System.out.println("Connected to server");
			 
			 st = conn.createStatement();
			 rs = st.executeQuery("SELECT * FROM ORDERDETAILS");
			 
			 if (rs.next()) {
				 od.setItemId(rs.getInt("ITEM_ID"));
				 od.setOrderId(rs.getInt("ORDER_ID"));
				 od.setProductId(rs.getInt("PRODUCT_ID"));
				 od.setQuantity(rs.getInt("QUANTITY"));
			 }
			
			} catch (SQLException se) { System.out.println("No data found"); }
		
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
			} catch (SQLException se) { System.out.println(se); }
		}
		return od;
	}
}
