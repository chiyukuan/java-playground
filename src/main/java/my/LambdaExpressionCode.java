package my;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class LambdaExpressionCode {

    public static void main(String[] args) {
        computeIfAbsent();
        streamCode();
        List<MyObject> objects = new ArrayList<>();
        objects.add(new MyObject(10, "30"));
        objects.add(new MyObject(20, "20"));
        objects.add(new MyObject(30, "10"));
        sortCompare(objects, false);
        System.out.println(objects);
        sortCompare(objects, true);
        System.out.println(objects);

    }

    public static void computeIfAbsent() {
        var map = new HashMap<String, Integer>();

        map.put("key1", 100);
        map.put("key2", 200);
        map.put("key3", 300);
        map.computeIfAbsent("key4", key -> {
            System.out.printf("key %s\n", key);
            return 400;
        });
    }

    public static void streamCode() {
        var nums = Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5),
                Arrays.asList(1, 2, 3, 4, 5)
        );
        var min = 3;
        var result = new ArrayList<Integer>();
        nums.stream().flatMap(Collection::stream).forEach(num -> {
            result.add(num);
            if (num >= min) {
                System.out.printf("%d ", num);
            }
        });
    }
    public static class MyObject {
        int value;
        String str;

        public MyObject(int value, String str) {
            this.value = value;
            this.str = str;
        }
    }

    public static void sortCompare(List<MyObject> objects, boolean useStr) {
        objects.sort((MyObject o1, MyObject o2) -> {
            return useStr ? o1.str.compareTo(o2.str) : Integer.compare(o1.value, o2.value);
        });
    }
}
