package AdventOfCode.PuzzleFive;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class PuzzleFive {
    public static List<String> readFile() {
        In reader = new In("C:\\Users\\micha\\Downloads\\RandomCode\\AdventOfCode\\PuzzleFive\\PuzzleFiveInput");
        List<String> ls = new ArrayList<>();
        while (!reader.isEmpty()) {
            ls.add(reader.readString());
        }
        return ls;
    }

    public static HashMap<String, ArrayList<String>> getRows(List<String> codes) {
        HashMap<String, ArrayList<String>> rows = new HashMap<>();
        for (String s : codes) {
            if (rows.containsKey(s.substring(0, 7))) {
                rows.get(s.substring(0, 7)).add(s);
            } else {
                ArrayList<String> x = new ArrayList<>();
                x.add(s);
                rows.put(s.substring(0, 7), x);
            }
        }
        return rows;
    }

    public static int getRowString(String s) {
        int low = 0, high = 127, factor = 64, index = 0;
        while(low != high) {
            if(s.charAt(index) == 'F') {
                high = high - factor;
            }
            else if(s.charAt(index) == 'B') {
                low = low + factor;
            }
            index++;
            factor /= 2;
        }
        return high;
    }

    public static int getColString(String s) {
        int low = 0, high = 7, factor = 4, index = 0;
        while(low != high) {
            if(s.charAt(index) == 'L') {
                high = high - factor;
            }
            else if(s.charAt(index) == 'R') {
                low = low + factor;
            }
            index++;
            factor /= 2;
        }
        return high;
    }

    public static int getID(int row, int col) {
        return row * 8 + col;
    }

    public static int maxID(HashMap<String, ArrayList<String>> boardingPasses) {
        String max = "";
        int maxVal = 0;
        for(String s : boardingPasses.keySet()) {
            if(getRowString(s) > maxVal) {
                maxVal = getRowString(s);
                max = s;
            }
        }
        int maxCol = 0;
        for(String s : boardingPasses.get(max)) {
            if(getColString(s.substring(7, s.length())) > maxCol) {
                maxCol = getColString(s.substring(7, s.length()));
            }
        }
        return getID(maxVal, maxCol);
    }

    public static void missingRows(HashMap<String, ArrayList<String>> boardings) {
        for(String s : boardings.keySet()) {
            if(boardings.get(s).size() != 8) {
                System.out.println(boardings.get(s));
            }
        }
    }

    public static int getMissingColNumber(ArrayList<String> ls) {
        int sum = 0;
        for(String s : ls) {
            sum += getColString(s.substring(7, s.length()));
        }
        return 1 + 2 + 3 + 4 + 5 + 6 + 7 - sum;
    }

    public static void main(String[] args) {
        System.out.println(getMissingColNumber(getRows(readFile()).get("BFFBBFF")));
        System.out.println(getID(getRowString("BFFBBFF"), 7));
    }
}
