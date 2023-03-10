package com.cpi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	
	public Product newProduct(int productId, String productName, String productDescription, String productPicture, int productStatus, float price) {
		
		Product p = new Product ();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "INSERT INTO product (product_id, product_name, product_description, " +
                "product_picture, product_status, price) VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			
			 DBConnect db = new DBConnect (server, "ORCL", dbUsername, dbPassword);
			 conn = db.getConnection();
			 System.out.println("Connected to server");
			 
			 ps = conn.prepareStatement(query);
			 ps.setInt(1, productId);
			 ps.setString(2, productName);
			 ps.setString(3, productDescription);
			 ps.setString(4, productPicture);
			 ps.setInt(5, productStatus);
			 ps.setFloat(6, price);
			 
			 ps.executeUpdate();
			 	
		 	} catch (SQLException se) { System.out.println("No data found."); }
		
		finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) { System.out.println(se); }
        }
		
		return p;
	}
	
	
}
