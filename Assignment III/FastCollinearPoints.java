/*************************************************************************
 *  A class that find colinear points from a collection of 2d
 *  point. This version is the fast version of the algorithm that make use
 *  of a comparator to sort the results before searching for colinear
 *  points
 * <p>
 * This class was created for the 3rd assignment of
 * Princeton's algorithm course in coursera
 *
 * @author Konstantinos Peratinos
 * @version 1.0
 *************************************************************************/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {
    private ArrayList<LineSegment> segz = new ArrayList<LineSegment>();
     private Point[] helpArr;
    
    public FastCollinearPoints(Point[] points) {   // finds all line segments containing 4 or more points  
        // Exception for Null arguements
        if (points == null) { throw new java.lang.IllegalArgumentException(); }
        // Calculating the slope   
        for (int i=0; i < points.length; i++) {
            // Exception for null element
            if (points[i] == null) { throw new java.lang.IllegalArgumentException(); }
            helpArr = Arrays.copyOfRange(points, i, points.length);  
            Comparator<Point> slopeOrd = points[i].slopeOrder();
            Arrays.sort(helpArr, slopeOrd);
            int z = 0; // Colinear point counter
            for (int j = 0; j < helpArr.length; j++) {
            // Exception for duplicates
                if (i != j) {
                    if (points[j] == points[i] || points[j] == null) { 
                        throw new java.lang.IllegalArgumentException(); 
                    }
                }
                if (j < helpArr.length-1 && points[i].slopeTo(helpArr[j]) == points[i].slopeTo(helpArr[j+1])) {
                    z++;
                } 
                else if(z > 1) {
                    Point[] colPoints = new Point[z+2];
                    colPoints[z+1] = points[i];
                    for (int w = 0; w <=z; w++){
                        colPoints[w] = helpArr[j-w];
                    }
                    Arrays.sort(colPoints);
                    segz.add(new LineSegment(colPoints[0], colPoints[z+1]));
                    z=0;
                } 
                else{
                    z=0;
                }
            }
        }
    }
       
       
    public int numberOfSegments() { // the number of line segments
        return segz.size();
    }
    
    public LineSegment[] segments() { // the line segments
        LineSegment[] sgmts = segz.toArray(new LineSegment[segz.size()]);
        return sgmts;
    }
}