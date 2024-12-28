import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainMenu extends JFrame {
    private JButton newGameButton;
    private JButton optionsButton;
    private JButton highScoresButton;
    private JButton exitButton;

    public MainMenu() {
        setTitle("Furry Catch");
        setSize(800, 840);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        setLayout(null);

        // Simgeyi ayarla
        ImageIcon icon = new ImageIcon(getClass().getResource("furrycatch.png"));
        setIconImage(icon.getImage());

        ImageIcon originalImage = new ImageIcon(getClass().getResource("background.png"));
        Image scaledImage = originalImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        ImageIcon backgroundImage = new ImageIcon(scaledImage);
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());
        setContentPane(backgroundLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(null);

        newGameButton = new GradientTextButton("NEW GAME");
        styleButton(newGameButton);
        newGameButton.setBounds(195, 653, 204, 65);
        buttonPanel.add(newGameButton);

        optionsButton = new GradientTextButton("OPTIONS");
        styleButton(optionsButton);
        optionsButton.setBounds(450, 653, 204, 65);
        buttonPanel.add(optionsButton);

        highScoresButton = new GradientTextButton("HIGH SCORES");
        styleButton(highScoresButton);
        highScoresButton.setBounds(195, 736, 459, 74);
        buttonPanel.add(highScoresButton);

        exitButton = new GradientTextButton("");
        styleButton(exitButton);
        exitButton.setBounds(747, 15, 42, 43);
        exitButton.setBorder(null);
        buttonPanel.add(exitButton);

        exitButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Do you really want to exit the game?",
                    "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        Font airstrikeFont = loadFont("resources/airstrike.ttf");
        if (airstrikeFont != null) {
            setFontForButtons(airstrikeFont);
        }

        add(buttonPanel);

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
        MusicManager.stopMusic();
        MusicManager.playMusic("resources/gameMusic.wav");

        this.setVisible(false);

        // Köpek simgesini 20x20 olarak ayarla
        ImageIcon dogIconOriginal = new ImageIcon("resources/dogs_icon.png");
        Image dogIconImage = dogIconOriginal.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon dogIcon = new ImageIcon(dogIconImage);

        String dogName = (String) JOptionPane.showInputDialog(
                this,
                "Enter dog name:",
                "Dog Name Input",
                JOptionPane.PLAIN_MESSAGE,
                dogIcon,
                null,
                ""
        );

        // Köpek adı girildiyse bark.wav sesini çal
        if (dogName != null && !dogName.trim().isEmpty()) {
            playSound("resources/bark.wav");
        }

        // Kedi simgesini 20x20 olarak ayarla
        ImageIcon catIconOriginal = new ImageIcon("resources/cats_icon.png");
        Image catIconImage = catIconOriginal.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon catIcon = new ImageIcon(catIconImage);

        String catName = (String) JOptionPane.showInputDialog(
                this,
                "Enter cat name:",
                "Cat Name Input",
                JOptionPane.PLAIN_MESSAGE,
                catIcon,
                null,
                ""
        );

        // Köpek adı girildiyse bark.wav sesini çal
        if (dogName != null && !dogName.trim().isEmpty()) {
            playSound("resources/meow.wav");
        }
        // Kullanıcı boş bırakırsa veya iptal ederse
        if (dogName == null || dogName.trim().isEmpty() || catName == null || catName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "You must enter both names to proceed.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Yeni oyun sınıfına isimleri ilet
        DogClass dog = new DogClass(dogName);
        CatClass cat = new CatClass(catName);
        new GameClass(dog, cat);
    }

    // Ses dosyasını çalmak için bir yardımcı metot
    private void playSound(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            System.err.println("Error playing sound: " + e.getMessage());
        }
    }


    private void showOptionsScreen() {
        OptionsScreen optionsScreen = new OptionsScreen(this);
        optionsScreen.setLocation(195, 250);
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
            MusicManager.playMusic("resources/music.wav");
            new MainMenu();
        });
    }
}
