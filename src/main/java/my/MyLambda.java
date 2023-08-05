package my;

import java.util.HashMap;

public class MyLambda {

    public static void main(String[] args) {
        computeIfAbsent();
    }

    public static void computeIfAbsent() {
        var map = new HashMap<String, Integer>();

        map.put("key1", 100);
        map.put("key2", 200);
        map.put("key3", 300);

        map.computeIfAbsent("key4", key -> {
            System.out.printf("key %s \n", key);
            return 400;
        });
    }
}
