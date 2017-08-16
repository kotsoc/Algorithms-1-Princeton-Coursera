/*
 * author : Konstantinos Peratinos
 * written : 23/7/2017
 * 
 * Algorithm course week 1 assignment : Percolation
 * 
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    
    private int size;
    private int openCount = 0;
    private boolean[][] grid;
    private WeightedQuickUnionUF quickUnion;
    private WeightedQuickUnionUF halfUnion;
    
    
    public Percolation(int n) { // create n-by-n grid, with all sites blocked
        if (n > 0) {
            this.size = n;
            this.quickUnion = new WeightedQuickUnionUF(size*size+3);
            this.halfUnion = new WeightedQuickUnionUF(size*size+2);
            grid = new boolean[size][size];
//            for (int i = 0; i < size; i++) {
//                quickUnion.union(size*size+1, i); // Connect top row to theoritical root
//                quickUnion.union(size*size+2, size*size-1-i); // connect bottom row to theoretical bottom
//            }
        } 
        else {
            throw new java.lang.IllegalArgumentException();
        }
    }
       
    public void open(int row, int col) { // open site (row, col) if it is not open already
        if (validate(row, col)) { 
            grid[row-1][col-1] = true;
            openCount++;
            if (row > 1 && isOpen(row-1, col)) {
                quickUnion.union((size*(row-1))+col-1, (size*(row-2))+col-1); // Connect to top
                halfUnion.union((size*(row-1))+col-1, (size*(row-2))+col-1); // Connect to top
            }
            if (col > 1 && isOpen(row, col-1)) {
                quickUnion.union((size*(row-1))+col-1, (size*(row-1))+(col-2)); // Connect to left
                halfUnion.union((size*(row-1))+col-1, (size*(row-1))+(col-2)); // Connect to left
            }
            if (row < size && isOpen(row+1, col)) {
                quickUnion.union((size*(row-1))+col-1, (size*row)+col-1); // Connect to bottom
                halfUnion.union((size*(row-1))+col-1, (size*row)+col-1); // Connect to bottom
            }
            if (col < size && isOpen(row, col+1)) {
                quickUnion.union((size*(row-1))+col-1, (size*(row-1)+(col))); // Connect to right
                halfUnion.union((size*(row-1))+col-1, (size*(row-1)+(col))); // Connect to right
            }
            if (row == 1) {
                quickUnion.union(size*size+1, size*(row-1)+col-1); // Connect top row to theoritical root
                halfUnion.union(size*size+1, size*(row-1)+col-1); // Connect top row to theoritical root
            }
            if (row == size) {
                quickUnion.union(size*size+2, size*(row-1)+col-1); // connect bottom row to theoretical bottom
            }
        } 
        else {
            throw new java.lang.IllegalArgumentException();
        }
    }
    
    public boolean isOpen(int row, int col) {  // is site (row, col) open?
        if (validate(row, col)) { 
            return grid[row-1][col-1];
        } 
        else {
            throw new java.lang.IllegalArgumentException();
        }
    }
    
    public boolean isFull(int row, int col) {  // is site (row, col) full?
        if (validate(row, col)) {
                return isOpen(row, col) && halfUnion.connected((size*(row-1))+col-1, size*size+1);
        } 
        else {
            throw new java.lang.IllegalArgumentException();
        }
    }
    
    public int numberOfOpenSites() {       // number of open sites
        return this.openCount;
    }
    
    public boolean percolates() {              // does the system percolate?
        return quickUnion.connected(size*size+1, size*size+2);
    }
    
    private boolean validate(int row, int col) {
       return row <= size && row > 0 && (col <= size && col > 0); 
    }
    
    public static void main(String[] args) {   // test client (optional)per
        Percolation perc = new Percolation(6);
        perc.open(1,6);
        perc.open(2,6);
        perc.open(3,6);
        perc.open(4,6);
        perc.open(5,6);
        //perc.open(5,5);
        perc.open(6,5);
        perc.quickUnion.find(5);
        System.out.println(perc.percolates());
        System.out.println(perc.quickUnion.find(34));
    }
}