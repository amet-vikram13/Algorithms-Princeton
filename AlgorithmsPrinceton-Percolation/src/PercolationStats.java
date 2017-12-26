import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats
{

    private double[] threshold;
    private final int trialno;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials)
    {
        Percolation perc;
        trialno = trials;
        threshold = new double[trials];
        for(int i=0;i<trials;i++)
        {
            perc = new Percolation(n);
            int count = 0;
            while(!perc.percolates())
            {
                int row = StdRandom.uniform(0,n);
                int col = StdRandom.uniform(0,n);
                if(!perc.isOpen(row,col))
                {
                    perc.open(row,col);
                    count++;
                }
            }
            double thresholdno = (double)count/(double)(n*n);
            threshold[i] = thresholdno;
            perc = null;
        }
    }

    // sample mean of percolation threshold
    public double mean() { return StdStats.mean(threshold); }

    // sample standard deviation of percolation threshold
    public double stddev() { return StdStats.stddev(threshold); }

    // low endpoint of 95% confidence interval
    public double confidenceLo() { return (mean() - ((1.96*stddev())/Math.sqrt(trialno))); }

    // high endpoint of 95% confidence interval
    public double confidenceHi() { return (mean() + ((1.96*stddev())/Math.sqrt(trialno))); }

    // test client (see below)
    public static void main(String[] args)
    {
        //int N = Integer.parseInt(args[0]);
        //int T = Integer.parseInt(args[1]);
        int N = 20; int T = 2000;
        if(N<=0 || T<=0)
            throw new IllegalArgumentException("Both N and T should be positive");

        PercolationStats percstats = new PercolationStats(N,T);
        System.out.println("mean                    = " + percstats.mean());
        System.out.println("stddev                  = " + percstats.stddev());
        System.out.println("95% confidence interval = ["+percstats.confidenceLo()+","+percstats.confidenceHi()+"]");
    }
}
