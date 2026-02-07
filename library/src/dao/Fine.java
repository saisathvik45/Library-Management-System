package dao;

import java.sql.*;
import db.DBConnection;

public class Fine {

    public static void addFine(int fineId, int borrowId, double amount) throws Exception {
        String sql = "INSERT INTO Fine VALUES (?, ?, ?, ?, ?)";
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, fineId);
        ps.setInt(2, borrowId);
        ps.setString(3, "UNPAID");
        ps.setDouble(4, amount);
        ps.setDouble(5, 0.0);
        ps.executeUpdate();
        con.close();
    }

    public static void payFine(int fineId) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("UPDATE Fine SET payment_status='PAID' WHERE fine_id=?");
        ps.setInt(1, fineId);
        ps.executeUpdate();
        con.close();
    }

    public static void viewFines() throws Exception {
        Connection con = DBConnection.getConnection();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Fine");

        while (rs.next()) {
            System.out.println(rs.getInt(1) + " Amount:" + rs.getDouble(4));
        }
        con.close();
    }
}
