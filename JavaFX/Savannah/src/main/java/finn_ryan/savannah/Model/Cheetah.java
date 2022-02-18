package finn_ryan.savannah.Model;

public class Cheetah extends Animal {
    private static final String color1 = "#FFE44D";
    private static final String color2 = "#FFD700";
    private static final String file = "src/main/resources/finn_ryan/savannah/cheetah.png";

    public Cheetah(int id) {
        super("Cheetah", 10, id, color1, color2, file, 2.0);
    }

    @Override
    public void newDay() {
        decHealth(1);
    }
}
