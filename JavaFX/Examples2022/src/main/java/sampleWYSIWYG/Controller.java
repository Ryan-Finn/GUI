package sampleWYSIWYG;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

//Initializable interface gives us initialize(...)
public class Controller implements Initializable {

    //names must match fxml IDs exactly
    //also must be public or use annotation so the fxml loader can set the values


    private int btnCnt = 0;
    @FXML
    private Label myText;
    @FXML
    private TextField myTextFeild;
    @FXML
    private VBox box;

    //xml specified
    public void buttonClicked(ActionEvent actionEvent) {
        btnCnt++;
        myText.setText("Button clicked " + btnCnt + " times");
    }

    //this is run after the constructors are done, and hte scene exists
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //specify our handlers manaully
        myTextFeild.addEventFilter(KeyEvent.ANY, new feildHandler());

        //able to find child by fx:ID
        Button btn = (Button) box.lookup("#newButton");
        btn.addEventFilter(MouseEvent.MOUSE_CLICKED, new mouseHandler());

        //able to find child by ID
        //WARNING, FX:id will be copied to the regular id if not given
        Button btn2 = (Button) box.lookup("#minus");
        btn2.addEventFilter(ActionEvent.ACTION, actionEvent -> {
            btnCnt--;
            myText.setText("Button clicked " + btnCnt + " times");
        });
    }

    //just announces mouse click location
    private class mouseHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            myText.setText("Mouse at " + event.getX() + " " + event.getY());
        }
    }

    //describe key event
    private class feildHandler implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent event) {

            myText.setText("Key: " + event.getCharacter() + " Type: " + event.getEventType());
        }
    }
}
