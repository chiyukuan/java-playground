package array;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are
 * subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding
 * places of nums2.
 *
 * The Next Greater Number of a number x in nums1 is the first greater number to its right in
 * nums2. If it does not exist, output -1 for this number.
 *
 * Example 1:
 *      Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
 *      Output: [-1,3,-1]
 *      Explanation: For number 4 in the first array, you cannot find the next greater number for
 *      it in the second array, so output -1. For number 1 in the first array, the next greater number
 *      for it in the second array is 3. For number 2 in the first array, there is no next greater number
 *      for it in the second array, so output -1.
 *
 * Example 2:
 *      Input: nums1 = [2,4], nums2 = [1,2,3,4].
 *      Output: [3,-1]
 *      Explanation: For number 2 in the first array, the next greater number for it in the second array
 * is 3. For number 4 in the first array, there is no next greater number for it in the second
 * array, so output -1. Note: All elements in nums1 and nums2 are unique. The length of both nums1
 * and nums2 would not exceed 1000.
 */
public class NextGreaterElementI {

    /**
     * Main method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int[] A = {4, 1, 2};
        int[] B = {1, 3, 4, 2};
        int[] result = new NextGreaterElementI().nextGreaterElement(A, B);
        System.out.println(result);
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // Map<Integer, nextGreaterNums>
        // from h -> l, record max num,
        //      if nums[i] > max num, put nums[i], -1 to map,
        //      else from i -> h find nums[j] > nums[i] and put nums[i], nums[j]
        // from l -> h, update nums1 with map.get(num)

        Map<Integer, Integer> ngNumMap = new HashMap<>();
        int max = Integer.MIN_VALUE;
        // nums2 = 1, 3, 4, 2
        for (int i=nums2.length - 1; i >= 0; i--) {
            if (nums2[i] > max) {
                ngNumMap.put(nums2[i], -1);
                max = nums2[i];
            } else {
                for (int j=i+1; j<nums2.length; j++) {
                    if (nums2[j] > nums2[i]) {
                        ngNumMap.put(nums2[i], nums2[j]);
                        break;
                    }
                }
            }
        }
        for (int i=0; i<nums1.length; i++) {
            nums1[i] = ngNumMap.get(nums1[i]);
        }
        return nums1;
    }
}
