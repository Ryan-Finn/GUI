package ObserverPatternExample_SDK_8up;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.beans.PropertyChangeSupport;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivityBasicTimer extends Application implements GetTime {

    public static void main(String[] args) {
        launch(args);
    }

    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    private long startTime = 0;
    private long millis;

    //both observers
    private ExampleButton btn;
    private ExampleTextView txt;

    private PropertyChangeSupport subject;

    @Override
    public long getTime() {
        return millis;
    }

    @Override
    public void start(Stage primaryStage) {
        //make layout
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        btn = new ExampleButton();
        txt = new ExampleTextView(this);
        ObservableList<Node> list = root.getChildren();
        list.add(btn);
        list.add(txt);

        //create scene
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Layouts");
        primaryStage.show();

        //setup subject/observer
        subject = new PropertyChangeSupport(this);
        subject.addPropertyChangeListener(btn);
        subject.addPropertyChangeListener(txt);

        //setup timer
        Timer timeline = new Timer();
        timeline.scheduleAtFixedRate(new TaskBasic(), 0, 1000);
        startTime = System.currentTimeMillis();

    }


    //Basic java timer task version
    private class TaskBasic extends TimerTask {
        @Override
        public void run() {
            //just tell the subject to update, and restarts the timer
            millis = System.currentTimeMillis() - startTime;

            //PULL: notify is what actually will send the message
            //subject.firePropertyChange("news", null, null);

            //PUSH: example sending information (only the Example button cares)
            Platform.runLater(() -> {
                subject.firePropertyChange("news", null, millis);
            });
        }
    }

}
