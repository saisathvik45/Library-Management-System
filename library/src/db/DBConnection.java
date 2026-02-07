package db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

    public static Connection getConnection() throws Exception {
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream("config.properties");
        props.load(fis);
        String url = props.getProperty("db.url") + props.getProperty("db.name");
        String user = props.getProperty("db.user");
        String pass = props.getProperty("db.pass");
        return DriverManager.getConnection(url, user, pass);
    }
}
