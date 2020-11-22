import java.util.HashSet;
import java.util.HashMap;
public class WordPattern {
    public boolean wordPattern(String pattern, String s) {
        String[] S = s.split(" ");
        if(S.length != pattern.length())
        {
            return false;
        }
        HashMap<Character, String> P = new HashMap<>();
        HashSet<String> container = new HashSet<>();
        for(int i = 0; i < pattern.length(); i++) {
            if(P.containsKey(pattern.charAt(i)) && !P.get(pattern.charAt(i)).equals(S[i]))
            {
                return false;
            }
            else if(!P.containsKey(pattern.charAt(i)) && container.contains(S[i]))
            {
                return false;
            }
            else {
                P.put(pattern.charAt(i), S[i]);
                container.add(S[i]);
            }
        }
        return true;
    }
}
