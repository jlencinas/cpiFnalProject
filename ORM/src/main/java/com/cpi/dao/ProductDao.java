package com.cpi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cpi.model.DBConnect;
import com.cpi.model.Product;

public class ProductDao {
	private static final String dbUsername = "CALANDRIA";
	private static final String dbPassword = "calandria";
	private static final String server = "training-db.cosujmachgm3.ap-southeast-1.rds.amazonaws.com";
	
	public Product getProduct()
	{
		Product p = new Product();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			
			 DBConnect db = new DBConnect (server, "ORCL", dbUsername, dbPassword);
			 conn = db.getConnection();
			 System.out.println("Connected to server");
			 
			 st = conn.createStatement();
			 rs = st.executeQuery("SELECT * FROM Product");
			 
			 if (rs.next())
			 {
				p.setProductID(rs.getInt("PRODUCT_ID"));
			 	p.setProductName(rs.getString("PRODUCT_NAME"));
			 	p.setProdcutDescription(rs.getString("PRODUCT_DESCRIPTION"));
			 	p.setProductPicture(rs.getString("PRODUCT_PICTURE"));
				p.setProductStatus(rs.getInt("PRODUCT_STATUS"));
				p.setProductPrice(rs.getFloat("PRICE"));
			 }
			 
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
		return p;
	}
}
