package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array nums of distinct integers, return all the possible permutations. You can return
 * the answer in any order.
 *
 * Example 1:
 *      Input: nums = [1,2,3]
 *      Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * Example 2:
 *      Input: nums = [0,1]
 *      Output: [[0,1],[1,0]]
 *
 * Example 3:
 *      Input: nums = [1]
 *      Output: [[1]]
 *
 * Constraints:
 *      - 1 <= nums.length <= 6
 *      - -10 <= nums[i] <= 10
 *      - All the integers of nums are unique.
 */
public class Permutations {

    public static void main(String[] args) throws Exception {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = new Permutations().permute(nums);
        result.forEach(System.out::println);
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        doNext(0, result, nums);
        return result;
    }

    public void doNext(int startIdx, List<List<Integer>> ret, int[] nums) {
        if (startIdx == nums.length - 1) {
            List<Integer> list = new ArrayList<>();
            for (int n : nums) list.add(n);
            ret.add(list);
        } else {
            for (int idx = startIdx; idx < nums.length; idx++) {
                // select, move elm to startIdx
                int tmp = nums[idx];
                nums[idx] = nums[startIdx];
                nums[startIdx] = tmp;
                doNext(startIdx + 1, ret, nums);
                // unselect, move elm back
                tmp = nums[idx];
                nums[idx] = nums[startIdx];
                nums[startIdx] = tmp;
            }
        }
    }
}
