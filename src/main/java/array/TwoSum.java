package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * Example:
 *      Given nums = [2, 7, 11, 15], target = 9,
 *      Because nums[0] + nums[1] = 2 + 7 = 9, return [0, 1].
 *
 * Solution:
 *      - O(n log n). Wrap index and element in a class and sort in increasing order. Do a two
 *          pointer sum and compare.
 *      - An alternative solution is to use hashing which is a O(n) solution
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {3, 2, 4};
        int[] ans = new TwoSum().twoSum(nums, 6);
        for (int i : ans) System.out.println(i);
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int idx=0; idx< nums.length; idx++) {
            if (map.containsKey(nums[idx])) {
                return new int[]{map.get(nums[idx]), idx};
            } else {
                map.put(target - nums[idx], idx);
            }
        }
        return new int[]{-1, -1};
    }
}
