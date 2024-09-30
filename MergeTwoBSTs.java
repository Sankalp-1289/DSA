import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class MergeTwoBSTs {
    public List<Integer> merge(Node root1, Node root2) {
        List<Integer> res = new ArrayList<>();
        Stack<Node> st1 = new Stack<>();
        Stack<Node> st2 = new Stack<>();

        Node curr1 = root1, curr2 = root2;
        while (curr1 != null || !st1.isEmpty() || curr2 != null || !st2.isEmpty()) {
            while (curr1 != null) {
                st1.push(curr1);
                curr1 = curr1.left;
            }
            while (curr2 != null) {
                st2.push(curr2);
                curr2 = curr2.left;
            }

            if (st2.isEmpty() || (!st1.isEmpty() && st1.peek().data <= st2.peek().data)) {
                curr1 = st1.pop();
                res.add(curr1.data);
                curr1 = curr1.right;
            } else {
                curr2 = st2.pop();
                res.add(curr2.data);
                curr2 = curr2.right;
            }
        }
        return res;
    }

    private static Node treeBuilder(Scanner sc) {
        System.out.println("Enter the root element (-1 for null):");
        return treeBuilderHelper(sc);
    }

    private static Node treeBuilderHelper(Scanner sc) {
        int data = sc.nextInt();
        if (data == -1) {
            return null;
        }
        Node node = new Node(data);
        System.out.println("Enter the left child of " + data + ":");
        node.left = treeBuilderHelper(sc);
        System.out.println("Enter the right child of " + data + ":");
        node.right = treeBuilderHelper(sc);

        return node;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Building the first BST:");
        Node root1 = treeBuilder(sc);
        System.out.println("Building the second BST:");
        Node root2 = treeBuilder(sc);

        MergeTwoBSTs merger = new MergeTwoBSTs();
        List<Integer> mergedResult = merger.merge(root1, root2);

        System.out.println("Merged BST (in sorted order): " + mergedResult);
    }
}
