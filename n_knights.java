import java.util.*;

public class n_knights {

    private static boolean isSafe(char[][] board, int r, int c, int n) {
        if (r - 1 >= 0 && c - 2 >= 0 && board[r - 1][c - 2] == 'K') return false;
        if (r - 1 >= 0 && c + 2 < n  && board[r - 1][c + 2] == 'K') return false;
        if (r - 2 >= 0 && c - 1 >= 0 && board[r - 2][c - 1] == 'K') return false;
        if (r - 2 >= 0 && c + 1 < n  && board[r - 2][c + 1] == 'K') return false;

        return true;
    }

    private static void helper(int cellIdx, int remaining, int n, char[][] board, List<List<String>> results) {
        
        if (remaining == 0) {
            List<String> validBoard = new ArrayList<>();
            for (char[] row : board) {
                validBoard.add(new String(row));
            }
            results.add(validBoard); 
            return;
        }

        int totalCells = n * n;

       
        if (cellIdx == totalCells || (totalCells - cellIdx) < remaining) {
            return;
        }

        int r = cellIdx / n;
        int c = cellIdx % n;

        if (isSafe(board, r, c, n)) {
            board[r][c] = 'K';
            helper(cellIdx + 1, remaining - 1, n, board, results);
            board[r][c] = '.'; 
        }

        helper(cellIdx + 1, remaining, n, board, results);
    }

    public static List<List<String>> solveNKnights(int n, int k) {
        List<List<String>> results = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        helper(0, k, n, board, results);
        return results;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter board size N: ");
        int n = sc.nextInt();
        System.out.print("Enter number of knights K: ");
        int k = sc.nextInt();

        List<List<String>> solutions = solveNKnights(n, k);
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
