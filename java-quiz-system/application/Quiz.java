package application;

import database.QuestionDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Quiz extends JFrame implements ActionListener {

    List<Question> questions;
    String useranswers[][]; // إجابات المستخدم

    JLabel qno, question;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup groupoptions;
    JButton next, submit, lifeline;

    public static int timer = 15;
    public static int ans_given = 0;
    public static int count = 0;
    public static int score = 0;

    String name;
    Timer t; // Swing Timer للتحكم بالمؤقت

    Quiz(String name) {
        this.name = name;
        setBounds(50, 0, 1440, 850);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // تحميل الأسئلة من قاعدة البيانات
        QuestionDAO dao = new QuestionDAO();
        questions = QuestionDAO.getAllQuestions();
        useranswers = new String[questions.size()][1];

        // إعداد واجهة المستخدم
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/quiz.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1440, 392);
        add(image);

        qno = new JLabel();
        qno.setBounds(100, 450, 50, 30);
        qno.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(qno);

        question = new JLabel();
        question.setBounds(150, 450, 900, 30);
        question.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(question);

        opt1 = new JRadioButton();
        opt2 = new JRadioButton();
        opt3 = new JRadioButton();
        opt4 = new JRadioButton();

        JRadioButton[] opts = {opt1, opt2, opt3, opt4};
        int y = 520;
        for (JRadioButton opt : opts) {
            opt.setBounds(170, y, 700, 30);
            opt.setBackground(Color.WHITE);
            opt.setFont(new Font("Dialog", Font.PLAIN, 20));
            add(opt);
            y += 40;
        }

        groupoptions = new ButtonGroup();
        groupoptions.add(opt1);
        groupoptions.add(opt2);
        groupoptions.add(opt3);
        groupoptions.add(opt4);

        next = new JButton("التالي");
        next.setBounds(1100, 550, 200, 40);
        next.setFont(new Font("Tahoma", Font.PLAIN, 22));
        next.setBackground(new Color(30, 144, 255));
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        add(next);

        lifeline = new JButton("50-50 مساعدة");
        lifeline.setBounds(1100, 630, 200, 40);
        lifeline.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lifeline.setBackground(new Color(30, 144, 255));
        lifeline.setForeground(Color.WHITE);
        lifeline.addActionListener(this);
        add(lifeline);

        submit = new JButton("إرسال");
        submit.setBounds(1100, 710, 200, 40);
        submit.setFont(new Font("Tahoma", Font.PLAIN, 22));
        submit.setBackground(new Color(30, 144, 255));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setEnabled(false);
        add(submit);

        start(count); // عرض أول سؤال
        setVisible(true);

        // إعداد المؤقت لكل سؤال
        t = new Timer(1000, e -> {
            timer--;
            repaint();
            if (timer <= 0) {
                ans_given = 1;
                saveAnswer();
                nextQuestionOrSubmit();
            }
        });
        t.start();
    }

    private void saveAnswer() {
        useranswers[count][0] = groupoptions.getSelection() != null ?
                groupoptions.getSelection().getActionCommand() : "";
    }

    private void nextQuestionOrSubmit() {
        if (count < questions.size() - 1) {
            count++;
            start(count);
            opt1.setEnabled(true);
            opt2.setEnabled(true);
            opt3.setEnabled(true);
            opt4.setEnabled(true);
            timer = 15;
        } else {
            next.setEnabled(false);
            submit.setEnabled(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            saveAnswer();
            nextQuestionOrSubmit();
        } else if (ae.getSource() == lifeline) {
            // تعطيل خيارين عشوائياً مع الحفاظ على الإجابة الصحيحة
            Question q = questions.get(count);
            JRadioButton[] opts = {opt1, opt2, opt3, opt4};
            int disabled = 0;
            for (JRadioButton opt : opts) {
                if (!opt.getText().equals(q.getAnswer()) && disabled < 2) {
                    opt.setEnabled(false);
                    disabled++;
                }
            }
            lifeline.setEnabled(false);
        } else if (ae.getSource() == submit) {
            saveAnswer();
            score = 0;
            for (int i = 0; i < useranswers.length; i++) {
                if (useranswers[i][0] != null &&
                        useranswers[i][0].equals(questions.get(i).getAnswer())) {
                    score += 10;
                }
            }
            t.stop(); // إيقاف المؤقت
            setVisible(false);
            new Score(name, score);

            // إعادة تهيئة الاختبار إذا أردنا إعادة تشغيله لاحقاً
            count = 0;
            score = 0;
            timer = 15;
            for (int i = 0; i < useranswers.length; i++) useranswers[i][0] = null;
        }
    }

    public void start(int count) {
        Question q = questions.get(count);
        qno.setText((count + 1) + ". ");
        question.setText(q.getQuestionText());

        opt1.setText(q.getOption1());
        opt1.setActionCommand(q.getOption1());

        opt2.setText(q.getOption2());
        opt2.setActionCommand(q.getOption2());

        opt3.setText(q.getOption3());
        opt3.setActionCommand(q.getOption3());

        opt4.setText(q.getOption4());
        opt4.setActionCommand(q.getOption4());

        groupoptions.clearSelection();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        g.setFont(new Font("Tahoma", Font.BOLD, 25));
        g.drawString("الوقت المتبقي: " + timer + " ثواني", 1100, 500);
    }

    public static void main(String[] args) {
        new Quiz("User");
    }
}
