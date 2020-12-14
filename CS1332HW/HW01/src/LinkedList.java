/**
 * Implements both List and Queue for to hold data types.
 * @author Kevin Huynh
 * @version 1.0
 * @param <T>
 */
public class LinkedList<T> implements List<T>, Queue<T> {
    /** The data in front of the list. */
    private Node<T> head;

    /** The data in the back of the list. */
    private Node<T> tail;

    /**
     * Constructor setting both fields to null.
     */
    public LinkedList() {
        head = null;
        tail = null;
    }

    /**
     * Adds the given data to the list at the specified index.
     * For example, `add(0, "Hello")` for a List
     * of generic type String would add `"Hello"`
     * to the beginning of the list.
     * @param index The index at which data will be added
     * @param data  the data to add
     */
    @Override
    public void add(int index, T data) {
        if (index < 0 || index > size() || data.equals(null)) {
            throw new IllegalArgumentException();
        }
        int count = 0;
        Node<T> plus = new Node<>(data);
        if (size() == 0) {
            head = plus;
            tail = plus;
        } else {
            Node<T> temp = head;
            Node<T> temp2;
            index -= 1;
            if (index < 0) {
                plus.setNext(head);
                head = plus;
            } else {
                while (count < index) {
                    count++;
                    temp = temp.getNext();
                }
                try {
                    temp2 = temp.getNext();
                    temp.setNext(plus);
                    plus.setNext(temp2);
                    temp2.getData();
                } catch (NullPointerException e) {
                    temp.setNext(plus);
                    plus.setNext(null);
                    tail = plus;
                }
            }
        }
    }

    /**
     * Removes the data at the given index from the list, and then returns it.
     * @param index the index to remove from
     * @return the removed piece of data
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IllegalArgumentException();
        }
        T data = get(index);
        int count = 0;
        if (index == 0) {
            head = head.getNext();
        } else {
            index--;
            Node<T> temp = head;
            Node<T> temp2 = head;
            while (count < index) {
                count++;
                temp = temp.getNext();
            }
            index++;
            if (index == size() - 1) {
                temp.setNext(null);
            } else {
                index++;
                count = 0;
                while (count < index) {
                    count++;
                    temp2 = temp2.getNext();
                }
                temp.setNext(temp2);
            }
        }
        return data;
    }

    /**
     * Returns the data at the specified index in the list.
     * @param index the index to return from
     * @return the data at the specified index
     */
    @Override
    public T get(int index) {
        int count = 0;
        Node<T> temp = head;
        while (count < index) {
            count++;
            temp = temp.getNext();
        }
        return temp.getData();
    }

    /**
     * returns **a new List object** containing
     * the first n elements in the current List*.
     * For example, if our list was [1, 2, 3, 4, 5, 6], subList(2) should
     * return a new list containing [1, 2], and subList(3)
     * should return a new list containing [1, 2, 3].
     * @param  n The amount of elements to take
     * @return a new List object containing the first
     * n elements in the current list, as described above
     */
    @Override
    public List<T> subList(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        if (n > size()) {
            n = size();
        }
        List<T> sub = new LinkedList<T>();
        Node<T> temp = head;
        int count = 0;
        while (count < n) {
            sub.add(count, temp.getData());
            temp = temp.getNext();
            count++;
        }
        return sub;
    }

    /**
     * Returns the number of items in this list.
     * @return the number of items in this list
     */
    @Override
    public int size() {
        if (head == null) {
            return 0;
        }
        int count = 1;
        Node<T> temp = head;
            while (!temp.equals(tail)) {
                temp = temp.getNext();
                count++;
            }
        return count;
    }

    /**
     * adds data to the Queue
     * Think about where in the list the data should be added in
     * order to make the ordering compatible.
     * @param data the data to add to the queue.
     */
    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        head = new Node<T>(data, head);
        if (tail == null) {
            tail = head;
        }
    }

    /**
     * removes the oldest piece of data from our Queue; that is,
     * the first thing we added, or the item that has been waiting the longest
     * to be removed.  Think about from where in the list we should remove to
     * make this work.
     * @return the dequeued item
     */
    @Override
    public T dequeue() {
        Node<T> temp = head;
        if (head == null) {
            throw new IllegalArgumentException();
        }
        T print = tail.getData();
        if (temp.equals(tail)) {
            return print;
        }
        while (!temp.getNext().equals(tail)) {
            temp = temp.getNext();
        }
        tail = temp;
        return print;
    }
}
