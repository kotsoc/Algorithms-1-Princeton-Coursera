/**
 * This class represents a randomized queue, a contruct
 * similar to a stack/queue but with randomzed pop.
 * It was created for the 2nd assignment of Princeton Algorithm I course
 * 
 * This implmentation of a Randomized queue uses an resizable arrray in
 * an effort to use less memory nd have faster amortized times
 * 
 * @author Konstantinos Peratinos
 */

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
private Item[] randQ;
private int n; // number of elements
private int nullC; // number of null elements ????

public RandomizedQueue() {                 // construct an empty randomized queue
// Keep a Null counter
randQ = (Item[]) new Object[2];
n = 0;
}

public boolean isEmpty() {                 // is the queue empty?
return n ==0;
}

public int size() {                        // return the number of items on the queue
    return n;
}

public void enqueue(Item item) {           // add the item
    if (n == randQ.length) { resize(randQ.length*2);}
    randQ[n++] = item;
}

public Item dequeue() {                    // remove and return a random item
    int element = StdRandom.uniform(randQ.length);
    while(randQ[element] == null) {
        element = StdRandom.uniform(randQ.length);
    }
    Item item = randQ[element];
    randQ[element] = null;
    return item;
}

public Item sample() {                     // return (but do not remove) a random item
}

public void resize(int capacity) {
    assert capacity >=n;
    
    Item[] copy = (Item[]) new Object[capacity];
    
    for (int i = 0; i < n; i++) {
        copy[i] = randQ[i];
    }
    randQ = copy;
}

public Iterator<Item> iterator() {         // return an independent iterator over items in random order
                            }
public static void main(String[] args) {   // unit testing (optional)
}
}