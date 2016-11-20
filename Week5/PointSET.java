import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class PointSET{

 private SET<Point2D> set;

 public PointSET() {
  set = new SET<Point2D>();
 }

 public boolean isEmpty() {
  return set.isEmpty();
 }

 public int size() {
  return set.size();
 }

 public void insert(Point2D p) {
  if ( p == null) {
   throw new java.lang.NullPointerException();
  }
  set.add(p);
 }

 public boolean contains(Point2D p) {
  if ( p == null) {
   throw new java.lang.NullPointerException();
  }
  return set.contains(p);
 }

 public void draw() {
  StdDraw.setPenColor(StdDraw.BLACK);
  StdDraw.setPenRadius(.01);
  for (Point2D p : set) {
   p.draw();
  }
 }

 public Iterable<Point2D> range(RectHV rect) {
  SET<Point2D> range = new SET<Point2D>();

  for (Point2D p : set) {
   if (rect.contains(p)) {
    range.add(p);
   }
  }
  return range;
 }

 public Point2D nearest(Point2D p) {
  if ( p == null) {
   throw new java.lang.NullPointerException();
  }
  Point2D nearest = set.min();
  for (Point2D point : set) {
   if (p.distanceTo(point) < p.distanceTo(nearest))
    nearest = point;
  }
  return nearest;
 }

  public static void main(String[] args) {
    PointSET pset = new PointSET();
    Point2D p = new Point2D(0.2, 0.3);
    RectHV rect = new RectHV(0.2, 0.2, 0.6, 0.6);
    pset.insert(p);
    for (int i = 0; i < 50; i++)
        pset.insert(new Point2D(StdRandom.uniform(), StdRandom.uniform()));
    rect.draw();
    StdDraw.circle(p.x(), p.y(), p.distanceTo(pset.nearest(p)));
    pset.draw();
    StdOut.println("Nearest to " + p.toString() + " = " + pset.nearest(p));
    for (Point2D point : pset.range(rect))
        StdOut.println("In Range: " + point.toString());
  }
}