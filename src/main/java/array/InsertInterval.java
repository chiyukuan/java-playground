package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi]
 * represent the start and the end of the ith interval and intervals is sorted in ascending order
 * by starti. You are also given an interval newInterval = [start, end] that represents the start
 * and end of another interval.
 *
 * Example 1:
 *  Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 *  Output: [[1,5],[6,9]]
 *
 * Example 2:
 *  Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 *  Output: [[1,2],[3,10],[12,16]]
 *  Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 *
 * Constraints:
 *      0 <= intervals.length <= 104
 *      intervals[i].length == 2
 *      0 <= starti <= endi <= 105
 *      intervals is sorted by starti in ascending order.
 *      newInterval.length == 2
 *      0 <= start <= end <= 10^5
 *
 * Solution: O(n): Iterate through each interval and check for each overlapping case
 */
public class InsertInterval {

    public static void main(String[] args) throws Exception {
        var result = new InsertInterval().insert(new int[][]{{1, 3}, {6,9}}, new int[]{2, 5});
        // var result = new InsertInterval().insert(new int[][]{}, new int[]{2, 5});
        // var result = new InsertInterval().insert(new int[][]{{1, 5}}, new int[]{6, 8});
        // var result = new InsertInterval().insert(new int[][]{{6, 8}}, new int[]{1, 5});
        // var result = new InsertInterval().insert(new int[][]{{0, 5}, {9,12}}, new int[]{7, 16});
        Arrays.stream(result).forEach(interval -> System.out.printf("%d, %d\n", interval[0], interval[1]));
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        // special cases
        if (intervals.length == 0) {
            return new int[][]{{newInterval[0], newInterval[1]}};
        }
        if (newInterval[1] < intervals[0][0]) {
            // at begin
            var result = new int[intervals.length + 1][2];
            result[0] = newInterval;
            for(int idx=0; idx< intervals.length; idx++) {
                result[idx+1] = intervals[idx];
            }
            return result;
        }
        if (intervals[intervals.length - 1][1] < newInterval[0]) {
            // at end
            var result = new int[intervals.length + 1][2];
            for(int idx=0; idx< intervals.length; idx++) {
                result[idx] = intervals[idx];
            }
            result[result.length - 1] = newInterval;
            return result;
        }
        // find overlap interval
        int idx = 0;
        for (; idx < intervals.length; idx++) {
            if (newInterval[0] <= intervals[idx][1]) {
                break;
            }
        }
        int overlapBeg = idx;
        for(; idx < intervals.length; idx++) {
            if (newInterval[1] < intervals[idx][0]) {
                break;
            }
        }
        int overlapEnd = Math.max(idx-1, 0);
        // construct result
        var result = new int[intervals.length - (overlapEnd - overlapBeg)][2];
        for (var ii=0; ii < overlapBeg; ii++) {
            result[ii] = intervals[ii];
        }
        result[overlapBeg][0] = Math.min(intervals[overlapBeg][0], newInterval[0]);
        result[overlapBeg][1] = Math.max(intervals[overlapEnd][1], newInterval[1]);
        for(int ii=overlapBeg+1, jj = overlapEnd + 1; jj < intervals.length; ii++, jj++) {
            result[ii] = intervals[jj];
        }
        return result;
    }
}
