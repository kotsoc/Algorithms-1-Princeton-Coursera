
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private final int trials;
    private final double[] percThreshold;
    
    
    
    public PercolationStats(int n, int trials) {    // perform trials independent experiments on an n-by-n grid
        int row, col, rng;
        int[] rand;
        this.trials = trials;
        rand = new int[n*n];
        percThreshold = new double[trials];
        for (int i = 0; i < n*n; i++) { // initialization of block opening array
            rand[i] = i; 
        }
        StdRandom.shuffle(rand); // Initial shuffle of the block opening array       
        for (int j = 0; j < trials; j++) { // Loop for running the trials
            int count = 0;
            Percolation perc = new Percolation(n);
            while (!perc.percolates() && count < n*n) { // Loop that performs the experiment
                rng = rand[count];
                row = (rng / n);
                col = (rng % n);
                perc.open(row+1, col+1);
                count++;
            }
            percThreshold[j] = perc.numberOfOpenSites()/((double) (n*n));
            StdRandom.shuffle(rand); // new shuffle so each experiment got a different ordering 
        }
    }
    
    public double mean() {                        // sample mean of percolation threshold
        return StdStats.mean(percThreshold);
    }
    
    public double stddev() {                        // sample standard deviation of percolation threshold
        return StdStats.stddev(percThreshold);
    }
    
    public double confidenceLo() {                  // low  endpoint of 95% confidence interval
        return mean() - ((1.96*StdStats.varp(percThreshold))/(Math.sqrt(trials)));
    }
    
    public double confidenceHi() {                  // high endpoint of 95% confidence interval
        return mean() + ((1.96*StdStats.varp(percThreshold))/(Math.sqrt(trials)));
    }
    
    
    public static void main(String[] args) { // test client (described below)
        int gridSize = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats percolate =  new PercolationStats(gridSize, trials);
        System.out.println("mean                    = "+ percolate.mean());
        System.out.println("stddev                  = "+(percolate.stddev()));
        System.out.println("95% confidence interval = ["+percolate.confidenceLo()+
                           ", "+ percolate.confidenceHi() +"]");

        
    }
}