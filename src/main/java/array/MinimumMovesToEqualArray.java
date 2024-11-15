package array;

import java.util.Arrays;

/**
 * Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.
 * In one move, you can increment n - 1 elements of the array by 1.
 *
 * Example 1:
 *      Input: nums = [1,2,3]
 *      Output: 3
 *      Explanation: Only three moves are needed (remember each move increments two elements):
 *                  [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 *
 * Example 2:
 *      Input: nums = [1,1,1]
 *      Output: 0
 *
 * Constraints:
 *      - n == nums.length
 *      - 1 <= nums.length <= 105
 *      - -109 <= nums[i] <= 109
 *      - The answer is guaranteed to fit in a 32-bit integer.
 *
 * Solution: O(n log n): Sort the array and find the median of the array. Use the median of array
 * to increment/decrement other value of array. Sum up the difference and return the answer.
 */
public class MinimumMovesToEqualArray {

    public static void main(String[] args) throws Exception {
        int[] A = {1, 2, 3, 4};
        System.out.println(new MinimumMovesToEqualArray().minMoves(A));
    }

    // increasing n-1 element by 1 <= equal to => decreasing 1 element by 1
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        for(var num: nums) {
            min = Math.min(min, num);
        }
        int ret = 0;
        for(var num: nums) {
            ret += num - min; // move to remove the difference.
        }
        return ret;
    }
}
