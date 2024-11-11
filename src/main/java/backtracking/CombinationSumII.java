package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of candidate numbers (candidates) and a target number (target), find
 * all unique combinations in candidates where the candidate numbers sum to target.
 *
 * Each number in candidates may only be used once in the combination.
 * Note: The solution set must not contain duplicate combinations.
 *
 * Example 1:
 *      Input: candidates = [10,1,2,7,6,1,5], target = 8
 *      Output: [ [1,1,6], [1,2,5], [1,7], [2,6] ]
 *
 * Example 2:
 *      Input: candidates = [2,5,2,1,2], target = 5
 *      Output: [ [1,2,2], [5] ]
 *
 * Constraints:
 *      - 1 <= candidates.length <= 100
 *      - 1 <= candidates[i] <= 50
 *      - 1 <= target <= 30
 */
public class CombinationSumII {
    /**
     * Main method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int[] candidates = {1, 1, 2, 2};
        List<List<Integer>> result = new CombinationSumII().combinationSum2(candidates, 4);
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        combination(0, target, candidates, new ArrayList<>(), result);
        return result;
    }

    private void combination(
            int i, int target, int[] candidates, List<Integer> row, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(row));
        } else if (target > 0) {
            for (int j = i, l = candidates.length; j < l; j++) {
                if (j > i && candidates[j] == candidates[j - 1]) continue;
                row.add(candidates[j]);
                combination(j + 1, target - candidates[j], candidates, row, result);
                row.remove(row.size() - 1);
            }
        }
    }
}
