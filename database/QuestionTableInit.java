package database;

import java.sql.Connection;
import java.sql.Statement;

public class QuestionTableInit {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // إنشاء جدول الأسئلة
            String sql = "CREATE TABLE IF NOT EXISTS question (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "question_text TEXT NOT NULL," +
                    "option1 TEXT," +
                    "option2 TEXT," +
                    "option3 TEXT," +
                    "option4 TEXT," +
                    "answer TEXT" +
                    ")";
            stmt.execute(sql);

            // إدخال أسئلة تجريبية
            stmt.execute("INSERT INTO question (question_text, option1, option2, option3, option4, answer) " +
                    "VALUES ('ما الأداة المستخدمة لإيجاد وتصحيح الأخطاء في برامج جافا؟', 'JVM', 'JDB', 'JDK', 'JRE', 'JDB')");

            stmt.execute("INSERT INTO question (question_text, option1, option2, option3, option4, answer) " +
                    "VALUES ('ما نوع الإرجاع method hashCode() في الكلاس Object؟', 'int', 'Object', 'long', 'void', 'int')");

            System.out.println("✅ Table created and sample questions inserted!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
