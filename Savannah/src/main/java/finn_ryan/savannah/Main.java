/*
Complete the following checklist. If you partially completed an item, put a note how it can be checked for what is working for partial credit.


____ Followed the class OOP diagram 	14
____ Observer pattern (ignores tiers)	20


1.	Tier: Views and animal
____ a. All objects (ignoring the sim area)
____ b. Have a 9 tile sim area	5
____ c. Able to add/remove an animal properly
____ d. Info bar listed correctly with all three items with default values
____ e. Tile Text correct for each for one rectangle
____ f. Tile Text correct for each for all rectangles
____ g. Radio buttons update properly
____ h. Selecting a rectangle with “view” affects the animal info somehow
____ i. Selecting a rectangle with “view” affects the animal info correctly


2a Tier: Advanced functionality	32
____ a. New day button has some noticeable effect
____ b. Animals updated properly on “new day”
____ c. Day count in info bar is correct
____ d. Animal removed when dead on “new day”
____ e. Num died in info bar is correct (updates on the day the animal dies)
____ f. Num tiles filled in info bar is correct at all times
____ e. The animal info at least holds
____ f. Reselecting the tile updates the animal information correctly


2b: Layout	48
____ a. Location of all items in correct spot
____ b. Layout still correct on window resize
____ c. Resize grid at minimum resets the grid and info
____ d. Everything still working that is listed above with resize


Final Tier: Extensions 30
Extension 1: <number> <points> <name>: <how to test/find if applicable>
Extension 2: <number> <points> <name>: <how to test/find if applicable>
Etc.


The grade you compute is the starting point for course staff, who reserve the right to change the grade if they disagree with your assessment and to deduct points for other issues they may encounter, such as errors in the submission process, naming issues, etc.
 */

package finn_ryan.savannah;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}