import javax.swing.*;
import java.awt.*;

class GradientTextButton extends JButton {
    public GradientTextButton(String text) {
        super(text);
        setContentAreaFilled(false); // Dolgu kaldırıldı
        setFocusPainted(false);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Font font = getFont();
        g2d.setFont(font);
        FontMetrics metrics = g2d.getFontMetrics(font);
        int textWidth = metrics.stringWidth(getText());
        int textHeight = metrics.getAscent();

        // Beyaz renkli yazılar için GradientPaint oluşturuluyor, opaklık %80 olacak
        GradientPaint textGradient = new GradientPaint(
                0, 0, new Color(255, 255, 255, 204), // %80 opaklık beyaz
                getWidth(), getHeight(), new Color(255, 255, 255, 204)
        );
        g2d.setPaint(textGradient);
        g2d.drawString(getText(), (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 4);

        // Çizgiyi kaldırıyoruz
        // g2d.setColor(new Color(200, 200, 200, 120)); // Rengi silmek istiyorsanız bu satırı kaldırın.
        // g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1); // Kenar çizgisini de kaldırıyoruz.
    }
}
