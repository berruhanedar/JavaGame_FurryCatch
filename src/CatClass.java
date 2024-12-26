import javax.swing.*;
import java.util.Random;

public class CatClass {
    public String name;
    public int x, y;
    public ImageIcon icon;

    public CatClass(String name) {
        this.name = name;
        this.x = 0;
        this.y = 0;
        this.icon = new ImageIcon("resources/cat_icon.png");  // Ensure correct path
    }

    public void makeSound() {
        System.out.println("Meow!");
    }

    public void move() {
        // Random movement logic
        this.x = new Random().nextInt(100);
        this.y = new Random().nextInt(100);
    }
}
