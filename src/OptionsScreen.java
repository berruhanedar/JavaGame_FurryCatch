import javax.swing.*;
import java.awt.*;

public class OptionsScreen extends JDialog {
    private boolean isMusicPlaying = false;
    private boolean isSoundEnabled = true;

    public OptionsScreen(JFrame parent) {
        super(parent, "", true);
        setUndecorated(true);
        setSize(458, 380);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(parent);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/options.png"));
                Image backgroundImage = backgroundIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);

        JToggleButton soundToggle = new JToggleButton("ON");
        soundToggle.setBounds(278, 120, 80, 33);
        styleToggleButton(soundToggle);
        soundToggle.addActionListener(e -> {
            if (soundToggle.isSelected()) {
                soundToggle.setText("OFF");
                isSoundEnabled = false;
                MusicManager.stopAllSounds();
            } else {
                soundToggle.setText("ON");
                isSoundEnabled = true;
                MusicManager.playBark(); 
            }
        });
        backgroundPanel.add(soundToggle);

        JToggleButton musicToggle = new JToggleButton("ON");
        musicToggle.setBounds(278, 192, 80, 33);
        styleToggleButton(musicToggle);
        musicToggle.addActionListener(e -> {
            if (musicToggle.isSelected()) {
                musicToggle.setText("OFF");
                isMusicPlaying = false;
                MusicManager.stopMusic(); 
            } else {
                musicToggle.setText("ON");
                isMusicPlaying = true;
                if (isSoundEnabled) {
                    MusicManager.playGameMusic();
                }
            }
        });
        backgroundPanel.add(musicToggle);

        JButton menuButton = new JButton();
        menuButton.setBounds(45, 265, 86, 85);
        styleButton(menuButton);
        menuButton.addActionListener(e -> {
            dispose();
            parent.dispose();

            MainClass mainMenu = new MainClass();
            mainMenu.setVisible(true);
        });
        backgroundPanel.add(menuButton);

        JButton restartButton = new JButton();
        restartButton.setBounds(188, 265, 86, 85);
        styleButton(restartButton);
        restartButton.addActionListener(e -> {
            System.out.println("Restart button clicked.");
            if (parent instanceof MainClass mainMenu) {
                if (!mainMenu.isGameStarted) {
                    mainMenu.openGameScreen();
                } else {
                    GameClass newGameClass = new GameClass(mainMenu.currentDog, mainMenu.currentCat);
                    newGameClass.restartGame(mainMenu.currentDog, mainMenu.currentCat); // Reset game
                    dispose(); 
                }
            }
        });
        backgroundPanel.add(restartButton);

        JButton continueButton = new JButton();
        continueButton.setBounds(325, 265, 86, 85);
        styleButton(continueButton);
        continueButton.addActionListener(e -> dispose());
        backgroundPanel.add(continueButton);

        JButton closeButton = new JButton();
        closeButton.setBounds(385, 25, 50, 30);
        closeButton.setBackground(Color.RED);
        closeButton.setFocusPainted(false);
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.addActionListener(e -> dispose());
        backgroundPanel.add(closeButton);
    }

    private void styleToggleButton(JToggleButton toggleButton) {
        toggleButton.setBackground(new Color(0, 0, 0, 0));
        toggleButton.setOpaque(false);
        toggleButton.setForeground(new Color(239, 165, 82));
        toggleButton.setFont(new Font("Arial", Font.BOLD, 18));
        toggleButton.setFocusPainted(false);
        toggleButton.setContentAreaFilled(false);
        toggleButton.setBorderPainted(false);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(0, 0, 0, 0));
        button.setOpaque(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
    }
}
