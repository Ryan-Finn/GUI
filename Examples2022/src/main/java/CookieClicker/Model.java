package CookieClicker;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Model {
    private int clickCnt = 0;
    private ArrayList<UpgradeModel> upgradeModel = new ArrayList<>();
    private PropertyChangeSupport subject; //subject!!!!

    public Model() {
        subject = new PropertyChangeSupport(this);
    }

    public void addObserver(PropertyChangeListener obv) {
        subject.addPropertyChangeListener(obv);
    }

    public void addUpgradeModel(UpgradeModel m) {
        upgradeModel.add(m);
    }

    public void adjustClickTotal(int clickCnt) {
        this.clickCnt += clickCnt;

        //notify upgrade views
        subject.firePropertyChange("click", null, this.clickCnt);
    }

    public int getAutoClickCount() {
        int sum = 0;
        for (UpgradeModel up : upgradeModel) {
            sum += up.getAutoClick();
        }
        return sum;
    }

    public int getClickCnt() {
        return clickCnt;
    }

    public int getUpgradeCount() {
        int sum = 0;
        for (UpgradeModel up : upgradeModel) {
            sum += up.getLevel();
        }
        return sum;
    }

}
