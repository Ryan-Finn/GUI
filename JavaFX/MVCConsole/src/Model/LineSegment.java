package Model;

import java.util.LinkedList;

public class LineSegment {
    private Point leftPoint = new Point(0,0);
    private Point rightPoint = new Point(1,1);

    public void Reset(){
       leftPoint = new Point(0,0);
       rightPoint = new Point(1,1);
    }

    public void SetLeft(Point p){
        leftPoint = p;
    }

    public void SetRight(Point p){
        rightPoint = p;
    }

    public Point getLeftPoint() {
        return leftPoint;
    }

    public Point getRightPoint() {
        return rightPoint;
    }

}
