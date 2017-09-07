import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private Point[] a;
    double[] slope = new double[4];
    ArrayList<LineSegment> segs = new ArrayList<LineSegment>();
    
    /**
     * Has to take an array of points!
     * 
     */
    public BruteCollinearPoints(Point x, Point y, Point z, Point w) { // finds all line segments containing 4 points
        if ( x != null &&  y != null &&  z != null &&  w != null) {
            a[0] = x;
            if (y == x) { throw new java.lang.IllegalArgumentException(); } 
            a[1] =y;
            if (z == x || z == y) { throw new java.lang.IllegalArgumentException(); }
            a[2] = z;
            if (w == x || w == y || w == z) { throw new java.lang.IllegalArgumentException(); }
            a[3] = w;
        }
        else {
            throw new java.lang.IllegalArgumentException();
        }
        for (int i = 1; i < 4; i++){
            slope[i] = a[0].slopeTo(a[i]);
                if (slope[1] == slope[i]) {
                    if (i ==3) {
                        Arrays.sort(a);
                        segs.add(new LineSegment(a[0],a[3]));
                    } 
                }
                else { break; }
        }
    }
    
    public int numberOfSegments() {// the number of line segments
        return segs.size();
    }
    
    public LineSegment[] segments() { // the line segments
        LineSegment[] sgmts = segs.toArray(new LineSegment[segs.size()]);
        return sgmts;
    }
}