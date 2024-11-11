package depth_first_search;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a
 * pair of nodes), write a function to check whether these edges make up a valid tree.
 * <p>
 * Example 1:
 * Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
 * Output: true
 * <p>
 * Example 2:
 * Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
 * Output: false
 * Note: you can assume that no duplicate edges will appear in edges. Since all edges
 * are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.
 */
public class GraphValidTree {

    private List[] graph;
    private BitSet done;

    public static void main(String[] args) throws Exception {
        System.out.println(new GraphValidTree().validTree(2, new int[][]{{1,0}}));
        System.out.println(new GraphValidTree().validTree(5, new int[][]{{0,1}, {1,2}, {2,3}, {1,4}}));
        System.out.println(new GraphValidTree().validTree(3, new int[][]{{0,1}, {1,2}, {0,2}}));
    }

    public boolean validTree(int n, int[][] edges) {
        List<List<Integer>> toOthers = new ArrayList<>();
        Set<Integer> notVisited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            toOthers.add(new ArrayList<>());
            notVisited.add(i);
        }
        for (var edge : edges) {
            toOthers.get(edge[0]).add(edge[1]);
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (toOthers.get(i).isEmpty() || ! notVisited.contains(i)) {
                continue;
            }
            stack.add(i);
            while (!stack.isEmpty()) {
                var cur = stack.pop();
                if (! notVisited.contains(cur)) {
                    return false;
                }
                notVisited.remove(cur);
                for (var other : toOthers.get(cur)) {
                    stack.push(other);
                }
            }
        }
        return notVisited.isEmpty();
    }
}
