package application;

import java.awt.*;          // مكتبة الرسومات والألوان
import javax.swing.*;      // مكتبة Swing لعمل الواجهة
import java.awt.event.*;    // مكتبة التعامل مع الأحداث (زي الضغط على الأزرار)

public class Score extends JFrame implements ActionListener { // كلاس النتيجة، ينفذ ActionListener

    // الكونستركتور يأخذ اسم المستخدم والنتيجة
    Score(String name, int score) {
        setBounds(400, 150, 750, 550);               // تحديد حجم ومكان النافذة
        getContentPane().setBackground(Color.WHITE); // تعيين خلفية بيضاء
        setLayout(null);                              // استخدام التصميم اليدوي بدون Layout Manager

        // إضافة صورة النتيجة
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/score.png")); // تحميل الصورة
        Image i2 = i1.getImage().getScaledInstance(300, 250, Image.SCALE_DEFAULT);      // تغيير حجم الصورة
        ImageIcon i3 = new ImageIcon(i2);                                                // تحويل الصورة بعد التعديل
        JLabel image = new JLabel(i3);                                                  // وضع الصورة في JLabel
        image.setBounds(0, 200, 300, 250);                                             // تحديد موقع وحجم الصورة
        add(image);                                                                     // إضافة الصورة للنافذة

        // عنوان يشكر اللاعب
        JLabel heading = new JLabel("شكراً " + name + " على لعب صفاء العقول"); // عرض رسالة شكر بالاسم
        heading.setBounds(45, 30, 700, 30);                  // موقع وحجم العنوان
        heading.setFont(new Font("Tahoma", Font.PLAIN, 26)); // نوع وحجم الخط
        add(heading);                                       // إضافة العنوان للنافذة

        // عرض النتيجة
        JLabel lblscore = new JLabel("نتيجتك: " + score);
        lblscore.setBounds(350, 200, 300, 30);             // موقع وحجم النص
        lblscore.setFont(new Font("Tahoma", Font.PLAIN, 26)); // خط وحجم النص
        add(lblscore);                                     // إضافة النص للنافذة

        // زر للعب مرة أخرى
        JButton submit = new JButton("العب مرة أخرى");
        submit.setBounds(380, 270, 120, 30);              // موقع وحجم الزر
        submit.setBackground(new Color(30, 144, 255));    // لون خلفية الزر
        submit.setForeground(Color.WHITE);                // لون نص الزر
        submit.addActionListener(this);                   // الاستماع للضغط على الزر
        add(submit);                                      // إضافة الزر للنافذة

        setVisible(true);                                 // عرض النافذة
    }

    // تنفيذ حدث الضغط على الزر
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);   // إخفاء نافذة النتيجة
        new Login();         // فتح نافذة تسجيل الدخول مرة أخرى
    }

    // لتجربة الكلاس بشكل مستقل
    public static void main(String[] args) {
        new Score("User", 0); // إنشاء نافذة نتيجة افتراضية
    }
}
