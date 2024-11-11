package array;

import java.util.Arrays;

/**
 * Given an array of integers that is already sorted in ascending order, find two numbers such
 * that they add up to a specific target number.
 *
 * The function twoSum should return indices of the two numbers such that they add up to the
 * target, where index1 must be less than index2. Please note that your returned answers (both
 * index1 and index2) are not zero-based.
 *
 * You may assume that each input would have exactly one solution and you may not use the same
 * element twice.
 *
 * Example:
 *      Input: numbers={2, 7, 11, 15}, target=9
 *      Output: index1=1, index2=2
 */
public class TwoSumII {

    public static void main(String[] args) throws Exception {
        Arrays.stream(new TwoSumII().twoSum(new int[]{2, 7, 11, 15}, 18)).forEach(System.out::println);
    }

    public int[] twoSum(int[] nums, int target) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            var sum = nums[l] + nums[h];
            if (sum == target) {
                return new int[]{l, h};
            }
            if (sum > target) {
                h--;
            } else {
                l++;
            }
        }
        return new int[]{-1, -1};
    }
}
