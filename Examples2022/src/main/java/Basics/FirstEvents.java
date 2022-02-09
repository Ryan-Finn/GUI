package Basics;

//possible solution
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class FirstEvents extends Application {
    private TextField input = new TextField();
    private Text output = new Text();
    private Button calc = new Button("Calc");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start( Stage primaryStage) {
        //Parent will accept any Group or Pane
        Parent root = makeContents();

        Scene scene = new Scene(root, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private Group makeContents( ) {

        Group g= new Group();
        ObservableList< Node > layout = g.getChildren();
        calc.setLayoutY(40);
        output.setLayoutY((80));
        layout.addAll(input, calc, output);

        //option 1, full class
        //calc.addEventFilter(ActionEvent.ACTION, new ActionFilter());

        //option 2, anonymous class
        calc.addEventFilter( ActionEvent.ACTION, new EventHandler< ActionEvent >( ) {
            @Override
            public void handle( ActionEvent actionEvent ) {
                double temp = Double.parseDouble( input.getText());
                temp *= 2;
                output.setText( "Anonymous: "+temp );
            }
        });

        //option 3, lambda
        calc.addEventFilter(ActionEvent.ACTION, actionEvent -> {
            output.setText(output.getText() + "\nLambda!");

        });

        return g;
    }

    private class ActionFilter implements EventHandler<ActionEvent> {
          @Override
          public void handle(ActionEvent event) {
              output.setText("Click!");
          }
    }

}