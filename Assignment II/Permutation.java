
/**
 * This class represents a permutation object that takes a integer 
 * n as a parameted and then reads n strings from standard input
 * and prints them in uniformly random order
 * 
 * This implementation useds Randomized queue object to store
 * the strings
 * 
 * @author Konstantinos Peratinos
 */

import java.util.Iterator;
import edu.princeton.cs.algs4.StdIn;


public class Permutation {
    
    public static void main(String args[]) {
        RandomizedQueue<String> rQ = new RandomizedQueue<String>();;
        int n;
        n = Integer.parseInt(args[0]);
//        String[] temp = StdIn.readString();
        for (int i=0; i<n; i++) {
            rQ.enqueue(StdIn.readString());
        }
        Iterator<String> it = rQ.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }
    
}