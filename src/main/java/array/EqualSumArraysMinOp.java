package array;

import java.util.Arrays;

/**
 * You are given two arrays of integers nums1 and nums2, possibly of different lengths.
 * The values in the arrays are between 1 and 6, inclusive.
 *
 * In one operation, you can change any integer's value in any of the arrays to any value
 * between 1 and 6, inclusive.
 *
 * Return the minimum number of operations required to make the sum of values in nums1 equal
 * to the sum of values in nums2. Return -1 if it is not possible to make the sum of the
 * two arrays equal.
 *
 * Example 1:
 *      Input: nums1 = [1,2,3,4,5,6], nums2 = [1,1,2,2,2,2]
 *      Output: 3
 *      Explanation: You can make the sums of nums1 and nums2 equal with 3 operations. All indices are 0-indexed.
 *      - Change nums2[0] to 6. nums1 = [1,2,3,4,5,6], nums2 = [6,1,2,2,2,2].
 *      - Change nums1[5] to 1. nums1 = [1,2,3,4,5,1], nums2 = [6,1,2,2,2,2].
 *      - Change nums1[2] to 2. nums1 = [1,2,2,4,5,1], nums2 = [6,1,2,2,2,2].
 *
 * Example 2:
 *      Input: nums1 = [1,1,1,1,1,1,1], nums2 = [6]
 *      Output: -1
 *      Explanation: There is no way to decrease the sum of nums1 or to increase the sum of nums2 to make them equal.
 *
 * Example 3:
 *      Input: nums1 = [6,6], nums2 = [1]
 *      Output: 3
 *      Explanation: You can make the sums of nums1 and nums2 equal with 3 operations. All indices are 0-indexed.
 *      - Change nums1[0] to 2. nums1 = [2,6], nums2 = [1].
 *      - Change nums1[1] to 2. nums1 = [2,2], nums2 = [1].
 *      - Change nums2[0] to 4. nums1 = [2,2], nums2 = [4].
 *
 * Constraints:
 *      - 1 <= nums1.length, nums2.length <= 105
 *      - 1 <= nums1[i], nums2[i] <= 6
 */

public class EqualSumArraysMinOp {

    public static void main(String[] args) {
        System.out.println(new EqualSumArraysMinOp().minOperations(
                new int[]{1,2,3,4,5,6}, new int[]{1,1,2,2,2,2}
        ));
    }

    /*
    Input: nums1 = [1,2,3,4,5,6], nums2 = [1,1,2,2,2,2]
    Output: 3

    nums1 = [1,2,2,4,5,1], nums2 = [6,1,2,2,2,2].

    -. do sum nums1 and sum sums2
    -. alterCount (L -> H)
    -     L array, 1 => alterCount[5] ++
    -     L array, 6 => alterCount[0] ++
    -     H array, 1 => alterCount[0] ++
    -     H array, 6 => alterCount[5] ++

    idx = 6;
    while (diff > 0) {
    for(int idx=5; idx > 0; idx--) {
        required = (diff / idx) + (diff % idx == 0 ? 0 : 1);
        if (alterCount[idx] > required) {
            totalCnt += required;
            break;
        } else {
            totalCnt += alterCount[idx];
            diff -= idx * alterCount[idx];
        }
        diff -= idx * alterCount[idx];
    }
    -. count[7], count of each number
    -.
     */
    public int minOperations(int[] nums1, int[] nums2) {
        int sum1 = Arrays.stream(nums1).sum();
        int sum2 = Arrays.stream(nums2).sum();

        int[] alterCount = new int[6];
        int diff;
        if (sum1 < sum2) {
            for(int num: nums1) { alterCount[6 - num]++; }
            for(int num: nums2) { alterCount[num - 1]++; }
            diff = sum2 - sum1;
        } else if (sum1 > sum2) {
            for(int num: nums2) { alterCount[6 - num]++; }
            for(int num: nums1) { alterCount[num - 1]++; }
            diff = sum1 - sum2;
        } else {
            return 0;
        }
        int total = 0;
        for(int idx=5; diff > 0 && idx > 0; idx--) {
            var cnt = Math.min((diff/idx) + (diff % idx == 0 ? 0 : 1), alterCount[idx]);
            diff -= cnt * idx;
            total += cnt;
        }
        return diff > 0 ? -1 : total;
    }
}
