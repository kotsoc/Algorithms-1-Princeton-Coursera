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
            helpArr = Arrays.copyOfRange(points, i, points.length);
            double[] slope = new double[points.length];   
            Comparator<Point> slopeOrd = points[i].slopeOrder();
            // Exception for null element
            if (points[i] == null) { throw new java.lang.IllegalArgumentException(); }  
            Arrays.sort(helpArr, slopeOrd);
            int z = 0; // Colinear point counter
            for (int j = 0; j < helpArr.length-1; j++) {
            // Exception for duplicates
                if (i != j) {
                    if (points[j] == points[i]) { 
                        throw new java.lang.IllegalArgumentException(); 
                    }
                }
                if (points[i].slopeTo(helpArr[j]) == points[i].slopeTo(helpArr[j+1])) {
                    z++;
                }
                else if(z > 1) {
                    Point[] colPoints = new Point[z+2];
                    colPoints[z+1] = points[i];
                    for (int w = 0; w <=z; w++){
                        colPoints[w] = helpArr[j-w];
                    }
                    z=0;
                    Arrays.sort(colPoints);
                    segz.add(new LineSegment(colPoints[0], colPoints[z+1]));
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