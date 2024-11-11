package my;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MyCollection {
    // Collection: represents a generalized grouping of objects of type E.

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        nums.stream().forEach(i -> System.out.printf("%d \n", i));
        nums = Arrays.asList(1, 2, 3, 4);
        for (var num : nums.stream().toList()) {
            System.out.printf("tolist %d \n", num);
        }
        nums.stream().filter(num -> {
            return num % 2 == 0;
        }).forEach(num -> System.out.printf("%d \n", num));
        List<Integer> tnums = nums.stream().collect(Collectors.toCollection(ArrayList::new));
        for (var num : tnums) {
            System.out.printf("tolist %d \n", num);
        }
        List<int[]> listIntArray = new ArrayList<>();
        listIntArray.add(new int[]{1, 2});
        listIntArray.add(new int[]{2, 3});
        // List<Integer> ret = listIntArray.stream().map(e -> e[1]).collect(Collectors.toList());

        List<Integer> ret = listIntArray.stream().map(e -> e[1]).toList();
        System.out.println(ret);
        var my = new MyCollection();
        my.listIntToMap();
        my.removeDuplicated();
    }

    public void listIntToMap() {

        AtomicInteger index = new AtomicInteger(1);
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);

        // var ret = nums.stream().collect(Collectors.toMap(v -> index.getAndIncrement(), v -> v));
        var ret = nums.stream().collect(Collectors.toMap(v -> index.getAndIncrement(), v -> v));
        System.out.println(ret);
    }

    public static List<Integer> getFinalOrder(int k, List<Integer> amount) {
        // create List<int[]>   widthdraw count, person-Index
        // sort this list, based on the widthdraw count, reverse peron-index

        AtomicInteger idx = new AtomicInteger(1);
        return amount.stream().map(s -> new int[]{idx.getAndIncrement(), (s / k) + (s % k == 0 ? 0 : 1)})
                .sorted(Comparator.comparingInt((int[] e) -> e[1]).thenComparingInt(e -> e[0]))
                .map(e -> e[0])
                .collect(Collectors.toList());
    }

    public void removeDuplicated() {
        List<Integer> list = Arrays.asList(5, 0, 3, 1, 2, 3, 0, 0);
        var newList = list.stream().distinct().toList();
        System.out.println(newList);
    }
}
