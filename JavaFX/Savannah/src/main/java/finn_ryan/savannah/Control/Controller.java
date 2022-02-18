package finn_ryan.savannah.Control;

import finn_ryan.savannah.Model.Animal;
import finn_ryan.savannah.Model.Savannah;
import finn_ryan.savannah.View.LayoutView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;

public class Controller {
    private final Savannah model;
    private final LayoutView view;

    public Controller(Savannah data, LayoutView view) {
        this.view = view;
        model = data;

        view.getNewDay().addEventFilter(ActionEvent.ACTION, new NewDayListener());
        view.getResize3().addEventFilter(ActionEvent.ACTION, new ResizeListener(3));
        view.getResize5().addEventFilter(ActionEvent.ACTION, new ResizeListener(5));
        view.getResize8().addEventFilter(ActionEvent.ACTION, new ResizeListener(8));
        view.getComboBox().addEventFilter(ActionEvent.ACTION, new ComboListener());
        view.getGroup().selectedToggleProperty().addListener((observableValue, toggle, t1) -> model.radio(((RadioButton)t1).getText()));
        for (Animal animal : model.getSavannah()) {
            animal.addEventFilter(ActionEvent.ACTION, new AnimalListener(animal));
        }
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

    private class AnimalListener implements EventHandler<ActionEvent> {
        private final Animal animal;
        public AnimalListener(Animal button) {
            super();
            animal = button;
        }

        @Override
        public void handle(ActionEvent e) {
            model.onClick(animal);
        }
    }
}