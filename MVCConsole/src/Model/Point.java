package Model;

public class Point {
    private int x = 0;
    private int y = 0;

    public Point() {
        x = 0;
        y = 0;
    }

    public Point(int x1, int y1) {
        x = x1;
        y = y1;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
