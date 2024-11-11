package my;

import java.util.Arrays;

public class UnionAndFind {

    public static void main(String[] args) {
        //var edges = new int[][]{{0,1}, {0,2}, {0,3}, {1,4}, {2,0}};
        var edges = new int[][]{{0,1}, {0,2}, {3,4}};
        // var edges = new int[][]{{0,1}, {1,2}, {0,3}, {3,2}};
        var graphSize = 5;

        var uf = new UnionAndFind(graphSize);
        for (var edge : edges) {
            if (! uf.union(edge[0], edge[1])) {
                System.out.printf("Is was connected already, before edge [%d %d]", edge[0], edge[1]);
            }
        }

        System.out.printf("All connected : %s\n", uf.count == graphSize);
    }
    int[] parents;
    int count=1;

    public UnionAndFind(int size) {
        this.parents = new int[size];
        for(int i=0; i < size; i++) {
            this.parents[i] = i;
        }
    }

    // return true indicate it was not connected and union DOES connect them.
    public boolean union(int from, int to) {
        int fromRoot = this.find(from);
        int toRoot = this.find(to);
        if (fromRoot != toRoot) {
            parents[toRoot] = fromRoot;
            count++;
            return true;
        }
        return false;
    }

    public int find(int node) {
        if (parents[node] != node) {
            parents[node] = parents[parents[node]]; // skip one parent and shorten the path to root
            node = parents[node];
        }
        return node;
    }

    public boolean isConnected(int node1, int node2) {
        return find(node1) == find(node2);
    }

    // max swap
    public int maximumSwap(int num) {
        // swapping the biggest digit, tail is better, after current index.
        //
        // create digit latest appear index table, size 10
        // for each digit, high to low, swap it??
        var array = String.valueOf(num).toCharArray();
        var lastIdxMap = new int[10];

        for (int idx=0; idx < array.length; idx++) {
            lastIdxMap[array[idx] - '0'] = idx;
        }
        for (int idx=0; idx < array.length; idx++) {
            var digit = array[idx] - '0';
            for (int ii=lastIdxMap.length - 1; ii > digit; ii--) {
                if (lastIdxMap[ii] > idx) {
                    var tmp = array[idx];
                    array[idx] = array[lastIdxMap[ii]];
                    array[lastIdxMap[ii]] = tmp;
                    return Integer.parseInt(String.valueOf(array));
                }
            }
        }
        return num;
    }
}
