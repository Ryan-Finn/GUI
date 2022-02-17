package finn_ryan.savannah.Model;

public class Zebra extends Animal {
    private static final String color1 = "#C5E8ED";
    private static final String color2 = "#B0E0E6";
    private static final String file = "src/main/resources/finn_ryan/savannah/zebra.png";

    public Zebra(int id) {
        super("Zebra", 10, id, color1, color2, file, 2.0);
    }

    @Override
    public void newDay() {
        decHealth(2);
    }
}
