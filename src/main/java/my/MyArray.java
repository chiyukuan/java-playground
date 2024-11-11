package my;

import java.util.Arrays;
import java.util.Comparator;

public class MyArray {

    public static void main(String[] args) {
        new MyArray().subArray();
        new MyArray().sortArray();
    }

    public void subArray() {
        int[][] array = {{1}, {2}, {3},};

        var sub = Arrays.copyOfRange(array, 0, 1 + 1);
        for (var elm : sub) {
            System.out.printf("%d \n", elm[0]);
        }
    }

    public void sortArray() {
        int[][] array = {{1, 2}, {8, 51}, {6, 7}};

        Arrays.sort(array, Comparator.comparingInt(e -> e[0]));
        for (var elm: array) {
            System.out.printf("%d %d\n", elm[0], elm[1]);
        }
    }
}
