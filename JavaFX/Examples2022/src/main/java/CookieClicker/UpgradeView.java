package CookieClicker;

import javafx.scene.control.Button;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UpgradeView extends Button implements PropertyChangeListener {

    private UpgradeModel infoModel;

    public UpgradeModel getModel(){
        return infoModel;
    }

    /**
     * Makes a Button that holds upgrade information.
     * @param infoModel the upgrade button info
     */
    public UpgradeView(UpgradeModel infoModel){
        this.infoModel = infoModel;
        setMaxSize(Double.MAX_VALUE, getPrefHeight());
        //setPrefSize(Double.MAX_VALUE, getPrefHeight());

        //OPTIONAL..additional button styling
        
        //BackgroundFill is a basic color background
        //Color is self explanatory
        //Corner Radii is how curved the corners are (use EMPTY for no curve)
        //Some Inset is how much padding there is
//        setBackground(new Background(
//                new BackgroundFill(Color.LIGHTSKYBLUE,
//                    new CornerRadii(3),
//                    new Insets(2,2,2,2))
//        ));


        //BorderStroke, style, corner radii, widths, and insets have static prebuilts
        //BorderStroke is a basic boarder class
         //Color is self explanatory
        //Style is solid, dashed, etc
        //Corner Radii is how curved the corners are (use EMPTY for no curve)
        //BorderWidth with is how wide
        //Inset here are optional 
//        setBorder(new Border(
//                new BorderStroke(
//                        Color.RED,
//                        BorderStrokeStyle.DASHED,
//                        CornerRadii.EMPTY,
//                        new BorderWidths(1,2,3,4),
//                        new Insets(0,2,5,10))));
    }

    //observer to forward the event
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        int money = (int) evt.getNewValue();

        //enable disable as avilable
        if(infoModel.canPurchase(money)) {
            //note: both setDisabled and setDisable are legal,
            // but only setDisable works on initialization...
            setDisable(false);
        }
        else{
            setDisable(true);
        }

        setText(infoModel.getName() + "\nNext Upgrade: $" + infoModel.getLevelCost() +
                "\nCurrent " + infoModel.getAutoClick() + " clicks per second" );
    }
}
