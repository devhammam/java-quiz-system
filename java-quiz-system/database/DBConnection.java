package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection conn;

    static {
        try {
            // تحميل درايفر SQLite
            Class.forName("org.sqlite.JDBC");

            // الاتصال بملف قاعدة البيانات (سيتم إنشاؤه تلقائيًا إن لم يكن موجودًا)
            conn = DriverManager.getConnection("jdbc:sqlite:quiz.db");
            System.out.println("✅ Connected to SQLite database successfully!");
        } catch (Exception ex) {
            System.out.println("❌ Cannot connect to SQLite database!");
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public static Connection getConnection() {
        return conn;
    }

    public static void closeConnection() {
        try {
            conn.close();
            System.out.println("✅ Disconnected from database.");
        } catch (SQLException ex) {
            System.out.println("❌ Error closing database connection!");
            ex.printStackTrace();
        }
    }
}
