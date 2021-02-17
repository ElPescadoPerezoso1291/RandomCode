import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WordSearch {
    public static void main(String[] args) {
        char[] firstRow = new char[]{'A', 'B', 'C', 'E'};
        char[] secondRow = new char[]{'S', 'F', 'C', 'S'};
        char[] thirdRow = new char[]{'A', 'D', 'E', 'E'};
        char[][] edgeBoard = new char[][]{new char[]{'a'}};
        char[][] board = new char[][]{firstRow, secondRow, thirdRow};
        HashSet<int[]> x = new HashSet<>();
        System.out.println(exist(edgeBoard, "a"));
    }

    public static boolean exist(char[][] board, String word) {
        if(board.length == 1 && board[0].length == 1) {
            return true;
        }
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (word.charAt(0) == board[row][col] && dfs(board, word, new Pair(row, col), new HashSet<>())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean dfs(char[][] board, String word, Pair coord, HashSet<Pair> marked) {
        if (word.length() == 1 && board[coord.row][coord.col] == word.charAt(0)) {
            return true;
        } else if (board[coord.row][coord.col] != word.charAt(0)) {
            return false;
        }
        for (Pair neighbor : neighbors(coord.row, coord.col, board)) {
            marked.add(coord);
            boolean found = false;
            if (!marked.contains(neighbor)) {
                found = dfs(board, word.substring(1), neighbor, marked);
            }
            if (found) {
                return found;
            } else {
                marked.remove(coord);
            }
        }
        return false;
    }

    private static List<Pair> neighbors(int row, int col, char[][] board) {
        ArrayList<Pair> neighborsOf = new ArrayList<>();
        if (row > 0) {
            neighborsOf.add(new Pair(row - 1, col));
        }
        if (row < board.length - 1) {
            neighborsOf.add(new Pair(row + 1, col));
        }
        if (col > 0) {
            neighborsOf.add(new Pair(row, col - 1));
        }
        if (col < board[0].length - 1) {
            neighborsOf.add(new Pair(row, col + 1));
        }
        return neighborsOf;
    }

    private static class Pair {
        int row;
        int col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            Pair p = (Pair) o;
            return p.row == row && p.col == col;
        }

        @Override
        public int hashCode() {
            return row * 31 + col;
        }
    }
}
