import java.util.LinkedList;
import java.util.Iterator;

/** A binary search tree with arbitrary Objects as keys.
 *  @author
 */
public class BST {
    /** Root of tree. */
    private BSTNode root;

    /** A BST containing the elements in the sorted list LIST. */
    public BST(LinkedList list) {
        root = linkedListToTree(list.iterator(), list.size());
    }

    /**
     * turn an already sorted linked list into a balanced search tree.
     * @param n is the number of nodes in the list.
     * @param iter is the list we are transforming.
     */
    private BSTNode linkedListToTree(Iterator iter, int n) {
        if (n <= 0) {
            return null;
        }
        Object head = iter.next();
        BSTNode left = linkedListToTree(iter, n / 2);
        BSTNode root = new BSTNode();
        root.item = head;
        root.left = left;
        root.item = iter.next();
        root.right = linkedListToTree(iter, n - n / 2 - 1);
        return root;

    }

    /**
     * Prints the tree to the console.
     */
    private void print() {
        print(root, 0);
    }

    /** Print NODE and its subtrees, indented D levels.  */
    private void print(BSTNode node, int d) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < d; i++) {
            System.out.print("  ");
        }
        System.out.println(node.item);
        print(node.left, d + 1);
        print(node.right, d + 1);
    }

    /**
     * Node for BST.
     */
    static class BSTNode {

        /** Item. */
        protected Object item;

        /** Left child. */
        protected BSTNode left;

        /** Right child. */
        protected BSTNode right;
    }
}
