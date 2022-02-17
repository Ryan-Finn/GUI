package finn_ryan.savannah.Model;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

public class Animal extends Button {
    private static final double INF = Double.POSITIVE_INFINITY;
    private static String name;
    private int health;
    private final int ID;
    private final PropertyChangeSupport subject;

    public Animal(String n, int hp, int id, String c1, String c2, String file, double div) {
        super(n.charAt(0) + ":" + hp);

        this.setMaxSize(INF, INF);

        this.setStyle("-fx-background-color:" +
                "radial-gradient(center 50% -40%, radius 200%, " + c1 + " 49%, " + c2 + " 50%);" +
                "-fx-border-color: white; -fx-border-radius: 10;" +
                "-fx-background-radius: 10;" +
                "-fx-background-insets: 1;"
        );

        if (!Objects.equals(file, "")) {
            ImageView image = open(file);
            image.fitWidthProperty().bind(this.widthProperty().divide(div));
            image.fitHeightProperty().bind(this.heightProperty().divide(div));
            image.setPreserveRatio(true);
            this.setGraphic(image);
            this.setContentDisplay(ContentDisplay.BOTTOM);
        }

        name = n;
        health = hp;
        ID = id;
        subject = new PropertyChangeSupport(this);
    }

    private ImageView open(String file) {
        try {
            return new ImageView(new Image(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new ImageView();
    }

    public void addObserver(PropertyChangeListener obv) {
        subject.addPropertyChangeListener(obv);
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void decHealth(int dec) {
        health -= dec;
        this.setText(name.charAt(0) + ":" + health);
    }

    public int getID() {
        return ID;
    }

    public void newDay() {}
}
