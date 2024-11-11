package array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes
 * difference between any two time-points in the list.
 *
 * Example 1:
 *      Input: timePoints = ["23:59","00:00"]
 *      Output: 1
 *
 * Example 2:
 *      Input: timePoints = ["00:00","23:59","00:00"]
 *      Output: 0
 *
 * Constraints:
 *      - 2 <= timePoints.length <= 2 * 104
 *      - timePoints[i] is in the format "HH:MM".
 *
 * Solution: O(N log N) convert each time value of the form hh:mm to minutes and sort the array.
 * For every pair (i, j) where j = i + 1 (also for the case where i = 0 and j = N - 1) check the
 * minute difference and return the minimum time difference as the answer.
 */
public class MinimumTimeDifference {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("23:59", "00:00");
        System.out.println(new MinimumTimeDifference().findMinDifference(list));
    }

    public int findMinDifference(List<String> timePoints) {
        if (timePoints.size() < 2) {
            return 0;
        }
        var minutes = timePoints.stream().map(timeStr -> {
                    var strs = timeStr.split(":");
                    return Integer.parseInt(strs[0]) * 60 + Integer.parseInt(strs[1]);
                })
                .sorted(Comparator.comparingInt(v -> v)).toArray(Integer[]::new);

        int prev = minutes[0];
        int min = minutes[0] + (24 * 60) - minutes[minutes.length- 1];
        for(int i=1; i<minutes.length; i++) {
            int tmp = minutes[i];
            min = Math.min(tmp - prev, min);
            prev = tmp;
        }
        return min;
    }
}
