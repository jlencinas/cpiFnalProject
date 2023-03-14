package com.cpi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cpi.model.DBConnect;
import com.cpi.model.Product;

@Component
public class ProductDao {

	private static final String dbUsername = "CALANDRIA";
	private static final String dbPassword = "calandria";
	private static final String server = "training-db.cosujmachgm3.ap-southeast-1.rds.amazonaws.com";
	private final DBConnect db;

	public ProductDao() {
		db = new DBConnect(server, "ORCL", dbUsername, dbPassword);
	}

	public List<Product> getProduct() {

		List<Product> p = new ArrayList<>();

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {

			conn = db.getConnection();
			System.out.println("Connected to server");

			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM Product");

			while (rs.next()) {

				Product product = new Product();
				product.setProductID(rs.getString("PRODUCT_ID"));
				product.setProductName(rs.getString("PRODUCT_NAME"));
				product.setProductDescription(rs.getString("PRODUCT_DESCRIPTION"));
				product.setProductPicture(rs.getString("PRODUCT_PICTURE"));
				product.setProductStatus(rs.getInt("PRODUCT_STATUS"));
				product.setProductPrice(rs.getFloat("PRICE"));

				p.add(product);

			}
		} catch (SQLException se) {
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

	public Product newProduct(int productId, String productName, String productDescription, String productPicture,
			int productStatus, float price) {

		Product p = null;
		Connection conn = null;
		PreparedStatement ps = null;
		Statement st = null;
		ResultSet rs = null;
		int prodId = 1;

		String query = "INSERT INTO product (product_id, product_name, product_description, "
				+ "product_picture, product_status, price) VALUES (?, ?, ?, ?, ?, ?)";

		try {

			conn = db.getConnection();
			System.out.println("Connected to server");

			st = conn.createStatement();
			rs = st.executeQuery("SELECT MAX(PRODUCT_ID) AS MPD FROM PRODUCT");

			if (rs.next()) {
				prodId = rs.getInt("MPD") + 1;
			}

			ps = conn.prepareStatement(query);
			ps.setInt(1, prodId);
			ps.setString(2, productName);
			ps.setString(3, productDescription);
			ps.setString(4, productPicture);
			ps.setInt(5, productStatus);
			ps.setFloat(6, price);

			ps.executeUpdate();
			System.out.println("Added new product");

		} catch (SQLException se) {
			System.out.println("No data found.");
		}

		finally {
			try {
				if (ps != null) {
					ps.close();
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

	public void updateProduct(Product product) {

		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE PRODUCT SET PRODUCT_NAME = ?, PRODUCT_DESCRIPTION = ?, PRODUCT_PICTURE = ?,\r\n"
				+ "PRODUCT_STATUS = ?, PRICE = ? WHERE PRODUCT_ID = ?";

		System.out.println(query);

		try {
			conn = db.getConnection();

			ps = conn.prepareStatement(query);

			ps.setString(1, product.getProductName());

			ps.setString(2, product.getProductDescription());

			ps.setString(3, product.getProductPicture());

			ps.setInt(4, product.getProductStatus());

			ps.setFloat(5, product.getProductPrice());

			ps.setString(6, product.getProductID());
			ps.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		}

		finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				System.out.println(se);
			}
		}
	}

	public Product getByProductID(int productID) {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		Product product = new Product();

		try {
			conn = db.getConnection();
			System.out.println("Connected to server");

			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM PRODUCT WHERE PRODUCT_ID = '" + productID + "'");

			if (rs.next()) {

				product.setProductID(rs.getString("PRODUCT_ID"));
				product.setProductName(rs.getString("PRODUCT_NAME"));
				product.setProductDescription(rs.getString("PRODUCT_DESCRIPTION"));
				product.setProductPicture(rs.getString("PRODUCT_PICTURE"));
				product.setProductStatus(rs.getInt("PRODUCT_STATUS"));
				product.setProductPrice(rs.getFloat("PRICE"));

			}
		} catch (SQLException se) {
			System.out.println(se);
		}

		return product;
	}
}