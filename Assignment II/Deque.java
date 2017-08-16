import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  The class represents a double ended queue
 * also know as Deque which supports adding and removing items 
 * from both ends. This class was created for the 2nd assignment of 
 * the algorithms I class that is hosted in coursera.org
 * <p>
 * The implementantion uses a double linked list with a
 * nested class for the list nodes
 * 
 *  @author Konstantinos Peratinos
 */


public class Deque<Item> implements Iterable<Item> {
    
    private Node first = null; // first node
    private Node last = null; // last node
    private int n; // size of the Deque
    
    private class Node {                 // inner class to represent each node
        private Item item;
        private Node next;
        private Node previous;
    }
    
    public Deque() {                           // construct an empty deque
        first = null;
        last = null;
        n=0;
    }
    
    public boolean isEmpty() {                 // is the deque empty?
        return first == null;
    }
    
    public int size() {                        // return the number of items on the deque
        return n;
    }
    
    public void addFirst(Item item) {          // add the item to the front
        if (n > 0) {
            if (item != null) {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            oldFirst.previous = first;
            first.next = oldFirst;
            }
             else {
                throw new java.lang.IllegalArgumentException();
            }
        }
        else {
            first = new Node();
            first.item = item;
            last = first;
        }
        n++;
    }
    
    
    public void addLast(Item item) {           // add the item to the end
        if (n > 0) {
            if (item != null) {
                Node oldLast = last;
                last = new Node();
                last.item = item;
                last.previous = oldLast;
                oldLast.next = last;
            }
            else {
                throw new java.lang.IllegalArgumentException();
            }
        }
        else {
        last = new Node();
        last.item = item;
        first = last;
        }
        n++;
    }
    
    public Item removeFirst() {                // remove and return the item from the front
        if (n > 0) {
            Item item = first.item;
            first.item =null;
            first = first.next;
            n--;
            return item;
        }
        else {
            throw new java.util.NoSuchElementException();
        }
    }
    
    public Item removeLast() {                 // remove and return the item from the end
        if (n > 0) {
            Item item = last.item;
            last.item =null;
            last = last.previous; /// check again
            n--;
            return item;
        }
        else {
            throw new java.util.NoSuchElementException();
        }
    }
    
    public Iterator<Item> iterator() {         // return an iterator over items in order from front to end
        return new ListIterator();
    }
    
    private class ListIterator implements Iterator<Item> { // An iterator class for the Deque
        private Node current = first;
        
        public boolean hasNext() { return current!=null; }
        
        public void remove() { throw new UnsupportedOperationException(); }
        
        public Item next() {
            if(!hasNext()) { throw new NoSuchElementException();}
            Item item = current.item;
            current = current.next;
            return item;
        }
        }
    
    public static void main(String[] args) {   // unit testing (optional)
        Deque<String> deck = new Deque<String>();
        for( String a: args) {
            deck.addFirst(a);
            deck.addLast(a);
        }
//        Iterator<String> it = deck.iterator();
//        for (int i = 0; i <12; i++){
//            System.out.println(deck.removeFirst());         
//        
//        while (it.hasNext()) {
//            String str = it.next();
//            System.out.println(str+ " ");
//        }
    }}