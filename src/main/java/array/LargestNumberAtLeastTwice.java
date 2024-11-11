package array;

/**
 * You are given an integer array nums where the largest integer is unique.
 *
 * Determine whether the largest element in the array is at least twice as much as every
 * other number in the array. If it is, return the index of the largest element,
 * or return -1 otherwise.
 *
 * Example 1:
 *      Input: nums = [3,6,1,0]
 *      Output: 1
 *      Explanation: 6 is the largest integer. For every other number in the array x, 6 is
 *                   at least twice as big as x. The index of value 6 is 1, so we return 1.
 *
 * Example 2:
 *      Input: nums = [1,2,3,4]
 *      Output: -1
 *      Explanation: 4 is less than twice the value of 3, so we return -1.
 *
 * Constraints:
 *      2 <= nums.length <= 50
 *      0 <= nums[i] <= 100
 *      The largest element in nums is unique.
 */
public class LargestNumberAtLeastTwice {

    public static void main(String[] args) throws Exception {
        System.out.println(new LargestNumberAtLeastTwice().dominantIndex(new int[]{1, 0}));
    }

    public int dominantIndex(int[] nums) {
        if (nums.length < 2) {
            return -1;
        }
        var sec = Integer.MIN_VALUE;
        var ret = 0;
        for(int idx=1; idx < nums.length; idx++) {
            if (nums[idx] > nums[ret]) {
                sec = nums[ret];
                ret = idx;
            } else if (nums[idx] > sec) {
                sec = nums[idx];
            }
        }
        return nums[ret] >= sec * 2 ? ret : -1;
    }
}
