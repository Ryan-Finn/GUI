package ObserverPatternExample_SDK_8up;

import javafx.scene.control.Button;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Locale;

/**
 * Extremely basic BUtton observer that puts the amount of time (mm:ss) in the button everytime the
 * observable changes
 */

public class ExampleButton extends Button implements PropertyChangeListener {

    private long startTime = 0;

    public ExampleButton()
    {
        startTime = System.currentTimeMillis();
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {


        long millis = 0;

        //calculate time here, or from subject data sent in
        if(evt.getNewValue() == null)
        {
            //PULL\recalculate observer
            millis = System.currentTimeMillis() - startTime;
        }else
        {
            //PUSH observer
            millis = (long)(evt.getNewValue()); 
        }

        int seconds = (int) (millis / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;

        setText(String.format(Locale.US,"%d:%02d", minutes, seconds));
    }
}
