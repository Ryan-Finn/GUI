package Controller;

import Model.LineSegment;
import Model.Point;
import View.LineView;

import java.util.Scanner;

public class BasicController extends Controller{


    public BasicController(Scanner scan, LineView v, LineSegment p) {
        super(scan, v, p);
    }

    @Override
    public void RunMenu() {
        int choice;
        Point p = null;
        int index = 0;

        do{
            view.DisplayMenu();
            choice = cin.nextInt();

            switch(choice){
                case 1: //edit left
                    view.DisplayGetPoint();
                    p = new Point(cin.nextInt(), cin.nextInt());
                    model.SetLeft(p);
                    break;

                case 2: //edit right
                    view.DisplayGetPoint();
                    p = new Point(cin.nextInt(), cin.nextInt());
                    model.SetRight(p);
                    break;

                case 3: //display
                    view.DisplayLine();
                    break;

                case 4: //reset
                    model.Reset();
                    break;

                case 0:
                    break;
                default:
                    view.DisplayNoOption();
                    break;
            }
        }while(choice != 0);
    }
}
