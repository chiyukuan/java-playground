package array;

import java.util.*;

/**
 * Given the availability time slots arrays slots1 and slots2 of two people and a meeting
 * duration, return the earliest time slot that works for both of them and is of duration.
 *
 * If there is no common time slot that satisfies the requirements, return an empty array.
 *
 * The format of a time slot is an array of two elements [start, end] representing an inclusive
 * time range from start to end.
 *
 * It is guaranteed that no two availability slots of the same person intersect with each other.
 * That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either
 * start1 > end2 or start2 > end1.
 *
 * Example 1:
 *      Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
 *      Output: [60,68]
 *
 * Example 2:
 *      Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
 *      Output: * []
 *
 * Constraints:
 *      - 1 <= slots1.length, slots2.length <= 10^4
 *      - slots1[i].length, slots2[i].length == 2
 *      - slots1[i][0] < slots1[i][1]
 *      - slots2[i][0] < slots2[i][1]
 *      - 0 <= slots1[i][j], slots2[i][j] <= 10^9
 *      = 1 <= duration <= 10^6
 */
public class MeetingScheduler {
    public static void main(String[] args) {
        System.out.println(new MeetingScheduler().minAvailableDuration(
                new int[][]{{10, 50}, {60, 120}, {140, 210}},
                new int[][]{{0, 15}, {60, 70}},
                12)
        );
        System.out.println(new MeetingScheduler().minAvailableDuration(
                new int[][]{{10, 50}, {60, 120}, {140, 210}},
                new int[][]{{0, 15}, {60, 80}},
                12)
        );
    }

    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        var sorted1 = Arrays.stream(slots1).sorted(Comparator.comparingInt(e -> e[0])).toList();
        var sorted2 = Arrays.stream(slots2).sorted(Comparator.comparingInt(e -> e[0])).toList();
        int idx1 = 0;
        int idx2 = 0;
        while (idx1 < sorted1.size() && idx2 < sorted2.size()) {
            var elm1 = sorted1.get(idx1);
            var elm2 = sorted2.get(idx2);
            if (elm1[0] > elm2[0]) {
                if (elm1[0] + duration <= elm1[1] && elm1[0] + duration <= elm2[1]) {
                    return Arrays.asList(elm1[0], elm1[0] + duration);
                }
            } else {
                if (elm2[0] + duration <= elm1[1] && elm2[0] + duration <= elm2[1]) {
                    return Arrays.asList(elm2[0], elm2[0] + duration);
                }
            }
            if (elm1[0] < elm2[0]) {
                idx1++;
            } else if (elm1[0] == elm2[0]) {
                idx1++;
                idx2++;
            } else {
                idx2++;
            }
        }
        return new ArrayList<>();
    }
}
