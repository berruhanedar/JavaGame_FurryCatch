import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameClass extends JFrame {
    private DogClass dog;
    private CatClass cat;
    private int roundCount;
    private Random random;

    private JLabel roundLabel;
    private JLabel dogNameLabel;
    private JLabel catNameLabel;
    private JLabel dogPositionLabel;
    private JLabel catPositionLabel;
    private JButton optionsButton;
    private JButton exitButton;

    private JPanel gamePanel;

    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                backgroundImage = new ImageIcon(getClass().getClassLoader().getResource(imagePath)).getImage();
            } catch (Exception e) {
                System.out.println("Background image not found: " + imagePath);
            }
            setLayout(null);
            ImageIcon icon = new ImageIcon(getClass().getResource("furrycatch.png"));
            setIconImage(icon.getImage());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public GameClass(DogClass dog, CatClass cat) {
        this.dog = dog;
        this.cat = cat;
        this.roundCount = 0;
        this.random = new Random();

        setUndecorated(true);
        setTitle("Animal Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(1025, 1030);

        JPanel sidePanel = new BackgroundPanel("sidepanel.png");
        sidePanel.setPreferredSize(new Dimension(200, 500));

        Font customFont;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("airstrike.ttf")).deriveFont(Font.PLAIN, 33);
        } catch (Exception e) {
            customFont = new Font("Serif", Font.PLAIN, 33); // Varsayılan font
            System.out.println("Custom font not loaded: " + e.getMessage());
        }

        roundLabel = new JLabel("Round: 0");
        roundLabel.setFont(customFont.deriveFont(22f));
        roundLabel.setForeground(new Color(124, 55, 26));
        roundLabel.setBounds(10, 320, 180, 22);
        roundLabel.setHorizontalAlignment(SwingConstants.CENTER);

        dogNameLabel = new JLabel(dog.name);
        dogNameLabel.setFont(customFont.deriveFont(24f)); // Yazı boyutunu 18 yap
        dogNameLabel.setForeground(new Color(124, 55, 26));
        dogNameLabel.setBounds(10, 465, 180, 24);
        dogNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        dogPositionLabel = new JLabel("(x: 0, y: 0)");
        dogPositionLabel.setFont(customFont.deriveFont(22f));
        dogPositionLabel.setForeground(new Color(124, 55, 26));
        dogPositionLabel.setBounds(10, 500, 180, 22);
        dogPositionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        catNameLabel = new JLabel(cat.name);
        catNameLabel.setFont(customFont.deriveFont(24f)); // Yazı boyutunu 18 yap
        catNameLabel.setForeground(new Color(124, 55, 26));
        catNameLabel.setBounds(10, 670, 180, 24);
        catNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        catPositionLabel = new JLabel("(x: 0, y: 0)");
        catPositionLabel.setFont(customFont.deriveFont(22f));
        catPositionLabel.setForeground(new Color(124, 55, 26));
        catPositionLabel.setBounds(10, 705, 180, 22);
        catPositionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        optionsButton = new JButton();
        optionsButton.setBounds(15, 149, 167, 56);
        optionsButton.setOpaque(false);
        optionsButton.setContentAreaFilled(false);
        optionsButton.setBorder(null);
        optionsButton.addActionListener(e -> {
            OptionsScreen optionsScreen = new OptionsScreen(this);
            optionsScreen.setVisible(true);
        });

        exitButton = new JButton();
        exitButton.setBounds(15, 225, 167, 56);
        exitButton.setOpaque(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorder(null);
        exitButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to exit?",
                    "Confirm Exit",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        sidePanel.add(roundLabel);
        sidePanel.add(dogNameLabel);
        sidePanel.add(catNameLabel);
        sidePanel.add(dogPositionLabel);
        sidePanel.add(catPositionLabel);
        sidePanel.add(optionsButton);
        sidePanel.add(exitButton);

        add(sidePanel, BorderLayout.EAST);

        gamePanel = new JPanel() {
            private Image backgroundImage;

            {
                try {
                    backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("gameboard.png")).getImage();
                } catch (Exception e) {
                    System.out.println("Gameboard image not found: gameboard.png");
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }

                g.setColor(new Color(76, 34, 16));
                Graphics2D g2d = (Graphics2D) g;
                g2d.setStroke(new BasicStroke(1));

                double gridSize = 8.24;
                for (int i = 0; i <= 100; i++) {
                    g2d.drawLine((int) (i * gridSize), 0, (int) (i * gridSize), gamePanel.getHeight());
                    g2d.drawLine(0, (int) (i * gridSize), gamePanel.getWidth(), (int) (i * gridSize));
                }

                Image dogImage = dog.icon.getImage();
                Image catImage = cat.icon.getImage();
                g.drawImage(dogImage, (int) (dog.x * gridSize), (int) (dog.y * gridSize), 40, 40, this);
                g.drawImage(catImage, (int) (cat.x * gridSize), (int) (cat.y * gridSize), 40, 40, this);

                g2d.setColor(Color.BLACK);
                g2d.drawRect(0, 0, (int) (100 * gridSize), (int) (100 * gridSize));
            }

        };

        gamePanel.setPreferredSize(new Dimension((int) (100 * 8.24), (int) (100 * 8.24)));
        add(gamePanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);

        dog.makeSound();
        cat.makeSound();

        startGame();
    }


    private void startGame() {
        // Start game music
        MusicManager.playMusic("resources/gameMusic.wav"); // Start the background music

        new Timer(1000, e -> {
            roundCount++;
            roundLabel.setText("Round: " + roundCount);

            // Stop the game if round count exceeds 1000
            if (roundCount >= 1000) {
                System.out.println("Game Over - Maximum rounds reached");
                JOptionPane.showMessageDialog(this, "Game Over - Maximum rounds reached", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                ((Timer) e.getSource()).stop();  // Stop the game
                MusicManager.playMusic("resources/gameover.wav");
                return;  // Exit the method to stop further processing
            }

            dog.move();
            cat.move();

            dogPositionLabel.setText("x: " + dog.x + ", y: " + dog.y);
            catPositionLabel.setText("x: " + cat.x + ", y: " + cat.y);

            // Print the dog name and position at each round
            System.out.println("Round " + roundCount + ": " + dog.name + "(dog) - Position: (x: " + dog.x + ", y: " + dog.y + ")");
            System.out.println("Round " + roundCount + ": " + cat.name + "(cat) - Position: (x: " + cat.x + ", y: " + cat.y + ")");

            if (Math.abs(dog.x - cat.x) <= 5 && Math.abs(dog.y - cat.y) <= 5) {
                System.out.println(dog.name + "(dog) and " + cat.name + "(cat) are fighting now!");
                new Thread(() -> {
                    MusicManager.stopMusic();
                    MusicManager.playMusic("resources/catdogfight.wav");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    MusicManager.stopMusic();
                    MusicManager.playMusic("resources/gameMusic.wav");
                }).start();
            }

            if (dog.x == 50 && dog.y == 50 && cat.x == 50 && cat.y == 50) {
                System.out.println("Game Over");
                JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                ((Timer) e.getSource()).stop();  // Stop the game
                MusicManager.playMusic("resources/gameover.wav");
            }

            repaint();
        }).start();
    }


    public void restartGame(DogClass dog, CatClass cat) {
        // Reset round counter
        roundCount = 0;
        roundLabel.setText("Round: " + roundCount);

        // Reset positions
        dog.x = 0;  // Assuming the initial position is (0, 0)
        dog.y = 0;
        cat.x = 0;
        cat.y = 0;

        // Update position labels
        dogPositionLabel.setText("(x: " + dog.x + ", y: " + dog.y + ")");
        catPositionLabel.setText("(x: " + cat.x + ", y: " + cat.y + ")");

        // Optionally reset game state (if needed)
        MusicManager.stopMusic();
        MusicManager.playMusic("resources/gameMusic.wav"); // Restart background music

        // Restart the game logic (e.g., moving the dog and cat)
        startGame();
    }




    public static void main(String[] args) {
        String dogName = JOptionPane.showInputDialog("Enter dog name:");
        String catName = JOptionPane.showInputDialog("Enter cat name:");

        DogClass dog = new DogClass(dogName);
        CatClass cat = new CatClass(catName);

        new GameClass(dog, cat);
    }
}
