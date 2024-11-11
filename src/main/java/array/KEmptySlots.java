package array;

import java.util.TreeSet;

/**
 * There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom one by one in N days.
 * In each day, there will be exactly one flower blooming and it will be in the status of blooming since then.
 *
 * Given an array flowers consists of number from 1 to N. Each number in the array represents the place
 * where the flower will open in that day.
 *
 * For example,flowers[i] = x means that the unique flower that blooms at day i will be at position x,
 * where i and x will be in the range from 1 to N.
 *
 * Also given an integer k, you need to output in which day there exists two flowers in the status of blooming,
 * and also the number of flowers between them is k and these flowers are not blooming.
 *
 * If there isn't such day, output -1.
 *
 * Example 1:
 *      Input: flowers: [1,3,2], k: 1
 *      Output: 2
 *      Explanation: In the second day, the first and the third flower have become blooming.
 *
 * Example 2:
 *      Input: flowers: [1,2,3], k: 1
 *      Output: -1
 *
 * Note:
 *      The given array will be in the range [1, 20000].
 * Additional Notes:
 *      flowers[i] = x
 *      1. should mean that the unique flower that blooms at dayi+1(noti) will be at positionx.
 *      2. If you can get multiple possible results, then you need to return the minimum one.
 *
 * Thoughts:
 *      1. construct a reverse index array: days[x] = i + 1 => the blooming day for place "x + 1" .
 *      2. Find the subarray days[left, left + 1, ..., left + k - 1, right] which satisfies for and
 *         left + 1<= i <=left + k-1: days[left] < days[i] && days[right] < days[i] => result is
 *         max(days[left], day[right]).
 */
public class KEmptySlots {

    public static void main(String[] args) throws Exception {
        System.out.println(new KEmptySlots().kEmptySlots(new int[]{1, 3, 2}, 1));
        System.out.println(new KEmptySlots().kEmptySlots(new int[]{1, 2, 3}, 1));
    }

    public int kEmptySlots(int[] flowers, int k) {
        var skip = -3;
        var search_down = 2;
        var search_up = -2;
        var blooms = new int[flowers.length + 1];
        for(int idx=0; idx < flowers.length; idx++) {
            int loc = flowers[idx];
            if (blooms[loc] == 0) {
                blooms[loc] = skip;
                if (loc + k + 1 <= blooms.length - 1) {
                    blooms[loc + k + 1] = search_down;
                }
                if (loc - k - 1 >= 0) {
                    blooms[loc - k - 1] = search_up;
                }
            } else if (blooms[loc] == search_down) {
                // check loc - k - 1 to here
                boolean ok = true;
                for(int ii = loc - k; ii < loc; ii++) {
                    if (blooms[ii] != 0) {
                        ok = false;
                        break;
                    }
                    blooms[ii] = skip;
                }
                if (ok) {
                    return  idx + 1;
                }
            } else if (blooms[loc] == -2) {
                // check here to loc + k + 1
                boolean ok = true;
                for (int ii = loc + 1; ii <= loc + k; ii++) {
                    if (blooms[ii] != 0) {
                        ok = false;
                        break;
                    }
                    blooms[ii] = skip;
                }
                if (ok) {
                    return  idx + 1;
                }
            }
        }
        return -1;
    }
}
