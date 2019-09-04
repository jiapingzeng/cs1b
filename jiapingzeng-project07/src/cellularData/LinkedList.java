package cellularData;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A list of Nodes
 * @param <T> type of objects in the list
 */

public class LinkedList<T> implements Iterable<T> {

    /**
     * first Node of the list
     */
    private Node<T> head;

    /**
     * size of the list
     */
    private int size;

    /**
     * Initialize list
     */
    LinkedList() {
        size = 0;
    }

    /**
     * Finds and returns object at specific index
     * @param index index of object
     * @return Object found at index
     * @throws IndexOutOfBoundsException if index is lower than 0 or higher than list size
     */
    T getIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        ListIterator li = new ListIterator();
        while (li.hasNext() && index >= 0) {
            T next = li.next();
            if (index == 0 && next != null) return next;
            index--;
        }
        return null;
    }

    /**
     * Checks if a specified object is in the list
     * @param data object to look for
     * @return object found
     */
    T contains(T data) {
        ListIterator li = new ListIterator();
        while (li.hasNext()) {
            T next = li.next();
            if (next.equals(data)) return next;
        }
        return null;
    }

    /**
     * Adds a new object to the list
     * @param data object to be added
     */
    void add(T data) {
        Node<T> node = new Node<>(data);
        if (size == 0) {
            head = node;
        } else {
            Node<T> current = head;
            for (int i = 0; i < size - 1; i++) {
                current = current.getNext();
            }
            current.setNext(node);
        }
        size++;
    }

    /**
     * returns list iterator
     * @return list iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    /**
     * returns size of the list
     * @return size of the list
     */
    int size() {
        return size;
    }

    /**
     * String of objects in the list
     * @return string of object information
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListIterator li = new ListIterator();
        while (li.hasNext()) {
            sb.append(li.next());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Iterator for LinkedList
     */
    class ListIterator implements Iterator<T> {

        /**
         * current Node
         */
        Node<T> current;

        /**
         * initializes iterator
         */
        ListIterator() {
            current = head;
        }

        /**
         * checks if current node exists
         * @return true if current node exists, false if not
         */
        public boolean hasNext() {
            return !(current == null);
        }

        /**
         * returns current node and move to next node
         * @return current node
         * @throws NoSuchElementException if current node does not exist
         */
        public T next() throws NoSuchElementException {
            if (current == null) throw new NoSuchElementException();
            T data = current.getData();
            current = current.getNext();
            return data;
        }
    }
}
