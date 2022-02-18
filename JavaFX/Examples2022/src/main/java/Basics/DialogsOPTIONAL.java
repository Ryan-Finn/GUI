package Basics;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;

public class DialogsOPTIONAL extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    private Text text;

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, WIDTH , HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello GUI Programming");

        makeContents(root);

        primaryStage.show();
    }

    private void makeContents(Group root ) {
        ObservableList<Node> list = root.getChildren();
        text = new Text("Stuff!");
        text.setTextOrigin( VPos.TOP);

        Button btn1 = new Button("Basic Info Dialog");
        btn1.setLayoutY(50);
        btn1.addEventHandler(ActionEvent.ACTION, new BasicAlert());

        Button btn2 = new Button("Basic Warning Dialog");
        btn2.setLayoutY(100);
        btn2.addEventHandler(ActionEvent.ACTION, new WarnAlert());

        Button btn3 = new Button("Basic Input Dialog");
        btn3.setLayoutY(150);
        btn3.addEventHandler(ActionEvent.ACTION, new InputAlert());

        Button btn4 = new Button("Chain IO Dialog");
        btn4.setLayoutY(200);
        btn4.addEventHandler(ActionEvent.ACTION, new IoAlert());

        list.add(text);
        list.add(btn1);
        list.add(btn2);
        list.add(btn3);
        list.add(btn4);
    }

    private static class BasicAlert implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("My Title");
            alert.setHeaderText("In the header band");
            alert.setContentText("Just the main text");

            alert.showAndWait();
        }
    }

    private static class WarnAlert implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Warning, warning!");
            alert.showAndWait();
        }
    }

    private class InputAlert implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            TextInputDialog alert = new TextInputDialog("default text");
            alert.setHeaderText(null); //setting this to null removes its title
            alert.setContentText("Please enter your name:");
            Optional<String> result = alert.showAndWait();
            if(result.isPresent()){
                text.setText(result.get());
            }else{
                text.setText("canceled");
            }
        }
    }

    private static class IoAlert implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            TextInputDialog in = new TextInputDialog("0");
            in.setContentText("Enter a number:");
            Optional<String> result = in.showAndWait();
            //yes, this should have a if(isPresent) surrounding it
            int num = Integer.parseInt(result.get());

            Alert out = new Alert(Alert.AlertType.INFORMATION);
            out.setContentText("Twice the number is " + num * 2 );
            out.showAndWait();

        }

    }
}

