package finn_ryan.savannah.View;

import finn_ryan.savannah.Model.Animal;
import javafx.scene.control.Button;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AnimalView extends Button implements PropertyChangeListener {
    public AnimalView(Animal model) {
        model.addObserver(this);
    }

    public void propertyChange(PropertyChangeEvent event) {

    }
}
