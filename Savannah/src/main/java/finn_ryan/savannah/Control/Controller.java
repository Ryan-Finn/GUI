package finn_ryan.savannah.Control;

import finn_ryan.savannah.Model.Savannah;
import finn_ryan.savannah.View.Layout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Controller {
    private final Savannah model;
    private final Layout view;

    public Controller(Savannah data, Layout view) {
        this.view = view;
        model = data;

        view.getNewDay().addEventFilter(ActionEvent.ACTION, new NewDayListener());
        view.getResize3().addEventFilter(ActionEvent.ACTION, new ResizeListener(3));
        view.getResize5().addEventFilter(ActionEvent.ACTION, new ResizeListener(5));
        view.getResize8().addEventFilter(ActionEvent.ACTION, new ResizeListener(8));
        view.getComboBox().addEventFilter(ActionEvent.ACTION, new ComboListener());
        //view.getGroup().addEventFilter(ActionEvent.ACTION, new ResizeListener(8));
    }

    private class NewDayListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            model.newDay();
        }
    }

    private class ResizeListener implements EventHandler<ActionEvent> {
        private final int size;
        public ResizeListener(int sz) {
            super();
            size = sz;
        }

        @Override
        public void handle(ActionEvent e) {
            model.resize(size);
        }
    }

    private class ComboListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            model.combo(view.getComboBox().getSelectionModel().getSelectedItem());
        }
    }
}