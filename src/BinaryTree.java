import static java.lang.Integer.parseInt;

public class BinaryTree {
    Node root;

    private class Node {
        int value;
        Node left;
        Node right;
        boolean isRed;

        Node(int value, boolean isRed){
            this.value = value;
            this.isRed = isRed;
        }
    }

    public Node turnLeft(Node node) {
        System.out.println("turn left");
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;

        newRoot.isRed = node.isRed;
        node.isRed = true;

        return newRoot;
    }

    public Node turnRight(Node node) {
        System.out.println("turn right");
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;

        newRoot.isRed = node.isRed;
        node.isRed = false;

        return newRoot;
    }

    public Node swap(Node node) {
        System.out.println("swap");
        node.isRed = true;
        node.left.isRed = false;
        node.right.isRed = false;
        return node;
    }

    private boolean isRed(Node node) {
        // Null nodes are black
        if (node == null) return false;
        // Check if node is red
        return node.isRed;
    }

    public void addNode(int value) {
        root = addNode(root, value);
    }

    private Node addNode(Node node, int value) {
        if (node == null) {
            return new Node(value, true);
        }

        if (value < node.value) {
            node.left = addNode(node.left, value);
        } else if (value > node.value) {
            node.right = addNode(node.right, value);
        } else {
            return null;
        }

        node = rebalance(node);
        return node;
    }


    public boolean find(int value) {
        if (root != null) {
            Node cur = root;
            while (cur != null) {
                if (cur.value == value)
                    return true;
                if (value < cur.value) {
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
        }
        return false;
    }

    public void printTree() {
        printTree(root);
        System.out.println();
    }

    private void printTree(Node node) {
        if (node != null) {
            printTree(node.left);
            System.out.print(node.value + " ");
            printTree(node.right);
        }
    }
    public Node rebalance(Node node) {
        if (isRed(node.right) && !isRed(node.left)) {
            return turnLeft(node);
        }

        if (isRed(node.left) && isRed(node.left.left)) {
            return turnRight(node);
        }

        if (isRed(node.left) && isRed(node.right)) {
            return swap(node);
        }
        return node;
    }

}




