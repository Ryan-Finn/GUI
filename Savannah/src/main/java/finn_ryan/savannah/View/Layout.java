package finn_ryan.savannah.View;

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

public class Layout {
    private Scene windowContents;

    public Layout() {
        init(700, 600);
    }

    public Layout(int sz) {
        init(sz, sz);
    }

    public Layout(int width, int height) {
        init(width, height);
    }

    private void addText(ObservableList<Node> parent, String text) {
        parent.add(new Text(text));
    }

    private void addButton(ObservableList<Node> parent, String text) {
        parent.add(new Button(text));
    }

    private void addComboBox(ObservableList<Node> parent, String[] text) {
        ComboBox<String> node = new ComboBox<>();
        node.getItems().addAll(text);
        node.getSelectionModel().select(0);
        parent.add(node);
    }

    private void addRadioButtons(ObservableList<Node> parent, String[] text) {
        ToggleGroup group = new ToggleGroup();
        for (String t : text) {
            RadioButton node = new RadioButton(t);
            node.setToggleGroup(group);
            parent.add(node);
        }
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

    private void init(int WIDTH, int HEIGHT) {
        BorderPane root = new BorderPane();
        windowContents = new Scene(root, WIDTH, HEIGHT);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        ObservableList<Node> hBoxList = hBox.getChildren();
        addText(hBoxList, "Day: 0\nFilled: 0\nDied: 0");
        addHSpacer(hBox);
        addButton(hBoxList, "New Day");
        addHSpacer(hBox);
        addText(hBoxList, "Resize: ");

        VBox vBox = new VBox();
        ObservableList<Node> vBoxList = vBox.getChildren();
        addButton(vBoxList, "3X3");
        addButton(vBoxList, "5X5");
        addButton(vBoxList, "8X8");
        hBoxList.add(vBox);

        VBox vBox2 = new VBox();
        vBox2.setAlignment(Pos.CENTER);
        ObservableList<Node> vBox2List = vBox2.getChildren();
        addVSpacer(vBox2);
        addComboBox(vBox2List, new String[]{"Zebra", "Cheetah", "None"});
        addRadioButtons(vBox2List, new String[]{"Add", "View"});
        addVSpacer(vBox2);
        addText(vBox2List, "Animal Info");
        addVSpacer(vBox2);
        addVSpacer(vBox2);

        root.setTop(hBox);
        root.setLeft(vBox2);
        SavannahView savannahView = new SavannahView(3);
        root.setCenter(savannahView.getView());
    }

    public Scene getScene() {
        return windowContents;
    }
}
