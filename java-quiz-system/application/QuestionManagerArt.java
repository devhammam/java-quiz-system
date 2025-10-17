package application;
import database.QuestionDAO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class QuestionManagerArt extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtQuestion,txtOpt1,txtOpt2,txtOpt3,txtOpt4,txtAnswer;
    private JButton btnAdd,btnDelete,btnDeleteAll,btnRefresh;

    public QuestionManagerArt(){
        setTitle("اداره الاسئلة");
        setSize(1200,750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));

        JPanel contentPane = new JPanel(new BorderLayout(10,10)) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gp = new GradientPaint(0, 0, new Color(52, 73, 94),
                        0, getHeight(), new Color(44, 62, 80));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());

                // نجوم خفيفة في الخلفية
                g2.setColor(new Color(64, 75, 99));
                g2.fillOval(30, 30, 15, 15);
                g2.fillOval(200, 100, 20, 20);
                g2.fillOval(getWidth() - 120, getHeight() - 80, 20, 20);
            }
        };
        contentPane.setBorder(new EmptyBorder(15,15,15,15));
        setContentPane(contentPane);

        // ---------------- جدول الأسئلة ----------------
        tableModel = new DefaultTableModel(
                new Object[]{"ID", "السؤال", "الخيار1", "الخيار2", "الخيار3", "الخيار4", "الإجابة"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(tableModel);
        table.setRowHeight(35);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setForeground(new Color(33, 37, 41));  // نص داكن وواضح
        table.setBackground(new Color(240, 244, 248)); // خلفية فاتحة مريحة للعين
        table.getTableHeader().setBackground(new Color(52, 152, 219)); // رأس الجدول أزرق واضح
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 17));

        // تظليل الصفوف مع hover ونص داكن
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            private int hoverRow = -1;
            {
                table.addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseMoved(MouseEvent e) {
                        int row = table.rowAtPoint(e.getPoint());
                        if (row != hoverRow) {
                            hoverRow = row;
                            table.repaint();
                        }
                    }
                });
                table.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseExited(MouseEvent e) {
                        hoverRow = -1;
                        table.repaint();
                    }
                });
            }

            @Override
            public Component getTableCellRendererComponent(JTable table,Object value,
                                                           boolean isSelected,boolean hasFocus,int row,int column){
                super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
                if(isSelected){
                    setBackground(new Color(41, 128, 185)); // أزرق داكن للصف المحدد
                    setForeground(Color.WHITE);
                } else if(row == hoverRow){
                    setBackground(new Color(224, 236, 244)); // خلفية فاتحة عند المرور
                    setForeground(new Color(33, 37, 41));    // نص داكن
                } else {
                    setBackground(row % 2 == 0 ? new Color(240, 244, 248) : new Color(210, 230, 245));
                    setForeground(new Color(33, 37, 41));    // نص داكن
                }
                setBorder(new LineBorder(new Color(52, 152, 219),1,true));
                return this;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(true);
        scrollPane.getViewport().setBackground(new Color(52, 73, 94)); // خلفية داكنة للمساحة المحيطة بالجدول
        scrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(74, 105, 173, 255), 2, true),
                "🔮 قائمة الأسئلة",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 18),
                new Color(255, 255, 255)
        ));
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // ---------------- نموذج إضافة سؤال ----------------
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(44, 62, 80, 255), 2, true),
                "✨ إضافة سؤال جديد",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 18),
                new Color(93, 173, 226)));
        formPanel.setBackground(new Color(33, 47, 61));  // أزرق غامق هادئ مناسب للنصوص البيضاء
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8,8,8,8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtQuestion = new JTextField(30);
        txtOpt1 = new JTextField(20);
        txtOpt2 = new JTextField(20);
        txtOpt3 = new JTextField(20);
        txtOpt4 = new JTextField(20);
        txtAnswer = new JTextField(20);

        JTextField[] fields = {txtQuestion, txtOpt1, txtOpt2, txtOpt3, txtOpt4, txtAnswer};
        for(JTextField field : fields) {
            field.setBackground(new Color(33, 47, 61));   // خلفية داكنة
            field.setForeground(Color.WHITE);             // نص أبيض واضح
            field.setCaretColor(new Color(41, 128, 185)); // مؤشر نص أزرق سماوي
            field.setBorder(BorderFactory.createLineBorder(new Color(52, 152, 219), 2, true)); // حدود زرقاء واضحة
            field.setFont(new Font("Arial", Font.PLAIN, 15));
        }

        String[] labels = {"السؤال:", "الخيار الأول:", "الخيار الثاني:", "الخيار الثالث:", "الخيار الرابع:", "الجواب السري:"};
        for(int i=0;i<labels.length;i++){
            gbc.gridx=0; gbc.gridy=i;
            JLabel label = new JLabel(labels[i]);
            label.setForeground(new Color(230, 220, 255));
            label.setFont(new Font("Arial", Font.BOLD, 16));
            formPanel.add(label, gbc);
            gbc.gridx=1;
            formPanel.add(fields[i], gbc);
        }

        btnAdd = createFantasyButton("إضافة سؤال", "/icons/ADDQ.png", new Color(41, 128, 185));
        gbc.gridx=0; gbc.gridy=6; gbc.gridwidth=2;
        formPanel.add(btnAdd, gbc);

        contentPane.add(formPanel, BorderLayout.EAST);

        // ---------------- أزرار الحذف والتحديث ----------------
        JPanel actionPanel = new JPanel();
        actionPanel.setBackground(new Color(30, 0, 60, 150));
        actionPanel.setBorder(new EmptyBorder(10,10,10,10));

        btnDelete = createFantasyButton("حذف سؤال محدد ", "/icons/delete.png", new Color(231, 76, 60));
        btnDeleteAll = createFantasyButton("حذف كل الاسئله ", "/icons/magic_delete_all.png", new Color(192, 57, 43));
        btnRefresh = createFantasyButton("تحديث الاسئله", "/icons/REF.png", new Color(39, 174, 96));

        actionPanel.add(btnDelete);
        actionPanel.add(btnDeleteAll);
        actionPanel.add(btnRefresh);
        contentPane.add(actionPanel, BorderLayout.SOUTH);

        // ---------------- الأحداث ----------------
        btnAdd.addActionListener(e -> addQuestion());
        btnDelete.addActionListener(e -> deleteSelectedQuestion());
        btnDeleteAll.addActionListener(e -> deleteAllQuestions());
        btnRefresh.addActionListener(e -> loadQuestions());

        loadQuestions();

        setVisible(true);
    }

    private JButton createFantasyButton(String text, String iconPath, Color baseColor){
        JButton btn = new JButton(text);
        try {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource(iconPath));
            Image scaledImage = originalIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            btn.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            btn.setIcon(null);
        }

        btn.setBackground(baseColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 15));
        btn.setBorder(new RoundedBorder(15, new Color(
                Math.min(baseColor.getRed()+50,255),
                Math.min(baseColor.getGreen()+50,255),
                Math.min(baseColor.getBlue()+50,255))));
        btn.setToolTipText(text);
        btn.setHorizontalTextPosition(SwingConstants.RIGHT);
        btn.setVerticalTextPosition(SwingConstants.CENTER);
        btn.setIconTextGap(10);

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { btn.setBackground(baseColor.brighter()); }
            @Override
            public void mouseExited(MouseEvent e) { btn.setBackground(baseColor); }
            @Override
            public void mousePressed(MouseEvent e) { btn.setLocation(btn.getX(), btn.getY()+2); }
            @Override
            public void mouseReleased(MouseEvent e) { btn.setLocation(btn.getX(), btn.getY()-2); }
        });

        return btn;
    }

    private void loadQuestions() {
        tableModel.setRowCount(0);
        List<Question> questions = QuestionDAO.getAllQuestions();
        for (Question q : questions) {
            tableModel.addRow(new Object[]{q.getId(), q.getQuestionText(), q.getOption1(), q.getOption2(),
                    q.getOption3(), q.getOption4(), q.getAnswer()});
        }
    }

    private void addQuestion() {
        String question = txtQuestion.getText();
        String opt1 = txtOpt1.getText();
        String opt2 = txtOpt2.getText();
        String opt3 = txtOpt3.getText();
        String opt4 = txtOpt4.getText();
        String answer = txtAnswer.getText();

        if(question.isEmpty() || opt1.isEmpty() || opt2.isEmpty() || opt3.isEmpty() || opt4.isEmpty() || answer.isEmpty()) {
            JOptionPane.showMessageDialog(this, " يرجى ملء جميع حقول السؤال!");
            return;
        }

        Question q = new Question(0, question,opt1,opt2,opt3,opt4,answer);
        if(QuestionDAO.insertQuestion(q)) {
            JOptionPane.showMessageDialog(this, "تم اضافة السؤال  بنجاح!");
            clearForm();
            loadQuestions();
        } else {
            JOptionPane.showMessageDialog(this, "فشل اضافة السؤال! حدث خطأ أثناء الاضافة.");
        }
    }

    private void deleteSelectedQuestion() {
        int row = table.getSelectedRow();
        if(row==-1) {
            JOptionPane.showMessageDialog(this, "اختر سؤال لحذفه أولاً!");
            return;
        }
        int id = (int) tableModel.getValueAt(row,0);
        if(QuestionDAO.deleteQuestion(id)) {
            JOptionPane.showMessageDialog(this,"تم حذف السؤال بنجاح بنجاح!");
            loadQuestions();
        }
    }

    private void deleteAllQuestions() {
        int confirm = JOptionPane.showConfirmDialog(this,"أيها المستخدم، هل أنت متأكد من حذف كل الاسئلة؟","تأكيد الحذف الشامل",JOptionPane.YES_NO_OPTION);
        if(confirm==JOptionPane.YES_OPTION) {
            if(QuestionDAO.deleteAllQuestions()) {
                JOptionPane.showMessageDialog(this,"تم حذف جميع الاسئلة !");
                loadQuestions();
            } else {
                JOptionPane.showMessageDialog(this,"فشل الحذف الشامل الشامل!");
            }
        }
    }

    private void clearForm() {
        txtQuestion.setText("");
        txtOpt1.setText("");
        txtOpt2.setText("");
        txtOpt3.setText("");
        txtOpt4.setText("");
        txtAnswer.setText("");
    }

    class RoundedBorder extends LineBorder {
        private int radius;
        private Color borderColor;

        RoundedBorder(int radius, Color borderColor){
            super(borderColor, 2, true);
            this.radius = radius;
            this.borderColor = borderColor;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius, radius, radius, radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(borderColor);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuestionManagerArt::new);
    }

}
