package util;

/**
 * A node in a generic linked list
 * @param <T> type of objects in the list
 */

public class Node<T> {

    /**
     * data stored in the util.Node
     */
    private T data;

    /**
     * next util.Node in the list
     */
    private Node<T> next;

    /**
     * initialize util.Node
     * @param data data to be stored
     */
    Node(T data) {
        this.data = data;
    }

    /**
     * initialize util.Node with data and next util.Node
     * @param data data to be stored
     * @param next next util.Node
     */
    Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    /**
     * returns data stored
     * @return data stored
     */
    T getData() {
        return data;
    }

    /**
     * sets data
     * @param data data to be stored
     */
    void setData(T data) {
        this.data = data;
    }

    /**
     * returns next util.Node
     * @return next util.Node
     */
    Node<T> getNext() {
        return next;
    }

    /**
     * sets next util.Node
     * @param next next util.Node
     */
    void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * returns String of data stored
     * @return String of data stored
     */
    @Override
    public String toString() {
        return data.toString();
    }
}
