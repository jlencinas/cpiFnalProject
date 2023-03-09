package com.cpi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cpi.model.DBConnect;
import com.cpi.model.Order;

/**
 * @author Jan Christian Buan
 *
 */
public class OrderDao {
	
	private static final String dbUsername = "CALANDRIA";
	private static final String dbPassword = "calandria";
	private static final String server = "training-db.cosujmachgm3.ap-southeast-1.rds.amazonaws.com";
	
	public Order getOrder (int orderId) {
		
		Order o = new Order();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			
			 DBConnect db = new DBConnect (server, "ORCL", dbUsername, dbPassword);
			 conn = db.getConnection();
			 System.out.println("Connected to server");
			 
			 st = conn.createStatement();
			 rs = st.executeQuery("SELECT * FROM orders WHERE ORDER_ID = '" + orderId + "'");
			 
			 if (rs.next()) {
				 	o.setOrderId(rs.getInt("ORDER_ID"));
				 	o.setCustomerFn(rs.getString("CUSTOMER_FN"));
				 	o.setSourceName(rs.getString("SOURCE_NAME"));
				 	o.setOrderSource(rs.getString("ORDER_SOURCE"));
				 	o.setCustomerLn(rs.getString("CUSTOMER_LN"));
				 	o.setMobileNumber(rs.getInt("MOBILE_NUMBER"));
				 	o.setOrderDate(rs.getDate("ORDER_DATE"));
				 	o.setDeliveryDate(rs.getDate("DELIVERY_DATE"));
				 	o.setOrderStatus(rs.getInt("ORDER_STATUS"));
				 	o.setPaymentStatus(rs.getInt("PAYMENT_STATUS"));
				 	o.setDiscount(rs.getFloat("DISCOUNT"));
				 	o.setRemarks(rs.getString("REMARKS"));
				 	
				 	rs = st.executeQuery("SELECT * FROM orderdetails WHERE ORDER_ID = '" + orderId + "'");
				 	
				 	while (rs.next()) {
				 		
				 		int productId = rs.getInt("PRODUCT_ID");
				 		int qty = rs.getInt("QUANTITY");
				 		
				 		ResultSet product = st.executeQuery("SELECT * FROM products WHERE PRODUCT_ID = '" + productId + "'");
				 		if (product.next()) {
				 			
				 			float price = product.getFloat("PRICE");
				 			float orderPrice = price * qty;
				 			o.setPrice(orderPrice);
				 		}
				 		product.close();
				 	}
				 }
			 
			} catch (SQLException se) { System.out.println("No data found."); }
		
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
				} 
				catch (SQLException se) { System.out.println(se); }
			}
		return o;
	}
}
