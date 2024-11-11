package my;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class SortedCollection {

    public static void main(String[] args) {

        SortedSet<int[]> sorted = new TreeSet<>(Comparator.comparingInt(elm -> elm[0]));

        sorted.add(new int[]{3, 10});
        sorted.add(new int[]{1, 10});
        sorted.forEach(elm -> {
            System.out.printf("%d %d\n", elm[0], elm[1]);
        });
    }
}
