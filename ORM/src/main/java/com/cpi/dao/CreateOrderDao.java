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
	public static void createOrder(Order order) {
		Connection conn = null;
		Statement stmt = null;
		Statement stmt2 = null;
		PreparedStatement st = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		int oid = 0;
		float totalPrice= 0;
		
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
			
			stmt2 = conn.createStatement();
			rs2 = stmt2.executeQuery("select SUM(a.quantity * b.price) as TOTAL_PRICE\r\n"
					+ "from orderdetails a, product b\r\n"
					+ "where a.product_ID = b.product_id\r\n"
					+ "and a.order_id = " + oid);
			 
			if (rs2.next()){
				totalPrice = rs2.getInt("TOTAL_PRICE");
			}else {
				totalPrice = 0;
			}
			System.out.println(order.getDeliveryDate());
			String orderDeliver = order.getDeliveryDate();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			java.util.Date parsed = format.parse(orderDeliver);
			Date sqlDate = new Date(parsed.getTime());
			
			String sql = "INSERT INTO ORDERS VALUES(?,?,?,?,?,?, SYSDATE,?, ? , ?, ?, ?, ?)";
			st = conn.prepareStatement(sql);
			st.setInt(1, oid);
			st.setString(2, order.getCustomerFn());
			st.setString(3, order.getSourceName());
			st.setString(4, order.getOrderSource());
			st.setString(5, order.getCustomerLn());
			st.setDate(6, sqlDate);
			st.setInt(7,  order.getOrderStatus());
			st.setInt(8, order.getPaymentStatus());
			st.setFloat(9,order.getDiscount());
			st.setFloat(10, totalPrice);
			st.setString(11, order.getRemarks());
			st.setString(12, order.getMobileNumber());
			st.executeUpdate();	
			 
		}  catch (SQLException | ParseException se) {
			System.out.println(se);
		} finally {
			
			try {
				if (rs1 != null) {
					rs1.close();
				}
				if (rs2 != null) {
					rs2.close();
				}
				if (st != null) {
					st.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (stmt2 != null) {
					stmt2.close();
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