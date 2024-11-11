package depth_first_search;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Given a reference of a node in a connected undirected graph.
 * Return a deep copy (clone) of the graph.
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 *
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 *
 *
 * Test case format:
 * For simplicity, each node's value is the same as the node's index (1-indexed). For example,
 * the first node with val == 1, the second node with val == 2, and so on. The graph is
 * represented in the test case using an adjacency list.
 *
 * An adjacency list is a collection of unordered lists used to represent a finite graph.
 * Each list describes the set of neighbors of a node in the graph.
 *
 * The given node will always be the first node with val = 1. You must return the copy of the
 * given node as a reference to the cloned graph.
 *
 * Example 1:
 *      Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 *      Output: [[2,4],[1,3],[2,4],[1,3]]
 *
 * Example 2:
 *      Input: adjList = [[]]
 *      Output: [[]]
 *      Explanation: Note that the input contains one empty list. The graph consists of only
 *      one node with val = 1 and it does not have any neighbors.
 *
 * Example 3:
 *      Input: adjList = []
 *      Output: []
 *      Explanation: This an empty graph, it does not have any nodes.
 */
public class CloneGraph {

    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public static void main(String[] args) throws Exception {
        // var nodes = new Node[1+3];  // ignore nodes[0]
        // int[][] neighbors = new int[][] {{2,3},{1,3},{1,2,3}};
        var nodes = new Node[1+4];  // ignore nodes[0]
        int[][] neighbors = new int[][] {{2,4},{1,3},{2,4},{1,3}};
        for (int i=1; i<nodes.length; i++) {
            nodes[i] = new Node(i+1);
        }
        for (int i=1; i<= neighbors.length; i++) {
            for (var n: neighbors[i-1]) {
                nodes[i].neighbors.add(nodes[n]);
            }
        }
        Node result = new CloneGraph().cloneGraph(nodes[1]);
        System.out.println(result);

        // print result
    }

    public Node cloneGraph(Node node) {
        if (node == null) return null;

        Map<Node, Node> nodeMap = new HashMap<>();
        nodeMap.put(node, new Node(node.val));
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            var cur = stack.peek();
            var curClone = nodeMap.get(cur);
            if (cur.neighbors.size() == curClone.neighbors.size()) {
                stack.pop();
                continue;
            }
            for (var n: cur.neighbors) {
                if (! nodeMap.containsKey(n)) {
                    nodeMap.put(n, new Node(n.val));
                    stack.push(n);
                }
                curClone.neighbors.add(nodeMap.get(n));
            }
        }
        return nodeMap.get(node);
    }
}
