package depth_first_search;

import java.util.*;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you
 * must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 *
 * Example 1:
 *      Input: numCourses = 2, prerequisites = [[1,0]]
 *      Output: true
 *      Explanation: There are a total of 2 courses to take. To take course 1 you should have
 *      finished course 0. So it is possible.
 *
 * Example 2:
 *      Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 *      Output: false
 *      Explanation: There are a total of 2 courses to take. To take course 1 you should have
 *      finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 *
 * Constraints:
 *      - 1 <= numCourses <= 2000
 *      - 0 <= prerequisites.length <= 5000
 *      - prerequisites[i].length == 2
 *      - 0 <= ai, bi < numCourses
 *      - All the pairs prerequisites[i] are unique.
 */
public class CourseSchedule {

    public static void main(String[] args) throws Exception {
        // System.out.println(new CourseSchedule().canFinish(2, new int[][]{{1, 0}}));
        System.out.println(new CourseSchedule().canFinish(3, new int[][]{{0, 1}, {0, 2}, {1, 2}}));
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i=0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (var pre: prerequisites) {
            graph.get(pre[0]).add(pre[1]);
        }
        boolean[] visited = new boolean[numCourses];
        boolean[] inPath = new boolean[numCourses];
        Stack<Integer> stack = new Stack<>();
        for (int i=0; i<numCourses; i++) {
            if (visited[i]) {
                continue;
            }
            stack.push(i); // DFS
            while(! stack.isEmpty()) {
                var cur = stack.peek();
                if (! inPath[cur]) {
                    inPath[cur] = true;
                    if (! graph.get(cur).isEmpty()) {
                        for (var pre : graph.get(cur)) {
                            if (inPath[pre]) {
                                return false;
                            }
                            if (!visited[pre]) { // not visited yet
                                stack.add(pre);
                            }
                        }
                        continue;
                    }
                }
                visited[cur] = true;
                inPath[cur] = false;
                stack.pop();
            }
        }
        return true;
    }
}
