package array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * determine if a person could attend all meetings.
 *
 * For example,
 *      - Given [[0, 30],[5, 10],[15, 20]], return false.
 *
 * Solution: Sort the interval based on the start interval. Then, for every interval check if the
 * previous interval ends before the start of the current interval.
 */
public class MeetingRooms {

    public static void main(String[] args) throws Exception {
        List<int[]> intervals = new ArrayList<>();
        intervals.add(new int[]{0, 30});
        intervals.add(new int[]{5, 10});
        intervals.add(new int[]{15, 20});
        System.out.println(new MeetingRooms().canAttendMeetings(intervals));
    }

    public boolean canAttendMeetings(List<int[]> intervals) {

        var sorted = intervals.stream().sorted(Comparator.comparingInt(e -> e[0])).toList();

        var lastEnd = Integer.MIN_VALUE;
        for (var e: sorted) {
            if (e[0]<lastEnd) {
                return false;
            } else {
                lastEnd = e[1];
            }
        }
        return true;
    }
}
