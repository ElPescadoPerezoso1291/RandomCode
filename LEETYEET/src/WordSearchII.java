import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class WordSearchII {
    private static TrieNode sentinel = new TrieNode(null, false, null);

    public static void main(String[] args) {
        char[][] board = new char[][]
                {new char[]{'o', 'a', 'a', 'n'},
                        new char[]{'e', 't', 'a', 'e'},
                        new char[]{'i', 'h', 'k', 'r'},
                        new char[]{'i', 'f', 'l', 'v'}};
        String[] words = new String[]{"oath", "pea", "eat", "rain"};
        System.out.println(findWords(board, words));
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

    public static List<String> findWords(char[][] board, String[] words) {
        List<String> returned = new ArrayList<>();
        for(String word : words) {
            TrieNode.addWord(sentinel, word, word);
        }
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(sentinel.branches.containsKey(board[i][j])) {
                    dfs(board, sentinel, new Pair(i, j), returned, new HashSet<>());
                }
            }
        }
        return returned;
    }

    private static void dfs(char[][] board, TrieNode t, Pair coord, List<String> found, HashSet<Pair> visited) {
        if (t.end) {
            found.add(t.word);
        }
        for (Pair x : neighbors(coord.row, coord.col, board)) {
            if(!visited.contains(x)) {
                for (Character c : t.branches.keySet()) {
                    if (board[x.row][x.col] == c) {
                        visited.add(coord);
                        dfs(board, t.branches.get(c), x, found, visited);
                        visited.remove(coord);
                    }
                }
            }
        }
    }

    private static class TrieNode {
        HashMap<Character, TrieNode> branches = new HashMap<>();
        Character c;
        boolean end;
        String word;

        TrieNode(Character c, boolean end, String word) {
            this.c = c;
            this.end = end;
            this.word = word;
        }

        static void addWord(TrieNode t, String word, String finalWord) {
            if (word.length() == 1) {
                t.branches.put(word.charAt(0), new TrieNode(word.charAt(0), true, finalWord));
                t.word = finalWord;
            } else {
                t.branches.put(word.charAt(0), new TrieNode(word.charAt(0), false, finalWord));
                addWord(t.branches.get(word.charAt(0)), word.substring(1), finalWord);
            }
        }

        boolean isLeaf() {
            return branches.size() == 0;
        }
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
