package Basics;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

public class BasicEvents extends Application {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 300;

    //searches the resource folder
    //private Image image = new Image("duck.jpg");

    //best option if using modules, but need matching package name
    private final Image image = new Image(
            Objects.requireNonNull(getClass().getResourceAsStream("duck-res.jpg")));


    private Text labelArea;
    private TextArea textArea;
    private Button button;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //make window
        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello Events!");

        //make contents
        makeContents(root);

        //display window
        primaryStage.show();
    }

    private void makeContents(Group root) {
        //make everything
        ObservableList<Node> list = root.getChildren();
        labelArea = new Text("Hello GUI\nProgramming!\nClick here!"); //new lines work with Text
        labelArea.setTextOrigin(VPos.TOP);

        TextField feild = new TextField("Type ?? to activate");
        feild.setLayoutX(200);

        button = new Button("Click");
        button.setLayoutX(350);
        button.setId("buttonId");

        textArea = new TextArea("Type here for key events");
        textArea.setLayoutY(70);

        //add everything to window
        list.add(labelArea);
        list.add(feild);
        list.add(button);
        list.add(textArea);

        //full event syntax
        //use "standard" event for item...a click for a button
        button.addEventFilter(ActionEvent.ACTION, new ActionHandler());

        //lambda version
        feild.addEventFilter(ActionEvent.ACTION,
                event -> labelArea.setText("Default text field event!") );


        //filter event with the eventType
        labelArea.addEventFilter(MouseEvent.MOUSE_CLICKED, new MouseHandler());
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, new MouseHandler());

        //accept all and filter in listener
        button.addEventFilter(MouseEvent.ANY, new MouseAllHandler());

        //less directly related events are still permitted
        //Note: you must click on the button to see the key events
        textArea.addEventFilter(KeyEvent.ANY, new KeyAllHandler());
    }

    //basic click
    private class ActionHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            textArea.setText("Default Click Event!");
        }
    }

    //how to determine origin Node
    private class MouseHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            //check direct memory location
            if (event.getSource()  == labelArea)
                textArea.setText("Found TEXT in  MouseHandler ");
            //check id..usually the better open but more work
            else if (((Node)event.getSource()).getId().compareTo( "buttonId" ) ==0 )
                textArea.setText("Found BUTTON in  MouseHandler ");
        }
    }

    //how to filter mouse events when you get them all
    private class MouseAllHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            EventType type = event.getEventType();

            //selection of mouse events
            if (type == MouseEvent.MOUSE_PRESSED && event.getClickCount() > 1)
                labelArea.setText("Double Clicked!");
            else if (type == MouseEvent.MOUSE_PRESSED )
                labelArea.setText("Clicked!");
            else if (type == MouseEvent.MOUSE_RELEASED && event.getClickCount() < 2)
                labelArea.setText("Released!");
            else if (type == MouseEvent.MOUSE_DRAGGED)
                labelArea.setText("Dragged!" + event.getX() + " " + event.getY());
            else if (type == MouseEvent.MOUSE_ENTERED) {
                labelArea.setText("Entered!");
                button.setGraphic(new ImageView(image)); //a graphic is an icon. It does not remove the text
            }
            else if (type == MouseEvent.MOUSE_EXITED) {
                labelArea.setText("Exited!");
                button.setGraphic(null); //removes the icon
            }

        }
    }

    //examples of key events
    private class KeyAllHandler implements EventHandler<KeyEvent> {
        //specifies a key combination
        final KeyCombination saveComb = new KeyCodeCombination(KeyCode.S,
                KeyCombination.CONTROL_DOWN);

        //see how long 'a' is held down
        int count = 0;

        @Override
        public void handle(KeyEvent event) {
            EventType type = event.getEventType();
            KeyCode code = event.getCode();

            //typed and pressed often both fired
            if (type == KeyEvent.KEY_PRESSED) {
                //a is special and sets a counter
                if(code == KeyCode.A) {
                    textArea.setText(" A held: " + count);
                    count++;
                }else {
                    textArea.setText(" Key down: " + code);
                }
            }
            else if (type == KeyEvent.KEY_RELEASED) {
                if(code == KeyCode.A) {
                    count = 0;
                    textArea.setText(" Released a " );
                }
            }
            else {
                //type == KeyEvent.KEY_TYPED

                //key_typed is also typically generated along with a key_pressed
                //so "a" need to be treated specially to see the count
                if(event.getCharacter().toLowerCase().compareTo("a") !=0) {
                    //key_typed is a special case. the code will be undefined
                    textArea.setText(" Typed: " + event.getCharacter());
                }

            }

            //how to check key combos
            if (saveComb.match(event)) {
                textArea.setText(" ctrl+S for save");
            }
        }
    }
}
