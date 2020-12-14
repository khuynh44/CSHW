import java.util.*;

/**
 * Your implementation of a BST.
 * @author Kevin Huynh
 * @version 1.0
 * @userid khuynh44 (i.e. gburdell3)
 * @GTID 903550619 (i.e. 900000000)
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize the BST with the data in the
     * Collection. The elements should be added to the BST in the order in
     * which they appear in the Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for-loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        for (T stuff: data) {
            add(stuff);
        }
    }

    /**
     * Adds the data to the tree.
     *
     * The data becomes a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        root = rAdd(root, data);
    }

    /**
     * The recursive helper method for the add method.
     * @param curr The BSTNode that is being recursed in.
     * @param data The data that is being added.
     * @return The new BST that is created after the rAddd method.
     */
    private BSTNode<T> rAdd(BSTNode<T> curr, T data) {
        if (curr == null) {
            size++;
            return new BSTNode(data);
        } else if (curr.getData().compareTo(data) > 0) {
            curr.setLeft(rAdd(curr.getLeft(), data));
        } else if (curr.getData().compareTo(data) < 0) {
            curr.setRight(rAdd(curr.getRight(), data));
        }
        return curr;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the predecessor to
     * replace the data. You MUST use recursion to find and remove the
     * predecessor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null) {
            throw new NoSuchElementException("Data cannot be null.");
        }
        BSTNode<T> dum = new BSTNode(data);
        root = rRemove(root, data, dum);
        return dum.getData();
    }

    /**
     * The recursive helper method for the remove method.
     * @param curr The BSTNode that the method will recurse in.
     * @param data The data that is being removed.
     * @param dum The node that holds the data being removed.
     * @return The dummy node that holds the removed data.
     * @throws if the data is not in the tree.
     */
    private BSTNode<T> rRemove(BSTNode<T> curr, T data, BSTNode dum) {
        if (curr == null) {
            throw new NoSuchElementException("Data is not in the tree");
        } else if (curr.getData().compareTo(data) > 0) {
            curr.setLeft(rRemove(curr.getLeft(), data, dum));
        } else if (curr.getData().compareTo(data) < 0) {
            curr.setRight(rRemove(curr.getRight(), data, dum));
        } else {
            dum.setData(curr.getData());
            size--;
            if (curr.getLeft() == null && curr.getRight() == null) {
                return null;
            } else if (curr.getRight() == null) {
                return curr.getLeft();
            } else if (curr.getLeft() == null) {
                return curr.getRight();
            } else {
                BSTNode<T> dum2 = new BSTNode(data);
                curr.setLeft(removePredecessor(curr.getLeft(), dum2));
                curr.setData(dum2.getData());
            }
        }
        return curr;
    }

    /**
     * The recursive helper method for the remove method.
     * If the node being removed contains 2 children, this method will execute.
     * It will find the predecessor of the node being removed.
     * @param curr The node being removed and recursed.
     * @param dum2 The dummy node that will hold the predecessor data.
     * @return The newly changed left side of the removed node.
     */
    private BSTNode<T> removePredecessor(BSTNode<T> curr, BSTNode<T> dum2) {
        if (curr.getRight() == null) {
            dum2.setData(curr.getData());
            return curr.getLeft();
        } else {
            curr.setRight(removePredecessor(curr.getRight(), dum2));
            return curr;
        }
    }

    /**
     * Returns the data from the tree matching the given parameter.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot by null");
        }
        return rGet(root, data);
    }

    /**
     * The recursive helper get method that recurses in
     * the tree to find the data.
     * @param curr The BSTNode the method will recurse through.
     * @param data The data the method is trying to find.
     * @return The data that the method finds.
     */
    private T rGet(BSTNode<T> curr, T data) {
        if (curr == null) {
            throw new NoSuchElementException("Data is not in the tree");
        }
        int value = curr.getData().compareTo(data);
        if (value > 0) {
            return rGet(curr.getLeft(), data);
        }
        if (value < 0) {
            return rGet(curr.getRight(), data);
        }
        return curr.getData();
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        try {
            rGet(root, data);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Generate a pre-order traversal of the tree.
     *
     * Must be O(n).
     *
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        List<T> list = new ArrayList<>();
        rPreorder(root, list);
        return list;
    }

    /**
     * The recursive preorder helper method that records data.
     * before recursing through the tree.
     * @param curr The BSTNode being recursed in.
     * @param list The list that is being added to.
     */
    private void rPreorder(BSTNode<T> curr, List<T> list) {
        if (curr != null) {
            list.add(curr.getData());
            rPreorder(curr.getLeft(), list);
            rPreorder(curr.getRight(), list);
        }
    }

    /**
     * Generate an in-order traversal of the tree.
     *
     * Must be O(n).
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        List<T> list = new ArrayList<>();
        rInorder(root, list);
        return list;
    }

    /**
     * The recursive inorder helper method that record data inbetween
     * calling the recursions.
     * @param curr The BSTNode being recursed in.
     * @param list The list being added to.
     */
    private void rInorder(BSTNode<T> curr, List<T> list) {
        if (curr != null) {
            rInorder(curr.getLeft(), list);
            list.add(curr.getData());
            rInorder(curr.getRight(), list);
        }
    }

    /**
     * Generate a post-order traversal of the tree.
     *
     * Must be O(n).
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        List<T> list = new ArrayList<>();
        rPostorder(root, list);
        return list;
    }

    /**
     * The recursive postorder helper method that records data
     * after calling the recursion.
     * @param curr The BSTNode being recursed in.
     * @param list The list being added to.
     */
    private void rPostorder(BSTNode<T> curr, List<T> list) {
        if (curr != null) {
            rPostorder(curr.getLeft(), list);
            rPostorder(curr.getRight(), list);
            list.add(curr.getData());
        }
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * This does not need to be done recursively.
     *
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use. You may import java.util.Queue as well as an implmenting
     * class.
     *
     * Must be O(n).
     *
     * @return the level order traversal of the tree
     */
    public List<T> levelorder() {
        Queue<BSTNode<T>> q = new LinkedList<>();
        List<T> list = new ArrayList<>();
        q.add(root);
        while (!q.isEmpty()) {
            BSTNode<T> curr = q.remove();
            if (curr != null) {
                list.add(curr.getData());
                q.add(curr.getLeft());
                q.add(curr.getRight());
            }
        }
        return list;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     *
     * Must be O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return rHeight(root);
    }

    /**
     * The recursive height helper method that recurses through
     * the tree while finding the max height of each node.
     * @param curr The BSTNode being recursed in.
     * @return The height of the tree.
     */
    private int rHeight(BSTNode<T> curr) {
        if (curr == null) {
            return -1;
        }
        return Math.max(rHeight(curr.getLeft()), rHeight(curr.getRight())) + 1;
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Finds the path between two elements in the tree, specifically the path
     * from data1 to data2, inclusive of both.
     *
     * To do this, you must first find the deepest common ancestor of both data
     * and add it to the list. Then traverse to data1 while adding its ancestors
     * to the front of the list. Finally, traverse to data2 while adding its
     * ancestors to the back of the list. Please note that there is no
     * relationship between the data parameters in that they may not belong
     * to the same branch. You will most likely have to split off and
     * traverse the tree for each piece of data.
     * *
     * You may only use 1 list instance to complete this method. Think about
     * what type of list to use since you will have to add to the front and
     * back of the list.
     *
     * This method only needs to traverse to the deepest common ancestor once.
     * From that node, go to each data in one traversal each. Failure to do
     * so will result in a penalty.
     *
     * If both data1 and data2 are equal and in the tree, the list should be
     * of size 1 and contain the element from the tree equal to data1 and data2.
     *
     * Hint: How can we use the order property of the BST to locate the deepest
     * common ancestor?
     *
     * Ex:
     * Given the following BST composed of Integers
     *              50
     *          /        \
     *        25         75
     *      /    \
     *     12    37
     *    /  \    \
     *   11   15   40
     *  /
     * 10
     * findPathBetween(10, 40) should return the list [10, 11, 12, 25, 37, 40]
     * findPathBetween(50, 37) should return the list [50, 25, 37]
     * findPathBetween(75, 75) should return the list [75]
     *
     * Must be O(log n) for a balanced tree and O(n) for worst case.
     *
     * @param data1 the data to start the path from
     * @param data2 the data to end the path on
     * @return the unique path between the two elements
     * @throws java.lang.IllegalArgumentException if either data1 or data2 is
     *                                            null
     * @throws java.util.NoSuchElementException   if data1 or data2 is not in
     *                                            the tree
     */
    public List<T> findPathBetween(T data1, T data2) {
        if (data1 == null || data2 == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        List<T> list = new ArrayList<>();
        rFindPathBetween(root, list, data1, data2);
        return list;
    }

    /**
     * The recursive findPathBetween helper method that finds the direct
     * ancestor between two nodes and adds nodes creating a path between.
     * the two nodes in a list.
     * @param curr The BSTNode being recursed in the find ancestor.
     * @param list The list being added to.
     * @param data1 The first data the node contains.
     * @param data2 The second data the node contains.
     */
    private void rFindPathBetween(BSTNode<T> curr, List<T> list, T data1,
                                  T data2) {
        int value1 = curr.getData().compareTo(data1);
        int value2 = curr.getData().compareTo(data2);
        if (value1 > 0 && value2 > 0) {
            rFindPathBetween(curr.getLeft(), list, data1, data2);
        } else if (value1 < 0 && value2 < 0) {
            rFindPathBetween(curr.getRight(), list, data1, data2);
        } else {
            list.add(curr.getData());
            BSTNode<T> temp1 = curr;
            BSTNode<T> temp2 = curr;
            while (value1 != 0 || value2 != 0) {
                try {
                    if (value1 > 0) {
                        list.add(0, temp1.getLeft().getData());
                        temp1 = temp1.getLeft();
                    }
                    if (value1 < 0) {
                        list.add(0, temp1.getRight().getData());
                        temp1 = temp1.getRight();
                    }
                    value1 = temp1.getData().compareTo(data1);
                    if (value2 > 0) {
                        list.add(temp2.getLeft().getData());
                        temp2 = temp2.getLeft();
                    }
                    if (value2 < 0) {
                        list.add(temp2.getRight().getData());
                        temp2 = temp2.getRight();
                    }
                    value2 = temp2.getData().compareTo(data2);
                } catch (NullPointerException e) {
                    throw new NoSuchElementException("Data1 or data2 is not"
                            + " in the tree");
                }
            }
        }
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
