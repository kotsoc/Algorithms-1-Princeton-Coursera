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
nullC = 2;
}

public boolean isEmpty() {                 // is the queue empty?
return nullC == randQ.length;
}

public int size() {                        // return the number of items on the queue
    return n;
}

public void enqueue(Item item) {           // add the item
    if (item == null) {
        throw new java.lang.IllegalArgumentException();
    }
    if (nullC == 0) { 
        resize(randQ.length*2);
        nullC = randQ.length /2;
    }
    int i =randQ.length-1;
    while (randQ[i] != null && i > 0){
        i--;
    }
    randQ[i] = item;
    n++;
    nullC--;
}

public Item dequeue() {                    // remove and return a random item
    if (n == 0) { throw new java.util.NoSuchElementException();}
    int element = StdRandom.uniform(randQ.length);
    while(randQ[element] == null) {
        element = StdRandom.uniform(randQ.length);
    }
    Item item = randQ[element];
    randQ[element] = null;
    nullC++;
    n--;
    if (nullC > (3*randQ.length/4) && randQ.length > 2) {
        resize(randQ.length/2);
    }
    return item;
}

public Item sample() {                     // return (but do not remove) a random item
    if (n == 0) { throw new java.util.NoSuchElementException();}
    int element = StdRandom.uniform(randQ.length);
    while(randQ[element] == null) {
        element = StdRandom.uniform(randQ.length);
    }
    return randQ[element];
}

private void resize(int capacity) {
    assert capacity >=n;
//    F
    Item[] copy = (Item[]) new Object[capacity];
    int j = 0;
    for (int i = 0; i < randQ.length; i++) {
        if ( randQ[i] != null) {copy[j++] = randQ[i];}
    }
    nullC = capacity -n;
    randQ = copy;
}

public Iterator<Item> iterator() {         // return an independent iterator over items in random order
    StdRandom.shuffle(randQ);
    
    return new rQueueIterator();
}
private class  rQueueIterator implements Iterator<Item> {
    int count = 0;
    
    public boolean hasNext() {
        return count < n; 
    }
    
    public void remove() { throw new UnsupportedOperationException(); }
    
    public Item next() {
        if (!hasNext()) { throw new NoSuchElementException();}
        while(randQ[count] == null && count < randQ.length) {
        count++;
        }
        return randQ[count++];
    }
}
public static void main(String[] args) {   // unit testing (optional)
    RandomizedQueue<String> r = new RandomizedQueue<String>();
//        for( String a: args) {
//            r.enqueue(a);
//            r.enqueue(a);
//        }
        int leng = r.size();
        for (int i = 0; i <leng; i++){
            System.out.println(r.dequeue());         
        }
        for (Integer i = 0; i < 1000; i++){
           r.enqueue(i.toString());
           if (i%33 ==0){
           System.out.println("Nullcounter is "+r.nullC);
           System.out.println("Elements "+r.n);}
        }
        Iterator<String> it = r.iterator();
        while (it.hasNext()) {
            String str = it.next();
            System.out.println(str+ " ");
        }
        r.enqueue("abc");
////        r.enqueue("gma");
        System.out.println(r.dequeue());
}
}