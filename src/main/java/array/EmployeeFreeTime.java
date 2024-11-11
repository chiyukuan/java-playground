package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * We are given a list schedule of employees, which represents the working time for each employee.
 *
 * <p>Each employee has a list of non-overlapping Intervals, and these intervals are in sorted
 * order.
 *
 * <p>Return the list of finite intervals representing common, positive-length free time for all
 * employees, also in sorted order.
 *
 * <p>Example 1: Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]] Output: [[3,4]] Explanation:
 * There are a total of three employees, and all common free time intervals would be [-inf, 1], [3,
 * 4], [10, inf]. We discard any intervals that contain inf as they aren't finite. Example 2: Input:
 * schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]] Output: [[5,6],[7,9]] (Even though we are
 * representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays.
 * For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not
 * defined.)
 *
 * <p>Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
 *
 * <p>Note:
 *
 * <p>schedule and schedule[i] are lists with lengths in range [1, 50]. 0 <= schedule[i].start <
 * schedule[i].end <= 10^8.
 *
 * <p>Solution: O(N * Log(N)) Where N is the number of flattened schedules.
 */
public class EmployeeFreeTime {

    public static void main(String[] args) {
        var schedules = Arrays.asList(
                Arrays.asList(new int[]{1, 2}, new int[]{5, 6}),
                Arrays.asList(new int[]{1, 3}),
                Arrays.asList(new int[]{4, 10})
        );
        for (var result : new EmployeeFreeTime().freeTime(schedules)) {
            System.out.printf("{%d, %d} ", result[0], result[1]);
        }
    }


    /*
    Input:  [[[1,2],[5,6]],[[1,3]],[[4,10]]]
    Output: [[3,4]]

    Sort schedule based on start(primary) and end(secondary)<-No need.

    Based on the lastEndTime,
        if lastEndTime < startTime => new list (lastEndTime -> startTime)

        lastEndTime = max(lastEndTime, endTime)
     */
    public List<int[]> freeTime(List<List<int[]>> schedules) {
        var sorted = schedules.stream().flatMap(Collection::stream).parallel()
                .sorted(Comparator.comparingInt(e->e[0])).collect(Collectors.toList());

        var lastEndTime = sorted.get(0)[1];
        List<int[]> ret = new ArrayList<>();
        for(var duration: sorted) {
            if (duration[0] > lastEndTime) {
                ret.add(new int[]{lastEndTime, duration[0]});
            }
            lastEndTime = Math.max(lastEndTime, duration[1]);
        }
        return ret;
    }

    public List<int[]> freeTime2(List<List<int[]>> schedules) {
        // Collection::stream: s -> s.stream()
        var durations = schedules.stream().parallel().flatMap(Collection::stream)
                .sorted(Comparator.comparingInt((int[] d) -> d[0])).toList();

        var result = new ArrayList<int[]>();
        var lastEnd = durations.get(0)[1];
        for (int ii = 1; ii < durations.size(); ii++) {
            // if lastEnd < next starting point => new free schedule
            if (lastEnd < durations.get(ii)[0]) {
                result.add(new int[]{lastEnd, durations.get(ii)[0]});
            }
            // if lastEnd < next end point => update the lastEnd
            if (lastEnd < durations.get(ii)[1]) {
                lastEnd = durations.get(ii)[1];
            }
        }
        return result;
    }
}
