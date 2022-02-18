package Scratch;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ByStep extends Application {

    private Button calculate = new Button("Step");
    private Text result = new Text("0");
    private CheckBox decrement = new CheckBox("Decrement");
    private int count = 0;

    public static void main(String[] args) {
        launch(args);
    }

    private Parent makeContents() {
        VBox box = new VBox();
        ObservableList<Node> children = box.getChildren();
        children.addAll(result, calculate, decrement);
        calculate.addEventFilter(ActionEvent.ACTION, (event)->{
            if(decrement.isSelected()){
                count--;
            }
            else{
                count++;
            }
            result.setText(""+count);
        });
        return box;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("scratch.fxml"));

        //primaryStage.setTitle("Scratch");
        //primaryStage.setScene(new Scene(root));
        //primaryStage.show();

        Parent root = makeContents();
        Scene scene = new Scene(root, 200, 200);
        primaryStage.setScene(scene);

        //display window
        primaryStage.show();
    }
}
