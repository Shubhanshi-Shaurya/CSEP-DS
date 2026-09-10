import java.util.*;


public class n_rooks {

    public static List<List<String>> solveNRooks(int n, int k) {
        List<List<String>> results = new ArrayList<>();
        if (k > n || k < 0) return results;

        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        boolean[] usedCols = new boolean[n];
        helper(0, k, n, board, usedCols, results);
        return results;
    }

    private static void helper(int row, int remaining, int n, char[][] board, 
                               boolean[] usedCols, List<List<String>> results) {
        if (remaining == 0) {
            List<String> validBoard = new ArrayList<>();
            for (char[] r : board) {
                validBoard.add(new String(r));
            }
            results.add(validBoard); 
            return;
        }

        if (row == n || (n - row) < remaining) {
            return;
        }

        for (int col = 0; col < n; col++) {
            if (!usedCols[col]) {
                board[row][col] = 'R';
                usedCols[col] = true;

                helper(row + 1, remaining - 1, n, board, usedCols, results);

                // Backtrack[cite: 1, 30]
                board[row][col] = '.';
                usedCols[col] = false;
            }
        }

        
        helper(row + 1, remaining, n, board, usedCols, results);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter board size N: ");
        int n = sc.nextInt();
        System.out.print("Enter number of rooks K: ");
        int k = sc.nextInt();

        List<List<String>> solutions = solveNRooks(n, k);
        System.out.println("\nTotal configurations: " + solutions.size());

        int count = 1;
        for (List<String> sol : solutions) {
            System.out.println("Solution #" + (count++) + ":");
            for (String row : sol) {
                System.out.println(row);
            }
            System.out.println();
        }
        sc.close();
    }
}
