package com.cpi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cpi.model.DBConnect;
import com.cpi.model.Order;
import com.cpi.model.Product;

@Component
public class ProductionDao {

	private static final String dbUsername = "CALANDRIA";
	private static final String dbPassword = "calandria";
	private static final String server = "training-db.cosujmachgm3.ap-southeast-1.rds.amazonaws.com";
	private static DBConnect db;

	public ProductionDao() {
		db = new DBConnect(server, "ORCL", dbUsername, dbPassword);
	}

	public List<Order> getOrdersToday() {

		List<Order> orders = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		LocalDate today = getDateToday();
		LocalDateTime startOfDay = LocalDateTime.of (today, LocalTime.MIN);
		LocalDateTime endOfDay = LocalDateTime.of (today, LocalTime.MAX);
		
		try {

			conn = db.getConnection();
			String query = "SELECT * FROM orders WHERE delivery_date > ? AND delivery_date < ?";
			ps = conn.prepareStatement(query);

			Timestamp ts = Timestamp.valueOf(startOfDay);
			Timestamp ts2 = Timestamp.valueOf(endOfDay);
			ps.setTimestamp(1, ts);
			ps.setTimestamp(2, ts2);

			rs = ps.executeQuery();

			while (rs.next()) {

				Order order = new Order();
				order.setOrderId(rs.getInt("order_id"));
				order.setCustomerFn(rs.getString("customer_fn"));
				order.setSourceName(rs.getString("source_name"));
				order.setOrderSource(rs.getString("order_source"));
				order.setCustomerLn(rs.getString("customer_ln"));
				order.setMobileNumber(rs.getString("mobile_number"));
				order.setOrderDate(rs.getDate("order_date"));
				order.setDeliveryDate(formatDate(rs.getDate("delivery_date")));
				order.setOrderStatus(rs.getInt("order_status"));
				order.setPaymentStatus(rs.getInt("payment_status"));
				order.setDiscount(rs.getFloat("discount"));
				order.setPrice(rs.getFloat("price"));
				orders.add(order);

				System.out.println(order.getDeliveryDate());
			}

		} catch (SQLException se) {
			System.out.println(se);
		}

		return orders;
	}

	public List<Order> getOrdersTodayByTime(boolean isAM) {

		List<Order> orders = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		LocalDate today = getDateToday();
		LocalDateTime startOfDay = LocalDateTime.of(today, LocalTime.MIN);
		LocalDateTime endOfDay = LocalDateTime.of(today, LocalTime.MAX);

		if (isAM) {
			startOfDay = LocalDateTime.of(today, LocalTime.MIN);
			endOfDay = LocalDateTime.of(today, LocalTime.NOON);
		} else {
			startOfDay = LocalDateTime.of(today, LocalTime.NOON).plusNanos(1);
			endOfDay = LocalDateTime.of(today, LocalTime.MAX);
		}

		try {

			conn = db.getConnection();
			String query = "SELECT * FROM orders WHERE delivery_date > ? AND delivery_date < ?";
			ps = conn.prepareStatement(query);

			Timestamp ts = Timestamp.valueOf(startOfDay);
			Timestamp ts2 = Timestamp.valueOf(endOfDay);
			ps.setTimestamp(1, ts);
			ps.setTimestamp(2, ts2);

			rs = ps.executeQuery();

			while (rs.next()) {

				Order order = new Order();
				order.setOrderId(rs.getInt("order_id"));
				order.setCustomerFn(rs.getString("customer_fn"));
				order.setSourceName(rs.getString("source_name"));
				order.setOrderSource(rs.getString("order_source"));
				order.setCustomerLn(rs.getString("customer_ln"));
				order.setMobileNumber(rs.getString("mobile_number"));
				order.setOrderDate(rs.getDate("order_date"));
				order.setDeliveryDate(formatDate(rs.getDate("delivery_date")));
				order.setOrderStatus(rs.getInt("order_status"));
				order.setPaymentStatus(rs.getInt("payment_status"));
				order.setDiscount(rs.getFloat("discount"));
				order.setPrice(rs.getFloat("price"));
				orders.add(order);

			}

		} catch (SQLException se) {
			System.out.println(se);
		}

		return orders;
	}
	
	public List<Product> availableProducts() {

		List<Product> p = new ArrayList<>();

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {

			conn = db.getConnection();
		
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM Product WHERE product_status <> 2");

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

	public LocalDate getDateToday() {
		return LocalDate.now();
	}

	private String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, yyyy");
		return sdf.format(date);
	}
}