import javax.swing.*;
import java.util.Random;

public class DogClass {
    public String name;
    public int x, y;
    public ImageIcon icon;

    // Updated constructor to accept both name and iconPath
    public DogClass(String name) {
        this.name = name;
        this.x = 0;
        this.y = 0;
        this.icon = new ImageIcon("resources/dog_icon.png");  // Ensure correct path
    }

    public void makeSound() {
        System.out.println("Woof!");
    }

    public void move() {
        // Random movement logic
        this.x = new Random().nextInt(100);
        this.y = new Random().nextInt(100);
    }
}
