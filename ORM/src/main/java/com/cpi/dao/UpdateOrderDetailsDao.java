package com.cpi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cpi.model.DBConnect;
import com.cpi.model.Product;
import com.cpi.model.UpdateOrderDetails;

public class UpdateOrderDetailsDao {
	private static final String dbUsername = "CALANDRIA";
	private static final String dbPassword = "calandria";
	private static final String server = "training-db.cosujmachgm3.ap-southeast-1.rds.amazonaws.com";
	
	public static List<UpdateOrderDetails> displayUpdateOrderDetails(){
		List<UpdateOrderDetails> uod = new ArrayList<>();
		try {	
			Connection conn = null;
			
			DBConnect db = new DBConnect (server, "ORCL", dbUsername, dbPassword);
			conn = db.getConnection();
			System.out.println("Connected to server");
			String sql = "select a.item_id, a.order_id, a.product_id, b.price, b.discount, c.product_name\r\n"
					+ "from orderdetails a, orders b, product c\r\n"
					+ "where a.order_id = b.order_id\r\n"
					+ "and a.product_id = c.product_id";
			Statement stmt = conn.createStatement();
			ResultSet rs1 = stmt.executeQuery(sql);
			while (rs1.next()) {
				UpdateOrderDetails upOrderDetails = new UpdateOrderDetails();
				upOrderDetails.setItem_id(rs1.getInt("ITEM_ID"));
				upOrderDetails.setOrder_id(rs1.getInt("ORDER_ID"));
				upOrderDetails.setProduct_id(rs1.getInt("PRODUCT_ID"));
				upOrderDetails.setPrice(rs1.getFloat("PRICE"));
				upOrderDetails.setDiscount(rs1.getFloat("DISCOUNT"));
				upOrderDetails.setProductName(rs1.getString("PRODUCT_NAME"));
				uod.add(upOrderDetails);
			}
			rs1.close();
			stmt.close();
			conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return uod;
	}
//
//	
//	DBConnect db = new DBConnect (server, "ORCL", dbUsername, dbPassword);
//	Connection conn = null;
//	PreparedStatement ps = null;
//
//	String query = "UPDATE orders SET order_status = ?, payment_status = ? WHERE order_id = ?";
//
//	try {
//
//		conn = db.getConnection();
//		ps = conn.prepareStatement(query);
//
//		ps.setInt(1, order.getOrderStatus());
//		ps.setInt(2, order.getPaymentStatus());
//		ps.setInt(3, order.getOrderId());
//
//		int rowsUpdated = ps.executeUpdate();
//
//		if (rowsUpdated > 0) {
//			System.out.println("Order ID: " + order.getOrderId() + " updated");
//		} else {
//			System.out.println("Failed to update Order ID: " + order.getOrderId());
//		}
//
//	} catch (SQLException se) {
//		System.out.println(se);
//	}
//
//	finally {
//		try {
//			if (ps != null) {
//				ps.close();
//			}
//			if (conn != null) {
//				conn.close();
//			}
//		} catch (SQLException se) {
//			System.out.println(se);
//		}
//	}
}
