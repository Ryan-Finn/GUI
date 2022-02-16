package finn_ryan.savannah.View;

import finn_ryan.savannah.Model.*;
import javafx.collections.ObservableList;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class SavannahView implements PropertyChangeListener {
    private final Savannah model;
    private final GridPane view;
    private static final double INF = Double.POSITIVE_INFINITY;

    public SavannahView(Savannah model) {
        this.model = model;
        view = new GridPane();
        init(3);
    }

    private void init(int sz) {
        ObservableList<RowConstraints> rc = view.getRowConstraints();
        ObservableList<ColumnConstraints> cc = view.getColumnConstraints();

        for (int i = 0; i < sz; i++) {
            RowConstraints row = new RowConstraints();
            row.setVgrow(Priority.ALWAYS);
            rc.add(row);

            ColumnConstraints col = new ColumnConstraints();
            col.setHgrow(Priority.ALWAYS);
            cc.add(col);

            for (int j = 0; j < sz; j++) {
                None button = new None(i * sz + j);
                button.setMaxSize(INF, INF);
                view.add(button, j, i);
                model.addAnimal(button);
            }
        }
    }

    public void resize(int sz) {
        view.getChildren().clear();
        view.getRowConstraints().clear();
        view.getColumnConstraints().clear();
        init(sz);
    }

    public void add(Animal replace, String animal) {
        Animal button;
        int id = replace.getID();
        if (Objects.equals(animal, "Zebra")) {
            button = new Zebra(id);
        } else if (Objects.equals(animal, "Cheetah")) {
            button = new Cheetah(id);
        } else {
            button = new None(id);
        }
        button.setMaxSize(INF, INF);
        view.getChildren().remove(replace);
        view.getChildren().add(id, button);
        model.addAnimal(button);
    }

    public GridPane getView() {
        return view;
    }

    public void propertyChange(PropertyChangeEvent event) {}
}
