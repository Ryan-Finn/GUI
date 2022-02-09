package ObserverPatternExample_SDK_8up;

import javafx.scene.text.Text;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Locale;

/**
 * * Extremely basic Text observer that puts the amount of time (mm:ss) in the Text everytime the
 * observable changes
 */
public class ExampleTextView extends Text implements PropertyChangeListener {
    long startTime = 0;
    private GetTime model;

    public ExampleTextView(GetTime model)
    {
        startTime = System.currentTimeMillis();
        this.model = model;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //PULL observer
        long millis = model.getTime();
        int seconds = (int) (millis / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;

        setText(String.format(Locale.US,"%d:%02d", minutes, seconds));
    }
}
