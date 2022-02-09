package LayoutExamples;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LayoutPractice extends Application {
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
    public void start( Stage primaryStage) {
        Pane root;
        initialize(); //create nodes so we have something to look at

        //only uncomment out one at a time
        //

        //root = makeVBox();
        //root = makeGridPracticeContents();
        //root = makeEmbeddedPracticeContents();
        root = makePracticeA();
        //root = makePracticeB();
        //root = makePracticeC();

        Scene scene = new Scene(root,WIDTH , HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Layouts");
        primaryStage.show();
    }

    private Pane makeVBox() {
        VBox box = new VBox();
        ObservableList<Node> list = box.getChildren();
        list.add(new Button("1"));
        list.add(new Button("2"));
        list.add(new Button("3"));
        return box;
    }

    private void initialize()  {
        area1.setMaxHeight(50);
        area2.setPrefColumnCount(15);
        area3.setPrefColumnCount(10);
        area2.setMaxHeight(50);

        area2.setStyle("-fx-control-inner-background:#ff0000;");
        area3.setStyle("-fx-control-inner-background:#ffff00;");

        output.setStyle("-fx-background-color: blue;");

        ObservableList<String> options =
                FXCollections.observableArrayList( "one", "two", "three");
        myList = new ComboBox<>(options);
        myList.getSelectionModel().select(1); //set the current selection

        slidy.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed( ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                output.setText(""+newValue.intValue());
            }
        });
    }


    public Pane makeGridPracticeContents() {
        GridPane root = new GridPane();
        root.setGridLinesVisible(true);
        root.setAlignment(Pos.CENTER);

        Button b = new Button("1");
        root.add(b, 0,0);
        b = new Button("2");
        root.add(b, 1,1);
        b = new Button("3");
        root.add(b, 2,2);
        return root;
    }

    public Pane makeEmbeddedPracticeContents() {
        VBox outer = new VBox();
        outer.setAlignment(Pos.CENTER);
        ObservableList<Node> list = outer.getChildren();
        GridPane gp = new GridPane();
        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(33);
        ColumnConstraints c2 = new ColumnConstraints();
        c2.setPercentWidth(33);
        ColumnConstraints c3 = new ColumnConstraints();
        c3.setPercentWidth(33);
        gp.getColumnConstraints().addAll(c1,c2,c3);

        //first row with more alignment settings
        Button temp = new Button("X");
        gp.add(temp, 0, 0);
        temp = new Button("X");
        temp.setPrefSize(200, temp.getPrefHeight()); // example of expanded the button to full width
        gp.add(temp, 1, 0);
        temp = new Button("X");
        GridPane.setHalignment(temp, HPos.CENTER); //example how to change the alignment
        gp.add(temp, 2, 0);

        //next row
        gp.add(new TextField("X"), 0, 1);
        gp.add(new TextField("X"), 1, 1);
        gp.add(new TextField("X"), 2, 1);

        list.add(gp);
        Button b = new Button("XXX");
        b.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        list.add(b);

        return outer;
    }

    private Pane makePracticeA() {
        GridPane root = new GridPane();
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(25);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(25);
        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(25);
        RowConstraints row4 = new RowConstraints();
        row4.setPercentHeight(25);
        root.getRowConstraints().addAll(row1,row2,row3,row4);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        root.getColumnConstraints().addAll(col1,col2);

        root.setVgap(2);
        root.setHgap(2);

        Pane top = new Pane();
        top.setStyle("-fx-background-color: blue;");
        root.add(top, 0,0,2,1);

        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++) {
                top = new Pane();
                top.setStyle("-fx-background-color: blue;");
                root.add(top, i, j+1);
            }
        }

        top = new Pane();
        top.setStyle("-fx-background-color: blue;");
        root.add(top, 0,3,2,1);
        return root;

    }
    private Pane makePracticeB() {
        BorderPane root = new BorderPane();
        Pane top = new Pane();
        Button b = new Button("top");
        b.setMaxWidth(800);
        root.setTop(b);

        b = new Button("bottom");
        b.setMaxWidth(800);
        root.setBottom(b);

        b = new Button("X");
        b.setMaxWidth(800);
        b.setMaxHeight(800);
        root.setCenter(b);

        VBox side = new VBox();
        ObservableList<Node> list  = side.getChildren();
        list.add(new Button("a"));
        list.add(new Button("a"));
        root.setLeft(side);

        side = new VBox();
        list  = side.getChildren();
        list.add(new Button("b"));
        list.add(new Button("b"));
        root.setRight(side);

        return root;
    }

    private Pane makePracticeC() {
        BorderPane root = new BorderPane();
        Pane top = new Pane();
        Button b = new Button("top");
        b.setMaxWidth(800);
        root.setTop(b);

        b = new Button("bottom");
        b.setMaxWidth(800);
        root.setBottom(b);

        b = new Button("X");
        b.setMaxWidth(800);
        b.setMaxHeight(800);
        root.setCenter(b);



        VBox side = new VBox();
        ObservableList<Node> list  = side.getChildren();
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        list.add(spacer);
        b = new Button("a");
        spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        list.add(b);
        list.add(spacer);
        b = new Button("a");
        spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        list.add(b);
        list.add(spacer);
        b = new Button("a");
        spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        list.add(b);
        list.add(spacer);
        root.setLeft(side);

        return root;
    }
}
