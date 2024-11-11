package array;

import java.util.Arrays;

/**
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 *
 * <p>Formally the function should: Return true if there exists i, j, k such that arr[i] < arr[j] <
 * arr[k] given 0 ≤ i < j < k ≤ n-1 else return false. Your algorithm should run in O(n) time
 * complexity and O(1) space complexity.
 *
 * <p>Examples: Given [1, 2, 3, 4, 5], return true.
 *
 * <p>Given [5, 4, 3, 2, 1], return false.
 */
public class IncreasingTripletSubsequence {

    /**
     * Main method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // int[] A = {1, 2, 3, 4, 5};
        int[] A = {5, 1, 6};
        System.out.println(new IncreasingTripletSubsequence().increasingTriplet(A));
    }

    public boolean increasingTriplet(int[] nums) {
        int low = nums[0];
        int high = Integer.MAX_VALUE;
        for(var idx=1; idx<nums.length; idx++) {
            if (low == nums[idx] || high == nums[idx]) {
                continue;
            }
            if (low > nums[idx]) {
                low = nums[idx];    // new low
            } else if (high > nums[idx]) {
                high = nums[idx];   // new high
            } else {
                return true;        // found
            }
        }
        return false;
    }
}
