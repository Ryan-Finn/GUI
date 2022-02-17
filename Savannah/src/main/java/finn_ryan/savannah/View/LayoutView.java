package finn_ryan.savannah.View;

import finn_ryan.savannah.Model.Animal;
import finn_ryan.savannah.Model.Savannah;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class LayoutView implements PropertyChangeListener {
    private final SavannahView savannahView;
    private final Scene windowContents;

    private final Text info;
    private final Text info2;
    private final Button newDay;
    private final Button resize3;
    private final Button resize5;
    private final Button resize8;
    private final ComboBox<String> comboBox;
    private final ToggleGroup group;

    private static String selected;
    private static String radio;
    private static Animal last;
    private static String lastStyle;
    private final Savannah model;

    public LayoutView(Savannah model) {
        this.model = model;
        model.addObserver(this);

        BorderPane root = new BorderPane();
        windowContents = new Scene(root, 700, 600);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        ObservableList<Node> hBoxList = hBox.getChildren();
        info = new Text("Day: 0\nFilled: 0\nDied: 0");
        hBoxList.add(info);
        addHSpacer(hBox);
        newDay = new Button("New Day");
        hBoxList.add(newDay);
        addHSpacer(hBox);
        Text text = new Text("Resize: ");
        hBoxList.add(text);

        VBox vBox = new VBox();
        ObservableList<Node> vBoxList = vBox.getChildren();
        resize3 = new Button("3X3");
        vBoxList.add(resize3);
        resize5 = new Button("5X5");
        vBoxList.add(resize5);
        resize8 = new Button("8X8");
        vBoxList.add(resize8);
        hBoxList.add(vBox);

        VBox vBox2 = new VBox();
        vBox2.setAlignment(Pos.CENTER);
        ObservableList<Node> vBox2List = vBox2.getChildren();
        addVSpacer(vBox2);

        comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Zebra", "Cheetah", "None");
        comboBox.getSelectionModel().select(0);
        selected = comboBox.getSelectionModel().getSelectedItem();
        vBox2List.add(comboBox);

        group = new ToggleGroup();
        for (String t : new String[]{"Add", "View"}) {
            RadioButton node = new RadioButton(t);
            node.setToggleGroup(group);
            vBox2List.add(node);
        }

        addVSpacer(vBox2);
        info2 = new Text("Animal Info");
        vBox2List.add(info2);
        addVSpacer(vBox2);
        addVSpacer(vBox2);

        root.setTop(hBox);
        root.setLeft(vBox2);
        savannahView = new SavannahView(model);
        root.setCenter(savannahView);
    }

    private void addHSpacer(HBox box) {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        box.getChildren().add(spacer);
    }

    private void addVSpacer(VBox box) {
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        box.getChildren().add(spacer);
    }

    public Scene getScene() {
        return windowContents;
    }

    public Button getNewDay() {
        return newDay;
    }

    public Button getResize3() {
        return resize3;
    }

    public Button getResize5() {
        return resize5;
    }

    public Button getResize8() {
        return resize8;
    }

    public ComboBox<String> getComboBox() {
        return comboBox;
    }

    public ToggleGroup getGroup() {
        return group;
    }

    public void propertyChange(PropertyChangeEvent event) {
        switch (event.getPropertyName()) {
            case "resize" -> {
                info2.setText("Animal Info");
                savannahView.resize((int) event.getNewValue());
            }
            case "combo" -> selected = (String) event.getNewValue();
            case "radio" -> {
                radio = (String) event.getNewValue();
                info2.setText("Animal Info");
                if (last != null) { last.setStyle(lastStyle); }
            }
            case "onClick" -> {
                if (Objects.equals(radio, "Add")) {
                    model.incFilled();
                    savannahView.add((Animal) event.getNewValue(), selected);
                } else if (Objects.equals(radio, "View")) {
                    if (last != null) { last.setStyle(lastStyle); }
                    last = (Animal) event.getNewValue();
                    lastStyle = last.getStyle();
                    last.setStyle("-fx-border-color: cyan; -fx-border-radius: 10; -fx-background-radius: 10; -fx-background-insets: 1;");
                    info2.setText(last.getName() + "\nHealth: " + last.getHealth());
                }
            }
            default -> {}
        }
        info.setText("Day: " + model.getDays() + "\nFilled: " + model.getFilled() + "\nDied: " + model.getDead());
    }
}
