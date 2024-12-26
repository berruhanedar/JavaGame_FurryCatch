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
        setTitle("Main Menu");
        setSize(800, 840);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        setLayout(null);

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

        MusicManager.playMusic("resources/gameSound.wav");

        this.setVisible(false);

        String dogName = JOptionPane.showInputDialog("Enter dog name:");
        String catName = JOptionPane.showInputDialog("Enter cat name:");

        String dogIconPath = "resources/dog_icon.png";
        String catIconPath = "resources/cat_icon.png";

        DogClass dog = new DogClass(dogName);
        CatClass cat = new CatClass(catName);

        new GameClass(dog, cat);
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