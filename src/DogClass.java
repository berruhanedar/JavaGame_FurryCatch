public class DogClass extends AnimalClass {
    public DogClass(String name) {
        super(name, "resources/dog_icon.png"); 
    }

    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }
}
