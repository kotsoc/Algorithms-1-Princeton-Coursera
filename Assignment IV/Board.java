import edu.princeton.cs.algs4.MinPQ;
import java.util.Arrays;
import java.util.ArrayList;

public class Board  {
    private Board pred;
    private int[] tiles;
    private int[][] twin,blockz;
    private int hamm,length,blank,blanki,blankj;
    private int dim = 0;

    public Board(int[][] blocks) {           // construct a board from an n-by-n array of blocks (where blocks[i][j] = block in row i, column j)
        length =blocks.length*blocks.length;
        tiles = new int[length];
        dim = dimension();
        twin = new int[dim][dim];
        blockz  = new int[dim][dim];
        // Converting it to 1 d array for better perfomance
        for (int i = 0; i < length; i++) {
            if (i < dim ){
                twin[i] = blocks[i].clone();
                //blockz[i] = blocks[i].clone();
            }
            tiles[i] = blocks[i/dim][i%dim];
            if (blocks[i/dim][i%dim] == 0 ) {
                blank = i;
                blanki = i/dim;
                blankj = i%dim;
            } else if (blocks[i/dim][i%dim] != i){
                hamm++;
            }
        }
        // Creating the twin
        if (length > 3) {
            twin[1][0] = blocks[1][1];
            twin[1][1] = blocks[1][0];
        }
    }

    public int dimension() {
        return (int)Math.sqrt(length);
    }

    public int hamming() {                   // number of blocks out of place
        return hamm;
    }

    public int manhattan() {                 // sum of Manhattan distances between blocks and goal
        int i = 0, j = 0, sum = 0;
        //dim1 = dimension();
        for (int k : tiles) {
            if (k != 0) {
                int regI = k%dim;
                int regJ = k/dim;
                sum += Math.abs(i-regI) + Math.abs(j-regJ);
            }
            i++;
            if (i == dim) {
                i = 0;
                j++;
            }
        }
        return sum;
    }

    public boolean isGoal() {                // is this board the goal board?
        return hamm == 0;
    }

    public Board twin() {                    // a board that is obtained by exchanging any pair of blocks
        return new Board(twin);
    }

    public boolean equals(Object y) {        // does this board equal y?
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        for (int k = 0; k < length; k++){
            if (this.tiles[k] != that.tiles[k]) return false;
        }
        return true;
    }

    public Iterable<Board> neighbors() {     // all neighboring boards
        ArrayList<Board> neighb =  new ArrayList<Board>();
        int n1[][] = new int[dim][dim];
        System.out.println(Arrays.deepToString(blockz));
        // Tile to the right moves to blank
        if ((blank%dim)+1 < dim) {
            int[] neigh = tiles.clone();
            neigh[blank] = tiles[blank+1];
            neigh[blank+1] = tiles[blank];
            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    n1[i][j] = neigh[i*dim + j];
                }
            }
            neighb.add(new Board(n1));
            System.out.println(Arrays.deepToString(n1));
        }
        // Tile to the left moves to blank
        if ((blank%dim)-1 >= 0) {
            int[] neigh = tiles.clone();
            neigh[blank] = tiles[blank-1];
            neigh[blank-1] = tiles[blank];
            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    n1[i][j] = neigh[i*dim + j];
                }
            }
            neighb.add(new Board(n1));
            System.out.println(Arrays.deepToString(n1));
        }
        // Tile to the bottom moves to blank
        if (blank+dim < length) {
            int[] neigh = tiles.clone();
            neigh[blank] = tiles[blank+dim];
            neigh[blank+dim] = tiles[blank];
            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    n1[i][j] = neigh[i*dim + j];
                }
            }
            neighb.add(new Board(n1));
            System.out.println(Arrays.deepToString(n1));
        }
        // Tile to the top moves to blank
        if (blank-dim >= 0) {
            int[] neigh = tiles.clone();
            neigh[blank] = tiles[blank-dim];
            neigh[blank-dim] = tiles[blank];
            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    n1[i][j] = neigh[i*dim + j];
                }
            }
            neighb.add(new Board(n1));
            System.out.println(Arrays.deepToString(n1));
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

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        StdOut.println(initial.hamming());
        StdOut.println(initial.manhattan());
        StdOut.println(initial.toString());
        ArrayList<Board> a = (ArrayList<Board>)initial.neighbors();
        // print solution to standard output
        /*if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);*/
    }
}