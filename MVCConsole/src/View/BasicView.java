package View;

import Model.LineSegment;

public class BasicView extends LineView {

    public BasicView(LineSegment l) {
        super(l);
    }

    @Override
    public void DisplayLeftPoint() {
        System.out.println( line.getLeftPoint().getX() + " "+ line.getLeftPoint().getY());
    }

    @Override
    public void DisplayRightPoint() {
        System.out.println( line.getRightPoint().getX() + " "+ line.getRightPoint().getY());
    }


    @Override
    public void DisplayMenu() {
        String menu = "1: Edit Left point\n" +
                "2: Edit Right point\n" +
                "3: Display line\n" +
                "4: Reset line\n" +
                "0: Exit\n" +
                "Choice: ";
       System.out.print(menu);
    }

}
