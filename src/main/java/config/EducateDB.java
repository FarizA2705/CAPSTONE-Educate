package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EducateDB {
    private static final String URL = "jdbc:mysql://localhost:3306/educate3";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Koneksi Database Gagal: " + e.getMessage());
        }
        return conn;
    }
}