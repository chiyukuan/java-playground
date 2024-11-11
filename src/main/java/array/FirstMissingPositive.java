package array;

/**
 * Given an unsorted integer array, find the first missing positive integer.
 *
 * <p>For example, Given [1,2,0] return 3, and [3,4,-1,1] return 2.
 *
 * <p>Your algorithm should run in O(n) time and uses constant space.
 */
public class FirstMissingPositive {
    private int L;

    public static void main(String[] args) throws Exception {
        // int[] nums = {1, 3, 5, 9};
        //int[] nums = {3, 4, -1, 1};
        // int[] nums = {2, 2};
        int[] nums = {1,2,6,3,5,4};
        // int[] nums = {1, 2, 0};
        // int[] nums = {1, 1};
        System.out.println(new FirstMissingPositive().firstMissingPositive(nums));
    }

    public int firstMissingPositive(int[] nums) {
        int defaultRet = nums.length;
        var markOnly = nums.length + 1;

        for (int i=0; i< nums.length; i++) {
            if (nums[i] <= 0) {
                nums[i] = markOnly;
            }
        }
        for (int num: nums) {
            var idx = Math.abs(num);
            if (idx >= markOnly) {
                continue;
            } else if (idx == nums.length) {
                defaultRet = nums.length + 1;
            } else {
                nums[idx] = - Math.abs(nums[idx]);
            }
        }
        for (int i=1; i<nums.length; i++) {
            if (nums[i] > 0) {
                return i;
            }
        }
        return defaultRet;
    }
}
