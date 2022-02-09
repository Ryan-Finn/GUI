module finn_ryan.savannah {
    requires javafx.controls;
    requires javafx.fxml;


    opens finn_ryan.savannah to javafx.fxml;
    exports finn_ryan.savannah;
}