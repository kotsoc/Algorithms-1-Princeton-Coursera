/*************************************************************************
 *  A class that find Solves the 8-puzzle problem using a min
 *  priority queue
 * <p>
 * This class was created for the 4th assignment of
 * Princeton's algorithm course in coursera
 *
 * @author Konstantinos Peratinos
 * @version 1.0
 *************************************************************************/

import edu.princeton.cs.algs4.MinPQ;
import java.util.Comparator;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    private boolean solvable;

    private Stack<Board> path;

    public Solver(Board initial) {          // find a solution to the initial board (using the A* algorithm)
        MinPQ<SearchNode> solve,solveTw;
        SearchNode current,twin,pred,twinPred;
        if (initial == null) {
            throw new IllegalArgumentException();
        }
        int moves = 0;
        path = new Stack<Board>();
        pred = null;
        twinPred = null;
        // Declaring initial Boards,Comparators and Prio Queues
        current = new SearchNode(initial, moves, pred);
        twin = new SearchNode(initial.twin(), moves, twinPred);
        Comparator<SearchNode> ManhFunction = new ManhOrder();
        //Comparator<SearchNode> HammFunction = new HammOrder();
        solve = new MinPQ<SearchNode>(ManhFunction);
        solveTw = new MinPQ<SearchNode>(ManhFunction);
        // Inserting initial search block + twin
        solve.insert(current);
        solveTw.insert(twin);

        // The Search Algorithm
        while (!(solve.isEmpty() || solveTw.isEmpty())) {
            current = solve.delMin();
            twin = solveTw.delMin();
            pred = current.predecessor;
//            System.out.println("prio is +" + current.manhPrio + "moves are " + moves );
//            System.out.println(current.currentB.toString());
            moves++;
            twinPred = twin.predecessor;
            if (current.currentB.isGoal()){
                solvable = true;
                while(current !=null) {
                    path.push(current.currentB);
                    current = current.predecessor;
                }
                break;
            } else if(twin.currentB.isGoal()) {
                solvable = false;
                break;
            }
            for (Board n : current.currentB.neighbors()) {
                if ((pred == null || !pred.currentB.equals(n))) {
                    solve.insert(new SearchNode(n, moves, current));
                }
            }
            for (Board n : twin.currentB.neighbors() ) {
                if (twinPred == null || !twinPred.currentB.equals(n)) {
                    solveTw.insert(new SearchNode(n, moves, twin));
                }
            }

        }
    }

    public boolean isSolvable() {            // is the initial board solvable?
        return solvable;
    }

    public int moves() {                     // min number of moves to solve initial board; -1 if unsolvable
        if (solvable) return path.size()-1;
        else return -1;
    }

    public Iterable<Board> solution() {     // sequence of boards in a shortest solution; null if unsolvable
    return path;
    }

    private class SearchNode {
        Board currentB;
        SearchNode predecessor;
        int hammPrio,manhPrio,moved;


        private SearchNode(Board init, int moves, SearchNode pred) {
            currentB = init;
            predecessor = pred;
            moved = moves;
            // Calculating priority functions
            //hammPrio = init.hamming() + moves;
            manhPrio = init.manhattan() + moves;

        }


    };

    private final class ManhOrder  implements Comparator<SearchNode> {

            public int compare(SearchNode n1, SearchNode n2) {
                int diff = n1.manhPrio - n2.manhPrio;
                if (diff == 0) {
                   return n2.moved - n1.moved;
                } else {
                    return diff;
                }
            }
        };

    private final class HammOrder  implements Comparator<SearchNode> {

            public int compare(SearchNode n1, SearchNode n2) {
                return n1.hammPrio - n2.hammPrio;
            }
        };

    public static void main(String[] args) {
// solve a slider puzzle (given below)
//// create initial board from file
// In in = new In(args[0]);
// int n = in.readInt();
// int[][] blocks = new int[n][n];
// for (int i = 0; i < n; i++)
// for (int j = 0; j < n; j++)
// blocks[i][j] = in.readInt();
// Board initial = new Board(blocks);
// //        StdOut.println(initial.hamming());
// //        StdOut.println(initial.manhattan());
// //        StdOut.println(initial.toString());
// //ArrayList<Board> a = (ArrayList<Board>)initial.neighbors();
// // solve the puzzle
// Solver solver = new Solver(initial);
//
// // print solution to standard output
// if (!solver.isSolvable())
// StdOut.println("No solution possible");
// else {
// StdOut.println("Minimum number of moves = " + solver.moves());
// for (Board board : solver.solution())
// StdOut.println(board.toString());
// }
    }
}
