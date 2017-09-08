/*************************************************************************
 *  A class that find colinear points from a collection of 2d
 *  point. This version is the brute one with no optimizations
 * <p>
 * This class was created for the 3rd assignment of
 * Princeton's algorithm course in coursera
 *
 * @author Konstantinos Peratinos
 * @version 1.0
 *************************************************************************/

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private double[] slope;
    private ArrayList<LineSegment> segs = new ArrayList<LineSegment>();
    
    /**
     * The constructor of the class, most of the work
     * is performed here.
     * 
     * @param an array of point objects
     */
    public BruteCollinearPoints(Point[] points) { // finds all line segments containing 4 points
        for (int i=0; i < points.length; i++) {
            slope = new double[points.length];
            if (points[i] == null) { throw new java.lang.IllegalArgumentException(); }
            for (int j = 0; j <points.length ; j++) {
                if (i != j) {
                    if (points[j] == null || points[j] == points[i]) { 
                        throw new java.lang.IllegalArgumentException(); 
                    }
                     slope[j] = points[i].slopeTo(points[j]);
////                     System.out.println(slope[j]);
                }
            }
            for (int k = 0; k < points.length; k++) {
                int z = 0; // Colinear point counter
                Point[] col_points = new Point[4];
                for (int m = points.length-1; m > k; m--){
                    if (slope[k] == slope[m] && k != m) {
                        col_points[z++] = points[m];
                        System.out.println("Found one" + m);
                    } 
                }
                if (z == 3) {
                    col_points[z] = points[i];
                    Arrays.sort(col_points);
                    LineSegment line =new LineSegment(col_points[0], col_points[3]);
                    if ( !segs.contains(line)){
                        segs.add(new LineSegment(col_points[0], col_points[3]));
                    }
                }
            }
        }
    } 
             
    
    public int numberOfSegments() { // the number of line segments
        return segs.size();
    }
    
    public LineSegment[] segments() { // the line segments
        System.out.println(segs.size());
        LineSegment[] sgmts = segs.toArray(new LineSegment[segs.size()]);
        return sgmts;
    }
}