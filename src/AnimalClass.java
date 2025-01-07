import javax.swing.*;
import java.util.Random;

public class AnimalClass {
    protected String name;
    protected int x, y;
    protected ImageIcon icon;

    public AnimalClass(String name, String iconPath) {
        this.name = name;
        this.x = 50; 
        this.y = 50;
        this.icon = new ImageIcon(iconPath);
    }

    public void move() {
        this.x = new Random().nextInt(101); 
        this.y = new Random().nextInt(101); 
    }

    public String getPosition() {
        return "(" + x + ", " + y + ")";
    }

    public void makeSound() {
        System.out.println("Some generic animal sound");
    }
}
 
