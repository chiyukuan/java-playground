package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * You are given two 0-indexed integer arrays fronts and backs of length n, where the ith card
 * has the positive integer fronts[i] printed on the front and backs[i] printed on the back.
 * Initially, each card is placed on a table such that the front number is facing up and the
 * other is facing down. You may flip over any number of cards (possibly zero).
 * <p>
 * After flipping the cards, an integer is considered good if it is facing down on some card
 * and not facing up on any card.
 * <p>
 * Return the minimum possible good integer after flipping the cards. If there are no good integers,
 * return 0.
 * <p>
 * Example:
 * Input: fronts = [1,2,4,4,7], backs = [1,3,4,1,3]
 * Output: 2
 * Explanation: If we flip the second card, the fronts are [1,3,4,4,7] and the backs are [1,2,4,1,3].
 * We choose the second card, which has number 2 on the back, and it isn't on the front of any card, so 2 is good.
 * <p>
 * Example:
 * Input: fronts = [1], backs = [1]
 * Output: 0
 * Explanation: There are no good integers no matter how we flip the cards, so we return 0.
 * <p>
 * Constraints:
 * - n == fronts.length == backs.length
 * - 1 <= n <= 1000
 * - 1 <= fronts[i], backs[i] <= 2000
 */
public class CardFilipGame {

    public static void main(String[] args) {
        // var result = new CardFilipGame().flipcard(new int[]{1,2,4,4,7}, new int[]{1,3,4,1,3});
        var result = new CardFilipGame().flipcard(new int[]{1, 1}, new int[]{1, 2});
        System.out.println(result);
    }

    /*
    Input: fronts, backs, positive integer
    Output: minimum possible good integer after flipping the cards

    Allow to flip over any number of cards (including 0)

    After flipping,
    - good integer: if it is facing down on some card and not facing up on any card.
        - No facing up and some facing down

    For example,
      fronts = [1,2,4,4,7], backs = [1,3,4,1,3]


    if number of both => remove this number,  (blackset)
    find min num not in blackset
     */
    public int flipcard(int[] fronts, int[] backs) {
        Set<Integer> blackSet = new HashSet<>();
        for (int i=0; i< fronts.length; i++) {
            if (fronts[i] == backs[i]) {
                blackSet.add(fronts[i]);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i=0; i< fronts.length; i++) {
            if (! blackSet.contains(fronts[i])) {
                min = Math.min(min, fronts[i]);
            }
            if (! blackSet.contains(backs[i])) {
                min = Math.min(min, backs[i]);
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
