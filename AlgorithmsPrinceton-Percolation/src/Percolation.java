import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
    private int[] grid;
    private final int upperlimit;
    private final int lowerlimit;
    private final int index;
    private final int topsite;
    private final int bottomsite;
    private int count;
    private WeightedQuickUnionUF uf;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n)
    {
        if(n<=0)
            throw new IllegalArgumentException("Grid Size should be positive");

        grid = new int[n*n+2];
        for(int i=0;i<grid.length-2;i++)
            grid[i] = 0;
        topsite = grid.length-1; grid[topsite] = 1;
        bottomsite = grid.length-2; grid[bottomsite] = 1;
        index = n;
        upperlimit = n-1;
        lowerlimit = 0;
        count = 0;
        uf = new WeightedQuickUnionUF(n*n+2);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
        if(row<lowerlimit || row>upperlimit)
            throw new IllegalArgumentException("row is out of range");
        if(col<lowerlimit || col>upperlimit)
            throw new IllegalArgumentException("col is out of range");


        int site = index*row+col;
        if(grid[site]==1)
            return;

        grid[site]=1;
        count++;

        if(col>lowerlimit && col<upperlimit)
        {
            if(isOpen(row,col+1))
                uf.union(site,site+1);
            if(isOpen(row,col-1))
                uf.union(site,site-1);

            if(row==lowerlimit)
            {
                uf.union(site,topsite);

                if(isOpen(row+1,col))
                    uf.union(site,site+index);
            }
            else if(row==upperlimit)
            {
                uf.union(site,bottomsite);

                if (isOpen(row-1, col))
                    uf.union(site, site-index);
            }
            else
            {
                if (isOpen(row-1, col))
                    uf.union(site, site-index);
                if (isOpen(row+1, col))
                    uf.union(site, site+index);
            }
        }
        else
        {
            if(col==lowerlimit)
            {
                if(isOpen(row,col+1))
                    uf.union(site,site+1);

                if(row==lowerlimit)
                {
                    uf.union(site,topsite);

                    if (isOpen(row+1, col))
                        uf.union(site, site+index);
                }
                else if(row==upperlimit)
                {
                    uf.union(site,bottomsite);

                    if (isOpen(row-1, col))
                        uf.union(site, site-index);
                }
                else
                {
                    if(isOpen(row+1,col))
                        uf.union(site,site+index);
                    if(isOpen(row-1,col))
                        uf.union(site,site-index);
                }
            }
            else if(col==upperlimit)
            {
                if (isOpen(row, col-1))
                    uf.union(site, site-1);

                if(row==lowerlimit)
                {
                    uf.union(site,topsite);

                    if (isOpen(row+1, col))
                        uf.union(site, site+index);
                }
                else if(row==upperlimit)
                {
                    uf.union(site,bottomsite);

                    if (isOpen(row-1, col))
                        uf.union(site, site-index);
                }
                else
                {
                    if(isOpen(row+1,col))
                        uf.union(site,site+index);
                    if(isOpen(row-1,col))
                        uf.union(site,site-index);
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {

        if(row<lowerlimit || row>upperlimit)
            throw new IllegalArgumentException("row is out of range");
        if(col<lowerlimit || col>upperlimit)
            throw new IllegalArgumentException("col is out of range");

        int site = index*row+col;
        if(grid[site]==0)
            return false;

        return true;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col)
    {
        if(row<lowerlimit || row>upperlimit)
            throw new IllegalArgumentException("row is out of range");
        if(col<lowerlimit || col>upperlimit)
            throw new IllegalArgumentException("col is out of range");

        int site = index*row+col;
        return uf.connected(site,topsite);
    }

    // returns the number of open sites
    public int numberOfOpenSites() { return count; }

    // does the system percolate?
    public boolean percolates() { return uf.connected(topsite,bottomsite); }

    // unit testing (required)
    public static void main(String[] args)
    {
        In in = new In(args[0]);
        int N = in.readInt();
        Percolation perc = new Percolation(N);
        int count = 0;
        while(!in.isEmpty())
        {
            int row = in.readInt();
            int col = in.readInt();
            perc.open(row,col);
            count++;
            System.out.println(count+". "+perc.percolates());
        }
        /*
        Percolation perc = new Percolation(5);
        perc.open(0,0);
        System.out.println(perc.isOpen(0,0));
        System.out.println(perc.isOpen(2,2));
        System.out.println(perc.isFull(0,0));
        perc.open(1,0);
        perc.open(2,0);
        perc.open(3,0);
        System.out.println(perc.numberOfOpenSites());
        System.out.println(perc.percolates());
        perc.open(4,0);
        System.out.println(perc.percolates());
        */

    }

}
