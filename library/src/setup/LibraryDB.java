
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class LibraryDB {

    static String MYSQL_URL;
    static String DB_NAME;
    static String USER;
    static String PASS;

    public static void main(String[] args) {
        try {
            Properties props = new Properties();
            FileInputStream fis = new FileInputStream("config.properties");
            props.load(fis);
            MYSQL_URL = props.getProperty("db.url");
            DB_NAME = props.getProperty("db.name");
            USER = props.getProperty("db.user");
            PASS = props.getProperty("db.pass");
            // 1. Connect to MySQL server (no database yet)
            Connection con = DriverManager.getConnection(MYSQL_URL, USER, PASS);
            Statement stmt = con.createStatement();
            // 2. Create database if not exists
            String createDB = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
            stmt.executeUpdate(createDB);
            System.out.println("Database checked/created.");
            // 3. Switch to database
            stmt.executeUpdate("USE " + DB_NAME);
            // 4. Create Books table
            String booksTable
                    = "CREATE TABLE IF NOT EXISTS Books ("
                    + "book_id INT PRIMARY KEY," + "title VARCHAR(100) NOT NULL," + "category VARCHAR(100)," + "publisher_name VARCHAR(100),"
                    + "author VARCHAR(100)," + "isbn_number VARCHAR(100) UNIQUE" + ")";
            stmt.executeUpdate(booksTable);
            // 5. Create Member table
            String memberTable
                    = "CREATE TABLE IF NOT EXISTS Member ("
                    + "member_id INT PRIMARY KEY," + "first_name VARCHAR(100)," + "last_name VARCHAR(100)," + "qualification VARCHAR(100),"
                    + "phone_number VARCHAR(15)," + "email_address VARCHAR(100) UNIQUE" + ")";
            stmt.executeUpdate(memberTable);
            // 6. Create Borrow table
            String borrowTable
                    = "CREATE TABLE IF NOT EXISTS Borrow ("
                    + "borrow_id INT PRIMARY KEY," + "book_id INT," + "member_id INT," + "issue_date DATE NOT NULL," + "due_date DATE NOT NULL,"
                    + "return_date DATE," + "FOREIGN KEY (book_id) REFERENCES Books(book_id) ON DELETE CASCADE,"
                    + "FOREIGN KEY (member_id) REFERENCES Member(member_id) ON DELETE CASCADE" + ")";
            stmt.executeUpdate(borrowTable);
            // 7. Create Fine table
            String fineTable
                    = "CREATE TABLE IF NOT EXISTS Fine ("
                    + "fine_id INT PRIMARY KEY," + "borrow_id INT NOT NULL," + "payment_status VARCHAR(30)," + "amount DECIMAL(8,2) NOT NULL,"
                    + "discount DECIMAL(8,2) DEFAULT 0.00," + "FOREIGN KEY (borrow_id) REFERENCES Borrow(borrow_id) ON DELETE CASCADE" + ")";
            stmt.executeUpdate(fineTable);
            System.out.println("All tables checked/created successfully.");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
