package com.cpi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cpi.model.DBConnect;
import com.cpi.model.OrderSummary;

public class AddOrderDetailsDao {
	private static final String dbUsername = "CALANDRIA";
	private static final String dbPassword = "calandria";
	private static final String server = "training-db.cosujmachgm3.ap-southeast-1.rds.amazonaws.com";
	
	public static void createOrderDetails(int productid, int quantity) {
		Connection conn = null;
		Statement stmt = null;
		Statement stmt2 = null;
		PreparedStatement st = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		int oid = 0;
		int tid = 0;
		
			try {
			
			DBConnect db = new DBConnect (server, "ORCL", dbUsername, dbPassword);
			conn = db.getConnection();
			System.out.println("Connected to server");
			stmt = conn.createStatement();
			rs1 = stmt.executeQuery("select MAX(ORDER_ID) as MOID from orders");
			 
			if (rs1.next()){
				oid = rs1.getInt("MOID");
			}else {
				oid = 1;
			}
			
			stmt2 = conn.createStatement();
			rs2 = stmt2.executeQuery("SELECT MAX(ITEM_ID) as MID FROM ORDERDETAILS");
			 
			if (rs2.next()){
				tid = rs2.getInt("MID") + 1;
			}else {
				tid = 1;
			}

			String sql = "INSERT INTO ORDERDETAILS VALUES (?,?,?,?)";
			st = conn.prepareStatement(sql);
			st.setInt(1, tid);
			st.setInt(2, oid);
			st.setInt(3, productid);
			st.setInt(4, quantity);
			st.executeUpdate();	
			 
		}  catch (SQLException se) {
			System.out.println(se);
		} finally {
			
			try {
				if (rs1 != null) {
					rs1.close();
				}
				if (rs2 != null) {
					rs2.close();
				}
				if (st != null) {
					st.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (stmt2 != null) {
					stmt2.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				System.out.println(se);
			}
		}
	}
	
	@SuppressWarnings("finally")
	public static OrderSummary displayOrderSummary(int productID, int quantity) {
		OrderSummary ordersummary = new OrderSummary();
		Statement stmt = null;
		ResultSet rs1 = null;
		Connection conn = null;
		try {    
            DBConnect db = new DBConnect (server, "ORCL", dbUsername, dbPassword);
            conn = db.getConnection();
            System.out.println("Connected to server");
            
            String sql = "select product_name, product_description, product_picture, price\r\n"
            		+ "from product\r\n"
            		+ "where product_id = " + productID;
            stmt = conn.createStatement();
            rs1 = stmt.executeQuery(sql);
            while (rs1.next()) {
               
            	ordersummary.setOrderName(rs1.getString("product_name"));
            	ordersummary.setOrderDescription(rs1.getString("product_description"));
            	ordersummary.setOrderPicture(rs1.getString("product_picture"));
            	ordersummary.setOrderPrice(rs1.getFloat("price"));
            	ordersummary.setOrderquantity(quantity);
            	ordersummary.setOrderItemID(productID);
            }
        }catch (SQLException se) {
			System.out.println(se);
		} finally {
			try {
				if (rs1 != null) {
					rs1.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				System.out.println(se);
			}
        return ordersummary;
		}
	}
}