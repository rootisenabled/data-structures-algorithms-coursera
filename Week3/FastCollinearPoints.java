import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class FastCollinearPoints {

    private ArrayList<LineSegment> lineSegments;

    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new NullPointerException();
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new NullPointerException();
        }
        this.lineSegments = new ArrayList<LineSegment>();
        for (int i = 0; i < points.length; i++) {
            Comparator<Point> pointComparator = points[i].slopeOrder();
            Point[] sortedSlopes = new Point[points.length - (i + 1)];
            System.arraycopy(points, i + 1, sortedSlopes, 0, points.length
                    - (i + 1));
            Arrays.sort(sortedSlopes, 0, sortedSlopes.length, pointComparator);
            Point min = points[i];
            Point max = points[i];
            double slope = -1;
            int collinears = 1;
            boolean lineAlreadyAdded = false;
            for (int j = 0; j < sortedSlopes.length; j++) {
                if (slope == Double.NEGATIVE_INFINITY)
                    throw new IllegalArgumentException();
                if (points[i].slopeTo(sortedSlopes[j]) == slope) {
                    collinears++;
                    if (sortedSlopes[j].compareTo(max) >= 0)
                        max = sortedSlopes[j];
                    else if (sortedSlopes[j].compareTo(min) < 0)
                        min = sortedSlopes[j];
                    if (j != sortedSlopes.length - 1)
                        continue;
                }
                if (collinears >= 4) {
                    for (int k = i - 1; k >= 0; k--) {
                        if (points[k].slopeTo(max) == slope) {
                            // We have already added this Line
                            lineAlreadyAdded = true;
                        }
                    }
                    if (!lineAlreadyAdded)
                        lineSegments.add(new LineSegment(min, max));
                }
                lineAlreadyAdded = false;
                slope = points[i].slopeTo(sortedSlopes[j]);
                collinears = 2;
                if (sortedSlopes[j].compareTo(points[i]) >= 0) {
                    max = sortedSlopes[j];
                    min = points[i];
                } else if (sortedSlopes[j].compareTo(points[i]) < 0) {
                    min = sortedSlopes[j];
                    max = points[i];
                } else {
                    min = points[i];
                    max = points[i];
                }
            }
        }
    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        LineSegment[] ret = new LineSegment[lineSegments.size()];
        ret = lineSegments.toArray(ret);
        return ret;
    }
    
    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
        
        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}