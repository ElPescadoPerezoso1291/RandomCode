package AdventOfCode.PuzzleTwo;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;

public class PuzzleTwo {
    public static int passwordCorrect(List<Node> passwords) {
        int count = 0;
        for(Node n : passwords) {
            String pass = n.password;
            int charCounter = 0;
            for(int i = 0; i < pass.length(); i++) {
                if(pass.charAt(i) == n.ch) {
                    charCounter++;
                }
            }
            if(charCounter >= n.low && charCounter <= n.high) {
                System.out.println(n.low + "-" + n.high + " " + n.ch + ": " + n.password);
                count++;
            }
        }
        return count;
    }

    public static int passwordCorrectTwo(List<Node> passwords) {
        int count = 0;
        for(Node n : passwords) {
            String pass = n.password;
            if(pass.charAt(n.low - 1) == n.ch && pass.charAt(n.high - 1) != n.ch) {
                System.out.println(n.low + "-" + n.high + " " + n.ch + ": " + n.password);
                count++;
            }
            if(pass.charAt(n.low - 1) != n.ch && pass.charAt(n.high - 1) == n.ch) {
                System.out.println(n.low + "-" + n.high + " " + n.ch + ": " + n.password);
                count++;
            }
        }
        return count;
    }

    public static List<Node> readFile() {
        In reader = new In("C:\\Users\\micha\\Downloads\\RandomCode\\AdventOfCode\\PuzzleTwo\\PuzzleTwoInput");
        List<Node> ls = new ArrayList<>();
        while(!reader.isEmpty()) {
            ls.add(new Node(reader.readInt(), reader.readInt(), reader.readString().charAt(0), reader.readString()));
        }
        return ls;
    }

    public static void main(String[] args) {
        passwordCorrect(readFile());
        passwordCorrectTwo(readFile());
    }

    public static class Node {
        int low;
        int high;
        char ch;
        String password;

        Node(int low, int high, char ch, String password) {
            this.low = low;
            this.high = high;
            this.ch = ch;
            this.password = password;
        }
    }
}
