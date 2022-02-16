package finn_ryan.savannah.View;

import finn_ryan.savannah.Model.Animal;
import finn_ryan.savannah.Model.Cheetah;
import finn_ryan.savannah.Model.None;
import finn_ryan.savannah.Model.Zebra;
import javafx.collections.ObservableList;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.util.Objects;

public class SavannahView {
    private final GridPane view;
    private static int r, c;
    private static final double INF = Double.POSITIVE_INFINITY;

    public SavannahView() {
        view = new GridPane();
        init(3);
    }

    private void init(int sz) {
        r = sz; c = sz;
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
                None button = new None(i + j * sz);
                button.setMaxSize(INF, INF);
                view.add(button, i, j);
            }
        }
    }

    public void resize(int sz) {
        view.getChildren().clear();
        view.getRowConstraints().clear();
        view.getColumnConstraints().clear();
        init(sz);
    }

    public void add(int id, String animal) {
        Animal button;
        if (Objects.equals(animal, "Zebra")) {
            button = new Zebra(id);
        } else if (Objects.equals(animal, "Cheetah")) {
            button = new Cheetah(id);
        } else {
            button = new None(id);
        }

        button.setMaxSize(INF, INF);
        view.getChildren().remove(view.getChildren().get(id));
        view.add(button, (id - (id % c)) / c, id % c);
    }

    public GridPane getView() {
        return view;
    }

    public static int getRows() {
        return r;
    }

    public static int getCols() {
        return c;
    }
}
