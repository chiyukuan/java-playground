package array;

import java.util.*;

/**
 * Given an array and a value, find if there is a triplet in array whose sum is equal to
 * the given value. If there is such a triplet present in array, then return true.
 * Else return false.
 *
 * Example 1:
 *      Input: n = 6, x = 13, arr[] = [1 4 45 6 10 8]
 *      Output: true
 *      Explanation: The triplet {1, 4, 8} in the array sums up to 13.
 *
 * Example 2:
 *      Input: n = 5, x = 10, arr[] = [1 2 4 3 6]
 *      Output: true
 *      Explanation: The triplet {1, 3, 6} in the array sums up to 10.
 *
 * Your Task:
 * You don't need to read input or print anything. Your task is to complete the function find3Numbers()
 * which takes the array arr[], the size of the array (n) and the sum (X) as inputs and returns True
 * if there exists a triplet in the array arr[] which sums up to X and False otherwise.
 *
 * Expected Time Complexity: O(n2)
 * Expected Auxiliary Space: O(n)
 */
public class Find3Numbers {

    public static void main(String args[]) {
        System.out.println(Find3Numbers.find3Numbers(Arrays.asList(1, 4, 45,6, 10, 8), 6, 13));
    }


    /*
    Input: arr[] = [1 4 45 6 10 8], x = 13

    for (int i=0; i < arr.length - 2; i++) {  // pick first one
        need = x - arr[i];
        // for(int j=i+1; j < arr.length -1; j++) {  // pick sec  <== optimize with hash
        for(int j=i+1; j < arr.length; kj++) {  // pic 3rd
            hash.contaion(need - nums[i]) ==> found
            else {
                push the hashtable
            }
        }
        // }
    }
     */
    public static boolean find3Numbers(List<Integer> arr, int n, int x) {
        for(int i=0; i<arr.size() - 2; i++) {
            int need = x - arr.get(i);
            Set<Integer> hashSet = new HashSet<>();
            for(int j=i+1; j< arr.size(); j++) {
                if (hashSet.contains(need - arr.get(j))) {
                    return true;
                } else {
                    hashSet.add(arr.get(j));
                }
            }
        }
        return false;
    }
    public static boolean find3Numbers2(List<Integer> arr, int n, int x) {
        for (int i=0; i < n - 2; i++) {     // pick 1rd number
            Set<Integer> set = new HashSet<>();
            int need = x - arr.get(i);
            for (int j=i+1; j < n; j++) {
                if (set.contains(need - arr.get(j))) {  // pick 3rd number
                    return true;
                }
                set.add(arr.get(j));    // record second number
            }

        }
        return false;
    }
}
