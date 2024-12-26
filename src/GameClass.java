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

    private JPanel gamePanel;

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

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBackground(Color.GRAY);
        sidePanel.setPreferredSize(new Dimension(200, 0));

        roundLabel = new JLabel("Round: 0");
        dogNameLabel = new JLabel("Dog: " + dog.name);
        catNameLabel = new JLabel("Cat: " + cat.name);
        optionsButton = new JButton("Options");
        optionsButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Options clicked"));

        sidePanel.add(roundLabel);
        sidePanel.add(dogNameLabel);
        sidePanel.add(catNameLabel);
        sidePanel.add(optionsButton);

        dogPositionLabel = new JLabel("Dog Position: (x: 0, y: 0)");
        catPositionLabel = new JLabel("Cat Position: (x: 0, y: 0)");
        sidePanel.add(dogPositionLabel);
        sidePanel.add(catPositionLabel);

        add(sidePanel, BorderLayout.EAST);

        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.setColor(Color.LIGHT_GRAY);
                double gridSize = 8.24;
                for (int i = 0; i <= 100; i++) {
                    g.drawLine((int)(i * gridSize), 0, (int)(i * gridSize), gamePanel.getHeight());  // Dikey çizgiler
                    g.drawLine(0, (int)(i * gridSize), gamePanel.getWidth(), (int)(i * gridSize));  // Yatay çizgiler
                }

                Image dogImage = dog.icon.getImage();
                Image catImage = cat.icon.getImage();
                g.drawImage(dogImage, (int)(dog.x * gridSize), (int)(dog.y * gridSize), 8, 8, this);
                g.drawImage(catImage, (int)(cat.x * gridSize), (int)(cat.y * gridSize), 8, 8, this);

                g.setColor(Color.BLACK);
                g.drawRect(0, 0, (int)(100 * gridSize), (int)(100 * gridSize)); // 100x100 kutucuk sınırı
            }
        };

        gamePanel.setPreferredSize(new Dimension((int)(100 * 8.24), (int)(100 * 8.24))); // Oyun paneli boyutunu 100x100 kutucuk olarak ayarladık
        add(gamePanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);

        dog.makeSound();
        cat.makeSound();

        startGame();
    }

    private void startGame() {
        new Timer(1000, e -> {
            roundCount++;
            roundLabel.setText("Round: " + roundCount);
            System.out.println("Round " + roundCount);

            dog.move();
            cat.move();

            dogPositionLabel.setText("Dog Position: (x: " + dog.x + ", y: " + dog.y + ")");
            catPositionLabel.setText("Cat Position: (x: " + cat.x + ", y: " + cat.y + ")");

            if (Math.abs(dog.x - cat.x) <= 5 && Math.abs(dog.y - cat.y) <= 5) {
                System.out.println(dog.name + " and " + cat.name + " are fighting now");
            }

            if (dog.x == 50 && dog.y == 50 && cat.x == 50 && cat.y == 50) {
                System.out.println("Game Over");
                ((Timer) e.getSource()).stop();
            }

            repaint();
        }).start();
    }

    public static void main(String[] args) {
        String dogName = JOptionPane.showInputDialog("Enter dog name:");
        String catName = JOptionPane.showInputDialog("Enter cat name:");

        DogClass dog = new DogClass(dogName);
        CatClass cat = new CatClass(catName);

        new GameClass(dog, cat);
    }
}
