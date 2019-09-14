import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implementation of a BST based String Set.
 * @author
 */
public class BSTStringSet implements StringSet {
    /** Creates a new empty set. */
    public BSTStringSet() {
        root = null;
    }

    @Override
    public void put(String s) {
        root = put(s, root);
    }
    public Node put (String s, Node root) {
        if (root == null) {
            root = new Node(s);
        }
        int temp = s.compareTo(root.s);
        if (temp < 0) {
            root.left = put(s, root.left);
        }
        if (temp > 0) {
            root.right = put(s, root.right);
        }
        return root;
    }

    @Override
    public boolean contains(String s) {
        return contains(s, root);
    }
    public boolean contains(String s, Node root) {
        if (root == null) {
            return false;
        }
        int temp = s.compareTo(root.s);
        if (temp < 0) {
            contains(s, root.left);
        }
        if (temp > 0) {
            contains(s, root.right);
        }
        return true;
    }

    @Override
    public List<String> asList() {
        ArrayList<String> aslist = new ArrayList<>();
        return asList(root, aslist);
    }
    public List<String> asList(Node root, ArrayList<String> aslist) {
        if (root == null) {
            return aslist;
        }
        else if (root != null && root.left == null && root.right == null) {
            aslist.add(root.s);
            return aslist;
        }
        else if (root != null && root.left == null && root.right != null) {
            aslist.add(root.s);
            asList(root.right, aslist);
            return aslist;
        }
        else {
            asList(root.left, aslist);
            aslist.add(root.s);
            asList(root.right, aslist);
            return aslist;
        }
    }

    /** Represents a single Node of the tree. */
    private static class Node {
        /** String stored in this Node. */
        private String s;
        /** Left child of this Node. */
        private Node left;
        /** Right child of this Node. */
        private Node right;

        /** Creates a Node containing SP. */
        Node(String sp) {
            s = sp;
        }
    }

    /** Root node of the tree. */
    private Node root;
}
