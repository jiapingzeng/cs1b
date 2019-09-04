package cellularData;

/**
 * A node in a generic linked list
 * @param <T> type of objects in the list
 */

public class Node<T> {

    /**
     * data stored in the Node
     */
    private T data;

    /**
     * next Node in the list
     */
    private Node<T> next;

    /**
     * initialize Node
     * @param data data to be stored
     */
    Node(T data) {
        this.data = data;
    }

    /**
     * initialize Node with data and next Node
     * @param data data to be stored
     * @param next next Node
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
     * returns next Node
     * @return next Node
     */
    Node<T> getNext() {
        return next;
    }

    /**
     * sets next Node
     * @param next next Node
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
