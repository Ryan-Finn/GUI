package Basics;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloWorld extends Application {

    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    public static void main(String[] args) {
        launch(args);
    }

    //this actually creates the window
    @Override
    public void start(Stage primaryStage) {
        //group is an area that can hold Nodes (non resizable)
        Group root = new Group();

        //Scene is the contents of the window
        //stage is the entire window
        Scene scene = new Scene(root,WIDTH , HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello GUI Programming");

        makeContents(root);

        //make visible
        primaryStage.show();
    }

    //this make the contents of the window
    private int y = 0;
    private int incr = 0;
    private void makeContents(Group root ) {

        //all views are Nodes, and they are in a tree structure
        //this call gets all immediate children nodes
        ObservableList<Node> list = root.getChildren();

        //make a new text and text field Node
        Text text = new Text("Hello GUI Programming");
        text.setTextOrigin(VPos.TOP); //adjust location of "anchor"

        //move down for next time
        text.setY(y);
        y+=incr;

        TextField feild = new TextField("Enter Something");
        feild.setLayoutX(200); //absolute pixel positioning

        //add these to the end of the children list
        list.add(text);
        list.add(feild);
    }
}
