package AdventOfCode.PuzzleOne;
import edu.princeton.cs.algs4.In;
import java.util.*;
public class PuzzleOne {
    public static void twoSum(int x, List<Integer> ls) {
        HashSet<Integer> hs = new HashSet<>();
        for(Integer z : ls) {
            hs.add(z);
        }
        for(Integer c : ls) {
            if(hs.contains(x - c)){
                System.out.println((x  - c) + " + " + c);
            }
        }
    }

    public static void threeSum(int x, List<Integer> ls) {
        HashSet<Integer> hs = new HashSet<>();
        for(Integer z : ls) {
            hs.add(z);
        }
        for(int i = 0; i < ls.size(); i++) {
            for(int j = i + 1; j < ls.size(); j++) {
                if(hs.contains(x - ls.get(i) - ls.get(j))) {
                    System.out.println((x - ls.get(i) - ls.get(j)) + " + " + ls.get(i) + " + " + ls.get(j));
                }
            }
        }
    }

    public static List<Integer> readFile() {
        In reader = new In("C:\\Users\\micha\\Downloads\\RandomCode\\AdventOfCode\\PuzzleOne\\PuzzleOneInput");
        List<Integer> ls = new ArrayList<>();
        while(!reader.isEmpty()) {
            ls.add(reader.readInt());
        }
        return ls;
    }

    public static void main(String[] args) {
        twoSum(2020, readFile());
        threeSum(2020, readFile());
    }
}
