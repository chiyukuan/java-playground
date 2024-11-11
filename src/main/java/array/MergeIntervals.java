package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 * Example 1:
 *      Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 *      Output: [[1,6],[8,10],[15,18]]
 *      Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 *
 * Example 2:
 *      Input: intervals = [[1,4],[4,5]]
 *      Output: [[1,5]]
 *      Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 * Constraints:
 *      - 1 <= intervals.length <= 104
 *      - intervals[i].length == 2
 *      - 0 <= starti <= endi <= 104
 */
public class MergeIntervals {

    public static void main(String[] args) throws Exception {
        var ret = new MergeIntervals().merge(new int[][]{{1,3},{2,6},{8,10},{15,18}});
        for (var elm: ret) {
            System.out.println(elm[0] + " " + elm[1]);
        }
    }

    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        var retIdx = 0;
        for (int idx=1; idx < intervals.length; idx++) {
            if (intervals[retIdx][1] < intervals[idx][0]) {
                retIdx++; // active is ready.
                intervals[retIdx] = intervals[idx]; // next active one
            } else {
                // update active
                intervals[retIdx][1] = Math.max(intervals[retIdx][1], intervals[idx][1]);
            }
        }
        return Arrays.copyOfRange(intervals, 0, retIdx+1);
    }

    public int[][] merge2(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> ret = new ArrayList<>();

        var begin = intervals[0][0];
        var end = intervals[0][1];
        for (var elm: intervals) {
            if (end < elm[0]) {
                ret.add(new int[]{begin, end});
                begin = elm[0];
                end = elm[1];
            } else {
                end = Math.max(end, elm[1]);
            }
        }
        ret.add(new int[]{begin, end});

        return ret.toArray(new int[ret.size()][2]);
    }
}
