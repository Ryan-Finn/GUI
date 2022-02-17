/*
Complete the following checklist. If you partially completed an item, put a note how it can be checked for what is working for partial credit.


__P__ Followed the class OOP diagram 	14  - Combined Tile and Animal into one, Animal is what's being observed now
__X__ Observer pattern (ignores tiers)	20


1.	Tier: Views and animal 56
__X__ a. All objects (ignoring the sim area)
__X__ b. Have a 9 tile sim area
____ c. Able to add/remove an animal properly
__X__ d. Info bar listed correctly with all three items with default values
__X__ e. Tile Text correct for each for one rectangle
__X__ f. Tile Text correct for each for all rectangles
__X__ g. Radio buttons update properly
__X__ h. Selecting a rectangle with “view” affects the animal info somehow
__P__ i. Selecting a rectangle with “view” affects the animal info correctly


2a Tier: Advanced functionality	32
__X__ a. New day button has some noticeable effect
__X__ b. Animals updated properly on “new day”
__X__ c. Day count in info bar is correct
____ d. Animal removed when dead on “new day”
____ e. Num died in info bar is correct (updates on the day the animal dies)
____ f. Num tiles filled in info bar is correct at all times
__X__ e. The animal info at least holds
__P__ f. Reselecting the tile updates the animal information correctly


2b: Layout	48
__X__ a. Location of all items in correct spot
__X__ b. Layout still correct on window resize
__X__ c. Resize grid at minimum resets the grid and info
__P__ d. Everything still working that is listed above with resize


Final Tier: Extensions 30
Extension 1: 2a  5: Button outline applied in LayoutView.PropertyChange
Extension 2: 2b 10: Images applied in Animal, image file location set in each animal's model.
Extension 3: 2d 10: Coloring applied in Animal, color set in each animal's model.
Extension 4: 2e  5: Styling applied in Animal


The grade you compute is the starting point for course staff, who reserve the right to change the grade if they disagree with your assessment and to deduct points for other issues they may encounter, such as errors in the submission process, naming issues, etc.
 */

package finn_ryan.savannah;

import finn_ryan.savannah.Control.Controller;
import finn_ryan.savannah.Model.Savannah;
import finn_ryan.savannah.View.LayoutView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage window) {
        Savannah model = new Savannah();
        LayoutView view = new LayoutView(model);
        Controller control = new Controller(model, view);

        window.setTitle("Savannah Simulator");
        window.setScene(view.getScene());

        window.show();
    }

    public static void main(String[] args) { launch(); }
}