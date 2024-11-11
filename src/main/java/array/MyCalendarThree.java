package array;

import java.util.*;

/**
 * A k-booking happens when k events have some non-empty intersection (i.e., there is some time
 * that is common to all k events.)
 *
 * You are given some events [startTime, endTime), after each given event, return an integer k
 * representing the maximum k-booking between all the previous events.
 *
 * Implement the MyCalendarThree class:
 *
 * MyCalendarThree() Initializes the object.
 * int book(int startTime, int endTime) Returns an integer k representing the largest integer such
 * that there exists a k-booking in the calendar.
 *
 *
 * Example 1:
 *      Input ["MyCalendarThree", "book", "book", "book", "book", "book", "book"]
 *            [[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
 *      Output [null, 1, 1, 2, 3, 3, 3]
 *
 *      Explanation
 *          MyCalendarThree myCalendarThree = new MyCalendarThree();
 *          myCalendarThree.book(10, 20); // return 1
 *          myCalendarThree.book(50, 60); // return 1
 *          myCalendarThree.book(10, 40); // return 2
 *          myCalendarThree.book(5, 15); // return 3
 *          myCalendarThree.book(5, 10); // return 3
 *          myCalendarThree.book(25, 55); // return 3
 *
 * Constraints:
 *      - 0 <= startTime < endTime <= 109
 *      - At most 400 calls will be made to book.
 */
public class MyCalendarThree {

    public static void main(String[] args) {
        MyCalendarThree calendar = new MyCalendarThree();
        System.out.println(calendar.book(10, 20));
        System.out.println(calendar.book(50, 60));
        System.out.println(calendar.book(10, 40));
        System.out.println(calendar.book(5, 15));
        System.out.println(calendar.book(5, 10));
        System.out.println(calendar.book(25, 55));
    }

    SortedSet<int[]> events;
    public MyCalendarThree() {
        this.events = new TreeSet<>(Comparator.comparingInt((int[] e) -> e[0]).thenComparingInt(e -> e[1]));
    }

    public int book(int startTime, int endTime) {
        var size = this.events.size() + 1;
        this.events.add(new int[]{startTime, size});
        this.events.add(new int[]{endTime, -size});
        int lastTime = Integer.MIN_VALUE;
        int max=0;
        int curr=0;
        for (var elm: this.events) {
            if (elm[0] != lastTime) {
                max = Math.max(max, curr);
                lastTime = elm[0];
            }
            if (elm[1] > 0) {
                curr ++;
            } else {
                curr --;
            }
        }
        return max;
    }
}
