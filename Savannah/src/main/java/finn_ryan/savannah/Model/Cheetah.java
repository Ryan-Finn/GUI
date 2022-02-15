package finn_ryan.savannah.Model;

public class Cheetah extends Animal {
    public Cheetah(int id) {
        super("Cheetah", 10, id);
    }

    public void newDay() {
        decHealth(1);
    }
}
