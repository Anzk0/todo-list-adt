/**
 * Node - one "box" in the doubly linked list.
 *
 * Each node stores one piece of data plus two links:
 *   next -> the node after this one  (null if this is the last node)
 *   prev -> the node before this one (null if this is the first node)
 *
 * The prev link is what makes the list doubly linked, so it can
 * be walked in both directions.
 *
 * @param <T> the type of data stored in the node
 */
public class Node<T> {

    private T data;
    private Node<T> next;
    private Node<T> prev;

    /**
     * Creates a new node holding the given data.
     * It is not linked to anything until the list connects it.
     *
     * @param data the item to store in this node
     */
    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    /** @return the item stored in this node */
    public T getData() {
        return data;
    }

    /** @param data the new item to store in this node */
    public void setData(T data) {
        this.data = data;
    }

    /** @return the node after this one, or null if this is the tail */
    public Node<T> getNext() {
        return next;
    }

    /** @param next the node that should come after this one */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    /** @return the node before this one, or null if this is the head */
    public Node<T> getPrev() {
        return prev;
    }

    /** @param prev the node that should come before this one */
    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }
}
