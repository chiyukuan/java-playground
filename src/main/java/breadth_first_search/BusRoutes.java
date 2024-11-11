package breadth_first_search;

import java.util.*;

/**
 * We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever.
 * For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels
 * in the sequence 1->5->7->1->5->7->1->. .. forever.
 *
 * We start at bus stop S (initially not on a bus), and we want to go to bus stop T. Travelling
 * by buses only, what is the least number of buses we must take to reach our destination? Return -1
 * if it is not possible.
 *
 * Example:
 *      Input: routes = [[1, 2, 7], [3, 6, 7]], S = 1 T = 6
 *      Output: 2
 *      Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus
 *      to the bus stop 6.
 *
 * Note:
 *      - 1 <= routes.length <= 500. 1 <= routes[i].length <= 500. 0 <= routes[i][j] < 10 ^ 6.
 *
 * Solution: Model a graph based on interconnection of routes and then run a BFS to find the
 * shortest distance.
 */
public class BusRoutes {

    public static void main(String[] args) {
        int[][] routes = {
                {1, 2, 3, 9}, {9, 3, 4, 5, 8}, {5, 6, 7, 8}, {9, 8, 10, 11}, {12, 13, 14, 6, 1, 2, 3, 5, 7}
        };
        System.out.println(new BusRoutes().numBusesToDestination(routes, 9, 14));
    }

    static class BRoute {
        int index;
        public Set<BRoute> nexts;

        public BRoute(int index) {
            this.index = index;
            this.nexts = new HashSet<>();
        }

        @Override
        public int hashCode() {
            return index;
        }
    }

    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        HashMap<Integer, List<BRoute>> bstopMap = new HashMap<>();
        List<BRoute> broutes = new ArrayList<>();

        Arrays.stream(routes).forEach(route -> {
            var broute = new BRoute(broutes.size());
            broutes.add(broute);
            Arrays.stream(route).forEach(bstop ->{
                List<BRoute> stopRoutes;
                if (bstopMap.containsKey(bstop)) {
                    stopRoutes = bstopMap.get(bstop);
                } else {
                    stopRoutes = new ArrayList<>();
                    bstopMap.put(bstop, stopRoutes);
                }
                stopRoutes.add(broute);
            });
        });
        if (! bstopMap.containsKey(source) || ! bstopMap.containsKey(target)) {
            return -1;
        }

        broutes.stream().forEach(broute -> {
            Arrays.stream(routes[broute.index]).forEach(bstop -> {
                bstopMap.get(bstop).stream().forEach(bstopRoute -> {
                    if (bstopRoute != broute) {
                        broute.nexts.add(bstopRoute);
                    }
                });
            });
        });

        int ret = 1;
        Set<BRoute> targetRoute = new HashSet<>(bstopMap.get(target));
        Set<BRoute> visited = new HashSet<>();
        Queue<BRoute> queue = new ArrayDeque<>(bstopMap.get(source));
        int queueSize = queue.size();
        while (! queue.isEmpty()) {
            var broute = queue.poll();
            if (targetRoute.contains(broute)) {
                return ret;
            }
            visited.add(broute);
            for(var next: broute.nexts) {
                if (! visited.contains(next)) {
                    queue.add(next);
                }
            }
            if ((--queueSize) == 0) {
                queueSize = queue.size();
                ret++;
            }
        }
        return -1;
    }

    public int numBusesToDestination2(int[][] routes, int source, int target) {
        if(source==target)
            return 0;

        Set<Integer> s=new HashSet<>();
        Queue<Integer> queue=new LinkedList<>();
        queue.add(source);
        Map<Integer,Set<Integer>> mp=new HashMap<>();
        for(int i=0;i<routes.length;i++){
            for(int j=0;j<routes[i].length;j++){
                if(mp.containsKey(routes[i][j])){
                    mp.get(routes[i][j]).add(i);
                }else{
                    Set<Integer> s1=new HashSet<>(); s1.add(i);
                    mp.put(routes[i][j],s1);
                }
            }
        }
        if(!mp.containsKey(source)||!mp.containsKey(target))
            return -1;
        int ans[]=new int[routes.length];Arrays.fill(ans,-1);
        int temp=1;
        while(queue.size()>0){
            int n=queue.size();
            for(int i1=0;i1<n;i1++){
                int x=queue.poll();
                for(int i:mp.get(x)){
                    if(ans[i]==-1){
                        for(int j=0;j<routes[i].length;j++){
                            if(!s.contains(routes[i][j])){
                                s.add(routes[i][j]);
                                queue.add(routes[i][j]);
                            }
                        }
                        ans[i]=temp;
                    }
                    if(mp.get(target).contains(i))
                        return ans[i];
                }
            }
            temp++;
        }
        return -1;
    }
}
