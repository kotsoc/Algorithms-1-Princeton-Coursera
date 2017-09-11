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
    private ArrayList<LineSegment> segs = new ArrayList<LineSegment>();
    private ArrayList<Integer> usedIndex = new ArrayList<Integer>();
    
    /**
     * The constructor of the class, most of the work
     * is performed here.
     * 
     * @param an array of point objects
     */
    public BruteCollinearPoints(Point[] points) { // finds all line segments containing 4 points
        if (points == null) { throw new java.lang.IllegalArgumentException(); }
        double[] slope;
        
        for (int i=0; i < points.length; i++) {
            slope = new double[points.length-i];
            if (points[i] == null) { throw new java.lang.IllegalArgumentException(); }
            
            for (int j = points.length-1; j > i ; j--) {
                if (i != j && j-i != i) {
                    if (points[j-i] == null || points[j-i] == points[i]) { 
                        throw new java.lang.IllegalArgumentException(); 
                    }
                }
//                     System.out.println(slope[j-i] = points[i].slopeTo(points[j-i]));
                    slope[j-i] = points[i].slopeTo(points[j]);
            }
            for (int k = 0; k < slope.length; k++) {
                int z = 0; // Colinear point counter
                Point[] col_points = new Point[4];
                
                for (int m = slope.length-1; m > k; m--){
                    if (slope[k] == slope[m] && k != m && !usedIndex.contains(m)) {
                        col_points[z++] = points[m+i];
                        usedIndex.add(m+i);
                    } 
                }
                if (z == 2) {
                    col_points[z] = points[i];
                    col_points[z+1] = points[k+i];
                    Arrays.sort(col_points);
                    usedIndex.add(i);
                    usedIndex.add(k+i);
                    segs.add(new LineSegment(col_points[0], col_points[3]));
                    z=0;
                }
                else {
                    int size = usedIndex.size();
                    for (int y =0; y < z; y++)
                    {
                       usedIndex.remove(size-1-y);
                    }
                }
            }
        }
    } 
             
    
    public int numberOfSegments() { // the number of line segments
        return segs.size();
    }
    
    public LineSegment[] segments() { // the line segments
//         System.out.println("Size is" +  numberOfSegments());
        LineSegment[] sgmts = segs.toArray(new LineSegment[segs.size()]);
        return sgmts;
    }
}