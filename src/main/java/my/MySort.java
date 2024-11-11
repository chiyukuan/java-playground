package my;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MySort {

    public static void main(String[] args) {
        var mySort = new MySort();
        mySort.add(100);
        mySort.add(300);
        mySort.add(50);
        mySort.show();
        mySort.sort();
        mySort.show();

        int array[] = new int[]{100, 300, 50};

        Arrays.sort(array);
        for (var elm : array) {
            System.out.printf("%d ", elm);
        }
        System.out.println("");
    }

    class Elm implements Comparable<Elm> {
        public int ival;

        public Elm(int ival) {
            this.ival = ival;
        }

        @Override
        public int compareTo(Elm o) {
            return Integer.compare(this.ival, o.ival);
        }
    }

    private final List<Elm> array;

    public MySort() {
        this.array = new ArrayList<>();
    }

    public void add(int val) {
        this.array.add(new Elm(val));
    }

    public void sort() {
        Collections.sort(this.array);
    }

    public void show() {
        for (var elm : this.array) {
            System.out.printf("%d ", elm.ival);
        }
        System.out.println("");
    }

}
