import java.util.Arrays;
import java.util.ArrayList;

public class BruteCollinearPoints {
    private int num;
    private ArrayList<LineSegment> segment = new ArrayList<LineSegment>();
    
    public BruteCollinearPoints(Point[] points) {
         if (points == null)
             throw new java.lang.NullPointerException();
         num = 0;
         Point[] copy = new Point[points.length];
         for (int i = 0; i < points.length; i++) {
             copy[i] = points[i];
         }
         Arrays.sort(copy);
         for (int i = 0; i < copy.length - 1; i++) {
             if (copy[i].compareTo(copy[i + 1]) == 0) {
                 throw new java.lang.IllegalArgumentException();
             }
         }
         for (int i = 0; i < copy.length - 3; i++) {
             for (int j = i + 1; j < copy.length - 2; j++) {
                 double slope1 = copy[i].slopeTo(copy[j]);
                 for (int k = j + 1; k < copy.length - 1; k++) {
                     double slope2 = copy[i].slopeTo(copy[k]);
                     if (slope1 != slope2)
                         continue;
                     int temp = 0;
                     for (int l = k + 1; l < copy.length; l++) {
                         double slope3 = copy[i].slopeTo(copy[l]);
                         if (slope1 == slope3)
                             temp = l;
                         if ((l == copy.length - 1) && (temp != 0)) {
                             num++;
                             segment.add(new LineSegment(copy[i], copy[temp]));
                        }
                     }
                 }
             }
         }
     }
    
    public int numberOfSegments() {
       return num;
    }
    
    public LineSegment[] segments() {
       LineSegment[] copyOfSegments = new LineSegment[num];
       for (int i = 0; i < num; i++) {
          copyOfSegments[i] = segment.get(i);
       }
       return copyOfSegments;
    }
}