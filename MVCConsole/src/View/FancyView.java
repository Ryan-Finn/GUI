package View;

import Model.LineSegment;
import Model.Point;

public class FancyView  extends LineView {
    public FancyView(LineSegment p) {
        super(p);
    }

    @Override
    public void DisplayLeftPoint() {
        System.out.println( "Left: (" + line.getLeftPoint().getX() + ", " + line.getLeftPoint().getY() +")" );
    }

    @Override
    public void DisplayRightPoint() {
        System.out.println( "Right: (" + line.getRightPoint().getX() + ", " + line.getRightPoint().getY() +")" );
    }


    @Override
    public void DisplayMenu() {
        String menu = "1: Edit Left point\n" +
                "2: Edit Right point\n" +
                "3: Display line\n" +
                "4: Reset line\n" +
                "0: Exit\n" +
                "--------------------------\n" +
                "Choice: ";
        System.out.print(menu);
    }

}
