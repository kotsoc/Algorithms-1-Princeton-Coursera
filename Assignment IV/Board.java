
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.ArrayList;

public class Board  {
    private final int[] tiles;
    private int blank,hamm,manh2;
    private final int length;
    private final int dim;

    public Board(int[][] blocks) {           // construct a board from an n-by-n array of blocks (where blocks[i][j] = block in row i, column j)
        length =blocks.length*blocks.length;
        tiles = new int[length];
        dim = blocks.length;
        hamm = 0;
        manh2 = 0;
        // Converting it to 1 d array for better perfomance
        for (int i = 0; i < length; i++) {
            int ii = i / dim;
            int jj = i % dim;
            tiles[i] = blocks[ii][jj];
            if (blocks[ii][jj] != 0 ) {
                int regI = (tiles[i]-1) / dim;
                int regJ = (tiles[i]-1) % dim;
                manh2 += Math.abs(ii-regI) + Math.abs(jj-regJ);
                if (blocks[ii][jj] != i+1) {
                    hamm++;
                }
            } else {
                blank = i;
            }
        }
    }

    private Board (int [] tilez) { // Constructor for 1-d array
        length =tilez.length;
        tiles = tilez;
        hamm = 0;
        manh2 = 0;
        dim = (int) Math.sqrt(length);

        // Converting it to 1 d array for better perfomance
        for (int i = 0; i < length; i++) {
            int ii = i / dim;
            int jj = i % dim;
            if (tiles[i] != 0) {
                if (tiles[i] != i+1) {
                    int regI = (tiles[i]-1) / dim;
                    int regJ = (tiles[i]-1) % dim;
                    manh2 += Math.abs(ii-regI) + Math.abs(jj-regJ);
                    hamm++;
                }
            } else {
                blank = i;
            }
        }

    }

//    private static int[][] Doubledim (int tilez[]) {
//        int dim = (int)Math.sqrt(tilez.length);
//        int blockz[][] = new int[dim][dim];
//        for (int i = 0; i < tilez.length; i++) {
//            blockz[i/dim][i%dim]= tilez[i];
//        }
//        return blockz;
//    }
//
//    private static int[] oneDim (int[][] blockz) {
//        int length =blockz.length;
//        int tilez[] = new int[length*length];
//        for (int i = 0; i < length; i++) {
//            tilez[i] = blockz[i / length][i % length];
//        }
//        return tilez;
//    }


    public int dimension() {
        return (int)Math.sqrt(length);
    }

    public int hamming() {                   // number of blocks out of place
        return hamm;
    }

    public int manhattan() {                 // sum of Manhattan distances between blocks and goal

        return manh2;
    }

    public boolean isGoal() {                // is this board the goal board?
        return hamm == 0;
    }

    public Board twin() {                    // a board that is obtained by exchanging any pair of blocks
        int[] twin = tiles.clone();
        if (blank/dim !=0){
            twin[0] = tiles[1];
            twin[1] = tiles[0];
        } else {
            twin[dim] = tiles[dim+1];
            twin[dim+1] = tiles[dim];
        }

        return new Board(twin);
    }

    public boolean equals(Object y) {        // does this board equal y?
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if (length != that.length) return false;
        return Arrays.equals(this.tiles, that.tiles);
    }

    public Iterable<Board> neighbors() {     // all neighboring boards
        Stack<Board> neighb =  new Stack<Board>();
        //int n1[][] = new int[dim][dim];
        int[] neigh;
        // Tile to the right moves to blank
        if ((blank%dim)+1 < dim) {
            neigh = tiles.clone();
            neigh[blank] = tiles[blank+1];
            neigh[blank+1] = tiles[blank];
            neighb.push(new Board(neigh));
        }
        // Tile to the left moves to blank
        if ((blank%dim)-1 >= 0) {
            neigh = tiles.clone();
            neigh[blank] = tiles[blank-1];
            neigh[blank-1] = tiles[blank];
            neighb.push(new Board(neigh));
        }
        // Tile to the bottom moves to blank
        if (blank+dim < length) {
            neigh = tiles.clone();
            neigh[blank] = tiles[blank+dim];
            neigh[blank+dim] = tiles[blank];
            neighb.push(new Board(neigh));
        }
        // Tile to the top moves to blank
        if (blank-dim >= 0) {
            neigh = tiles.clone();
            neigh[blank] = tiles[blank-dim];
            neigh[blank-dim] = tiles[blank];
            neighb.push(new Board(neigh));
        }
        return neighb;
    }

    public String toString() {               // string representation of this board (in the output format specified below)
        StringBuilder s = new StringBuilder();
        s.append(dimension() + "\n");
        for (int i = 0; i < length; i++) {
            s.append(String.format("%2d ", tiles[i]));
            if ( i % dimension() == dimension()-1 ) { //i/dimension() > 0 &&
                s.append("\n");
            }
        }
        return s.toString();
    }

    public static void main(String[] args) {
        // For testing
//        In in = new In(args[0]);
//        int n = in.readInt();
//        int[][] blocks = new int[n][n];
//        for (int i = 0; i < n; i++)
//            for (int j = 0; j < n; j++)
//                blocks[i][j] = in.readInt();
//        Board initial = new Board(blocks);
//        StdOut.println(initial.hamming());
//        StdOut.println("is"+initial.manh2);
//        StdOut.println(Arrays.toString(initial.tiles));
//        //        StdOut.println(initial.toString());
//        //ArrayList<Board> a = (ArrayList<Board>)initial.neighbors();
        // solve the puzzle
        //Solver solver = new Solver(initial);

    }
}