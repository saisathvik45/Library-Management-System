package dao;

import db.DBConnection;
import java.sql.*;

public class Member {

    public static void addMem(int id, String firstname, String lastname, String qualification, String phone, String email) throws Exception {
        String sql = "INSERT INTO Member VALUES (?, ?, ?, ?, ?, ?)";
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setString(2, firstname);
        ps.setString(3, lastname);
        ps.setString(4, qualification);
        ps.setString(5, phone);
        ps.setString(6, email);
        ps.executeUpdate();
        con.close();
    }

    public static void getMem() throws Exception {
        Connection con = DBConnection.getConnection();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Member");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2));
        }
        con.close();
    }

    public static void updateMem(int id, String phone) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("UPDATE Member SET phone_number=? WHERE member_id=?");
        ps.setString(1, phone);
        ps.setInt(2, id);
        ps.executeUpdate();
        con.close();
    }

    public static void deleteMem(int id) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("DELETE FROM Member WHERE member_id=?");
        ps.setInt(1, id);
        ps.executeUpdate();
        con.close();
    }
}
