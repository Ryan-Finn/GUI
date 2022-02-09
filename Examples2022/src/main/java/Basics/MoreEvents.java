package Basics;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class MoreEvents extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;
    private Timer timeline  = new Timer();
    private Integer timeSeconds = 0;

    //standard setup
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root,WIDTH , HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello GUI Programming");

        makeContents(root);

        primaryStage.show();
    }

    private void makeContents(Group root ) {
        ObservableList<Node> list = root.getChildren();
        Text text = new Text("Hello GUI Programming");
        text.setTextOrigin( VPos.TOP);

        TextField feild = new TextField("Enter Something");
        feild.setLayoutY(50);

        TextArea area = new TextArea("stuff");
        area.setLayoutY(100);
        area.setMaxHeight(30);

        Button btn = new Button("Click");
        btn.setLayoutY(150);

        //radio buttons
        ToggleGroup group = new ToggleGroup(); //the group where only 1 can be selected
        RadioButton rBtn1 = new RadioButton("option 1");
        RadioButton rBtn2 = new RadioButton("option 2");
        RadioButton rBtn3 = new RadioButton("option 3");
        rBtn1.setToggleGroup(group);
        rBtn2.setToggleGroup(group);
        rBtn3.setToggleGroup(group);
        rBtn1.setLayoutY(200);
        rBtn2.setLayoutY(220);
        rBtn3.setLayoutY(240);
        group.selectToggle( rBtn3 );

        //checkbox
        CheckBox c1 = new CheckBox("Check 1");
        c1.setLayoutY(275);
        CheckBox c2 = new CheckBox("Check 2");
        c2.setLayoutY(300);


        //slider
        Slider slidy = new Slider(0, 100, 10);
        slidy.setLayoutY(325);

        //comboBox
        ComboBox<String> myList = new ComboBox<>();
        //option for later assignment: observable list
        myList.getItems().addAll("one", "two", "three");
        myList.getSelectionModel().select(1); //set the current selection
        myList.setLayoutY(350);

        list.add(text);
        list.add(feild);
        list.add(area);
        list.add(btn);
        list.add(rBtn1);
        list.add(rBtn2);
        list.add(rBtn3);
        list.add(c1);
        list.add(c2);
        list.add(slidy);
        list.add(myList);



        //events for some of the above------------------------------------------------------------------------
        //-----------------------------------------------------------------------------------------------------

        //CHECKBOX----------------------------------------------------------------------------------
        //default behavior
        c1.addEventFilter(  ActionEvent.ACTION, actionEvent -> {
            //how to get the current selection without a event
            boolean temp = c1.isSelected();
        } );

        //direct behavior
        c1.selectedProperty().addListener(new ChangeListener<Boolean>() {

            //observable value is a reference to the observer of the value
            //in this case the checkbox that triggers the event!
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                System.out.println("New check box value: " + newValue);


            }
        });





        //COMBOBOX------------------------------------------------------------------------------------\
        //default behavior
        myList.addEventFilter(ActionEvent.ACTION, actionEvent -> {
            //how to get the current selection without the event telling you anything
            int index = myList.getSelectionModel().getSelectedIndex();
            String selectText = myList.getSelectionModel().getSelectedItem();
        } );

        //direct behavior
        myList.getSelectionModel().selectedItemProperty().addListener( new ChangeListener< String >( ) {
            @Override
            public void changed( ObservableValue< ? extends String > observableValue, String oldSelect, String newSelect ) {
                System.out.println("Old Select: " + oldSelect);
                System.out.println("New Select: " +  newSelect);
            }
        });






        //SLIDER-----------------------------------------------------------------------------------
        //direct...only mouse or key event will be triggers, NOT ActionEvent

        slidy.addEventFilter( MouseEvent.MOUSE_DRAGGED, actionEvent -> {
            //how to get the current selection without the event telling you anything
            double val = slidy.getValue();
            System.out.println("indirect slider: "+val);
        } );

        //also has setEVENTTYPE for each of these
        slidy.setOnDragDetected( mouseEvent -> { } );

        //Direct: why a sliders use this way come in with a generic Number class is unknown to me
        slidy.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("direct slider: "+newValue.intValue());
            }
        });






        //RADIO BUTTONS---------------------------------------------------------------------------------------
        //why a radio button is set up so differently than all others is unknown to me
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {


            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle oldRadio, Toggle newRadio) {
                System.out.println("Old radio: " + ((RadioButton)oldRadio).getText());
                System.out.println("New radio: " + ((RadioButton)newRadio).getText());


                //how to get the current selection without a event
                Toggle monitor = ov.getValue();
                Toggle selected = group.getSelectedToggle();
                boolean temp = rBtn1.isSelected();

            }
        });


        
        //timer--------------------------------------------------------------------------------------------
        //also could do this with javafx's Timeline, but that's more designed for animations 
        // and adds some unneeded detail   

        //timeline.schedule will run once
        // timeline.scheduleAtFixedRate (task, startAt, periodBetweenEventsInMilliseconds
        timeline.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeSeconds++;
                // update timerLabel
                text.setText(
                        timeSeconds.toString());
            
            }},
       0, 1000);

    }
}
