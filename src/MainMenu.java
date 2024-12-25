import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class MainMenu extends JFrame {
    private JButton newGameButton;
    private JButton optionsButton;
    private JButton highScoresButton;  // Yeni High Scores butonu
    private JButton exitButton;

    public MainMenu() {
        setTitle("Main Menu");
        setSize(800, 840);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); // Pencere boyutunu sabitle
        setUndecorated(true); // Başlık çubuğunu kaldır
        setLayout(null); // Null Layout kullanıyoruz

        // Görseli yükleyin ve boyutlandırın
        ImageIcon originalImage = new ImageIcon(getClass().getResource("background.png"));
        Image scaledImage = originalImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        ImageIcon backgroundImage = new ImageIcon(scaledImage);
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());
        setContentPane(backgroundLabel);

        // Butonlar için panel oluşturuluyor
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(null); // Panelde de null layout kullanılıyor

        // "New Game" Butonu
        newGameButton = new GradientTextButton("NEW GAME");
        styleButton(newGameButton);
        newGameButton.setBounds(195, 653, 204, 65); // Burada x, y, width, height ile konumlandırıyoruz
        buttonPanel.add(newGameButton);

        // "Options" Butonu
        optionsButton = new GradientTextButton("OPTIONS");
        styleButton(optionsButton);
        optionsButton.setBounds(450, 653, 204, 65); // Bu butonu da istediğiniz gibi konumlandırabilirsiniz
        buttonPanel.add(optionsButton);

        // "High Scores" Butonu - Yeni buton
        highScoresButton = new GradientTextButton("HIGH SCORES");
        styleButton(highScoresButton);
        highScoresButton.setBounds(195, 736, 459, 74);  // Yüksek Skor butonunun konumu
        buttonPanel.add(highScoresButton);

        // "Exit" Butonu
        exitButton = new GradientTextButton("");
        styleButton(exitButton);
        exitButton.setBounds(747, 15, 42, 43); // Sağ üst köşe
        exitButton.setBorder(null); // Kenarlığı kaldır
        buttonPanel.add(exitButton);

// Exit butonuna aksiyon ekleme
        exitButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Do you really want to exit the game?",
                    "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0); // Programı kapat
            }
        });


        // Butonlara fontu ayarlayın
        Font airstrikeFont = loadFont("resources/airstrike.ttf");
        if (airstrikeFont != null) {
            setFontForButtons(airstrikeFont); // Apply the Airstrike font to buttons
        }

        // Paneli ekleyin
        add(buttonPanel);

        // Butonlara Aksiyon Ekleme
        newGameButton.addActionListener(e -> showProgressBarAndStartNewGame());
        optionsButton.addActionListener(e -> showOptionsScreen());
        highScoresButton.addActionListener(e -> showHighScoresScreen()); // High Scores butonuna aksiyon ekleme

        setVisible(true);
    }

    private Font loadFont(String fontPath) {
        try {
            // Load the font file
            File fontFile = new File(fontPath);
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            return font.deriveFont(30f); // Set font size (adjust as needed)
        } catch (FontFormatException | IOException e) {
            System.err.println("Error loading font: " + e.getMessage());
            return null;
        }
    }

    private void setFontForButtons(Font font) {
        // Uygulamak için yazı tipinin boyutunu küçük yapalım
        Font smallerFont = font.deriveFont(28f); // Burada 30f yerine 24f kullandık
        newGameButton.setFont(smallerFont);
        optionsButton.setFont(smallerFont);
        highScoresButton.setFont(smallerFont);
    }


    private void styleButton(JButton button) {
        button.setContentAreaFilled(false); // Dolgu kaldırıldı
        button.setFocusPainted(false); // Odak işaretini kaldır
        button.setOpaque(false); // Opaklık kaldırıldı
        button.setBorder(null); // Kenarlığı kaldırdık
    }


    private void showProgressBarAndStartNewGame() {
        JDialog progressDialog = new JDialog(this, "Loading...", true);
        progressDialog.setSize(300, 100);
        progressDialog.setLocationRelativeTo(this);
        progressDialog.setVisible(true);
    }

    private void showOptionsScreen() {
        OptionsScreen optionsScreen = new OptionsScreen(this);

        // Pencerenin konumunu ana pencereye göre ayarlama
        Point parentLocation = this.getLocationOnScreen(); // Ana pencerenin ekrandaki konumunu al
        int newX = 195; // X koordinatını 190 yapıyoruz
        int newY = 250; // Y koordinatını 500 yapıyoruz
        optionsScreen.setLocation(newX, newY); // Yeni konumu ayarla

        // Hareket ettirilemez yapmak için
        optionsScreen.setUndecorated(true); // Pencere çerçevesini kaldır
        optionsScreen.setResizable(false); // Yeniden boyutlandırmayı devre dışı bırak
        optionsScreen.setModal(true); // Dikkat çekmek için modal yap
        optionsScreen.setSize(458, 380); // OptionsScreen boyutunu 200x200 yapıyoruz

        optionsScreen.setVisible(true);
    }



    // High Scores ekranını gösteren metod
    private void showHighScoresScreen() {
        new HighScoresScreen(this).setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu());
    }
}

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
