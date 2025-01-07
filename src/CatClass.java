import javax.swing.*;
import java.util.Random;

public class CatClass extends AnimalClass {
    public CatClass(String name) {
        super(name, "resources/cat_icon.png");
    }

    @Override
    public void makeSound() {
        System.out.println("Meow!");
    }
}
 
