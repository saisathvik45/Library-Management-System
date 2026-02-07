package dao;

import db.DBConnection;
import java.sql.*;

public class Books {

    public static void insert(int bookId, String title, String category, String publisher, String author, String isbn) throws Exception {
        String sql = "INSERT INTO Books(book_id, title, category,publisher_name,author,isbn_number) VALUES(?,?,?,?,?,?)";
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, bookId);
        ps.setString(2, title);
        ps.setString(3, category);
        ps.setString(4, publisher);
        ps.setString(5, author);
        ps.setString(6, isbn);
        ps.executeUpdate();
        con.close();
        System.out.println("Inserted Books");
    }

    public static void getBooks() throws Exception {
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Books");
        while (rs.next()) {
            System.out.println(rs.getInt("book_id") + "|" + rs.getString("title") + "|" + rs.getString("author"));
        }
        con.close();
    }

    public static void update(int bookId, String newTitle) throws Exception {
        String sql = "UPDATE Books SET title=? WHERE book_id=?";
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, newTitle);
        ps.setInt(2, bookId);
        ps.executeUpdate();
        con.close();
        System.out.println("Book updated.");
    }

    public static void delete(int bookId) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("DELETE FROM Books WHERE book_id=?");
        ps.setInt(1, bookId);
        ps.executeUpdate();
        con.close();
        System.out.println("Book deleted.");
    }
}
