import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class MainMenu extends JFrame {
    private JButton newGameButton;
    private JButton optionsButton;
    private JButton highScoresButton;
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
        newGameButton.setBounds(195, 653, 204, 65);
        buttonPanel.add(newGameButton);

        // "Options" Butonu
        optionsButton = new GradientTextButton("OPTIONS");
        styleButton(optionsButton);
        optionsButton.setBounds(450, 653, 204, 65);
        buttonPanel.add(optionsButton);

        // "High Scores" Butonu
        highScoresButton = new GradientTextButton("HIGH SCORES");
        styleButton(highScoresButton);
        highScoresButton.setBounds(195, 736, 459, 74);
        buttonPanel.add(highScoresButton);

        // "Exit" Butonu
        exitButton = new GradientTextButton("");
        styleButton(exitButton);
        exitButton.setBounds(747, 15, 42, 43);
        exitButton.setBorder(null);
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
        newGameButton.addActionListener(e -> openGameScreen());
        optionsButton.addActionListener(e -> showOptionsScreen());
        highScoresButton.addActionListener(e -> showHighScoresScreen());

        setVisible(true);
    }

    private Font loadFont(String fontPath) {
        try {
            File fontFile = new File(fontPath);
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            return font.deriveFont(30f);
        } catch (FontFormatException | IOException e) {
            System.err.println("Error loading font: " + e.getMessage());
            return null;
        }
    }

    private void setFontForButtons(Font font) {
        Font smallerFont = font.deriveFont(28f);
        newGameButton.setFont(smallerFont);
        optionsButton.setFont(smallerFont);
        highScoresButton.setFont(smallerFont);
    }

    private void styleButton(JButton button) {
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setBorder(null);
    }

    private void openGameScreen() {
        // Stop the current music
        MusicManager.stopMusic();  // No file path needed

        // Start the new music for the game screen
        MusicManager.playMusic("resources/gameSound.wav");

        // Hide the main menu
        this.setVisible(false);

        // Open the game screen
        new GameClass();
    }




    private void showOptionsScreen() {
        OptionsScreen optionsScreen = new OptionsScreen(this);
        Point parentLocation = this.getLocationOnScreen();
        int newX = 195;
        int newY = 250;
        optionsScreen.setLocation(newX, newY);
        optionsScreen.setUndecorated(true);
        optionsScreen.setResizable(false);
        optionsScreen.setModal(true);
        optionsScreen.setSize(458, 380);
        optionsScreen.setVisible(true);
    }

    private void showHighScoresScreen() {
        new HighScoresScreen(this).setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MusicManager.playMusic("resources/sound.wav");
            new MainMenu();
        });
    }
}
