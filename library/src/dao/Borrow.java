package dao;

import java.sql.*;
import java.time.LocalDate;
import db.DBConnection;

public class Borrow{

    public static void issueBook(int borrowId, int bookId, int memberId, int days) throws Exception {
        LocalDate issue = LocalDate.now();
        LocalDate due = issue.plusDays(days);
        String sql = "INSERT INTO Borrow VALUES (?, ?, ?, ?, ?, ?)";
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, borrowId);
        ps.setInt(2, bookId);
        ps.setInt(3, memberId);
        ps.setDate(4, Date.valueOf(issue));
        ps.setDate(5, Date.valueOf(due));
        ps.setDate(6, null);
        ps.executeUpdate();
        con.close();
    }

    public static void returnBook(int borrowId) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("UPDATE Borrow SET return_date=CURDATE() WHERE borrow_id=?");
        ps.setInt(1, borrowId);
        ps.executeUpdate();
        con.close();
    }

    public static void viewBorrowHistory() throws Exception {
        Connection con = DBConnection.getConnection();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Borrow");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " Book:" + rs.getInt(2));
        }
        con.close();
    }
}
