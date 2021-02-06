package AdventOfCode.PuzzleFour;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PuzzleFour {
    public static List<HashMap<String, Object>> readFile() {
        In reader = new In("C:\\Users\\micha\\Downloads\\RandomCode\\AdventOfCode\\PuzzleFour\\PuzzleFourInput");
        List<HashMap<String, Object>> ls = new ArrayList<>();
        while (!reader.isEmpty()) {
            boolean newLine = false;
            HashMap<String, Object> hm = new HashMap<>();
            while (!newLine) {
                String category = reader.readString();
                if (category.equals("byr")
                        || category.equals("iyr")
                        || category.equals("eyr")
                        || category.equals("byr")) {
                    hm.put(category, reader.readInt());
                } else if (category.equals("hcl")
                        || category.equals("ecl")
                        || category.equals("pid")
                        || category.equals("cid")
                        || category.equals("hgt")) {
                    hm.put(category, reader.readString());
                } else if (category.equals("-")) {
                    newLine = true;
                }
            }
            ls.add(hm);
        }
        return ls;
    }

    public static int countValidPassports(List<HashMap<String, Object>> passports) {
        int count = 0;
        for (HashMap<String, Object> passport : passports) {
            if (passport.size() == 8 || (passport.size() == 7 && passport.get("cid") == null)) {
                count++;
            }
        }
        return count;
    }

    public static int countValidPassportsSpecific(List<HashMap<String, Object>> passports) {
        int count = 0;
        for (HashMap<String, Object> passport : passports) {
            if (passport.size() == 8 || (passport.size() == 7 && passport.get("cid") == null)) {
                String numberHeight = "", units = "", height = (String) passport.get("hgt");
                for (int i = 0; i < height.length(); i++) {
                    if (height.charAt(i) >= 48 && height.charAt(i) <= 57) {
                        numberHeight += height.charAt(i);
                    } else {
                        units += height.charAt(i);
                    }
                }
                int numHeight = Integer.parseInt(numberHeight);
                if ((Integer) passport.get("byr") >= 1920 && (Integer) passport.get("byr") <= 2002
                        && (Integer) passport.get("iyr") >= 2010 && (Integer) passport.get("iyr") <= 2020
                        && (Integer) passport.get("eyr") >= 2020 && (Integer) passport.get("eyr") <= 2030
                        && ((units.equals("cm") && numHeight >= 150 && numHeight <= 193) || (units.equals("in") && numHeight >= 59 && numHeight <= 76))
                        && validHairColor((String) passport.get("hcl"))
                        && validEyeColor((String) passport.get("ecl"))
                        && validPid((String) passport.get("pid"))) {
                    count++;
                }
            }
        }
        return count;
    }

    public static boolean validHairColor(String s) {
        if (s.charAt(0) != '#' || s.length() - 1 != 6) {
            return false;
        }
        for (int i = 1; i < s.length(); i++) {
            char x = s.charAt(i);
            if (!((x >= 48 && x <= 57) || (x >= 97 && x <= 102))) {
                return false;
            }
        }
        return true;
    }

    public static boolean validEyeColor(String s) {
        return s.equals("amb") || s.equals("blu") || s.equals("brn") || s.equals("gry") || s.equals("grn") || s.equals("hzl") || s.equals("oth");
    }

    public static boolean validPid(String s) {
        if (s.length() != 9) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!(s.charAt(i) >= 48 && s.charAt(i) <= 57)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(countValidPassportsSpecific(readFile()));
    }
}
