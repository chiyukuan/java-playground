package breadth_first_search;

import java.util.*;

/**
 * Your car starts at position 0 and speed +1 on an infinite number line. (Your car can go into negative positions.)
 *
 * Your car drives automatically according to a sequence of instructions A (accelerate) and R
 * (reverse).
 *
 * When you get an instruction "A", your car does the following: position += speed, speed *= 2.
 *
 * When you get an instruction "R", your car does the following: if your speed is positive then
 * speed = -1 , otherwise speed = 1. (Your position stays the same.)
 *
 * <p>For example, after commands "AAR", your car goes to positions 0->1->3->3, and your speed goes
 * to 1->2->4->-1.
 *
 * <p>Now for some target position, say the length of the shortest sequence of instructions to get
 * there.
 *
 * Example 1:
 *      Input: target = 3
 *      Output: 2
 *      Explanation: The shortest instruction sequence is "AA". Your position goes from 0->1->3.
 *
 * Example 2:
 *      Input: target = 6
 *      Output: 5
 *      Explanation: The shortest instruction sequence is "AAARA". Your position goes from 0->1->3->7->7->6.
 *
 * <p>Note:
 *
 * <p>1 <= target <= 10000.
 *
 * <p>Solution: O(n log n) Do a BFS and visit every possible state. Prune the search space by
 * avoiding negative vertices and keep a boundary target of approximately (target * 2) - beyond this
 * boundary target the race car should not progress in the forward direction.
 */
public class RaceCar {

    public static void main(String[] args) {
        System.out.println(new RaceCar().racecar(6));
    }

    public int racecar(int target) {
        // visit is not required. Node with 2 values, it will only slow down the calculation

        Set<int[]> visited = new HashSet<>();
        Queue<int[]>  queue = new ArrayDeque<>();
        queue.add(new int[]{0, 1});
        int queueSize = queue.size();
        int cmdCount = 0;
        while(! queue.isEmpty()) {
            var curr = queue.poll();
            if (curr[0] == target) {
                return cmdCount;
            }
            visited.add(curr);
            var speedUp = new int[]{curr[0] + curr[1], 2 * curr[1]};
            if (! visited.contains(speedUp)) {
                queue.add(speedUp);
            }
            if ((curr[0] + curr[1] > target && curr[1] > 0) || (curr[0] + curr[1] < target && curr[1] < 0)) {
                var reverse = new int[]{curr[0], curr[1] > 0 ? -1 : 1};
                if (!visited.contains(reverse)) {
                    queue.add(reverse);
                }
            }
            if (--queueSize == 0) {
                queueSize = queue.size();
                cmdCount++;
            }
        }
        return -1;
    }
}
