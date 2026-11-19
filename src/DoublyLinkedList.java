/**
 * DoublyLinkedList - my implementation of the ListADT interface.
 *
 * The list keeps track of:
 *   head -> the first node
 *   tail -> the last node
 *   size -> how many nodes there are
 *
 *   null <- [A] <-> [B] <-> [C] -> null
 *           head            tail
 *
 * Storing the tail means adding to the end is O(1) (instant),
 * and storing size means counting is O(1) instead of walking the list.
 *
 * It uses no java.util classes; everything is built from Node objects.
 *
 * @param <T> the type of element stored in the list
 */
public class DoublyLinkedList<T> implements ListADT<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Creates an empty list.
     */
    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // ADD METHODS

    /**
     * Adds an item to the end of the list. O(1) because the tail is stored.
     */
    @Override
    public void add(T item) {
        checkNotNull(item);
        Node<T> newNode = new Node<>(item);

        if (isEmpty()) {
            // first item: it is both the head and the tail
            head = newNode;
            tail = newNode;
        } else {
            // link the new node after the current tail
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
        size++;
    }

    /**
     * Adds an item to the front of the list. O(1).
     */
    @Override
    public void addFirst(T item) {
        checkNotNull(item);
        Node<T> newNode = new Node<>(item);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            // link the new node before the current head
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }
        size++;
    }

    // REMOVE

    /**
     * Removes the item at the given position. There are four cases:
     *   1. it is the only node
     *   2. it is the head
     *   3. it is the tail
     *   4. it is somewhere in the middle
     */
    @Override
    public T remove(int index) throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Cannot remove - the list is empty.");
        }
        checkIndex(index);

        Node<T> target = getNode(index);

        if (size == 1) {
            // case 1: removing the only node leaves an empty list
            head = null;
            tail = null;
        } else if (target == head) {
            // case 2: the second node becomes the new head
            head = head.getNext();
            head.setPrev(null);
        } else if (target == tail) {
            // case 3: the second-last node becomes the new tail
            tail = tail.getPrev();
            tail.setNext(null);
        } else {
            // case 4: join the nodes either side of the target together
            target.getPrev().setNext(target.getNext());
            target.getNext().setPrev(target.getPrev());
        }

        size--;
        return target.getData();
    }

    // GET / COUNT / EMPTY

    @Override
    public T get(int index) throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Cannot get an item - the list is empty.");
        }
        checkIndex(index);
        return getNode(index).getData();
    }

    /**
     * Returns the count straight from the size field. O(1).
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // SEARCHING

    /**
     * Walks from the head comparing each item with equals(). O(n).
     */
    @Override
    public int indexOf(T item) {
        if (item == null) {
            return -1;
        }
        Node<T> current = head;
        int index = 0;

        while (current != null) {
            if (current.getData().equals(item)) {
                return index;
            }
            current = current.getNext();
            index++;
        }
        return -1; // not found
    }

    @Override
    public boolean contains(T item) {
        return indexOf(item) != -1;
    }

    // INDEX CHECKING

    @Override
    public boolean isValidIndex(int index) {
        return index >= 0 && index < size;
    }

    /**
     * Throws an exception if the index is not valid.
     * Used inside remove() and get() so they never go off the end.
     */
    private void checkIndex(int index) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException(
                "Position " + index + " is not valid. List has " + size
                + " item(s), so valid positions are 0 to " + (size - 1) + ".");
        }
    }

    /**
     * Stops null items being added, as they would break equals() checks.
     */
    private void checkNotNull(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add a null item to the list.");
        }
    }

    // HELPER - find the node at a position

    /**
     * Returns the node at the given position.
     * If the index is in the second half, it starts from the tail and
     * walks backwards using prev, so it never walks more than half
     * the list.
     * Assumes the index has already been checked.
     */
    private Node<T> getNode(int index) {
        Node<T> current;

        if (index < size / 2) {
            // first half: start at the head and go forwards
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
        } else {
            // second half: start at the tail and go backwards
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.getPrev();
            }
        }
        return current;
    }

    // DISPLAY

    /**
     * Lists the items from first to last, e.g. [A, B, C].
     */
    @Override
    public String toString() {
        // StringBuilder avoids making a new String every time text is added
        StringBuilder result = new StringBuilder("[");
        Node<T> current = head;

        while (current != null) {
            result.append(current.getData());
            if (current.getNext() != null) {
                result.append(", ");
            }
            current = current.getNext();
        }
        return result.append("]").toString();
    }

    /**
     * Lists the items from last to first using the prev links, e.g. [C, B, A].
     */
    @Override
    public String toStringReverse() {
        StringBuilder result = new StringBuilder("[");
        Node<T> current = tail;

        while (current != null) {
            result.append(current.getData());
            if (current.getPrev() != null) {
                result.append(", ");
            }
            current = current.getPrev();
        }
        return result.append("]").toString();
    }
}
