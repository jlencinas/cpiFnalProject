package com.cpi.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.cpi.model.DBConnect;
import com.cpi.model.Order;

public class CreateOrderDao {
	private static final String dbUsername = "CALANDRIA";
	private static final String dbPassword = "calandria";
	private static final String server = "training-db.cosujmachgm3.ap-southeast-1.rds.amazonaws.com";
	public static void createOrder(Order order) throws ParseException {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement st = null;
		ResultSet rs1 = null;
		int oid = 0;
		
			try {
			
			DBConnect db = new DBConnect (server, "ORCL", dbUsername, dbPassword);
			conn = db.getConnection();
			System.out.println("Connected to server");
			stmt = conn.createStatement();
			rs1 = stmt.executeQuery("select MAX(ORDER_ID) as MOID from orders");
			 
			if (rs1.next()){
				oid = rs1.getInt("MOID") + 1;
			}else {
				oid = 1;
			}
			 
			String orderDeliver = order.getDeliveryDate();
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
			java.util.Date parsed = format.parse(orderDeliver);
			Date sqlDate = new Date(parsed.getTime());
			
			String sql = "INSERT INTO ORDERS VALUES(?,?,?,?,?,?, SYSDATE,?, ? , ?, ?, ?, ?)";
			st = conn.prepareStatement(sql);
			st.setInt(1, oid);
			st.setString(2, order.getCustomerFn());
			st.setString(3, order.getSourceName());
			st.setString(4, order.getOrderSource());
			st.setString(5, order.getCustomerLn());
			st.setInt(6, order.getMobileNumber());
			st.setDate(7, sqlDate);
			st.setInt(8, order.getOrderStatus());
			st.setInt(9, order.getPaymentStatus());
			st.setFloat(10, order.getDiscount());
			st.setFloat(11, order.getPrice());
			st.setString(12, order.getRemarks());
			st.executeUpdate();	
			 
		}  catch (SQLException se) {
			System.out.println(se);
		} finally {
			
			try {
				if (rs1 != null) {
					rs1.close();
				}
				if (st != null) {
					st.close();
				}
				if (stmt != null) {
					stmt.close();
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
