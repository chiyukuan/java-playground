package tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class BTreeTraversal {

    public static void main(String[] args) {
        Node root = new Node(1,
                new Node(2,
                        new Node(4, null, null),
                        new Node(5, null, null)
                ),
                new Node(3,
                        new Node(6, null, null),
                        new Node(7, null, null)
                )
        );
        var traveersal = new BTreeTraversal(root);
        // deep first traversal
        traveersal.inorder();
        traveersal.preorder();
        traveersal.postorder();
        // breath first traversal
        traveersal.bft();
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;

    public BTreeTraversal(Node root) {
        this.root = root;
    }

    public void inorder() {
        this.inorder(root);
        System.out.println("");
    }

    private void inorder(Node node) {
        if (node == null) {
            return;
        }
        this.inorder(node.left);
        System.out.printf("%d ", node.value);
        this.inorder(node.right);
    }

    public void preorder() {
        this.preorder(root);
        System.out.println("");
    }

    private void preorder(Node node) {
        if (node == null) {
            return;
        }
        System.out.printf("%d ", node.value);
        this.preorder(node.left);
        this.preorder(node.right);
    }

    public void postorder() {
        this.postorder(root);
        System.out.println("");
    }

    private void postorder(Node node) {
        if (node == null) {
            return;
        }
        this.postorder(node.left);
        this.postorder(node.right);
        System.out.printf("%d ", node.value);
    }

    public void bft() {
        var queue = new ArrayDeque<Node>();
        var queue2 = new ArrayDeque<Node>();
        queue.add(this.root);
        while (!queue.isEmpty()) {
            var node = queue.pop();
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
