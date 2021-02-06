package AdventOfCode.PuzzleSix;
import edu.princeton.cs.algs4.In;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PuzzleSix {
    public static List<String> readFile() {
        In reader = new In("C:\\Users\\micha\\Downloads\\RandomCode\\AdventOfCode\\PuzzleSix\\PuzzleSixInput");
        List<String> ls = new ArrayList<>();
        while(!reader.isEmpty()) {
            String soFar = "", current = "";
            while(!current.equals("-")) {
                soFar += current;
                current = reader.readString();
            }
            ls.add(soFar);
        }
        return ls;
    }

    public static List<List<String>> readFileTwo() {
        In reader = new In("C:\\Users\\micha\\Downloads\\RandomCode\\AdventOfCode\\PuzzleSix\\PuzzleSixInput");
        List<List<String>> ls = new ArrayList<>();
        while(!reader.isEmpty()) {
            List<String> list = new ArrayList<>();
            String current = reader.readString();
            while(!current.equals("-")) {
                list.add(current);
                current = reader.readString();
            }
            ls.add(list);
        }
        return ls;
    }

    public static int sumAnswers(List<String> answers) {
        int counter = 0;
        for(String s : answers) {
            HashSet<Character> seen = new HashSet<>();
            for(int i = 0; i < s.length(); i++) {
                if(!seen.contains(s.charAt(i))) {
                    seen.add(s.charAt(i));
                    counter++;
                }
            }
        }
        return counter;
    }

    public static int sumAnswersTwo(List<List<String>> answers) {
        int count = 0;
        for(List<String> ls : answers) {
            HashSet<Character> seenFirst = new HashSet<>();
            for(int i = 0; i < ls.get(0).length(); i++) {
                seenFirst.add(ls.get(0).charAt(i));
            }
            for(int i = 1; i < ls.size(); i++) {
                HashSet<Character> seenInFirst = new HashSet<>();
                String curr = ls.get(i);
                for(int j = 0; j < curr.length(); j++) {
                    if(seenFirst.contains(curr.charAt(j))) {
                        seenInFirst.add(curr.charAt(j));
                    }
                }
                seenFirst = seenInFirst;
            }
            count += seenFirst.size();
        }
        return count;
    }
    public static void main(String[] args) {
        System.out.println(sumAnswersTwo(readFileTwo()));
    }
}
