package array;

import java.util.*;

/**
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements
 * in arr2 are also in arr1.
 *
 * Sort the elements of arr1 such that the relative ordering of items in arr1 are the
 * same as in arr2. Elements that do not appear in arr2 should be placed at the end of
 * arr1 in ascending order.
 *
 * Example 1:
 *      Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 *      Output: [2,2,2,1,4,3,3,9,6,7,19]
 *
 * Example 2:
 *      Input: arr1 = [28,6,22,8,44,17], arr2 = [22,28,8,6]
 *      Output: [22,28,8,6,17,44]
 *
 * Constraints:
 *      - 1 <= arr1.length, arr2.length <= 1000
 *      - 0 <= arr1[i], arr2[i] <= 1000
 *      - All the elements of arr2 are distinct.
 *      - Each arr2[i] is in arr1.
 */
public class RelativeSortArray {
    public static void main(String[] args) {
        var ret = new RelativeSortArray().relativeSortArray(
                new int[]{2,3,1,3,2,4,6,7,9,2,19},
                new int[]{2,1,4,3,9,6}
        );
        Arrays.stream(ret).forEach(e -> {System.out.printf("%d ", e);});
        System.out.println();
    }

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> arr2Map = new HashMap<>();
        List<Integer> others = new ArrayList<>();
        for(var val: arr2) {
            arr2Map.put(val, 0);
        }
        for(var val: arr1) {
            if (arr2Map.containsKey(val)) {
                arr2Map.put(val, arr2Map.get(val) + 1);
            } else {
                others.add(val);
            }
        }
        others.sort(Comparator.comparingInt(e -> e));
        int[] ret = new int[arr1.length];
        var idx = 0;
        for(var val: arr2) {
            for(int i=0; i < arr2Map.get(val); i++) {
                ret[idx++] = val;
            }
        }
        for (var val : others) {
            ret[idx++] = val;
        }
        return ret;
    }
}
