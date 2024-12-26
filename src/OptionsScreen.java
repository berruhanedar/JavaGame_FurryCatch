import javax.swing.*;
import java.awt.*;

public class OptionsScreen extends JDialog {

    public OptionsScreen(JFrame parent) {
        super(parent, "", true);
        setUndecorated(true);
        setSize(490, 490);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(parent);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/options.png")); // Doğru resim yolunu kullandığınızdan emin olun
                Image backgroundImage = backgroundIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);

        // Sound toggle button
        JToggleButton soundToggle = new JToggleButton("ON");
        soundToggle.setBounds(278, 120, 80, 33);
        soundToggle.setBackground(new Color(0, 0, 0, 0));
        soundToggle.setOpaque(false);
        soundToggle.setForeground(new Color(239, 165, 82));
        soundToggle.setFont(new Font("Arial", Font.BOLD, 18));
        soundToggle.setFocusPainted(false);
        soundToggle.setContentAreaFilled(false);
        soundToggle.setBorderPainted(false);
        soundToggle.addActionListener(e -> {
            if (soundToggle.isSelected()) {
                soundToggle.setText("OFF");
                MusicManager.stopAllSounds();
            } else {
                soundToggle.setText("ON");
                MusicManager.playBark();
            }
        });
        backgroundPanel.add(soundToggle);

        JToggleButton musicToggle = new JToggleButton("ON");
        musicToggle.setBounds(278, 192, 80, 33);
        musicToggle.setBackground(new Color(0, 0, 0, 0));
        musicToggle.setOpaque(false);
        musicToggle.setForeground(new Color(239, 165, 82));
        musicToggle.setFont(new Font("Arial", Font.BOLD, 18));
        musicToggle.setFocusPainted(false);
        musicToggle.setContentAreaFilled(false);
        musicToggle.setBorderPainted(false);

        musicToggle.addActionListener(e -> {
            if (musicToggle.isSelected()) {
                musicToggle.setText("ON");
                if (!soundToggle.isSelected()) {
                    MusicManager.playMusic("resources/music.wav");
                }
            } else {
                musicToggle.setText("OFF");
                MusicManager.stopMusic();
            }
        });
        backgroundPanel.add(musicToggle);

        JButton menuButton = new JButton();
        menuButton.setBounds(45, 265, 86, 85);
        menuButton.setBackground(new Color(0, 0, 0, 0));
        menuButton.setOpaque(false);
        menuButton.setFocusPainted(false);
        menuButton.setContentAreaFilled(false);
        menuButton.setBorderPainted(false);
        backgroundPanel.add(menuButton);
        menuButton.addActionListener(e -> {
            new MainMenu();
            dispose();
        });

        JButton replayButton = new JButton();
        replayButton.setBounds(188, 265, 86, 85);
        replayButton.setBackground(new Color(0, 0, 0, 0));
        replayButton.setOpaque(false);
        replayButton.setFocusPainted(false);
        replayButton.setContentAreaFilled(false);
        replayButton.setBorderPainted(false);
        backgroundPanel.add(replayButton);

        JButton continueButton = new JButton();
        continueButton.setBounds(325, 265, 86, 85);
        continueButton.setBackground(new Color(0, 0, 0, 0));
        continueButton.setOpaque(false);
        continueButton.setFocusPainted(false);
        continueButton.setContentAreaFilled(false);
        continueButton.setBorderPainted(false);
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
}
