package tree;

public class BTreeTraversal {

    public static void main(String[] args) {
        Node root = new Node( 1,
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
        traveersal.inorder();
        traveersal.preorder();
        traveersal.postorder();
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
}
