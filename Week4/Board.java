import edu.princeton.cs.algs4.Queue;

public class Board {
    private int N;
    private int newBlocks[][];
    
    public Board(int[][] blocks) {
        N = blocks.length;
        newBlocks = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newBlocks[i][j] = blocks[i][j];
            }
        }
    }
    
    public int dimension() {
        return N;
    }
    
    private int getCellValue(int x, int y) {
        return x * N + y + 1;
    }
    
    public int hamming() {
        int numBlocks = 0;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (newBlocks[i][j] == 0)
                    continue;
                if (newBlocks[i][j] != getCellValue(i, j))
                    numBlocks++;
            }
        }
        
        return numBlocks;
    }
    
    public int manhattan() {
        int numBlocks = 0;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (newBlocks[i][j] == 0)
                    continue;
                int row = (newBlocks[i][j] - 1) / N;
                int col = (newBlocks[i][j] - 1) % N;
                numBlocks += (Math.abs(i - row) + Math.abs(j - col));
            }
        }
        return numBlocks;
    }
    
    public boolean isGoal() {
        return hamming() == 0;
    }
    
    public Board twin() {
        int[][] twin = copyBoard();
        for (int i = 0; i < N; i++) {
            boolean flag = true;
            for (int j = 0; j < N; j++) {
                if (twin[i][j] == 0) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) {
                int temp = twin[i][0];
                twin[i][0] = twin[i][1];
                twin[i][1] = temp;
                break;
            }
        }
        return new Board(twin);
    }
    
    private int[][] copyBoard() {
        int[][] b = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
            b[i][j] = newBlocks[i][j];
        return b;
    }
    
    public boolean equals(Object y) {
        if (y == this)
            return true;
        if (y == null)
            return false;
        if (!(y instanceof Board))
            return false;
        Board that = (Board) y;
        if (that.dimension() != dimension())
            return false;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (that.newBlocks[i][j] != newBlocks[i][j])
                        return false;
            }
        }
        return true;
    }
    
    public Iterable<Board> neighbors() {
        Queue<Board> neighbors = new Queue<Board>();
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (newBlocks[i][j] == 0) {
                    if (i != 0) {
                        int[][] nb = copyBoard();
                        nb[i][j] = nb[i - 1][j];
                        nb[i - 1][j] = 0;
                        neighbors.enqueue(new Board(nb));
                    }
                    if (i != N - 1) {
                        int[][] nb = copyBoard();
                        nb[i][j] = nb[i + 1][j];
                        nb[i + 1][j] = 0;
                        neighbors.enqueue(new Board(nb));
                    }
                    if (j != 0) {
                        int[][] nb = copyBoard();
                        nb[i][j] = nb[i][j - 1];
                        nb[i][j - 1] = 0;
                        neighbors.enqueue(new Board(nb));
                    }
                    if (j != N - 1) {
                        int[][] nb = copyBoard();
                        nb[i][j] = nb[i][j + 1];
                        nb[i][j + 1] = 0;
                        neighbors.enqueue(new Board(nb));
                    }
                    
                    return neighbors;
                }
            }
        }
        
        return null;
    }
    
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", newBlocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
}