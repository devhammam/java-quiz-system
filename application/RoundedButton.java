package application;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
// هاذا الكلاس من اجل الازرار تصبح مدوره
class RoundedButton extends JButton {
    private int arc = 30; // نصف قطر الزوايا

    public RoundedButton(String text) {
        super(text);
        setFocusPainted(false);  // إزالة الإطار الأزرق عند التركيز
        setContentAreaFilled(false); // منع الملء الافتراضي
        setForeground(Color.WHITE);
        setFont(new Font("Noto Naskh Arabic", Font.BOLD, 16));
        setHorizontalAlignment(SwingConstants.CENTER);
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); // دعم RTL
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // رسم خلفية الزر بزوايا مستديرة
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground().darker());
        g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, arc, arc);
        g2.dispose();
    }
}
