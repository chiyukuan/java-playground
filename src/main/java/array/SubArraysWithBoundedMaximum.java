package array;

/**
 * Given an integer array nums and two integers left and right, return the number of
 * contiguous non-empty subarrays such that the value of the maximum array element
 * in that subarray is in the range [left, right].
 *
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 *
 * Example 1:
 *      Input: nums = [2,1,4,3], left = 2, right = 3
 *      Output: 3
 *      Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].
 *
 * Example 2:
 *      Input: nums = [2,9,2,5,6], left = 2, right = 8
 *      Output: 7
 *
 *
 * Constraints:
 *      - 1 <= nums.length <= 105
 *      - 0 <= nums[i] <= 109
 *      - 0 <= left <= right <= 109
 */
public class SubArraysWithBoundedMaximum {
    /**
     * Main method
     *
     * @param args
     */
    public static void main(String[] args) {
        // int[] A = {2, 1, 4, 3};
        int[] A = {45,2,49,6,55,73,27,17,4,71};
        System.out.println(new SubArraysWithBoundedMaximum().numSubarrayBoundedMax(A, 17, 33));
    }

    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        //  [... X ....] Y,  left <= X <= right, left <= Y (or end of array)
        int low = 0;
        int found = -1;
        int ret = 0;
        for (int idx=0; idx < nums.length; idx++) {
            if (found < low) {  // not found, adjust low or found index
                if (nums[idx] > right) {
                    low = idx + 1;
                    continue;
                }
                if (left <= nums[idx] && nums[idx] <= right) {
                    found = idx;
                }
            } else {
                if (left <= nums[idx]) { // found and find the target group
                    ret += (found - low + 1) * ((idx - 1) - found + 1);
                    if (nums[idx] <= right) {
                        found = idx;
                    } else {
                        low = idx+1;
                    }
                }
            }
        }
        if (found >= low) {
            ret += (found - low + 1) * ((nums.length - 1) - found + 1);
        }
        return ret;
    }
}
