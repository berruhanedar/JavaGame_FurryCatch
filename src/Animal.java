public class Animal {
    protected String name;
    protected int x, y;

    public Animal(String name) {
        this.name = name;
        this.x = 50; // Başlangıç pozisyonu (orta nokta)
        this.y = 50;
    }

    public void moveRandomly() {
        this.x = (int) (Math.random() * 101); // 0-100 arası rastgele pozisyon
        this.y = (int) (Math.random() * 101);
    }

    public String getPosition() {
        return "(" + x + ", " + y + ")";
    }
}
