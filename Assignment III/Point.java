/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *  
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import java.util.Scanner;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point
//    public static final Comparator<Point> slopeOrd;

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        if (this.y == that.y) { 
            if (this.x == that.x) {
                return Double.NEGATIVE_INFINITY;
            } 
            else { return 0; }
        }        
        else if (this.x == that.x) {
            return Double.POSITIVE_INFINITY;
        }
        else {
            return (double)(that.y - this.y) / (that.x - this.x);
        }
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        if (this.y > that.y) { 
            return 1;
        }
        else if (this.y < that.y) {
            return -1;
        }
        else {
            if (this.x > that.x) { return 1; }
            else if (this.x < that.x) { return -1; }
            else { return 0; }
        }
    }

    
    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new slopeComparator();
    }
    

    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
    
    private class slopeComparator implements Comparator<Point> {
        public int compare(Point i, Point ii) {
            if (slopeTo(i) > slopeTo(ii)) {
                return 1;
            }
            else if (slopeTo(i) < slopeTo(ii)) {
                return -1;
            }
            else { return 0; }
    }
    }
    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
////        int x,y;
////        int j = 0;
////        if (args.length > 0){ 
////            n = Integer.parseInt(args[0]);
////            Point[] arr = new Point[n];
////        }
////        else{ 
////            throw new java.lang.IllegalArgumentException(); 
////        }
////        for (i = 0; i < n; i=+2) {
////            x = Integer.parseInt(args[i]);
////            y = Integer.parseInt(args[i+1]);
////            arr[j++] = Point(x, y);
////        }
////        BruteCollinearPoints brut = new BruteCollinearPoints
//        Point[] arr = new Point[7];
//        arr[0] = new Point(3, 6);
//        arr[2] = new Point(7, 4);
//        arr[3] = new Point(0, 0);
//        arr[6] = new Point(2, 3);
//        arr[4] = new Point(8, 9);
//        arr[1] = new Point(3, 5);
//        arr[5] = new Point(1, 4);
////         final Comparator<Point> comp = arr[5].slopeOrder(); 
//        Arrays.sort(arr);
//        for (int i = 0; i < 7; i++) {
//            System.out.println(arr[i].toString());
////            System.out.println(arr[0].slopeTo(arr[i]));
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
        System.out.println(segment);
        segment.draw();
    }
    StdDraw.show();
}

        
   
}