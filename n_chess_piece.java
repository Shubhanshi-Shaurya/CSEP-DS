
import java.util.Arrays;

public class n_chess_piece {

    static final String[] PIECES = {"Queen", "Rook", "Bishop", "Knight"};

    public static boolean canAttack(int[] p1, String t1, int[] p2, String t2) {
        return pieceAttacks(p1, t1, p2) || pieceAttacks(p2, t2, p1);
    }

    private static boolean pieceAttacks(int[] from, String type, int[] to) {
        int n = from.length;
        int nonZeroDiffs = 0;
        int commonDelta = -1;
        boolean allSameNonZero = true;

        int count1 = 0, count2 = 0;

        for (int i = 0; i < n; i++) {
            int d = Math.abs(from[i] - to[i]);
            if (d != 0) {
                nonZeroDiffs++;
                if (commonDelta == -1) {
                    commonDelta = d;
                } else if (commonDelta != d) {
                    allSameNonZero = false;
                }

                if (d == 1) count1++;
                else if (d == 2) count2++;
            }
        }

        if (nonZeroDiffs == 0) return true;

        boolean rookAttack = (nonZeroDiffs == 1);
        boolean bishopAttack = (nonZeroDiffs >= 2 && allSameNonZero);

        switch (type) {
            case "Rook":
                return rookAttack;

            case "Bishop":
                return bishopAttack;

            case "Queen":
                return rookAttack || bishopAttack;

            case "Knight":
                return (nonZeroDiffs == 2 && count1 == 1 && count2 == 1);

            default:
                return false;
        }
    }

    private static int[] indexToCoord(long index, int n, int L) {
        int[] coord = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            coord[i] = (int) (index % L);
            index /= L;
        }
        return coord;
    }

    public static boolean findPlacement(int n, int L) {
        long totalCells = 1;
        for (int i = 0; i < n; i++) totalCells *= L;

        int[][] positions = new int[4][n];
        return backtrack(0, 0, positions, n, L, totalCells);
    }

    private static boolean backtrack(int pieceIdx, long startCell, int[][] positions, int n, int L, long totalCells) {
        if (pieceIdx == 4) {
            return true;
        }

        for (long cell = startCell; cell < totalCells; cell++) {
            int[] coord = indexToCoord(cell, n, L);

            boolean safe = true;
            for (int prev = 0; prev < pieceIdx; prev++) {
                if (canAttack(positions[prev], PIECES[prev], coord, PIECES[pieceIdx])) {
                    safe = false;
                    break;
                }
            }

            if (safe) {
                positions[pieceIdx] = coord;
                if (backtrack(pieceIdx + 1, 0, positions, n, L, totalCells)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int dimensions = 3;
        int boardLength = 4;

        int[][] positions = new int[4][dimensions];
        long totalCells = (long) Math.pow(boardLength, dimensions);

        if (backtrack(0, 0, positions, dimensions, boardLength, totalCells)) {
            System.out.println("Valid safe placement found on a " + dimensions + "D board of side " + boardLength + ":");
            for (int i = 0; i < 4; i++) {
                System.out.println(PIECES[i] + " at: " + Arrays.toString(positions[i]));
            }
        } else {
            System.out.println("No safe placement possible with given parameters.");
        }
    }
}