package AdventOfCode.PuzzleThree;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;
public class PuzzleThree {
    public static int countTrees(List<String> mountain, int dx, int dy) {
        int x = 0, y = 0, count = 0;
        while(y < mountain.size()) {
            String currentLine = mountain.get(y);
            if(x >= currentLine.length()) {
                x = x % (currentLine.length());
            }
            else {
                if(currentLine.charAt(x) == '#') {
                    count++;
                }
                x += dx;
                y += dy;
            }
        }
        return count;
    }

    public static List<String> readFile() {
        In reader = new In("C:\\Users\\micha\\Downloads\\RandomCode\\AdventOfCode\\PuzzleThree\\PuzzleThreeInput");
        List<String> ls = new ArrayList<>();
        while(!reader.isEmpty()) {
            ls.add(reader.readString());
        }
        return ls;
    }

    public static void main(String[] args) {
        System.out.println(countTrees(readFile(), 1, 1));
    }
}
