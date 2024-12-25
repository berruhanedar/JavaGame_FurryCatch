import javax.swing.*;
import java.awt.*;


// Yeni High Scores ekranı sınıfı
class HighScoresScreen extends JFrame {
    public HighScoresScreen(JFrame parent) {
        setTitle("High Scores");
        setSize(400, 400);
        setLocationRelativeTo(parent);  // Parent penceresine göre ortalanmış

        // İçeriği basit tutmak için bir label ekleyebiliriz
        JLabel label = new JLabel("High Scores List", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 23));
        add(label);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}


