import javax.swing.*;
import java.awt.*;

public class GameClass extends JFrame {

    public GameClass() {
        setTitle("New Game");
        setSize(800, 840); // Oyun ekranının boyutu
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Bu pencere kapatıldığında sadece bu pencere kapanır
        setResizable(false); // Pencere boyutunu sabitle
        setLayout(new BorderLayout()); // BorderLayout kullanıyoruz

        // Ekrana bir label ekleyebiliriz
        JLabel label = new JLabel("Game is starting...", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24)); // Yazı tipi ve boyutu
        add(label, BorderLayout.CENTER); // Label'ı ekliyoruz

        setLocationRelativeTo(null); // Pencereyi ekranın ortasında aç

        setVisible(true); // Pencereyi görünür yapıyoruz
    }
}
