package com.cpi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cpi.model.DBConnect;
import com.cpi.model.Product;

public class AddProduct {
	private static final String dbUsername = "CALANDRIA";
	private static final String dbPassword = "calandria";
	private static final String server = "training-db.cosujmachgm3.ap-southeast-1.rds.amazonaws.com";
	public static List<Product> getProducts() throws ClassNotFoundException {
		
		List<Product> products = new ArrayList<>();
		try {	
			Connection conn = null;
			Statement st = null;
			
			DBConnect db = new DBConnect (server, "ORCL", dbUsername, dbPassword);
			conn = db.getConnection();
			System.out.println("Connected to server");
			
			String sql = "SELECT * FROM product";
			Statement stmt = conn.createStatement();
			ResultSet rs1 = stmt.executeQuery(sql);
			while (rs1.next()) {
				Product p = null;
				p.setProductID(rs1.getInt("product_id"));
				p.setProductName(rs1.getString("product_name"));
				p.setProductDescription(rs1.getString("product_description"));
				p.setProductPicture(rs1.getString("product_picture"));
				p.setProductStatus(rs1.getInt("product_status"));
				p.setProductPrice(rs1.getFloat("price"));
				products.add(p);
			}
			rs1.close();
			stmt.close();
			conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
}
