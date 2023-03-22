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

	public static List<UpdateOrderDetails> displayUpdateOrderDetails() {
		List<UpdateOrderDetails> uod = new ArrayList<>();
		int oid = 0;
		try {
			Connection conn = null;

			DBConnect db = new DBConnect(server, "ORCL", dbUsername, dbPassword);
			conn = db.getConnection();
			System.out.println("Connected to server");
			Statement stmt1 = conn.createStatement();
			ResultSet rs2 = stmt1.executeQuery("select MAX(ORDER_ID) as MOID from orders");

			if (rs2.next()) {
				oid = rs2.getInt("MOID");
			} else {
				oid = 1;
			}
			System.out.println(oid);

			String sql = "select a.item_id, a.order_id, a.product_id, a.quantity, c.price as Product_Price, b.discount, b.price as Total_Price,c.product_name\r\n"
					+ "from orderdetails a, orders b, product c\r\n" + "where a.order_id = b.order_id\r\n"
					+ "and a.product_id = c.product_id\r\n" + "and a.order_id = " + oid;
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
			rs2.close();
			stmt.close();
			stmt1.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uod;
	}

}