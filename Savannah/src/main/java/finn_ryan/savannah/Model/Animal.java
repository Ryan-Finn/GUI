package finn_ryan.savannah.Model;

import javafx.scene.control.Button;

public class Animal extends Button {
    private static String name;
    private static int health;
    private static int ID;

    public Animal(String n, int hp, int id) {
        super(n.charAt(0) + ":" + hp);
        name = n;
        health = hp;
        ID = id;
    }

    public static String getName() {
        return name;
    }

    public static int getHealth() {
        return health;
    }

    public void decHealth(int dec) {
        health -= dec;
        this.setText(name.charAt(0) + ":" + health);
    }

    public static int getID() {
        return ID;
    }
}
