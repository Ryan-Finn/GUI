package Basics;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ExampleNodes extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    //standard setup
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root,WIDTH , HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello GUI Programming");

        makeContents(root);

        primaryStage.show();
    }

    private void makeContents(Group root ) {
        ObservableList<Node> list = root.getChildren();
        Text text = new Text("Hello GUI Programming");
        text.setTextOrigin( VPos.TOP);

        TextField feild = new TextField("Enter Something");
        feild.setLayoutY(50);

        TextArea area = new TextArea("stuff");
        area.setLayoutY(100);
        area.setMaxHeight(30);

        Button btn = new Button("Click");
        btn.setLayoutY(150);

        //radio buttons
        ToggleGroup group = new ToggleGroup(); //the group where only 1 can be selected
        RadioButton rBtn1 = new RadioButton("option 1");
        RadioButton rBtn2 = new RadioButton("option 2");
        RadioButton rBtn3 = new RadioButton("option 3");
        rBtn1.setToggleGroup(group);
        rBtn2.setToggleGroup(group);
        rBtn3.setToggleGroup(group);
        rBtn1.setLayoutY(200);
        rBtn2.setLayoutY(220);
        rBtn3.setLayoutY(240);
        group.selectToggle( rBtn3 );

        //checkbox
        CheckBox c1 = new CheckBox("Check 1");
        c1.setLayoutY(275);
        CheckBox c2 = new CheckBox("Check 2");
        c2.setLayoutY(300);


        //slider
        Slider slidy = new Slider(0, 100, 10);
        slidy.setLayoutY(325);

        //comboBox
        ComboBox<String> myList = new ComboBox<>();
        //option for later assignment: observable list
        myList.getItems().addAll("one", "two", "three");
        myList.getSelectionModel().select(1); //set the current selection
        myList.setLayoutY(350);

        list.add(text);
        list.add(feild);
        list.add(area);
        list.add(btn);
        list.add(rBtn1);
        list.add(rBtn2);
        list.add(rBtn3);
        list.add(c1);
        list.add(c2);
        list.add(slidy);
        list.add(myList);
    }
}
