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
    private JButton howToPlayButton;
    private JButton exitButton;
    public boolean isGameStarted = false;
    public DogClass currentDog;
    public CatClass currentCat;

    public MainMenu() {
        setTitle("Furry Catch");
        setSize(800, 840);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        setLayout(null);

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

        // Changed to "How to Play"
        howToPlayButton = new GradientTextButton("HOW TO PLAY");
        styleButton(howToPlayButton);
        howToPlayButton.setBounds(195, 736, 459, 74);
        buttonPanel.add(howToPlayButton);

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

        howToPlayButton.addActionListener(e -> showHowToPlayDialog());

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
        howToPlayButton.setFont(smallerFont);
    }

    private void styleButton(JButton button) {
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setBorder(null);
    }

    public void openGameScreen() {
        isGameStarted = true;

        MusicManager.stopMusic();
        MusicManager.playMusic("resources/gameMusic.wav");

        this.setVisible(false);

        ImageIcon dogIconOriginal = new ImageIcon("resources/dogs_icon.png");
        Image dogIconImage = dogIconOriginal.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon dogIcon = new ImageIcon(dogIconImage);

        String dogName = null;

        // Köpek adı girişi
        while (dogName == null || dogName.trim().isEmpty()) {
            dogName = (String) JOptionPane.showInputDialog(
                    this,
                    "Enter dog name:",
                    "DOG",
                    JOptionPane.PLAIN_MESSAGE,
                    dogIcon,
                    null,
                    ""
            );

            if (dogName == null) {
                // Kullanıcı Back'a basarsa ana menüye dönüyoruz
                goToMainMenu();
                return;
            }

            if (dogName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Dog name cannot be empty!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Köpek havlama sesi
        playSound("resources/bark.wav");
        System.out.println(dogName + " is barked!");

        ImageIcon catIconOriginal = new ImageIcon("resources/cats_icon.png");
        Image catIconImage = catIconOriginal.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon catIcon = new ImageIcon(catIconImage);

        String catName = null;

        // Kedi adı girişi
        while (catName == null || catName.trim().isEmpty()) {
            catName = (String) JOptionPane.showInputDialog(
                    this,
                    "Enter cat name:",
                    "CAT",
                    JOptionPane.PLAIN_MESSAGE,
                    catIcon,
                    null,
                    ""
            );

            if (catName == null) {
                // Kullanıcı Back'a basarsa ana menüye dönüyoruz
                goToMainMenu();
                return;
            }

            if (catName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Cat name cannot be empty!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Kedi miyavlama sesi
        playSound("resources/meow.wav");
        System.out.println(catName + " is meowed!");

        // Oyun başlatılacak
        currentDog = new DogClass(dogName);
        currentCat = new CatClass(catName);
        new GameClass(currentDog, currentCat);
    }


    private void goToMainMenu() {
        // Ana menüye dönme işlemi
        JOptionPane.showMessageDialog(this, "Returning to main menu...");
        System.out.println("Returning to main menu...");
        // Ana menüye geri dönmek için yeni bir MainMenu örneği oluşturabiliriz:
        new MainMenu();
        this.dispose();  // Mevcut pencereyi kapat
    }

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

    private void showHowToPlayDialog() {
        String gameInstructions = "How to Play:\n\n"
                + "1. Click 'NEW GAME' to start a new game.\n"
                + "2. Enter a dog and cat name when prompted.\n"
                + "3. The goal is to catch the cat with the dog.\n"
                + "4. The dog and cat will move randomly on the stage.\n"
                + "5. If the dog and cat are 5 units away from each other, a fight will occur.\n"
                + "6. If the dog and cat meet at the center of the stage (x:50, y:50), the game ends.\n"
                + "7. When the game ends, you will see the message 'Game Over'.\n\n"
                + "Enjoy the game!";


        JOptionPane.showMessageDialog(this, gameInstructions, "How to Play", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MusicManager.playMusic("resources/music.wav");
            new MainMenu();
        });
    }
}
