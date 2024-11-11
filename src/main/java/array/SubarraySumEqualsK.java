package array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * Example 1:
 *      Input: nums = [1,1,1], k = 2
 *      Output: 2
 *
 * Example 2:
 *      Input: nums = [1,2,3], k = 3
 *      Output: 2
 *
 * Constraints:
 *      - 1 <= nums.length <= 2 * 104
 *      - -1000 <= nums[i] <= 1000
 *      - -107 <= k <= 107
 */
public class SubarraySumEqualsK {

    public static void main(String[] args) throws Exception {
        int[] A = {1, 2, 1, -2, 3, -1, -1};
        System.out.println(new SubarraySumEqualsK().subarraySum(A, 2));
    }

    // integer array: int[] nums
    //        target: k
    //      subarray: continuous non-empty sequences of elements within an array.
    //  Sum[i] = nums[0] ... nums[i-1], Sum[0] = 0
    //  Put Sum[i] + k to hashmap, For Sum[j] find the Sum[j] - Sum[i] = k

    public int subarraySum(int[] nums, int k) {
        int ret = 0;
        int sum = 0;
        Map<Integer, Integer> lkp = new HashMap<>();
        lkp.put(sum, 1);
        for(int i=0; i < nums.length; i++) {
            sum += nums[i];
            if (lkp.containsKey(sum - k)) {
                ret += lkp.get(sum - k);
            }
            if (lkp.containsKey(sum)) {
                lkp.put(sum, lkp.get(sum) + 1);
            } else {
                lkp.putIfAbsent(sum, 1);
            }
        }
        return ret;
    }
}
