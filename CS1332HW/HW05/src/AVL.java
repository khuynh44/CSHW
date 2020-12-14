import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL.
 *
 * @author Kevin Huynh
 * @version 1.0
 * @userid khuynh44 (i.e. gburdell3)
 * @GTID 903550619 (i.e. 900000000)
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class AVL<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private AVLNode<T> root;
    private int size;

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize the AVL with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * @param data the data to add to the tree
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        for (T stuff: data) {
            add(stuff);
        }
    }

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary.
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
     * Adds elements to the tree while also updating their
     * height and balancing factor and rotating as needed.
     * @param curr The BSTNode that is being recursed in.
     * @param data The data that is being added.
     * @return The new BST that is created after the rAddd method.
     */
    private AVLNode<T> rAdd(AVLNode<T> curr, T data) {
        if (curr == null) {
            size++;
            return new AVLNode<>(data);
        } else if (curr.getData().compareTo(data) > 0) {
            curr.setLeft(rAdd(curr.getLeft(), data));
        } else if (curr.getData().compareTo(data) < 0) {
            curr.setRight(rAdd(curr.getRight(), data));
        }
        if (curr.getRight() == null && curr.getLeft() == null) {
            curr.setHeight(0);
            curr.setBalanceFactor(0);
        } else if (curr.getRight() == null) {
            curr.setHeight(curr.getLeft().getHeight() + 1);
            curr.setBalanceFactor(curr.getHeight());
        } else if (curr.getLeft() == null) {
            curr.setHeight(curr.getRight().getHeight() + 1);
            curr.setBalanceFactor(-curr.getHeight());
        } else {
            curr.setHeight(Math.max(curr.getLeft().getHeight(),
                    curr.getRight().getHeight()) + 1);
            curr.setBalanceFactor(curr.getLeft().getHeight()
                    - curr.getRight().getHeight());
        }
        if (curr.getBalanceFactor() < -1) {
            if (curr.getRight().getBalanceFactor() > 0) {
                curr.setRight(rightRotation(curr.getRight()));
            }
            return leftRotation(curr);
        } else if (curr.getBalanceFactor() > 1) {
            if (curr.getLeft().getBalanceFactor() < 0) {
                curr.setLeft(leftRotation(curr.getLeft()));
            }
            return rightRotation(curr);
        }
        return curr;
    }

    /**
     * Does a left rotation on a subpart of the tree and
     * adjust height and balancing factor accordingly.
     * @param curr The current root of the subpart of the tree.
     * @return The new root of the subpart.
     */
    private AVLNode<T> leftRotation(AVLNode<T> curr) {
        AVLNode<T> curr2 = curr.getRight();
        curr.setRight(curr2.getLeft());
        curr2.setLeft(curr);
        if (curr.getRight() == null && curr.getLeft() == null) {
            curr.setHeight(0);
            curr.setBalanceFactor(0);
        } else if (curr.getRight() == null) {
            curr.setHeight(curr.getLeft().getHeight() + 1);
            curr.setBalanceFactor(curr.getHeight());
        } else if (curr.getLeft() == null) {
            curr.setHeight(curr.getRight().getHeight() + 1);
            curr.setBalanceFactor(-curr.getHeight());
        } else {
            curr.setHeight(Math.max(curr.getLeft().getHeight(),
                    curr.getRight().getHeight()) + 1);
            curr.setBalanceFactor(curr.getLeft().getHeight()
                    - curr.getRight().getHeight());
        }
        if (curr2.getRight() == null) {
            curr2.setHeight(curr2.getLeft().getHeight() + 1);
            curr2.setBalanceFactor(-curr2.getHeight());
        } else {
            curr2.setHeight(Math.max(curr2.getLeft().getHeight(),
                    curr2.getRight().getHeight()) + 1);
            curr2.setBalanceFactor(curr2.getLeft().getHeight()
                    - curr2.getRight().getHeight());
        }
        return curr2;
    }

    /**
     * Does a right rotation on a subpart of the tree and
     * adjust height and balancing factor accordingly.
     * @param curr The current root of the subpart of the tree.
     * @return The new root of the subpart.
     */
    private AVLNode<T> rightRotation(AVLNode<T> curr) {
        AVLNode<T> curr2 = curr.getLeft();
        curr.setLeft(curr2.getRight());
        curr2.setRight(curr);
        if (curr.getRight() == null && curr.getLeft() == null) {
            curr.setHeight(0);
            curr.setBalanceFactor(0);
        } else if (curr.getRight() == null) {
            curr.setHeight(curr.getLeft().getHeight() + 1);
            curr.setBalanceFactor(curr.getHeight());
        } else if (curr.getLeft() == null) {
            curr.setHeight(curr.getRight().getHeight() + 1);
            curr.setBalanceFactor(-curr.getHeight());
        } else {
            curr.setHeight(Math.max(curr.getLeft().getHeight(),
                    curr.getRight().getHeight()) + 1);
            curr.setBalanceFactor(curr.getLeft().getHeight()
                    - curr.getRight().getHeight());
        }
        if (curr2.getLeft() == null) {
            curr2.setHeight(curr2.getRight().getHeight() + 1);
            curr2.setBalanceFactor(-curr2.getHeight());
        } else {
            curr2.setHeight(Math.max(curr2.getLeft().getHeight(),
                    curr2.getRight().getHeight()) + 1);
            curr2.setBalanceFactor(curr2.getLeft().getHeight()
                    - curr2.getRight().getHeight());
        }
        return curr2;
    }

    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data, NOT predecessor. As a reminder, rotations can occur
     * after removing the successor node. Do NOT use the provided public
     * predecessor method to remove a 2-child node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException   if the data is not found
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null.");
        }
        AVLNode<T> dum = new AVLNode<>(data);
        root = rRemove(root, data, dum);
        return dum.getData();
    }

    /**
     * The recursive helper method for the remove method.
     * Removes element and adjust height and balancing factor
     * accordingly while performing rotations on imbalances.
     * @param curr The BSTNode that the method will recurse in.
     * @param data The data that is being removed.
     * @param dum The node that holds the data being removed.
     * @return The dummy node that holds the removed data.
     * @throws java.util.NoSuchElementException if the data is not found
     */
    private AVLNode<T> rRemove(AVLNode<T> curr, T data, AVLNode<T> dum) {
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
                AVLNode<T> dum2 = new AVLNode<>(data);
                curr.setRight(removeSuccessor(curr.getRight(), dum2));
                curr.setData(dum2.getData());
            }
        }
        if (curr.getRight() == null && curr.getLeft() == null) {
            curr.setHeight(0);
            curr.setBalanceFactor(0);
        } else if (curr.getRight() == null) {
            curr.setHeight(curr.getLeft().getHeight() + 1);
            curr.setBalanceFactor(curr.getHeight());
        } else if (curr.getLeft() == null) {
            curr.setHeight(curr.getRight().getHeight() + 1);
            curr.setBalanceFactor(-curr.getHeight());
        } else {
            curr.setHeight(Math.max(curr.getLeft().getHeight(),
                    curr.getRight().getHeight()) + 1);
            curr.setBalanceFactor(curr.getLeft().getHeight()
                    - curr.getRight().getHeight());
        }
        if (curr.getBalanceFactor() < -1) {
            if (curr.getRight().getBalanceFactor() > 0) {
                curr.setRight(rightRotation(curr.getRight()));
                return leftRotation(curr);
            }
            return leftRotation(curr);
        } else if (curr.getBalanceFactor() > 1) {
            if (curr.getLeft().getBalanceFactor() < 0) {
                curr.setLeft(leftRotation(curr.getLeft()));
                return rightRotation(curr);
            }
            return rightRotation(curr);
        }
        return curr;
    }

    /**
     * The recursive helper method for the remove method.
     * If the node being removed contains 2 children, this method will execute.
     * It will find the successor of the node being removed.
     * @param curr The node being removed and recursed.
     * @param dum2 The dummy node that will hold the successor data.
     * @return The newly changed left side of the removed node.
     */
    private AVLNode<T> removeSuccessor(AVLNode<T> curr, AVLNode<T> dum2) {
        if (curr.getLeft() == null) {
            dum2.setData(curr.getData());
            return curr.getRight();
        } else {
            curr.setLeft(removeSuccessor(curr.getLeft(), dum2));
            if (curr.getRight() == null && curr.getLeft() == null) {
                curr.setHeight(0);
                curr.setBalanceFactor(0);
            } else if (curr.getRight() == null) {
                curr.setHeight(curr.getLeft().getHeight() + 1);
                curr.setBalanceFactor(curr.getHeight());
            } else if (curr.getLeft() == null) {
                curr.setHeight(curr.getRight().getHeight() + 1);
                curr.setBalanceFactor(-curr.getHeight());
            } else {
                curr.setHeight(Math.max(curr.getLeft().getHeight(),
                        curr.getRight().getHeight()) + 1);
                curr.setBalanceFactor(curr.getLeft().getHeight()
                        - curr.getRight().getHeight());
            }
            if (curr.getBalanceFactor() < -1) {
                if (curr.getRight().getBalanceFactor() > 0) {
                    curr.setRight(rightRotation(curr.getRight()));
                    return leftRotation(curr);
                }
                return leftRotation(curr);
            } else if (curr.getBalanceFactor() > 1) {
                if (curr.getLeft().getBalanceFactor() < 0) {
                    curr.setLeft(leftRotation(curr.getLeft()));
                    return rightRotation(curr);
                }
                return rightRotation(curr);
            }
            return curr;
        }
    }

    /**
     * Returns the element from the tree matching the given parameter.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * @param data the data to search for in the tree
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
    private T rGet(AVLNode<T> curr, T data) {
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
     * @param data the data to search for in the tree
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
     * Returns the height of the root of the tree. Do NOT compute the height
     * recursively. This method should be O(1).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (root == null) {
            return -1;
        }
        return root.getHeight();
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * In your BST homework, you worked with the concept of the predecessor, the
     * largest data that is smaller than the current data. However, you only
     * saw it in the context of the 2-child remove case.
     *
     * This method should retrieve (but not remove) the predecessor of the data
     * passed in. There are 3 cases to consider:
     * 1: The left subtree is non-empty. In this case, the predecessor is the
     * rightmost node of the left subtree.
     * 2: The left subtree is empty. In this case, the predecessor is
     * the deepest
     * ancestor of the node containing data whose right child is also
     * an ancestor of data.
     * 3: If the data passed in is the minimum data in the tree, return null.
     *
     * This should NOT be used in the remove method.
     *
     * Ex:
     * Given the following AVL composed of Integers
     *     76
     *   /    \
     * 34      90
     *  \    /
     *  40  81
     * predecessor(76) should return 40
     * predecessor(81) should return 76
     *
     * @param data the data to find the predecessor of
     * @return the predecessor of data. If there is no smaller data than the
     * one given, return null.
     * @throws java.lang.IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T predecessor(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        AVLNode<T> temp = rPredecessor(root, null, data);
        if (temp == null) {
            return null;
        }
        return temp.getData();
    }

    /**
     * The recursive helper method to find the predecessor of an
     * element.
     * @param curr1 The current node to search for the data
     * @param curr2 The node that will hold the predecessor node.
     * @param data The data of the curr1 node.
     * @return The predecessor node or null if there is no predecessor.
     */
    private AVLNode<T> rPredecessor(AVLNode<T> curr1, AVLNode<T> curr2,
                                    T data) {
        if (curr1 == null) {
            throw new NoSuchElementException("Data is not in the tree");
        }
        if (curr1.getData().compareTo(data) > 0) {
            return rPredecessor(curr1.getLeft(), curr2, data);
        } else if (curr1.getData().compareTo(data) < 0) {
            curr2 = curr1;
            return rPredecessor(curr1.getRight(), curr2, data);
        } else if (curr1.getLeft() != null) {
            curr2 = curr1.getLeft();
            while (curr2.getRight() != null) {
                curr2 = curr2.getRight();
            }
        }
        return curr2;
    }

    /**
     * Finds and retrieves the k-smallest elements from the AVL in sorted order,
     * least to greatest.
     *
     * In most cases, this method will not need to traverse the entire tree to
     * function properly, so you should only traverse the branches of the tree
     * necessary to get the data and only do so once. Failure to do so will
     * result in an efficiency penalty.
     *
     * Ex:
     * Given the following AVL composed of Integers
     *              50
     *            /    \
     *         25      75
     *        /  \     / \
     *      13   37  70  80
     *    /  \    \      \
     *   12  15    40    85
     *  /
     * 10
     * kSmallest(0) should return the list []
     * kSmallest(5) should return the list [10, 12, 13, 15, 25].
     * kSmallest(3) should return the list [10, 12, 13].
     *
     * @param k the number of smallest elements to return
     * @return sorted list consisting of the k smallest elements
     * @throws java.lang.IllegalArgumentException if k < 0 or k > n, the number
     *                                            of data in the AVL
     */
    public List<T> kSmallest(int k) {
        List<T> list = new ArrayList<>();
        rKsmallest(list, root, k);
        return list;
    }

    /**
     * The helper method to find the smallest elements in the tree.
     * @param list The list that will hold the smallest elements.
     * @param curr The current node to recurse through.
     * @param k The number of smallest elements.
     */
    private void rKsmallest(List<T> list, AVLNode<T> curr, int k) {
        if (curr != null) {
            rKsmallest(list, curr.getLeft(), k);
            if (list.size() < k) {
                list.add(curr.getData());
                if (curr.getRight() != null) {
                    rKsmallest(list, curr.getRight(), k);
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
    public AVLNode<T> getRoot() {
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
