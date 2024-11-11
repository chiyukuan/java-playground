package array;

import java.util.*;

/**
 * Given an array of integers nums, write a method that returns the "pivot" index of this array.
 *
 * <p>We define the pivot index as the index where the sum of the numbers to the left of the index
 * is equal to the sum of the numbers to the right of the index.
 *
 * <p>If no such index exists, we should return -1. If there are multiple pivot indexes, you should
 * return the left-most pivot index.
 *
 * <p>Example 1:
 *
 * <p>Input: nums = [1, 7, 3, 6, 5, 6] Output: 3 Explanation: The sum of the numbers to the left of
 * index 3 (nums[3] = 6) is equal to the sum of numbers to the right of index 3. Also, 3 is the
 * first index where this occurs.
 *
 * <p>Example 2:
 *
 * <p>Input: nums = [1, 2, 3] Output: -1 Explanation: There is no index that satisfies the
 * conditions in the problem statement.
 *
 * <p>Note:
 *
 * <p>The length of nums will be in the range [0, 10000]. Each element nums[i] will be an integer in
 * the range [-1000, 1000].
 *
 * <p>Solution: O(N) maintain a prefix and posfix sum array and then use this to arrive at the
 * answer.
 */
public class FindPivotIndex {
    public static void main(String[] args) {
        // var nums = new int[]{1, 7, 3, 6, 5, 6};
        var nums = new int[]{2, 1, -1};
        // var nums = new int[]{1};
        System.out.println(new FindPivotIndex().pivotIndex(nums));
    }

    public int pivotIndex(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        var lsums = new int[nums.length];
        var hsums = new int[nums.length];
        lsums[0] = nums[0];
        for (int idx = 1; idx < lsums.length; idx++) {
            lsums[idx] = lsums[idx - 1] + nums[idx];
        }
        hsums[nums.length - 1] = nums[nums.length - 1];
        for (int idx = nums.length - 2; idx >= 0; idx--) {
            hsums[idx] = hsums[idx + 1] + nums[idx];
        }
        if (hsums[1] == 0) {
            return 0;
        }
        for (int i = 1; i < nums.length - 1; i++) {
            if (lsums[i - 1] == hsums[i + 1]) {
                return i;
            }
        }
        if (lsums[nums.length - 2] == 0) {
            return nums.length - 1;
        }
        return -1;
    }
}
