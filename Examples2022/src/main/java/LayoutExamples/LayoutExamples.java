package LayoutExamples;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LayoutExamples extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    private Label output = new Label("A"); //label allows colored background, text does not
    private TextField input = new TextField("A");
    private TextArea area1 = new TextArea();
    private TextArea area2 = new TextArea();
    private TextArea area3 = new TextArea();
    private Button button = new Button("A");
    private Button[] buttons = new Button[4];
    private CheckBox check = new CheckBox("Check me!");
    private ComboBox<String> myList;
    private Slider slidy = new Slider(0, 100, 10);

    @Override
    public void start(Stage primaryStage) {

        Pane root;
        initialize(); //create nodes so we have something to look at

        //only uncomment out one at a time
        //
        //root = makeFlowContents();
        root = makeBoxContents();
        //root = makeBorderContents();
        //root = makeGridContents();
        //root = makeStackContents(); //optional

        Scene scene = new Scene(root,WIDTH , HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Layouts");
        primaryStage.show();
    }

    private void initialize()  {
        area2.setPrefColumnCount(15);

        area2.setStyle("-fx-control-inner-background:#ff0000;");
        area3.setStyle("-fx-control-inner-background:#ffff00;");

        output.setStyle("-fx-background-color: blue;");

        ObservableList<String> options =
                FXCollections.observableArrayList( "one", "two", "three");
        myList = new ComboBox<>(options);
        myList.getSelectionModel().select(1); //set the current selection

        slidy.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                output.setText(""+newValue.intValue());
            }
        });
    }

    private Pane makeFlowContents(  ) {
        FlowPane root = new FlowPane();
        root.setHgap(10); // handy spacing property which adds 10 horizontal pixels between nodes

        ObservableList<Node> list = root.getChildren();
        list.add(output);
        list.add(input);
        list.add(button);
        list.add(check);
        list.add(myList);
        list.add(slidy);

        //move location of fill
        root.setAlignment( Pos.CENTER_LEFT );

        return root;
    }

    private Pane makeBoxContents(  ) {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setFillWidth(true); //without this, fills as much as possible, IF the node's max size allows it
        root.setSpacing(20); //layout control to have 20 pixel between nodes


        ObservableList<Node> list = root.getChildren();
        //uncomment to allow the text node to expand
        output.setMaxWidth(Double.POSITIVE_INFINITY);
        list.add(output);
        list.add(input);
        list.add(button);
        list.add(check);
        list.add(myList);

        //spring
        //Region r = new Region();
        //VBox.setVgrow(r, Priority.ALWAYS);
        //list.add(r);

        list.add(slidy);

        return root;
    }

    private Pane makeBorderContents(  ) {
        BorderPane root = new BorderPane();
        root.setTop(input);
        root.setBottom(area1);
        root.setCenter(button);
        //see what happens when the size isn't
        // limited on area2 when center is gone!
        root.setLeft(area2);
        //root.setRight(area3);
        //BorderPane.setMargin(area1, new Insets(10)); //CLASS method to set margins
        return root;
    }
    
    public Pane makeGridContents() {
        GridPane root = new GridPane();
        
        root.setHgap(50);
        root.setGridLinesVisible(true);
        root.setAlignment(Pos.CENTER); //the ENTIRE content

        //hard code the height
        //root.setVgap(50); //note, this and below do not "place nice together"

        //set the height by percent rather than by node
        //there is a Column version
        RowConstraints row1 = new RowConstraints(); //optional # of pixels
        row1.setPercentHeight(25);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(50);
        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(25);
        root.getRowConstraints().addAll(row1,row2,row3);

        //left
        Pane b = new Pane(new Text("X"));
        b.setStyle("-fx-background-color: blue;");
        root.add(b, 0,0);

        //top
        b = new Pane(new Text("X"));
        b.setStyle("-fx-background-color: yellow;");
        root.add(b, 1,0);

        //center
        b = new Pane(new Text(""));
        b.setStyle("-fx-background-color: red;");
        root.add(b, 1,1);

        //bottom
        b = new Pane(new Text("X"));
        b.setStyle("-fx-background-color: green;");
        root.add(b, 1,2, 2, 1);

        //right
        b = new Pane(new Text("X"));
        b.setStyle("-fx-background-color: white;");
        root.add(b, 2,0, 1, 2);

        return root;
    }

    private Pane makeStackContents(  ) {
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);

        ObservableList<Node> list = root.getChildren();
        output.setFont(Font.font(50));
        list.add(output);
        list.add(slidy);

        return root;
    }

}

