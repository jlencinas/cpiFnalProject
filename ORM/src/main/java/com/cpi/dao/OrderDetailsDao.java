package com.cpi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cpi.model.DBConnect;
import com.cpi.model.OrderDetails;

/**
 * @author Jan Christian Buan
 *
 */
public class OrderDetailsDao {

	private static final String dbUsername = "CALANDRIA";
	private static final String dbPassword = "calandria";
	private static final String server = "training-db.cosujmachgm3.ap-southeast-1.rds.amazonaws.com";

	public static List<OrderDetails> getOrderDetails() {

		List<OrderDetails> orderDetails = new ArrayList<>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		Statement st1 = null;
		ResultSet rs2 = null;
		int oid = 0;

		try {

			DBConnect db = new DBConnect(server, "ORCL", dbUsername, dbPassword);
			conn = db.getConnection();
			System.out.println("Connected to server");

			st1 = conn.createStatement();
			rs2 = st1.executeQuery("select MAX(ORDER_ID) as MOID from orders");

			if (rs2.next()) {
				oid = rs2.getInt("MOID");
			} else {
				oid = 1;
			}

			st = conn.createStatement();
			rs = st.executeQuery("select * from orderdetails where order_id = " + oid);

			if (rs.next()) {
				OrderDetails od = new OrderDetails();
				od.setItemId(rs.getInt("ITEM_ID"));
				od.setOrderId(rs.getInt("ORDER_ID"));
				od.setProductId(rs.getInt("PRODUCT_ID"));
				od.setQuantity(rs.getInt("QUANTITY"));
				orderDetails.add(od);
			}

		} catch (SQLException se) {
			System.out.println("No data found");
		}

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
			} catch (SQLException se) {
				System.out.println(se);
			}
		}
		return orderDetails;
	}

	public static void deleteOrderDetail(int itemID) {
		Connection conn = null;
		try {

			PreparedStatement ps = null;
			DBConnect db = new DBConnect(server, "ORCL", dbUsername, dbPassword);
			conn = db.getConnection();
			String query = "delete from orderdetails where item_id = ?";
			
			ps = conn.prepareStatement(query);
			

			ps.setInt(1, itemID);
			
			ps.executeUpdate();
			System.out.println("The Item is Deleted");
			ps.close();
		} catch (SQLException se) {
			System.out.println("No data found");
		}

		finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				System.out.println(se);
			}
		}
	}

	public static void updateOrderDetails(int quantity, int itemID) {
		Connection conn = null;
		try {

			DBConnect db = new DBConnect(server, "ORCL", dbUsername, dbPassword);
			conn = db.getConnection();

			PreparedStatement ps = null;

			String query = "update orderdetails set quantity = ? where item_id = ?";

			ps = conn.prepareStatement(query);

			ps.setInt(1, quantity);
			ps.setInt(2, itemID);
			
			ps.executeUpdate();
			System.out.println("The Item is Updated");
			ps.close();
		} catch (SQLException se) {
			System.out.println("No data found");
		}

		finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				System.out.println(se);
			}
		}
	}
}