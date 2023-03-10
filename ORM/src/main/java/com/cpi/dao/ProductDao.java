package com.cpi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cpi.model.DBConnect;
import com.cpi.model.Product;

/**
 * @author Jan Christian Buan
 *
 */
public class ProductDao {

	private static final String dbUsername = "CALANDRIA";
	private static final String dbPassword = "calandria";
	private static final String server = "training-db.cosujmachgm3.ap-southeast-1.rds.amazonaws.com";

	public Product getProduct() {
		Product p = null;

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {

			DBConnect db = new DBConnect(server, "ORCL", dbUsername, dbPassword);
			conn = db.getConnection();
			System.out.println("Connected to server");

			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM Product");

			if (rs.next()) {

				p.setProductID(rs.getInt("PRODUCT_ID"));

				p.setProductName(rs.getString("PRODUCT_NAME"));
				p.setProductDescription(rs.getString("PRODUCT_DESCRIPTION"));
				p.setProductPicture(rs.getString("PRODUCT_PICTURE"));

				p.setProductName(rs.getString("PRODUCT_NAME"));
				p.setProductDescription(rs.getString("PRODUCT_DESCRIPTION"));
				p.setProductPicture(rs.getString("PRODUCT_PICTURE"));

				p.setProductStatus(rs.getInt("PRODUCT_STATUS"));
				p.setProductPrice(rs.getFloat("PRICE"));

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

			DBConnect db = new DBConnect(server, "ORCL", dbUsername, dbPassword);
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

	public void updateProduct(int productId, String productName, String productDescription, String productPicture,
			Integer productStatus, Float price) {

		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE PRODUCT SET PRODUCT_NAME = ?, PRODUCT_DESCRIPTION = ?, PRODUCT_PICTURE = ?,\r\n"
				+ "PRODUCT_STATUS = ?, PRICE = ? WHERE PRODUCT_ID = ?";

		System.out.println(query);

		try {
			DBConnect db = new DBConnect(server, "ORCL", dbUsername, dbPassword);
			conn = db.getConnection();

			ps = conn.prepareStatement(query);

			ps.setString(1, productName);

			ps.setString(2, productDescription);

			ps.setString(3, productPicture);

			ps.setInt(4, productStatus);

			ps.setFloat(5, price);

			ps.setInt(6, productId);
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
		PreparedStatement ps = null;
		ResultSet rs = null;
		Product product = null;

		String query = "SELECT * FROM PRODUCT WHERE PRODUCT_ID = ?";

		try {
			DBConnect db = new DBConnect(server, "ORCL", dbUsername, dbPassword);
			conn = db.getConnection();
			System.out.println("Connected to server");

			ps = conn.prepareStatement(query);
			ps.setInt(1, productID);
			rs = ps.executeQuery();

			if (rs.next()) {

				int id = rs.getInt("PRODUCT_ID");
				String name = rs.getString("PRODUCT_NAME");
				String description = rs.getString("PRODUCT_DESCRIPTION");
				String picture = rs.getString("PRODUCT_PICTURE");
				int status = rs.getInt("PRODUCT_STATUS");
				float price = rs.getFloat("PRICE");

				product = new Product(id, name, description, picture, status, price);
			}
		} catch (SQLException se) {
			System.out.println(se);
		}

		return product;
	}
}
