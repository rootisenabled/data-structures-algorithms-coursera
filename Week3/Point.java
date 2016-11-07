import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Point implements Comparable<Point> {
    
    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private class SlopeOrder implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            double a = Point.this.slopeTo(p1);
            double b = Point.this.slopeTo(p2);
            if (a < b) {
                return -1;
            }
            else if (a == b) {
                return 0;
            }
            else 
                return 1;
        }
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point that) {
        if (that == null)
            throw new NullPointerException();
        double deltaX = (double) (that.x - this.x);
        double deltaY = (double) (that.y - this.y);
        double slope = deltaY / deltaX;
        
        if (deltaX == 0 && deltaY == 0) {
           return Double.NEGATIVE_INFINITY;
        }
        if (deltaX == 0) {
           return Double.POSITIVE_INFINITY;
        }
        if (deltaY == 0) {
           return 0.0;
        }
        else {
           return slope;
        }
    }

    public int compareTo(Point that) {
        if (this.y < that.y || (this.y == that.y && this.x < that.x))
            return -1;
        else if (that.x == this.x && that.y == this.y)
            return 0;
        else
            return 1;
    }
    
    public Comparator<Point> slopeOrder() {
        return new SlopeOrder();
    }
    
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public static void main(String[] args) {
        Point p = new Point(2, 2);
        Point q1 = new Point(0, 2);
        Point q2 = new Point(2, 0);
        Point q3 = new Point(3, 4);
        Point q4 = new Point(4, 3);
        
        StdOut.println(p.slopeTo(q1));
        StdOut.println(p.slopeTo(q2));
        StdOut.println(p.slopeTo(q3));
        StdOut.println(p.slopeTo(q4));
        StdOut.println("==================");
        StdOut.println(p.compareTo(q1));
        StdOut.println(p.compareTo(q2));
        StdOut.println(p.compareTo(q3));
        StdOut.println(p.compareTo(q4));
        StdOut.println(q1.toString());
    }
}