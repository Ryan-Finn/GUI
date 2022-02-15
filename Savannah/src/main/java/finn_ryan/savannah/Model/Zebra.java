package finn_ryan.savannah.Model;

public class Zebra extends Animal {
    public Zebra(int id) {
        super("Zebra", 8, id);
    }

    public void newDay() {
        decHealth(2);
    }
}
