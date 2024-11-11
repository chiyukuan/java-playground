package breadth_first_search;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class BinarayTreeLevelOrderTraversalRaw {

    public static void main(String[] argv) {
        int[] srcArray = new int[]{3, 9, 20, -1, -1, 15, 7};

        var result = bwt(srcArray);
        for (var layer : result) {
            for (var elm : layer) {
                System.out.printf("%d ", elm);
            }
            System.out.println("");
        }

        BinarayTreeLevelOrderTraversalRaw bTree = new BinarayTreeLevelOrderTraversalRaw(srcArray);
        bTree.show();
    }

    public static List<List<Integer>> bwt(int[] array) {
        var result = new ArrayList<List<Integer>>();
        int start = 0;
        int end = 1;
        while (start < array.length) {
            result.add(tLayer(start, Math.min(end, array.length), array));
            var count = end - start;
            start = end;
            end += count * 2;
        }
        return result;
    }

    public static List<Integer> tLayer(int start, int end, int[] array) {
        var result = new ArrayList<Integer>();
        while (start < end) {
            if (array[start] != -1) {
                result.add(array[start]);
            }
            start++;
        }
        return result;
    }

    public class Node {
        public Node left;
        public Node right;
        public int value;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public Node root;

    public BinarayTreeLevelOrderTraversalRaw(int[] array) {
        this.root = this.build(0, array);
    }

    public Node build(int idx, int[] array) {
        if (idx >= array.length || array[idx] == -1) {
            return null;
        }
        return new Node(array[idx], build(idx * 2 + 1, array), build(idx * 2 + 2, array));
    }

    public void show() {
        if (this.root == null) {
            return;
        }

        var queue = new ArrayDeque<Node>();
        var queue2 = new ArrayDeque<Node>();
        queue.add(this.root);

        while (!queue.isEmpty()) {
            Node node = queue.pop();
            System.out.printf("%d ", node.value);
            if (node.left != null) {
                queue2.add(node.left);
            }
            if (node.right != null) {
                queue2.add(node.right);
            }
            if (queue.isEmpty()) {
                System.out.println("");
                queue = queue2;
                queue2 = new ArrayDeque<Node>();
            }
        }

    }

}
