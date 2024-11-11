package array;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

/**
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 *
 * Example 1:
 *      Input: nums = [10,9,2,5,3,7,101,18]
 *      Output: 4
 *      Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 *
 * Example 2:
 *      Input: nums = []
 *      Output: 4
 *
 * Example 3:
 *      Input: nums = [7,7,7,7,7,7,7]
 *      Output: 1
 *
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        System.out.println(new LongestIncreasingSubsequence().lengthOfLIS(new int[]{1, 3, 5, 4, 7}));
        System.out.println(new LongestIncreasingSubsequence().lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}));
    }

    public int lengthOfLIS(int[] nums) {
        var lengths = new int[nums.length]; // including self
        var max = 0;
        for (int i = 0; i < nums.length; i++) {
            var prev = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    prev = Math.max(prev, lengths[j]);
                }
            }
            lengths[i] = prev + 1;
            max = Math.max(max, lengths[i]);
        }
        return max;
    }
}