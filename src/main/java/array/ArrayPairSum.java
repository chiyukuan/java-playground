package array;

import java.util.*;

/**
 * Given an array of 2n integers, your task is to group these integers into n pairs of integer,
 * say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of * min(ai, bi) for all i from 1 to n
 * as large as possible.
 * <p>
 * Example 1:
 * Input: [1,4,3,2]
 * Output: 4 Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).
 * <p>
 * Note: n is a positive integer, which is in the range of [1, 10000]. All the integers in the array
 * will be in the range of [-10000, 10000].
 * <p>
 * Solution: O(n log n) General idea is to pair the smallest with the next smallest value inorder
 * to get the max sum of minimum.
 */
public class ArrayPairSum {

    public static void main(String[] args) {
        var nums = new int[]{1, 2, 3, 4};
        System.out.println(new ArrayPairSum().pairMinToMinSum(nums));
        System.out.println(new ArrayPairSum().pairMinToMaxSum(nums));
        System.out.println(new ArrayPairSum().pairMaxToMinSum(nums));
        System.out.println(new ArrayPairSum().pairMaxToMaxSum(nums));
    }

    // nums[]  => Sum of all pair min( nums[i] + nums[j] ) to largest value.
    // For example, 1, 2, 3, 4, 5, 6, 7, 8
    //      =>      1     3     5     7      ==> 15

    // sort
    // for (int i = 0; i < nums.length; i+=2) results += nums[i]
    public int pairMinToMinSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int ii = 0; ii < nums.length / 2; ii++) {
            sum += nums[ii];
        }
        return sum;
    }

    public int pairMinToMaxSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int ii = 0; ii < nums.length; ii += 2) {
            sum += nums[ii];
        }
        return sum;
    }

    public int pairMaxToMinSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int ii = 1; ii < nums.length; ii += 2) {
            sum += nums[ii];
        }
        return sum;
    }

    public int pairMaxToMaxSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int ii = nums.length / 2; ii < nums.length; ii++) {
            sum += nums[ii];
        }
        return sum;
    }
}
