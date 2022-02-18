module rebenitsch {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    exports Basics;
    exports CookieClicker;
    exports LayoutExamples;
    exports ObserverPatternExample_SDK_8up;

    opens sampleWYSIWYG to javafx.fxml;
    exports sampleWYSIWYG;

    opens Scratch to javafx.fxml;
    exports Scratch;
}