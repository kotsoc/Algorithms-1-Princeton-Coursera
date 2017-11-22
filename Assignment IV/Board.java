public class Board {
    private int[][] brd;
    private int manh = 0;
    private int dim;
    
    public Board(int[][] blocks) {       // construct a board from an n-by-n array of blocks
        dim =  blocks.length;
        brd = blocks;
            for ( int i = 1; i <= (blocks.length*blocks.length); i++) {
                Boolean done = false;
                for (int j = 0; j <(blocks.length*blocks.length); j++) {
                    int x = j/dim;
                    int y = j%dim;
                    if ( blocks[x][y] == i) {
                        manh +=  Math.abs(x-((i-1)/dim)) + Math.abs(y-((i-1)%dim));
                    }
            }
        }
    }
    
    public int dimension() {                 // board dimension n
        return brd.length;
    }
    
    public int hamming() {                   // number of blocks out of place
        int hamm = 0;
        for ( int i = 0; i < (brd.length*brd.length); i++) {
            if ( brd[i/dim][i%dim] != i+1 &&  brd[i/dim][i%dim] > 0) {
                hamm+=1;
            }
        }
        return hamm;
    }
    
    public int manhattan() {                 // sum of Manhattan distances between blocks and goal
        return manh;
    }
    
    public boolean isGoal() {                // is this board the goal board?
        if (hamming() > 0) { return false; }
        else { return true; }
    }
    
    public Board twin() {                    // a board that is obtained by exchanging any pair of blocks
        return new Board(new int[2][]);
    }
    
    public boolean equals(Object y) {        // does this board equal y?
        return true;
    }
    
    public Iterable<Board> neighbors() {     // all neighboring boards
        return null;
    }
    
    public String toString() {               // string representation of this board (in the output format specified below)
        return "gkatzos";
    }
    public static void main(String[] args) { // unit tests (not graded)
        int[][] multi = new int[][]{
            { 8, 1, 3},
            { 4, 0, 2},
            { 7, 6, 5}};
        Board b = new Board(multi);
        
        System.out.println(b.manhattan());
        System.out.println(b.hamming());
        
        
    }
}