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
	
	public static List<UpdateOrderDetails> displayUpdateOrderDetails(int id){
		List<UpdateOrderDetails> uod = new ArrayList<>();
		try {	
			Connection conn = null;
			
			DBConnect db = new DBConnect (server, "ORCL", dbUsername, dbPassword);
			conn = db.getConnection();
			System.out.println("Connected to server");
			String sql = "select a.item_id, a.order_id, a.product_id, a.quantity, c.price as Product_Price, b.discount, b.price as Total_Price,c.product_name\r\n"
					+ "from orderdetails a, orders b, product c\r\n"
					+ "where a.order_id = b.order_id\r\n"
					+ "and a.product_id = c.product_id\r\n"
					+ "and a.order_id = " + id;
			Statement stmt = conn.createStatement();
			ResultSet rs1 = stmt.executeQuery(sql);
			while (rs1.next()) {
				UpdateOrderDetails upOrderDetails = new UpdateOrderDetails();
				upOrderDetails.setItem_id(rs1.getInt("ITEM_ID"));
				upOrderDetails.setOrder_id(rs1.getInt("ORDER_ID"));
				upOrderDetails.setProduct_id(rs1.getInt("PRODUCT_ID"));
				upOrderDetails.setQuantity(rs1.getInt("QUANTITY"));
				upOrderDetails.setPrice(rs1.getFloat("Product_Price"));
				upOrderDetails.setDiscount(rs1.getFloat("DISCOUNT"));
				upOrderDetails.setTotalPrice(rs1.getFloat("Total_Price"));
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
	
	//Remember to everyone
	
//	public static List<Product> showOrderDetails(List<String> productid, List<String> quantity){
//		List<Product> product = new ArrayList<>();
//		Connection conn = null;
//		DBConnect db = new DBConnect (server, "ORCL", dbUsername, dbPassword);
//		conn = db.getConnection();
//		System.out.println("Connected to server");
//		String sql = "select product_id, product_name, price\r\n"
//				+ "from product\r\n"
//				+ "where product_id = 1";
//		Statement stmt;
//		try {
//			stmt = conn.createStatement();
//			ResultSet rs1 = stmt.executeQuery(sql);
//			while (rs1.next()) {
//				Product p = new Product();
//				p.setProductID(rs1.getString("PRODUCT_ID"));
//				p.setProductName(rs1.getString("PRODUCT_NAME"));
//				p.setProductDescription(rs1.getString("PRODUCT_DESCRIPTION"));
//				p.setProductPicture(rs1.getString("PICTURE"));
//				p.setProductStatus(rs1.getInt("PRODUCT_STATUS"));
//				p.setProductPrice(rs1.getFloat("PRICE"));
//				product.add(p);
//			}
//			rs1.close();
//			stmt.close();
//			conn.close();
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		}
//		
//		
//		return product;
//	}

}
