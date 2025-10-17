package application;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
// هاذا الكلاس من اجل جعل مكان ادخال النص مدور
class RoundedTextField extends JTextField {
    private Shape shape;
    private int arc = 20; // نصف قطر الزوايا

    public RoundedTextField(int columns) {
        super(columns);
        setOpaque(false); // مهم لجعل الخلفية مرئية مستديرة
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, arc, arc);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, arc, arc);
    }

    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, arc, arc);
        }
        return shape.contains(x, y);
    }
}