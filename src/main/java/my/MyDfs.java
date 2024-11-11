package my;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class MyDfs {

    public static void main(String[] args) {
        //var edges = new int[][]{{0,1}, {0,2}, {0,3}, {1,4}, {2,0}};
        var edges = new int[][]{{0,1}, {0,2}, {3,4}};
        // var edges = new int[][]{{0,1}, {1,2}, {0,3}, {3,2}};

        var myDfs = new MyDfs();

    }
    /*
    Map<Integer, List<Integer>> graph;
    Set<Integer> notVisited;

    public boolean validTree(int[][] edges) {
        graph = new HashMap<>();
        notVisited = new HashSet<>();
        for (var edge: edges) {
            if (graph.containsKey(edge[0])) {
                // found
                graph.get(edge[0]).add(edge[1]);
            } else {
                List<Integer> dests = new ArrayList<>();
                dests.add(edge[1]);
            }
            notVisited.add(edge[0]);
            notVisited.add(edge[1]);
        }

        var vertex = notVisited.stream().findAny().get().e
        notVisited.remove(vertex);
        if (! dfs(vertex)) {
            return false;
        }
        return notVisited.isEmpty();
    }

    public boolean dfs(int vertex) {
        if (! this.notVisited.contains(vertex)) {
            return false;
        }
    }
    */
    // public void dfs(Node root, )
}
