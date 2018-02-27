

import edu.princeton.cs.algs4.MinPQ;

public class Solver {
    MinPQ<String> pq = new MinPQ<String>();
    Board origin,twin,pred;
    int moves;
    //MinPQ = prioTw = new minPQ();
    public Solver(Board initial) {          // find a solution to the initial board (using the A* algorithm)
        Board origin = initial;
        Board twin = initial.twin();
        Board pred = null;
        int moves = 0;


    }
    public boolean isSolvable() {            // is the initial board solvable?

    return true;
    }
    public int moves() {                     // min number of moves to solve initial board; -1 if unsolvable
    return 0;
    }
    /*public Iterable<Board> solution() {     // sequence of boards in a shortest solution; null if unsolvable
    return origin;}*/

    public static void main(String[] args) { // solve a slider puzzle (given below)

    }
}
