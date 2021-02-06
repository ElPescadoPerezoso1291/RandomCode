import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class TestList {

    @Test
    public void testList() {
        ArrayList<Integer> expected = new ArrayList<>();
        ArrayDeque<Integer> actual = new ArrayDeque<>();
        DLList<Integer> actual2 = new DLList<>();
        int N = 100000000;
        for(int i = 0; i < N; i++) {
            assertEquals(expected.size(), actual.size());
            assertEquals(expected.size(), actual2.size());
            int x = (int) (Math.random() * 5);
            if(x == 0) {
                expected.add(0, i);
                actual2.addFirst(i);
                actual.addFirst(i);
            } else if(x == 1) {
                expected.add(i);
                actual2.addLast(i);
                actual.addLast(i);
            } else if(x == 2) {
                if(expected.size() > 0) {
                    int e = expected.remove(expected.size() - 1);
                    int a = actual.removeLast();
                    int a2 = actual2.removeLast();
                    assertEquals(e, a);
                    assertEquals(e, a2);
                }
            } else if(x == 3) {
                if(expected.size() > 0) {
                    int e = expected.remove(0);
                    int a = actual.removeFirst();
                    int a2 = actual2.removeFirst();
                    assertEquals(e, a);
                    assertEquals(e, a2);
                }
            } else if(x == 4) {
                if(expected.size() > 0) {
                    int rando = (int) (Math.random() * expected.size());
                    int e = expected.get(rando);
                    int a = actual.get(rando);
                    int a2 = actual.get(rando);
                }
            }
        }
    }
}
