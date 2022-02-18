package CookieClicker;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CookieClone extends Application  {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    //Model
    private Model model;

    //View
    private Layout layout;

    //Controller
    private Controller ctrl;

    public static void main(String[] args) {
        launch(args);
    }

    //standard start code
    @Override
    public void start(Stage primaryStage) {
        model = new Model();

        //create MVC and GUI form
        layout = new Layout(model);

        Scene scene = new Scene(layout.root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cookie Clone!");

        /*
         *  Attach controller to view and model
         */
        ctrl = new Controller(model, layout);


        /*
         *  observer pattern example
         */
        ctrl.AddUpgrade("upgrade1.txt");
        ctrl.AddUpgrade("upgrade2.txt");

        /*
         *  refresh
         */
        model.adjustClickTotal(0);

        primaryStage.show();
    }


}
