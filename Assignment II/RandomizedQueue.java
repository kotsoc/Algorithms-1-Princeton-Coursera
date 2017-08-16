/**
 * This class represents a randomized queue, a contruct
 * similar to a stack/queue but with randomzed pop.
 * It was created for the 2nd assignment of Princeton Algorithm I course
 * 
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

public RandomizedQueue() {                 // construct an empty randomized queue
}  
public boolean isEmpty() {                 // is the queue empty?
}
public int size() {                        // return the number of items on the queue
}
public void enqueue(Item item) {           // add the item
}
public Item dequeue() {                    // remove and return a random item
}
public Item sample() {                     // return (but do not remove) a random item
}
public Iterator<Item> iterator() {         // return an independent iterator over items in random order
                            }
public static void main(String[] args) {   // unit testing (optional)
}
}