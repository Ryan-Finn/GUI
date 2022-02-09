package CookieClicker;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

import java.beans.PropertyChangeSupport;

public class Controller {

    //stuff for timers
    private long firstCall = 0;
    private boolean hidden = true;

    private Layout layout;
    private Model model;

    public Controller(Model data, Layout layout) {
        this.layout = layout;
        model = data;

        /*
        * Controllers job to connect events to views
         */
        layout.output.addEventFilter(ActionEvent.ACTION, new CountListener());

        /*
        * Setup the timer
         */
        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), new Tick()));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
        firstCall = System.currentTimeMillis();

    }

    /**
     * Handles normal click increase in money
     */
    private class CountListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            model.adjustClickTotal(1); // tell the model to update
        }
    }



    /**
     * Handles purchasing upgradeViews
     */
    private class BuyUpgrade implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            int money = model.getClickCnt();
            UpgradeView button = (UpgradeView) e.getSource();
            button.getModel().purchase(money);
        }
    }


    /**
     * Handles the autoclick increase in "money"
     */
    private class Tick implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

            long currentTime = System.currentTimeMillis();

            //debugging statement
            // wait 5 seconds to display last upgrade
            System.out.println((currentTime - firstCall) / 1000.0);

            if (hidden && (currentTime - firstCall) / 1000.0 > 10) {
                AddUpgrade("upgrade3.txt");
                hidden = false;

            }
            int count = model.getAutoClickCount();
            model.adjustClickTotal(count);
        }
    }

    public void AddUpgrade(String upgradeFile) {
        /*
         *  observer pattern example
         * and add upgrade buttons to side pane
         */
        UpgradeModel upgrade = new UpgradeModel(upgradeFile, model);
        model.addUpgradeModel(upgrade);

        UpgradeView upgradeView = new UpgradeView(upgrade);
        ObservableList<Node> list = layout.panel.getChildren();
        list.add(upgradeView);

        upgradeView.addEventHandler(ActionEvent.ACTION, new BuyUpgrade());
        model.addObserver(upgradeView);
    }


}
