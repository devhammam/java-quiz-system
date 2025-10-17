package application;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//صفحة الواجهه لادخال الاسم
 public class Login extends JFrame implements ActionListener {
     RoundedButton rules,back;
     RoundedTextField tfname;


     Login(){
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         getContentPane().setBackground(Color.white);

         setLayout(null);
         ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/login.jpeg"));// location image

         JLabel image= new JLabel(i1);
         image.setBounds(0,0,600,500);// image left
         add(image);//


         JLabel heading= new JLabel(" تريڤيا");// عنوان
         heading.setBounds(750, 60, 300, 45);// الموقع والحجم
         heading.setFont(new Font("Droid Arabic Kufi", Font.BOLD, 30)); // تحديد نوع الخط ونمطه وحجمة
         heading.setForeground(new Color(30, 144, 254));// تحديد لون الخط باستخدام قيم ار جي بي
         heading.setHorizontalAlignment(SwingConstants.CENTER);// بقعه النص في الوسط
         heading.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);// جعل اتجاه النص من اليمين لانه عربي

         add(heading); // الاضافه الى النافذه كي يضهر على الواجهه



         JLabel name = new JLabel("أدخل اسمك");
         name.setBounds(750, 140, 300, 20); //
         name.setFont(new Font("Noto Naskh Arabic", Font.BOLD, 20));
         name.setForeground(new Color(30, 144, 254));
         name.setHorizontalAlignment(SwingConstants.CENTER);
         name.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); // لضبط اتجاه الكتابة من اليمين لليسار

         add(name);

         // من هنا خاص بحقل ادخال الاسم
         tfname = new RoundedTextField(20); // طول الحقل
         tfname.setBounds(735, 200, 300, 35); // موقع وحجم الحقل
         tfname.setFont(new Font("Noto Naskh Arabic", Font.PLAIN, 18));
         tfname.setForeground(new Color(30, 144, 254));
         tfname.setBackground(new Color(245, 245, 245));
         tfname.setHorizontalAlignment(SwingConstants.RIGHT);
         tfname.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         tfname.setText("أدخل اسمك");
         tfname.setForeground(Color.GRAY);


// Placeholder عند التركيز
         tfname.addFocusListener(new java.awt.event.FocusAdapter() {
             @Override
             public void focusGained(java.awt.event.FocusEvent e) {
                 if (tfname.getText().equals("أدخل اسمك")) {
                     tfname.setText("");
                     tfname.setForeground(new Color(30, 144, 254));
                 }
             }
             @Override
             public void focusLost(java.awt.event.FocusEvent e) {
                 if (tfname.getText().isEmpty()) {
                     tfname.setText("أدخل اسمك");
                     tfname.setForeground(Color.GRAY);
                 }
             }
         });

         add(tfname);
         // الى هنا



         // هنا الزر تحت الحقل
         rules = new RoundedButton("القوانين");
         rules.setBounds(735, 270, 120, 40);   // موقع وحجم الزر
         rules.setBackground(new Color(30, 144, 254)); // لون أزرق جذاب


         // هنا وضعت زر بداله الاستماع عندما يضغط الزر تضهر القوانين النص في الاسفل
         rules.addActionListener(this); // استخدم this
         add(rules);


         add(rules);
         // الى هنا


         // زر الرجوع من هنا

         back = new RoundedButton("عودة"); // تحويل النص للغة العربية
         back.setBounds(915, 270, 120, 40); // ضبط موقع وحجم الزر
         back.setBackground(new Color(30, 144, 254)); // لون أزرق جذاب

         // الكود الذي يتم تنفيذه عند الضغط على زر العودة
         back.addActionListener(this);
         add(back);


         setSize(1200,500); // عرض النافذه
         setLocation(200,150);// موقع النافذه على الشاشه
         setVisible(true); // تشغيل النافذه






         //زر الاغلاق



     }
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == rules) {
            String name = tfname.getText();
            if(name == null || name.trim().isEmpty() || name.equals("أدخل اسمك")) {
                JOptionPane.showMessageDialog(this, "الرجاء إدخال اسمك أولاً!");
            } else {
                setVisible(false);
                new Rules(name);
            }
        } else if(ae.getSource() == back) {
            setVisible(false);
        }
    }


    public static void main(String[] args) {
        new Login();
    }
}

