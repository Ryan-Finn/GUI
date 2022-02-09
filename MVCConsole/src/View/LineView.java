package View;

import Model.LineSegment;

public abstract class LineView {

    protected LineSegment line;

    public LineView(LineSegment l) {
       line = l;
    }

    public void DisplayGetPoint(){
        System.out.print("Point (x y): ");
    }

    public abstract void DisplayLeftPoint();
    public abstract void DisplayRightPoint();

    public void DisplayNoOption() {
        System.out.println( "This option does not exist. Please choose another." );
    }

    public void DisplayLine(){
        DisplayLeftPoint();
        DisplayRightPoint();
    }
    public abstract void DisplayMenu();

}
