import java.util.*;

public class n_bishops {

    private static void backtrack(int r, int c, int remaining, int n, char[][] board,boolean[] diag1, boolean[] diag2, List<List<String>> results) {
        if (remaining == 0) {
            List<String> validBoard = new ArrayList<>();
            for (char[] row : board) validBoard.add(new String(row));
            results.add(validBoard);
            return;
        }

        // Total cells on board: n * n
        for (int pos = r * n + c; pos < n * n; pos++) {
            int row = pos / n;
            int col = pos % n;

            int d1 = row - col + n - 1;
            int d2 = row + col;

            if (!diag1[d1] && !diag2[d2]) {
                // Place bishop
                board[row][col] = 'B';
                diag1[d1] = true;
                diag2[d2] = true;

                backtrack(row, col + 1, remaining - 1, n, board, diag1, diag2, results);

                // Backtrack
                board[row][col] = '.';
                diag1[d1] = false;
                diag2[d2] = false;
            }
        }
    }

    public static List<List<String>> solveNBishops(int n, int k) {
        List<List<String>> results = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) Arrays.fill(row, '.');

        boolean[] diag1 = new boolean[2 * n]; // r - c + n - 1
        boolean[] diag2 = new boolean[2 * n]; // r + c

        backtrack(0, 0, k, n, board, diag1, diag2, results);
        return results;
    }

    public static void main(String[] args) {
        
    }
}
