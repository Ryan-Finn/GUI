
import Controller.BasicController;
import Controller.ErrorCheckingController;
import Controller.Controller;
import Model.LineSegment;
import View.BasicView;
import View.FancyView;
import View.LineView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Controller controller = null;
        LineView view = null;
        LineSegment model = new LineSegment();

        Scanner cin = new Scanner(System.in);
        int choice = 0;

        System.out.println("---Basic setup---");

        //setup view
        System.out.print("Choose view type. 0) Basic 1) Fancy: ");
        choice = cin.nextInt();
        if(choice != 1){

            if(choice != 0)
                System.out.println("Unknown choice. Basic chosen.");
            view = new BasicView(model);

        }else {

            view = new FancyView(model);
        }


        //setup controller
        System.out.print("Choose controller type. 0) Basic 1) Error checking: ");
        choice = cin.nextInt();
        if(choice != 1){

            if(choice != 0)
                System.out.println("Unknown choice. Basic chosen.");

            controller = new BasicController(cin, view, model );

        }else {
            controller = new ErrorCheckingController(cin, view, model );
        }

        System.out.println("Setup complete, start running");
        System.out.println("-------------------------------------------------\n");
        controller.RunMenu();

    }
}
