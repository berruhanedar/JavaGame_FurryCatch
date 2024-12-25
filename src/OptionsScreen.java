import javax.swing.*;
import java.awt.*;

public class OptionsScreen extends JDialog {

    public OptionsScreen(JFrame parent) {
        super(parent, "", true); // Başlık kısmı boş olacak şekilde ayarlandı
        setUndecorated(true); // Başlık çubuğunu kaldırmak için
        setSize(490, 490);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(parent);

        // Create a JPanel for the background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/options.png")); // Doğru resim yolunu kullandığınızdan emin olun
                Image backgroundImage = backgroundIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(null); // Manuel yerleştirme
        setContentPane(backgroundPanel); // backgroundPanel'ı içerik paneli olarak ayarla

        // Sound toggle button
        JToggleButton soundToggle = new JToggleButton("ON");
        soundToggle.setBounds(250, 80, 80, 30);
        soundToggle.setBackground(Color.GREEN);
        soundToggle.setForeground(Color.WHITE);
        soundToggle.setFocusPainted(false);
        soundToggle.addActionListener(e -> {
            if (soundToggle.isSelected()) {
                soundToggle.setText("OFF");
                soundToggle.setBackground(Color.RED);
            } else {
                soundToggle.setText("ON");
                soundToggle.setBackground(Color.GREEN);
            }
        });
        backgroundPanel.add(soundToggle);

        // Music toggle button
        JToggleButton musicToggle = new JToggleButton("OFF");
        musicToggle.setBounds(250, 140, 80, 30);
        musicToggle.setBackground(Color.RED);
        musicToggle.setForeground(Color.WHITE);
        musicToggle.setFocusPainted(false);
        musicToggle.addActionListener(e -> {
            if (musicToggle.isSelected()) {
                musicToggle.setText("ON");
                musicToggle.setBackground(Color.GREEN);
            } else {
                musicToggle.setText("OFF");
                musicToggle.setBackground(Color.RED);
            }
        });
        backgroundPanel.add(musicToggle);

        // Bottom buttons
        JButton menuButton = new JButton();
        menuButton.setBounds(50, 250, 60, 60);
        menuButton.setBackground(Color.GREEN);
        menuButton.setFocusPainted(false);
        backgroundPanel.add(menuButton);

        JButton replayButton = new JButton();
        replayButton.setBounds(160, 250, 60, 60);
        replayButton.setBackground(Color.GREEN);
        replayButton.setFocusPainted(false);
        backgroundPanel.add(replayButton);

        JButton continueButton = new JButton();
        continueButton.setBounds(270, 250, 60, 60);
        continueButton.setBackground(Color.GREEN);
        continueButton.setFocusPainted(false);
        backgroundPanel.add(continueButton);

        // Close button
        JButton closeButton = new JButton("X");
        closeButton.setBounds(400, 20, 50, 30); // İstenilen pozisyon ve boyut
        closeButton.setBackground(Color.RED);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> dispose()); // Pencereyi kapatır
        backgroundPanel.add(closeButton);

    }

}
