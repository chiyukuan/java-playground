package array;

import java.util.*;

/**
 * Given an array of integers nums, half of the integers in nums are odd, and the other half are even.
 *
 * Sort the array so that whenever nums[i] is odd, i is odd, and whenever nums[i] is even, i is even.
 *
 * Return any answer array that satisfies this condition.
 *
 * Example 1:
 *      Input: nums = [4,2,5,7]
 *      Output: [4,5,2,7]
 *      Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.
 *
 * Example 2:
 *      Input: nums = [2,3]
 *      Output: [2,3]
 */
public class SortArrayByParityII {
    public static void main(String[] args) {
        var res = new SortArrayByParityII().sortArrayByParityII(new int[]{4, 3, 5, 7});
        Arrays.stream(res).forEach(num -> System.out.printf("%d ", num));
        System.out.println();
    }

    public int[] sortArrayByParityII(int[] nums) {
        int oIdx = 1;
        int eIdx = 0;
        while(oIdx < nums.length && eIdx < nums.length) {
            while ((nums[oIdx] & 0x1) != 0) {
                oIdx += 2;
                if (oIdx >= nums.length) {
                    return nums;
                }
            }
            while ((nums[eIdx] & 0x1) == 0) {
                eIdx += 2;
                if (eIdx >= nums.length) {
                    return nums;
                }
            }

            var tmp = nums[oIdx];
            nums[oIdx] = nums[eIdx];
            nums[eIdx] = tmp;
        }
        return nums;
    }
}
