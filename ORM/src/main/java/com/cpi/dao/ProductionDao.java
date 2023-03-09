package com.cpi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cpi.model.DBConnect;
import com.cpi.model.Production;

/**
 * @author Jan Christian Buan
 *
 */
public class ProductionDao {

	private static final String dbUsername = "CALANDRIA";
	private static final String dbPassword = "calandria";
	private static final String server = "training-db.cosujmachgm3.ap-southeast-1.rds.amazonaws.com";
	
	public Production getProduction() {
		Production p = new Production ();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			
			 DBConnect db = new DBConnect (server, "ORCL", dbUsername, dbPassword);
			 conn = db.getConnection();
			 System.out.println("Connected to server");
			 
			 st = conn.createStatement();
			 rs = st.executeQuery("SELECT * FROM PRODUCTION");
			 
			 if(rs.next()) {
		
				 p.setProductionID(rs.getInt("PRODUCTION_ID"));
				 p.setProductionUser(rs.getString("PRODUCTION_USER"));
				 p.setProductID(rs.getInt("PRODUCT_ID"));
				 p.setProductionDate(rs.getDate("PRODUCTION_DATE"));
				 p.setProductionQuantity(rs.getInt("PRODUCTION_QUANTITY")); 
			 }
			 
			} catch (SQLException se) { System.out.println("No data found"); }
		
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
			} 
			
			catch (SQLException se) { System.out.println(se); }
		}
		return p;
	}
}
