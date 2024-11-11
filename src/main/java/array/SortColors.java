package array;

/**
 * Given an array with n objects colored red, white or blue, sort them so that objects of the
 * same color are adjacent, with the colors in the order red, white and blue.
 *
 * <p>Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue
 * respectively.
 *
 * <p>Note: You are not suppose to use the library's sort function for this problem.
 *
 * <p>Follow up: A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total
 * number of 0's, then 1's and followed by 2's.
 *
 * <p>Could you come up with an one-pass algorithm using only constant space?
 *
 * <p>Solution: The below solution works with one pass. The basic idea is to keep track of start and
 * end index of contiguous 1s and push the 0s to left of 1s and 2 to right of 1s.
 */
public class SortColors {

    public static void main(String[] args) throws Exception {
        int[] nums = {2, 1, 0, 0, 1};
        new SortColors().sortColors(nums);
        for (int i : nums) System.out.println(i);
    }

    static final int RED = 0;
    static final int BLUE = 2;

    public void sortColors(int[] nums) {
        var rIdx = 0;
        var wIdx = 0;
        var bIdx = nums.length - 1;
        while (wIdx <= bIdx) {
            if (nums[wIdx] == RED) {
                if (wIdx == rIdx) {
                    wIdx++;
                    rIdx++;
                } else {
                    var tmp = nums[rIdx];
                    nums[rIdx++] = nums[wIdx];
                    nums[wIdx] = tmp;
                }
            } else if (nums[wIdx] == BLUE) {
                if (wIdx == bIdx) {
                    break;
                }
                var tmp = nums[bIdx];
                nums[bIdx--] = nums[wIdx];
                nums[wIdx] = tmp;
            } else {
                wIdx++;
            }
        }
    }
}
