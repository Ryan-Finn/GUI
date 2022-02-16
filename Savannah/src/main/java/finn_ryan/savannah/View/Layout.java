package finn_ryan.savannah.View;

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

public class Layout implements PropertyChangeListener {
    private final SavannahView savannahView;
    private final Scene windowContents;

    private final Text info;
    private final Button newDay;
    private final Button resize3;
    private final Button resize5;
    private final Button resize8;
    private final ComboBox<String> comboBox;
    private final ToggleGroup group;

    private static String selected;
    private final Savannah model;

    public Layout(Savannah model) {
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
        addText(hBoxList, "Resize: ");

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
        //addComboBox(vBox2List, new String[]{"Zebra", "Cheetah", "None"});
        comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Zebra", "Cheetah", "None");
        comboBox.getSelectionModel().select(0);
        selected = comboBox.getSelectionModel().getSelectedItem();
        vBox2List.add(comboBox);

        //addRadioButtons(vBox2List, new String[]{"Add", "View"});
        group = new ToggleGroup();
        for (String t : new String[]{"Add", "View"}) {
            RadioButton node = new RadioButton(t);
            node.setToggleGroup(group);
            vBox2List.add(node);
        }

        addVSpacer(vBox2);
        addText(vBox2List, "Animal Info");
        addVSpacer(vBox2);
        addVSpacer(vBox2);

        root.setTop(hBox);
        root.setLeft(vBox2);
        savannahView = new SavannahView();
        root.setCenter(savannahView.getView());
    }

    private void addText(ObservableList<Node> parent, String text) {
        parent.add(new Text(text));
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
        info.setText("Day: " + model.getDays() + "\nFilled: " + model.getFilled() + "\nDied: " + model.getDead());
        switch (event.getPropertyName()) {
            case "resize":
                savannahView.resize((int)event.getNewValue());
                break;

            case "combo":
                selected = (String)event.getNewValue();
                break;

            case "onClick":
                savannahView.add((int)event.getNewValue(), selected);
                break;

            default:
                break;
        }
    }
}
