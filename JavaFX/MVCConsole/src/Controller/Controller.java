package Controller;

import Model.LineSegment;
import View.LineView;

import java.util.Scanner;

public abstract class  Controller {
    protected Scanner cin;
    protected LineView view;
    protected LineSegment model;

    public Controller(Scanner scan, LineView v, LineSegment p){
        cin = scan;
        view = v;
        model = p;
    }
    public abstract void RunMenu();
}
