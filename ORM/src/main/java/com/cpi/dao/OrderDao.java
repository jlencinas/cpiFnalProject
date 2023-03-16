package com.cpi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cpi.model.DBConnect;
import com.cpi.model.Order;

@Component
public class OrderDao {

	private static final String dbUsername = "CALANDRIA";
	private static final String dbPassword = "calandria";
	private static final String server = "training-db.cosujmachgm3.ap-southeast-1.rds.amazonaws.com";
	private final DBConnect db;

	public OrderDao() {
		db = new DBConnect(server, "ORCL", dbUsername, dbPassword);
	}

	public Order getOrder(int orderId) {

		Order o = new Order();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {

			conn = db.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM orders WHERE ORDER_ID = '" + orderId + "'");

			if (rs.next()) {
				o.setOrderId(rs.getInt("ORDER_ID"));
				o.setCustomerFn(rs.getString("CUSTOMER_FN"));
				o.setSourceName(rs.getString("SOURCE_NAME"));
				o.setOrderSource(rs.getString("ORDER_SOURCE"));
				o.setCustomerLn(rs.getString("CUSTOMER_LN"));
				o.setMobileNumber(rs.getString("MOBILE_NUMBER"));
				o.setOrderDate(rs.getDate("ORDER_DATE"));
				o.setDeliveryDate(formatDate(rs.getDate("DELIVERY_DATE")));
				o.setOrderStatus(rs.getInt("ORDER_STATUS"));
				o.setPaymentStatus(rs.getInt("PAYMENT_STATUS"));
				o.setDiscount(rs.getFloat("DISCOUNT"));
				o.setRemarks(rs.getString("REMARKS"));

				rs = st.executeQuery("SELECT * FROM orderdetails WHERE ORDER_ID = '" + orderId + "'");

				while (rs.next()) {

					int productId = rs.getInt("PRODUCT_ID");
					int qty = rs.getInt("QUANTITY");

					ResultSet product = st
							.executeQuery("SELECT * FROM products WHERE PRODUCT_ID = '" + productId + "'");
					if (product.next()) {

						float price = product.getFloat("PRICE");
						float orderPrice = price * qty;
						o.setPrice(orderPrice);
					}
					product.close();
				}
			}

		} catch (SQLException se) {
		
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
		return o;
	}

	public Order getOrderDetails(String mobileNumber) {

		Order order = new Order();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * FROM orders WHERE mobile_number = ?";

		try {

			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, mobileNumber);
			rs = ps.executeQuery();

			if (rs.next()) {
				order.setOrderId(rs.getInt("ORDER_ID"));
				order.setOrderStatus(rs.getInt("ORDER_STATUS"));
				order.setDeliveryDate(formatDate(rs.getDate("DELIVERY_DATE")));
				order.setPaymentStatus(rs.getInt("PAYMENT_STATUS"));
			}

		} catch (SQLException se) {
			System.out.println(se);
		}

		finally {

			try {
				if (rs != null) {
					rs.close();
				}

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
		return order;
	}

	public List<Order> getOrdersByDate() {

		List<Order> orders = new ArrayList<>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * FROM orders ORDER BY order_date ASC";

		try {

			conn = db.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {

				Order order = new Order();
				order.setOrderId(rs.getInt("ORDER_ID"));
				order.setOrderDate(rs.getDate("ORDER_DATE"));
				order.setOrderStatus(rs.getInt("ORDER_STATUS"));
				order.setDeliveryDate(formatDate(rs.getDate("DELIVERY_DATE")));
				order.setPaymentStatus(rs.getInt("PAYMENT_STATUS"));

				orders.add(order);
			}

		} catch (SQLException se) {
			System.out.println(se);
		}

		return orders;
	}

	public void updateOrder(Order order) {

		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE orders SET order_status = ?, payment_status = ? WHERE order_id = ?";

		try {

			conn = db.getConnection();
			ps = conn.prepareStatement(query);

			ps.setInt(1, order.getOrderStatus());
			ps.setInt(2, order.getPaymentStatus());
			ps.setInt(3, order.getOrderId());

			int rowsUpdated = ps.executeUpdate();

			if (rowsUpdated > 0) {
				System.out.println("Order ID: " + order.getOrderId() + " updated");
			} else {
				System.out.println("Failed to update Order ID: " + order.getOrderId());
			}

		} catch (SQLException se) {
			System.out.println(se);
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

	private String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, yyyy");
		return sdf.format(date);
	}

}
