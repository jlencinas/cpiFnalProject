package com.cpi.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.cpi.model.AuditOrder;
import com.cpi.model.DBConnect;

public class AuditOrderDao {
	private static final String dbUsername = "CALANDRIA";
	private static final String dbPassword = "calandria";
	private static final String server = "training-db.cosujmachgm3.ap-southeast-1.rds.amazonaws.com";
	
	public static void setAuditOrder(AuditOrder auditorder) {
		Connection conn = null;
		PreparedStatement st = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		int aid = 0;
		
		try {
			
			DBConnect db = new DBConnect (server, "ORCL", dbUsername, dbPassword);
			conn = db.getConnection();
			System.out.println("Connected to server");
			
			stmt = conn.createStatement();
			rs1 = stmt.executeQuery("select MAX(ITEMCHANGE_ID) as MAID from auditOrder");
			 
			if (rs1.next()){
				aid = rs1.getInt("MAID") + 1;
			}else {
				aid = 1;
			}
			 
			
			String sql = "INSERT INTO AUDITORDER VALUES(?, ?, ?, ?, ?, ?, SYSDATE)";
			st = conn.prepareStatement(sql);
			st.setInt(1, aid);
			st.setString(2, auditorder.getUsername());
			st.setInt(3, auditorder.getItemID());
			st.setString(4, auditorder.getFieldChanged());
			st.setInt(5, auditorder.getOldValue());
			st.setInt(6, auditorder.getNewValue());
			
			st.executeUpdate();
			 
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
		}
	}
	
	
	public static List<AuditOrder> getAuditOrders(int page, int rows) {

        List<AuditOrder> auditOrders = new ArrayList<>();
        DBConnect db = new DBConnect (server, "ORCL", dbUsername, dbPassword);
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int offset = (page-1) * rows;

        String query = "SELECT * FROM AUDITORDER OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {

            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, offset);
            ps.setInt(2, rows);
            rs = ps.executeQuery();

            while (rs.next()) {

                AuditOrder auOrder = new AuditOrder();
                auOrder.setItemID(rs.getInt("ITEM_ID"));
                auOrder.setFieldChanged(rs.getString("FIELDCHANGED"));
                auOrder.setOldValue(rs.getInt("OLD_VALUE"));
                auOrder.setNewValue(rs.getInt("NEW_VALUE"));
                auOrder.setUsername(rs.getString("USERNAME"));
                auditOrders.add(auOrder);
            }

        } catch (SQLException se) {
            System.out.println(se);
        }

        return auditOrders;
    }

    public static int getTotalAuditOrdersCount() {

        int total = 0;
        try {
            DBConnect db = new DBConnect(server, "ORCL", dbUsername, dbPassword);
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM AUDITORDER");
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

    private static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, yyyy");
        return sdf.format(date);
    }
}