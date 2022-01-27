package finn_ryan.savannah;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    private Label text;

    @FXML
    protected void onButtonClick() {
        text.setText("\"Something\"");
    }
}