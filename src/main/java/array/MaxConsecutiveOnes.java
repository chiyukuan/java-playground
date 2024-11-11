package array;

/**
 * Given a binary array nums, return the maximum number of consecutive 1's in the array.
 *
 * Example 1:
 *      Input: nums = [1,1,0,1,1,1]
 *      Output: 3
 *      Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
 *
 * Example 2:
 *      Input: nums = [1,0,1,1,0,1]
 *      Output: 2
 */
public class MaxConsecutiveOnes {
    public static void main(String[] args) {
        System.out.println(new MaxConsecutiveOnes().findMaxConsecutiveOnes(new int[]{1, 1, 0, 1,1,1}));
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int cur = 0;
        for(var num: nums) {
            if (num == 1) {
                cur++;
            } else if (cur != 0) {
                max = Math.max(max, cur);
                cur = 0;
            }
        }
        return Math.max(max, cur);
    }

}
