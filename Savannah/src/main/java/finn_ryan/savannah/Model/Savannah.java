package finn_ryan.savannah.Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Savannah {
    private int days = 0;
    private int filled = 0;
    private int dead = 0;
    private final ArrayList<Animal> savannah = new ArrayList<>();
    private final PropertyChangeSupport subject;

    public Savannah() { subject = new PropertyChangeSupport(this); }

    public void addObserver(PropertyChangeListener obv) {
        subject.addPropertyChangeListener(obv);
    }

    public void newDay() {
        this.days += 1;
        subject.firePropertyChange("newDay", null, this.days);
    }

    public void resize(int newSize) {
        this.days = 0;
        this.filled = 0;
        this.dead = 0;
        this.savannah.clear();
        subject.firePropertyChange("resize", null, newSize);
    }

    public void combo(String selectedItem) {
        subject.firePropertyChange("combo", null, selectedItem);
    }

    public void onClick() {
        subject.firePropertyChange("onClick", null, 1);
    }

    public int getDays() {
        return days;
    }

    public int getFilled() {
        return filled;
    }

    public int getDead() {
        return dead;
    }
}
