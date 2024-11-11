package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
 * <p>
 * Example 1:
 * Input: n = 4, k = 2
 * Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 * Explanation: There are 4 choose 2 = 6 total combinations.
 * Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
 * <p>
 * Example 2:
 * Input: n = 1, k = 1
 * Output: [[1]]
 * Explanation: There is 1 choose 1 = 1 total combination.
 */
public class Combinations {

    public static void main(String[] args) {
        List<List<Integer>> result = new Combinations().combine(4, 2);
        System.out.println(result);
    }

    List<List<Integer>> result;
    int[] nums;
    int maxNum;

    public List<List<Integer>> combine(int maxNum, int numNeeded) {
        this.result = new ArrayList<>();
        this.nums = new int[numNeeded];
        this.maxNum = maxNum;
        pickNext(0, numNeeded);
        return this.result;
    }

    public void pickNext(int prevNum, int numNeeded) {
        if (numNeeded == 0) {
            var numList = new ArrayList<Integer>();
            for (var num : nums) {
                numList.add(num);
            }
            result.add(numList);
        } else {
            for (int currNum = prevNum + 1; currNum <= maxNum; currNum++) {
                nums[numNeeded - 1] = currNum;
                pickNext(currNum, numNeeded - 1);
            }
        }
    }
}
