package com.cpi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cpi.model.DBConnect;
import com.cpi.model.Summary;

public class GetSummaryDetails {
	private static final String dbUsername = "CALANDRIA";
	private static final String dbPassword = "calandria";
	private static final String server = "training-db.cosujmachgm3.ap-southeast-1.rds.amazonaws.com";
	
	public static List<Summary> getSummary(int page, int rows) throws ClassNotFoundException {
		List<Summary> summary = new ArrayList<>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		int offset = (page - 1) * rows;
				
		try {	
			DBConnect db = new DBConnect (server, "ORCL", dbUsername, dbPassword);
			conn = db.getConnection();
			String query = "SELECT p.PRODUCT_ID, o.DELIVERY_DATE, o.PAYMENT_STATUS, "
					+ "SUM(od.QUANTITY) AS \"QUANTITY\", SUM(p.PRICE * od.QUANTITY) AS \"PRICE\" "
					+ "FROM PRODUCT p, ORDERS o, ORDERDETAILS od WHERE o.ORDER_ID = od.ORDER_ID "
					+ "AND od.PRODUCT_ID = p.PRODUCT_ID AND o.ORDER_STATUS NOT IN (50,90) "
					+ "GROUP BY p.PRODUCT_ID, o.DELIVERY_DATE, o.PAYMENT_STATUS "
					+ "ORDER BY p.PRODUCT_ID, o.DELIVERY_DATE, o.PAYMENT_STATUS OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
			st = conn.prepareStatement(query);
			st.setInt(1, offset);
			st.setInt(2, rows);
			rs = st.executeQuery();
							
			while (rs.next()) {
				Summary sum = new Summary();
				sum.setProdId(rs.getInt("PRODUCT_ID"));
				sum.setDeliveryDate(rs.getString("DELIVERY_DATE"));
				sum.setPaymentStatus(rs.getInt("PAYMENT_STATUS"));
				sum.setQuantity(rs.getInt("QUANTITY"));
				sum.setPrice(rs.getInt("PRICE"));
				summary.add(sum);
			}
				
			rs.close();
			st.close();
			conn.close();
		}
		
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Di ka men nakonek sa server eh");
		}
		
		return summary;
	}
		
public static int getTotalCount() {
		
		int total = 0;
		try {
			DBConnect db = new DBConnect(server, "ORCL", dbUsername, dbPassword);
			Connection conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*)\r\n"
					+ "FROM PRODUCT p, ORDERS o, ORDERDETAILS od \r\n"
					+ "WHERE o.ORDER_ID = od.ORDER_ID \r\n"
					+ "AND od.PRODUCT_ID = p.PRODUCT_ID \r\n"
					+ "AND o.ORDER_STATUS NOT IN (50,90)");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
}
