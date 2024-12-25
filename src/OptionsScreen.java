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
        soundToggle.setBounds(278, 120, 80, 33); // Yeni koordinat ve boyut
        soundToggle.setBackground(new Color(0, 0, 0, 0)); // Şeffaf arka plan
        soundToggle.setOpaque(false); // Opaklık kaldırıldı, şeffaf oldu
        soundToggle.setForeground(Color.WHITE);
        soundToggle.setFocusPainted(false); // Fokus çizgisi olmasın
        soundToggle.setContentAreaFilled(false); // İç alanı şeffaf yap
        soundToggle.setBorderPainted(false); // Çerçeveyi kaldır
        soundToggle.addActionListener(e -> {
            if (soundToggle.isSelected()) {
                soundToggle.setText("OFF");
            } else {
                soundToggle.setText("ON");
            }
            // Arka planın şeffaf olduğundan emin ol
            soundToggle.setBackground(new Color(0, 0, 0, 0));
        });
        backgroundPanel.add(soundToggle);

// Music toggle button
        JToggleButton musicToggle = new JToggleButton("OFF");
        musicToggle.setBounds(278, 192, 80, 33); // Yeni koordinat ve boyut
        musicToggle.setBackground(new Color(0, 0, 0, 0)); // Şeffaf arka plan
        musicToggle.setOpaque(false); // Opaklık kaldırıldı, şeffaf oldu
        musicToggle.setForeground(Color.WHITE);
        musicToggle.setFocusPainted(false); // Fokus çizgisi olmasın
        musicToggle.setContentAreaFilled(false); // İç alanı şeffaf yap
        musicToggle.setBorderPainted(false); // Çerçeveyi kaldır
        musicToggle.addActionListener(e -> {
            if (musicToggle.isSelected()) {
                musicToggle.setText("ON");
            } else {
                musicToggle.setText("OFF");
            }
            // Arka planın şeffaf olduğundan emin ol
            musicToggle.setBackground(new Color(0, 0, 0, 0));
        });
        backgroundPanel.add(musicToggle);

// Menu button
        JButton menuButton = new JButton();
        menuButton.setBounds(45, 265, 86, 85); // Yeni koordinat ve boyut
        menuButton.setBackground(new Color(0, 0, 0, 0)); // Şeffaf arka plan
        menuButton.setOpaque(false); // Opaklık kaldırıldı, şeffaf oldu
        menuButton.setFocusPainted(false); // Fokus çizgisi olmasın
        menuButton.setContentAreaFilled(false); // İç alanı şeffaf yap
        menuButton.setBorderPainted(false); // Çerçeveyi kaldır
        backgroundPanel.add(menuButton);

        // Replay button
        JButton replayButton = new JButton();
        replayButton.setBounds(188, 265, 86, 85); // Yeni koordinat ve boyut
        replayButton.setBackground(new Color(0, 0, 0, 0)); // Şeffaf arka plan
        replayButton.setOpaque(false); // Opaklık kaldırıldı, şeffaf oldu
        replayButton.setFocusPainted(false); // Fokus çizgisi olmasın
        replayButton.setContentAreaFilled(false); // İç alanı şeffaf yap
        replayButton.setBorderPainted(false); // Çerçeveyi kaldır
        backgroundPanel.add(replayButton);

        // Continue button
        JButton continueButton = new JButton();
        continueButton.setBounds(325, 265, 86, 85); // Yeni koordinat ve boyut
        continueButton.setBackground(new Color(0, 0, 0, 0)); // Şeffaf arka plan
        continueButton.setOpaque(false); // Opaklık kaldırıldı, şeffaf oldu
        continueButton.setFocusPainted(false); // Fokus çizgisi olmasın
        continueButton.setContentAreaFilled(false); // İç alanı şeffaf yap
        continueButton.setBorderPainted(false); // Çerçeveyi kaldır
        backgroundPanel.add(continueButton);
        // Close button without text and border
        JButton closeButton = new JButton();
        closeButton.setBounds(385, 25, 50, 30); // Yeni koordinat ve boyut
        closeButton.setBackground(Color.RED); // Kapatma butonunun arka plan rengini kırmızı yapalım
        closeButton.setFocusPainted(false);
        closeButton.setBorderPainted(false); // Çerçeveyi kaldır
        closeButton.setContentAreaFilled(false); // İç alanı doldurma
        closeButton.addActionListener(e -> dispose()); // Pencereyi kapatır
        backgroundPanel.add(closeButton);
    }
}


