package Basics;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.util.ArrayList;

public class FindMeCode extends Application {

    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;
    ArrayList<Button> searchList = new ArrayList<>();
    Button correct = new Button();


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root,WIDTH , HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Find the button!");

        makeContents(root);

        primaryStage.show();
    }

    private void makeContents(Group root ) {
        ObservableList<Node> list = root.getChildren();

        //make 5 buttons and add them to a list
        for(int i = 0; i < 5; i++){
            Button button = new Button(" ");
            button.setLayoutY(i*50);
            list.add(button);
            searchList.add(button);

            //using e.getSource(), we can reuse a listener
            searchList.get(i).addEventFilter(ActionEvent.ACTION, new Clicked());
        }

        //set a random button to be correct
       correct = searchList.get((int)(Math.random()*5));
    }

    //the call for the wrong item
    private class Clicked implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            //this searches the button list until if finds a match
            // Then it sets it to "nope"
            if(!event.getSource().equals(correct)){
                ((Button)event.getSource()).setText("Nope");
            }else{
                ((Button)event.getSource()).setText("Found!");
            }
        }
    }
}
