package application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Rules extends JFrame implements ActionListener {

    String name;
    RoundedButton start, back;
    JTextArea rulesText;

    Rules(String name) {
        this.name = name;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // عنوان عربي
        JLabel heading = new JLabel("مرحبًا " + name + " في اختبار  العقول"); // رساله الترحيب في البرنامج معا القواعد
        heading.setBounds(50, 20, 700, 30);
        heading.setFont(new Font("Droid Arabic Kufi", Font.BOLD, 28));
        heading.setForeground(new Color(30, 144, 254));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        add(heading);

        // منطقة النص مع القواعد
        rulesText = new JTextArea();
        rulesText.setBounds(50, 90, 700, 350);
        rulesText.setFont(new Font("Noto Naskh Arabic", Font.PLAIN, 16));
        rulesText.setText(
                "1. يجب الإجابة بدقة ووضوح.\n" +
                        "2. لا تبتسم بدون داعٍ، فقد يشتت الآخرين.\n" +
                        "3. جميع الأسئلة إجبارية.\n" +
                        "4. البكاء مسموح ولكن بهدوء.\n" +
                        "5. الحكيم يجيب فقط، لا تسأل بلا داعٍ.\n" +
                        "6. لا تقلق إذا كان صديقك يجيب أكثر.\n" +
                        "7. استعد جيدًا، هذا الاختبار ليس للهواة.\n" +
                        "8. حظًا موفقًا!"
        );
        rulesText.setEditable(false);
        rulesText.setLineWrap(true);
        rulesText.setWrapStyleWord(true);
        rulesText.setBackground(Color.WHITE);
        rulesText.setForeground(new Color(30, 144, 254));
        rulesText.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        add(rulesText);

        // زر العودة
        back = new RoundedButton("عودة");
        back.setBounds(250, 500, 120, 40);
        back.setBackground(new Color(30, 144, 254));
        back.addActionListener(this);
        add(back);

        // زر البدء
        start = new RoundedButton("ابدأ");
        start.setBounds(400, 500, 120, 40);
        start.setBackground(new Color(30, 144, 254));
        start.addActionListener(this);
        add(start);

        setSize(800, 650);
        setLocation(350, 100);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == start) {
            setVisible(false);
            new Quiz(name); // افترض نافذة Quiz جاهزة
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Login(); // العودة للنافذة الرئيسية
        }
    }

    public static void main(String[] args) {
        new Rules("User");
    }
}
